package Auth;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import changePassword.GantiPassActivity;
import com.example.simadeui.R;

import Helper.PreferenceHelper;

public class ProfileActivityFrag extends Fragment{
    View v;
    TextView tv_det_profile;
    TextView tv_chang_pass;
    TextView tv_name;
    TextView tv_work;

    private Button btnLogout;

    String name;
    String work;

    PreferenceHelper preferenceHelper;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        v  = inflater.inflate(R.layout.activity_profile_frag, container, false);

        tv_det_profile = v.findViewById(R.id.tv_det_profile);
        tv_chang_pass = v.findViewById(R.id.tv_change_pass);
        tv_name = v.findViewById(R.id.tv_nama);
        tv_work = v.findViewById(R.id.tv_pekerjaan);
        btnLogout = v.findViewById(R.id.bt_logout);

        preferenceHelper = new PreferenceHelper(getActivity());

        name = preferenceHelper.getName();
        work = preferenceHelper.getWork();
        tv_name.setText(name);
        tv_work.setText(work);


        tv_det_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DetProfileActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        tv_chang_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), GantiPassActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                preferenceHelper.setLogout();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return v;

    }

}
