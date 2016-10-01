/**
 * File: CategoryDAO.java
 * Purpose: Search categories on webservice
 */

package com.mathheals.meajuda.dao;

import android.content.Context;

import com.mathheals.meajuda.model.Category;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Vector;

public class CategoryDAO extends DAO {

    private static CategoryDAO instance;

    /**
     * Constructs DAO with the current context
     * @param currentContext Current context
     */
    private CategoryDAO(Context currentContext){
        super(currentContext);
    }

    /**
     * Get the current instance or create a new if none was created
     * @param context - Actual context of the application
     * @return CategoryDAO - The current or new CategoryDAO instance
     */
    public static CategoryDAO getInstance(final Context context) {
        if (CategoryDAO.instance != null) {
            //nothing to do
        } else {
            CategoryDAO.instance = new CategoryDAO(context);
        }
        return CategoryDAO.instance;
    }

    /**
     * Searches category by Id
     * @param idCategory Id of searched category
     * @return JSONObject based on executed database consult
     */
    public JSONObject searchCategoryById(int idCategory){
        String consultQueryString = getConsultQueryString(idCategory);

        JSONObject consultQuery = executeConsult(consultQueryString);

        return consultQuery;
    }

    /**
     * Returns all the categories registered on database
     * @return Vector with the categories
     */
    public Vector<Category> getAllCategories() throws JSONException{

        final String SELECT_ALL_CATEGORIES_QUERY =
                "SELECT * FROM Categoria;";

        JSONObject consultResult = executeConsult(SELECT_ALL_CATEGORIES_QUERY);

        Vector<Category> categories = new Vector<>();

        if(consultResult!= null){
            for (int i = 0; i < consultResult.length(); i++){

                Category category = new Category(
                        consultResult.getJSONObject("" + i).getInt("idCategoria"),
                        consultResult.getJSONObject("" + i).getString("descricao")
                );

                categories.add(category);
            }
        }
        else{
            categories = null;
        }

        return categories;
    }

    /**
     * Returns the consult query string of a given category
     * @param idCategory id of category searched
     * @return string of the database command of consult category
     */
    private String getConsultQueryString(int idCategory){

        final String SELECT_NAME_CATEGORY_QUERY =
                "SELECT descricao FROM Categoria WHERE ";

        final String idCategoryValue = "idCategoria = " + idCategory;

        final String consultQueryString = SELECT_NAME_CATEGORY_QUERY + idCategoryValue;
        return consultQueryString;
    }
}
