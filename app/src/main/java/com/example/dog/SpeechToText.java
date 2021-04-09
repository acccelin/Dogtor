package com.example.dog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.UUID;

public class SpeechToText extends AppCompatActivity {

    private TextView Output;
    private TextView txtBoolean;

    private final UUID PORT_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");//Serial Port Service ID
    private BluetoothDevice device;
    private BluetoothSocket socket;
    private OutputStream outputStream;
    private InputStream inputStream;
    private boolean bluetoothOn = false;
    private String macAddress = "98:D3:31:F9:58:E7";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech_to_text);
        Output = (TextView) findViewById(R.id.Output);
        txtBoolean = (TextView) findViewById(R.id.textViewBoolean);

        socket = BluetoothStatus.getBtSocket();
        if (socket != null && socket.isConnected()) {
            Toast.makeText(getApplicationContext(), " Connection Opened!", Toast.LENGTH_SHORT).show();
            bluetoothOn = true;
        } else {
            if (BTinit()) {
                if (BTconnect()) {
                    Toast.makeText(getApplicationContext(), " Connection Opened!", Toast.LENGTH_SHORT).show();
                    bluetoothOn = true;
                } else {
                    Toast.makeText(getApplicationContext(), "Bluetooth connection lost", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(getApplicationContext(), "Bluetooth device failed", Toast.LENGTH_SHORT).show();
            }
        }
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

                    if (result.get(0).equals("move forward")){
                        txtBoolean.setText("Dogtor is moving forward." );
                        if (bluetoothOn) {
                            msgSending("s");
                            Toast.makeText(getApplicationContext(), " Forward", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else if (result.get(0).equals("move backward")){
                        txtBoolean.setText("Dogtor is moving backward." );
                        if (bluetoothOn) {
                            msgSending("b");
                            Toast.makeText(getApplicationContext(), " backward", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else if (result.get(0).equals("turn right")) {
                        txtBoolean.setText("Dogtor is turning right.");
                        if (bluetoothOn) {
                            msgSending("r");
                            Toast.makeText(getApplicationContext(), " Turn right", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else if (result.get(0).equals("turn left")){
                        txtBoolean.setText("Dogtor is turning left.");
                        if (bluetoothOn) {
                            msgSending("l");
                            Toast.makeText(getApplicationContext(), "Turn left", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else if (result.get(0).equals("stop")) {
                        txtBoolean.setText("Dogtor stopped.");
                        if (bluetoothOn) {
                            msgSending("e");
                            Toast.makeText(getApplicationContext(), " Stop", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else if (result.get(0).equals("automation")){
                        txtBoolean.setText("Turn on automation mode.");
                        if (bluetoothOn) {
                            msgSending("p");
                            Toast.makeText(getApplicationContext(), " Automation", Toast.LENGTH_SHORT).show();
                        }
                    }

                    else {
                        txtBoolean.setText("Invalid command.");
                    }
                }
                break;
        }
    }


    public boolean BTinit() {
        boolean found = true;
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null) {
            Toast.makeText(getApplicationContext(), "Device doesnt Support Bluetooth", Toast.LENGTH_SHORT).show();
        }
        if (!bluetoothAdapter.isEnabled()) {
            Intent enableAdapter = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableAdapter, 0);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        device = bluetoothAdapter.getRemoteDevice(macAddress);
        return found;
    }

    public boolean BTconnect() {
        boolean connected = true;
        try {
            socket = BluetoothStatus.getBtSocket();
            if (socket == null) {
                socket = device.createRfcommSocketToServiceRecord(PORT_UUID);
                socket = (BluetoothSocket) device.getClass().getMethod("createRfcommSocket", new Class[]{int.class}).invoke(device, 1);
            }

            if (!socket.isConnected()) {
                socket.connect();
                BluetoothStatus.setBtSocket(socket);
            }
        } catch (IOException | NoSuchMethodException e) {
            e.printStackTrace();
            connected = false;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            connected = false;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            connected = false;
        }
        if (connected) {
            try {
                outputStream = socket.getOutputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                inputStream = socket.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


        return connected;
    }

    private void msgSending(String co) {
        try {
            outputStream.write(co.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
