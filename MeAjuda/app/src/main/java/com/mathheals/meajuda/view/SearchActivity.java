package com.mathheals.meajuda.view;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.mathheals.meajuda.R;
import com.mathheals.meajuda.model.School;
import com.mathheals.meajuda.model.Topic;
import com.mathheals.meajuda.presenter.*;
import com.mathheals.meajuda.view.schools.SchoolList;
import com.mathheals.meajuda.view.topics.TopicList;
import com.mathheals.meajuda.view.topics.TopicView;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener,
        TabLayout.OnTabSelectedListener {

    private Integer currentTab;
    private TabLayout tabs;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        List<Topic> topicList = TopicPresenter.getInstance(getBaseContext()).getAllTopics();
        List<School> schoolList = null;
        try{
            schoolList = SchoolPresenter.getInstance().getAllSchools();
        } catch(JSONException e){
            e.printStackTrace();
        }

        TabsAdapter tabsAdapter = new TabsAdapter(this.getSupportFragmentManager(), topicList,
                schoolList);

        // Set up the ViewPager with the sections adapter.
        viewPager = (ViewPager) findViewById(R.id.container);
        viewPager.setAdapter(tabsAdapter);

        tabs = (TabLayout) this.findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);

        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(this);

        MenuItem searchItem = menu.findItem(R.id.menu_search);
        searchItem.expandActionView();

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

    @Override
    public boolean onQueryTextSubmit(String query){
        Integer currentTab = tabs.getSelectedTabPosition();

        Fragment currentFragment = getSupportFragmentManager().findFragmentByTag(
                "android:switcher:" + R.id.container + ":" + viewPager.getCurrentItem());

        switch(currentTab){
            case 0:
                SearchTopic searchTopic = new SearchTopic();
                List topicList = searchTopic.search(getBaseContext(), query);

                TopicList topicListFragment = (TopicList) currentFragment;
                topicListFragment.getAdapater().updateList(topicList);

                break;
            case 1:
                SearchSchool searchSchool = new SearchSchool();
                List schoolList = null;

                try{
                    schoolList = searchSchool.search(getBaseContext(), query);
                } catch(JSONException e){
                    e.printStackTrace();
                }

                SchoolList schoolListFragment = (SchoolList) currentFragment;
                schoolListFragment.getAdapater().updateList(schoolList);
                break;
        }

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText){
        return false;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab){
        currentTab = tab.getPosition();
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab){

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab){

    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }
}
