package com.bille.group3.food_e;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Alan on 2017/01/01.
 *
 * Will (for now) handle both the List display and Map display.
 */

public class MainFragment extends Fragment {
    private ListView mListView;

    private boolean showList;

    public static MainFragment newInstance()
    {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        showList = true;

        View v = inflater.inflate(R.layout.fragment_main_view, container, false);

        Fragment listFragment = new MainListFragment();

        ToggleButton btn = (ToggleButton) v.findViewById(R.id.mainListToggleButton);
        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (showList) {
                    Fragment fragment = new MainListFragment();
                    FragmentManager fm = getChildFragmentManager();

                    FragmentTransaction transaction = fm.beginTransaction();
                    transaction.replace(R.id.mainContentFragment, fragment);
                    transaction.commit();
                    showList = false;
                } else {
                    Fragment fragment = new MapsActivity();
                    FragmentManager fm = getChildFragmentManager();

                    FragmentTransaction transaction = fm.beginTransaction();
                    transaction.replace(R.id.mainContentFragment, fragment);
                    transaction.commit();
                    showList = true;
                }

            }
        });

        return v;
    }
}
