package com.mathheals.meajuda.dao;

import android.app.Activity;
import android.content.Context;

import com.mathheals.meajuda.model.Comment;
import com.mathheals.meajuda.model.Topic;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CommentDAO extends DAO {

    private static CommentDAO instance;

    private CommentDAO(Context currentContext){
        super(currentContext);
    }

    public static CommentDAO getInstance(final Context context) {
        if(CommentDAO.instance == null) {

            CommentDAO.instance = new CommentDAO(context);

        } else {
            //nothing to do
        }

        return CommentDAO.instance;
    }

    public void createComment(Integer idTopic, Integer idCategory, Integer idUser,
                              String description, String imageURL, String audioURL) {
        String QUERY = "INSERT INTO Comentario(Topico_idTopico, " +
                "Topico_Categoria_idCategoria, Usuario_idUsuario, descricao, imagemURL, audioURL) " +
                "VALUES("+ idTopic +", "+ idCategory +", "+ idUser +", " +
                " \" " + description + " \", \" "+ imageURL +" \", \" "+ audioURL +" \")";

        executeQuery(QUERY);
    }

    public String getAudioURLByCommentId(Integer idComment) throws JSONException {
        final String QUERY = "SELECT audioURL FROM Comentario WHERE idComentario = " +
                ""+ idComment + " ";

        JSONObject consultResult = executeConsult(QUERY);

        String audioURL = consultResult.getJSONObject("0").getString("audioURL");

        return audioURL;
    }

    public List<Comment> getCommentsByTopicId(int idTopic) {
        final String SELECT_COMMENTS_QUERY = "SELECT * FROM Comentario WHERE Topico_idTopico = " +
                ""+ idTopic +" ";

        JSONObject consultResult = executeConsult(SELECT_COMMENTS_QUERY);

        List<Comment> comments = new ArrayList<>();

        if(consultResult != null) {
            for (int i = 0; i < consultResult.length(); i++) {
                try{
                    Integer idComment = consultResult.getJSONObject("" + i).
                            getInt("idComentario");
                    Integer idCategory = consultResult.getJSONObject("" + i).
                            getInt("Topico_Categoria_idCategoria");
                    Integer idUser = consultResult.getJSONObject("" + i).getInt("Usuario_idUsuario");
                    String description = consultResult.getJSONObject("" + i)
                            .getString("descricao");
                    String imageURL = consultResult.getJSONObject("" + i)
                            .getString("imagemURL");
                    String audioURL = consultResult.getJSONObject("" + i)
                            .getString("audioURL");

                    Comment comment = new Comment(idComment, idTopic, idCategory, idUser,
                            description, imageURL, audioURL);
                    comments.add(comment);
                } catch(JSONException e){
                    e.printStackTrace();
                }

            }
        } else {
            //Nothing to do
        }

        return comments;
    }
}
