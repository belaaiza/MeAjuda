package com.mathheals.meajuda.dao;

import android.content.Context;
import android.util.Log;

import com.mathheals.meajuda.exception.UserException;
import com.mathheals.meajuda.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UserDAO extends DAO {

    private static UserDAO instance;

    /**
     * Constructs DAO with the current context
     * @param currentContext Current context
     */
    private UserDAO(Context currentContext){
        super(currentContext);
    }

    /**
     * Get the current instance or create a new if none was created
     * @param context - Actual context of the application
     * @return UserDAO - The current or new UserDAO instance
     */
    public static UserDAO getInstance(final Context context) {
        if (UserDAO.instance != null) {
            //nothing to do
        } else {
            UserDAO.instance = new UserDAO(context);
        }
        return UserDAO.instance;
    }

    /**
     * Saves an user on the database
     * @param user - User to be saved
     * @return String - Returns a text confirming if the query was executed with success
     */
    public String saveUser(User user){

        final String QUERY = "INSERT INTO Usuario(nome, sobrenome, email, login, senha, rating, " +
                "Escola_idEscola, Classificacao_idClassificacao)VALUES (\" " + user.getFirstName() +
                " \", \" " + user.getLastName() + " \", \" " + user.getEmail() + " \", \" " +
                user.getUsername() + " \", \" " + user.getPassword() + " \", " + user.getRating() +
                ", " + user.getIdSchool() + ", " + user.getIdClassification() + ")";

        Log.d("Final Query", QUERY);

        String queryStatus = this.executeQuery(QUERY);
        Log.d("User save status", queryStatus);

        return queryStatus;
    }

    /**
     * Updates the user's data on the database
     * @param user
     * @return String - Returns a text confirming if the query was executed with success
     */
    public String updateUser(User user){

        final String QUERY = "UPDATE Usuario SET nome=\"" + user.getFirstName() + "\", " +
                "sobrenome=\"" + user.getLastName() + "\", " + "email=\"" + user.getEmail() +
                "\", " + "login=\"" + user.getUsername() + "\", " + "senha=\"" + user.getPassword()
                + "\"" + " WHERE idUsuario=" + user.getUserId() + " ";

        Log.d("Final Query", QUERY);

        String queryStatus = this.executeQuery(QUERY);
        Log.d("User update status", queryStatus);

        return queryStatus;
    }

    /**
     * Searches an user at database by his login name
     * @param login - The login of an user
     * @return JSONObject - Returns a JSONObject with the results of the consult
     */
    public JSONObject searchUserByLoginName(String login){
        final String QUERY = "SELECT * FROM Usuario WHERE login =\"" + login + "\"";
        Log.d("FinalLoginSearchQuery", QUERY);

        JSONObject userData = this.executeConsult(QUERY);

        return userData;
    }

    /**
     * Searches an user at database by his name
     * @param name - The name or part of the name of an user
     * @return List - Array of users found
     */
    public List<User> searchUserByName(String name, Context context){
        final String QUERY = "SELECT * FROM Usuario WHERE nome =\"" + name + "\"";

        JSONObject userFound = this.executeConsult(QUERY);
        List<User> listUsers = new ArrayList<>();

        try{
            if(userFound != null){
                for(int i = 0; i < userFound.length(); i++){

                    User user = null;
                    try{
                        user = new User(userFound.getJSONObject(i + "").getInt("idUsuario"),
                                userFound.getJSONObject(i + "").getString("nome"),
                                userFound.getJSONObject(i + "").getString("sobrenome"),
                                userFound.getJSONObject(i + "").getString("email"),
                                userFound.getJSONObject(i + "").getString("login"),
                                userFound.getJSONObject(i + "").getInt("rating"),
                                userFound.getJSONObject(i + "").getInt("Classificacao_idClassificacao"));
                    } catch(UserException e){
                        e.printStackTrace();
                    }

                    listUsers.add(user);
                }
            }
        } catch(JSONException e){
            e.printStackTrace();
        }

        return listUsers;
    }

    /**
     * Get all users on database
     * @param context - Context of the application
     * @return List - Array of users found
     */
    public List<User> getAllUsers(Context context){
        final String QUERY = "SELECT * FROM Usuario";

        JSONObject userFound = this.executeConsult(QUERY);
        List<User> listUsers = new ArrayList<>();

        try{
            if(userFound != null){
                for(int i = 0; i < userFound.length(); i++){

                    User user = null;
                    try{
                        user = new User(userFound.getJSONObject(i + "").getInt("idUsuario"),
                                userFound.getJSONObject(i + "").getString("nome"),
                                userFound.getJSONObject(i + "").getString("sobrenome"),
                                userFound.getJSONObject(i + "").getString("email"),
                                userFound.getJSONObject(i + "").getString("login"),
                                userFound.getJSONObject(i + "").getInt("rating"),
                                userFound.getJSONObject(i + "").getInt("Classificacao_idClassificacao"));
                    } catch(UserException e){
                        e.printStackTrace();
                    }

                    listUsers.add(user);
                }
            }
        } catch(JSONException e){
            e.printStackTrace();
        }

        return listUsers;
    }

    /**
     * Update an user rating
     * @param rating - New user rating
     * @param userId - User to be updated
     * @return String - Returns the query execution status
     */
    public String updateUserRating(Integer rating, Integer userId){
        final String QUERY = "UPDATE Usuario SET rating = " + rating + " WHERE idUsuario =" +
                userId + " ";

        String result = this.executeQuery(QUERY);

        return result;
    }

    /**
     * Update an user classification
     * @param classificationId - New user classification identifier
     * @param userId - User to be updated
     * @return String - Returns the query execution status
     */
    public String updateUserClassification(Integer classificationId, Integer userId){
        final String QUERY = "UPDATE Usuario SET Classificacao_idClassificacao = " +
                classificationId + " WHERE idUsuario =" + userId + " ";

        String result = this.executeQuery(QUERY);

        return result;
    }

    /**
     * Searches an user at database by his email
     * @param email - The email of an user
     * @return JSONObject - Returns a JSONObject with the results of the consult
     */
    public JSONObject searchUserByEmail (String email){
        final String QUERY = "SELECT * FROM Usuario WHERE email =\"" + email + "\"";

        JSONObject userData = this.executeConsult(QUERY);

        return userData;
    }

    /**
     * Searches an user name at database by his id
     * @param idUser - The id of an user
     * @return String - Returns a String with the name of the user
     * @throws JSONException
     */
    public String getUserNameById(int idUser) throws JSONException {
        final String QUERY = "SELECT nome FROM Usuario WHERE idUsuario = "+ idUser +" ";

        JSONObject consultResult = executeConsult(QUERY);

        String name = consultResult.getJSONObject("0").getString("nome");

        return name;
    }

    public Integer getUserEvaluationById(Integer idUser) throws JSONException {
        final String SEARCH_TOPICS_QUERY = "SELECT descricao FROM AvaliacaoTopico WHERE " +
                "Topico_Usuario_idUsuario = " + idUser;

        final String SEARCH_COMMENTS_QUERY = "SELECT descricao FROM AvaliacaoComentario WHERE " +
                "Comentario_Usuario_idUsuario = " + idUser;

        JSONObject consultResultTopics = executeConsult(SEARCH_TOPICS_QUERY);
        JSONObject consultResultComments = executeConsult(SEARCH_COMMENTS_QUERY);

        Integer userEvaluation = 0;

        if(consultResultTopics != null) {
            for(int i = 0; i < consultResultTopics.length(); i++) {
                Integer topicEvaluation = consultResultTopics.getJSONObject("" + i).
                        getInt("descricao");

                userEvaluation += topicEvaluation;
            }
        }

        if(consultResultComments != null) {
            for(int i = 0; i < consultResultComments.length(); i++) {
                Integer commentEvaluation = consultResultComments.getJSONObject("" + i).
                        getInt("descricao");

                userEvaluation += commentEvaluation;
            }
        }

        return userEvaluation;
    }

    public List<Integer> getUserIdListBySchoolCode(String schoolCode) throws JSONException {
        final String QUERY = "SELECT idUsuario FROM Usuario WHERE " +
                "codigoEscola = \""+ schoolCode +"\" ";

        JSONObject consultResult = executeConsult(QUERY);

        List<Integer> userIdList = new ArrayList<>();

        if(consultResult != null) {
            for(int i = 0; i < consultResult.length(); i++) {
                Integer idUser = consultResult.getJSONObject("" + i).getInt("idUsuario");

                userIdList.add(idUser);
            }
        }

        return userIdList;
    }

    public List<String> getSchoolCodeList() throws JSONException {
        final String QUERY = "SELECT codigoEscola FROM Usuario";

        JSONObject consultResult = executeConsult(QUERY);

        List<String> schoolCodeList = new ArrayList<>();

        if(consultResult != null) {
            for (int i = 0; i < consultResult.length(); i++) {
                String schoolCode = consultResult.getJSONObject("" + i).getString("codigoEscola");

                schoolCodeList.add(schoolCode);
            }
        }

        return schoolCodeList;
    }

}
