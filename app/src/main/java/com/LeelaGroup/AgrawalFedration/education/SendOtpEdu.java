package com.LeelaGroup.AgrawalFedration.education;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.LeelaGroup.AgrawalFedration.R;
import com.LeelaGroup.AgrawalFedration.matrimony.ResetPasswordActivity;
import com.LeelaGroup.AgrawalFedration.matrimony.SendOtpActivity;
import com.LeelaGroup.AgrawalFedration.matrimony.validation.CustomValidator;

/**
 * Created by USer on 29-08-2017.
 */

public class SendOtpEdu extends AppCompatActivity {
    Button send;
    EditText etOtp;
    Toolbar toolbar;
    String eotp = "", userid = "", otp = "";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mat_send_otp_activity);


        etOtp = (EditText) findViewById(R.id.et_otp);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Otp");
        send = (Button) findViewById(R.id.otp_send);
        getOtp();
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateFirst()) {
                    getOtp();
                    if (eotp.equals(otp)) {
                        Intent intent = new Intent(SendOtpEdu.this, ResetPassEdu.class);
                        intent.putExtra("userid", userid);
                        startActivity(intent);
                        finish();
                    } else {

                        Toast.makeText(SendOtpEdu.this, "Please enter correct Otp", Toast.LENGTH_LONG).show();
                    }
                } else {

                }
            }
        });

    }

    public void getOtp() {
        userid = getIntent().getStringExtra("userid");
        otp = getIntent().getStringExtra("otp");
    }

    public boolean validateFirst() {
        etOtp.setError(null);
        CustomValidator validator = new CustomValidator();
        eotp = etOtp.getText().toString();
        if (!validator.isValidPincode(eotp)) {
            etOtp.requestFocus();
            etOtp.setError("Otp Must Be 6 Digits");
            return false;
        }
        etOtp.setError(null);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case android.R.id.home:

                onBackPressed();
                finish();

                return  true;
        }

        return super.onOptionsItemSelected(item);
    }


}
