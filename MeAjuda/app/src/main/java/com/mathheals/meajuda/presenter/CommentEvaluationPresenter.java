package com.mathheals.meajuda.presenter;

import android.content.Context;

import com.mathheals.meajuda.dao.CommentEvaluationDAO;

import org.json.JSONException;

public class CommentEvaluationPresenter {
    private static CommentEvaluationPresenter instance;
    private static CommentEvaluationDAO commentEvaluationDAO;
    private Context context;

    private CommentEvaluationPresenter(Context currentContext) {
        this.context = currentContext;
    }

    public static CommentEvaluationPresenter getInstance(final Context context) {
        if(CommentEvaluationPresenter.instance == null){
            CommentEvaluationPresenter.instance = new CommentEvaluationPresenter(context);
            commentEvaluationDAO = commentEvaluationDAO.getInstance(context);

        } else {
            //nothing to do
        }

        return CommentEvaluationPresenter.instance;
    }

    public void evaluateComment(Integer idComment, Integer idTopic, Integer idCategory,
                                Integer evaluation, Integer idUser) throws JSONException {
        commentEvaluationDAO.evaluateComment(idComment, idTopic, idCategory, evaluation, idUser);
    }

    public Integer getCommentEvaluation(Integer idComment) throws JSONException {
        Integer commentEvaluation = commentEvaluationDAO.getCommentEvaluation(idComment);

        return commentEvaluation;
    }
}
