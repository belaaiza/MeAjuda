package com.mathheals.meajuda.presenter;

import android.content.Context;

import com.mathheals.meajuda.dao.UserDAO;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class SearchUser implements SearchStrategy {

    @Override
    public List search(Context context, String query){
        UserDAO userDAO = UserDAO.getInstance(context);
        JSONArray usersFound = userDAO.searchUserByName(query);
        List list = new ArrayList();
        return list;
    }
}
