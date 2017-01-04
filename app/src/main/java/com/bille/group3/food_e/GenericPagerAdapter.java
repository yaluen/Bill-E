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
    public Fragment getItem(int position) {
        MainMapFragment tab1 = new MainMapFragment();
        ReceiptSearchFragment tab3= ReceiptSearchFragment.newInstance();
        switch (position) {
            case 0:
                return tab1;
//            case 1:
//                TabFragment2 tab2 = new TabFragment2();
//                return tab2;
            case 2:
                return tab3;
//            case 3:
//                TabFragment3 tab3 = new TabFragment3();
//                return tab3;
            default:
                return tab1;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
