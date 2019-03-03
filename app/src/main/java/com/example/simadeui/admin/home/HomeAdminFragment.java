package com.example.simadeui.admin.home;

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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.simadeui.R;
import com.example.simadeui.admin.writer.AdminWriterActivity;

import java.util.List;

import Api.ApiClient;
import Helper.PreferenceHelper;
import Model.HistoryResponse;
import Model.VillagerWorkerAdmin;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeAdminFragment extends Fragment implements HistoryView {

    private TextView tvName, tvWarga, tvWargaKerja, tvWargaBelum;
    private Button btnWrite;
    private PreferenceHelper preferenceHelper;
    private VillagerWorkerAdmin villagerWorkerAdmin;
    private ProgressDialog progressDialog;
    private List<HistoryResponse> historyResponseList;
    private RecyclerView recyclerView;
    private HistoryPresenter presenter;
    private HistoryAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_admin, container, false);
        tvName = view.findViewById(R.id.tv_name_admin_home);
        tvWarga = view.findViewById(R.id.tvwarga);
        tvWargaKerja = view.findViewById(R.id.tvkerja);
        tvWargaBelum = view.findViewById(R.id.tvtidakkerja);
        btnWrite = view.findViewById(R.id.btn_admin_writer);
        recyclerView = view.findViewById(R.id.rv_history);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        preferenceHelper = new PreferenceHelper(getContext());
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Now Loading...");
        presenter = new HistoryPresenter(this, ApiClient.getService(getContext()));
        presenter.showHistory();

//        set admin name
        tvName.setText(preferenceHelper.getName());

//        Intent to Admin Write Activity
        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AdminWriterActivity.class);
                startActivity(intent);
            }
        });

//        Set Villager Worker
        showWorked();
    }

    private void showWorked() {
        ApiClient.getService(getContext())
                .villagerWorked()
                .enqueue(new Callback<VillagerWorkerAdmin>() {
                    @Override
                    public void onResponse(Call<VillagerWorkerAdmin> call, Response<VillagerWorkerAdmin> response) {
                        if (response.isSuccessful()){
                            villagerWorkerAdmin = response.body();
                            tvWarga.setText(String.valueOf(villagerWorkerAdmin.getVillager()));
                            tvWargaKerja.setText(String.valueOf(villagerWorkerAdmin.getWorked()));
                            int belum = villagerWorkerAdmin.getVillager() - villagerWorkerAdmin.getWorked();
                            tvWargaBelum.setText(String.valueOf(belum));
                        }else {
                            Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<VillagerWorkerAdmin> call, Throwable t) {
                        Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                    }
                });
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
    public void onSuccess(List<HistoryResponse> historyResponseList) {
        this.historyResponseList = historyResponseList;
        adapter = new HistoryAdapter(getContext(), historyResponseList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
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
