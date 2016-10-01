package com.mathheals.meajuda.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mathheals.meajuda.R;
import com.mathheals.meajuda.model.Category;
import com.mathheals.meajuda.presenter.CategoryPresenter;

import org.json.JSONException;

import java.util.Vector;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Setup toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setUpNavigationDrawer(toolbar);

        try{
            fillCategoriesMenu();
        } catch(JSONException e){
            e.printStackTrace();
        }

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

            if((categoriesCount+1)%2 == 0){
                //Creates a layout for the new menu item
                View newItem = getLayoutInflater().inflate
                        (R.layout.categories_menu_item, null);

                //Sets the text of the new menu item as the given category name
                TextView categoryName = (TextView) newItem.findViewById(R.id.categoryName);
                categoryName.setText(categories.get(categoriesCount).getName());

                menuFirstColumn.addView(newItem);
            }
            else{
                //Creates a layout for the new menu item
                View newItem = getLayoutInflater().inflate
                        (R.layout.categories_menu_item, null);

                //Sets the text of the new menu item as the given category name
                TextView categoryName = (TextView) newItem.findViewById(R.id.categoryName);
                categoryName.setText(categories.get(categoriesCount).getName());

                menuSecondColumn.addView(newItem);
            }
        }
    }

    private void setUpNavigationDrawer(Toolbar toolbar){

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if(id == R.id.action_settings){
            return true;
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

                }
                else{
                    if(id == R.id.nav_manage){

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
