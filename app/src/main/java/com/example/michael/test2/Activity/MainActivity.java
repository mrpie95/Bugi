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
import com.example.michael.test2.Fragments.CircleLoadingFragment;
import com.example.michael.test2.Fragments.graphFragment;
import com.example.michael.test2.Java.Account;
import com.example.michael.test2.Java.Expense;
import com.example.michael.test2.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    AccountListFragment list;
    ArrayList<Account> accounts = new ArrayList<Account>();
    ArrayList<Account> test1;
    boolean loaded = false;
    boolean test = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("accounts");

//        Account act = new Account("name", "test", "test", 0.0);


//        myRef.setValue("i  love kotek","i  love kotek");

        intialiseApp();

        // myRef.setValue(accounts);


//        FirebaseDatabase test = myRef.getDatabase()

        myRef.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                if (!test)
                {
                    GenericTypeIndicator<ArrayList<Account>> t = new GenericTypeIndicator<ArrayList<Account>>()
                    {
                    };
                    test1 = dataSnapshot.getValue(t);

                    if (!test).

                    updateAccountList();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {

            }
        });


        myRef.addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                GenericTypeIndicator<ArrayList<Account>> t = new GenericTypeIndicator<ArrayList<Account>>() {};
                test1 = dataSnapshot.getValue(t);
                loaded = true;

                if (!test)
                updateAccountList();
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {

            }
        });


    }

    @Override
    public void onPause()
    {
        super.onPause();

        DatabaseReference myRef;
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("accounts");
        test = true;

        if (loaded)
        {
            myRef.setValue(test1);
        }

        database.purgeOutstandingWrites();
        myRef.goOffline();

    }


    @Override
    public void onBackPressed()
    {
//        this.finishAffinity();
        // super.onBackPressed(); // Comment this super call to avoid calling finish()
        if (getFragmentManager().getBackStackEntryCount() == 0)
        {
            this.finish();
        } else {
            getFragmentManager().popBackStack();
        }
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

                return true;

            case R.id.btn2:
                test1.remove(test1.size()-1);
                updateAccountList();
                return true;

            case R.id.test_item:
                test1.get(0).getExpenses().remove(test1.get(0).getExpenses().size()-1);
                updateAccountList();
                return true;

            default:
                super.onOptionsItemSelected(item);

        }
        return false;
    }

    public void AccuontDetails()
    {
//        Intent intent = new Intent(this, AccountDetailActivity.class);
//        EditText editText = (EditText) findViewById(R.id.edit_message);
//        String message = editText.getText().toString();

//        intent.putExtra("ACCOUNT_DATA", account);
//        startActivity(intent);
    }


    //update the list to display all current account and
    //display the correseponding graphs
    public void updateAccountList()
    {
        Bundle bundle = new Bundle();

        if (test1 != null)
            bundle.putParcelableArrayList("accounts1", test1);

        graphFragment graph = new graphFragment();
        if (test1 != null)
            graph.setArguments(bundle);
        FragmentManager fragManager = getFragmentManager();
        FragmentTransaction tranView1 = fragManager.beginTransaction();
        tranView1.replace(R.id.view1, graph);
        tranView1.commit();

        if (loaded)
        {
            list = new AccountListFragment();
            if (test1 != null)
                list.setArguments(bundle);

            fragManager = getFragmentManager();
            FragmentTransaction tranView2 = fragManager.beginTransaction();
            tranView2.replace(R.id.view2, list);
            //tranView2.addToBackStack(null);
            tranView2.commit();
        }
        else
        {
            CircleLoadingFragment loading = new CircleLoadingFragment();
            fragManager = getFragmentManager();
            FragmentTransaction tranView2 = fragManager.beginTransaction();
            tranView2.replace(R.id.view2, loading);
            tranView2.commit();
        }


    }

    public void addAccount(Account act)
    {
        if (test1 == null)
            test1 = new ArrayList<Account>();

        test1.add(act);
        updateAccountList();
    }

//    public void replaceAccount(Account act, int pos)
//    {
//        //accounts.set(pos, act);
//        updateAccountList();
//    }

    private void intialiseApp()
    {

        updateAccountList();
        //**** needs fixing ****
        ActionBar actionBar = getActionBar();
        if (actionBar != null)
        {
            actionBar.setBackgroundDrawable(new ColorDrawable(Color.rgb(0, 128, 255)));
            //actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setTitle("Bugi");
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabSummary);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("accounts1", test1);

                FragmentManager fm = getFragmentManager();
                AddExpenseFragment dialogFragment = new AddExpenseFragment ();
                dialogFragment.setArguments(bundle);

                dialogFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.CustomDialog);
                dialogFragment.show(fm, "");
            }
        });
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
