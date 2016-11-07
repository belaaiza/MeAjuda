package com.mathheals.meajuda.presenter;

import android.content.Context;

import com.mathheals.meajuda.dao.TopicDAO;

import org.json.JSONArray;

public class SearchTopic implements SearchStrategy {

    @Override
    public JSONArray search(Context context, String query){
        TopicDAO topicDAO = TopicDAO.getInstance(context);
        JSONArray topicsFound = topicDAO.searchTopicByTitle(query);

        return topicsFound;
    }
}