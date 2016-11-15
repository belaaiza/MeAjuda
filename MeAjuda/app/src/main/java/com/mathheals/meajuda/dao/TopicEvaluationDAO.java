package com.mathheals.meajuda.dao;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import org.json.JSONException;
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

    private JSONObject getTopicEvaluationJSONOBjectByUserId(Integer idTopic, Integer idUser) {
        final String QUERY = "SELECT * FROM AvaliacaoTopico WHERE Topico_idTopico = "+ idTopic +" " +
                "AND" + " Usuario_idUsuario = "+ idUser +" ";

        Log.d("id do topico na dao", idTopic + "");
        Log.d("id do usuario na dao", idUser + "");

        JSONObject consultResult = executeConsult(QUERY);

        return consultResult;
    }

    public void evaluateTopic(Integer idTopic, Integer idCategory, Integer idUserEvaluated,
                              Integer evaluation, Integer idUser) throws JSONException {
        JSONObject jsonObjectTopicEvaluation = getTopicEvaluationJSONOBjectByUserId(idTopic, idUser);

        String QUERY;

        if(jsonObjectTopicEvaluation == null) {
            Log.d("Entrei aqui", "evaluateTopic ");
            QUERY = "INSERT INTO AvaliacaoTopico(descricao, Topico_idTopico, " +
                    "Topico_Categoria_idCategoria, Topico_Usuario_idUsuario, Usuario_idUsuario) " +
                    "VALUES("+ evaluation +", "+ idTopic +", "+ idCategory +", " +
                    ""+ idUserEvaluated +", "+ idUser +" )";
        }else {
            Integer idEvaluation = jsonObjectTopicEvaluation.getJSONObject("0").getInt("idAvaliacao");

            QUERY = "UPDATE AvaliacaoTopico SET descricao = "+ evaluation +" WHERE " +
                    "idAvaliacao = "+ idEvaluation +"";
        }

        executeQuery(QUERY);
    }

    public Integer getTopicEvaluation(Integer idTopic) throws JSONException {
        String QUERY = "SELECT * FROM AvaliacaoTopico WHERE Topico_idTopico = " + idTopic;

        JSONObject consultResult = executeConsult(QUERY);

        Integer topicEvaluation = 0;

        if(consultResult != null) {
            for (int i = 0; i < consultResult.length(); i++) {
                Integer topicEvaluationByUser = consultResult.getJSONObject("" + i).getInt("descricao");

                topicEvaluation += topicEvaluationByUser;
            }
        }

        return topicEvaluation;
    }

    public Integer getTopicEvaluationByUserId(Integer idTopic, Integer idUser) {
        JSONObject jsonObjectTopicEvaluation = getTopicEvaluationJSONOBjectByUserId(idTopic, idUser);

        Integer evaluation = 0;

        try {
            if(jsonObjectTopicEvaluation != null) {
                evaluation = jsonObjectTopicEvaluation.getJSONObject("0").getInt("descricao");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return evaluation;
    }

    public void deleteTopicEvaluation(Integer idTopic, Integer idUser) {
        final String QUERY = "DELETE FROM AvaliacaoTopico WHERE Topico_idTopico = "+ idTopic +" " +
                "AND Usuario_idUsuario = "+ idUser +"";

        executeQuery(QUERY);
    }
}
