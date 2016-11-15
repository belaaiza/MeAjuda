package com.mathheals.meajuda.dao;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

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

    private JSONObject getCommentEvaluationJSONOBjectByUserId(Integer idComment, Integer idUser) {
        final String QUERY = "SELECT * FROM AvaliacaoComentario WHERE Comentario_idComentario = " +
                ""+ idComment +" AND Usuario_idUsuario = "+ idUser +" ";

        JSONObject consultResult = executeConsult(QUERY);

        return consultResult;
    }

    public void evaluateComment(Integer idComment, Integer idTopic, Integer idCategory,
                                Integer idUserEvaluated, Integer evaluation,
                                Integer idUser) throws JSONException {
        JSONObject jsonObjectCommentEvaluation = getCommentEvaluationJSONOBjectByUserId(idComment, idUser);

        String QUERY;

        if(jsonObjectCommentEvaluation == null) {
            Log.d("Entrei aqui", "evaluateComment ");
            QUERY = "INSERT INTO AvaliacaoComentario(descricao, Comentario_idComentario, " +
                    "Topico_idTopico, Topico_Categoria_idCategoria, Comentario_Usuario_idUsuario, " +
                    "Usuario_idUsuario) " +
                    "VALUES("+ evaluation +", "+ idComment +", "+ idTopic +", "+ idCategory +", " +
                    ""+ idUserEvaluated +", "+ idUser +" )";
        }else {
            Integer idEvaluation = jsonObjectCommentEvaluation.getJSONObject("0").getInt("idAvaliacao");

            QUERY = "UPDATE AvaliacaoComentario SET descricao = "+ evaluation +" WHERE " +
                    "idAvaliacao = "+ idEvaluation +"";
        }

        executeQuery(QUERY);
    }

    public Integer getCommentEvaluation(Integer idComment) throws JSONException {
        String QUERY = "SELECT * FROM AvaliacaoComentario WHERE Comentario_idComentario = " + idComment;

        JSONObject consultResult = executeConsult(QUERY);

        Integer commentEvaluation = 0;

        if(consultResult != null) {
            for (int i = 0; i < consultResult.length(); i++) {
                Integer commentEvaluationByUser = consultResult.getJSONObject("" + i).getInt("descricao");

                commentEvaluation += commentEvaluationByUser;
            }
        }

        return commentEvaluation;
    }

    public Integer getCommentEvaluationByUserId(Integer idComment, Integer idUser) {
        JSONObject jsonObjectCommentEvaluation = getCommentEvaluationJSONOBjectByUserId(idComment, idUser);

        Integer evaluation = 0;

        try {
            if(jsonObjectCommentEvaluation != null) {
                jsonObjectCommentEvaluation.getJSONObject("0").getInt("descricao");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return evaluation;
    }

    public void deleteCommentEvaluation(Integer idComment, Integer idUser) {
        final String QUERY = "DELETE FROM AvaliacaoComentario WHERE Comentario_idComentario = "+ idComment +" " +
                "AND Usuario_idUsuario = "+ idUser +"";

        executeQuery(QUERY);
    }

}
