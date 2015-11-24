package com.example.asus1.smsreader;

/**
 * Created by Asus1 on 24.11.2015.
 */
public class SMSData {

    private String number;
    private String body;

    public SMSData(String number, String body) {
        this.number = number;
        this.body = body;
    }


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

}
