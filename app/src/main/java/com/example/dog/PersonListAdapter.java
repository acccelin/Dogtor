package com.example.dog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class PersonListAdapter extends ArrayAdapter<TestPerson> {
    private static final String TAG = "PersonListAdapter";

    private Context mContext;
    int mResource;


    public PersonListAdapter(Context context, int resource, ArrayList<TestPerson> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        String name = getItem(position).getName();
        String birthday= getItem(position).getBirthday();
        String sex = getItem(position).getSex();

        TestPerson testPerson = new TestPerson(name,birthday,sex);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource,parent,false);

        TextView tvName = (TextView) convertView.findViewById(R.id.test1);
        TextView tvBirthday = (TextView) convertView.findViewById(R.id.test2);
        TextView tvSex = (TextView) convertView.findViewById(R.id.test3);

        tvName.setText(name);
        tvBirthday.setText(birthday);
        tvSex.setText(sex);

        return convertView;

    }

}
