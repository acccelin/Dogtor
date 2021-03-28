package com.example.dog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Vitals extends AppCompatActivity {

    private ImageButton imageButtonHR;
    private Button buttonBP;
    private ImageButton imageButtonBT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vitals);

        imageButtonHR = (ImageButton) findViewById(R.id.imageButtonVitalHeart);
        imageButtonHR.setOnClickListener(new View.OnClickListener() {
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

        imageButtonBT = (ImageButton) findViewById(R.id.imageButtonVitalTemp);
        imageButtonBT.setOnClickListener(new View.OnClickListener() {
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
