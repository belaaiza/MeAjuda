package com.mathheals.meajuda.presenter;

import android.content.Context;

import com.mathheals.meajuda.dao.TopicDAO;
import com.mathheals.meajuda.model.Topic;

import org.json.JSONArray;

import java.util.List;

public class SearchTopic implements SearchStrategy {

    @Override
    public List search(Context context, String query){
        TopicDAO topicDAO = TopicDAO.getInstance(context);
        List<Topic> topicsFound = topicDAO.searchTopicByTitle(query);

        return topicsFound;
    }
}
