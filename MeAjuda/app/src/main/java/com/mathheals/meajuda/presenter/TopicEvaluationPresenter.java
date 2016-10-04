package com.mathheals.meajuda.presenter;

import android.content.Context;
import com.mathheals.meajuda.dao.TopicEvaluationDAO;

public class TopicEvaluationPresenter {

    private static TopicEvaluationPresenter instance;
    private Context context;

    private TopicEvaluationPresenter(Context currentContext) {
        this.context = currentContext;
    }

    public static TopicEvaluationPresenter getInstance(final Context context) {
        if(TopicEvaluationPresenter.instance == null){

            TopicEvaluationPresenter.instance = new TopicEvaluationPresenter(context);

        } else {
            //nothing to do
        }

        return TopicEvaluationPresenter.instance;
    }

    public void createEvaluation(int categoryId, String title, String description){
        TopicEvaluationDAO topicDAO = TopicEvaluationDAO.getInstance(context);


    }
}
