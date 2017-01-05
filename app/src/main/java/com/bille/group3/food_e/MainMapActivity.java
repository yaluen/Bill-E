package com.bille.group3.food_e;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class MainMapActivity extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static View view;
    private LayoutInflater inflate;
    private ArrayList<SharedFood> sharedFoods = new ArrayList<>();
    private ArrayList<LatLng> coordinates = new ArrayList<>();

    HashMap<Marker, SharedFood> markerToFood = new HashMap<Marker, SharedFood>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inflate = inflater;
        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
        }
        try {
            view = inflater.inflate(R.layout.fragment_main_map, container, false);
            // Obtain the SupportMapFragment and get notified when the map is ready to be used.
            SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        } catch (InflateException e) {

        }


        return view;
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        setUpMarker();

        mMap.setInfoWindowAdapter(new MainMapInfoWIndow());

        // Add a marker in Sydney and move the camera
        coordinates.add(new LatLng(25.037712, 121.505814));
//        mMap.addMarker(new MarkerOptions().position(taipei).title("Apples for sale 30% off"));
        coordinates.add(new LatLng(24.976198, 121.546547));
//        mMap.addMarker(new MarkerOptions().position(taipei_1).title("Oranges for sale 30% off"));
        coordinates.add(new LatLng(25.014726, 121.530278));
//        mMap.addMarker(new MarkerOptions().position(taipei_2).title("Half pizza"));
        coordinates.add(new LatLng(25.114726, 121.530278));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(coordinates.get(0)));

        for (int i = 0; i < coordinates.size(); i++)
        {
            Marker customMarker = mMap.addMarker(new MarkerOptions()
                    .position(coordinates.get(i)));
            markerToFood.put(customMarker, sharedFoods.get(i));
        }

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(coordinates.get(0), 12.0f));

    }

    private void setUpMarker() {

        sharedFoods.add(new SharedFood("Apples for sale 30% off", new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime(),
                new GregorianCalendar(2014, Calendar.FEBRUARY, 23).getTime(), "WanHua 7-11", "shared_apples"));

        sharedFoods.add(new SharedFood("Oranges for sale 30% off", new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime(),
                new GregorianCalendar(2014, Calendar.FEBRUARY, 23).getTime(), "XinDian Carrefour", "shared_oranges"));

        sharedFoods.add(new SharedFood("Peaches for sale 30% off", new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime(),
                new GregorianCalendar(2014, Calendar.FEBRUARY, 23).getTime(), "ShiDa Night Market", "shared_peaches"));

        sharedFoods.add(new SharedFood("Free pizza for everyone", new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime(),
                new GregorianCalendar(2014, Calendar.FEBRUARY, 18).getTime(), "Beitou Cake House", "shared_pizza"));

    }


    class MainMapInfoWIndow implements GoogleMap.InfoWindowAdapter {

        private final View myContentsView;

        MainMapInfoWIndow(){
            myContentsView = inflate.inflate(R.layout.main_list_object, null);
        }

        @Override
        public View getInfoWindow(Marker marker) {
            return null;
        }

        @Override
        public View getInfoContents(Marker marker) {
            SharedFood foodItem = markerToFood.get(marker);

            TextView description = (TextView) myContentsView.findViewById(R.id.listFoodDescription);
            TextView expire = (TextView) myContentsView.findViewById(R.id.listFoodExpireDate);
            TextView location = (TextView) myContentsView.findViewById(R.id.listFoodLocation);
            ImageView image = (ImageView) myContentsView.findViewById(R.id.listFoodImage);

            //set address and description
            location.setText("Location: " + foodItem.getLocation());

            //display trimmed excerpt for description
            int descriptionLength = foodItem.getDescription().length();
            if(descriptionLength >= 100){
                String descriptionTrim = foodItem.getDescription().substring(0, 100) + "...";
                description.setText(descriptionTrim);
            }else{
                description.setText(foodItem.getDescription());
            }

            expire.setText("Expires: " + new SimpleDateFormat("MM.dd.yyy HH:mm").format(foodItem.getExpiration()));

            //get the image associated with this property
            int imageID = getContext().getResources().getIdentifier(foodItem.getImage(), "drawable", getContext().getPackageName());
            image.setImageResource(imageID);

            return myContentsView;
        }
    }

}
