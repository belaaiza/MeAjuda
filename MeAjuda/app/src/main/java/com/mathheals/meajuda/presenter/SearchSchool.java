package com.mathheals.meajuda.presenter;

import android.content.Context;

import com.mathheals.meajuda.dao.JSONHelper;
import com.mathheals.meajuda.dao.UserDAO;
import com.mathheals.meajuda.model.School;
import com.mathheals.meajuda.model.Topic;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SearchSchool implements SearchStrategy {

    @Override
    public List search(Context context, String query) throws JSONException{

        JSONHelper jsonHelper = JSONHelper.getInstance();
        List<School> schoolList = jsonHelper.schoolListFromName(query);

        return schoolList;
    }
}
