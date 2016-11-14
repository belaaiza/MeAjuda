package com.mathheals.meajuda.view.topics;

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

import java.util.List;

public class TopicList extends Fragment {

    private List<Topic> data;
    private TopicListAdapter topicListAdapter;

    public TopicList(List<Topic> data){
        this.data = data;
    }

    public TopicList(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View topicListView = inflater.inflate(R.layout.fragment_topic_list, container, false);

        RecyclerView recyclerView = (RecyclerView) topicListView.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        topicListAdapter = new TopicListAdapter(data, (AppCompatActivity) this.getActivity(),
                getContext());
        recyclerView.setAdapter(topicListAdapter);

        return topicListView;
    }

    public TopicListAdapter getAdapter(){
        return this.topicListAdapter;
    }

}
