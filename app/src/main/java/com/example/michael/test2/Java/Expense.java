package com.example.michael.test2.Java;

import android.location.Location;

import java.util.Date;

/**
 * Created by Michael on 29-Sep-16.
 */
public class Expense
{
    private String description;
    private Double cost;
    private Date date;
    private Location location;

    public Expense()
    {

    }

    public Expense (String description, Double cost, Date date)
    {
        this.description = description;
        this.cost = cost;
        this.date = date;
//        this.location = location;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String value)
    {
        description = value;
    }

    public Double getCost ()
    {
        return cost;
    }

    public void setCost(Double value)
    {
        cost = value;
    }

    public Date getDate ()
    {
        return date;
    }

    public void setDate(Date value)
    {
        date = value;
    }

    public Location getLocation ()
    {
        return location;
    }

    public void setLocation (Location value)
    {
        location = value;
    }
}

