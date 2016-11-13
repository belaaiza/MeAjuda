package com.mathheals.meajuda.view.topics;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mathheals.meajuda.R;
import com.mathheals.meajuda.dao.DownloadImageTask;
import com.mathheals.meajuda.model.Comment;
import com.mathheals.meajuda.model.Topic;
import com.mathheals.meajuda.presenter.CommentPresenter;
import com.mathheals.meajuda.presenter.TopicPresenter;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopicView extends Fragment implements View.OnClickListener {

    private TextView nameAuthorTextView;
    private TextView titleTextView;
    private TextView contentTextView;
    private ImageView topicImage;

    Integer idTopic = 0;

    public TopicView() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View topicView = inflater.inflate(R.layout.fragment_topic_view, container, false);

        Bundle args = this.getArguments();

        idTopic = args.getInt("idTopic", 0);
        Log.d("Topic id: ", idTopic + "");

        assert(idTopic != 0);

        RecyclerView recyclerView = (RecyclerView) topicView.findViewById
                (R.id.recycler_view_topics);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        CommentPresenter commentPresenter = CommentPresenter.getInstance(getContext());
        List<Comment> comments = commentPresenter.getCommentsOfTopic
                (idTopic, getContext());

        CommentListAdapter commentListAdapter = new CommentListAdapter(getContext(), comments);

        recyclerView.setAdapter(commentListAdapter);

        setViews(topicView);
        setTopicInfo();

        return topicView;
    }

    private void setViews(View view) {
        nameAuthorTextView = (TextView) view.findViewById(R.id.nameAuthor);

        titleTextView = (TextView) view.findViewById(R.id.title);
        titleTextView.setTypeface(null, Typeface.BOLD);

        topicImage = (ImageView) view.findViewById(R.id.topicViewImage);

        contentTextView = (TextView) view.findViewById(R.id.content);

        Button playAudioButton = (Button) view.findViewById(R.id.topicViewPlayAudio);
        playAudioButton.setOnClickListener(this);
    }

    private void setTopicInfo() {
        Log.d("id do topico: ", idTopic + "");

        TopicPresenter topicPresenter = TopicPresenter.getInstance(getContext());

        Topic topic = topicPresenter.getTopicById(idTopic);

        String imageURL = topic.getImageURL();

        Log.d("imageURL ", imageURL);

        if(imageURL != "N") {
            Log.d("Entrei aqui", "setTopicInfo ");
            new DownloadImageTask(topicImage).execute(imageURL);
            topicImage.setVisibility(View.VISIBLE);
        }

        nameAuthorTextView.setText(topic.getNameOwner());
        titleTextView.setText(topic.getTitle());
        contentTextView.setText(topic.getDescription());
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.topicViewPlayAudio:
                TopicPresenter topicPresenter = TopicPresenter.getInstance(getContext());

                try {
                    topicPresenter.playAudio(idTopic);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
        }
    }

}
