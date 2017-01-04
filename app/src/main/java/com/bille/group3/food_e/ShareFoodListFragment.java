package com.bille.group3.food_e;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
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
    public final static int REQUEST_IMAGE_CAPTURE = 1;
    private final static int REQUEST_DETAILS = 2;
    private View header;
    private int editPos= -1;
    private SharedFood editFood = null;
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
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK)
        {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            SharedFood food = new SharedFood(imageBitmap);
            Intent setDataIntent = new Intent(getActivity(),SharedFoodAddActivity.class);
            setDataIntent.putExtra(SharedFoodAddActivity.SHARE_FOOD_EXTRA,food);
            startActivityForResult(setDataIntent,REQUEST_DETAILS);
        }
        else
        {
            if (requestCode == REQUEST_DETAILS && resultCode == Activity.RESULT_OK)
            {
                Bundle extras = data.getExtras();
                SharedFood add = (SharedFood) extras.get(SharedFoodAddActivity.SHARE_FOOD_EXTRA_RESULT);
                list.add(add);
                adapter.notifyDataSetChanged();
                editFood = null;
            } else
            {
                //If edditing went wrong add element that user wanted to element again to list where it was removed
                if (requestCode == REQUEST_DETAILS && editPos != -1)
                {
                    list.add(editPos,editFood);
                    adapter.notifyDataSetChanged();
                    editFood = null;
                    editPos = -1;
                }
            }
        }
    }
    @Override
    public void onListItemClick(ListView l, View v, final int position, long id)
    {
        final int pos = position-1;
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Change Element");
        builder.setMessage("Do you want to change the element ?").setCancelable(true);
        builder.setPositiveButton("Edit",new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog,int id)
            {
                editPos = pos;
                editFood = adapter.getItem(pos);
                adapter.getItem(pos);
                Intent setDataIntent = new Intent(getActivity(),SharedFoodAddActivity.class);
                setDataIntent.putExtra(SharedFoodAddActivity.SHARE_FOOD_EXTRA,editFood);
                adapter.remove(editFood);
                startActivityForResult(setDataIntent,REQUEST_DETAILS);
            }
        });
        builder.setNegativeButton("Delete",new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog,int id)
            {
                adapter.remove(adapter.getItem(pos));
            }
        });
        builder.create().show();
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
}
