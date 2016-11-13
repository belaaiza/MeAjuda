package com.mathheals.meajuda.presenter;

import android.content.Context;

import com.mathheals.meajuda.dao.CommentDAO;
import com.mathheals.meajuda.model.Comment;

import java.util.List;

/**
 * Created by izabela on 16/10/16.
 */
public class CommentPresenter {

    private static CommentPresenter instance;
    private static Context context;

    private CommentPresenter(Context currentContext) {
        this.context = currentContext;
    }

    public static CommentPresenter getInstance(final Context context) {
        if(CommentPresenter.instance != null){
            //nothing to do
        } else {
            CommentPresenter.instance = new CommentPresenter(context);
        }

        return CommentPresenter.instance;
    }

    public List<Comment> getCommentsOfTopic(int idTopic){
        List<Comment> allComments;

        allComments = CommentDAO.getInstance(context).getCommentsByTopicId(idTopic);

        return allComments;
    }

    public void createComment(Integer idTopic, Integer idCategory, String comment) {
        CommentDAO commentDAO = CommentDAO.getInstance(context);
        commentDAO.createComment(idTopic, idCategory, comment);
    }
}
