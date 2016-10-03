package com.mathheals.meajuda.dao;

import android.app.Activity;
import android.content.Context;
import com.mathheals.meajuda.model.TopicEvaluation;
import org.json.JSONObject;

/**
 * Created by geovanni on 02/10/16.
 */
public class TopicEvaluationDAO extends DAO {

    private static TopicEvaluationDAO instance;

    private TopicEvaluationDAO(Context currentContext) {
        super((Activity) currentContext);
    }

    public static TopicEvaluationDAO getInstance(final Context context) {
        if(TopicEvaluationDAO.instance == null) {

            TopicEvaluationDAO.instance = new TopicEvaluationDAO(context);

        } else {
            //nothing to do
        }

        return TopicEvaluationDAO.instance;
    }
    public void createEvaluateTopic(int evaluation,String description)
    {

        String QUERY = " defaultValue";

        executeQuery(QUERY);
    }

}