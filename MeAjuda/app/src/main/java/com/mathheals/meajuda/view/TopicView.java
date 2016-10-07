package com.mathheals.meajuda.view;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mathheals.meajuda.R;
import com.mathheals.meajuda.model.Topic;
import com.mathheals.meajuda.presenter.TopicPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopicView extends Fragment {

    private TextView nameAuthorTextView;
    private TextView titleTextView;
    private TextView contentTextView;
    private TextView likesTextView;
    private TextView dislikesTextView;

    public TopicView() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View topicView = inflater.inflate(R.layout.fragment_topic_view, container, false);

        Bundle args = this.getArguments();

        int idTopic = args.getInt("idTopic", 0);
        Log.d("Topic id: ", idTopic + "");

        assert(idTopic != 0);

        setTextViews(topicView);
        setTopicInfo(idTopic);

        return topicView;
    }

    private void setTextViews(View view) {
        nameAuthorTextView = (TextView) view.findViewById(R.id.nameAuthor);

        titleTextView = (TextView) view.findViewById(R.id.title);
        titleTextView.setTypeface(null, Typeface.BOLD);

        contentTextView = (TextView) view.findViewById(R.id.content);
        likesTextView = (TextView) view.findViewById(R.id.likes);
        dislikesTextView = (TextView) view.findViewById(R.id.dislikes);
    }

    private void setTopicInfo(int idTopic) {
        TopicPresenter topicPresenter = TopicPresenter.getInstance(getContext());

        Topic topic = topicPresenter.getTopicById(idTopic);

        nameAuthorTextView.setText(topic.getNameOwner());
        titleTextView.setText(topic.getTitle());
        contentTextView.setText(topic.getDescription());

        //Trocar essa parte pela avaliação real depois
        likesTextView.setText("7 likes");
        dislikesTextView.setText("3 dislikes");

    }


}
