package com.LeelaGroup.AgrawalFedration.education;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
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
import android.widget.TextView;
import android.widget.Toast;

import com.LeelaGroup.AgrawalFedration.MyUtility;
import com.LeelaGroup.AgrawalFedration.EducationSessionManager;
import com.LeelaGroup.AgrawalFedration.Education_Pojos.EducationLoginPojo;
import com.LeelaGroup.AgrawalFedration.Network.ApiClient;
import com.LeelaGroup.AgrawalFedration.R;
import com.LeelaGroup.AgrawalFedration.Service.ServiceAPIEducation;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Adwait on 03/07/2017.
 */

public class login_education extends AppCompatActivity {
    ProgressDialog progressDialog;
    EditText email, password;
    TextInputLayout layout_email, layout_password;
    String pd_email, pd_pwd;
    Button login;
    EducationSessionManager educationSessionManager;
    TextView forgotPass, new_user;
    String getEmail = "", userid = "", otp = "", pass = "";
    Toolbar toolbar;

    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_education);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Login....");



         /*   toolbar = (Toolbar) findViewById(R.id.toolbar_matrimony);
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("Login");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
*/

        educationSessionManager = new EducationSessionManager(getApplicationContext());

        layout = (LinearLayout) findViewById(R.id.parent);
        login = (Button) findViewById(R.id.login);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);

        forgotPass = (TextView) findViewById(R.id.forgot);
        new_user = (TextView) findViewById(R.id.new_user);

        layout_email = (TextInputLayout) findViewById(R.id.layout_email);
        layout_password = (TextInputLayout) findViewById(R.id.layout_password);
        layout = (LinearLayout) findViewById(R.id.parent);
        email.addTextChangedListener(new MyTextWatcher(email));
        password.addTextChangedListener(new MyTextWatcher(password));

        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                MyUtility.hideKeyboard(layout, login_education.this);
                if (submitForm()) {
                    if (MyUtility.isConnected(login_education.this)) {
                        login_edu();
                    } else {
                        MyUtility.internetProblem(layout);
                    }
                }

            }
        });

        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    forgotPass();
                Intent intent = new Intent(login_education.this, ForgotPassEdu.class);
                startActivity(intent);
                finish();
            }
        });

        new_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login_education.this, Registration.class));
            }
        });
    }


    private boolean submitForm() {
        if (!validateEmail()) {
            return false;
        }

        if (!validatePassword()) {
            return false;
        }
        return true;
    }

    private boolean validatePassword() {

        if (password.getText().toString().trim().isEmpty()) {
            layout_password.setError("Please enter password");

            return false;
        } else {

            layout_password.setErrorEnabled(false);
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

                case R.id.email:
                    validateEmail();
                    break;

                case R.id.password:
                    validatePassword();
                    break;
            }


        }
    }

    public void login_edu() {

        progressDialog.show();

        ServiceAPIEducation service = ApiClient.getRetrofit().create(ServiceAPIEducation.class);

        pd_email = email.getText().toString();
        pd_pwd = password.getText().toString();

        Call<EducationLoginPojo> ed = service.setLogin(pd_email, pd_pwd);

        ed.enqueue(new Callback<EducationLoginPojo>() {
            @Override
            public void onResponse(Call<EducationLoginPojo> call, Response<EducationLoginPojo> response) {
                progressDialog.dismiss();
                EducationLoginPojo edp = response.body();
                if (edp.isSuccess()) {
                    Toast.makeText(login_education.this, edp.getMessage(), Toast.LENGTH_SHORT).show();
                    /*AlertDialog.Builder alert = new AlertDialog.Builder(login_education.this);
                    alert.setTitle("Message");
                    alert.setMessage(edp.getMessage());
                    alert.show();
*/

                    //Bundle b = new Bundle();
                    // b.putString("myname", pd_email);

                    Intent intent = new Intent(getApplicationContext(), EducationNews.class);
                    //intent.putExtras(b);
                    educationSessionManager.createUserLoginSession(pd_email);
                    /*intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);*/

                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    startActivity(intent);
                    login_education.this.finish();
                }

                Toast.makeText(login_education.this, edp.getMessage(), Toast.LENGTH_SHORT).show();
                /*AlertDialog.Builder alert = new AlertDialog.Builder(login_education.this);
                alert.setTitle("Message");
                alert.setMessage(edp.getMessage());
                alert.show();
                progressDialog.dismiss();*/
            }


            @Override
            public void onFailure(Call<EducationLoginPojo> call, Throwable t) {


                progressDialog.dismiss();
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


}
