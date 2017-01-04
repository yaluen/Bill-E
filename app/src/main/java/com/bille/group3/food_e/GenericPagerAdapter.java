package com.bille.group3.food_e;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Loh-Shilin on 2017/01/01.
 */

public class GenericPagerAdapter extends FragmentPagerAdapter {
    final private int mNumOfTabs = 4;

    public GenericPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position)
    {
        switch (position) {
            case 0:
                return MainMapFragment.newInstance();
            case 1:
                return  ShareFoodListFragment.newInstance();
            case 2:
                return ReceiptSearchFragment.newInstance();
            case 3:
                return BarcodeFragment.newInstance();
            default:
                return MainMapFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
