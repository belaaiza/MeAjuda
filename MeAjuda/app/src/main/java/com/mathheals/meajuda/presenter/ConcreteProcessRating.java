package com.mathheals.meajuda.presenter;

import android.content.Context;

import com.mathheals.meajuda.dao.CommentEvaluationDAO;
import com.mathheals.meajuda.dao.TopicEvaluationDAO;
import com.mathheals.meajuda.dao.UserDAO;
import com.mathheals.meajuda.model.TopicEvaluation;

import org.json.JSONException;

public class ConcreteProcessRating extends ProcessRating {
    private Context context;
    private Integer evaluationType;
    private Integer userToBeUpdatedId;
    private Integer evaluation;
    private Integer newRating;
    private TopicEvaluation topicEvaluation;


    public ConcreteProcessRating(Context context, TopicEvaluation topicEvaluation) {
        this.context = context;
        this.topicEvaluation = topicEvaluation;
    }

    @Override
    public Integer processEvaluation() {
        if(evaluationType == 0) {
            TopicEvaluationDAO topicEvaluationDAO = TopicEvaluationDAO.getInstance(context);

            try {
                /*topicEvaluationDAO.evaluateTopic(topicEvaluation.getIdTopic(),
                        topicEvaluation.getIdCategory(), topicEvaluation.getEvaluationDescription(),
                        topicEvaluation.getIdUser());*/
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            CommentEvaluationDAO commentEvaluationDAO = CommentEvaluationDAO.getInstance(context);

            //commentEvaluationDAO.evaluateComment(userToBeUpdatedId, evaluation);
        }

        UserDAO userDAO = UserDAO.getInstance(context);

        Integer newRating = null;

        try {
            //Integer rating = userDAO.getUserRatingById(userToBeUpdatedId);

            //newRating = rating + evaluation;
        } catch (Exception e) {
            e.printStackTrace();
        }

        assert  newRating != null;

        this.newRating = newRating;

        return newRating;
    }

    @Override
    public Integer processClassification() {
        UserDAO userDAO = UserDAO.getInstance(context);

        Integer newClassification = null;

        //FIXME
        if(newRating <= 10){
            userDAO.updateUserClassification(1, userToBeUpdatedId);
            newClassification = 1;
        }
        else if(newRating <= 30){
            userDAO.updateUserClassification(2, userToBeUpdatedId);
            newClassification = 2;
        }
        else if(newRating <= 50){
            userDAO.updateUserClassification(3, userToBeUpdatedId);
            newClassification = 3;
        }
        else if(newRating <= 80){
            userDAO.updateUserClassification(4, userToBeUpdatedId);
            newClassification = 4;
        }
        else if(newRating <= 120){
            userDAO.updateUserClassification(5, userToBeUpdatedId);
            newClassification = 5;
        }

        assert newClassification != null;

        return newClassification;
    }
}
