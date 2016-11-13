package com.mathheals.meajuda.dao;

import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;

import com.mathheals.meajuda.presenter.TopicPresenter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadAudioTask extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... urls) {
        String encodedAudio = "";

        try {
            URL url = new URL(urls[0]);

            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String str;

            while ((str = in.readLine()) != null) {
                encodedAudio += str;
            }
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        return encodedAudio;
    }

    protected void onPostExecute(String encodedAudio) {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" +
                TopicPresenter.generateFileNameExternalStorage() + ".3gp";

        File audioFile = new File(path);

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(audioFile);

            fos.write(Base64.decode(encodedAudio.getBytes(), Base64.DEFAULT));
            fos.close();

            MediaPlayer mp = new MediaPlayer();
            mp.setDataSource(path);
            mp.prepare();
            mp.start();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
