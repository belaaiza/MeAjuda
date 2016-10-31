package com.mathheals.meajuda.view.topics;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mathheals.meajuda.R;
import com.mathheals.meajuda.model.Comment;

import java.util.List;

public class CommentListAdapter extends RecyclerView.Adapter<CommentListAdapter.ViewHolder> {

    private List<Comment> data;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView description;
        public TextView author;

        public ViewHolder(CardView card) {
            super(card);

            this.description = (TextView) card.findViewById(R.id.content);
            this.author = (TextView) card.findViewById(R.id.nameAuthor);
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

        holder.description.setText(rowData.getDescription());
        holder.author.setText("Testando");
    }

    @Override
    public int getItemCount(){
        return data.size();
    }

}
