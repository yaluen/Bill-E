package com.bille.group3.food_e;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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
 * Created by hduser on 1/5/17.
 */

public class MainListFragment extends Fragment implements TextView.OnEditorActionListener{

    private ArrayList<SharedFood> sharedFoods = new ArrayList<>();
    private ArrayList<SharedFood> displayedItems = new ArrayList<>();
    private ListView mListView;

    public static MainFragment newInstance()
    {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        sharedFoods.add(new SharedFood("Apples for sale 30% off", new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime(),
                new GregorianCalendar(2014, Calendar.FEBRUARY, 23).getTime(), "Shilin Carrefour", "shared_apples"));

        sharedFoods.add(new SharedFood("Oranges for sale 30% off", new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime(),
                new GregorianCalendar(2014, Calendar.FEBRUARY, 23).getTime(), "Shilin Carrefour", "shared_oranges"));

        sharedFoods.add(new SharedFood("Peaches for sale 30% off", new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime(),
                new GregorianCalendar(2014, Calendar.FEBRUARY, 23).getTime(), "Shilin Carrefour", "shared_peaches"));

        sharedFoods.add(new SharedFood("Free pizza for everyone", new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime(),
                new GregorianCalendar(2014, Calendar.FEBRUARY, 18).getTime(), "NTU CSIE Building", "shared_pizza"));

        View v = inflater.inflate(R.layout.fragment_main_list, container, false);

        displayedItems.clear();
        displayedItems.addAll(sharedFoods);

        ArrayAdapter<SharedFood> adapter = new SharedFoodArrayAdapter(this.getContext(), 0, displayedItems);

        mListView = (ListView) v.findViewById(R.id.mainFoodListView);
        mListView.setAdapter(adapter);

        EditText edit = (EditText) v.findViewById(R.id.mainSearchBar);
        edit.setOnEditorActionListener(this);

        return v;
    }


    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if(i == EditorInfo.IME_ACTION_DONE)
        {
            final View view = textView.getRootView();
            final CharSequence search = textView.getText();
            //Basic search
            final List<SharedFood> foundElements = new LinkedList();
            for(SharedFood b:sharedFoods)
            {
                if(b.getSearchString().toLowerCase().contains(search.toString().toLowerCase()))
                {
                    foundElements.add(b);
                }
            }
            if(foundElements.size() != 0)
            {
                displayedItems.clear();
                displayedItems.addAll(foundElements);
//                Collections.sort(foundElements,Bill.getDateComperator());

                ((BaseAdapter) mListView.getAdapter()).notifyDataSetChanged();
            }
            else
            {
                Toast.makeText(view.getContext(), R.string.receipt_search_fail,Toast.LENGTH_LONG);
            }
            return false;
        }
        return true;
    }
}
