package com.mathheals.meajuda.dao;

import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Consult {

    private  String url;
    private String result;
    private boolean isDoing;
    private String query;
    private final String PARAM = "query";

    public Consult(String query, String url) {
        this.query= query;
        this.url = url;
        setIsDoing(false);
    }

    public boolean getIsDoing() {
        return isDoing;
    }

    public void setIsDoing(boolean value) {
        isDoing = value;
    }

    public String exec() {
        new Access().execute();
        return getResult();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    private class Access extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... args) {
            try {
                HttpResponse response;
                HttpClient client = new DefaultHttpClient();
                HttpPost post = new HttpPost(url);

                List<NameValuePair> pairs = new ArrayList<>();
                pairs.add(new BasicNameValuePair(PARAM, query));

                post.setEntity(new UrlEncodedFormEntity(pairs));
                response = client.execute(post);

                result = inputStreamToString(response.getEntity().getContent()).toString();
                setIsDoing(true);

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute(String result) {
            Consult.this.setIsDoing(true);
        }

        private StringBuilder inputStreamToString(InputStream is) throws IOException {
            String rLine = "";
            StringBuilder answer = new StringBuilder();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));

            while ((rLine = rd.readLine()) != null) {
                answer.append(rLine);
            }
            return answer;
        }
    }


}