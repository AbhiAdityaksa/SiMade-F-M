package Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.simadeui.HomeActivityFrag;
import com.example.simadeui.ProfileActivityFrag;

import userReport.LaporanActivityFrag;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public ViewPagerAdapter(FragmentManager fm, Context mContext) {
        super(fm);
        this.mContext = mContext;
    }

    @Override
    public Fragment getItem(int i) {
        if (i == 0){
            return new HomeActivityFrag();
        }else if (i == 1){
            return new LaporanActivityFrag();
        }else {
            return new ProfileActivityFrag();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
