package com.bille.group3.food_e;

import java.util.Date;

/**
 * Created by Loh-Shilin on 2017/01/02.
 */

public class SharedFood {
    private String description;
    private Date expiration;
    private Date creation;
    private String location;
    private String image;

    public SharedFood(String descript, Date create, Date expire, String locat, String img)
    {
        description = descript;
        creation = create;
        expiration = expire;
        location = locat;
        image = img;
    }

    public String getLocation() {return location; }
    public Date getExpiration() {return expiration; }
    public Date getCreation() {return creation; }
    public String getDescription() {return description; }
    public String getImage() { return image; }
}
