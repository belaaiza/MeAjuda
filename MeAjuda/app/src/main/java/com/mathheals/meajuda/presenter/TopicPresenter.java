package com.mathheals.meajuda.presenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.mathheals.meajuda.dao.DownloadAudioTask;
import com.mathheals.meajuda.dao.PostRequest;
import com.mathheals.meajuda.dao.TopicDAO;
import com.mathheals.meajuda.model.Topic;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
                            Bitmap image, String base64Audio){
        String imageURL = "N";

        if(image != null) {
            imageURL = postImage(idUser, image);
        }

        String audioURL = "N";

        if(base64Audio != null) {
            audioURL = postAudio(idUser, base64Audio);
        }

        topicDAO.createTopic(categoryId, title, description, imageURL, audioURL);
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

    public List<Topic> getAllTopics(){
        List<Topic> topicList;

        topicList = topicDAO.getAllTopics();

        return topicList;
    }

    public void playAudio(final Integer idTopic) throws JSONException,
            IOException {
        final String URL = topicDAO.getAudioURLByTopicId(idTopic);

        if(URL != "N") {
            Log.d("Audio URL: ", topicDAO.getAudioURLByTopicId(idTopic));

            DownloadAudioTask downloadAudioTask = new DownloadAudioTask();
            downloadAudioTask.execute(URL);
        }else {
            Toast.makeText(context, "Esse tópico não possui um áudio anexado",
                    Toast.LENGTH_LONG).show();

            Log.d("playAudio", "Esse tópico não possui um áudio anexado");
        }
    }

    private String postAudio(final Integer idUser, String base64Audio) {
        final String URL = "https://meajuda.000webhostapp.com/save_audio.php";

        String name = generateFileName(idUser);

        List<NameValuePair> postParams = new ArrayList<>();
        postParams.add(new BasicNameValuePair("name", name));
        postParams.add(new BasicNameValuePair("audio", base64Audio));

        PostRequest postRequest = new PostRequest(context, URL, postParams);
        postRequest.execute();

        final String AUDIO_URL = "https://meajuda.000webhostapp.com/records/" + name + ".txt";

        return AUDIO_URL;
    }

    private String postImage(final Integer idUser, Bitmap image) {
        final String URL = "https://meajuda.000webhostapp.com/save_picture.php";

        String name = generateFileName(idUser);

        String encodedImage = encodeImage(image);

        Log.d("image name: ", name);

        List<NameValuePair> postParams = new ArrayList<>();
        postParams.add(new BasicNameValuePair("name", name));
        postParams.add(new BasicNameValuePair("image", encodedImage));

        PostRequest postRequest = new PostRequest(context, URL, postParams);
        postRequest.execute();

        final String IMAGE_URL = "https://meajuda.000webhostapp.com/pictures/" + name + ".jpg";

        return IMAGE_URL;
    }

    private String encodeImage(Bitmap image) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 70, byteArrayOutputStream);

        String encodedImage = Base64.encodeToString(byteArrayOutputStream.toByteArray(),
                Base64.DEFAULT);

        return encodedImage;
    }

    public static String generateFileName(Integer idUser) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        String dateString = dateFormat.format(date);

        dateString = dateString.replace("/", "-");
        dateString = dateString.replace(" ", "_");

        String name = dateString + "_" + idUser;

        return name;
    }

    public static String generateFileNameExternalStorage() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        String dateString = dateFormat.format(date);

        dateString = dateString.replace("/", "-");
        dateString = dateString.replace(" ", "_");
        dateString = dateString.replace(":", "");

        return dateString;
    }
}
