package com.example.dheeraj.smartmessagetracking;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        if (!connectionistheir()) {


            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("SmartMessageTracking \n Application Need Internet connection and Location ")

                    .setTitle("Warning")
                    .setCancelable(false)
                    .setPositiveButton("Turn Wifi on",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    /*Intent i = new Intent(Settings.ACTION_DATA_ROAMING_SETTINGS);
                                    startActivity(i);*/
                                    WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                                    wifiManager.setWifiEnabled(true);


                                }
                            }
                    )
                    .setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    MainActivity.this.finish();
                                }
                            }
                    );
            AlertDialog alert = builder.create();
            alert.show();


        }







        Thread Splash=new Thread(){
            public void run() {
                try {
                    sleep(3 * 1000);

                    Intent i = new Intent(MainActivity.this,LOGINME.class);
                    startActivity(i);
                    finish();
                } catch (Exception e) {
                }
            }
        };
        Splash.start();




    }

    private boolean connectionistheir() {
        ConnectivityManager connectivityManager=(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        return networkInfo!=null && networkInfo.isConnected();

    }
}