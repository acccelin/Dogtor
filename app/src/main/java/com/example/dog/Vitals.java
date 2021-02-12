package com.example.dog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Vitals extends AppCompatActivity {

    private Button buttonHR;

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

    }

    public void openHR(){
        Intent intent = new Intent(this,Test.class);
        startActivity(intent);
    }
}
