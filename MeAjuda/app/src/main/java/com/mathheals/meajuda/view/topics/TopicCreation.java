package com.mathheals.meajuda.view.topics;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
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

                topicPresenter.createTopic(1, categoryId, title, description, image);

                Toast.makeText(getActivity(), "Tópico criado com sucesso", Toast.LENGTH_LONG).show();

                break;
            case R.id.image:
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);

                break;

            default:
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d("here", "onActivityResult ");

        if (requestCode == RESULT_LOAD_IMAGE /*&& resultCode == RESULT_OK */&& data != null){
            Log.d("here2", "onActivityResult ");
            Uri selectedImage = data.getData();
            imagePreview.setImageURI(null);
            imagePreview.setImageURI(selectedImage);
            imagePreview.setVisibility(View.VISIBLE);
        }
    }
}
