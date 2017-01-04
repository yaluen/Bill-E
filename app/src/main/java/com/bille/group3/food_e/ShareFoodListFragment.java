package com.bille.group3.food_e;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PeterM11x on 03.01.2017.
 * List fragment for handling shared objects
 */

public class ShareFoodListFragment extends ListFragment
{
    private final ArrayList<SharedFood> list = new ArrayList<>();
    private shareFoodAdapter adapter;
    private final static int REQUEST_IMAGE_CAPTURE =1;
    private View header;
    public void ShareFoodListFragment()
    {
        //Required Emty constructor
    }
    public static ListFragment newInstance()
    {
        ShareFoodListFragment fragment = new ShareFoodListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;

    }
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View v = inflater.inflate(R.layout.fragment_share_food,container,false);
        adapter = new shareFoodAdapter(this.getActivity(),list);
        header = inflater.inflate(R.layout.header_list_share,null);
        setListAdapter(adapter);
        FloatingActionButton button = (FloatingActionButton) v.findViewById(R.id.shareAddButton);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //TODO start activity to get picture
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        });
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        getListView().addHeaderView(header);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            SharedFood food = new SharedFood("A new Item","Item",imageBitmap,null,"loc");
            list.add(food);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id)
    {
        //TODO implement dialog to remove/update entree
        super.onListItemClick(l, v, position, id);
    }

    private class  shareFoodAdapter extends ArrayAdapter<SharedFood>
    {
        private final Context context;
        public shareFoodAdapter(Context context, List<SharedFood> values)
        {
            super(context,-1,values);
            this.context = context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            //Get layout and views
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.list_element,parent,false);
            TextView line1 = (TextView) rowView.findViewById(R.id.firstLine);
            TextView line2 = (TextView) rowView.findViewById(R.id.secondLine);
            ImageView prev = (ImageView) rowView.findViewById(R.id.shareFood_icon);
            //Set data
            //TODO possible nullpointer exception
            SharedFood actItem = getItem(position);
            prev.setImageBitmap(actItem.getBitmap());
            line1.setText(actItem.getDescription());
            line2.setText(actItem.getCategory());
            return rowView;
        }
    }
    /*private class ViewHolder
    {
        private ImageView img;
        private TextView line1,line2;
        public ViewHolder(View row)
        {
            this.img = (ImageView) row.findViewById(R.id.icon);
            this.line1 = (TextView) row.findViewById(R.id.firstLine);
            this.line2 = (TextView) row.findViewById(R.id.secondLine);
        }
        public ImageView getImageView()
        {
            return img;
        }
        public TextView getLine1()
        {
            return line1;
        }
        public TextView getLine2()
        {
            return line2;
        }
    }*/
}
