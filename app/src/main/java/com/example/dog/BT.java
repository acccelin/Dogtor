package com.example.dog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

public class BT extends AppCompatActivity {

    private static final String TAG = "BodyTemp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bt);

        Log.d(TAG, "onCreate: Started.");
        ListView mListView = (ListView) findViewById(R.id.listView);

        //creating random nonsense
        BTData john = new BTData("02/02/2021","37");
        BTData john1 = new BTData("02/03/2021","35");
        BTData john2 = new BTData("02/08/2021","38");
        BTData john3 = new BTData("02/07/08","40");
        BTData john4 = new BTData("02/10/2021","37");
        BTData john5 = new BTData("02/12/2021","37");
        BTData john6 = new BTData("02/14/2021","37");
        BTData john7 = new BTData("02/15/2021","37");
        BTData john8 = new BTData("02/16/2021","37");
        BTData john9 = new BTData("02/17/2021","36");
        BTData john11 = new BTData("02/19/2021","37");
        BTData john12 = new BTData("02/20/2021","37");
        BTData john13 = new BTData("02/21/2021","37");
        BTData john14 = new BTData("02/22/2021","37");
        BTData john15 = new BTData("02/23/2021","36");


        ArrayList<BTData> Temperaturelist = new ArrayList<>();
        Temperaturelist.add(john);
        Temperaturelist.add(john1);
        Temperaturelist.add(john2);
        Temperaturelist.add(john3);
        Temperaturelist.add(john4);
        Temperaturelist.add(john5);
        Temperaturelist.add(john6);
        Temperaturelist.add(john7);
        Temperaturelist.add(john8);
        Temperaturelist.add(john9);
        Temperaturelist.add(john11);
        Temperaturelist.add(john12);
        Temperaturelist.add(john13);
        Temperaturelist.add(john14);
        Temperaturelist.add(john15);


        BTListAdapter adapter = new BTListAdapter(this,R.layout.adapter_view_layout,Temperaturelist);
        mListView.setAdapter(adapter);

    }
}
