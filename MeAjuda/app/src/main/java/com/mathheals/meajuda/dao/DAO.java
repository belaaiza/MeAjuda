/**
* File: DAO.java
* Purpose: Process database queries
*/

package com.mathheals.meajuda.dao;

import android.content.Context;
import android.widget.Toast;
import org.json.JSONObject;
import com.mathheals.meajuda.dao.ConsultDAO;
import java.util.Calendar;

public abstract class DAO{

    private static final int LIMIT_CONNECTION_TIME = 50000;
    private static final String STRING_EMPTY = "";
    private static final String URL_QUERY = "http://meajuda.orgfree.com/query.php";
    private static final String URL_CONSULT = "http://meajuda.orgfree.com/consult.php";
    private static final String CONNECTION_PROBLEM_MESSAGE =
            "Problema de conexão com o servidor (verifique se esta conectado a internet)";

    private Context currentContext;

    /**
     * Constructs DAO with the current context
     *
     * @param currentContext Current context
     */
    public DAO(Context currentContext){
        this.currentContext = currentContext;
    }

    /**
     * Process a query in the database
     *
     * @param query    Query text to be used in the database
     * @param urlQuery URL of the database
     * @return Answer of the query
     */
    private String processQuery(String query, String urlQuery){
        assert query != null;
        assert urlQuery != null;

        ConsultDAO consultDAO = new ConsultDAO(query, urlQuery);
        consultDAO.execute();

        boolean isConnectionTimedOut = this.testConnectionTime(consultDAO);

        String consultAnswer = STRING_EMPTY;
        if(!isConnectionTimedOut){
            consultAnswer = consultDAO.getResult();
        }
        else{
            consultAnswer = null;
        }

        return consultAnswer;
    }

    /**
     * Checks if query time exceeded the time limit
     *
     * @param timeLimit   Time limit to consult database
     * @param currentTime Current time of the consult
     * @return True if the limit was exceeded, false otherwise
     */
    public static boolean limitExceeded(long timeLimit, long currentTime){
        assert timeLimit >= 0;
        assert currentTime >= 0;

        boolean isLimitExceeded;

        if(currentTime < timeLimit){
            isLimitExceeded = false;
        }
        else{
            isLimitExceeded = true;
        }

        return isLimitExceeded;
    }

    /**
     * Executes a query in the database
     *
     * @param query Query text to be executed in the database
     * @return Executed query answer
     */
    protected String executeQuery(String query){
        assert query != null;

        String executedQuery = processQuery(query, URL_QUERY);
        assert executedQuery != null;

        return executedQuery;
    }

    /**
     * Executes database consult and creates a JSONObject from it
     *
     * @param query Query text to be executed in the database
     * @return JSONObject generated based on the consult
     */
    protected JSONObject executeConsult(String query){
        assert query != null;

        String consultJson = STRING_EMPTY;

        JSONObject jsonObject = null;

        try{
            consultJson = processQuery(query, URL_CONSULT);
            assert consultJson != null;

            jsonObject = new JSONObject(consultJson);
        } catch(Exception e){
            e.printStackTrace();
        }

        return jsonObject;
    }

    /**
     * Tests connection time of a given consult
     *
     * @param consultDAO Consult to be made in database
     * @return True if the connection had timed out, false otherwise
     */
    private boolean testConnectionTime(ConsultDAO consultDAO){
        long currentTime = Calendar.getInstance().getTime().getTime();
        assert currentTime >= 0;

        long timeLimit = currentTime + LIMIT_CONNECTION_TIME;
        assert timeLimit >= 0;

        while(!consultDAO.getIsDoing() && currentTime < timeLimit){
            currentTime = Calendar.getInstance().getTime().getTime();
        }

        boolean isConnectionTimedOut = false;
        if(limitExceeded(timeLimit, currentTime)){
            Toast.makeText(currentContext, CONNECTION_PROBLEM_MESSAGE, Toast.LENGTH_LONG).show();
            isConnectionTimedOut = true;
        }
        else{
            isConnectionTimedOut = false;
        }

        return isConnectionTimedOut;
    }
}
