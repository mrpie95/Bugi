package com.example.michael.test2.Java;

import android.app.Activity;

import com.example.michael.test2.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael on 29-Sep-16.
 */
public class tests
{
//
//    public static Account TestAccount()
//    {
//        Account act = new Account("Bank 1", "Card",333);
//
//        return act;
//    }

    public static void graphTest(Activity activity)
    {
        GraphView graph = (GraphView) activity.findViewById(R.id.graph2);

//        graph.setBackgroundColor();


        List<DataPoint> test2 = new ArrayList<DataPoint>();
        List<DataPoint> test3 = new ArrayList<DataPoint>();

        int i;

        for(i=0;i<1000;i++)
        {
            test2.add(new DataPoint((i* Math.PI)/180, Math.sin(i* Math.PI/180) ));
        }

        for(i=0;i<1000;i++)
        {
            test3.add(new DataPoint((i* Math.PI)/180, Math.cos(i* Math.PI/180) ));
        }

        DataPoint[] test = test2.toArray(new DataPoint[test2.size()]);

//        DataPoint[] test = new DataPoint[]
//                {
//                    new DataPoint(0, 400),
//                    new DataPoint(1, 398),
//                    new DataPoint(2, 360),
//                    new DataPoint(3, 300),
//                    new DataPoint(4, 310)
//                };

        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(test);

        test = test3.toArray(new DataPoint[test3.size()]);

        LineGraphSeries<DataPoint> series2 = new LineGraphSeries<DataPoint>(test);

//        series3.setColor(FFFF);

        series2.setThickness(15);
        graph.addSeries(series);
        graph.addSeries(series2);

//        Viewport plot = graph.getViewport();
//////
////        plot.setYAxisBoundsManual(true);
////        plot.setMinY(0.0);
//////      plot.setMaxY(350);
//
//
//        plot.setScrollable(true);
    }
}
