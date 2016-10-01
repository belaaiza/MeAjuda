package com.mathheals.meajuda.dao;

import android.content.Context;

import org.json.JSONObject;

/**
 * Created by izabela on 01/10/16.
 */
public class CategoryDAO extends DAO {

    private static final String SELECT_NAME_CATEGORY_COMMAND =
            "SELECT descricao FROM Categoria WHERE ";

    /**
     * Constructs DAO with the current context
     *
     * @param currentContext Current context
     */
    public CategoryDAO(Context currentContext){
        super(currentContext);
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
     * Returns the consult query string of a given category
     * @param idCategory id of category searched
     * @return string of the database command of consult category
     */
    private String getConsultQueryString(int idCategory){
        final String idCategoryValue = "idCategoria = " + idCategory;

        final String consultQueryString = SELECT_NAME_CATEGORY_COMMAND + idCategoryValue;
        return consultQueryString;
    }
}
