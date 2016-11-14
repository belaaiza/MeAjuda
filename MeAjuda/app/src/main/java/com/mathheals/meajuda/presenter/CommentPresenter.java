package com.mathheals.meajuda.presenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.mathheals.meajuda.dao.CommentDAO;
import com.mathheals.meajuda.dao.DownloadAudioTask;
import com.mathheals.meajuda.dao.DownloadImageTask;
import com.mathheals.meajuda.dao.PostRequest;
import com.mathheals.meajuda.model.Comment;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by izabela on 16/10/16.
 */
public class CommentPresenter {

    private static CommentPresenter instance;
    private static Context context;
    private static CommentDAO commentDAO;

    private CommentPresenter(Context currentContext) {
        this.context = currentContext;
    }

    public static CommentPresenter getInstance(final Context context) {
        if(CommentPresenter.instance != null){
            //nothing to do
        } else {
            CommentPresenter.instance = new CommentPresenter(context);
            commentDAO = CommentDAO.getInstance(context);
        }

        return CommentPresenter.instance;
    }

    public List<Comment> getCommentsOfTopic(int idTopic){
        List<Comment> allComments;

        allComments = CommentDAO.getInstance(context).getCommentsByTopicId(idTopic);

        return allComments;
    }

    public void createComment(Integer idTopic, Integer idCategory, Integer idUser, String comment,
                              Bitmap image, String base64Audio) {
        String imageURL = "N";

        if(image != null) {
            imageURL = postImage(idUser, image);
        }

        String audioURL = "N";

        if(base64Audio != null) {
            audioURL = postAudio(idUser, base64Audio);
        }

        CommentDAO commentDAO = CommentDAO.getInstance(context);
        commentDAO.createComment(idTopic, idCategory, idUser, comment, imageURL, audioURL);
    }

    public void playAudio(final Integer idComment) throws JSONException,
            IOException {
        final String URL = commentDAO.getAudioURLByCommentId(idComment);

        if(URL != "N") {
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

    public void showImage(ImageView image, String imageURL) {
        new DownloadImageTask(image).execute(imageURL);
    }
}
