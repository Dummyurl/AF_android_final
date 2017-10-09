package com.LeelaGroup.AgrawalFedration.matrimony;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

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

public class ResetPasswordActivity extends AppCompatActivity {

    Button savePss;
    EditText pass;
    EditText cnfPass;
    Toolbar toolbar;
    String userid = "", epass = "";
    LinearLayout layout;
    private ProgressDialog pDialog;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mat_reset_password_activity);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        pass = (EditText) findViewById(R.id.rp_pass);
        cnfPass = (EditText) findViewById(R.id.rp_cnfpass);
        layout = (LinearLayout) findViewById(R.id.reset_pass_parent);
        savePss = (Button) findViewById(R.id.rp_save);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("Reset Password");
        }

        savePss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyUtility.hideKeyboard(layout,ResetPasswordActivity.this);
                if (validateFirst()) {
                    cnfPass.setError(null);
                    if (pass.getText().toString().equals(cnfPass.getText().toString())) {

                        if (MyUtility.isConnected(ResetPasswordActivity.this)){
                            savePassword();
                        }else {
                            MyUtility.internetProblem(layout);
                        }

                    } else {
                        cnfPass.requestFocus();
                        cnfPass.setError("Password Not Matched");
                    }
                }

            }
        });
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
    }

    public boolean validateFirst() {
        pass.setError(null);
        CustomValidator validator = new CustomValidator();
        String epass = pass.getText().toString();
        if (!validator.isValidPassword(epass)) {
            pass.requestFocus();
            pass.setError("Password Must Be At Least 6 Digit Long");
            return false;
        }
        pass.setError(null);

        return true;
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

    public void getId() {
        userid = getIntent().getStringExtra("userid");
        epass = pass.getText().toString();
    }


    private void savePassword() {
        showpDialog();
        getId();
        ServiceMatrimony serviceMatrimony = ApiClient.getRetrofit().create(ServiceMatrimony.class);
        Call<ResetPasswordPojo> call = serviceMatrimony.resetPassword(userid, epass);
        call.enqueue(new Callback<ResetPasswordPojo>() {
            @Override
            public void onResponse(Call<ResetPasswordPojo> call, Response<ResetPasswordPojo> response) {
                hidepDialog();
                ResetPasswordPojo passwordPojo = response.body();
                if (passwordPojo.getSuccess()) {

                    Intent intent = new Intent(ResetPasswordActivity.this, LoginMatrimony.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(ResetPasswordActivity.this, "Password changed successfully", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(ResetPasswordActivity.this, passwordPojo.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResetPasswordPojo> call, Throwable t) {
                hidepDialog();
                Toast.makeText(ResetPasswordActivity.this, "Please Check Internet Connection", Toast.LENGTH_LONG).show();

            }
        });
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
