package com.example.dog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Selection extends AppCompatActivity {

    private ImageButton buttonAbout;
    private ImageButton buttonCtrl;
    private ImageButton buttonVitals;
    private ImageButton buttonTest;
    private Button buttonBlueTooth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        buttonAbout = (ImageButton) findViewById(R.id.imageButtonAbout);
        buttonAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAbout();
            }
        });

        buttonCtrl = (ImageButton) findViewById(R.id.imageButtonRemote);
        buttonCtrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openControls();
            }
        });

        buttonVitals = (ImageButton) findViewById(R.id.imageButtonRecord);
        buttonVitals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openVitals();
            }
        });

        // Click on the microphone, it goes to Bluetooth, I let Qing decide.
        buttonTest = (ImageButton) findViewById(R.id.imageButtonVoice);
        buttonTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSpeech();
            }
        });

        buttonBlueTooth = (Button) findViewById(R.id.buttonBlueTooth);
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
