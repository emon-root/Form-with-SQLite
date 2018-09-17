package com.imrannazir.demo_project;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class DataAdapter extends BaseAdapter {
    private ArrayList<PersonData> listdata;
    private Activity activity;
    private LayoutInflater inflater;

    public DataAdapter(ArrayList<PersonData> listdata, Activity activity) {
        this.listdata = listdata;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return listdata.size();
    }

    @Override
    public Object getItem(int position) {
        return listdata.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(inflater==null)
            inflater=(LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView==null)
            view=inflater.inflate(R.layout.data_row,null);
        TextView name= (TextView) view.findViewById(R.id.name_row);
        TextView phone= (TextView)view.findViewById(R.id.phone_row);
        TextView email= (TextView) view.findViewById(R.id.email_row);

        PersonData dataModel=listdata.get(position);
        name.setText(dataModel.getName());
        email.setText(dataModel.getEmail());
        phone.setText(dataModel.getPhone());

        return view;
    }
}