package com.example.votingsystem;

import android.app.FragmentManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;


/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class fullinfo extends DialogFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String name;




    public static fullinfo newInstance1(String name) {
        fullinfo fragment = new fullinfo();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, name);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            name = getArguments().getString(ARG_PARAM1);
        }



    }

    @Override
    public void onStart() {
        super.onStart();

        // make screen same size divice
        if(getDialog()!=null)
        {
            getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        }
    }

    // retrun all info related to candicator
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment_fullinfo, container, false);

        TextView canname=view.findViewById(R.id.infoname);
        TextView city=view.findViewById(R.id.infoCity);
        TextView degree=view.findViewById(R.id.infoDegree);
        TextView job=view.findViewById(R.id.infoJob);
        TextView party=view.findViewById(R.id.infoParty);
        TextView plan=view.findViewById(R.id.infoPlan);


        // get canticator info from fire base
            Query query= FirebaseFirestore.getInstance().collection("candicates");

            query.whereEqualTo("name",name).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                    if(queryDocumentSnapshots.size()<=0)
                    {
                        return;
                    }

                    if(  queryDocumentSnapshots.getDocuments().get(0).exists())
                    {

                        canname.setText(queryDocumentSnapshots.getDocuments().get(0).get("name").toString());
                        degree.setText(queryDocumentSnapshots.getDocuments().get(0).get("degree").toString());
                        plan.setText(queryDocumentSnapshots.getDocuments().get(0).get("plan").toString());
                        city.setText(queryDocumentSnapshots.getDocuments().get(0).get("city").toString());
                        party.setText(queryDocumentSnapshots.getDocuments().get(0).get("party").toString());
                        job.setText(queryDocumentSnapshots.getDocuments().get(0).get("job").toString());




                    }


                }
            });

        Button close=view.findViewById(R.id.close);

        close.setOnClickListener(v->{dismiss();});

        return view;
    }

    @Override
    public void show(@NonNull androidx.fragment.app.FragmentManager manager, @Nullable String tag) {
        super.show(manager, tag);
    }


}
