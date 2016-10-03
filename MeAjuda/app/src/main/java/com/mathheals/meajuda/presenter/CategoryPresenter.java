package com.mathheals.meajuda.presenter;

import android.content.Context;

import com.mathheals.meajuda.dao.CategoryDAO;
import com.mathheals.meajuda.model.Category;

import org.json.JSONException;

import java.util.Vector;

/**
 * Created by izabela on 01/10/16.
 */
public class CategoryPresenter {

    private static CategoryPresenter instance;
    private Context context;

    private CategoryPresenter(Context currentContext){
        this.context = currentContext;
    }

    /**
     * Get the current instance or create a new if none was created
     * @param context - Actual context of the application
     * @return CategoryPresenter - The current or new CategoryPresenter instance
     */
    public static CategoryPresenter getInstance(final Context context) {
        if (CategoryPresenter.instance != null) {
            //nothing to do
        } else {
            CategoryPresenter.instance = new CategoryPresenter(context);
        }
        return CategoryPresenter.instance;
    }

    public Vector<Category> getAllCategories(Context context) throws JSONException{
        CategoryDAO categoryDAO = CategoryDAO.getInstance(context);

        Vector<Category> categories = categoryDAO.getAllCategories();

        return categories;
    }
}
