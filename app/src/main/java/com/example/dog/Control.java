package com.example.dog;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

public class Control extends AppCompatActivity {

    private Button Switch;
    private Button buttonFore, buttonBack, buttonRight, buttonLeft, buttonStop;

    BluetoothAdapter myBluetooth = BluetoothAdapter.getDefaultAdapter();
    ;
    BluetoothSocket btSocket = BluetoothStatus.getBtSocket();
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
        setContentView(R.layout.activity_control);

        Switch = findViewById(R.id.switch_auto);

        buttonFore = findViewById(R.id.buttonFore);
        buttonBack = findViewById(R.id.buttonBack);
        buttonRight = findViewById(R.id.buttonRight);
        buttonLeft = findViewById(R.id.buttonLeft);
        buttonStop = findViewById(R.id.button);


        if (BTinit()) {
            if (BTconnect()) {
                Toast.makeText(getApplicationContext(), " Connection Opened!", Toast.LENGTH_SHORT).show();
                bluetoothOn = true;
            } else {
                Toast.makeText(getApplicationContext(), " error2", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(getApplicationContext(), " error1", Toast.LENGTH_SHORT).show();
        }

        buttonFore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bluetoothOn) {
                    msgSending("s");
                    Toast.makeText(getApplicationContext(), " Forward", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bluetoothOn) {
                    msgSending("b");
                    Toast.makeText(getApplicationContext(), " backward", Toast.LENGTH_SHORT).show();
                }
            }
        });
        buttonRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bluetoothOn) {
                    msgSending("r");
                    Toast.makeText(getApplicationContext(), " Turn right", Toast.LENGTH_SHORT).show();
                }
            }
        });
        buttonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bluetoothOn) {
                    msgSending("l");
                    Toast.makeText(getApplicationContext(), "Turn left", Toast.LENGTH_SHORT).show();
                }
            }
        });
        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bluetoothOn) {
                    msgSending("e");
                    Toast.makeText(getApplicationContext(), " Stop", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bluetoothOn) {
                    msgSending("p");
                    Toast.makeText(getApplicationContext(), " Automation", Toast.LENGTH_SHORT).show();
                }
            }
        });


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
