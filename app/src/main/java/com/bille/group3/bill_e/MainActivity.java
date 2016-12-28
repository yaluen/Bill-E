package com.bille.group3.bill_e;

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
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private Toolbar toolbar;
    private TabLayout mTabs;
    private ViewPager mViewPager;
    private int pagerIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initActionBar();


//        mViewPager = (ViewPager) findViewById(R.id.pager);
        //setupViewPager(mViewPager);
        setupTabLayout();

    }

    private void initActionBar(){
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#AAFF00")));
        actionBar.setStackedBackgroundDrawable(new ColorDrawable(Color.parseColor("#AAFF00")));
        actionBar.setTitle(Html.fromHtml("<font color='#434343'> Bill-E </font>"));

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
        mTabs = (android.support.design.widget.TabLayout) findViewById(R.id.tabLayout);
        mTabs.addTab(mTabs.newTab().setText("User Pattern"));
        mTabs.addTab(mTabs.newTab().setText("Gateway"));
        mTabs.addTab(mTabs.newTab().setText("Group"));


        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setAdapter(new SamplePagerAdapter());
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabs));
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {
            }

            @Override
            public void onPageSelected(int pos) {
                //Toast.makeText(ListActivity.this, "d"+pos, Toast.LENGTH_SHORT).show();
                pagerIndex = pos;
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });

        mTabs.setupWithViewPager(mViewPager);

        pagerIndex = 0;
//        list_view = new ListView[3];
//        listItemAdapter = new CustomAdapter[3];
    }

    private void setupTabLayout() {
        mTabs = (TabLayout) findViewById(R.id.tabLayout);
        mTabs.setTabGravity(TabLayout.GRAVITY_FILL);
        mTabs.setupWithViewPager(mViewPager);
        mTabs.addTab(mTabs.newTab());
        mTabs.addTab(mTabs.newTab());
        mTabs.addTab(mTabs.newTab());
        mTabs.addTab(mTabs.newTab());

        mTabs.getTabAt(0).setIcon(R.drawable.home_tab_btn);
        mTabs.getTabAt(1).setIcon(R.drawable.food_tab_btn);
        mTabs.getTabAt(2).setIcon(R.drawable.receipt_tab_btn);
        mTabs.getTabAt(3).setIcon(R.drawable.scan_tab_btn);
    }




    private class SamplePagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return o == view;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "User Pattern";
                case 1:
                    return "Gateway";
                case 2:
                    return "Group";
                default:
                    return "User Pattern";
            }
            //return "Item " + (position + 1);
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //Toast.makeText(ListActivity.this, "instantiateItem", Toast.LENGTH_SHORT).show();

//            View view = getLayoutInflater().inflate(R.layout.pager_item, container, false);
//            container.addView(view);
//
//
//            list_view[position] = (ListView) view.findViewById(R.id.list);
//            list_view[position].setOnScrollListener(new AbsListView.OnScrollListener() {
//                @Override
//                public void onScrollStateChanged(AbsListView view, int scrollState) {
//                }
//
//                @Override
//                public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//                    //Log.v("SCROLL", ""+firstVisibleItem);
//                    if ( (firstVisibleItem == 0 && lastVisible == 1) || (firstVisibleItem == 0 && lastVisible == 0) ) {
//                        mSwipeRefreshLayout.setEnabled(true);
//                    } else {
//                        mSwipeRefreshLayout.setEnabled(false);
//                    }
//                    lastVisible = firstVisibleItem;
//                }
//            });
//
//            showGroup(view, position);
//            return view;
            return null;
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

    }
}
