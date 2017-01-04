package com.bille.group3.food_e;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Loh-Shilin on 2017/01/02.
 */

public class SharedFood implements Serializable
{

    private String description = null;
    private String category = null;
    private byte[] bitmap = null;
    private Date expiration = null;
    private Date creation = null;
    private String location = null;
    private String image = null;
    private String from = null;
    private String to = null;
    /*Constructors*/

    public SharedFood()
    {
    }

    public SharedFood(String description, Date creation, Date expiration, String location, String image) {
        this.description = description;
        this.creation = creation;
        this.expiration = expiration;
        this.location = location;
        this.image = image;
    }
    public SharedFood(Bitmap bitmap)
    {
        this.description = null;
        this.category = null;
        this.bitmap = getByteArray(bitmap);
        this.expiration = null;
        this.location = null;
    }
    public String getSearchString()
    {
        return description + " " + location;
    }
    private byte[] getByteArray(Bitmap bitmap)
    {
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,50,bs);
        return bs.toByteArray();
    }

    /*Getter*/
    public String getCategory() {
        return category;
    }
    public Bitmap getBitmap()
    {
        return BitmapFactory.decodeByteArray(bitmap,0,bitmap.length);
    }
    public String getLocation() {return location; }
    public Date getExpiration() {return expiration; }
    public Date getCreation() {return creation; }
    public String getDescription() {return description; }
    public String getImage() { return image; }
    public String getFrom() {
    return from;}
    public String getTo() {return to;
    }
    /*Setter*/
    public void setDescription(String description) {
        this.description = description;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public void setBitmap(Bitmap bitmap)
    {
        this.bitmap = getByteArray(bitmap);
    }
    public void setCreation(Date creation) {
        this.creation = creation;
    }
    public void setTo(String to) {
        this.to = to;
    }
    public void setFrom(String from) {
        this.from = from;
    }
}
