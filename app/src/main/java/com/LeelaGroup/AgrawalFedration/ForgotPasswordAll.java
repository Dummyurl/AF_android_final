package com.LeelaGroup.AgrawalFedration;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.LeelaGroup.AgrawalFedration.Network.ApiClient;
import com.LeelaGroup.AgrawalFedration.Service.Business_ServiceAPI;

import com.LeelaGroup.AgrawalFedration.matrimony.validation.CustomValidator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by USer on 11-08-2017.
 */

public class ForgotPasswordAll extends AppCompatActivity {
    Button sendOtp;
    EditText etEmail;
    Toolbar toolbar;
    String getOtp = "", userid = "", otp = "";
    LinearLayout layout;
    private ProgressDialog pDialog;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mat_forgot_password_activity);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        sendOtp = (Button) findViewById(R.id.fg_send);
        etEmail = (EditText) findViewById(R.id.fg_email);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Forgot Password");
        layout = (LinearLayout) findViewById(R.id.forgot_pass_parent);
        sendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyUtility.hideKeyboard(layout, ForgotPasswordAll.this);
                if (validateFirst()) {
                    if (MyUtility.isConnected(ForgotPasswordAll.this)) {
                        getOTP();
                    } else {
                        MyUtility.internetProblem(layout);
                    }

                }
            }
        });

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
    }


    private void getOTP() {
        showpDialog();
        String getEmail = etEmail.getText().toString();
        Business_ServiceAPI business_serviceAPI = ApiClient.getRetrofit().create(Business_ServiceAPI.class);
        Call<ForgotPasswordPojo> call = business_serviceAPI.getOtp(getEmail);
        call.enqueue(new Callback<ForgotPasswordPojo>() {
            @Override
            public void onResponse(Call<ForgotPasswordPojo> call, Response<ForgotPasswordPojo> response) {
                hidepDialog();
                ForgotPasswordPojo forgotPasswordPojo = response.body();
                if (forgotPasswordPojo.getSuccess()) {
                    userid = forgotPasswordPojo.getUserid();
                    otp = forgotPasswordPojo.getOTP();

                    Toast.makeText(ForgotPasswordAll.this, forgotPasswordPojo.getMessage(), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(new Intent(ForgotPasswordAll.this, SendOtpAll.class));
                    intent.putExtra("otp", otp);
                    intent.putExtra("userid", userid);
                    startActivity(intent);
                    finish();
                    //matchOtp();
                } else {

                    Toast.makeText(ForgotPasswordAll.this, forgotPasswordPojo.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ForgotPasswordPojo> call, Throwable t) {
                hidepDialog();
                Toast.makeText(ForgotPasswordAll.this, "Please Check Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:

                onBackPressed();
                finish();

                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public boolean validateFirst() {
        etEmail.setError(null);
        CustomValidator validator = new CustomValidator();
        final String email = etEmail.getText().toString();
        if (!validator.isValidEmail(email)) {
            etEmail.requestFocus();
            etEmail.setError("Enter Registered Email");
            return false;
        }
        etEmail.setError(null);

        return true;
    }
    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
