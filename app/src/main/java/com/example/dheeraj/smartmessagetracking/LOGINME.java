package com.example.dheeraj.smartmessagetracking;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class LOGINME extends AppCompatActivity {


   EditText numberedit;
   Button login;
   TextView nextlink;
ProgressDialog progressDialog;
    private FirebaseAuth mAuth;

    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginme);

    numberedit = (EditText)findViewById(R.id.editText);
    nextlink = (TextView)findViewById(R.id.link);
    login = (Button)findViewById(R.id.login);

        mAuth = FirebaseAuth.getInstance();


    mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted( PhoneAuthCredential phoneAuthCredential) {


            progressDialog = new ProgressDialog(LOGINME.this);
            progressDialog.setMessage("Please wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();
/*

            Toast.makeText(LOGINME.this,"Phone number verified ", Toast.LENGTH_LONG).show();
            signinwithphoneauthcreditional(phoneAuthCredential);

            Intent intent = new Intent(LOGINME.this, MainProcess.class);
            startActivity(intent);

              finish();
*/                      progressDialog.dismiss();
                        Toast.makeText(LOGINME.this,"Phone number verified ", Toast.LENGTH_LONG).show();
                        signinwithphoneauthcreditional(phoneAuthCredential);
                        Intent intent = new Intent(LOGINME.this, MainProcess.class);
                        startActivity(intent);
                        finish();




        }

        @Override
        public void onVerificationFailed(FirebaseException e) {

           // Log.w(TAG, "onVerificationFailed", e);
            if (e instanceof FirebaseAuthInvalidCredentialsException) {
                numberedit.setError("Invalid phone number.");
            } else if (e instanceof FirebaseTooManyRequestsException) {
                Toast.makeText(LOGINME.this, "Your verification codes are more Please try again",
                        Toast.LENGTH_SHORT).show();
            }



        }
    };


    nextlink.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(LOGINME.this,Registration.class));
        }
    });



    login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!(numberedit.length() == 10))
                {
                 numberedit.setError("Please enter a valid phonenumber");
                }else
                    {
                        startPhoneNumberVerification(numberedit.getText().toString());
                   }

            }
        });






    }

    private void signinwithphoneauthcreditional(PhoneAuthCredential phoneAuthCredential) {

        mAuth.signInWithCredential(phoneAuthCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete( Task<AuthResult> task) {

                if(task.isSuccessful()){
                   // Log.d(TAG,"Signin sucessfull");
                    FirebaseUser user = task.getResult().getUser();
                    finish();
                }

            }
        });


    }

    private void startPhoneNumberVerification(String s) {

        PhoneAuthProvider.getInstance().verifyPhoneNumber(s,60, TimeUnit.SECONDS,this,mCallbacks);

    }
}
