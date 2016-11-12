package com.mathheals.meajuda.presenter;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

public interface SearchStrategy {
    List search(Context context, String query) throws JSONException;
}
