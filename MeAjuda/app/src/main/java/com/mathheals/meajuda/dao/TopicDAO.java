package com.mathheals.meajuda.dao;

import android.content.Context;

import com.mathheals.meajuda.model.Topic;

import org.json.JSONArray;
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

    public void createTopic(int idCategory, String title, String description, String imageURL) {
        final String QUERY;

        QUERY = "INSERT INTO Topico(descricao, Categoria_idCategoria, titulo, Usuario_idUsuario, " +
                "imagemURL) "+
            "VALUES(\" "+ description +" \", "+ idCategory +", \" "+ title +" \", \" 6 \", " +
                "\" " + imageURL +" \")";

        executeQuery(QUERY);

    }

    public Topic getTopicById(int idTopic) throws JSONException {
        final String SELECT_TOPIC_BY_ID_QUERY = "SELECT titulo, descricao, imagemURL, " +
                "Usuario_idUsuario FROM Topico WHERE idTopico = "+ idTopic +" ";

        JSONObject consultResult = executeConsult(SELECT_TOPIC_BY_ID_QUERY);

        int idOwner = consultResult.getJSONObject("0").getInt("Usuario_idUsuario");

        UserDAO userDAO = UserDAO.getInstance(context);

        String nameOwner = userDAO.getUserNameById(idOwner);
        String title = consultResult.getJSONObject("0").getString("titulo");
        String description = consultResult.getJSONObject("0").getString("descricao");
        String imageURL = consultResult.getJSONObject("0").getString("imagemURL");

        Topic topic = new Topic(idTopic, title, description, nameOwner, imageURL);

        return topic;
    }

    public List<Topic> getTopicsByCategory(int idCategory) throws JSONException {
        final String SELECT_ALL_TOPICS_QUERY = "SELECT idTopico, titulo, descricao, imagemURL, " +
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

                String imageURL = consultResult.getJSONObject("" + i).getString("imagemURL");

                Topic topic = new Topic(idTopic, title, description, nameOwner, imageURL);
                topics.add(topic);
            }
        } else {
            //Nothing to do
        }

        return topics;
    }

    /**
     * Searches an topic at database by his title
     * @param title - The title or part of the title of a topic
     * @return JSONArray - Array of topics found
     */
    public JSONArray searchTopicByTitle(String title){
        final String QUERY = "SELECT * FROM Topico WHERE titulo =\"" + title + "\"";

        JSONObject topicFound = this.executeConsult(QUERY);
        JSONArray topicsAsArray = null;

        try{
            topicsAsArray = new JSONArray(topicFound);
        } catch(JSONException e){
            e.printStackTrace();
        }

        return topicsAsArray;
    }

}
