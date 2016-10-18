package com.mathheals.meajuda.view.topics;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mathheals.meajuda.R;
import com.mathheals.meajuda.model.Topic;
import com.mathheals.meajuda.presenter.TopicPresenter;
import com.mathheals.meajuda.view.CardListAdapter;

import java.util.ArrayList;
import java.util.List;

public class TopicList extends Fragment implements View.OnClickListener {

    public TopicList(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View topicListView = inflater.inflate(R.layout.fragment_topic_list, container, false);

        RecyclerView recyclerView = (RecyclerView) topicListView.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        TopicPresenter topicPresenter = TopicPresenter.getInstance(getContext());


        Bundle args = this.getArguments();

        int idCategory = args.getInt("idCategory", 0);
        Log.d("Category id: ", idCategory + "");

        assert(idCategory != 0);

        List<Topic> data = topicPresenter.getTopicsByCategory(idCategory);

        recyclerView.setAdapter(new CardListAdapter(this.getContext(), data));

        return topicListView;
    }

    @Override
    public void onClick(View view){
        /*if(view.getId() == R.id.newTopic){
            TopicCreation topicCreation = new TopicCreation();
            openFragment(topicCreation);
        }*/
    }

    private void openFragment(Fragment fragmentToBeOpen){
        android.support.v4.app.FragmentTransaction fragmentTransaction =
                getActivity().getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.layout_main, fragmentToBeOpen);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
