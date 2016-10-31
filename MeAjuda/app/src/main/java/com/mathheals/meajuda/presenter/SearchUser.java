package com.mathheals.meajuda.presenter;

import android.content.Context;

import com.mathheals.meajuda.dao.UserDAO;

import org.json.JSONArray;

public class SearchUser implements SearchStrategy {

    @Override
    public JSONArray search(Context context, String query){
        UserDAO userDAO = UserDAO.getInstance(context);
        JSONArray usersFound = userDAO.searchUserByName(query);

        return usersFound;
    }
}
