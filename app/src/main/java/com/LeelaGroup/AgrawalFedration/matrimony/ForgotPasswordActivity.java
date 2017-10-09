package com.LeelaGroup.AgrawalFedration.matrimony;

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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.LeelaGroup.AgrawalFedration.ForgotPasswordPojo;
import com.LeelaGroup.AgrawalFedration.MyUtility;
import com.LeelaGroup.AgrawalFedration.Network.ApiClient;
import com.LeelaGroup.AgrawalFedration.R;
import com.LeelaGroup.AgrawalFedration.ResetPasswordPojo;
import com.LeelaGroup.AgrawalFedration.Service.ServiceMatrimony;
import com.LeelaGroup.AgrawalFedration.matrimony.validation.CustomValidator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by USer on 10-08-2017.
 */

public class ForgotPasswordActivity extends AppCompatActivity {

    Button sendOtp;
    EditText etEmail;
    Toolbar toolbar;
    private ProgressDialog pDialog;
    String getOtp="",userid="",otp="";
    LinearLayout layout;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mat_forgot_password_activity);

          toolbar  = (Toolbar) findViewById(R.id.toolbar);
          sendOtp=(Button)findViewById(R.id.fg_send);
          etEmail=(EditText)findViewById(R.id.fg_email);
        layout=(LinearLayout)findViewById(R.id.forgot_pass_parent);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Forgot Password");

        sendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyUtility.hideKeyboard(layout,ForgotPasswordActivity.this);
              if (validateFirst())
              {
                  if (MyUtility.isConnected(ForgotPasswordActivity.this)) {
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
        String getEmail=etEmail.getText().toString();
        ServiceMatrimony serviceMatrimony= ApiClient.getRetrofit().create(ServiceMatrimony.class);
        Call<ForgotPasswordPojo> call=serviceMatrimony.getOtp(getEmail);
        call.enqueue(new Callback<ForgotPasswordPojo>() {
            @Override
            public void onResponse(Call<ForgotPasswordPojo> call, Response<ForgotPasswordPojo> response) {
                hidepDialog();
                ForgotPasswordPojo forgotPasswordPojo =response.body();
                if (forgotPasswordPojo.getSuccess())
                {
                    userid= forgotPasswordPojo.getUserid();
                    otp= forgotPasswordPojo.getOTP();
                    Toast.makeText(ForgotPasswordActivity.this, forgotPasswordPojo.getMessage(), Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(new Intent(ForgotPasswordActivity.this,SendOtpActivity.class));
                    intent.putExtra("otp",otp);
                    intent.putExtra("userid",userid);
                    startActivity(intent);
                    finish();
                }
                else {

                    Toast.makeText(ForgotPasswordActivity.this, forgotPasswordPojo.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ForgotPasswordPojo> call, Throwable t) {
                hidepDialog();
                Toast.makeText(ForgotPasswordActivity.this, "Please Check Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });

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


    public boolean validateFirst(){
        etEmail.setError(null);
        CustomValidator validator=new CustomValidator();
        final String email=etEmail.getText().toString();
        if(!validator.isValidEmail(email)){
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
