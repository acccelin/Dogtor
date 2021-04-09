package com.example.dog;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;



import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

public class BluetoothFragment extends DialogFragment {
    static final String Tag = "BluetoothFragment";

    protected Button buttonNext;
    protected  String macAddress="00:18:E4:35:35:88";
    public static String EXTRA_ADDRESS = "device_address";
    private BluetoothDevice device;

    //bluetooth
    String address = "98:D3:31:F9:58:E7";
    BluetoothAdapter myBluetooth = BluetoothAdapter.getDefaultAdapter();
    ;
    BluetoothSocket socket = BluetoothStatus.getBtSocket();
    private boolean isBtConnected = false;
    private boolean isDataSending = false;
    private InputStream inputStream;
    byte buffer[];
    boolean stopThread;
    private OutputStream outputStream;
    static final UUID PORT_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    public BluetoothFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bluetooth,container,false);

        Log.d(Tag, "onCreate()");



        buttonNext = view.findViewById(R.id.buttonNext);



        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                goToNextpage();
                // getDialog().dismiss();
            }
        });



        return view;
    }
    public void goToNextpage(){
        myBluetooth = BluetoothAdapter.getDefaultAdapter();
        if ( myBluetooth==null ) {
            Log.d(Tag, "Bluetooth device not available");
        } else if ( !myBluetooth.isEnabled() ) {

            Intent turnBTon = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(turnBTon, 1);
            /*while(!myBluetooth.isEnabled()){
                try
                {
                    Thread.sleep(1000);
                }
                catch(InterruptedException ex)
                {
                    Thread.currentThread().interrupt();
                }
                startActivityForResult(turnBTon, 1);
            }*/




        }
        if(myBluetooth.isEnabled()){
            Intent intent = new Intent(( getActivity()), Control.class);
            intent.putExtra(EXTRA_ADDRESS, macAddress);
            getContext().startActivity(intent);
        }
    }


    public boolean BTinit() {
        boolean found = true;
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null) {
            //Toast.makeText(getApplicationContext(), "Device doesnt Support Bluetooth", Toast.LENGTH_SHORT).show();
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

        device = bluetoothAdapter.getRemoteDevice(address);
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
}
