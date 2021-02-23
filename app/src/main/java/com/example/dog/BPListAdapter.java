package com.example.dog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class BPListAdapter extends ArrayAdapter<BPData> {

    private static final String TAG = "PersonListAdapter";

    private Context mContext;
    int mResource;


    public BPListAdapter(Context context, int resource, ArrayList<BPData> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        String date = getItem(position).getDate();
        String rate= getItem(position).getRate();

        BPData bpData = new BPData(date,rate);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource,parent,false);

        TextView tvDate = (TextView) convertView.findViewById(R.id.test1);
        TextView tvRate = (TextView) convertView.findViewById(R.id.test2);

        tvDate.setText(date);
        tvRate.setText(rate);

        return convertView;

    }
}
