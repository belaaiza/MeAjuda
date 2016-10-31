package com.mathheals.meajuda.presenter;

import android.content.Context;

public class UserGamification implements  Gamification {

    @Override
    public void notify(GamificationObserver gamificationObserver){
        gamificationObserver.updateRating();
        gamificationObserver.updateClassification();
    }
}
