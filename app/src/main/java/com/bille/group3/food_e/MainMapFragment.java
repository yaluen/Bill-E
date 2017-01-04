package com.bille.group3.food_e;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Alan on 2017/01/01.
 *
 * Will (for now) handle both the List display and Map display.
 */

public class MainMapFragment extends Fragment{

    private ArrayList<SharedFood> sharedFoods = new ArrayList<>();

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


        ArrayAdapter<SharedFood> adapter = new SharedFoodArrayAdapter(this.getContext(), 0, sharedFoods);

        ListView listView = (ListView) getView().findViewById(R.id.mainFoodListView);
        listView.setAdapter(adapter);

        return inflater.inflate(R.layout.fragment_main_map, container, false);
    }
}
