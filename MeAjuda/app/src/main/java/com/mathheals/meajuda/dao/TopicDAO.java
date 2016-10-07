package com.mathheals.meajuda.dao;

import android.content.Context;

import com.mathheals.meajuda.model.Topic;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TopicDAO extends DAO {

    private static TopicDAO instance;
    Context context;

    private TopicDAO(Context currentContext) {
        super(currentContext);
        this.context = currentContext;
    }

    public static TopicDAO getInstance(final Context context) {
        if(TopicDAO.instance != null) {
            //Nothing to do
        } else {
            TopicDAO.instance = new TopicDAO(context);
        }

        return TopicDAO.instance;
    }

    public void createTopic(int idCategory, String title, String description) {
        final String QUERY;

        QUERY = "INSERT INTO Topico(descricao, Categoria_idCategoria, titulo) " +
            "VALUES(\" "+ description +" \", "+ idCategory +", \" "+ title +" \")";

        executeQuery(QUERY);

    }

    public Topic getTopicById(int idTopic) throws JSONException {
        final String SELECT_TOPIC_BY_ID_QUERY = "SELECT titulo, descricao, " +
                "Usuario_idUsuario FROM Topico WHERE idTopico = "+ idTopic +" ";

        JSONObject consultResult = executeConsult(SELECT_TOPIC_BY_ID_QUERY);

        int idOwner = consultResult.getJSONObject("0").getInt("Usuario_idUsuario");

        UserDAO userDAO = UserDAO.getInstance(context);

        String nameOwner = userDAO.getUserNameById(idOwner);
        String title = consultResult.getJSONObject("0").getString("titulo");
        String description = consultResult.getJSONObject("0").getString("descricao");

        Topic topic = new Topic(idTopic, title, description, nameOwner);

        return topic;
    }

    public List<Topic> getTopicsByCategory(int idCategory) throws JSONException {
        final String SELECT_ALL_TOPICS_QUERY = "SELECT idTopico, titulo, descricao, " +
                "Usuario_idUsuario FROM Topico WHERE Categoria_idCategoria = "+ idCategory +" ";

        JSONObject consultResult = executeConsult(SELECT_ALL_TOPICS_QUERY);

        List<Topic> topics = new ArrayList<>();

        UserDAO userDAO = UserDAO.getInstance(context);

        if(consultResult != null) {
            for (int i = 0; i < consultResult.length(); i++) {
                int idTopic = consultResult.getJSONObject("" + i).getInt("idTopico");
                int idOwner = consultResult.getJSONObject("" + i).getInt("Usuario_idUsuario");

                String title = consultResult.getJSONObject("" + i).getString("titulo");
                String description = consultResult.getJSONObject("" + i).getString("descricao");
                String nameOwner = userDAO.getUserNameById(idOwner);

                Topic topic = new Topic(idTopic, title, description, nameOwner);
                topics.add(topic);
            }
        } else {
            //Nothing to do
        }

        return topics;
    }


}
