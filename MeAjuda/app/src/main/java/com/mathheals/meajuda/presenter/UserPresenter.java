package com.mathheals.meajuda.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.mathheals.meajuda.R;
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

        String message = "";

        String userPassword = searchResult.getJSONObject("0").getString("senha");
        if(userPassword.equals(passwordTyped)){
            message = context.getResources().getString(R.string.success_authentication);
        }
        else{
            message = context.getResources().getString(R.string.error_password);
        }

        return message;
    }

    public SharedPreferences.Editor createLoginSession(String email,
                                                        SharedPreferences session) {

        // All Shared Preferences Keys
        final String IS_LOGIN = "IsLoggedIn";

        // Email address
        final String KEY_EMAIL = "email";

        SharedPreferences.Editor editor = session.edit();

        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_EMAIL, email);
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

        try{
            user = new User(userFound.getJSONObject("0").getString("nome"),
                    userFound.getJSONObject("0").getString("sobrenome"),
                    userFound.getJSONObject("0").getString("login"),
                    userFound.getJSONObject("0").getInt("rating"));

        } catch(UserException e){
            e.printStackTrace();

        } catch(ParseException e){
            e.printStackTrace();

        } catch(JSONException e){
            e.printStackTrace();
        }

        return user;
    }
}
