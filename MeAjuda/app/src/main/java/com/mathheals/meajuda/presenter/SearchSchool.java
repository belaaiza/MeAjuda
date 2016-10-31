package com.mathheals.meajuda.presenter;

import android.content.Context;

import com.mathheals.meajuda.dao.JSONHelper;

import org.json.JSONArray;
import org.json.JSONException;

public class SearchSchool implements SearchStrategy {

    @Override
    public JSONArray search(Context context, String query){

        final String URL = "http://mobile-aceite.tcu.gov.br:80/nossaEscolaRS/rest/escolas?nome=" +
                query;
        JSONHelper jsonHelper = JSONHelper.getInstance();
        String result = jsonHelper.getJSONObjectApi(URL);
        JSONArray schoolsAsArray = null;

        try{
            schoolsAsArray = new JSONArray(result);
        } catch(JSONException e){
            e.printStackTrace();
        }

        return schoolsAsArray;
    }
}
