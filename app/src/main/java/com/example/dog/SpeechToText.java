package com.example.dog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class SpeechToText extends AppCompatActivity {

    private TextView Output;
    private TextView txtBoolean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech_to_text);
        Output = (TextView) findViewById(R.id.Output);
        txtBoolean = (TextView) findViewById(R.id.textViewBoolean);
    }

    public void getSpeechInput(View view){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault()); //gets default language of the device

        if (intent.resolveActivity(getPackageManager())!=null){
            startActivityForResult(intent,10);
        }else {
            Toast.makeText(this,"Your device does not support speech input.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        switch (requestCode){
            case 10:
                if (resultCode == RESULT_OK && data != null){
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    Output.setText(result.get(0));

                    if (result.get(0).equals("go to room one")){
                        txtBoolean.setText("Dogtor is going to room 1." );
                    }
                    else if (result.get(0).equals("go to room two")){
                        txtBoolean.setText("Dogtor is going to room 2." );
                    }
                    else if (result.get(0).equals("read my body temperature")) {
                        txtBoolean.setText("Dogtor is reading the body temperature.");
                    }
                    else if (result.get(0).equals("read my heart rate")){
                        txtBoolean.setText("Dogtor is taking your heart rate.");
                    }

                    else {
                        txtBoolean.setText("Invalid command.");
                    }
                }
                break;
        }
    }

}
