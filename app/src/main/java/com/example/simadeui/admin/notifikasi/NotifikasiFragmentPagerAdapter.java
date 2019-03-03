package com.example.simadeui.admin.notifikasi;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.simadeui.admin.notifikasi.carityverification.CarityVerificationFragment;
import com.example.simadeui.admin.notifikasi.userquestion.UserQuestionFragment;
import com.example.simadeui.admin.notifikasi.userreport.UserRefortFragment;
import com.example.simadeui.admin.notifikasi.verifuser.VerifUserFragment;

public class NotifikasiFragmentPagerAdapter extends FragmentPagerAdapter {

    private Context mycontext;

    public NotifikasiFragmentPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mycontext = context;
    }

    @Override
    public Fragment getItem(int i) {
        if (i == 0){
            return new VerifUserFragment();
        } else if (i == 1){
            return new UserRefortFragment();
        } else if (i == 2){
            return new UserQuestionFragment();
        }else {
            return new CarityVerificationFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

}
