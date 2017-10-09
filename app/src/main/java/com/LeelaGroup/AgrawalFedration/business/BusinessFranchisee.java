package com.LeelaGroup.AgrawalFedration.business;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.LeelaGroup.AgrawalFedration.Business_Pojo.FranchiseeRegPojo;
import com.LeelaGroup.AgrawalFedration.Business_Medical_Session;
import com.LeelaGroup.AgrawalFedration.MyUtility;
import com.LeelaGroup.AgrawalFedration.Network.ApiClient;
import com.LeelaGroup.AgrawalFedration.R;
import com.LeelaGroup.AgrawalFedration.Service.Business_ServiceAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BusinessFranchisee extends AppCompatActivity implements View.OnClickListener {
    RadioGroup rg;
    RadioButton rb;

    EditText et_firstName, et_lastName, et_email, et_mobile_no, et_address, et_chapter;

    TextInputLayout tl_firstName, tl_lastName, tl_email, tl_mobile_no, tl_address, tl_country, tl_stat, tl_city;

    AutoCompleteTextView sp_country, sp_state, sp_city;

    Button bt_save, bt_reset;

    String Fran_fname, Fran_lname, Fran_email, Fran_mobile, Fran_address, Fran_country, Fran_state, Fran_city, Fran_chapter, Fran_gender;

    private ProgressDialog pDialog;
    Business_Medical_Session business_Medical_session;
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_franchisee);

        business_Medical_session = new Business_Medical_Session(getApplicationContext());

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Franchisee Registration");

        layout = (LinearLayout) findViewById(R.id.parent);

        et_firstName = (EditText) findViewById(R.id.et_first_name);
        et_lastName = (EditText) findViewById(R.id.et_last_name);
        et_email = (EditText) findViewById(R.id.et_email);
        et_mobile_no = (EditText) findViewById(R.id.et_mobile);
        et_address = (EditText) findViewById(R.id.et_address);

        tl_firstName = (TextInputLayout) findViewById(R.id.tl_first_name);
        tl_lastName = (TextInputLayout) findViewById(R.id.tl_last_name);
        tl_email = (TextInputLayout) findViewById(R.id.tl_email);
        tl_mobile_no = (TextInputLayout) findViewById(R.id.tl_mobile);
        tl_address = (TextInputLayout) findViewById(R.id.tl_address);
        tl_city = (TextInputLayout) findViewById(R.id.ti_ac_city);
        tl_stat = (TextInputLayout) findViewById(R.id.ti_ac_state);
        tl_country = (TextInputLayout) findViewById(R.id.ti_ac_country);


        sp_country = (AutoCompleteTextView) findViewById(R.id.sp_country);
        sp_state = (AutoCompleteTextView) findViewById(R.id.sp_state);
        sp_city = (AutoCompleteTextView) findViewById(R.id.sp_city);
        et_chapter = (EditText) findViewById(R.id.et_chapter);

        bt_save = (Button) findViewById(R.id.bt_save);
        bt_reset = (Button) findViewById(R.id.bt_reset);

        et_firstName.addTextChangedListener(new MyTextWatcher(et_firstName));
        et_lastName.addTextChangedListener(new MyTextWatcher(et_lastName));
        et_email.addTextChangedListener(new MyTextWatcher(et_email));
        et_mobile_no.addTextChangedListener(new MyTextWatcher(et_mobile_no));
        et_address.addTextChangedListener(new MyTextWatcher(et_address));
        sp_country.addTextChangedListener(new MyTextWatcher(sp_country));
        sp_state.addTextChangedListener(new MyTextWatcher(sp_state));
        sp_city.addTextChangedListener(new MyTextWatcher(sp_city));


        bt_save.setOnClickListener(this);
    }

    public void onRadioButtonClicked() {
        rg = (RadioGroup) findViewById(R.id.radioGroup);
        int radioButtonId = rg.getCheckedRadioButtonId();
        rb = (RadioButton) findViewById(radioButtonId);

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.bt_save:

                MyUtility.hideKeyboard(layout,BusinessFranchisee.this);
                if (submiForm()) {
                    if (MyUtility.isConnected(BusinessFranchisee.this))
                    {
                        AddBusinessFranchise();
                    }else {
                        MyUtility.internetProblem(layout);
                    }
                    break;
                }
        }
    }

    private void AddBusinessFranchise()

    {
        showpDialog();
        onRadioButtonClicked();
        Fran_fname = et_firstName.getText().toString();
        Fran_lname = et_lastName.getText().toString();
        Fran_email = et_email.getText().toString();
        Fran_mobile = et_mobile_no.getText().toString();
        Fran_address = et_address.getText().toString();
        Fran_chapter = et_chapter.getText().toString();
        Fran_country = sp_country.getText().toString();
        Fran_state = sp_state.getText().toString();
        Fran_city = sp_city.getText().toString();
        Fran_gender = rb.getText().toString();

        Business_ServiceAPI getResponse = ApiClient.getRetrofit().create(Business_ServiceAPI.class);
        Call<FranchiseeRegPojo> call = getResponse.AddBusinessFranchise(Fran_fname, Fran_lname, Fran_email, Fran_mobile, Fran_address,
                Fran_chapter, Fran_country, Fran_state, Fran_city, Fran_gender);

        call.enqueue(new Callback<FranchiseeRegPojo>() {
            @Override
            public void onResponse(Call<FranchiseeRegPojo> call, Response<FranchiseeRegPojo> response) {
                FranchiseeRegPojo franchisee = response.body();
                hidepDialog();
                if (franchisee.getMessage() != null) {
                   /* AlertDialog.Builder builder = new AlertDialog.Builder(BusinessFranchisee.this);
                    builder.setTitle("Message");
                    builder.setMessage(franchisee.getMessage());*/
                     Toast.makeText(BusinessFranchisee.this, "Our admin will contact you", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(BusinessFranchisee.this, BusinessActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<FranchiseeRegPojo> call, Throwable t) {
                Toast.makeText(BusinessFranchisee.this, getText(R.string.checkConn), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private boolean submiForm() {
        if (!validateFirstName()) {
            return false;
        }
        if (!validateLastName()) {
            return false;
        }
        if (!validateEmail()) {
            return false;
        }
        if (!validateMobile()) {
            return false;
        }
        if (!validateAddress()) {
            return false;
        }
        if (!validateCountry()) {
            return false;
        }
        if (!validateState()) {
            return false;
        }
        if (!validateCity()) {
            return false;
        }

        //Toast.makeText(getApplicationContext(), "Thank You!", Toast.LENGTH_SHORT).show();

        return true;
    }

    private boolean validateCountry() {
        String country = sp_country.getText().toString().trim();

        if (country.isEmpty() || !country.matches("[a-zA-Z ]+")) {
            tl_country.setError("Please Enter Valid Country Name");
            sp_country.requestFocus();
            sp_country.setError(null);
            return false;
        } else {
            tl_country.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateState() {
        String state = sp_state.getText().toString().trim();

        if (state.isEmpty() || !state.matches("[a-zA-Z ]+")) {
            tl_stat.setError("Please Enter State");
            sp_state.requestFocus();
            sp_state.setError(null);
            return false;
        } else {
            tl_stat.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateCity() {
        String city = sp_city.getText().toString().trim();

        if (city.isEmpty() || !city.matches("[a-zA-Z ]+")) {
            tl_city.setError("Please Enter City");
            sp_city.requestFocus();
            sp_city.setError(null);
            return false;
        } else {
            tl_city.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateFirstName() {
        String first = et_firstName.getText().toString().trim();

        if (first.isEmpty() || !first.matches("[a-zA-Z ]+")) {
            tl_firstName.setError("Please Enter Valid Name");
            et_firstName.requestFocus();
            et_firstName.setError(null);
            return false;
        } else {
            tl_firstName.setErrorEnabled(false);
        }

        return true;
    }


    private boolean validateLastName() {
        String last = et_lastName.getText().toString().trim();

        if (last.isEmpty() || !last.matches("[a-zA-Z ]+")) {
            requestFocus(et_lastName);
            et_lastName.setError(null);
            tl_lastName.setError("Please Enter Valid Name");
            return false;
        } else {
            tl_lastName.setErrorEnabled(false);
        }

        return true;
    }


    private boolean validateEmail() {
        String email = et_email.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            requestFocus(et_email);
            tl_email.setError(getString(R.string.err_email));
            et_email.setError(null);
            return false;
        } else {
            tl_email.setErrorEnabled(false);
        }

        return true;
    }

    private boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }


    private boolean validateMobile() {
        String mobile = et_mobile_no.getText().toString().trim();

        if (mobile.isEmpty() && mobile.length() < 10 && mobile.matches("[7-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]")) {
            tl_mobile_no.setError(getString(R.string.err_mobile_no));
            et_mobile_no.setError(null);
            requestFocus(et_mobile_no);
            return false;
        } else {
            tl_mobile_no.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateAddress() {
        String address = et_address.getText().toString().trim();

        if (address.isEmpty()) {
            tl_address.setError(getString(R.string.err_address));
            requestFocus(et_address);
            et_address.setError(null);
            return false;
        } else {
            tl_address.setErrorEnabled(false);
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

        private MyTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            switch (view.getId()) {
                case R.id.et_first_name:
                    validateFirstName();
                    break;
                case R.id.et_last_name:
                    validateLastName();
                    break;
                case R.id.et_email:
                    validateEmail();
                    break;
                case R.id.et_mobile:
                    validateMobile();
                    break;
                case R.id.et_address:
                    validateAddress();
                    break;
                case R.id.sp_country:
                    validateCountry();
                    break;
                case R.id.sp_state:
                    validateState();
                    break;
                case R.id.sp_city:
                    validateCity();
                    break;
                default:


            }
        }
    }

    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
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
}
