package com.mathheals.meajuda.dao;

import android.content.Context;

import com.mathheals.meajuda.model.User;

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

}
