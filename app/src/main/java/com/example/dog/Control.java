package com.example.dog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

public class Control extends AppCompatActivity {

    Switch Switch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);

        Switch = findViewById(R.id.switch_auto);

        //save switch state
        SharedPreferences sharedPreferences = getSharedPreferences("save", MODE_PRIVATE);
        Switch.setChecked(sharedPreferences.getBoolean("value",true));

        Switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Switch.isChecked()){
                    //When switch is checked
                    SharedPreferences.Editor editor = getSharedPreferences("save", MODE_PRIVATE).edit();
                    editor.putBoolean("value",true);
                    editor.apply();
                    Switch.setChecked(true);
                }
                else{
                    //When switch unchecked
                    SharedPreferences.Editor editor = getSharedPreferences("save", MODE_PRIVATE).edit();
                    editor.putBoolean("value", false);
                    editor.apply();
                    Switch.setChecked(false);
                }
            }
        });

    }
}
