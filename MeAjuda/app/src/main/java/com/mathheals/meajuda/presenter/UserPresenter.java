package com.mathheals.meajuda.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Log;

import com.mathheals.meajuda.R;
import com.mathheals.meajuda.dao.JSONHelper;
import com.mathheals.meajuda.dao.UserDAO;
import com.mathheals.meajuda.exception.UserException;
import com.mathheals.meajuda.model.School;
import com.mathheals.meajuda.model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class UserPresenter {

    private static UserPresenter instance;
    Context context;

    private UserPresenter(Context currentContext) {
        this.context = currentContext;
    }

    public static UserPresenter getInstance(Context context) {
        if (instance == null) {
            instance = new UserPresenter(context);
        }
        return instance;
    }

    public String registerUser(String firstName, String lastName, String username, String mail,
                                         String mailConfirmation, String password,
                                         String passwordConfirmation, String codeSchool, Context context){
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
        editor.putString(context.getResources().getString(R.string.key_school), user.getCodeSchool());
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
        editor.putInt("id", user.getUserId());
        editor.commit();

        return editor;
    }

    public SharedPreferences.Editor finishLoginSession(SharedPreferences session) {

        // All Shared Preferences Keys
        final String IS_LOGIN = "IsLoggedIn";

        SharedPreferences.Editor editor = session.edit().clear();
        editor.commit();

        editor.putBoolean(IS_LOGIN, false);
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

    public Integer getUserClassificationId(Integer idUser) throws JSONException{
        UserDAO userDAO = UserDAO.getInstance(context);

        Integer userEvaluation = userDAO.getUserEvaluationById(idUser);

        Integer idClassification;

        if(userEvaluation < 1){
            idClassification = 1;
        }
        else
            if(userEvaluation < 2){
                idClassification = 2;
            }
            else
                if(userEvaluation < 3){
                    idClassification = 3;
                }
                else
                    if(userEvaluation < 4){
                        idClassification = 4;
                    }
                    else
                        if(userEvaluation < 5){
                            idClassification = 5;
                        }
                        else{
                            idClassification = 6;
                        }

        Log.d("userEvaluation " + idUser, userEvaluation + "");
        Log.d("classification " + idUser, idClassification + "");

        return idClassification;
    }

    public List<User> getUserRanking() throws JSONException, UserException {
        UserDAO userDAO = UserDAO.getInstance(context);

        List<Integer> userIdList = userDAO.getUserIdList();

        List<User> userRanking = new ArrayList<>();

        for(int i = 0; i < userIdList.size(); i++) {
            Integer idUser = userIdList.get(i);

            User user = userDAO.getUserById(idUser);

            userRanking.add(user);
        }

        Collections.sort(userRanking, new Comparator<User>(){
            @Override
            public int compare(User lhs, User rhs){
                return lhs.getRating() > rhs.getRating() ? -1 : 1;
            }
        });

        return userRanking;
    }

    public List<User> getAllUsers(Context context){
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