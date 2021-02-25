package com.example.dog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Selection extends AppCompatActivity {

    private Button buttonAbout;
    private Button buttonCtrl;
    private Button buttonVitals;
    private Button buttonTest;
    private Button buttonBlueTooth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        buttonAbout = (Button) findViewById(R.id.buttonAbout);
        buttonAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAbout();
            }
        });

        buttonCtrl = (Button) findViewById(R.id.buttonControls);
        buttonCtrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openControls();
            }
        });

        buttonVitals = (Button) findViewById(R.id.buttonVitals);
        buttonVitals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openVitals();
            }
        });

        buttonTest = (Button) findViewById(R.id.buttontest);
        buttonTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSpeech();
            }
        });

        buttonTest = (Button) findViewById(R.id.buttonBlueTooth);
        buttonTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBlueTooth();
            }
        });
    }

    public void openAbout(){
        Intent intent = new Intent(this,About.class);
        startActivity(intent);
    }

    public void openControls(){
        Intent intent = new Intent(this,Control.class);
        startActivity(intent);
    }

    public void openVitals(){
        Intent intent = new Intent(this,Vitals.class);
        startActivity(intent);
    }

    public void openSpeech(){
        Intent intent = new Intent(this,SpeechToText.class);
        startActivity(intent);
    }

    public void openBlueTooth(){
        Intent intent = new Intent(this,Bluetooth.class);
        startActivity(intent);
    }

}
