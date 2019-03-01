package com.example.simadeui.admin.home;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.simadeui.R;
import com.example.simadeui.admin.writer.AdminWriterActivity;

import Api.ApiClient;
import Helper.PreferenceHelper;
import Model.VillagerWorkerAdmin;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeAdminFragment extends Fragment {

    private TextView tvName, tvWarga, tvWargaKerja, tvWargaBelum;
    private Button btnWrite;
    private PreferenceHelper preferenceHelper;
    private VillagerWorkerAdmin villagerWorkerAdmin;
//    private ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_admin, container, false);
        tvName = view.findViewById(R.id.tv_name_admin_home);
        tvWarga = view.findViewById(R.id.tvwarga);
        tvWargaKerja = view.findViewById(R.id.tvkerja);
        tvWargaBelum = view.findViewById(R.id.tvtidakkerja);
        btnWrite = view.findViewById(R.id.btn_admin_writer);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        preferenceHelper = new PreferenceHelper(getContext());

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
}
