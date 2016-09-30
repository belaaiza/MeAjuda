package com.mathheals.meajuda.dao;

import android.app.Activity;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.Calendar;

public abstract class DAO {

    private final String URL_QUERY = "http://meajuda.orgfree.com/query.php";
    private final String URL_CONSULT = "http://meajuda.orgfree.com/consult.php";

    private final int CONNECTION_TIMEOUT = 15000;
    protected Activity currentActivity;

    public DAO(Activity currentActivity) {
        this.currentActivity = currentActivity;
    }

    public DAO() {

    }

    private String query(String query,String urlQuery) {
        Consult consult = new Consult(query,urlQuery);
        consult.exec();

        long currentTime = Calendar.getInstance().getTime().getTime();
        long timeLimit = currentTime + CONNECTION_TIMEOUT;
        while(!consult.getIsDoing() && currentTime < timeLimit) {
            currentTime = Calendar.getInstance().getTime().getTime();
        }

        if(timeLimitExceeded(timeLimit, currentTime)) {
            Toast.makeText(currentActivity,"Problema de conexão com o servidor (verifique se está conectado à internet)", Toast.LENGTH_LONG).show();
            return null;
        }


        return consult.getResult();
    }
    public static boolean timeLimitExceeded(long timeLimit, long currentTime) {
        return (currentTime >= timeLimit);
    }

    protected String executeQuery(String query) {
        return query(query, URL_QUERY);
    }

    protected JSONObject executeConsult(String query) {
        String json;
        JSONObject jObject = null;
        try {
            json = query(query, URL_CONSULT);
            jObject  = new JSONObject(json);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jObject;
    }

}
