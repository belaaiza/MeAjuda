package com.mathheals.meajuda.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mathheals.meajuda.view.topics.TopicList;
import com.mathheals.meajuda.view.users.UserRegister;

public class TabsAdapter extends FragmentPagerAdapter {


    public TabsAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position){
        switch(position){
            case 0:
                return new UserRegister();
            case 1:
                return new UserRegister();
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
