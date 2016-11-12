package com.example.michael.test2.Java;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.michael.test2.R;

import java.util.ArrayList;


public class accountList_names extends ArrayAdapter<Account>
{
    private final Activity context;
    private ArrayList<Account> account;

    public accountList_names(Activity context, ArrayList<Account> account)
    {
        super(context, R.layout.account_mini_list_item, account);
        this.context = context;
        this.account = account;
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View rowView= inflater.inflate(R.layout.account_mini_list_item, null, true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.exp_act_name);
        ImageView icon = (ImageView) rowView.findViewById(R.id.act_icon);

        //add budget once implemented into accounts
        Double balance =  account.get(position).getBalance();
        txtTitle.setText(account.get(position).getName()+" - $"+balance.toString() );
        icon.setImageResource(account.get(position).getColourResource());

        return rowView;
    }
}
