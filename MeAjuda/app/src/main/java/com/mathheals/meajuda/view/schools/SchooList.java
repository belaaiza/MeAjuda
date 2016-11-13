package com.mathheals.meajuda.view.schools;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mathheals.meajuda.R;
import com.mathheals.meajuda.model.Topic;
import com.mathheals.meajuda.view.CardListAdapter;
import com.mathheals.meajuda.view.SearchActivity;

import java.util.List;

public class SchooList extends Fragment implements View.OnClickListener {

    private List<Topic> data;

    public SchooList(List<Topic> data){
        this.data = data;
    }

    public SchooList(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View topicListView = inflater.inflate(R.layout.fragment_topic_list, container, false);

        RecyclerView recyclerView = (RecyclerView) topicListView.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new CardListAdapter(data, (AppCompatActivity) this.getActivity(),
                getContext()));

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
