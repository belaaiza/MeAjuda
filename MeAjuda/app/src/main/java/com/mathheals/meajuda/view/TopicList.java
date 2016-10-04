package com.mathheals.meajuda.view;

import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by izabela on 03/10/16.
 */
public class TopicList extends Fragment {

    public TopicList(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View topicListView = inflater.inflate(R.layout.fragment_topic_list, container, false);

        RecyclerView recyclerView = (RecyclerView) topicListView.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<Topic> data = new ArrayList<>();
        Topic topic1 = new Topic(1, "Testando", "Testando a descrição da parada");
        Topic topic2 = new Topic(1, "Testando", "Testando a descrição da parada");

        data.add(topic1);
        data.add(topic2);

        recyclerView.setAdapter(new CardListAdapter(this.getContext(), data));

        return topicListView;
    }

}
