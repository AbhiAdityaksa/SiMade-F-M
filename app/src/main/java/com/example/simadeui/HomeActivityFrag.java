package com.example.simadeui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.List;

import Adapter.RecyclerNewsAdapter;
import Api.ApiClient;
import Model.VillageInfoResponse;
import utama.UtamaAdapter;
import utama.UtamaPresenter;
import utama.UtamaView;

public class HomeActivityFrag extends Fragment implements UtamaAdapter.OnClickListener, UtamaView {

    private RecyclerView recyclerNews;
    private List<VillageInfoResponse> villageInfoResponseList;
    private VillageInfoResponse villageInfoResponse;
    private UtamaAdapter adapter;
    private UtamaPresenter presenter;
    private ProgressDialog progressDialog;
    private ViewFlipper vf;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.activity_home_frag, container, false);
        recyclerNews = v.findViewById(R.id.recycler_news);
        vf = v.findViewById(R.id.vf_home);
        setFlipperImage();

/*
        news.add("Aliquam quis faucibus tellus, ac ultrices eros. Nulla facilisi. Praesent aliquet dui eros, id ullamcorper odio ultricies nec. Phasellus in nibh molestie, luctus turpis eu, cursus felis. Phasellus viverra nisi felis, vitae elementum ex ornare sit amet. Integer ut arcu elit. Suspendisse urna purus, hendrerit vel mi ac, dignissim rutrum orci. Nullam sodales convallis sem in ullamcorper. In eleifend lacus non tincidunt porta.");
        news.add("Aliquam quis faucibus tellus, ac ultrices eros. Nulla facilisi. Praesent aliquet dui eros, id ullamcorper odio ultricies nec. Phasellus in nibh molestie, luctus turpis eu, cursus felis. Phasellus viverra nisi felis, vitae elementum ex ornare sit amet. Integer ut arcu elit. Suspendisse urna purus, hendrerit vel mi ac, dignissim rutrum orci. Nullam sodales convallis sem in ullamcorper. In eleifend lacus non tincidunt porta.");
        news.add("Aliquam quis faucibus tellus, ac ultrices eros. Nulla facilisi. Praesent aliquet dui eros, id ullamcorper odio ultricies nec. Phasellus in nibh molestie, luctus turpis eu, cursus felis. Phasellus viverra nisi felis, vitae elementum ex ornare sit amet. Integer ut arcu elit. Suspendisse urna purus, hendrerit vel mi ac, dignissim rutrum orci. Nullam sodales convallis sem in ullamcorper. In eleifend lacus non tincidunt porta.");
        setLinearLayout();*/
        return v;

    }

    private void setFlipperImage() {
        int desaCover[] = {R.drawable.iv_alert1, R.drawable.iv_alert2};
        for (int aDesa_cover : desaCover){
            animFlipper(aDesa_cover);
        }
    }

    private void animFlipper(int img) {
        ImageView imageView = new ImageView(getContext());
        imageView.setBackgroundResource(img);

        vf.addView(imageView);
        vf.setFlipInterval(3500);
        vf.setAutoStart(true);

        vf.setInAnimation(getContext(), android.R.anim.slide_in_left);
        vf.setOutAnimation(getContext(), android.R.anim.slide_out_right);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Now Loading...");

        presenter = new UtamaPresenter(this, ApiClient.getService(getContext()));
        presenter.showNews();
    }

    @Override
    public void onClick(int position) {
        villageInfoResponse = villageInfoResponseList.get(position);
        Bundle bundle = new Bundle();
        bundle.putParcelable(DetPostActivity.KEY_INFO, villageInfoResponse);
        Intent intent = new Intent(getContext(), DetPostActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void showLoading() {
        progressDialog.show();
    }

    @Override
    public void hideLoading() {
        progressDialog.hide();
    }

    @Override
    public void onSuccess(List<VillageInfoResponse> villageInfoResponseList) {
        this.villageInfoResponseList = villageInfoResponseList;
        adapter = new UtamaAdapter(getContext(), villageInfoResponseList);
        adapter.setOnClickListener(this);
        recyclerNews.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerNews.setAdapter(adapter);
    }

    @Override
    public void onError() {
        Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(Throwable t) {
        Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
    }

/*
    public void setLinearLayout(){
        recyclerNewsAdapter = new RecyclerNewsAdapter(getActivity(),news);
        LinearLayoutManager linearLayout = new LinearLayoutManager(getContext());
        recyclerNews.setAdapter(recyclerNewsAdapter);
        recyclerNews.setLayoutManager(linearLayout);
    }
*/

}
