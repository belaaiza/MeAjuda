package com.mathheals.meajuda.presenter;

import android.content.Context;

import com.mathheals.meajuda.dao.UserDAO;

public class UserGamificationObserver implements GamificationObserver {

    private Integer evaluation;
    private Integer userToBeUpdatedId;

    public UserGamificationObserver(Integer userToBeUpdatedId, Integer evaluation){
        this.userToBeUpdatedId = userToBeUpdatedId;
        this.evaluation = evaluation;
    }

    @Override
    public void updateRating(Context context){
        UserDAO userDao = UserDAO.getInstance(context);
        userDao.updateUserRating(evaluation, userToBeUpdatedId);
    }

    @Override
    public void updateClassification(Context context){
        UserDAO userDao = UserDAO.getInstance(context);

        if(evaluation <= 10){
            userDao.updateUserClassification(1, userToBeUpdatedId);
        }
        else if(evaluation <= 30){
            userDao.updateUserClassification(2, userToBeUpdatedId);
        }
        else if(evaluation <= 50){
            userDao.updateUserClassification(3, userToBeUpdatedId);
        }
        else if(evaluation <= 80){
            userDao.updateUserClassification(4, userToBeUpdatedId);
        }
        else if(evaluation <= 120){
            userDao.updateUserClassification(5, userToBeUpdatedId);
        }
    }
}
