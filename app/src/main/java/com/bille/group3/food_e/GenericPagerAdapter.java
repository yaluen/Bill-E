package com.bille.group3.food_e;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.ActionBar;
import android.text.Html;

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
//                actionBar.setTitle(Html.fromHtml("<font color='#434343'> Food-E </font>"));
                return MainMapFragment.newInstance();
            case 1:
//                actionBar.setTitle(Html.fromHtml("<font color='#434343'> Share Food </font>"));
                return  ShareFoodListFragment.newInstance();
            case 2:
//                actionBar.setTitle(Html.fromHtml("<font color='#434343'> View Receipts </font>"));
                return ReceiptSearchFragment.newInstance();
            case 3:
//                actionBar.setTitle(Html.fromHtml("<font color='#434343'> E-Receipt Scan </font>"));
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
