package com.mathheals.meajuda.presenter;

import android.content.Context;

public abstract class ProcessRating {
    public void processRating(Context context, Integer userToBeUpdatedId) {
        Integer newRating = processEvaluation();
        Integer newClassificationId = processClassification();

        GamificationObserver gamificationObserver = new UserGamificationObserver(context,
                userToBeUpdatedId, newRating, newClassificationId);

        UserGamification userGamification = new UserGamification();
        userGamification.notify(gamificationObserver);
    }

    public abstract Integer processEvaluation();

    public abstract Integer processClassification();
}
