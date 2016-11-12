package com.example.michael.test2.Fragments;

import android.app.DialogFragment;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.michael.test2.Activity.MainActivity;
import com.example.michael.test2.Java.Account;
import com.example.michael.test2.R;

/**
 * Created by Michael on 11-Nov-16.
 */
public class AddAccountFragment extends DialogFragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_account_dialog, container, false);
        getDialog().setTitle("Add Account");

        Button dismiss = (Button) rootView.findViewById(R.id.atc_dialog_dismiss);
        Button add = (Button) rootView.findViewById(R.id.atc_dialog_add);

        final TextView name = (TextView) rootView.findViewById(R.id.dialog_act_name);
        final Spinner type = (Spinner) rootView.findViewById(R.id.act_dialog_type);
        final TextView strBalance = (TextView) rootView.findViewById(R.id.act_dialog_strBalance);

        final Spinner colStr = (Spinner) rootView.findViewById(R.id.act_dialog_colour_spin);
        final ImageView col = (ImageView) rootView.findViewById(R.id.act_dialog_colour);



        colStr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                col.setImageResource(findIcon((String) colStr.getSelectedItem()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView){}
        });

        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (name.getText().toString().length() != 0)
                {
                    Double balance = Double.parseDouble(strBalance.getText().toString());

                    Account act = new Account(name.getText().toString(), (String) type.getSelectedItem(), (String) colStr.getSelectedItem(), balance );
                    ((MainActivity) getActivity()).addAccount(act);
                    dismiss();
                }
                else
                {

                }
            }
        });

        dismiss.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return rootView;
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
}
