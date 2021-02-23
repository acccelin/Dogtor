package com.example.dog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

public class BP extends AppCompatActivity {

    private static final String TAG = "BP";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bp);
        Log.d(TAG, "onCreate: Started.");
        ListView mListView = (ListView) findViewById(R.id.listView);


        //creating random nonsense
        BPData john = new BPData("02/02/2021","60");
        BPData john1 = new BPData("02/03/2021","151");
        BPData john2 = new BPData("02/08/2021","80");
        BPData john3 = new BPData("02/07/08","90000");
        BPData john4 = new BPData("02/10/2021","98");
        BPData john5 = new BPData("02/12/2021","80");
        BPData john6 = new BPData("02/14/2021","101");
        BPData john7 = new BPData("02/15/2021","89");
        BPData john8 = new BPData("02/16/2021","88");
        BPData john9 = new BPData("02/17/2021","98");
        BPData john11 = new BPData("02/19/2021","120");
        BPData john12 = new BPData("02/20/2021","117");
        BPData john13 = new BPData("02/21/2021","124");
        BPData john14 = new BPData("02/22/2021","111");
        BPData john15 = new BPData("02/23/2021","98");


        ArrayList<BPData> BPlist = new ArrayList<>();
        BPlist.add(john);
        BPlist.add(john1);
        BPlist.add(john2);
        BPlist.add(john3);
        BPlist.add(john4);
        BPlist.add(john5);
        BPlist.add(john6);
        BPlist.add(john7);
        BPlist.add(john8);
        BPlist.add(john9);
        BPlist.add(john11);
        BPlist.add(john12);
        BPlist.add(john13);
        BPlist.add(john14);
        BPlist.add(john15);


        BPListAdapter adapter = new BPListAdapter(this,R.layout.adapter_view_layout,BPlist);
        mListView.setAdapter(adapter);
    }
}
