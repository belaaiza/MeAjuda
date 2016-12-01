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

import org.json.JSONObject;

import java.text.ParseException;

public class LoggedIn implements State{
    private static LoggedIn instance;
    Context context;

    private LoggedIn(Context currentContext) {
        this.context = currentContext;
    }

    public static LoggedIn getInstance(Context context) {
        if (instance == null) {
            instance = new LoggedIn(context);
        }
        return instance;
    }

    @Override
    public SharedPreferences.Editor updateLoginSession(User user, SharedPreferences session) {

        // All Shared Preferences Keys
        final String IS_LOGIN = "IsLoggedIn";

        SharedPreferences.Editor editor = session.edit().clear();
        editor.commit();

        editor.putBoolean(IS_LOGIN, false);
        editor.commit();

        return editor;
    }

    public String updateUser(String firstName, String lastName, String username, String mail,
                             String mailConfirmation, String password,
                             String passwordConfirmation){
        String message = "";

        SharedPreferences currentSession = PreferenceManager.getDefaultSharedPreferences
                (context);

        try{
            User user = new User(currentSession.getInt(context.getString(R.string.key_id), -1),
                    firstName, lastName, username, mail, mailConfirmation,
                    password, passwordConfirmation);

            UserDAO userDAO = UserDAO.getInstance(context);
            userDAO.updateUser(user);

            updateLoginSession(user, currentSession);

            message = user.USER_SUCCESSFULLY_UPDATED;

        }catch(UserException e){
            message = e.getMessage();
        }catch(ParseException e){
            e.printStackTrace();
        }

        return message;
    }

    public User showProfile(String emailOrLogin){
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
