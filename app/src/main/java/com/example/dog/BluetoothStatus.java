package com.example.dog;

import android.bluetooth.BluetoothSocket;

public class BluetoothStatus {
    private static boolean BTconnection=false;
    private static boolean Socketstatus=false;
    private static boolean Datastatus=false;
    private static BluetoothSocket btSocket = null;

    public BluetoothStatus(){}

    public static void setBTconnection(boolean bTconnection)
    {BTconnection=bTconnection;}
    public static void setSocketstatus(boolean socketstatus)
    {
        Socketstatus=socketstatus;
    }
    public static void setDatastatus(boolean datastatus){
        Datastatus=datastatus;
    }

    public static void setBtSocket(BluetoothSocket iBtSocket){
        btSocket=iBtSocket;
    }

    public static BluetoothSocket getBtSocket() {
        return btSocket;
    }

    public static boolean isBTconnection() {
        return BTconnection;
    }

    public static boolean isDatastatus() {
        return Datastatus;
    }

    public static boolean isSocketstatus() {
        return Socketstatus;
    }
}
