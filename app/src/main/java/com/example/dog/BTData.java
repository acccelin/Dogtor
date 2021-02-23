package com.example.dog;

public class BTData {
    private String date;
    private String rate;


    public BTData(String date, String rate) {
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
