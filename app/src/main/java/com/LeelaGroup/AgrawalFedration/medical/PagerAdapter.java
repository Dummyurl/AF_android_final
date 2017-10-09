package com.LeelaGroup.AgrawalFedration.medical;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.LeelaGroup.AgrawalFedration.Medical_Pojos.Medical;

/**
 * Created by Adwait on 11/05/2017.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                Medical_Fetch tab1 = new Medical_Fetch();
                 return tab1;
            case 1:
                Doctor_fetch  tab2 = new Doctor_fetch();
                return tab2;
            case 2:
                 Hospital_fetch tab3 = new Hospital_fetch();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount()
    {
        return mNumOfTabs;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Medical";
            case 1:
                return "Doctor";
            case 2:
                return "Hospital";
        }
        return null;
    }

}
