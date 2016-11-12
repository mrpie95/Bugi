package com.example.michael.test2.Java;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.michael.test2.R;


public class accountList extends ArrayAdapter<String>
{
    private final Activity context;
    private final String[] name;
    private final String[] type;
    private final Integer[] colour;

    public accountList(Activity context, String[] name, String[] type, Integer[] colour)
    {
        super(context, R.layout.account_list_item, name);
        this.context = context;
        this.name = name;
        this.type = type;
        this.colour = colour;

    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View rowView= inflater.inflate(R.layout.account_list_item, null, true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
        TextView txtTitle2 = (TextView) rowView.findViewById(R.id.txt2);
        LinearLayout layout = (LinearLayout) rowView.findViewById(R.id.accountListBG);
        layout.setBackgroundColor(colour[position]);
        txtTitle.setText("Name: " + name[position] );
        txtTitle2.setText("Type: " + type[position]);

        return rowView;
    }
}
