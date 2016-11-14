package com.mathheals.meajuda.view.topics;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mathheals.meajuda.R;
import com.mathheals.meajuda.dao.DownloadImageTask;
import com.mathheals.meajuda.model.Comment;
import com.mathheals.meajuda.presenter.CommentPresenter;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public class CommentListAdapter extends RecyclerView.Adapter<CommentListAdapter.ViewHolder> {

    private List<Comment> data;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title;
        public TextView description;
        public TextView author;

        public ImageView image;

        public Button playAudio;

        public ViewHolder(CardView card) {
            super(card);

            this.description = (TextView) card.findViewById(R.id.content);
            this.author = (TextView) card.findViewById(R.id.nameAuthor);
            this.image = (ImageView) card.findViewById(R.id.commentViewImage);
            this.playAudio = (Button) card.findViewById(R.id.commentViewPlayAudio);

            this.playAudio.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
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

        holder.description.setText(descriptionComment);

        if(imageURL != "N") {
            CommentPresenter commentPresenter = CommentPresenter.getInstance(context);

            commentPresenter.showImage(holder.image, imageURL);
        }

        //TODO: Adicionar o nome certo do autor
        holder.author.setText("Testando");
    }

    @Override
    public int getItemCount(){
        return data.size();
    }

}
