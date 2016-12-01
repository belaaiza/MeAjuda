package com.mathheals.meajuda.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.mathheals.meajuda.model.User;

public interface State {
    SharedPreferences.Editor updateLoginSession(User user, SharedPreferences session);
}
