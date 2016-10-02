package com.mathheals.meajuda.presenter;

import android.content.Context;

import com.mathheals.meajuda.dao.TopicDAO;

public class TopicPresenter {

    private static TopicPresenter instance;
    private Context context;

    private TopicPresenter(Context currentContext) {
        this.context = currentContext;
    }

    public static TopicPresenter getInstance(final Context context) {
        if(TopicPresenter.instance != null){
            //nothing to do
        } else {
            TopicPresenter.instance = new TopicPresenter(context);
        }

        return TopicPresenter.instance;
    }

    public void createTopic(int categoryId, String title, String description){
        TopicDAO topicDAO = TopicDAO.getInstance(context);

        topicDAO.createTopic(categoryId, title, description);
    }
}
