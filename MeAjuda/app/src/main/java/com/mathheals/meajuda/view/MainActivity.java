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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mathheals.meajuda.R;
import com.mathheals.meajuda.model.Category;
import com.mathheals.meajuda.presenter.CategoryPresenter;
import com.mathheals.meajuda.view.topics.TopicCreation;
import com.mathheals.meajuda.view.topics.TopicList;
import com.mathheals.meajuda.view.users.LoginActivity;
import com.mathheals.meajuda.view.users.UserUpdate;
import com.mathheals.meajuda.view.users.ViewProfile;

import org.json.JSONException;

import java.util.Vector;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    private SharedPreferences session;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Setup toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.session = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        setUpNavigationDrawer(toolbar);

        try{
            fillCategoriesMenu();
        } catch(JSONException e){
            e.printStackTrace();
        }

    }

    public void editActions(View view){
        TextView idCategory = (TextView)view.findViewById(R.id.idCategory);

        Bundle args = new Bundle();
        args.putInt("idCategory", Integer.parseInt(idCategory.getText().toString()));

        TopicList topicList = new TopicList();
        topicList.setArguments(args);

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

    private void setUpNavigationDrawer(Toolbar toolbar){

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if(session != null && session.getBoolean("IsLoggedIn", false)){
            navigationView.getMenu().findItem(R.id.nav_manage).setVisible(true);
        }


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
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if(id == R.id.action_settings){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
            return true;
        } else if(id == R.id.create_topic){
            TopicCreation topicCreation = new TopicCreation();
            openFragment(topicCreation);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item){
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if(id == R.id.nav_camera){
            // Handle the camera action
        }
        else{
            if(id == R.id.nav_gallery){

            }
            else{
                if(id == R.id.nav_slideshow){
                    UserUpdate userUpdate = new UserUpdate();
                    openFragment(userUpdate);
                }
                else{
                    if(id == R.id.nav_manage){
                        ViewProfile viewProfile = new ViewProfile();
                        openFragment(viewProfile);
                    }
                    else{
                        if(id == R.id.nav_share){

                        }
                        else{
                            if(id == R.id.nav_send){

                            }
                        }
                    }
                }
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
