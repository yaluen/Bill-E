package com.bille.group3.food_e;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.TabLayout;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;


public class MainActivity extends AppCompatActivity {
    private TabLayout mTabs;
    private ViewPager mViewPager;
    public ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTabs = (TabLayout) findViewById(R.id.tabLayout);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);

        initActionBar();
        initViewPager();
        initTabLayout();
    }

    private void initActionBar(){
        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#AAFF00")));
        actionBar.setStackedBackgroundDrawable(new ColorDrawable(Color.parseColor("#AAFF00")));
        actionBar.setTitle(Html.fromHtml("<font color='#434343'> Food-E </font>"));

//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        // Set an OnMenuItemClickListener to handle menu item clicks
//        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                // Handle the menu item
//                return true;
//            }
//        });
//        // Inflate a menu to be displayed in the toolbar
//        toolbar.inflateMenu(R.menu.menu_main);
//        setSupportActionBar(toolbar);
//
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeButtonEnabled(true);

//        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open, R.string.close) {
//            @Override
//            public void onDrawerOpened(View drawerView) {
//                super.onDrawerOpened(drawerView);
//
//            }
//            @Override
//            public void onDrawerClosed(View drawerView) {
//                super.onDrawerClosed(drawerView);
//            }
//        };
//        mActionBarDrawerToggle.syncState();
//        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);
    }

    private void initViewPager(){
        final PagerAdapter adapter = new GenericPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            // This method will be invoked when a new page becomes selected.
            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    actionBar.setTitle(Html.fromHtml("<font color='#434343'> Food-E </font>"));
                } else if (position == 1) {
                    actionBar.setTitle(Html.fromHtml("<font color='#434343'> Share Food </font>"));
                } else if (position == 2) {
                    actionBar.setTitle(Html.fromHtml("<font color='#434343'> View Receipts </font>"));
                } else if (position == 3) {
                    actionBar.setTitle(Html.fromHtml("<font color='#434343'> E-Receipt Scan </font>"));
                }

            }

            // This method will be invoked when the current page is scrolled
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // Code goes here
            }

            // Called when the scroll state changes:
            // SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING
            @Override
            public void onPageScrollStateChanged(int state) {
                // Code goes here
            }
        });
    }

    private void initTabLayout() {
        mTabs.setTabGravity(TabLayout.GRAVITY_FILL);
        mTabs.setupWithViewPager(mViewPager);

        mTabs.getTabAt(0).setIcon(R.drawable.home_tab_btn);
        mTabs.getTabAt(1).setIcon(R.drawable.food_tab_btn);
        mTabs.getTabAt(2).setIcon(R.drawable.receipt_tab_btn);
        mTabs.getTabAt(3).setIcon(R.drawable.scan_tab_btn);
    }
}
