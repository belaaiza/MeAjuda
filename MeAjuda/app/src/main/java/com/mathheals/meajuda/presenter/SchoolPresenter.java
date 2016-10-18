package com.mathheals.meajuda.presenter;

import android.content.Context;

import com.mathheals.meajuda.model.School;

public class SchoolPresenter {

    private static SchoolPresenter instance;

    public static SchoolPresenter getInstance() {
        if (instance == null) {
            instance = new SchoolPresenter();
        }
        return instance;
    }
    
}
