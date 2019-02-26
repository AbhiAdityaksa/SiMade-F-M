package com.example.simadeui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ProfileActivityFrag extends Fragment{
    View v;
    TextView tv_det_profile;
    TextView tv_chang_pass;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        v  = inflater.inflate(R.layout.activity_profile_frag, container, false);

        tv_det_profile = v.findViewById(R.id.tv_det_profile);
        tv_chang_pass = v.findViewById(R.id.tv_change_pass);

        tv_det_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),DetProfileActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        tv_chang_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),GantiPassActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        return v;

    }

}
