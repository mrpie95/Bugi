package com.example.michael.test2.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.michael.test2.R;
import com.jjoe64.graphview.GraphView;

public class testFragment extends Fragment
{
    private GraphView graphView;

    public testFragment()
    {

    }

    @Override
    public  void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_test, container, false);





        // add the data here if necessary
        // ...

        //tests.graphTest(getActivity());

      //  LinearLayout layout = (LinearLayout) view.findViewById(R.id.graph2);

//        layout.addView(graphView);


        return view;
    }



}


