package com.example.dog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

public class HeartRate extends AppCompatActivity {

    private static final String TAG = "HeartRate";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart_rate);
        Log.d(TAG, "onCreate: Started.");
        ListView mListView = (ListView) findViewById(R.id.listView);

        //creating random nonsense
        HeartRateData john = new HeartRateData("02/02/2021","100");
        HeartRateData john1 = new HeartRateData("02/03/2021","101");
        HeartRateData john2 = new HeartRateData("02/08/2021","90");
        HeartRateData john3 = new HeartRateData("02/07/08","99");
        HeartRateData john4 = new HeartRateData("02/10/2021","98");
        HeartRateData john5 = new HeartRateData("02/12/2021","80");
        HeartRateData john6 = new HeartRateData("02/14/2021","101");
        HeartRateData john7 = new HeartRateData("02/15/2021","89");
        HeartRateData john8 = new HeartRateData("02/16/2021","88");
        HeartRateData john9 = new HeartRateData("02/17/2021","98");
        HeartRateData john11 = new HeartRateData("02/19/2021","120");
        HeartRateData john12 = new HeartRateData("02/20/2021","117");
        HeartRateData john13 = new HeartRateData("02/21/2021","124");
        HeartRateData john14 = new HeartRateData("02/22/2021","111");
        HeartRateData john15 = new HeartRateData("02/23/2021","98");


        ArrayList<HeartRateData> ratelist = new ArrayList<>();
        ratelist.add(john);
        ratelist.add(john1);
        ratelist.add(john2);
        ratelist.add(john3);
        ratelist.add(john4);
        ratelist.add(john5);
        ratelist.add(john6);
        ratelist.add(john7);
        ratelist.add(john8);
        ratelist.add(john9);
        ratelist.add(john11);
        ratelist.add(john12);
        ratelist.add(john13);
        ratelist.add(john14);
        ratelist.add(john15);


        HeartRateListAdapter adapter = new HeartRateListAdapter(this,R.layout.adapter_view_layout,ratelist);
        mListView.setAdapter(adapter);

    }
}
