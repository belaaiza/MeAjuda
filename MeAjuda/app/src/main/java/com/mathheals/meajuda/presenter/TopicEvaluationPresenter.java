package com.mathheals.meajuda.presenter;

import android.content.Context;
import com.mathheals.meajuda.dao.TopicEvaluationDAO;

import org.json.JSONException;

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

    public void evaluateTopic(Integer idTopic, Integer idCategory, Integer evaluation,
                              Integer idUser) throws JSONException {
        TopicEvaluationDAO topicEvaluationDAO = TopicEvaluationDAO.getInstance(context);
        topicEvaluationDAO.evaluateTopic(idTopic, idCategory, evaluation, idUser);
    }
}
