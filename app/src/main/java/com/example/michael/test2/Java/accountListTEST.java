package com.example.michael.test2.Java;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.michael.test2.R;

import java.util.ArrayList;

public class accountListTEST extends ArrayAdapter<Account>
{
    private final Activity context;
    private ArrayList<Account> account;

    public accountListTEST(Activity context, ArrayList<Account> account)
    {
        super(context, R.layout.account_list_item, account);
        this.context = context;
        this.account = account;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View rowView= inflater.inflate(R.layout.account_list_item, null, true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
        TextView txtTitle2 = (TextView) rowView.findViewById(R.id.txt2);
        TextView balance = (TextView) rowView.findViewById(R.id.account_accountBalanace);
//        RelativeLayout layout = (RelativeLayout) rowView.findViewById(R.id.accountListBG);
//        LinearLayout layout = (LinearLayout) rowView.findViewById(R.id.accountListBG);
        ImageView colour =  (ImageView) rowView.findViewById(R.id.account_icon);

        colour.setImageResource((account.get(position).getColourResource()));

        txtTitle.setText(account.get(position).getName() );
        txtTitle2.setText(account.get(position).getType() );

        Double temp = account.get(position).getBalance();

        balance.setText("Balance $" + temp.toString());

        return rowView;
    }
}
