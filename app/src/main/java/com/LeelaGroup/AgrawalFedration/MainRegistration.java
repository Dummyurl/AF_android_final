package com.LeelaGroup.AgrawalFedration;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.LeelaGroup.AgrawalFedration.Business_Pojo.Business_Registration_Pojo;
import com.LeelaGroup.AgrawalFedration.Network.ApiClient;
import com.LeelaGroup.AgrawalFedration.Service.Business_ServiceAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRegistration extends AppCompatActivity implements View.OnClickListener {
    EditText name;
    EditText mname;
    EditText lname;
    EditText email;
    EditText mobile;
    EditText password;
    Button register, go_to_login;
    String user_name, user_email, user_mobile, user_password,midname="";
    private ProgressDialog pDialog;

    TextInputLayout layout_name, layout_lname, layout_email, layout_mobile, layout_password;
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_registration);
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Register");
        layout_name = (TextInputLayout) findViewById(R.id.layout_name);
        layout_lname = (TextInputLayout) findViewById(R.id.layout_lname);
        layout_email = (TextInputLayout) findViewById(R.id.layout_email);
        layout_mobile = (TextInputLayout) findViewById(R.id.layout_uesrname);
        layout_password = (TextInputLayout) findViewById(R.id.layout_password);

        name = (EditText) findViewById(R.id.name);
        mname = (EditText) findViewById(R.id.mname);
        lname = (EditText) findViewById(R.id.lname);
        email = (EditText) findViewById(R.id.email);
        mobile = (EditText) findViewById(R.id.uesrname);
        password = (EditText) findViewById(R.id.password);

        register = (Button) findViewById(R.id.signup);
        //go_to_login = (Button) findViewById(R.id.btn_signup);
        layout = (LinearLayout) findViewById(R.id.parent);
        name.addTextChangedListener(new MainRegistration.MyTextWatcher(name));
        email.addTextChangedListener(new MainRegistration.MyTextWatcher(email));
        password.addTextChangedListener(new MainRegistration.MyTextWatcher(password));

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyUtility.hideKeyboard(layout, MainRegistration.this);
                if (submitForm()) {
                    if (MyUtility.isConnected(MainRegistration.this)) {
                        businessRegistration();
                    } else {
                        MyUtility.internetProblem(layout);
                    }
                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:

                onBackPressed();

                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        submitForm();
    }

    private boolean submitForm() {
        if (!validateName()) {
            return false;
        }
        if (!validateLName()) {
            return false;
        }

        if (!validateEmail()) {
            return false;
        }

        if (!validatePassword()) {
            return false;
        }

        return true;

    }

    private boolean validateName() {

        if (name.getText().toString().trim().isEmpty()) {
            name.setError(getString(R.string.err_msg_first_name));

            requestFocus(name);

            return false;
        } else {
            layout_name.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateLName() {

        if (lname.getText().toString().trim().isEmpty()) {
            lname.setError(getString(R.string.err_msg_last_name));

            requestFocus(lname);

            return false;
        } else {
            layout_lname.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateEmail() {
        String email1 = email.getText().toString().trim();

        if (email1.isEmpty() || !isValidEmail(email1)) {

            layout_email.setError(getString(R.string.err_msg_email));

            requestFocus(email);
            return false;
        } else {
            layout_email.setErrorEnabled(false);
        }
        return true;
    }

    private boolean isValidEmail(String email1) {

        return !TextUtils.isEmpty(email1) && android.util.Patterns.EMAIL_ADDRESS.matcher(email1).matches();
    }

    private boolean validatePassword() {
        if (password.getText().toString().trim().isEmpty() && password.getText().toString().trim().length() < 6) {
            password.setError(getString(R.string.err_msg_password));

            requestFocus(password);

            return false;
        } else {
            layout_password.setErrorEnabled(false);
        }

        return true;

    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        public MyTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence s, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {

                case R.id.name:
                    validateName();
                    break;

                case R.id.lname:
                    validateLName();
                    break;

                case R.id.email:
                    validateEmail();
                    break;

                case R.id.password:
                    validatePassword();
                    break;
            }

        }
    }

    private void businessRegistration() {


        showpDialog();

        midname=mname.getText().toString().trim();
        user_name = name.getText().toString()+" "+midname+" "+lname.getText().toString().trim();
        user_email = email.getText().toString();
        user_mobile = mobile.getText().toString();
        user_password = password.getText().toString();

        Business_ServiceAPI serviceAPI = ApiClient.getRetrofit().create(Business_ServiceAPI.class);
        Call<Business_Registration_Pojo> call = serviceAPI.business_reg(user_name, user_email, user_mobile, user_password);
        call.enqueue(new Callback<Business_Registration_Pojo>() {

            @Override
            public void onResponse(Call<Business_Registration_Pojo> call, Response<Business_Registration_Pojo> response) {
                hidepDialog();
                Business_Registration_Pojo pojo = response.body();
                if (pojo.getSuccess()) {
                    Toast.makeText(MainRegistration.this, pojo.getMessage(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainRegistration.this, MainLoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {

                    Toast.makeText(MainRegistration.this, pojo.getMessage(), Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<Business_Registration_Pojo> call, Throwable t) {
                hidepDialog();
                Toast.makeText(MainRegistration.this, "Please check Connection", Toast.LENGTH_SHORT).show();

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
