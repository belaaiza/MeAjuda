package com.mathheals.meajuda.dao;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PostRequest extends AsyncTask<String, String, String> {

    private String url = "";
    private Context context;
    private int response = 400;
    private List<NameValuePair> postParams;

    public PostRequest(Context context, String url, List<NameValuePair> postParams){
        this.context = context;
        this.url = url;
        this.postParams = postParams;
    }

    protected void onPreExecute() {
        super.onPreExecute();
    }

    protected String doInBackground(String... params) {
        String result = null;

        try {
            HttpResponse response;
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(url);

            post.setEntity(new UrlEncodedFormEntity(postParams));
            response = client.execute(post);

            result = inputStreamToString(response.getEntity().getContent()).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    protected void onPostExecute(String bodyTextResponse) {
        super.onPostExecute(bodyTextResponse);
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
