package com.mathheals.meajuda.view.topics;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.mathheals.meajuda.R;
import com.mathheals.meajuda.presenter.TopicPresenter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopicCreation extends Fragment implements View.OnClickListener, MaterialSpinner
        .OnItemSelectedListener {

    private Integer categoryId;
    private EditText titleEditText;
    private EditText descriptionEditText;

    private ImageView createTopicButton;
    private ImageView selectImageButton;
    private ImageView imagePreview;

    private boolean recording = false;

    MediaRecorder mediaRecorder;

    MediaPlayer mediaPlayer ;

    String AudioSavePathInDevice = null;

    public static final int RequestPermissionCode = 1;

    private static final Integer RESULT_LOAD_IMAGE = 9002;

    public TopicCreation() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_topic_creation, container, false);

        MaterialSpinner spinner = (MaterialSpinner) view.findViewById(R.id.category_spinner);
        spinner.setItems("Matemática", "Português", "História", "Geografia", "Ed Física", "Física",
                "Filosofia", "Sociologia", "Inglês", "Espanhol", "Biologia", "Outras");
        spinner.setOnItemSelectedListener(this);

        titleEditText = (EditText) view.findViewById(R.id.titleField);
        descriptionEditText = (EditText) view.findViewById(R.id.descriptionField);

        imagePreview = (ImageView) view.findViewById(R.id.imagePreview);

        createTopicButton = (ImageView) view.findViewById(R.id.create_topic_button);
        createTopicButton.setOnClickListener(this);

        selectImageButton = (ImageView) view.findViewById(R.id.image);
        selectImageButton.setOnClickListener(this);


        ImageView startStopRecording = (ImageView) view.findViewById(R.id.attachment);
        startStopRecording.setOnClickListener(this);

        Button startPlaying = (Button) view.findViewById(R.id.play_audio);
        startPlaying.setOnClickListener(this);

        Button uploadAudio = (Button) view.findViewById(R.id.upload_audio);
        uploadAudio.setOnClickListener(this);

        return view;
    }

    @Override
    public void onItemSelected(MaterialSpinner view, int position, long id, Object item){
        if(item.equals("Matemática")){
            categoryId = 1;
        }
        else if(item.equals("Português")){
            categoryId = 2;
        }
        else if(item.equals("História")){
            categoryId = 3;
        }
        else if(item.equals("Geografia")){
            categoryId = 4;
        }
        else if(item.equals("Ed Física")){
            categoryId = 5;
        }
        else if(item.equals("Física")){
            categoryId = 6;
        }
        else if(item.equals("Filosofia")){
            categoryId = 7;
        }
        else if(item.equals("Sociologia")){
            categoryId = 8;
        }
        else if(item.equals("Inglês")){
            categoryId = 9;
        }
        else if(item.equals("Espanhol")){
            categoryId = 10;

        }else if(item.equals("Biologia")){
            categoryId = 11;
        }
        else if(item.equals("Outras")){
            categoryId = 12;
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.create_topic_button:
                String title = titleEditText.getText().toString();
                String description = descriptionEditText.getText().toString();

                TopicPresenter topicPresenter = TopicPresenter.getInstance(getContext());

                Bitmap image = ((BitmapDrawable) imagePreview.getDrawable()).getBitmap();

                topicPresenter.createTopic(7, 1, title, description, image);

                Toast.makeText(getActivity(), "Tópico criado com sucesso", Toast.LENGTH_LONG).show();

                break;

            case R.id.image:
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);

                break;

            case R.id.upload_audio:
                String encoded_audio = encodeAudio(AudioSavePathInDevice);

                Log.d("Encoded Audio: ", encoded_audio);

                decodeAudio(encoded_audio, AudioSavePathInDevice);

                break;

            case R.id.attachment:
                Log.d("to playando", "onClick ");
                if(!recording) {
                    if(!checkPermission()) {
                        ActivityCompat.requestPermissions(getActivity(), new
                                String[]{WRITE_EXTERNAL_STORAGE, RECORD_AUDIO}, RequestPermissionCode);
                    }

                    AudioSavePathInDevice =
                            Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + "AudioRecording.3gp";

                    MediaRecorderReady();

                    try {
                        mediaRecorder.prepare();
                        mediaRecorder.start();
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Toast.makeText(getActivity(), "Recording started",
                            Toast.LENGTH_LONG).show();

                    recording = true;
                }else {
                    mediaRecorder.stop();

                    Toast.makeText(getActivity(), "Recording Completed",
                            Toast.LENGTH_LONG).show();

                    recording = false;
                }

                break;

            case R.id.play_audio:
                mediaPlayer = new MediaPlayer();
                try {
                    mediaPlayer.setDataSource(AudioSavePathInDevice);
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                mediaPlayer.start();
                Toast.makeText(getActivity(), "Recording Playing",
                        Toast.LENGTH_LONG).show();

                break;

            default:
        }

    }

    private void decodeAudio(String base64AudioData, String path) {

        try {
            String aux = Environment.getExternalStorageDirectory().getAbsolutePath() + "/novo.3gp";

            File audioFile = new File(aux);

            FileOutputStream fos = new FileOutputStream(audioFile);
            fos.write(Base64.decode(base64AudioData.getBytes(), Base64.DEFAULT));
            fos.close();

            try {

                MediaPlayer mp = new MediaPlayer();
                mp.setDataSource(aux);
                mp.prepare();
                mp.start();

            } catch (Exception e) {
                Log.d("deu ruim", "decodeAudio ");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private String encodeAudio(String selectedPath) {

        byte[] audioBytes;

            // Just to check file size.. Its is correct i-e; Not Zero
            File audioFile = new File(selectedPath);
            long fileSize = audioFile.length();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(new File(selectedPath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        byte[] buf = new byte[1024];
            int n;
        try {
            while (-1 != (n = fis.read(buf)))
                baos.write(buf, 0, n);
        } catch (IOException e) {
            e.printStackTrace();
        }
        audioBytes = baos.toByteArray();

        // Here goes the Base64 string
        String _audioBase64 = Base64.encodeToString(audioBytes, Base64.DEFAULT);

        return _audioBase64;
    }

    public void MediaRecorderReady(){
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mediaRecorder.setOutputFile(AudioSavePathInDevice);
    }

    public boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getContext(),
                WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(getContext(),
                RECORD_AUDIO);
        return result == PackageManager.PERMISSION_GRANTED &&
                result1 == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && data != null){
            Uri selectedImage = data.getData();
            imagePreview.setImageURI(null);
            imagePreview.setImageURI(selectedImage);
            imagePreview.setVisibility(View.VISIBLE);
        }
    }
}
