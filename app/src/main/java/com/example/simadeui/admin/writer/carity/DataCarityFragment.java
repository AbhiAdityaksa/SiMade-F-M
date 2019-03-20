package com.example.simadeui.admin.writer.carity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.simadeui.R;
import com.example.simadeui.admin.writer.carity.listdetail.ListDataCarityActivity;

import java.util.List;

import Api.ApiClient;
import Model.SumCarityResponse;

public class DataCarityFragment extends Fragment implements DataCarityAdapter.OnClickListener, DataCarityView {

    private List<SumCarityResponse> sumCarityResponseList;
    private RecyclerView recyclerView;
    private DataCarityAdapter adapter;
    private DataCarityPresenter presenter;
    private SumCarityResponse sumCarityResponse;
    private ProgressDialog progressDialog;
    private TextView textView;

    public DataCarityFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data_sumbangan_admin, container, false);
        recyclerView = view.findViewById(R.id.rv_data_sumbangan_admin);
        textView = view.findViewById(R.id.tv_data_sumbangan_admin_kosong);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Now Loading...");

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        presenter = new DataCarityPresenter(this, ApiClient.getService(getContext()));
        presenter.showSumCarity();
    }

    @Override
    public void onClick(int position) {
        sumCarityResponse = sumCarityResponseList.get(position);
        Bundle bundle = new Bundle();
        bundle.putParcelable(ListDataCarityActivity.KEY_CARITY, sumCarityResponse);
        Intent intent = new Intent(getContext(), ListDataCarityActivity.class);
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
    public void onSuccess(List<SumCarityResponse> sumCarityResponses) {
        if (sumCarityResponses.isEmpty()){
            textView.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }else {
            textView.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            this.sumCarityResponseList = sumCarityResponses;
            adapter = new DataCarityAdapter(getContext(), sumCarityResponseList);
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
        Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
    }
}
