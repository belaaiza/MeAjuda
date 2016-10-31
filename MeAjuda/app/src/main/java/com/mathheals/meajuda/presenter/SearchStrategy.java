package com.mathheals.meajuda.presenter;

import android.content.Context;

import org.json.JSONArray;

public interface SearchStrategy {
    JSONArray search(Context context, String query);
}
