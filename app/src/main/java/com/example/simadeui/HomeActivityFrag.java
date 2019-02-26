package com.example.simadeui;

import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import Adapter.RecyclerNewsAdapter;
import Auth.AuthView;
import Model.News;
import Model.User;

public class HomeActivityFrag extends Fragment{
    View v;
    RecyclerView recyclerNews;
    RecyclerNewsAdapter recyclerNewsAdapter;
    List<News> newsList =new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        v = inflater.inflate(R.layout.activity_home_frag, container, false);
        recyclerNews = v.findViewById(R.id.recycler_news);
//        setLinearLayout();
        return v;

    }

//    public void setLinearLayout(){
//        recyclerNewsAdapter = new RecyclerNewsAdapter(getActivity(),newsList);
//
//        recyclerNews.setAdapter(recyclerNewsAdapter);
//        recyclerNews.setLayoutManager(recyclerView);
//    }

}
