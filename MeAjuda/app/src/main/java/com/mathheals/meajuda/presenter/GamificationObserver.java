package com.mathheals.meajuda.presenter;

import android.content.Context;

public abstract class GamificationObserver {

    private Integer userToBeUpdatedId;
    private Integer evaluation;

    public void GamificationObserver(Integer userToBeUpdatedId, Integer evaluation){
        setUserToBeUpdatedId(userToBeUpdatedId);
        setEvaluation(evaluation);
    }

    public void setUserToBeUpdatedId(Integer userToBeUpdatedId){
        this.userToBeUpdatedId = userToBeUpdatedId;
    }

    public void setEvaluation(Integer evaluation){
        this.evaluation = evaluation;
    }

    void updateRating(Context context){
        //nothing to do
    }

    void updateClassification(Context context){
        //nothing to do
    }
}
