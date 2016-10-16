package com.mathheals.meajuda.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.mathheals.meajuda.R;
import com.mathheals.meajuda.dao.JSONHelper;
import com.mathheals.meajuda.dao.UserDAO;
import com.mathheals.meajuda.exception.UserException;
import com.mathheals.meajuda.model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;

public class UserPresenter {

    private static UserPresenter instance;

    public static UserPresenter getInstance() {
        if (instance == null) {
            instance = new UserPresenter();
        }
        return instance;
    }

    public String registerUser(String firstName, String lastName, String username, String mail,
                                         String mailConfirmation, String password,
                                         String passwordConfirmation, Context context){
        String message = "";

        try{
            User user = new User(firstName, lastName, username, mail, mailConfirmation,
                    password, passwordConfirmation, 0, null, 1);

            UserDAO userDAO = UserDAO.getInstance(context);
            userDAO.saveUser(user);

            message = user.USER_SUCCESSFULLY_REGISTERED;
        }catch(UserException e){
            message = e.getMessage();
        }catch(ParseException e){
            e.printStackTrace();
        }

        return message;
    }

    public String updateUser(String firstName, String lastName, String username, String mail,
                               String mailConfirmation, String password,
                               String passwordConfirmation, Context context){
        String message = "";

        SharedPreferences currentSession = PreferenceManager.getDefaultSharedPreferences
                (context);

        try{
            User user = new User(currentSession.getInt(context.getString(R.string.key_id), -1),
                    firstName, lastName, username, mail, mailConfirmation,
                    password, passwordConfirmation);

            UserDAO userDAO = UserDAO.getInstance(context);
            userDAO.updateUser(user);

            updateLoginSession(user, context, currentSession);

            message = user.USER_SUCCESSFULLY_UPDATED;

        }catch(UserException e){
            message = e.getMessage();
        }catch(ParseException e){
            e.printStackTrace();
        }

        return message;
    }

    public String authenticateUser(String emailOrLogin, String typedPassword, Context context)
            throws JSONException{

        String message = "";

        UserDAO userDAO = UserDAO.getInstance(context);
        JSONObject searchByEmailResult = userDAO.searchUserByEmail(emailOrLogin);

        if(searchByEmailResult != null){
            message = verifyLoginPassword(searchByEmailResult, typedPassword, context);
        }
        else{
            JSONObject searchByLoginResult = userDAO.searchUserByLoginName(emailOrLogin);

            if(searchByLoginResult != null){
                message = verifyLoginPassword(searchByLoginResult, typedPassword, context);
            }
            else{
                message = context.getResources().getString(R.string.error_login_or_email);
            }
        }

        return message;
    }


    private String verifyLoginPassword(JSONObject searchResult, String passwordTyped,
                                       Context context) throws JSONException{
        String message;

        String userPassword = searchResult.getJSONObject("0").getString("senha");
        if(userPassword.equals(passwordTyped)){
            message = searchResult.toString();
        }
        else{
            message = context.getResources().getString(R.string.error_password);
        }

        return message;
    }

    public SharedPreferences.Editor createLoginSession(String userJson,Context context,
                                                        SharedPreferences session) {

        User user = JSONHelper.getInstance().jsonToUser(userJson);

        // All Shared Preferences Keys
        final String IS_LOGIN = "IsLoggedIn";

        SharedPreferences.Editor editor = session.edit();

        editor.putBoolean(IS_LOGIN, true);
        editor.putInt(context.getResources().getString(R.string.key_id), user.getUserId());
        editor.putString(context.getResources().getString(R.string.key_email), user.getEmail());
        editor.putString(context.getResources().getString(R.string.key_login), user.getUsername());
        editor.putString(context.getResources().getString(R.string.key_name), user.getFirstName());
        editor.putString(context.getResources().getString(R.string.key_last_name),
                user.getLastName());
        editor.putString(context.getResources().getString(R.string.key_password),
                user.getPassword());
        editor.putInt(context.getResources().getString(R.string.key_rating), user.getRating());
        editor.putInt(context.getResources().getString(R.string.key_classification),
                user.getIdClassification());
        editor.putInt(context.getResources().getString(R.string.key_school), user.getIdSchool());
        editor.commit();

        return editor;
    }

    public SharedPreferences.Editor updateLoginSession(User user,Context context,
                                                       SharedPreferences session) {

        // All Shared Preferences Keys
        final String IS_LOGIN = "IsLoggedIn";

        SharedPreferences.Editor editor = session.edit();

        editor.putBoolean(IS_LOGIN, true);
        editor.putString(context.getResources().getString(R.string.key_email), user.getEmail());
        editor.putString(context.getResources().getString(R.string.key_login), user.getUsername());
        editor.putString(context.getResources().getString(R.string.key_name), user.getFirstName());
        editor.putString(context.getResources().getString(R.string.key_last_name),
                user.getLastName());
        editor.putString(context.getResources().getString(R.string.key_password),
                user.getPassword());
        editor.commit();

        return editor;
    }

    public User showProfile(String emailOrLogin, Context context){
        UserDAO userDAO = UserDAO.getInstance(context);

        JSONObject userFound = userDAO.searchUserByEmail(emailOrLogin);

        if(userFound == null){
            userFound = userDAO.searchUserByLoginName(emailOrLogin);
        }

        Log.d("USER FOUND", userFound.toString());

        User user = null;

        user = JSONHelper.getInstance().jsonToUser(userFound.toString());

        return user;
    }
}
