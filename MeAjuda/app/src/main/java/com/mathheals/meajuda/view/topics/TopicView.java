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
import android.widget.ImageView;
import android.widget.TextView;

import com.mathheals.meajuda.R;
import com.mathheals.meajuda.dao.DownloadImageTask;
import com.mathheals.meajuda.model.Comment;
import com.mathheals.meajuda.model.Topic;
import com.mathheals.meajuda.model.TopicEvaluation;
import com.mathheals.meajuda.presenter.CommentPresenter;
import com.mathheals.meajuda.presenter.TopicEvaluationPresenter;
import com.mathheals.meajuda.presenter.TopicPresenter;

import org.json.JSONException;

import java.util.List;

public class TopicView extends Fragment implements View.OnClickListener {

    private TextView nameAuthorTextView;
    private TextView titleTextView;
    private TextView contentTextView;
    private TextView topicEvaluationTextView;

    private ImageView topicImage;

    private Topic currentTopic;

    private Integer topicEvaluation;

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

        RecyclerView recyclerView = (RecyclerView) topicView.findViewById
                (R.id.recycler_view_topics);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        CommentPresenter commentPresenter = CommentPresenter.getInstance(getContext());
        List<Comment> comments = commentPresenter.getCommentsOfTopic
                (idTopic, getContext());

        CommentListAdapter commentListAdapter = new CommentListAdapter(getContext(), comments);

        ImageView upEvaluate = (ImageView) topicView.findViewById(R.id.up_evaluation);
        upEvaluate.setOnClickListener(this);

        ImageView downEvaluate = (ImageView) topicView.findViewById(R.id.down_evaluation);
        downEvaluate.setOnClickListener(this);

        recyclerView.setAdapter(commentListAdapter);

        setViews(topicView);
        setTopicInfo(idTopic);

        return topicView;
    }

    private void setViews(View view) {
        nameAuthorTextView = (TextView) view.findViewById(R.id.nameAuthor);

        titleTextView = (TextView) view.findViewById(R.id.title);
        titleTextView.setTypeface(null, Typeface.BOLD);

        topicEvaluationTextView = (TextView) view.findViewById(R.id.topicEvaluation);

        contentTextView = (TextView) view.findViewById(R.id.content);

        topicImage = (ImageView) view.findViewById(R.id.topicViewImage);
    }

    private void setTopicInfo(int idTopic) {
        Log.d("id do topico: ", idTopic + "");

        TopicPresenter topicPresenter = TopicPresenter.getInstance(getContext());

        currentTopic = topicPresenter.getTopicById(idTopic);

        String imageURL = currentTopic.getImageURL();

        new DownloadImageTask(topicImage).execute(imageURL);
        topicImage.setVisibility(View.VISIBLE);

        nameAuthorTextView.setText(currentTopic.getNameOwner());
        titleTextView.setText(currentTopic.getTitle());
        contentTextView.setText(currentTopic.getDescription());

        TopicEvaluationPresenter topicEvaluationPresenter = TopicEvaluationPresenter.
                getInstance(getContext());

        try {
            topicEvaluation = topicEvaluationPresenter.getTopicEvaluation(idTopic);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        topicEvaluationTextView.setText(topicEvaluation + "");
    }

    @Override
    public void onDestroy(){
        if(this.getArguments().getBoolean("comeFromSearch")){
            getActivity().finish();
        }

        super.onDestroy();
    }

    @Override
    public void onClick(View v){
        TopicEvaluationPresenter topicEvaluationPresenter =
                TopicEvaluationPresenter.getInstance(getContext());

        final Integer POSITIVE_EVALUATION = 1;
        final Integer NEGATIVE_EVALUATION = -1;

        switch (v.getId()) {
            case R.id.up_evaluation:
                //TODO: Trocar esse número mágico pelo id do usuário
                try {
                    topicEvaluationPresenter.evaluateTopic(currentTopic.getIdTopic(),
                            currentTopic.getIdCategory(), POSITIVE_EVALUATION, 7);
                    //TODO: Arrumar o bug do caso em que ele já havia votado, seja positivamente ou negativamente
                    topicEvaluation++;
                    topicEvaluationTextView.setText(topicEvaluation + "");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                break;

            case R.id.down_evaluation:
                //TODO: Trocar esse número mágico pelo id do usuário
                try {
                    topicEvaluationPresenter.evaluateTopic(currentTopic.getIdTopic(),
                            currentTopic.getIdCategory(), NEGATIVE_EVALUATION, 7);
                    topicEvaluation--;
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                break;
        }
    }
}
