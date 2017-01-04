package com.bille.group3.food_e;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.bille.group3.food_e.ShareFoodListFragment.REQUEST_IMAGE_CAPTURE;

public class SharedFoodAddActivity extends AppCompatActivity
{
    public final static String SHARE_FOOD_EXTRA = "share";
    public final static String SHARE_FOOD_EXTRA_RESULT = "result";
    private SharedFood result = new SharedFood();
    ImageView img;
    private final static SimpleDateFormat dateformat = new SimpleDateFormat("mm.dd.yyyy", Locale.US);
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_food_add);
        initActionBar();
        SharedFood obj = (SharedFood)getIntent().getSerializableExtra(SHARE_FOOD_EXTRA);
        result = obj;
        img = (ImageView) findViewById(R.id.imageAddShare);
        Bitmap bmp = obj.getBitmap();
        img.setImageBitmap(bmp);
        img.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        });
        result.setBitmap(bmp);
        //OnEditListener Description
        EditText description = (EditText)findViewById(R.id.enter_text_description);
        if(obj.getDescription() != null)
        {
            description.setText(obj.getDescription());
        }
        description.setOnEditorActionListener(new TextView.OnEditorActionListener()
        {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent)
            {
                if(i == EditorInfo.IME_ACTION_NEXT)
                {
                    String txt = textView.getText().toString();
                    result.setDescription(txt);
                    return false;
                }
                return true;
            }
        });
        //Spinner
        Spinner spinner = (Spinner) findViewById(R.id.spinner_add_food);
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.category_options,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Preset spinner if necessary
        if(obj.getCategory() != null)
        {
            int pos = adapter.getPosition(obj.getCategory());
            if(pos > 0)
            {
                spinner.setSelection(pos);
            }
        }
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                result.setCategory(adapter.getItem(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        EditText date = (EditText) findViewById(R.id.edit_text_exp);
        if(obj.getCreation() != null)
        {
            date.setText(dateformat.format(obj.getCreation()));
        }
        date.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent)
            {
                if(i == EditorInfo.IME_ACTION_NEXT)
                {
                    String txt = textView.getText().toString();
                    try
                    {
                        txt = txt.replace('/','.');
                        txt = txt.replace('-','.');
                        String [] splt = txt.split("\\.");
                        if(splt.length != 3 || splt[0].length() != 2 || splt[1].length() != 2 ||splt[2].length() !=4)
                        {
                            Toast.makeText(SharedFoodAddActivity.this,"Wrong date format try mm.dd.yyyy",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Date d = dateformat.parse(txt);
                            result.setCreation(d);
                        }
                    }
                    catch (ParseException e)
                    {
                        e.printStackTrace();
                    }
                    return false;
                }
                return true;
            }
        });
        EditText from = (EditText) findViewById(R.id.edit_from);
        if(obj.getFrom() != null)
        {
            from.setText(obj.getFrom());
        }
        from.setOnEditorActionListener(new TextView.OnEditorActionListener()
        {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent)
            {
                if(i == EditorInfo.IME_ACTION_NEXT)
                {
                    result.setFrom(textView.getText().toString());
                    return false;
                }
                return true;
            }
        });
        EditText to = (EditText) findViewById(R.id.edit_to);
        if(obj.getTo() != null)
        {
            to.setText(obj.getTo());
        }
        to.setOnEditorActionListener(new TextView.OnEditorActionListener()
        {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent)
            {
                if(i == EditorInfo.IME_ACTION_DONE)
                {
                    result.setTo(textView.getText().toString());
                    return false;
                }
                return true;
            }
        });
        Button btn = (Button) findViewById(R.id.button_confirm_share);
        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(result.getDescription() != null)
                {
                    if(result.getBitmap() != null)
                    {
                        if(result.getCategory() != null)
                        {
                            if(result.getCreation() != null)
                            {
                                if(result.getFrom() != null)
                                {
                                    if(result.getTo() != null)
                                    {
                                        Intent resultIntent = new Intent();
                                        resultIntent.putExtra(SHARE_FOOD_EXTRA_RESULT,result);
                                        setResult(RESULT_OK,resultIntent);
                                        finish();
                                    }
                                    else
                                    {
                                        displayErrorToast("to");
                                    }
                                }
                                else
                                {
                                    displayErrorToast("from");
                                }
                            }
                            else
                            {
                                displayErrorToast("expire");
                            }
                        }
                        else
                        {
                            displayErrorToast("category");
                        }
                    }
                    else
                    {
                        displayErrorToast("picture");
                    }
                }
                else
                {
                    displayErrorToast("description");
                }
            }
        });
    }
    private void displayErrorToast(String msg)
    {
        Toast.makeText(this,"Please check the field "+msg,Toast.LENGTH_LONG).show();
    }
    private void initActionBar()
    {
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#AAFF00")));
        actionBar.setStackedBackgroundDrawable(new ColorDrawable(Color.parseColor("#AAFF00")));
        actionBar.setTitle(Html.fromHtml("<font color='#434343'> Food-E </font>"));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK)
        {
            Bundle extras = data.getExtras();
            Bitmap b =(Bitmap) extras.get("data");
            result.setBitmap(b);
            img.setImageBitmap(b);
        }
    }
}
