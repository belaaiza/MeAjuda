package com.mathheals.meajuda.view.topics;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mathheals.meajuda.R;
import com.mathheals.meajuda.exception.UserException;
import com.mathheals.meajuda.model.Topic;
import com.mathheals.meajuda.presenter.TopicEvaluationPresenter;
import com.mathheals.meajuda.presenter.UserPresenter;
import com.mathheals.meajuda.view.MainActivity;
import com.mathheals.meajuda.view.SearchActivity;

import java.util.List;
import android.support.v7.widget.CardView;

import org.json.JSONException;

public class TopicListAdapter extends RecyclerView.Adapter<TopicListAdapter.ViewHolder> {

    private List<Topic> data;
    private AppCompatActivity currentActivity;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView idTopic;
        public TextView title;
        public TextView description;
        public TextView author;
        private TextView topicEvaluation;
        private ImageView profilePhoto;

        public ViewHolder(CardView card) {
            super(card);

            this.idTopic = (TextView) card.findViewById(R.id.idTopic);
            this.title = (TextView) card.findViewById(R.id.title);
            this.description = (TextView) card.findViewById(R.id.description);
            this.author = (TextView) card.findViewById(R.id.author);
            this.topicEvaluation = (TextView) card.findViewById(R.id.topicEvaluation);
            this.profilePhoto = (ImageView) card.findViewById(R.id.user_profile_photo);

            card.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //Gets the selected topic to be open on the list
            Topic selectedItem = data.get(this.getAdapterPosition());

            int idTopic = selectedItem.getIdTopic();

            Bundle args = new Bundle();
            args.putInt("idTopic", idTopic);

            TopicView topicView = new TopicView();
            topicView.setArguments(args);

            if(currentActivity instanceof MainActivity){
                openFragment(topicView);
            }
            else if(currentActivity instanceof SearchActivity){
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("whichFragment", "topic");
                intent.putExtra("idTopic", idTopic);
                context.startActivity(intent);
            }
        }
    }

    private void openFragment(Fragment fragmentToBeOpen){
        android.support.v4.app.FragmentTransaction fragmentTransaction = currentActivity.
                getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.layout_main, fragmentToBeOpen, "TopicViewFragment");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public TopicListAdapter(List<Topic> data, AppCompatActivity activity, Context context) {
        this.data = data;
        this.currentActivity = activity;
        this.context = context;
    }

    @Override
    public TopicListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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
        holder.author.setText(rowData.getNameOwner());

        TopicEvaluationPresenter topicEvaluationPresenter = TopicEvaluationPresenter.
                getInstance(context);

        Integer idTopic = rowData.getIdTopic();

        try {
            Integer topicEvaluationValue = topicEvaluationPresenter.getTopicEvaluation(idTopic);

            holder.topicEvaluation.setText(topicEvaluationValue + "");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        
        UserPresenter userPresenter = UserPresenter.getInstance(context);
        try{
            Drawable userPhoto = userPresenter.getClassificationIcon(userPresenter
            .getUserClassification(rowData.getIdOwner()));
            holder.profilePhoto.setImageDrawable(userPhoto);

        } catch(UserException e){
            e.printStackTrace();
        } catch(JSONException e){
            e.printStackTrace();
        }
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
