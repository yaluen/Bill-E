package com.bille.group3.food_e;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alan on 1/3/17.
 */

public class SharedFoodArrayAdapter extends ArrayAdapter<SharedFood> {

    private Context context;
    private List<SharedFood> sharedFoodsList;

    public SharedFoodArrayAdapter(Context context, int resource, ArrayList<SharedFood> objects) {
        super(context, resource, objects);

        this.context = context;
        this.sharedFoodsList = objects;
    }

    //called when rendering the list
    public View getView(int position, View convertView, ViewGroup parent) {

        //get the property we are displaying
        SharedFood foodItem = sharedFoodsList.get(position);

        //get the inflater and inflate the XML layout for each item
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.main_list_object, null);

        TextView description = (TextView) view.findViewById(R.id.listFoodDescription);
        TextView expire = (TextView) view.findViewById(R.id.listFoodExpireDate);
        TextView location = (TextView) view.findViewById(R.id.listFoodLocation);
        ImageView image = (ImageView) view.findViewById(R.id.listFoodImage);

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
        int imageID = context.getResources().getIdentifier(foodItem.getImage(), "drawable", context.getPackageName());
        image.setImageResource(imageID);

        return view;
    }
}
