package com.mathheals.meajuda.presenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Base64;
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
import java.io.ByteArrayOutputStream;
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

    public void createTopic(Integer idUser, Integer categoryId, String title, String description,
                            Bitmap image){
        String imageURL = "N";

        if(image != null) {
            imageURL = postImage(idUser, image);
        }

        topicDAO.createTopic(categoryId, title, description, imageURL);
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

    private String postImage(final Integer idUser, Bitmap image) {
        final String URL ="https://meajuda.000webhostapp.com/save_picture.php";

        String name = generateImageName(idUser);

        String encodedImage = encodeImage(image);

        Log.d("image name: ", name);

        List<NameValuePair> postParams = new ArrayList<>();
        postParams.add(new BasicNameValuePair("name", name));
        postParams.add(new BasicNameValuePair("image", encodedImage));

        PostRequest postRequest = new PostRequest(context, URL, postParams);
        postRequest.execute();

        final String IMAGE_URL = "https://meajuda.000webhostapp.com/" + name;

        return IMAGE_URL;
    }

    private String encodeImage(Bitmap image) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 70, byteArrayOutputStream);

        String encodedImage = Base64.encodeToString(byteArrayOutputStream.toByteArray(),
                Base64.DEFAULT);

        return encodedImage;
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
