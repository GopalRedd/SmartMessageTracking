package com.example.dheeraj.smartmessagetracking;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;

/**
 * Created by GopalReddy on 4/20/2018.
 */

public  class Otp  extends Activity {


    EditText ev;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
ev = (EditText)findViewById(R.id.otp);


    }

    public void recivedSms(String message) {
        try
        {
            ev.setText(message);
        }
        catch (Exception e) {}




    }
}
