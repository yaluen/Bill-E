package com.bille.group3.food_e;
import java.util.Comparator;
import java.util.Date;
/**
 * Created by PeterM11x on 02.01.2017.
 *Container class for bills
 */
public class Bill
{
    private final Date date;
    private final String location;
    private final int total;
    private final String details;
    public Bill(Date date,String location,int total,String details)
    {
        this.date = date;
        this.location = location;
        this.total = total;
        this.details = details;
    }
    public Date getDate()
    {
        return date;
    }
    public String getLocation()
    {
        return location;
    }
    public int getTotal()
    {
        return total;
    }
    public String getDetails()
    {
        return details;
    }
    public static DateComperator getDateComperator()
    {
        return new DateComperator();
    }
    public static LocationComperator getLocationComperator() { return new LocationComperator();}
    public static TotalComperator getTotalComperator(){return new TotalComperator();}
    private static class DateComperator implements Comparator
    {
        @Override
        public int compare(Object o, Object t)
        {
            if(o instanceof Bill && t instanceof  Bill)
            {
                return ((Bill) o).getDate().compareTo(((Bill) t).getDate());
            }
            return 0;
        }
    }
    private static class LocationComperator implements Comparator
    {
        @Override
        public int compare(Object o, Object t)
        {
            if(o instanceof Bill && t instanceof Bill)
            {
                return ((Bill) o).getLocation().compareTo(((Bill) t).getLocation());
            }
            return 0;
        }
    }
    private static class TotalComperator implements Comparator
    {
        @Override
        public int compare(Object o, Object t)
        {
            if(o instanceof Bill && t instanceof Bill)
            {
                return Integer.compare(((Bill) o).getTotal(),((Bill) t).getTotal());
            }
            return 0;
        }
    }
}
