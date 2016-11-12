package com.example.michael.test2.Fragments;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.michael.test2.Java.Account;
import com.example.michael.test2.Java.Expense;
import com.example.michael.test2.R;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class graphSingleFragment extends Fragment
{
    private GraphView graphView;
    private ArrayList<Account> accounts;

    public graphSingleFragment()
    {

    }

    @Override
    public  void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_graph, container, false);
        graphView = (GraphView)view.findViewById(R.id.graph2);

        accounts = new ArrayList<Account>();

        ArrayList<Account> act = getArguments().getParcelableArrayList("accounts1");

        //checks if there is an account(s)
        if (act.size() > 0)
        {
            for (Account a : act)
            {
                //checks if an account has expenses
                if (a.getExpenses().size() > 0)
                    accounts.add(a);
            }
            if (accounts.size() > 0)
                DrawGraph();
        }
        else
        {
            graphView.removeAllSeries();
        }

        return view;
    }

    public void DrawGraph()
    {
        Account act;
        ArrayList<Expense> exp;

        double xmax = 0;
        double xmin = 0;
        double ymax = 0;
        double ymin = 0;

        for (int j = 0; j < accounts.size(); j++)
        {
            act = accounts.get(j);
            exp = act.getExpenses();
            xmin = exp.get(0).getDate().getTime();

            graphView.removeAllSeries();

            DataPoint[] data = new DataPoint[exp.size()];

            Double totalBalance = act.getStartingBalance();
            //sets the x and y points for each expense
            for (int i = 0; i < exp.size(); i++)
            {
                totalBalance += exp.get(i).getCost();
                double time = exp.get(i).getDate().getTime();

                if (time < xmin)
                    xmin =  time;

                if (time > xmax)
                    xmax = time;

//                if (totalBalance < ymin)
//                    ymin = totalBalance;
                if (totalBalance > ymax)
                    ymax = totalBalance;

                data[i] = new DataPoint(exp.get(i).getDate(), totalBalance);

            }


//            if ((exp.get(i).getDate().getTime()) > xmax)


//            xmax = exp.get(exp.size()-1).getDate().getTime();



            LineGraphSeries<DataPoint> series = new LineGraphSeries<>(data);

            series.setColor(act.getColourInt());
            series.setThickness(15);

            graphView.addSeries(series);
        }

        graphView.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter()
        {
            @Override
            public String formatLabel(double value, boolean isValueX) {

                if (isValueX) {
                    //x axis date formatting
                    String formatDate =  "dd/MM";
                    SimpleDateFormat formatter = new SimpleDateFormat(formatDate);

                    // Create a calendar object that will convert the date and time value in
                    // milliseconds to date.
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis((long)value);
                    String result = formatter.format(calendar.getTime());
                    return result;
                } else {
                    // show currency for y values
                    return "$" + super.formatLabel(value, isValueX);
                }
            }
        });

        graphView.setTitle("Summary");
        graphView.getGridLabelRenderer().setNumHorizontalLabels(5); // only 4 because of the space
        graphView.getGridLabelRenderer().setHumanRounding(false);
        graphView.getGridLabelRenderer().setLabelsSpace(3);
        graphView.getGridLabelRenderer().setGridStyle(GridLabelRenderer.GridStyle.BOTH);

        graphView.getViewport().setMaxY(ymax);
        graphView.getViewport().setMinY(0.0);

        graphView.getViewport().setMaxX(xmax);
        graphView.getViewport().setXAxisBoundsManual(true);
        graphView.getGridLabelRenderer().setHumanRounding(false);

        GridLabelRenderer label =  graphView.getGridLabelRenderer();
        label.setGridColor(Color.BLACK);
        label.setVerticalLabelsColor(Color.BLACK);
        label.setHorizontalLabelsColor(Color.BLACK);
    }
}


