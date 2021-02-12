package com.example.dog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Person;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test extends AppCompatActivity {

    private static final String TAG = "Test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Log.d(TAG, "onCreate: Started.");
        ListView mListView = (ListView) findViewById(R.id.listView);

        //creating random nonsense
        TestPerson john = new TestPerson("John","12-20-1998" ,"Male");
        TestPerson john1 = new TestPerson("John1","12-20-1998" ,"Male");
        TestPerson john2 = new TestPerson("John2","12-20-1998" ,"Male");
        TestPerson john3 = new TestPerson("John3","12-20-1998" ,"Male");
        TestPerson john4 = new TestPerson("John4","12-20-1998" ,"Male");
        TestPerson john5 = new TestPerson("John5","12-20-1998" ,"Male");
        TestPerson john6 = new TestPerson("John6","12-20-1998" ,"Male");
        TestPerson john7 = new TestPerson("John7","12-20-1998" ,"Male");
        TestPerson john8 = new TestPerson("John8","12-20-1998" ,"Male");
        TestPerson john9 = new TestPerson("John9","12-20-1998" ,"Male");
        TestPerson john11 = new TestPerson("John11","12-20-1998" ,"Male");
        TestPerson john12 = new TestPerson("John12","12-20-1998" ,"Male");
        TestPerson john13 = new TestPerson("John13","12-20-1998" ,"Male");
        TestPerson john14 = new TestPerson("John14","12-20-1998" ,"Male");
        TestPerson john15 = new TestPerson("John15","12-20-1998" ,"Male");


        ArrayList<TestPerson> peoplelist = new ArrayList<>();
        peoplelist.add(john);
        peoplelist.add(john1);
        peoplelist.add(john2);
        peoplelist.add(john3);
        peoplelist.add(john4);
        peoplelist.add(john5);
        peoplelist.add(john6);
        peoplelist.add(john7);
        peoplelist.add(john8);
        peoplelist.add(john9);
        peoplelist.add(john11);
        peoplelist.add(john12);
        peoplelist.add(john13);
        peoplelist.add(john14);
        peoplelist.add(john15);


        PersonListAdapter adapter = new PersonListAdapter(this,R.layout.adapter_view_layout,peoplelist);
        mListView.setAdapter(adapter);

    }
}
