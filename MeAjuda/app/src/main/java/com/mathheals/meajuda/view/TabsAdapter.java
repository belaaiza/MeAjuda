package com.mathheals.meajuda.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mathheals.meajuda.view.schools.SchoolList;
import com.mathheals.meajuda.view.topics.TopicList;
import com.mathheals.meajuda.view.users.UserList;

import java.util.List;

public class TabsAdapter extends FragmentPagerAdapter {

    private List topicList;
    private List schoolList;
    private List userList;

    public TabsAdapter(FragmentManager fm, List topicList, List schoolList, List userList){
        super(fm);
        this.topicList = topicList;
        this.schoolList = schoolList;
        this.userList = userList;
    }

    @Override
    public Fragment getItem(int position){
        switch(position){
            case 0:
                return new TopicList(topicList);
            case 1:
                return new SchoolList(schoolList);
            case 2:
                return new UserList(userList);
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
            case 2:
                return "Usuários";
        }
        return null;
    }

}
