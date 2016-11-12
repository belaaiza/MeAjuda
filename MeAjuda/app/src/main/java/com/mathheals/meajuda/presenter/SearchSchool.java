package com.mathheals.meajuda.presenter;

import android.content.Context;

import com.mathheals.meajuda.dao.JSONHelper;
import com.mathheals.meajuda.dao.UserDAO;
import com.mathheals.meajuda.model.Topic;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SearchSchool implements SearchStrategy {

    @Override
    public List search(Context context, String query) throws JSONException{

        final String URL = "http://mobile-aceite.tcu.gov.br:80/nossaEscolaRS/rest/escolas?nome=" +
                query;
        JSONHelper jsonHelper = JSONHelper.getInstance();
        String result = jsonHelper.getJSONObjectApi(URL);
        JSONObject resultAsJSON = new JSONObject(result);

        List listTopic = new ArrayList();

        try{
            for(int i=0; i<resultAsJSON.length(); i++){
                UserDAO userDAO = UserDAO.getInstance(context);
                String nameOwner = userDAO.getUserNameById(resultAsJSON.getJSONObject(i+"")
                        .getInt("Usuario_idUsuario"));

                Topic topic = new Topic(resultAsJSON.getJSONObject(i+"").getInt("idTopico"),
                        resultAsJSON.getJSONObject(i+"").getString("titulo"),
                        resultAsJSON.getJSONObject(i+"").getString("descricao"),
                        nameOwner);

                listTopic.add(topic);
            }

        } catch(JSONException e){
            e.printStackTrace();
        }

        return listTopic;
    }
}
