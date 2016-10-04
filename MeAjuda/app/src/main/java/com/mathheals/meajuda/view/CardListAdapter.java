package com.mathheals.meajuda.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mathheals.meajuda.R;
import com.mathheals.meajuda.model.Topic;

import java.util.List;
import android.support.v7.widget.CardView;

/**
 * Created by izabela on 03/10/16.
 */
public class CardListAdapter extends RecyclerView.Adapter<CardListAdapter.ViewHolder> {

    private List<Topic> data;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title;
        public TextView description;

        public ViewHolder(CardView card) {
            super(card);
            this.title = (TextView) card.findViewById(R.id.title);
            this.description = (TextView) card.findViewById(R.id.description);
            card.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //Gets the selected topic to be open on the list
            Topic selectedItem = data.get(this.getAdapterPosition());
        }
    }

    public CardListAdapter(Context context, List<Topic> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public CardListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CardView view = (CardView) inflater.inflate(R.layout.card_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onAttachedToRecyclerView(final RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Topic rowData = this.data.get(position);
        holder.title.setText(rowData.getTitle());
        holder.description.setText(rowData.getDescription());
    }

    @Override
    public int getItemCount(){
        return data.size();
    }

}
