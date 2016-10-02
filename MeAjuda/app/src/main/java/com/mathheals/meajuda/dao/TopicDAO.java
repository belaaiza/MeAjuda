package com.mathheals.meajuda.dao;

import android.content.Context;
import android.util.Log;

import com.mathheals.meajuda.model.Topic;

public class TopicDAO extends DAO {

    private static TopicDAO instance;

    private TopicDAO(Context currentContext) {
        super(currentContext);
    }

    public static TopicDAO getInstance(final Context context) {
        if(TopicDAO.instance != null) {
            //nothing to do
        } else {
            TopicDAO.instance = new TopicDAO(context);
        }

        return TopicDAO.instance;
    }

    public void createTopic(int categoryId, String title, String description) {
        final String QUERY;

        QUERY = "INSERT INTO Topico(descricao, Categoria_idCategoria, titulo) " +
            "VALUES(\" "+ description +" \", "+ categoryId +", \" "+ title +" \")";

        String aux = executeQuery(QUERY);



        Log.d("sla", aux);
    }


}
