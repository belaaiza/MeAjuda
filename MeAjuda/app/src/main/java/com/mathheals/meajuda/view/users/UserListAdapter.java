package com.mathheals.meajuda.view.users;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mathheals.meajuda.R;
import com.mathheals.meajuda.model.Topic;
import com.mathheals.meajuda.model.TopicEvaluation;
import com.mathheals.meajuda.model.User;
import com.mathheals.meajuda.presenter.UserPresenter;
import com.mathheals.meajuda.view.MainActivity;
import com.mathheals.meajuda.view.SearchActivity;
import com.mathheals.meajuda.view.topics.TopicView;
import com.sun.jna.platform.win32.Netapi32Util;

import org.w3c.dom.Text;

import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {

    private List<User> data;
    private AppCompatActivity currentActivity;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name;
        public ImageView photo;
        public TextView classification;
        public TextView username;
        public TextView rating;

        public ViewHolder(CardView card) {
            super(card);

            this.name = (TextView) card.findViewById(R.id.name);
            this.photo = (ImageView) card.findViewById(R.id.photo);
            this.classification = (TextView) card.findViewById(R.id.classification);
            this.username = (TextView) card.findViewById(R.id.username);
            this.rating = (TextView) card.findViewById(R.id.rating);
            card.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //Gets the selected topic to be open on the list
            User selectedItem = data.get(this.getAdapterPosition());

            ViewProfile viewProfile = new ViewProfile();

            /*
            if(currentActivity instanceof MainActivity){
                openFragment(topicView);
            }
            else if(currentActivity instanceof SearchActivity){
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("whichFragment", "topic");
                intent.putExtra("idTopic", idTopic);
                context.startActivity(intent);
            }*/
        }
    }

    private void openFragment(Fragment fragmentToBeOpen){
        android.support.v4.app.FragmentTransaction fragmentTransaction = currentActivity.
                getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.layout_main, fragmentToBeOpen, "TopicViewFragment");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public UserListAdapter(List<User> data, AppCompatActivity activity, Context context) {
        this.data = data;
        this.currentActivity = activity;
        this.context = context;
    }

    @Override
    public UserListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CardView view = (CardView) inflater.inflate(R.layout.card_list_item_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onAttachedToRecyclerView(final RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User rowData = this.data.get(position);

        holder.name.setText(rowData.getFirstName()+" "+rowData.getLastName());
        holder.username.setText(rowData.getUsername());
        //TODO setar rating do usuario
        holder.rating.setText("10");
        Integer classification = rowData.getIdClassification();

        UserPresenter userPresenter = UserPresenter.getInstance(context);
        Drawable userPhoto = userPresenter.getClassificationIcon(classification);
        holder.photo.setImageDrawable(userPhoto);
    }

    @Override
    public int getItemCount(){
        return data.size();
    }

    public void updateList(List data) {
        this.data = data;
        notifyDataSetChanged();
    }
}
