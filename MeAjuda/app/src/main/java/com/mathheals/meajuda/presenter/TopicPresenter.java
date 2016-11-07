package com.mathheals.meajuda.presenter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.mathheals.meajuda.dao.PostRequest;
import com.mathheals.meajuda.dao.TopicDAO;
import com.mathheals.meajuda.model.Comment;
import com.mathheals.meajuda.model.Topic;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class TopicPresenter {

    private static TopicPresenter instance;
    private Context context;
    private static TopicDAO topicDAO;

    private TopicPresenter(Context currentContext) {
        this.context = currentContext;
    }

    public static TopicPresenter getInstance(final Context context) {
        if(TopicPresenter.instance != null){
            //nothing to do
        } else {
            TopicPresenter.instance = new TopicPresenter(context);
            topicDAO = TopicDAO.getInstance(context);
        }

        return TopicPresenter.instance;
    }

    public void createTopic(int categoryId, String title, String description){
        topicDAO.createTopic(categoryId, title, description);
    }

    public List<Topic> getTopicsByCategory(int idCategory) {
        List<Topic> topics = new ArrayList<>();

        try {
            topics = topicDAO.getTopicsByCategory(idCategory);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return topics;
    }

    public Topic getTopicById(int idTopic) {
        Topic topic = null;

        try {
            topic = topicDAO.getTopicById(idTopic);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return topic;
    }

    public void postImage(final Integer idUser, final String encodedImage) {
        final String URL ="https://meajuda.000webhostapp.com/save_picture.php";

        String name = generateImageName(idUser);

        Log.d("image name: ", name);

        List<NameValuePair> postParams = new ArrayList<>();
        postParams.add(new BasicNameValuePair("name", name));
        postParams.add(new BasicNameValuePair("image", encodedImage));

        PostRequest postRequest = new PostRequest(context, URL, postParams);
        postRequest.execute();
    }

    private String generateImageName(Integer idUser) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        String dateString = dateFormat.format(date);

        dateString = dateString.replace("/", "-");
        dateString = dateString.replace(" ", "_");

        String name = dateString + "_" + idUser;

        return name;
    }
}
