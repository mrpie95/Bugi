package com.example.michael.test2.Fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.michael.test2.Activity.MainActivity;
import com.example.michael.test2.Java.Account;
import com.example.michael.test2.Java.Expense;
import com.example.michael.test2.Java.accountListTEST;
import com.example.michael.test2.Java.accountList_names;
import com.example.michael.test2.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

/**
 * Created by Michael on 11-Nov-16.
 */
public class AddExpenseFragment extends DialogFragment{

    EditText desc;
    EditText amount;
    EditText date;
    EditText location;

    Date dateData;

    Account currentAccount;
    int currentPosition;
    Spinner account;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().setTitle("Add Cost");

        View rootView = inflater.inflate(R.layout.fragment_add_expense_dialog, container, false);

        ArrayList<Account> acts = getArguments().getParcelableArrayList("accounts1");

        desc = (EditText) rootView.findViewById(R.id.exp_dialog_name);
        amount = (EditText) rootView.findViewById(R.id.exp_dialog_amount);
        date = (EditText) rootView.findViewById(R.id.exp_dialog_date);
        location = (EditText) rootView.findViewById(R.id.exp_dialog_location);

        Calendar calendar = Calendar.getInstance();
        dateData = calendar.getTime();

        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        date.setText(format.format(dateData));


        Button dismiss = (Button) rootView.findViewById(R.id.exp_dialog_dismiss);
        Button expense = (Button) rootView.findViewById(R.id.exp_btn_exp);
        Button payment = (Button) rootView.findViewById(R.id.exp_btn_pay);
        ImageButton dateBtn = (ImageButton) rootView.findViewById(R.id.exp_date_button);

        dateBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                DialogFragment newFragment = new SelectFromDate();
                newFragment.show(getActivity().getFragmentManager(), "DatePicker");
            }
        });

        account = (Spinner) rootView.findViewById(R.id.exp_dialog_type);
        account.setAdapter(new accountList_names(getActivity(), acts));

        account.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                currentAccount = (Account) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        expense.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                addCost(true);
            }
        });

        payment.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                addCost(false);
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

    //adds the expense with true beings an expense, date conflict checks and sorting of dates
    // to all for adding dates in the past 
    private void addCost(boolean exp)
    {
        String name  = desc.getText().toString();
        String cost  = amount.getText().toString();
        Expense newExp;

        if (exp)
            newExp = new Expense(name,-1*Double.parseDouble(cost),dateData);
        else
            newExp = new Expense(name,Double.parseDouble(cost),dateData);


        if (CheckForExpenseConflicts(newExp))
        {
            Calendar fixedExp = Calendar.getInstance();
            fixedExp.setTimeInMillis(newExp.getDate().getTime() + 1);
            newExp.setDate(fixedExp.getTime());
        }

        currentAccount.AddExpense(newExp);

        Collections.sort(currentAccount.getExpenses() ,new Comparator<Expense>()
        {
            @Override
            public int compare(Expense s1, Expense s2)
            {
                return Double.compare(s1.getDate().getTime(), s2.getDate().getTime());
            }
        });

        ((MainActivity) getActivity()).updateAccountList();
        dismiss();

    }

    private Boolean CheckForExpenseConflicts(Expense newExp)
    {
        ArrayList<Expense> e = currentAccount.getExpenses();
        for (Expense exp : e)
        {
            if (newExp.getDate().getTime() == exp.getDate().getTime())
                return true;
        }
        return false;
    }

    private class SelectFromDate extends DialogFragment implements DatePickerDialog.OnDateSetListener
    {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState)
        {
            final Calendar calendar = Calendar.getInstance();
            int yy = calendar.get(Calendar.YEAR);
            int mm = calendar.get(Calendar.MONTH);
            int dd = calendar.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), this, yy, mm, dd);
        }

        public void onDateSet(DatePicker view, int yy, int mm, int dd)
        {
            date.setText(dd + "/" + (mm+1) + "/" + yy);
            String dtStart = dd+"/"+(mm+1)+"/"+yy;
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

            try {
                    dateData = format.parse(dtStart);

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
}
