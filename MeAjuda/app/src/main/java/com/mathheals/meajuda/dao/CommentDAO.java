package com.mathheals.meajuda.dao;

import android.app.Activity;
import android.content.Context;

import com.mathheals.meajuda.model.Comment;
import com.mathheals.meajuda.model.Topic;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by geovanni on 02/10/16.
 */
public class CommentDAO extends DAO {

    private static CommentDAO instance;

    private CommentDAO(Context currentContext) {
        super((Activity) currentContext);
    }

    public static CommentDAO getInstance(final Context context) {
        if(CommentDAO.instance == null) {

            CommentDAO.instance = new CommentDAO(context);

        } else {
            //nothing to do
        }

        return CommentDAO.instance;
    }
    public void createComment(final int idPai,final int Topic_idTopic,final int Topic_Category_idCategory)
    {

        String QUERY = "INSERT INTO `1233593`.`Comentario` (`idComentario`, `idPai`, `Topico_idTopico`,"+
                                                     "`Topico_Categoria_idCategoria`) VALUES (NULL,"+
                                                     " "+ idPai +" \", "+ Topic_idTopic +", \" "+ Topic_Category_idCategory +
                                                     " \")";

        executeQuery(QUERY);
    }

    public List<Comment> getCommentsOfTopic(int idTopic, Context context) {
        final String SELECT_COMMENTS_QUERY = "SELECT * FROM Comentario WHERE idPai= "+ idTopic +" ";

        JSONObject consultResult = executeConsult(SELECT_COMMENTS_QUERY);

        List<Comment> comments = new ArrayList<>();

        UserDAO userDAO = UserDAO.getInstance(context);

        String commentDescription = null;

        if(consultResult != null) {
            for (int i = 0; i < consultResult.length(); i++) {
                try{
                    commentDescription = consultResult.getJSONObject("" + i)
                            .getString("descricao");
                } catch(JSONException e){
                    e.printStackTrace();
                }

                Comment comment = new Comment(idTopic, commentDescription);
                comments.add(comment);
            }
        } else {
            //Nothing to do
        }

        return comments;
    }
}
