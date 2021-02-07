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
}
