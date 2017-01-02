package com.bille.group3.food_e;

import android.icu.text.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by PeterM11x on 02.01.2017.
 *Container class for bills
 */
public class Bill
{
    private final String date;
    private final String shop;
    private final int total;
    private final String details;
    public Bill(String date,String shop,int total,String details)
    {
        this.date = date;
        this.shop = shop;
        this.total = total;
        this.details = details;
    }
    public String getDate()
    {
        return date;
    }
    public String getShop()
    {
        return shop;
    }
    public int getTotal()
    {
        return total;
    }
    public String getDetails()
    {
        return details;
    }
}
