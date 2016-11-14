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
import com.mathheals.meajuda.presenter.CommentPresenter;
import com.mathheals.meajuda.presenter.TopicPresenter;

import java.util.List;

public class TopicView extends Fragment implements View.OnClickListener {

    private TextView nameAuthorTextView;
    private TextView titleTextView;
    private TextView contentTextView;
    private ImageView topicImage;
    private Topic currentTopic;

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

        topicImage = (ImageView) view.findViewById(R.id.topicViewImage);

        contentTextView = (TextView) view.findViewById(R.id.content);
    }

    private void setTopicInfo(int idTopic) {
        Log.d("id do topico: ", idTopic + "");

        TopicPresenter topicPresenter = TopicPresenter.getInstance(getContext());

        currentTopic = topicPresenter.getTopicById(idTopic);

        String imageURL = currentTopic.getImageURL();

        /*if(imageURL != "N") {
            try {
                Log.d("URL: ", imageURL);
                Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(imageURL).
                        getContent());

                topicImage.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/

        new DownloadImageTask(topicImage).execute(imageURL);
        topicImage.setVisibility(View.VISIBLE);

        nameAuthorTextView.setText(currentTopic.getNameOwner());
        titleTextView.setText(currentTopic.getTitle());
        contentTextView.setText(currentTopic.getDescription());
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
        if(v.getId() == R.id.up_evaluation){
            TopicPresenter topicPresenter = TopicPresenter.getInstance(getContext());
            //TODO chamar aqui o metodo da presenter com id do topico, do usuario e avaliacao plus one
            //topicPresenter.evaluateTopic(currentTopic.getIdTopic(), currentTopic.);
        }
        else if(v.getId() == R.id.down_evaluation){

        }
    }
}
