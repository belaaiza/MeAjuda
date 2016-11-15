package com.mathheals.meajuda.view.topics;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mathheals.meajuda.R;
import com.mathheals.meajuda.model.Comment;
import com.mathheals.meajuda.presenter.CommentEvaluationPresenter;
import com.mathheals.meajuda.presenter.CommentPresenter;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public class CommentListAdapter extends RecyclerView.Adapter<CommentListAdapter.ViewHolder> {

    private List<Comment> data;
    private Context context;

    public Integer commentEvaluationValue;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title;
        public TextView description;
        public TextView author;
        public TextView commentEvaluation;

        public ImageView image;
        public ImageView upEvaluate;
        public ImageView downEvaluate;

        public Button playAudio;

        public ViewHolder(CardView card) {
            super(card);

            this.description = (TextView) card.findViewById(R.id.content);
            this.author = (TextView) card.findViewById(R.id.nameAuthor);
            this.commentEvaluation = (TextView) card.findViewById(R.id.commentEvalutation);
            this.image = (ImageView) card.findViewById(R.id.commentViewImage);
            this.playAudio = (Button) card.findViewById(R.id.commentViewPlayAudio);
            this.upEvaluate = (ImageView) card.findViewById(R.id.up_evaluation);
            this.downEvaluate = (ImageView) card.findViewById(R.id.down_evaluation);

            this.playAudio.setOnClickListener(this);
            this.upEvaluate.setOnClickListener(this);
            this.downEvaluate.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            CommentEvaluationPresenter commentEvaluationPresenter = CommentEvaluationPresenter.
                    getInstance(context);

            Comment currentComment = data.get(this.getAdapterPosition());

            final Integer POSITIVE_EVALUATION = 1;
            final Integer NEGATIVE_EVALUATION = -1;

            SharedPreferences session = PreferenceManager.getDefaultSharedPreferences(context);
            int loggedUserId = session.getInt("id",-1);

            switch (v.getId()) {
                case R.id.commentViewPlayAudio:
                    CommentPresenter commentPresenter = CommentPresenter.getInstance(context);

                    Comment comment = data.get(this.getAdapterPosition());

                    try {
                        commentPresenter.playAudio(comment.getIdComment());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    break;

                case R.id.up_evaluation:

                    try {
                        commentEvaluationPresenter.evaluateComment(currentComment.getIdComment(),
                                currentComment.getIdTopic(), currentComment.getIdCategory(),
                                currentComment.getIdUser(), POSITIVE_EVALUATION, loggedUserId);
                        commentEvaluationValue++;

                        commentEvaluation.setText(commentEvaluationValue + "");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    break;

                case R.id.down_evaluation:
                    try {
                        commentEvaluationPresenter.evaluateComment(currentComment.getIdComment(),
                                currentComment.getIdTopic(), currentComment.getIdCategory(),
                                currentComment.getIdUser(), NEGATIVE_EVALUATION, loggedUserId);
                        commentEvaluationValue--;

                        commentEvaluation.setText(commentEvaluationValue + "");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    break;
            }
        }
    }

    public CommentListAdapter(Context context, List<Comment> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public CommentListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CardView view = (CardView) inflater.inflate(R.layout.card_item_comments, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onAttachedToRecyclerView(final RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Comment rowData = this.data.get(position);

        String descriptionComment = rowData.getDescription();
        String imageURL = rowData.getImageURL();

        CommentEvaluationPresenter commentEvaluationPresenter = CommentEvaluationPresenter.
                getInstance(context);

        Integer idComment = rowData.getIdComment();

        try {
            Integer commentEvaluationValue = commentEvaluationPresenter.getCommentEvaluation(idComment);
            holder.commentEvaluation.setText(commentEvaluationValue + "");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            commentEvaluationValue = commentEvaluationPresenter.getCommentEvaluation(rowData.
                    getIdComment());
            holder.commentEvaluation.setText(commentEvaluationValue + "");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        holder.description.setText(descriptionComment);
        SharedPreferences session = PreferenceManager.getDefaultSharedPreferences(context);
        String authorName = session.getString("name","user");
        holder.author.setText(authorName);


        if(imageURL != "N") {
            CommentPresenter commentPresenter = CommentPresenter.getInstance(context);

            commentPresenter.showImage(holder.image, imageURL);
        }
    }

    @Override
    public int getItemCount(){
        return data.size();
    }

}
