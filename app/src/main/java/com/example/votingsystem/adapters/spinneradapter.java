package com.example.votingsystem.adapters;

import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.example.votingsystem.R;
import com.example.votingsystem.module.voteModule;

import java.util.List;

public class spinneradapter extends BaseAdapter {


    List<voteModule>data;

    public spinneradapter(List<voteModule>data)
    {
        this.data=data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).getIndex();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.spineradapter,parent,false);

        TextView textView=convertView.findViewById(R.id.spin);

        textView.setText(data.get(position).getCandi());

        return convertView;
    }
}
