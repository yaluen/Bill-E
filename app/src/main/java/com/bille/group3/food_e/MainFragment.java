package com.bille.group3.food_e;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ToggleButton;

/**
 * Created by Alan on 2017/01/01.
 *
 * Will (for now) handle both the List display and Map display.
 */

public class MainFragment extends Fragment {

    private boolean showList = true;

    public static MainFragment newInstance()
    {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

//        showList = false;
        View view = inflater.inflate(R.layout.fragment_main_view, container, false);
        FragmentManager fm = getChildFragmentManager();
        Log.d("Main", "OnCreate " + showList);
        ToggleButton btn = (ToggleButton) view.findViewById(R.id.mainListToggleButton);

        btn.setChecked(showList);

        if (showList) {
            Log.d("Main", "ShowList is True");
            btn.setChecked(true);
            Fragment fragment = new MainListFragment();

            FragmentTransaction transaction = fm.beginTransaction();
            transaction.replace(R.id.mainContentFragment, fragment);
            transaction.commit();
        } else {
            btn.setChecked(false);
            Fragment fragment = new MainMapActivity();

            FragmentTransaction transaction = fm.beginTransaction();
            transaction.replace(R.id.mainContentFragment, fragment);
            transaction.commit();
        }

//        Fragment listFragment = new MainListFragment();
//        FragmentManager fm = getChildFragmentManager();
//        FragmentTransaction transaction = fm.beginTransaction();
//        transaction.replace(R.id.mainContentFragment, listFragment);
//        transaction.commit();
//        Fragment mapFragment = new MainMapActivity();
//        FragmentManager fm = getChildFragmentManager();


        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                FragmentManager fm = getChildFragmentManager();
                if (showList) {
                    Fragment fragment = new MainMapActivity();

                    FragmentTransaction transaction = fm.beginTransaction();
                    transaction.replace(R.id.mainContentFragment, fragment);
                    transaction.commit();

                    showList = false;
                } else {
                    Fragment fragment = new MainListFragment();

                    FragmentTransaction transaction = fm.beginTransaction();
                    transaction.replace(R.id.mainContentFragment, fragment);
                    transaction.commit();

                    showList = true;
                }

            }
        });

        return view;
    }
}
