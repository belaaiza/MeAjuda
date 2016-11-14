package com.mathheals.meajuda.presenter;

import android.content.Context;
import com.mathheals.meajuda.dao.TopicEvaluationDAO;

import org.json.JSONException;

public class TopicEvaluationPresenter {

    private static TopicEvaluationPresenter instance;
    private static TopicEvaluationDAO topicEvaluationDAO;
    private Context context;

    private TopicEvaluationPresenter(Context currentContext) {
        this.context = currentContext;
    }

    public static TopicEvaluationPresenter getInstance(final Context context) {
        if(TopicEvaluationPresenter.instance == null){
            TopicEvaluationPresenter.instance = new TopicEvaluationPresenter(context);
            topicEvaluationDAO = TopicEvaluationDAO.getInstance(context);

        } else {
            //nothing to do
        }

        return TopicEvaluationPresenter.instance;
    }

    public void evaluateTopic(Integer idTopic, Integer idCategory, Integer idUserEvaluated,
                              Integer evaluation, Integer idUser) throws JSONException {
        topicEvaluationDAO.evaluateTopic(idTopic, idCategory, idUserEvaluated, evaluation, idUser);
    }

    public Integer getTopicEvaluation(Integer idTopic) throws JSONException {
        Integer topicEvaluation = topicEvaluationDAO.getTopicEvaluation(idTopic);

        return topicEvaluation;
    }
}
