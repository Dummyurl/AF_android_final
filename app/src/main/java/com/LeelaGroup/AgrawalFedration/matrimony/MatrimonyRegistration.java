package com.LeelaGroup.AgrawalFedration.matrimony;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.LeelaGroup.AgrawalFedration.MyUtility;
import com.LeelaGroup.AgrawalFedration.Network.ApiClient;
import com.LeelaGroup.AgrawalFedration.R;
import com.LeelaGroup.AgrawalFedration.Service.ServiceMatrimony;
import com.LeelaGroup.AgrawalFedration.matrimony.models.RegistrationDetails;
import com.LeelaGroup.AgrawalFedration.matrimony.validation.CustomValidator;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MatrimonyRegistration extends AppCompatActivity implements View.OnClickListener {
    EditText editTextfName, editTextEmail, editTextmName,
            editTextlName, editTextNumber, editTextpassword, editTextconpassword;
    Button register;
    private ProgressDialog pDialog;
    String mat_fname, mat_mname, mat_lname, mat_email, mat_phone, mat_gender, mat_pwd;
    RadioGroup rg;
    RadioButton rb;
    Toolbar toolbar;

    private AwesomeValidation awesomeValidation;
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrimony_registration);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        layout=(LinearLayout)findViewById(R.id.activity_matrimony_registration);
        editTextmName = (EditText) findViewById(R.id.midname);
        editTextfName = (EditText) findViewById(R.id.firstname);
        editTextlName = (EditText) findViewById(R.id.lastname);
        editTextEmail = (EditText) findViewById(R.id.mail);
        editTextNumber = (EditText) findViewById(R.id.num);
        editTextpassword = (EditText) findViewById(R.id.pass);
        editTextconpassword = (EditText) findViewById(R.id.cnpass);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        register = (Button) findViewById(R.id.register);
        register.setOnClickListener(this);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            setTitle("Register");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);

    }

    public void getGender() {
        rg = (RadioGroup) findViewById(R.id.radioGroup);
        int radioButtonId = rg.getCheckedRadioButtonId();
        rb = (RadioButton) findViewById(radioButtonId);
    }

    @Override
    public boolean onSupportNavigateUp() {
        Intent intent = new Intent(MatrimonyRegistration.this, LoginMatrimony.class);
        startActivity(intent);
        finish();
        return true;
    }

    @Override
    public void onClick(View view) {
        if (view == register) {
            MyUtility.hideKeyboard(layout,MatrimonyRegistration.this);
            if (validateFirst()) {
                if (MyUtility.isConnected(MatrimonyRegistration.this)){
                    setRegistration();
                }else {
                    MyUtility.internetProblem(layout);
                }
            }
        }
    }

    public boolean validateFirst() {
        CustomValidator validator = new CustomValidator();

        final String fanme = editTextfName.getText().toString();
        if (!validator.isValidName(fanme)) {
            editTextfName.requestFocus();
            editTextfName.setError("Please Enter Valid Name");
            return false;
        }
        editTextfName.setError(null);

        final String manme = editTextmName.getText().toString();
        if (!validator.isValidName(manme)) {
            editTextmName.requestFocus();
            editTextmName.setError("Please Enter Valid Name");
            return false;
        }
        editTextmName.setError(null);

        final String lanme = editTextlName.getText().toString();
        if (!validator.isValidName(lanme)) {
            editTextlName.requestFocus();
            editTextlName.setError("Please Enter Valid Name");
            return false;
        }
        editTextlName.setError(null);


        final String email = editTextEmail.getText().toString();
        if (!validator.isValidEmail(email)) {
            editTextEmail.requestFocus();
            editTextEmail.setError("Please Enter Valid Email");
            return false;
        }
        editTextEmail.setError(null);

        final String contact = editTextNumber.getText().toString();
        if (!validator.isValidMobile(contact)) {
            editTextNumber.requestFocus();
            editTextNumber.setError("Please Enter Valid Contact Number");
            return false;
        }
        editTextNumber.setError(null);

        final String pass = editTextpassword.getText().toString();
        if (!validator.isValidPassword(pass)) {
            editTextpassword.requestFocus();
            editTextpassword.setError("Password at least 6 digit length");
            return false;
        }
        editTextpassword.setError(null);

        final String cnfPass = editTextconpassword.getText().toString().trim();
        if (!pass.equals(cnfPass)) {
            editTextconpassword.requestFocus();
            editTextconpassword.setError("Password Not Matched");
            return false;
        }
        editTextconpassword.setError(null);

        return true;
    }

    public void setRegistration() {
        showpDialog();
        getGender();
        mat_fname = editTextfName.getText().toString();
        mat_mname = editTextmName.getText().toString();
        mat_lname = editTextlName.getText().toString();
        mat_email = editTextEmail.getText().toString();
        mat_phone = editTextNumber.getText().toString();
        mat_gender = rb.getText().toString();
        mat_pwd = editTextpassword.getText().toString();

        ServiceMatrimony serviceMatrimony = ApiClient.getRetrofit().create(ServiceMatrimony.class);
        Call<RegistrationDetails> registrationDetailsCall = serviceMatrimony.setRegDetaild(mat_fname, mat_mname, mat_lname, mat_email, mat_phone, mat_gender, mat_pwd);

        registrationDetailsCall.enqueue(new Callback<RegistrationDetails>() {
            RegistrationDetails registrationDetails=new RegistrationDetails();
            @Override
            public void onResponse(Call<RegistrationDetails> call, Response<RegistrationDetails> response) {
                hidepDialog();
                registrationDetails=response.body();
                Toast.makeText(MatrimonyRegistration.this, "Successfully registered", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MatrimonyRegistration.this, LoginMatrimony.class));
                finish();
            }

            @Override
            public void onFailure(Call<RegistrationDetails> call, Throwable t) {
                hidepDialog();
                Toast.makeText(MatrimonyRegistration.this, "Something went wrong", Toast.LENGTH_SHORT).show();

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
    //rg = (RadioGroup) findViewById(R.id.radioGroup);

}
