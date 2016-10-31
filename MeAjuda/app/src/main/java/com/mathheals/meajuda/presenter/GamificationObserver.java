package com.mathheals.meajuda.presenter;

import android.content.Context;

public interface GamificationObserver {

    Integer userToBeUpdatedId = null;
    Integer evaluation = null;

    void updateRating(Context context);

    void updateClassification(Context context);
}
