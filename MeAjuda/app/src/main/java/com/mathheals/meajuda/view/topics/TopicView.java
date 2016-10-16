package com.mathheals.meajuda.view.topics;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mathheals.meajuda.R;
import com.mathheals.meajuda.model.Comment;
import com.mathheals.meajuda.model.Topic;
import com.mathheals.meajuda.presenter.CommentPresenter;
import com.mathheals.meajuda.presenter.TopicPresenter;
import com.mathheals.meajuda.view.CardListAdapter;
import com.mathheals.meajuda.view.users.CommentsListAdapter;

import java.util.List;

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

        RecyclerView recyclerView = (RecyclerView) topicView.findViewById
                (R.id.recycler_view_topics);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        CommentPresenter commentPresenter = CommentPresenter.getInstance(getContext());
        List<Comment> data = commentPresenter.getCommentsOfTopic(idTopic, getContext());

        recyclerView.setAdapter(new CommentsListAdapter(this.getContext(), data));

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
    }

    private void setTopicInfo(int idTopic) {
        TopicPresenter topicPresenter = TopicPresenter.getInstance(getContext());

        Topic topic = topicPresenter.getTopicById(idTopic);

        nameAuthorTextView.setText(topic.getNameOwner());
        titleTextView.setText(topic.getTitle());
        contentTextView.setText(topic.getDescription());
    }


}
