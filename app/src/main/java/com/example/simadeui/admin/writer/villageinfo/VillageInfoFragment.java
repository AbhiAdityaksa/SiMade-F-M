package com.example.simadeui.admin.writer.villageinfo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.simadeui.R;
import com.example.simadeui.admin.writer.villageinfo.addvillageinfo.AddVillageInfoActivity;

import java.util.List;

import Api.ApiClient;
import Model.VillageInfoResponse;

public class VillageInfoFragment extends Fragment implements VillageInfoAdapter.OnClickListener, VillageInfoView {

    private FloatingActionButton btnAdd;
    private List<VillageInfoResponse> villageInfoResponseList;
    private RecyclerView recyclerView;
    private VillageInfoAdapter adapter;
    private VillageInfoPresenter presenter;
    private ProgressDialog progressDialog;
    private TextView tvKosong;

    public VillageInfoFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info_desa_admin, container, false);
        recyclerView = view.findViewById(R.id.rv_info_desa_admin);
        tvKosong = view.findViewById(R.id.tv_info_desa_admin_kosong);
        btnAdd = view.findViewById(R.id.add_info);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Now Loading...");

        presenter = new VillageInfoPresenter(this, ApiClient.getService(getContext()));
        presenter.showInfo();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddVillageInfoActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(int position) {
        VillageInfoResponse villageInfoResponse = villageInfoResponseList.get(position);
        Bundle bundle = new Bundle();
        bundle.putParcelable(DetailVillageInfoActivity.KEY_INFO, villageInfoResponse);
        Intent intent = new Intent(getContext(), DetailVillageInfoActivity.class);
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
        if (villageInfoResponseList.isEmpty()){
            tvKosong.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }else {
            tvKosong.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            this.villageInfoResponseList = villageInfoResponseList;
            adapter = new VillageInfoAdapter(getContext(), villageInfoResponseList);
            adapter.setOnClickListener(this);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void onError() {
        Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(Throwable t) {
        Toast.makeText(getContext(), "Error"+t, Toast.LENGTH_SHORT).show();
    }
}
