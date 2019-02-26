package com.example.simadeui;

import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
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
//    List<News> newsList =new ArrayList<>();
    ArrayList<String> news = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        v = inflater.inflate(R.layout.activity_home_frag, container, false);
        recyclerNews = v.findViewById(R.id.recycler_news);


        news.add("Aliquam quis faucibus tellus, ac ultrices eros. Nulla facilisi. Praesent aliquet dui eros, id ullamcorper odio ultricies nec. Phasellus in nibh molestie, luctus turpis eu, cursus felis. Phasellus viverra nisi felis, vitae elementum ex ornare sit amet. Integer ut arcu elit. Suspendisse urna purus, hendrerit vel mi ac, dignissim rutrum orci. Nullam sodales convallis sem in ullamcorper. In eleifend lacus non tincidunt porta.");
        setLinearLayout();
        return v;

    }

    public void setLinearLayout(){
        recyclerNewsAdapter = new RecyclerNewsAdapter(getActivity(),news);
        LinearLayoutManager linearLayout = new LinearLayoutManager(getContext());
        recyclerNews.setAdapter(recyclerNewsAdapter);
        recyclerNews.setLayoutManager(linearLayout);
    }

}
