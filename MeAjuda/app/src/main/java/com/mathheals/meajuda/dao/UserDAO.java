package com.mathheals.meajuda.dao;

import android.content.Context;
import android.util.Log;
import com.mathheals.meajuda.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
     * @return JSONArray - Array of users found
     */
    public JSONArray searchUserByName(String name){
        final String QUERY = "SELECT * FROM Usuario WHERE nome =\"" + name + "\"";

        JSONObject userFound = this.executeConsult(QUERY);
        JSONArray usersAsArray = null;

        try{
            usersAsArray = new JSONArray(userFound);
        } catch(JSONException e){
            e.printStackTrace();
        }

        return usersAsArray;
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
        final String QUERY = "UPDATE Usuario SET  = " + classificationId + " WHERE idUsuario =" +
                userId + " ";

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

}
