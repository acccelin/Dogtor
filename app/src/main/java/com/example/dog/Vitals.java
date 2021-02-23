package com.example.dog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Vitals extends AppCompatActivity {

    private Button buttonHR;
    private Button buttonBP;
    private Button buttonBT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vitals);

        buttonHR = (Button) findViewById(R.id.buttonHR);
        buttonHR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHR();
            }
        });

        buttonBP = (Button) findViewById(R.id.buttonBP);
        buttonBP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBP();
            }
        });

        buttonBT = (Button) findViewById(R.id.buttonBT);
        buttonBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBT();
            }
        });

    }

    public void openHR(){
        Intent intent = new Intent(this,HeartRate.class);
        startActivity(intent);
    }

    public void openBP(){
        Intent intent = new Intent(this,BP.class);
        startActivity(intent);
    }

    public void openBT(){
        Intent intent = new Intent(this,BT.class);
        startActivity(intent);
    }
}
