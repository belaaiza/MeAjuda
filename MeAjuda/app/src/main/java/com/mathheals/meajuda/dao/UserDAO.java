package com.mathheals.meajuda.dao;

import android.content.Context;

import com.mathheals.meajuda.exception.UserException;
import com.mathheals.meajuda.model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.Vector;

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
     * @param user
     * @return String - Returns a text confirming if the query was executed with success
     */
    public String saveUser(User user){

        final String QUERY = "INSERT INTO Usuario(email, nome, sobrenome, rating, Escola_idEscola" +
                ")VALUES" + "(\"" + user.getEmail() + "\", \"" + user.getFirstName() + "\", \""
                + user.getLastName() + "\"," + user.getRating() + "\"," + user.getIdSchool() + "\")";

        String queryStatus = this.executeQuery(QUERY);

        return queryStatus;
    }

    public Vector<User> searchUserFirstName(int owner) throws JSONException, ParseException, UserException {
        assert owner >= 1;

        JSONObject json = this.executeConsult("SELECT * FROM tb_event WHERE idFirstName=" + owner + " GROUP BY idFirstName");


        if(json == null) {
            return null;
        }else{
            // Nothing to do
        }

        Vector<User> users = new Vector<>();

        for (int i = 0; i < json.length(); i++) {

            User user = new User(json.getJSONObject("" + i).getString("userFirstName"),
                    json.getJSONObject("" + i).getString("userLastName"),
                    json.getJSONObject("" + i).getString("username"),
                    json.getJSONObject("" + i).getString("email"),
                    json.getJSONObject("" + i).getString("mailConfirmation"),
                    json.getJSONObject("" + i).getString("password"),
                    json.getJSONObject("" + i).getString("passwordConfirmation"),
                    json.getJSONObject("" + i).getInt("rating"),
                    json.getJSONObject("" + i).getInt("idSchool"),
                    json.getJSONObject("" + i).getInt("idClassification")
                    );
            users.add(user);
        }

        return users;
    }


}
