package com.example.michael.test2.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.michael.test2.R;
import com.jjoe64.graphview.GraphView;

public class accountDetailsFragment extends Fragment
{
    private GraphView graphView;

    public accountDetailsFragment()
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


        return view;
    }



}


