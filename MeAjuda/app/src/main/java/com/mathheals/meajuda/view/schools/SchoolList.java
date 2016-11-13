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
import com.mathheals.meajuda.model.School;
import com.mathheals.meajuda.model.Topic;
import com.mathheals.meajuda.view.topics.TopicListAdapter;

import java.util.List;

public class SchoolList extends Fragment {

    private List<School> data;
    private SchoolListAdapter schoolListAdapter;

    public SchoolList(List<School> data){
        this.data = data;
    }

    public SchoolList(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View topicListView = inflater.inflate(R.layout.fragment_topic_list, container, false);

        RecyclerView recyclerView = (RecyclerView) topicListView.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        schoolListAdapter = new SchoolListAdapter(data, (AppCompatActivity) this.getActivity(),
                getContext());
        recyclerView.setAdapter(schoolListAdapter);

        return topicListView;
    }

    public SchoolListAdapter getAdapater(){
        return this.schoolListAdapter;
    }

}
