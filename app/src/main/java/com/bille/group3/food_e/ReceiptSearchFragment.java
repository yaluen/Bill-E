package com.bille.group3.food_e;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;
import java.util.Comparator;

/**
 *
 */
public class ReceiptSearchFragment extends Fragment
{
    private Bill[] testdata;
    private final String[] SHOPS = {"7-11","7-11","Family Markt","Carrefour","7-11",
            "Family Markt","Burger King","Gap","C%A","Appel Store",};
    private final String[] DATES = {"12/10/2016","12/11/2016","11/30/2016","12/30/2016",
            "12/20/2016","11/20/2016","12/14/2016","11/3/2016","12/17/2016","12/24/2016"};
    private final int[] PRICES = {100,110,200,500,50,70,186,700,896,10000};
    private final String [] DETAILS ={
            "Chips 50,Drink 50",
            "Salad 50,Dressing 10,Drink 50",
            "Meal 100,Coffee 60,Copies 40",
            "Coffee 400,Suhsi 100",
            "Drink 50",
            "Coffee 70",
            "Soup 86,Meal 100",
            "T-Shirt 500,Socks 200",
            "T-Shirt 600,Underware 296",
            "Adapter 10000",};
    public ReceiptSearchFragment()
    {
        // Required empty public constructor
    }
    public static ReceiptSearchFragment newInstance()
    {
        ReceiptSearchFragment fragment = new ReceiptSearchFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Bill[] testdata = new Bill[10];
        initTestData();
    }
    private void initTestData()
    {
        //Setup Testdata
        testdata = new Bill[10];
        for(int i=0;i<testdata.length;i++)
        {
            testdata[i] = new Bill(DATES[i],SHOPS[i],PRICES[i],DETAILS[i]);
        }
        Arrays.sort(testdata,new DateComperator());
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_receipt, container, false);
    }
    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
    }
    private class DateComperator implements Comparator
    {
        @Override
        public int compare(Object o, Object t)
        {
            if(o instanceof Bill && t instanceof  Bill)
            {
                return ((Bill) o).getDate().compareTo(((Bill) t).getDate());
            }
            return 0;
        }
    }
}