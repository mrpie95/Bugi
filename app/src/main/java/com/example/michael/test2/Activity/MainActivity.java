package com.example.michael.test2.Activity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.michael.test2.Fragments.AccountListFragment;
import com.example.michael.test2.Fragments.AddAccountFragment;
import com.example.michael.test2.Fragments.AddExpenseFragment;
import com.example.michael.test2.Fragments.graphFragment;
import com.example.michael.test2.Java.Account;
import com.example.michael.test2.Java.Expense;
import com.example.michael.test2.R;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity
{
    AccountListFragment list;
    ArrayList<Account> accounts = new ArrayList<Account>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intialiseApp();
    }

    @Override
    public void onBackPressed()
    {

        // super.onBackPressed(); // Comment this super call to avoid calling finish()
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.add_item:

                FragmentManager fm = getFragmentManager();
                AddAccountFragment dialogFragment = new AddAccountFragment ();
                dialogFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.CustomDialog);
                dialogFragment.show(fm, "Sample Fragment");

//                accounts.addAll(mockAccounts(3));
//                updateAccountList();
                return true;

            case R.id.btn2:
                accounts.remove(accounts.size()-1);
                updateAccountList();
                return true;

            case R.id.test_item:
                accounts.get(0).getExpenses().remove(accounts.get(0).getExpenses().size()-1);
                updateAccountList();
                return true;

            default:
                super.onOptionsItemSelected(item);

        }
        return false;
    }


    //update the list to display all current account and
    //display the coreseponding graphs
    public void updateAccountList()
    {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("accounts1", accounts);

        graphFragment graph = new graphFragment();
        graph.setArguments(bundle);
        FragmentManager fragManager = getFragmentManager();
        FragmentTransaction tranView1 = fragManager.beginTransaction();
        tranView1.replace(R.id.view1, graph);
        tranView1.addToBackStack(null);
        tranView1.commit();

        list = new AccountListFragment();
        list.setArguments(bundle);

        fragManager = getFragmentManager();
        FragmentTransaction tranView2 = fragManager.beginTransaction();
        tranView2.replace(R.id.view2, list);
        tranView2.addToBackStack(null);
        tranView2.commit();
    }

    public void addAccount(Account act)
    {
        accounts.add(act);
        updateAccountList();
    }

//    public void replaceAccount(Account act, int pos)
//    {
//        //accounts.set(pos, act);
//        updateAccountList();
//    }

    private void intialiseApp()
    {

        //**** needs fixing ****
        ActionBar actionBar = getActionBar();
        if (actionBar != null)
        {
            actionBar.setBackgroundDrawable(new ColorDrawable(Color.rgb(0, 128, 255)));
//            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setTitle("Bugi");
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabSummary);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("accounts1", accounts);

                FragmentManager fm = getFragmentManager();
                AddExpenseFragment dialogFragment = new AddExpenseFragment ();
                dialogFragment.setArguments(bundle);

                dialogFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.CustomDialog);
                dialogFragment.show(fm, "");
            }
        });

        accounts.addAll(mockAccounts(1));
        updateAccountList();
    }

    //Generate mock test accounts with data
    public ArrayList<Account> mockAccounts(int i)
    {
        ArrayList<Account> results = new ArrayList<Account>();
        Account act;

        if (i >= 1)
        {
            Calendar calendar = Calendar.getInstance();
            act = new Account("test 3", "bank", "purple", 100.00);
//            Expense exp = new Expense("burger for kitkat", 100.00, calendar.getTime());
//            act.AddExpense(exp);
//
//            calendar.add(Calendar.DATE, 1);
//            exp = new Expense("flowers for kitkat", -50.00,calendar.getTime());
//            act.AddExpense(exp);
//
//            calendar.add(Calendar.DATE, 1);
//            exp = new Expense("flowers for kitkat", 100.00,calendar.getTime());
//            act.AddExpense(exp);

            results.add(act);
        }
         if (i >= 2)
        {
            Calendar calendar = Calendar.getInstance();
            act = new Account("test 3", "mastercard", "blue", 400.00);
            Expense exp = new Expense("burger for kitkat", 100.00, calendar.getTime());
            act.AddExpense(exp);

            calendar.add(Calendar.DATE, 1);
            exp = new Expense("flowers for kitkat", 0.00,calendar.getTime());
            act.AddExpense(exp);

            results.add(act);
        }
        if (i >= 3)
        {
            Calendar calendar = Calendar.getInstance();
            act = new Account("test 3", "bank", "red", 200.99);
            Expense exp = new Expense("burger for kitkat", 50.00, calendar.getTime());
            act.AddExpense(exp);

            calendar.add(Calendar.DATE, 1);
            exp = new Expense("flowers for kitkat", 50.00, calendar.getTime());
            act.AddExpense(exp);

            results.add(act);
        }

        return results;
    }
}
