package com.example.votingsystem.adapters;

import android.app.Activity;
import android.app.ActivityManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.votingsystem.R;
import com.example.votingsystem.fullinfo;
import com.example.votingsystem.module.voteModule;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class voterRecAdapter extends RecyclerView.Adapter<voterRecAdapter.itemView> {


    List<voteModule>data;
    FragmentManager activity;
    boolean isadmin;
    boolean x=false;


    public voterRecAdapter(List<voteModule> data, FragmentManager supportFragmentManager, boolean isadmin) {

        this.data=data;
        this.activity=supportFragmentManager;
        this.isadmin=isadmin;
    }

    @NonNull
    @Override
    public itemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new itemView(LayoutInflater.from(parent.getContext()).inflate(R.layout.recvoderadapter,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull itemView holder, int position) {


        holder.itemView.setOnClickListener(v -> {


         DialogFragment dd= fullinfo.newInstance1(data.get(position).getCandi());
dd.show(activity,"ss");


        });


        holder.candicate.setText(data.get(position).getCandi()+"");
        holder.index.setText(data.get(position).getIndex()+"");
        holder.city.setText(data.get(position).getCity());

        votes(holder.votes,position);

    }

    private void votes(TextView votes,int Postion) {
        FirebaseFirestore.getInstance().collection("votingProcess").document("votingProcess").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
             boolean    x= (boolean) documentSnapshot.get("voting");

             if(isadmin )
             {
                 votes.setText(data.get(Postion).getVote()+"");
             }
             else {
                 if(x)
                 {
                     votes.setText("....");

                 }
                 else {
                     votes.setText(data.get(Postion).getVote()+"");

                 }
             }

            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class itemView extends RecyclerView.ViewHolder {

        TextView index,candicate,votes,city;

        public itemView(@NonNull View itemView) {
            super(itemView);

            index=itemView.findViewById(R.id.index);
            candicate=itemView.findViewById(R.id.candicate);
            votes=itemView.findViewById(R.id.votes);
            city=itemView.findViewById(R.id.city);


        }
    }
}
