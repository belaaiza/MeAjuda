package com.mathheals.meajuda.dao;

import android.content.Context;
import android.util.Log;

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
        final String SELECT_TOPIC_BY_ID_QUERY = "SELECT * FROM Topico WHERE idTopico = "+ idTopic +" ";

        JSONObject consultResult = executeConsult(SELECT_TOPIC_BY_ID_QUERY);

        Integer idOwner = consultResult.getJSONObject("0").getInt("Usuario_idUsuario");
        Integer idCategory = consultResult.getJSONObject("0").getInt("Categoria_idCategoria");

        UserDAO userDAO = UserDAO.getInstance(context);

        String nameOwner = userDAO.getUserNameById(idOwner);
        String title = consultResult.getJSONObject("0").getString("titulo");
        String description = consultResult.getJSONObject("0").getString("descricao");
        String imageURL = consultResult.getJSONObject("0").getString("imagemURL");

        Topic topic = new Topic(idTopic, idCategory, title, description, nameOwner, imageURL);

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

                Topic topic = new Topic(idTopic, idCategory, title, description, nameOwner, imageURL);
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
     * @return List<Topic> - Array of topics found
     */
    public List<Topic> searchTopicByTitle(String title){
        final String QUERY = "SELECT * FROM Topico WHERE titulo LIKE '%"+title+"%'";

        JSONObject topicFound = this.executeConsult(QUERY);
        List<Topic> listTopic = new ArrayList<>();

        try{
            if(topicFound != null){
                for(int i = 0; i < topicFound.length(); i++){
                    UserDAO userDAO = UserDAO.getInstance(context);
                    String nameOwner = userDAO.getUserNameById(topicFound.getJSONObject(i + "")
                            .getInt("Usuario_idUsuario"));

                    Topic topic = new Topic(topicFound.getJSONObject(i + "").getInt("idTopico"),
                            topicFound.getJSONObject(i + "").getString("titulo"),
                            topicFound.getJSONObject(i + "").getString("descricao"),
                            nameOwner);

                    listTopic.add(topic);
                }
            }
        } catch(JSONException e){
            e.printStackTrace();
        }

        return listTopic;
    }

    /**
     * Get all topics from database
     * @return List<Topic> - Array of topics found
     */
    public List<Topic> getAllTopics(){
        final String QUERY = "SELECT * FROM Topico";

        JSONObject topicFound = this.executeConsult(QUERY);
        List<Topic> listTopic = new ArrayList<>();

        try{
            for(int i=0; i<topicFound.length(); i++){
                UserDAO userDAO = UserDAO.getInstance(context);
                String nameOwner = userDAO.getUserNameById(topicFound.getJSONObject(i+"")
                        .getInt("Usuario_idUsuario"));

                Topic topic = new Topic(topicFound.getJSONObject(i+"").getInt("idTopico"),
                        topicFound.getJSONObject(i+"").getString("titulo"),
                        topicFound.getJSONObject(i+"").getString("descricao"),
                        nameOwner);

                listTopic.add(topic);
            }

        } catch(JSONException e){
            e.printStackTrace();
        }

        return listTopic;
    }


}
