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
    private static LoggedIn loggedInInstance;
    private static NotLoggedIn notLoggedInInstance;

    private State state;

    Context context;

    private UserPresenter(Context currentContext) {
        this.context = currentContext;
    }

    public static UserPresenter getInstance(Context context) {
        if (instance == null) {
            instance = new UserPresenter(context);
            loggedInInstance = LoggedIn.getInstance(context);
            notLoggedInInstance = NotLoggedIn.getInstance(context);
        }
        return instance;
    }

    public String registerUser(String firstName, String lastName, String username, String mail,
                                         String mailConfirmation, String password,
                                         String passwordConfirmation, String codeSchool, Context context){
        String message = notLoggedInInstance.registerUser(firstName, lastName, username, mail,
                mailConfirmation, password, passwordConfirmation, codeSchool);

        return message;
    }

    public String updateUser(String firstName, String lastName, String username, String mail,
                               String mailConfirmation, String password,
                               String passwordConfirmation, Context context){
        String message = loggedInInstance.updateUser(firstName, lastName, username, mail,
                mailConfirmation, password, passwordConfirmation);

        return message;
    }

    public String authenticateUser(String emailOrLogin, String typedPassword, Context context)
            throws JSONException{

        String message = notLoggedInInstance.authenticateUser(emailOrLogin, typedPassword);

        return message;
    }

    public SharedPreferences.Editor createLoginSession(String userJson,Context context,
                                                        SharedPreferences session) {

        SharedPreferences.Editor editor = notLoggedInInstance.createLoginSession(userJson, session);

        return editor;
    }

    public void setState(SharedPreferences session) {
        final String IS_LOGIN = "IsLoggedIn";

        boolean isLoggedIn = session.getBoolean(IS_LOGIN, false);

        if(isLoggedIn) {
            this.state = loggedInInstance;
        } else {
            this.state = notLoggedInInstance;
        }
    }

    public SharedPreferences.Editor updateLoginSession(User user,Context context,
                                                       SharedPreferences session) {

        setState(session);

        SharedPreferences.Editor editor = state.updateLoginSession(user, session);

        return editor;
    }

    public SharedPreferences.Editor finishLoginSession(SharedPreferences session) {
        setState(session);

        SharedPreferences.Editor editor = state.updateLoginSession(null, session);

        return editor;
    }

    public User showProfile(String emailOrLogin, Context context){
        User user = loggedInInstance.showProfile(emailOrLogin);

        return user;
    }

    public List<User> getUserRanking() throws JSONException, UserException {
        List<User> userRanking = notLoggedInInstance.getUserRanking();

        return userRanking;
    }

    public List<User> getAllUsers(Context context){
        List<User> usersFound = notLoggedInInstance.getAllUsers();

        return usersFound;
    }

    public Drawable getClassificationIcon(Integer classification){
        Drawable levelIcon = notLoggedInInstance.getClassificationIcon(classification);

        return levelIcon;
    }

    public Integer getUserClassification(Integer userId) throws UserException, JSONException {
        Integer userClassification = notLoggedInInstance.getUserClassification(userId);

        return userClassification;
    }

    public User getUser(Integer userId) throws UserException, JSONException {
        User user = notLoggedInInstance.getUser(userId);

        return user;
    }
}