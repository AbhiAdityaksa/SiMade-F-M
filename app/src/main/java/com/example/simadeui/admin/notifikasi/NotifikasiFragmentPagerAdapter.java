package com.example.simadeui.admin.notifikasi;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.simadeui.R;
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
        } else {
            return new UserQuestionFragment();
        }
//        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return mycontext.getString(R.string.title_verif_user_admin);
            case 1:
                return mycontext.getString(R.string.title_user_report_admin);
            case 2:
                return mycontext.getString(R.string.title_user_question_admin);
            default:
                    return null;
        }
    }
}
