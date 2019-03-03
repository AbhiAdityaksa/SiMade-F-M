package com.example.simadeui.admin.writer;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.simadeui.admin.writer.carity.DataCarityFragment;
import com.example.simadeui.admin.writer.category.CategoryFragment;
import com.example.simadeui.admin.writer.reportcategory.ReportCategoryFragment;
import com.example.simadeui.admin.writer.villageinfo.VillageInfoFragment;

public class AdminWriterFragmentPagerAdapter extends FragmentPagerAdapter {

    private Context mycontext;

    public AdminWriterFragmentPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mycontext = context;
    }

    @Override
    public Fragment getItem(int i) {
        if (i == 0){
            return new VillageInfoFragment();
        } else if (i == 1){
            return new ReportCategoryFragment();
        }else if (i == 2){
            return new CategoryFragment();
        }else {
            return new DataCarityFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
