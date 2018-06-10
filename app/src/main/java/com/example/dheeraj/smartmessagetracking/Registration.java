package com.example.dheeraj.smartmessagetracking;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Registration extends AppCompatActivity {

    EditText name,emailid,moobilenumber,address;
    RadioGroup rg;
    RadioGroup m1,f1;
    Button signup;
    TextView tv;
    String hgender;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        name = (EditText)findViewById(R.id.fullnme);
        emailid = (EditText)findViewById(R.id.gmailid);
        moobilenumber =(EditText)findViewById(R.id.mobile);
        address =(EditText)findViewById(R.id.address);
        rg=(RadioGroup)findViewById(R.id.radio_group);
        signup = (Button)findViewById(R.id.button2);
        tv = (TextView)findViewById(R.id.textviw);


        int selectedId = rg.getCheckedRadioButtonId();

        // find the radiobutton by returned id
        RadioButton gender = (RadioButton) findViewById(selectedId);
        String hgender =gender.getText().toString();


        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
          startActivity(new Intent(Registration.this,LOGINME.class) );
            }
        });


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                whensignupclickexecutemeok();

            }
        });


    }


    private void whensignupclickexecutemeok() {


        String email =emailid.getText().toString();

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


        if(!(name.length() >= 10))
        {
            name.setError("Please enter fullname");
        }else if(!(email.matches(emailPattern)) )
            {
                emailid.setError("Enter a valid email id");
            } else if(!(moobilenumber.length()== 10))
        {
            moobilenumber.setError("Enter a valid number");
        }else if(!(address.length()>=12))
        {
            address.setError("Enter address");
        }else if((rg.getCheckedRadioButtonId() == -1))
        {
          Toast.makeText(getApplicationContext(),"Please select gender",Toast.LENGTH_LONG).show();
        }else{

            AFTERTHATSENDTOME();

        }





    }



    public void RadioBUTTONCLICKED(View view) {

        String userGender="";
// Check that the button is  now checked?
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {

            case R.id.radio1:
                if (checked)
                    userGender = "male";
                break;
            case R.id.radio2:
                if (checked)
                    userGender = "female";
                break;

        }

        String GEnder = userGender;

    }

    private void AFTERTHATSENDTOME() {

        String Name = name.getText().toString();
        String Emailid = emailid.getText().toString();
        String Mobilenumber = moobilenumber.getText().toString();
        String Address = address.getText().toString();

  SharedPreferences sharedPreferences = getSharedPreferences("gopalfile", Context.MODE_PRIVATE);
  SharedPreferences.Editor editor = sharedPreferences.edit();
  editor.putString("name",Name);
  editor.putString("emailid",Emailid);
  editor.putString("mobilenumber",Mobilenumber);
  editor.putString("address",Address);
  editor.commit();
/*

  progressDialog = new ProgressDialog(Registration.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
*/

     Toast.makeText(getApplicationContext(),"Thanks for Register@SmartMessageTrack",Toast.LENGTH_LONG).show();
                    Intent i = new Intent(Registration.this,LOGINME.class);
                    startActivity(i);

                    finish();

    }

}
