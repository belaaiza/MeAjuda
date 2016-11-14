package com.mathheals.meajuda.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mathheals.meajuda.view.schools.SchoolList;
import com.mathheals.meajuda.view.topics.TopicList;

import java.util.List;

public class TabsAdapter extends FragmentPagerAdapter {

    private List topicList;
    private List schoolList;

    public TabsAdapter(FragmentManager fm, List topicList, List schoolList){
        super(fm);
        this.topicList = topicList;
        this.schoolList = schoolList;
    }

    @Override
    public Fragment getItem(int position){
        switch(position){
            case 0:
                return new TopicList(topicList);
            case 1:
                return new SchoolList(schoolList);
        }

        return null;
    }

    @Override
    public int getCount(){
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position){
        switch(position){
            case 0:
                return "Tópicos";
            case 1:
                return "Escolas";
        }
        return null;
    }

}
