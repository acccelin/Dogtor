package com.example.dog;


import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

import static java.lang.Thread.sleep;

public class Bluetooth extends AppCompatActivity {

    Button btnPaired;
    ListView devicelist;

    private BluetoothAdapter myBluetooth = null;
    private Set<BluetoothDevice> pairedDevices;
    public static String EXTRA_ADDRESS = "device_address";
    private ProgressDialog progress;
    BluetoothSocket btSocket;
    private boolean isBtConnected = false;
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private InputStream inputStream;
    private OutputStream outputStream;
    byte buffer[];
    boolean stopThread;
    boolean isDataSending=false;


    String address = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blue_tooth);

        btnPaired = findViewById(R.id.button);
        devicelist = findViewById(R.id.listView);

        myBluetooth = BluetoothAdapter.getDefaultAdapter();
        if (myBluetooth == null) {
            Toast.makeText(getApplicationContext(), "Bluetooth device not available", Toast.LENGTH_LONG).show();
            finish();
        } else if (!myBluetooth.isEnabled()) {
            Intent turnBTon = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(turnBTon, 1);
        }

        btnPaired.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pairedDevicesList();
            }
        });
    }

    private void pairedDevicesList() {
        pairedDevices = myBluetooth.getBondedDevices();
        ArrayList list = new ArrayList();

        if (pairedDevices.size() > 0) {
            for (BluetoothDevice bt : pairedDevices) {
                list.add(bt.getName().toString() + "\n" + bt.getAddress().toString());
            }
        } else {
            Toast.makeText(getApplicationContext(), "No Paired Bluetooth Devices Found.", Toast.LENGTH_LONG).show();
        }

        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        devicelist.setAdapter(adapter);
        devicelist.setOnItemClickListener(myListClickListener);
    }

    private AdapterView.OnItemClickListener myListClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String info = ((TextView) view).getText().toString();
            address = info.substring(info.length() - 17);
            gotoTesting(address);
            /*try {
                BluetoothDevice dispositivo = myBluetooth.getRemoteDevice(address);
                btSocket = dispositivo.createInsecureRfcommSocketToServiceRecord(myUUID);
                btSocket =(BluetoothSocket) dispositivo.getClass().getMethod("createRfcommSocket", new Class[] {int.class}).invoke(dispositivo,1);

                if (btSocket == null) {
                    Toast.makeText(getApplicationContext(), "Socket Problem.", Toast.LENGTH_LONG).show();
                } else if (!btSocket.isConnected()) {
                    btSocket.connect();
                    //Toast.makeText(getApplicationContext(), "Socket Connection Problem.", Toast.LENGTH_LONG).show();
                }
                if (btSocket != null && btSocket.isConnected()) {



                    btSocket.getOutputStream().write(new String("1").getBytes());
                    Toast.makeText(getApplicationContext(), "Sent 1.", Toast.LENGTH_LONG).show();
                    if(!isDataSending){
                    beginListenForData();}
                }


            } catch (Exception e) {
                e.printStackTrace();
            }*/
           /* if (!isBtConnected) {
                if (address != null && myBluetooth.isEnabled()) {
                    if (btSocket == null || !btSocket.isConnected()) {
                        new ConnectBT().execute();
                        BluetoothStatus.setBTconnection(true);
                        //sendingDatatest();
                    }
                }
            } else {
                try {
                    btSocket.getOutputStream().write(new String("1").getBytes());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }*/
            //Intent i = getIntent();
            //i.putExtra(EXTRA_ADDRESS, address);
            //startActivity(i);
        }
    };

    protected void gotoTesting(String macAddress) {
        Intent intent = new Intent(this, BluetoothTesting.class);
        intent.putExtra(EXTRA_ADDRESS, macAddress);
        startActivity(intent);
    }


    private class ConnectBT extends AsyncTask<Void, Void, Void> {
        private boolean ConnectSuccess = true;

        @Override
        protected void onPreExecute() {
            progress = ProgressDialog.show(Bluetooth.this, "Connecting...", "Please Wait!!!");
        }

        @Override
        protected Void doInBackground(Void... devices) {
            try {
                if (btSocket == null || !isBtConnected) {
                    myBluetooth = BluetoothAdapter.getDefaultAdapter();
                    BluetoothDevice dispositivo = myBluetooth.getRemoteDevice(address);
                    btSocket = dispositivo.createInsecureRfcommSocketToServiceRecord(myUUID);
                    BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                    btSocket.connect();
                }
            } catch (IOException e) {
                ConnectSuccess = false;
            }
            if (ConnectSuccess) {

                BluetoothStatus.setBtSocket(btSocket);
                try {
                    inputStream = btSocket.getInputStream();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            if (!ConnectSuccess) {
                msg("Connection Failed. Is it a SPP Bluetooth? Try again.");
                finish();
            } else {
                msg("Connected");
                isBtConnected = true;
            }

            progress.dismiss();
        }


    }
    private void msg(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
    }

    void sendingDatatest() {
        Thread thread = new Thread(new Runnable() {

            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        btSocket.getOutputStream().write(new String("1").getBytes());
                        sleep(5000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        });

        thread.start();
    }
    void beginListenForData()
    {
        isDataSending=true;
        final Handler handler = new Handler();
        stopThread = false;
        buffer = new byte[1024];
        Thread thread  = new Thread(new Runnable()
        {
            public void run()
            {
                while(!Thread.currentThread().isInterrupted() && !stopThread)
                {
                    try
                    {
                        int byteCount = btSocket.getInputStream().available();
                        if(byteCount > 0)
                        {
                            byte[] rawBytes = new byte[byteCount];
                            btSocket.getInputStream().read(rawBytes);
                            final String string=new String(rawBytes,"UTF-8");
                            handler.post(new Runnable() {
                                public void run()
                                {
                                    try {
                                        sleep(5000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    msg(string);

                                }
                            });

                        }
                    }
                    catch (IOException ex)
                    {
                        stopThread = true;
                    }
                }
            }
        });

        thread.start();
    }}
