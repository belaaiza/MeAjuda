package com.mathheals.meajuda.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;

import com.mathheals.meajuda.R;
import com.mathheals.meajuda.dao.JSONHelper;
import com.mathheals.meajuda.dao.UserDAO;
import com.mathheals.meajuda.exception.UserException;
import com.mathheals.meajuda.model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class NotLoggedIn implements State {
    private static NotLoggedIn instance;
    Context context;

    private NotLoggedIn(Context currentContext) {
        this.context = currentContext;
    }

    public static NotLoggedIn getInstance(Context context) {
        if (instance == null) {
            instance = new NotLoggedIn(context);
        }
        return instance;
    }

    @Override
    public SharedPreferences.Editor updateLoginSession(User user, SharedPreferences session) {

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
        editor.putInt("id", user.getUserId());
        editor.commit();

        return editor;
    }

    public String registerUser(String firstName, String lastName, String username, String mail,
                               String mailConfirmation, String password,
                               String passwordConfirmation, String codeSchool){
        String message = "";

        try{
            User user = new User(firstName, lastName, username, mail, mailConfirmation,
                    password, passwordConfirmation, 0, codeSchool, 1);

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

    public String authenticateUser(String emailOrLogin, String typedPassword) throws JSONException {

        String message = "";

        UserDAO userDAO = UserDAO.getInstance(context);
        JSONObject searchByEmailResult = userDAO.searchUserByEmail(emailOrLogin);

        if(searchByEmailResult != null){
            message = verifyLoginPassword(searchByEmailResult, typedPassword);
        }
        else{
            JSONObject searchByLoginResult = userDAO.searchUserByLoginName(emailOrLogin);

            if(searchByLoginResult != null){
                message = verifyLoginPassword(searchByLoginResult, typedPassword);
            }
            else{
                message = context.getResources().getString(R.string.error_login_or_email);
            }
        }

        return message;
    }

    private String verifyLoginPassword(JSONObject searchResult, String passwordTyped)
            throws JSONException{
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

    public SharedPreferences.Editor createLoginSession(String userJson, SharedPreferences session) {

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
        editor.putString(context.getResources().getString(R.string.key_school), user.getCodeSchool());
        editor.commit();

        return editor;
    }

    public List<User> getAllUsers(){
        List<User> usersFound = UserDAO.getInstance(context).getAllUsers(context);
        return usersFound;
    }

    public Drawable getClassificationIcon(Integer classification){
        Drawable levelIcon = null;
        switch(classification){
            case 1:
                levelIcon = ResourcesCompat.getDrawable(context.getResources(),
                        R.drawable.level1, context.getTheme());
                break;
            case 2:
                levelIcon = ResourcesCompat.getDrawable(context.getResources(),
                        R.drawable.level2, context.getTheme());
                break;
            case 3:
                levelIcon = ResourcesCompat.getDrawable(context.getResources(),
                        R.drawable.level3, context.getTheme());
                break;
            case 4:
                levelIcon = ResourcesCompat.getDrawable(context.getResources(),
                        R.drawable.level4, context.getTheme());
                break;
            case 5:
                levelIcon = ResourcesCompat.getDrawable(context.getResources(),
                        R.drawable.level5, context.getTheme());
                break;
            case 6:
                levelIcon = ResourcesCompat.getDrawable(context.getResources(),
                        R.drawable.level6, context.getTheme());
                break;
        }

        return levelIcon;
    }

    public Integer getUserClassification(Integer userId) throws UserException, JSONException {
        UserDAO userDAO = UserDAO.getInstance(context);
        User user = userDAO.getUserById(userId);

        return user.getIdClassification();
    }

    public User getUser(Integer userId) throws UserException, JSONException {
        UserDAO userDAO = UserDAO.getInstance(context);
        User user = userDAO.getUserById(userId);

        return user;
    }
}
