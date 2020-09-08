package com.example.votingsystem;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Loadingfragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Loadingfragment extends DialogFragment {

    // TODO: Rename parameter arguments, choose names that match


    public Loadingfragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static Loadingfragment newInstance() {
        Loadingfragment fragment = new Loadingfragment();
           fragment.setCancelable(false);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    // return loading screen on voting
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_loadingfragment, container, false);
    }
}