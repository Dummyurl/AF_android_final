package com.LeelaGroup.AgrawalFedration.education;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.LeelaGroup.AgrawalFedration.medical.Hospital_fetch;

/**
 * Created by Admin on 8/16/2017.
 */

public class PagerAdapter_Sc extends FragmentStatePagerAdapter {

    int mNumOfTabs;

    public PagerAdapter_Sc(FragmentManager fm, int mNumOfTabs) {
        super(fm);
        this.mNumOfTabs = mNumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Seed_Scholarship tab1 = new Seed_Scholarship();
                return tab1;
            case 1:
                Education_Loan_Program tab2 = new Education_Loan_Program();
                return tab2;

            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "SEED Scholarship";
            case 1:
                return "ALEP";

        }
        return null;
    }
}
