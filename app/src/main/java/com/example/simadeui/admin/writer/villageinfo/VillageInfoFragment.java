package com.example.simadeui.admin.writer.villageinfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.simadeui.R;
import com.example.simadeui.admin.writer.villageinfo.addvillageinfo.AddVillageInfoActivity;

public class VillageInfoFragment extends Fragment {

    private FloatingActionButton btnAdd;

    public VillageInfoFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info_desa_admin, container, false);
        btnAdd = view.findViewById(R.id.add_info);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddVillageInfoActivity.class);
                startActivity(intent);
            }
        });
    }
}
