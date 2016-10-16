package com.mathheals.meajuda.view.users;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mathheals.meajuda.R;
import com.mathheals.meajuda.model.Comment;
import com.mathheals.meajuda.model.Topic;
import com.mathheals.meajuda.view.MainActivity;
import com.mathheals.meajuda.view.topics.TopicView;

import java.util.List;

/**
 * Created by izabela on 03/10/16.
 */
public class CommentsListAdapter extends RecyclerView.Adapter<CommentsListAdapter.ViewHolder> {

    private List<Comment> data;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView description;
        public TextView author;

        public ViewHolder(CardView card) {
            super(card);
            this.description = (TextView) card.findViewById(R.id.description);
            this.author = (TextView) card.findViewById(R.id.author);
            card.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //Gets the selected topic to be open on the list
            /*Comment selectedItem = data.get(this.getAdapterPosition());

            int idTopic = selectedItem.getIdTopic();

            Bundle args = new Bundle();
            args.putInt("idTopic", idTopic);

            TopicView topicView = new TopicView();
            topicView.setArguments(args);

            openFragment(topicView);*/
        }
    }

    private void openFragment(Fragment fragmentToBeOpen){
        MainActivity mainActivity = (MainActivity)context;

        android.support.v4.app.FragmentTransaction fragmentTransaction = mainActivity.
                getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.layout_main, fragmentToBeOpen);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public CommentsListAdapter(Context context, List<Comment> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public CommentsListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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
        holder.author.setText(rowData.getIdParent());
    }

    @Override
    public int getItemCount(){
        return data.size();
    }

}
