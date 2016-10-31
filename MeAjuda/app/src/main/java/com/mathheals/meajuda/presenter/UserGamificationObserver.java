package com.mathheals.meajuda.presenter;

import android.content.Context;

import com.mathheals.meajuda.dao.UserDAO;

public class UserGamificationObserver implements GamificationObserver {

    private Context context;
    private Integer userToBeUpdatedId;
    private Integer newRating;
    private Integer newClassificationId;

    public UserGamificationObserver(Context context, Integer userToBeUpdatedId, Integer newRating,
                                    Integer newClassificationId){
        this.context = context;
        this.userToBeUpdatedId = userToBeUpdatedId;
        this.newRating = newRating;
        this.newClassificationId = newClassificationId;
    }

    @Override
    public void updateRating(){
        UserDAO userDAO = UserDAO.getInstance(context);
        userDAO.updateUserRating(newRating, userToBeUpdatedId);
    }

    @Override
    public void updateClassification(){
        UserDAO userDAO = UserDAO.getInstance(context);
        userDAO.updateUserClassification(newClassificationId, userToBeUpdatedId);
    }
}
