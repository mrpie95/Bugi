package com.example.michael.test2.Fragments;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.michael.test2.Activity.MainActivity;
import com.example.michael.test2.Java.Account;
import com.example.michael.test2.Java.Communicator;
import com.example.michael.test2.Java.Expense;
import com.example.michael.test2.Java.accountListTEST;
import com.example.michael.test2.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;

public class AccountListFragment extends ListFragment
{
    private View row;
    private ListView accountList = null;
    private ArrayList<Account> accounts = null;

    @Override
    public  void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list , container, false);

//        if (savedInstanceState != null)
//        {
        if (getArguments() != null)
        {
            accounts = getArguments().getParcelableArrayList("accounts1");
            accountList = (ListView) view.findViewById(R.id.listView);
        }

        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        if (accounts != null)
        {
            accountList.setAdapter(new accountListTEST(getActivity(), accounts));
            accountList.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                {
                    row = view;
                    if (row != null)
                    {
                        ((MainActivity) getActivity()).AccuontDetails();
                        Toast.makeText(getActivity(), "You Clicked at " + position, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}


