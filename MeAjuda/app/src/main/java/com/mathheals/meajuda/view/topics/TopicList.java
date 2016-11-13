package com.mathheals.meajuda.view.topics;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mathheals.meajuda.R;
import com.mathheals.meajuda.model.Topic;
import com.mathheals.meajuda.presenter.TopicPresenter;
import com.mathheals.meajuda.view.CardListAdapter;
import com.mathheals.meajuda.view.MainActivity;
import com.mathheals.meajuda.view.SearchActivity;
import com.sun.jna.platform.win32.WinNT;

import java.util.List;

public class TopicList extends Fragment {

    private List<Topic> data;
    private CardListAdapter cardListAdapter;

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
        cardListAdapter = new CardListAdapter(data, (AppCompatActivity) this.getActivity(),
                getContext());
        recyclerView.setAdapter(cardListAdapter);

        return topicListView;
    }

    public CardListAdapter getAdapater(){
        return this.cardListAdapter;
    }

}
