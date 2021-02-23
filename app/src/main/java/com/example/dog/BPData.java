package com.example.dog;

public class BPData {
    private String date;
    private String rate;


    public BPData(String date, String rate) {
        this.date = date;
        this.rate = rate;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate= rate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
