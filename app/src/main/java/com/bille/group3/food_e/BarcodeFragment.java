package com.bille.group3.food_e;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 */
public class BarcodeFragment extends Fragment
{
    public BarcodeFragment()
    {
        // Required empty public constructor
    }
    public static Fragment newInstance()
    {
        BarcodeFragment fragment = new BarcodeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_barcode, container, false);
    }

}
