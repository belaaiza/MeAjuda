package com.mathheals.meajuda.presenter;

import android.content.Context;

import com.mathheals.meajuda.dao.TopicDAO;
import com.mathheals.meajuda.model.Comment;
import com.mathheals.meajuda.model.Topic;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class TopicPresenter {

    private static TopicPresenter instance;
    private Context context;
    private static TopicDAO topicDAO;

    private TopicPresenter(Context currentContext) {
        this.context = currentContext;
    }

    public static TopicPresenter getInstance(final Context context) {
        if(TopicPresenter.instance != null){
            //nothing to do
        } else {
            TopicPresenter.instance = new TopicPresenter(context);
            topicDAO = TopicDAO.getInstance(context);
        }

        return TopicPresenter.instance;
    }

    public void createTopic(int categoryId, String title, String description){
        topicDAO.createTopic(categoryId, title, description);
    }

    public List<Topic> getTopicsByCategory(int idCategory) {
        List<Topic> topics = new ArrayList<>();

        try {
            topics = topicDAO.getTopicsByCategory(idCategory);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return topics;
    }

    public Topic getTopicById(int idTopic) {
        Topic topic = null;

        try {
            topic = topicDAO.getTopicById(idTopic);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return topic;
    }
}
