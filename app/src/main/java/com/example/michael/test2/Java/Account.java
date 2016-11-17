package com.example.michael.test2.Java;

import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.michael.test2.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Account implements Parcelable
{
    private String name;
    private String type;
    private String colour;

    private ArrayList<Budget> budgets;
    private ArrayList<Expense> expenses;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(name);
        out.writeString(type);
        out.writeString(colour);
    }

    public static final Parcelable.Creator<Account> CREATOR = new Parcelable.Creator<Account>() {
        public Account createFromParcel(Parcel in) {
            return new Account(in);
        }

        public Account[] newArray(int size) {
            return new Account[size];
        }
    };

    public Account()
    {

    }

    private Account(Parcel in) {
        name = in.readString();
        type = in.readString();
        colour = in.readString();
    }

    public Account (String name, String type, String colour, Double balance)
    {
        this.name = name;
        this.type = type;
        this.colour = colour;
        expenses = new ArrayList<Expense>();

        Expense e = new Expense("Starting Balance", balance, Calendar.getInstance().getTime());
        expenses.add(e);

        budgets = new ArrayList<Budget>();
    }

    public String getName() { return name; }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public void setColour(String colour)
    {
        this.colour = colour;
    }

    public String getColour()
    {
        return colour;
    }

    public ArrayList<Expense> getExpenses()
    {
        return expenses;
    }

    public void AddExpense (Expense cost)
    {
        expenses.add(cost);
    }

    public double getBalance()
    {
        Double calcBalance = 0.0;

        for (Expense e: expenses)
        {
            calcBalance += e.getCost();
        }
        return calcBalance;
    }

    public double getStartingBalance()
    {
        return expenses.get(0).getCost();
    }


    public int getColourResource()
    {
        return findIcon(colour);
    }

    public int getColourInt()
    {
        return findColour(colour);
    }

    public void AddBudgets (Budget budget)
    {
        budgets.add(budget);
    }

    public List<Budget> getBudgets ()
    {
        return budgets;
    }

    private int findIcon(String colour)
    {
        switch (colour.toLowerCase())
        {
            case "yellow":
                return R.drawable.ic_yellow_small;

            case "blue":
                return R.drawable.ic_blue_small;

            case "red":
                return R.drawable.ic_red_small;

            case "green":
                return R.drawable.ic_green_small;

            case "purple":
                return R.drawable.ic_purple_small;

            default:
                return R.drawable.ic_blue_small;
        }
    }

    private int findColour(String colour)
    {
        switch (colour.toLowerCase())
        {
            case "yellow":
                return Color.YELLOW;

            case "blue":
                return Color.BLUE;

            case "red":
                return Color.RED;

            case "green":
                return Color.GREEN;

            case "purple":
                return Color.rgb(98, 0, 231);

            default:
                return Color.RED;
        }
    }




}
