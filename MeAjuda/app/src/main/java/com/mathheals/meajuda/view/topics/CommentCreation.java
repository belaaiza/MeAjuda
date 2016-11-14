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

import com.mathheals.meajuda.R;
import com.mathheals.meajuda.dao.CommentDAO;
import com.mathheals.meajuda.presenter.CommentPresenter;
import com.mathheals.meajuda.presenter.TopicPresenter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

/**
 * A simple {@link Fragment} subclass.
 */
public class CommentCreation extends Fragment implements View.OnClickListener{
    private Integer idTopic = 0;
    private Integer idCategory = 0;

    private EditText commentField;

    private ImageView selectImageButton;
    private ImageView imagePreview;

    private MediaRecorder mediaRecorder;

    MediaPlayer mediaPlayer;

    private boolean recording = false;

    private String AudioSavePathInDevice = null;

    private static final Integer RESULT_LOAD_IMAGE = 9002;

    public static final int RequestPermissionCode = 1;

    public CommentCreation() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_comment_creation, container, false);

        Bundle args = this.getArguments();

        idTopic = args.getInt("idTopic", 0);
        idCategory = args.getInt("idCategory", 0);

        Log.d("idTopic", idTopic + "");
        Log.d("idCategory", idCategory + "");

        assert idTopic != 0;
        assert idCategory != 0;

        ImageView createCommenButton = (ImageView) view.findViewById(R.id.create_comment_button);
        createCommenButton.setOnClickListener(this);

        commentField = (EditText) view.findViewById(R.id.descriptionCommentField);

        selectImageButton = (ImageView) view.findViewById(R.id.imageIcon);
        selectImageButton.setOnClickListener(this);

        imagePreview = (ImageView) view.findViewById(R.id.imageCommentPreview);

        ImageView startStopRecording = (ImageView) view.findViewById(R.id.record_comment_audio);
        startStopRecording.setOnClickListener(this);

        Button startPlaying = (Button) view.findViewById(R.id.comment_play_audio);
        startPlaying.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.create_comment_button:
                String description = commentField.getText().toString();

                String encodedAudio = encodeAudio(AudioSavePathInDevice);

                Bitmap image = null;

                if(imagePreview.getDrawable() != null) {
                    image = ((BitmapDrawable) imagePreview.getDrawable()).getBitmap();
                }

                CommentPresenter commentPresenter = CommentPresenter.getInstance(getContext());
                commentPresenter.createComment(idTopic, idCategory, 7, description, image,
                        encodedAudio);

                Log.d("id do topico", idTopic + "");
                Log.d("id da categoria", idCategory + "");
                Log.d("comentário", description);

                Toast.makeText(getActivity().getBaseContext(), "Comentário criado com sucesso",
                        Toast.LENGTH_SHORT).show();

                break;

            case R.id.imageIcon:
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);

                break;

            case R.id.record_comment_audio:
                if(!recording) {
                    if(!checkPermission()) {
                        ActivityCompat.requestPermissions(getActivity(), new
                                String[]{WRITE_EXTERNAL_STORAGE, RECORD_AUDIO}, RequestPermissionCode);
                    }

                    //TODO: Adicionar um subdiretório com o nome do APP e outro com o nome da pasta em q os áudios ficarão salvos
                    AudioSavePathInDevice =
                            Environment.getExternalStorageDirectory().getAbsolutePath() + "/" +
                                    TopicPresenter.generateFileNameExternalStorage() + ".3gp";

                    AudioSavePathInDevice = AudioSavePathInDevice.replaceAll(":", "");

                    Log.d("Caminho", AudioSavePathInDevice);

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

            case R.id.comment_play_audio:
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
                break;
        }
    }

    private String encodeAudio(String selectedPath) {
        //TODO: Testar se funfa sem esse if
        if(selectedPath == null) {
            return null;
        }

        byte[] audioBytes;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(new File(selectedPath));

            byte[] buf = new byte[1024];

            int n;

            while (-1 != (n = fis.read(buf))) {
                baos.write(buf, 0, n);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        audioBytes = baos.toByteArray();

        String _audioBase64 = Base64.encodeToString(audioBytes, Base64.DEFAULT);

        return _audioBase64;
    }

    public void MediaRecorderReady() {
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
