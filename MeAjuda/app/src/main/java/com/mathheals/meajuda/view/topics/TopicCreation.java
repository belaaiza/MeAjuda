package com.mathheals.meajuda.view.topics;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mathheals.meajuda.R;
import com.mathheals.meajuda.presenter.TopicPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopicCreation extends Fragment implements View.OnClickListener {

    private EditText categoryIdEditText;
    private EditText titleEditText;
    private EditText descriptionEditText;

    public TopicCreation() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_topic_creation, container, false);

        categoryIdEditText = (EditText) view.findViewById(R.id.categoryField);
        titleEditText = (EditText) view.findViewById(R.id.titleField);
        descriptionEditText = (EditText) view.findViewById(R.id.descriptionField);

        Button createTopicButton = (Button) view.findViewById(R.id.create_topic_button);

        createTopicButton.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View v) {
        String title = titleEditText.getText().toString();
        int categoryId = Integer.parseInt(categoryIdEditText.getText().toString());
        String description = descriptionEditText.getText().toString();

        TopicPresenter topicPresenter = TopicPresenter.getInstance(getContext());

        topicPresenter.createTopic(categoryId, title, description);

        Toast.makeText(getActivity(), "Tópico criado com sucesso", Toast.LENGTH_LONG).show();
    }
}
