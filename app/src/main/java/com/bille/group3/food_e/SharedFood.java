package com.bille.group3.food_e;

import android.graphics.Bitmap;

import java.util.Date;

/**
 * Created by Loh-Shilin on 2017/01/02.
 */

public class SharedFood {
    private String description;
    private String category;
    private Bitmap bitmap;
    private Date expiration;
    private Date creation;
    private String location;
    private String image;
    /*Constructors*/
    public SharedFood(String description, Date creation, Date expiration, String location, String image) {
        this.description = description;
        this.creation = creation;
        this.expiration = expiration;
        this.location = location;
        this.image = image;
    }
    public SharedFood(String description, String category, Bitmap preview, Date expiration, String location)
    {
        this.description = description;
        this.category = category;
        this.bitmap = preview;
        this.expiration = expiration;
        this.location = location;
    }
    /*Getter*/
    public String getCategory() {
        return category;
    }
    public Bitmap getBitmap() {
        return bitmap;
    }
    public String getLocation() {return location; }
    public Date getExpiration() {return expiration; }
    public Date getCreation() {return creation; }
    public String getDescription() {return description; }
    public String getImage() { return image; }
    public String getSearchString() {
        return description + " " + location;
    }

    /*Setter*/
    public void setDescription(String description) {
        this.description = description;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }
    public void setLocation(String location) {
        this.location = location;
    }
}
