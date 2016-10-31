package com.mathheals.meajuda.presenter;

import android.content.Context;

import com.mathheals.meajuda.dao.CommentDAO;
import com.mathheals.meajuda.dao.TopicDAO;
import com.mathheals.meajuda.model.Comment;
import com.mathheals.meajuda.model.Topic;

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

    public List<Comment> getCommentsOfTopic(int idTopic, Context context){
        List<Comment> allComments;

        allComments = CommentDAO.getInstance(context).getCommentsOfTopic(idTopic);

        return allComments;
    }
}
