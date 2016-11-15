package com.mathheals.meajuda.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.design.widget.NavigationView;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mathheals.meajuda.R;
import com.mathheals.meajuda.exception.UserException;
import com.mathheals.meajuda.model.Category;
import com.mathheals.meajuda.model.School;
import com.mathheals.meajuda.model.Topic;
import com.mathheals.meajuda.model.User;
import com.mathheals.meajuda.presenter.CategoryPresenter;
import com.mathheals.meajuda.presenter.SchoolPresenter;
import com.mathheals.meajuda.presenter.TopicPresenter;
import com.mathheals.meajuda.presenter.UserPresenter;
import com.mathheals.meajuda.view.schools.SchoolList;
import com.mathheals.meajuda.view.schools.SchoolView;
import com.mathheals.meajuda.view.topics.TopicCreation;
import com.mathheals.meajuda.view.topics.TopicList;
import com.mathheals.meajuda.view.topics.TopicView;
import com.mathheals.meajuda.view.users.LoginActivity;
import com.mathheals.meajuda.view.users.UserList;
import com.mathheals.meajuda.view.users.UserUpdate;
import com.mathheals.meajuda.view.users.ViewProfile;
import com.sun.jna.platform.win32.Netapi32Util;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class MainActivity extends AppCompatActivity implements NavigationView
        .OnNavigationItemSelectedListener {

    private SharedPreferences session;
    private Toolbar toolbar;
    private MenuItem createTopic;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Setup toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.session = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        try{
            setUpNavigationDrawer(toolbar);
        } catch(JSONException e){
            e.printStackTrace();
        } catch(UserException e){
            e.printStackTrace();
        }

        try{
            fillCategoriesMenu();
        } catch(JSONException e){
            e.printStackTrace();
        }

        if(getIntent().getExtras() != null ){
            String whichFragment = getIntent().getExtras().getString("whichFragment");

            if(whichFragment.equals("topic")){
                Integer idTopic = getIntent().getExtras().getInt("idTopic");
                Bundle bundle = new Bundle();
                bundle.putInt("idTopic", idTopic);
                bundle.putBoolean("comeFromSearch", true);

                TopicView topicView = new TopicView();
                topicView.setArguments(bundle);

                openFragment(topicView, "TopicViewFragment");
            }
            else if(whichFragment.equals("school")){
                School school = new Gson().fromJson(
                        getIntent().getExtras().getString("school"), School.class);
                SchoolView schoolView = new SchoolView(school);

                Bundle bundle = new Bundle();
                bundle.putBoolean("comeFromSearch", true);
                schoolView.setArguments(bundle);

                openFragment(schoolView, "SchoolViewFragment");
            }
            else if(whichFragment.equals("user")){
                    User user = new Gson().fromJson(getIntent().getExtras().getString("user"),
                            User.class);
                    ViewProfile viewProfile = new ViewProfile(user);

                    Bundle bundle = new Bundle();
                    bundle.putBoolean("comeFromSearch", true);

                    viewProfile.setArguments(bundle);
                    openFragment(viewProfile);
            }

        }

    }

    public void editActions(View view){
        TextView idCategoryTextView = (TextView)view.findViewById(R.id.idCategory);

        Integer idCategory = Integer.parseInt(idCategoryTextView.getText().toString());

        assert(idCategory != 0);

        TopicPresenter topicPresenter = TopicPresenter.getInstance(getBaseContext());
        List<Topic> data = topicPresenter.getTopicsByCategory(idCategory);

        TopicList topicList = new TopicList(data);
        openFragment(topicList);
    }

    private void fillCategoriesMenu() throws JSONException{
        CategoryPresenter categoryPresenter = CategoryPresenter
                .getInstance(getApplicationContext());

        Vector<Category> categories = categoryPresenter.getAllCategories(getApplicationContext());

        if(categories != null){
            insertCategoriesOnLayout(categories);
        }else{
            Toast.makeText(getApplicationContext(), "Um problema ocorreu", Toast.LENGTH_SHORT)
                    .show();
        }
    }

    private void insertCategoriesOnLayout(Vector<Category> categories){
        //Gets the layout of the two columns of categories menu
        LinearLayout menuFirstColumn = (LinearLayout) findViewById(R.id.menuFirstColumn);
        LinearLayout menuSecondColumn = (LinearLayout) findViewById(R.id.menuSecondColumn);

        for(int categoriesCount = 0; categoriesCount < categories.size(); categoriesCount++){

            //Gets current category on the for loop
            Category currentCategory = categories.get(categoriesCount);

            //Creates a layout for the new menu item
            View newItem = getLayoutInflater().inflate
                    (R.layout.categories_menu_item, null);

            setUpItemMenuView(newItem, currentCategory);

            if((categoriesCount+1)%2 == 0){
                menuFirstColumn.addView(newItem);
            }
            else{
                menuSecondColumn.addView(newItem);
            }
        }
    }

    private void setUpItemMenuView(View itemMenuCategory, Category categoryInfo) {
        //Sets the layout background
        FrameLayout imageFrame = (FrameLayout) itemMenuCategory.findViewById(R.id.imageFrame);
        imageFrame.setBackgroundColor(Color.parseColor(categoryInfo.getColor()));

        //Sets the text of the new menu item as the given category name
        TextView categoryName = (TextView) itemMenuCategory.findViewById(R.id.categoryName);
        categoryName.setText(categoryInfo.getName());

        //Sets the category id
        TextView categoryId = (TextView) itemMenuCategory.findViewById(R.id.idCategory);
        categoryId.setText(categoryInfo.getIdCategory() + "");

        //Sets the category icon
        ImageView categoryIcon = (ImageView) itemMenuCategory.findViewById(R.id.categoryIcon);


        int drawableId = getResources().getIdentifier(categoryInfo.getIconName(), "drawable",
                getPackageName());
        Drawable icon = ResourcesCompat.getDrawable(getResources(), drawableId,
                getTheme());
        categoryIcon.setImageDrawable(icon);
    }

    private void setUpNavigationDrawer(Toolbar toolbar) throws JSONException, UserException{

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close) {

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset){
                try{

                    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
                    View headerLayout = navigationView.getHeaderView(0);
                    ImageView imageView = (ImageView) headerLayout.findViewById(R.id.imageView);

                    if(session != null && session.getBoolean("IsLoggedIn", false)){
                        UserPresenter userPresenter = UserPresenter.getInstance(getBaseContext());
                        Drawable userPhoto = userPresenter.getClassificationIcon(userPresenter
                                .getUserClassification(session.getInt(getBaseContext().getResources()
                                        .getString(R.string.key_id), -1)));

                        imageView.setImageDrawable(userPhoto);

                        navigationView.getMenu().findItem(R.id.edit_profile).setVisible(true);
                        navigationView.getMenu().findItem(R.id.show_profile).setVisible(true);
                    }
                    else{
                        Drawable userPhoto = ResourcesCompat.getDrawable(getResources(),
                                R.drawable.logo, getTheme());
                        imageView.setImageDrawable(userPhoto);

                        navigationView.getMenu().findItem(R.id.edit_profile).setVisible(false);
                        navigationView.getMenu().findItem(R.id.show_profile).setVisible(false);
                    }

                } catch(JSONException e){
                    e.printStackTrace();
                } catch(UserException e){
                    e.printStackTrace();
                }
            }

        };

        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void openFragment(Fragment fragmentToBeOpen, String tag){
        android.support.v4.app.FragmentTransaction fragmentTransaction =
                getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.layout_main, fragmentToBeOpen, tag);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void openFragment(Fragment fragmentToBeOpen){
        android.support.v4.app.FragmentTransaction fragmentTransaction =
                getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.layout_main, fragmentToBeOpen);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed(){
        final TopicView topicView = (TopicView)
                getSupportFragmentManager().findFragmentByTag("TopicViewFragment");

        final SchoolView schoolView = (SchoolView)
                getSupportFragmentManager().findFragmentByTag("SchoolViewFragment");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
        else if((topicView != null && topicView.getArguments().getBoolean("comeFromSearch")
                && topicView.isVisible()) || (schoolView != null &&
                schoolView.getArguments().getBoolean("comeFromSearch") && schoolView.isVisible())){
            finish();
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        MenuItem menuItem = menu.findItem(R.id.action_settings);
        createTopic = menu.findItem(R.id.create_topic);
        if(session.getBoolean("IsLoggedIn", false)){
            menuItem.setTitle("Sair");
            createTopic.setVisible(true);
        }
        else{
            menuItem.setTitle("Entrar");
            createTopic.setVisible(false);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        int id = item.getItemId();

        if(id == R.id.action_settings){
            if(session.getBoolean("IsLoggedIn", false)){
                item.setTitle("Entrar");
                createTopic.setVisible(false);
                UserPresenter userPresenter = UserPresenter.getInstance(getBaseContext());
                userPresenter.finishLoginSession(session);
            }
            else {
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
            return true;
        } else if(id == R.id.create_topic){
            TopicCreation topicCreation = new TopicCreation();
            openFragment(topicCreation);
        } else if (id == R.id.search){
            Intent intent = new Intent(this, SearchActivity.class);
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item){
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if(id == R.id.user_ranking){
            UserPresenter userPresenter = UserPresenter.getInstance(getBaseContext());

            List<User> userRanking = new ArrayList<>();

            try {
                userRanking = userPresenter.getUserRanking();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (UserException e) {
                e.printStackTrace();
            }

            UserList userList = new UserList(userRanking);

            openFragment(userList);
        }
        else if(id == R.id.school_ranking){
            SchoolPresenter schoolPresenter = SchoolPresenter.getInstance(getBaseContext());

            List<School> schoolRanking = new ArrayList<>();

            try {
                schoolRanking = schoolPresenter.getSchoolRanking();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            SchoolList schoolList = new SchoolList(schoolRanking);

            openFragment(schoolList);
        }
        else if(id == R.id.edit_profile){
            UserUpdate userUpdate = new UserUpdate();
            openFragment(userUpdate);
        }
        else if(id == R.id.show_profile){
            Bundle bundle = new Bundle();
            bundle.putBoolean("isOwnProfile", true);

            UserPresenter userPresenter = UserPresenter.getInstance(getBaseContext());
            User user = userPresenter.showProfile(session.getString("email", ""), this);

            ViewProfile viewProfile = new ViewProfile(user);
            viewProfile.setArguments(bundle);
            openFragment(viewProfile);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void searchAction(){
        onSearchRequested();
    }
}
