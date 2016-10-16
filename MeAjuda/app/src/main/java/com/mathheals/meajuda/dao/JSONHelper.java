package com.mathheals.meajuda.dao;

import android.util.Log;

import com.mathheals.meajuda.model.School;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class JSONHelper{
    public static String getJSONObjectApi(final String URL) {
        String getApi = null;

        GetRequest request = new GetRequest();

        getApi = request.execute(URL).toString();

        try {
            getApi = request.get().toString();
        } catch (ExecutionException e){
            Log.d("ExecutionException", URL);
        } catch (InterruptedException e){
            Log.d("InterruptedException", URL);
        }
        return getApi;
    }

    //public static List<School> schoolListFromJsonFromName(String typedSchoolName)
    public static void schoolListFromJsonFromName(String typedSchoolName, int desiredNumberSchools)
            throws JSONException {
        final String URL = "http://mobile-aceite.tcu.gov.br/nossaEscolaRS/rest/escolas?" +
                "nome="+ typedSchoolName +"&quantidadeDeItens="+ desiredNumberSchools +"";

        String schoolListApiString = getJSONObjectApi(URL);

        Log.d("JSON: ", schoolListApiString);

        JSONObject schoolListJSONObject = new JSONObject(schoolListApiString);

        JSONArray schoolListJSONArray = schoolListJSONObject.getJSONArray("");

        List<School> schoolList = new ArrayList<>();

        /*JSONObject schools = new JSONObject(schoolList);
        JSONArray results = schools.getJSONArray("");

        populateSchoolList(results);*/
    }

    private static void populateSchoolList(JSONArray results) {

    }
}
