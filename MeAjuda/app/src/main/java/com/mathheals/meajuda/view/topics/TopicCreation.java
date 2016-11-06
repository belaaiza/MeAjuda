package com.mathheals.meajuda.view.topics;


import android.os.Bundle;
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

        ImageView createTopicButton = (ImageView) view.findViewById(R.id.create_topic_button);
        createTopicButton.setOnClickListener(this);

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
        String title = titleEditText.getText().toString();
        String description = descriptionEditText.getText().toString();

        TopicPresenter topicPresenter = TopicPresenter.getInstance(getContext());

        topicPresenter.createTopic(categoryId, title, description);

        Toast.makeText(getActivity(), "Tópico criado com sucesso", Toast.LENGTH_LONG).show();
    }
}
