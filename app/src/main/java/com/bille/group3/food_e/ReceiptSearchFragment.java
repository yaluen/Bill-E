package com.bille.group3.food_e;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;



/**
 *
 */
public class ReceiptSearchFragment extends Fragment implements TextView.OnEditorActionListener
{
    private final static SimpleDateFormat dateformat = new SimpleDateFormat("mm.dd.yyyy", Locale.US);
    private final static String[] DATES = {"12.10.2016","12.11.2016","11.30.2016","12.30.2016",
        "12.20.2016","11.20.2016","12.14.2016","11.3.2016","12.17.2016","12.24.2016"};
    private final static int[] PRICES = {100,110,200,500,50,70,186,700,896,10000};
    private final static float CONTENT_TEXTSIZE = 20;
    private final static int PADDING_R = 5, PADDING_L = 5 ,PADDING_T = 10, PADDING_B = 10;
    private final static String [] DETAILS ={
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
    private final Bill[] testdata = new Bill[10];
    private final List<Bill> displayedItems = new LinkedList();
    private final String[] SHOPS = {"7-11","7-11","Family Markt","Carrefour","7-11",
            "Family Markt","Burger King","Gap","Fitch","Appel Store",};

    private LayoutInflater inflater;

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
        initTestData();
        displayedItems.clear();
        displayedItems.addAll(Arrays.asList(testdata));
    }
    private void initTestData()
    {
        //Setup Testdata
        Calendar c = Calendar.getInstance();
        for(int i=0;i<testdata.length;i++)
        {
            try {
                testdata[i] = new Bill(dateformat.parse(DATES[i]),SHOPS[i],PRICES[i],DETAILS[i]);
            } catch (ParseException e)
            {
                e.printStackTrace();
            }
        }
        Arrays.sort(testdata,Bill.getDateComperator());
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        this.inflater = inflater;
        View v = inflater.inflate(R.layout.fragment_search_receipt, container, false);
        Spinner spinner = (Spinner) v.findViewById(R.id.bill_sort_spinner);
        ArrayAdapter <CharSequence> adapter = ArrayAdapter.createFromResource(this.getActivity(),R.array.sort_options,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                switch(i)
                {
                    case 0: //Sort after date
                        Collections.sort(displayedItems,Bill.getDateComperator());
                        break;
                    case 1://Sort after location
                        Collections.sort(displayedItems,Bill.getLocationComperator());
                        break;
                    case 2://Sort after total
                        Collections.sort(displayedItems,Bill.getTotalComperator());
                        break;
                    default:
                        Collections.sort(displayedItems,Bill.getDateComperator());
                        break;
                }
                displayNewTable(view.getRootView(),displayedItems);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        displayNewTable(v,displayedItems);
        EditText edit = (EditText) v.findViewById(R.id.receiptSearchBar);
        edit.setOnEditorActionListener(this);
        return v;
    }
    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
    }
    public void displayNewTable(View view,List<Bill> elementsToDisplay)
    {
        //TODO iterate over list create a new row element for every element, id = pos in list
        TableLayout table = (TableLayout) view.findViewById(R.id.receiptTable);
        TableRow header = (TableRow)view.findViewById(R.id.headerTableReceipts);
        table.removeAllViews();
        table.addView(header,0);
        final Context c = this.getContext();
        for (int i = 0; i <elementsToDisplay.size() ; i++)
        {
            //Construct new Row
            // create date, location and price with data from list
            TableRow row = new TableRow(c);
            TableLayout.LayoutParams tableRowParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,TableLayout.LayoutParams.WRAP_CONTENT);
            int leftMargin=0;
            int topMargin=1;
            int rightMargin=0;
            int bottomMargin=1;
            tableRowParams.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);
            row.setLayoutParams(tableRowParams);
            row.setBackgroundColor(getResources().getColor(R.color.grid_1));
            TextView date = new TextView(c);
            date.setText(dateformat.format(elementsToDisplay.get(i).getDate()));
            date.setPadding(PADDING_L,PADDING_T,PADDING_R,PADDING_B);
            date.setTextSize(CONTENT_TEXTSIZE);
            date.setGravity(Gravity.CENTER);
            TextView location = new TextView(c);
            location.setText(elementsToDisplay.get(i).getLocation());
            location.setPadding(PADDING_L,PADDING_T,PADDING_R,PADDING_B);
            location.setTextSize(CONTENT_TEXTSIZE);
            location.setGravity(Gravity.CENTER);
            TextView price = new TextView(c);
            price.setText(Integer.toString(elementsToDisplay.get(i).getTotal()));
            price.setPadding(PADDING_L,PADDING_T,PADDING_R,PADDING_B);
            price.setTextSize(CONTENT_TEXTSIZE);
            price.setGravity(Gravity.CENTER);
            //Set row id and on click listener
            //            final TextView msg = new TextView(c);
            row.setClickable(true);
            row.setId(i);
            final int b = i;
            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    View receipts_details = (View) getActivity().getLayoutInflater().inflate(R.layout.receipt_list_object, null);
                    View receipts_details_final = builtTextToDisplay(b, receipts_details);
                    final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//                    builder.setTitle("Change Element");
                    builder.setView(receipts_details_final).setCancelable(true);
                    builder.setPositiveButton("Close",new DialogInterface.OnClickListener()
                    {
                        public void onClick(DialogInterface dialog,int id)
                        {
                            dialog.cancel();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.setCanceledOnTouchOutside(true);
                    dialog.show();
                }
            });
            //Add text fields to row
            row.addView(date);
            row.addView(location);
            row.addView(price);
            //add row to table
            table.addView(row);
        }
    }
    private View builtTextToDisplay(int pos, View v)
    {
        Bill bill = displayedItems.get(pos);
        StringBuilder b = new StringBuilder();
        ((TextView)v.findViewById(R.id.receiptDetailsTitle)).setText("Receipt Details");
        ((TextView)v.findViewById(R.id.receiptDetailsLocation)).setText("Location: " + bill.getLocation());
        ((TextView)v.findViewById(R.id.receiptDetailsDate)).setText("Date: " + dateformat.format(bill.getDate()));
        ((TextView)v.findViewById(R.id.receiptDetailsTotal)).setText("Total: " + bill.getTotal());
        ((TextView)v.findViewById(R.id.receiptDetailList)).setText("Details \n\n");
//        b.append("Location:          ");
//        b.append(bill.getLocation()+"\n");
//        b.append("date                  ");
//        b.append(dateformat.format(bill.getDate())+"\n");
//        b.append("Total:                ");
//        b.append(bill.getTotal()+"\n\n");
//        b.append("Details \n");
        String details = bill.getDetails();
        String [] detailsSplit = details.split(",");
        for(String s :detailsSplit)
        {
            String [] singleDetailArray = s.split(" ");
            if(singleDetailArray.length == 2)
            {
                String temp = ((TextView)v.findViewById(R.id.receiptDetailList)).getText().toString();
                temp = temp + singleDetailArray[0]+" - "+singleDetailArray[1]+"\n\n";
                ((TextView)v.findViewById(R.id.receiptDetailList)).setText(temp);
            }
        }
        return v;
    }
    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent)
    {
        if(i == EditorInfo.IME_ACTION_DONE)
        {
            final View view = textView.getRootView();
            final  CharSequence search = textView.getText();
            //Basic search
            final List<Bill> foundElements = new LinkedList();
            for(Bill b:testdata)
            {
                if(dateformat.format(b.getDate()).contains(search) ||
                        b.getLocation().toLowerCase().contains(search) ||
                        Integer.toString(b.getTotal()).contains(search)||
                        b.getDetails().toLowerCase().contains(search))
                {
                    foundElements.add(b);
                }
            }
            if(foundElements.size() != 0)
            {
                displayedItems.clear();
                displayedItems.addAll(foundElements);
                Collections.sort(foundElements,Bill.getDateComperator());
                displayNewTable(view,foundElements);
            }
            else
            {
                Toast.makeText(view.getContext(), R.string.receipt_search_fail,Toast.LENGTH_LONG).show();
            }
            return false;
        }
        return true;
    }
}