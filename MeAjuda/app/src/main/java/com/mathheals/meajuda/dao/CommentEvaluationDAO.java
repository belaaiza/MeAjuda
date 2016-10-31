package com.mathheals.meajuda.dao;

import android.content.Context;

public class CommentEvaluationDAO extends DAO {
    private static CommentEvaluationDAO instance;

    private CommentEvaluationDAO(Context currentContext) {
        super(currentContext);
    }

    public static CommentEvaluationDAO getInstance(final Context context) {
        if(CommentEvaluationDAO.instance == null) {

            CommentEvaluationDAO.instance = new CommentEvaluationDAO(context);

        } else {
            //nothing to do
        }

        return CommentEvaluationDAO.instance;
    }

    public void evaluateComment(Integer userToBeEvaluatedId, Integer evaluation) {

    }
}
