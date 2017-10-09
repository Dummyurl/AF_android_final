package com.LeelaGroup.AgrawalFedration.education;

import android.app.DatePickerDialog;
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
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.LeelaGroup.AgrawalFedration.Education_Pojos.PersonalDetailPojo;
import com.LeelaGroup.AgrawalFedration.MyUtility;
import com.LeelaGroup.AgrawalFedration.Network.ApiClient;
import com.LeelaGroup.AgrawalFedration.R;
import com.LeelaGroup.AgrawalFedration.Service.ServiceAPIEducation;

import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PersonalDetails extends AppCompatActivity implements OnClickListener {

    EditText FirstName;
    EditText LastName;
    EditText FatherName;
    EditText MotherName;
    EditText MobileNo1;
    EditText MobileNo2;
    EditText Email;
    EditText Address;
    EditText Pincode;
    EditText password;

    RadioGroup radioGroup;
    RadioButton selectedGender;

    ProgressDialog progressDialog;

    Bundle b;
    String Exam, email;

    String sess;

    String exam, pd_fname, pd_lname, pd_dob, pd_father_name, pd_mother_name, pd_gender, pd_mob1, pd_mob2, pd_email, pd_addr, pd_pincode, pd_city, pd_state, pd_pwd;


    TextInputLayout layout_FirstName, layout_LastName, layout_FatherName,
            layout_MotherName, layout_MobileNo1, layout_MobileNo2, layout_Email,
            layout_Address, layout_Pincode, layout_Password, layout_city, layout_state;

    EditText e5;
    TextView b3;
    int day, month, year;

    Button P_Next;
    // Button P_Prev;

    Spinner spinnerstate, spinnercity;

    List<PersonalDetailPojo> statedata;
    String[] nameListstate;
    ArrayAdapter<String> dataAdapterstate;

    List<PersonalDetailPojo> citydata;
    String[] nameListcity;
    ArrayAdapter<String> dataAdaptercity;

    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_matrimony);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setTitle("Personal Details");

        getStateData();

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("check email...");

        Intent intent = getIntent();

        Bundle b = intent.getExtras();

        Exam = b.getString("myname");

        layout = (LinearLayout) findViewById(R.id.parent);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroupgender);

        layout_FirstName = (TextInputLayout) findViewById(R.id.layout_FirstName);
        layout_LastName = (TextInputLayout) findViewById(R.id.layout_LastName);
        layout_FatherName = (TextInputLayout) findViewById(R.id.layout_FatherName);
        layout_MotherName = (TextInputLayout) findViewById(R.id.layout_MotherName);
        layout_MobileNo1 = (TextInputLayout) findViewById(R.id.layout_MobileNo1);
        layout_MobileNo2 = (TextInputLayout) findViewById(R.id.layout_MobileNo2);
        layout_Email = (TextInputLayout) findViewById(R.id.layout_Email);
        layout_Address = (TextInputLayout) findViewById(R.id.layout_Address);
        layout_Pincode = (TextInputLayout) findViewById(R.id.layout_Pincode);
        layout_Password = (TextInputLayout) findViewById(R.id.layout_password);
        layout_city = (TextInputLayout) findViewById(R.id.il_city);
        layout_state = (TextInputLayout) findViewById(R.id.il_state);


        FirstName = (EditText) findViewById(R.id.FirstName);
        LastName = (EditText) findViewById(R.id.LastName);
        FatherName = (EditText) findViewById(R.id.FatherName);
        MotherName = (EditText) findViewById(R.id.MotherName);
        MobileNo1 = (EditText) findViewById(R.id.MobileNo1);
        MobileNo2 = (EditText) findViewById(R.id.MobileNo2);
        Email = (EditText) findViewById(R.id.Email);
        Address = (EditText) findViewById(R.id.Address);
        Pincode = (EditText) findViewById(R.id.Pincode);
        password = (EditText) findViewById(R.id.password);

        spinnerstate = (Spinner) findViewById(R.id.spinnerstate);
        spinnercity = (Spinner) findViewById(R.id.spinnercity);

        FirstName.addTextChangedListener(new MyTextWatcher(FirstName));
        LastName.addTextChangedListener(new MyTextWatcher(LastName));
        FatherName.addTextChangedListener(new MyTextWatcher(FatherName));
        MotherName.addTextChangedListener(new MyTextWatcher(MotherName));
        MobileNo1.addTextChangedListener(new MyTextWatcher(MobileNo1));
        MobileNo2.addTextChangedListener(new MyTextWatcher(MobileNo2));
        Email.addTextChangedListener(new MyTextWatcher(Email));
        Address.addTextChangedListener(new MyTextWatcher(Address));
        Pincode.addTextChangedListener(new MyTextWatcher(Pincode));
        password.addTextChangedListener(new MyTextWatcher(password));
//        spinnerstate.addTextChangedListener(new MyTextWatcher(spinnerstate));
//        spinnercity.addTextChangedListener(new MyTextWatcher(spinnercity));

        e5 = (EditText) findViewById(R.id.e5);

        b3 = (TextView) findViewById(R.id.b3);
        b3.setOnClickListener(this);


        P_Next = (Button) findViewById(R.id.P_Next);
        // P_Prev=(Button)findViewById(R.id.P_Prev);

        P_Next.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (submitForm()) {
                    if (MyUtility.isConnected(PersonalDetails.this)) {
                        CheckEmail();
                    } else {
                        MyUtility.internetProblem(layout);
                    }
                }
            }
        });
    }


    private void getStateData() {
        final ServiceAPIEducation state = ApiClient.getRetrofit().create(ServiceAPIEducation.class);
        Call<List<PersonalDetailPojo>> call = state.fetchState();
        call.enqueue(new Callback<List<PersonalDetailPojo>>() {
            @Override
            public void onResponse(Call<List<PersonalDetailPojo>> call, Response<List<PersonalDetailPojo>> response) {
                statedata = response.body();
                nameListstate = new String[statedata.size()];
                for (int i = 0; i < statedata.size(); i++) {
                    nameListstate[i] = statedata.get(i).getSname();
                }

                dataAdapterstate = new ArrayAdapter<String>(PersonalDetails.this, R.layout.spinner_text, nameListstate);
                dataAdapterstate.setDropDownViewResource(android.R.layout.simple_list_item_1);
                spinnerstate.setAdapter(dataAdapterstate);
                spinnerstate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        pd_state = spinnerstate.getSelectedItem().toString();
                        getCityName();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void onFailure(Call<List<PersonalDetailPojo>> call, Throwable t) {

            }
        });
    }

    private void getCityName() {

        final ServiceAPIEducation state = ApiClient.getRetrofit().create(ServiceAPIEducation.class);
        Call<List<PersonalDetailPojo>> call = state.getCityData(pd_state);
        call.enqueue(new Callback<List<PersonalDetailPojo>>() {
            @Override
            public void onResponse(Call<List<PersonalDetailPojo>> call, Response<List<PersonalDetailPojo>> response) {
                citydata = response.body();
                nameListcity = new String[citydata.size()];
                for (int i = 0; i < citydata.size(); i++) {
                    nameListcity[i] = citydata.get(i).getCityName();
                }

                dataAdaptercity = new ArrayAdapter<String>(PersonalDetails.this, R.layout.spinner_text, nameListcity);
                dataAdaptercity.setDropDownViewResource(android.R.layout.simple_list_item_1);
                spinnercity.setAdapter(dataAdaptercity);
                spinnercity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        pd_city = spinnercity.getSelectedItem().toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void onFailure(Call<List<PersonalDetailPojo>> call, Throwable t) {

            }
        });

    }


    @Override
    public void onClick(View v) {
        if (v == b3) {
            final Calendar c = Calendar.getInstance();
            day = c.get(Calendar.DAY_OF_MONTH);
            month = c.get(Calendar.MONTH);
            year = c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    e5.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                }
            }, day, month, year);
            c.add(Calendar.YEAR,-70);


            datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());

            datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
            datePickerDialog.show();
        }
    }

   /* @Override
    public void onBackPressed()
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(PersonalDetails.this);

        alert.setTitle("MSG");
        alert.setMessage("Please fill all form...");

        alert.show();
    }
*/

    public void Bundle() {

       /* int selectedGenderId = radioGroup.getCheckedRadioButtonId();
        selectedGender = (RadioButton) findViewById(selectedGenderId);



        exam = Exam;
        sess=Email.getText().toString();
        pd_fname=FirstName.getText().toString();
        pd_lname=LastName.getText().toString();
        pd_dob=e5.getText().toString();
        pd_father_name=FatherName.getText().toString();
        pd_mother_name=MotherName.getText().toString();
        pd_gender=selectedGender.getText().toString();
        pd_mob1=MobileNo1.getText().toString();
        pd_mob2=MobileNo2.getText().toString();
        pd_email=Email.getText().toString();
        pd_addr=Address.getText().toString();
        pd_pincode=Pincode.getText().toString();
        pd_city=spinnercity.getText().toString();
        pd_state=spinnerstate.getText().toString();
        pd_pwd=password.getText().toString();*/


        b = new Bundle();
        b.putString("myname", Exam);
        b.putString("Session", sess);
        b.putString("FirstName", pd_fname);
        b.putString("LastName", pd_lname);
        b.putString("DOB", pd_dob);
        b.putString("FatherName", pd_father_name);
        b.putString("MotherName", pd_mother_name);
        b.putString("Gender", pd_gender);
        b.putString("MobileNo1", pd_mob1);
        b.putString("MobileNo2", pd_mob2);
        b.putString("Email", pd_email);
        b.putString("Address", pd_addr);
        b.putString("Pincode", pd_pincode);
        b.putString("city", pd_city);
        b.putString("State", pd_state);
        b.putString("password", pd_pwd);


        Intent intent = new Intent(getApplicationContext(), FamilyDetails.class);
        intent.putExtras(b);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        PersonalDetails.this.finish();


    }

    public void CheckEmail() {
        progressDialog.show();

        int selectedGenderId = radioGroup.getCheckedRadioButtonId();
        selectedGender = (RadioButton) findViewById(selectedGenderId);

        exam = Exam;
        sess = Email.getText().toString();
        pd_fname = FirstName.getText().toString();
        pd_lname = LastName.getText().toString();
        pd_dob = e5.getText().toString();
        pd_father_name = FatherName.getText().toString();
        pd_mother_name = MotherName.getText().toString();
        pd_gender = selectedGender.getText().toString();
        pd_mob1 = MobileNo1.getText().toString();
        pd_mob2 = MobileNo2.getText().toString();
        pd_email = Email.getText().toString();
        pd_addr = Address.getText().toString();
        pd_pincode = Pincode.getText().toString();
       /* pd_city=spinnercity.getSelectedItem().toString();*/
       /* pd_state=spinnerstate.getSelectedItem().toString();*/
        pd_pwd = password.getText().toString();


        ServiceAPIEducation service = ApiClient.getRetrofit().create(ServiceAPIEducation.class);

        Call<PersonalDetailPojo> ce = service.CheckEmail(pd_email);

        ce.enqueue(new Callback<PersonalDetailPojo>() {
            @Override
            public void onResponse(Call<PersonalDetailPojo> call, Response<PersonalDetailPojo> response) {

                PersonalDetailPojo ce = response.body();

                if (ce != null) {
                    boolean r = ce.isSuccess();
                    if (ce.isSuccess()) {
                       // Toast.makeText(getApplicationContext(), ce.getMessage(), Toast.LENGTH_SHORT).show();
                        /*AlertDialog.Builder alert = new AlertDialog.Builder(PersonalDetails.this);

                        alert.setTitle("MSG");
                        alert.setMessage(ce.getMessage());

                        alert.show();*/

                        progressDialog.dismiss();

                        Bundle();
                    } else {
                        Toast.makeText(getApplicationContext(), ce.getMessage(), Toast.LENGTH_SHORT).show();

                  /*      AlertDialog.Builder alert = new AlertDialog.Builder(PersonalDetails.this);

                        alert.setTitle("MSG");
                        alert.setMessage(ce.getMessage());

                        alert.show();
*/
                        progressDialog.dismiss();
                    }
                } else {
                    assert ce != null;
                    Log.v("Response", ce.toString());
                }
            }

            @Override
            public void onFailure(Call<PersonalDetailPojo> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }


    private boolean submitForm() {

        if (!validateFirstName()) {
            return false;
        }

        if (!validateLastName()) {
            return false;
        }

        if (!validateDOB()) {
            return false;
        }


        if (!validateFatherName()) {
            return false;
        }
        if (!validateMotherName()) {
            return false;
        }
        if (!validateMobileNo1()) {
            return false;
        }
       /* if (!validateMobileNo2()) {
            return false;
        }*/
        if (!validateEmail()) {
            return false;
        }
//        if (!validateState())
//        {
//            return false;
//        }
//        if (!validateCity())
//        {
//            return false;
//        }
        if (!validateAddress()) {
            return false;
        }
        if (!validatePincode()) {
            return false;
        }

        if (!validatePassword()) {
            return false;
        }
//        if (!validateConfirmPassword())
//        {
//            return false;
//        }

        return true;
    }

    //    private boolean validateConfirmPassword() {
//        String cpassword = Confirmpassword.getText().toString().trim();
//        String password1 = password.getText().toString().trim();
//
//        if (cpassword != password1)
//        {
//            layout_Confirmpassword.setError(getString(R.string.err_msg_cpassword));
//
//            return false;
//        }else{
//
//            layout_Confirmpassword.setErrorEnabled(false);
//        }
//        return true;
//    }
    private boolean validateFirstName() {
        String first = FirstName.getText().toString().trim();

        if (first.isEmpty() || !first.matches("[a-zA-Z ]+")) {
            layout_FirstName.setError("Please Enter Valid Name");
            FirstName.setError(null);
            FirstName.requestFocus();
            return false;
        } else {
            layout_FirstName.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateLastName() {
        String Last = LastName.getText().toString().trim();

        if (Last.isEmpty() || !Last.matches("[a-zA-Z ]+")) {
            layout_LastName.setError("Please Enter Valid Name");
            LastName.setError(null);
            LastName.requestFocus();
            return false;
        } else {
            layout_LastName.setErrorEnabled(false);
        }

        return true;
    }


    private boolean validateDOB() {
        String DOB = e5.getText().toString().trim();

        if (DOB.isEmpty()) {
            e5.setError("Please Select Birth Date");
            e5.requestFocus();
            return false;
        } else {
            e5.setError(null);
        }

        return true;
    }


    private boolean validateFatherName() {
        String Last = FatherName.getText().toString().trim();

        if (Last.isEmpty() || !Last.matches("[a-zA-Z ]+")) {
            layout_FatherName.setError("Please Enter Valid Name");
            FatherName.requestFocus();
            FatherName.setError(null);
            return false;
        } else {
            layout_FatherName.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateMotherName() {
        String Last = MotherName.getText().toString().trim();

        if (Last.isEmpty() || !Last.matches("[a-zA-Z ]+")) {
            layout_MotherName.setError("Please Enter Valid Name");
            MotherName.requestFocus();
            MotherName.setError(null);
            return false;
        } else {
            layout_MotherName.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateMobileNo1() {

        String MobileNo11 = MobileNo1.getText().toString().trim();

        if (MobileNo11.isEmpty() || MobileNo11.length() < 10) {
            layout_MobileNo1.setError(getString(R.string.err_msg_mobile));
            MobileNo1.requestFocus();
            MobileNo1.setError(null);

            return false;
        } else {
            layout_MobileNo1.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateMobileNo2() {
        String MobileNo12 = MobileNo2.getText().toString().trim();

        if (MobileNo12.length() < 10 && MobileNo12.matches("[7-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]")) {
            layout_MobileNo2.setError(getString(R.string.err_msg_mobile));

            MobileNo2.requestFocus();
            MobileNo2.setError(null);

            return false;
        } else {
            layout_MobileNo2.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateEmail() {

        String email1 = Email.getText().toString().trim();

        if (email1.isEmpty() || !isValidateEmail(email1)) {

            layout_Email.setError(getString(R.string.err_msg_email));
            Email.setError(null);

            Email.requestFocus();

            return false;
        } else {

            layout_Email.setErrorEnabled(false);
        }

        return true;
    }
//    private boolean validateState() {
//        String state = spinnerstate.getText().toString().trim();
//
//        if (state.isEmpty()|| !state.matches("[a-zA-Z ]+")) {
//            layout_state.setError("Please Enter Valid State");
//            spinnerstate.requestFocus();
//            return false;
//        } else {
//            layout_state.setErrorEnabled(false);
//        }
//
//        return true;
//    }
//    private boolean validateCity() {
//        String city = spinnercity.getText().toString().trim();
//
//        if (city.isEmpty()|| !city.matches("[a-zA-Z ]+")) {
//            layout_city.setError("Please Enter Valid State");
//            spinnercity.requestFocus();
//            return false;
//        } else {
//            layout_city.setErrorEnabled(false);
//        }
//
//        return true;
//    }


    private boolean validateAddress() {

        if (Address.getText().toString().trim().isEmpty()) {
            layout_Address.setError(getString(R.string.err_msg_address));

            Address.requestFocus();
            Address.setError(null);

            return false;
        } else {
            layout_Address.setErrorEnabled(false);
        }
        return true;

    }

    private boolean validatePincode() {
        String pincode = Pincode.getText().toString().trim();

        if (pincode.isEmpty() || pincode.length() < 6) {
            layout_Pincode.setError(getString(R.string.err_msg_pincode));
            Pincode.setError(null);

            Pincode.requestFocus();

            return false;
        } else {
            layout_Pincode.setErrorEnabled(false);
        }

        return true;

    }

    private boolean validatePassword() {

        String pass=password.getText().toString().trim();
        if (pass.isEmpty() && pass.length()<6) {
            layout_Password.setError(getString(R.string.err_msg_password));
            password.requestFocus();
            password.setError(null);
            return false;
        } else {

            layout_Password.setErrorEnabled(false);
        }
        return true;
    }


    private boolean isValidateEmail(String email1) {

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

                case R.id.FirstName:
                    validateFirstName();
                    break;

                case R.id.LastName:
                    validateLastName();
                    break;

                case R.id.e5:
                    validateDOB();
                    break;

                case R.id.FatherName:
                    validateFatherName();
                    break;

                case R.id.MotherName:
                    validateMotherName();
                    break;

                case R.id.MobileNo1:
                    validateMobileNo1();
                    break;

                case R.id.MobileNo2:
                    validateMobileNo2();
                    break;

                case R.id.Pincode:
                    validatePincode();
                    break;

                case R.id.Email:
                    validateEmail();
                    break;

//                case R.id.spinnerstate: validateState();
//                    break;
//
//                case R.id.spinnercity: validateCity();
//                    break;

                case R.id.Address:
                    validateAddress();
                    break;

                case R.id.password:
                    validatePassword();
                    break;

            }

        }
    }

    /*private void PersonalDetailForm() {

        progressDialog.show();

        int selectedGenderId = radioGroup.getCheckedRadioButtonId();
        selectedGender = (RadioButton) findViewById(selectedGenderId);

        ServiceAPIEducation service = ApiClient.getRetrofit().create(ServiceAPIEducation.class);

        PersonalDetailPojo PDP = new PersonalDetailPojo();




        PDP.setExam(exam);
        PDP.setSess(sess);
        PDP.setPd_fname(pd_fname);
        PDP.setPd_lname(pd_lname);
        PDP.setPd_dob(pd_dob);
        PDP.setPd_fname(pd_father_name);
        PDP.setPd_mother_name(pd_mother_name);
        PDP.setPd_gender(pd_gender);
        PDP.setPd_mob1(pd_mob1);
        PDP.setPd_mob2(pd_mob2);
        PDP.setPd_email(pd_email);
        PDP.setPd_addr(pd_addr);
        PDP.setPd_pincode(pd_pincode);
        PDP.setPd_city(pd_city);
        PDP.setPd_state(pd_state);
        PDP.setPd_pwd(pd_pwd);



        Call<PersonalDetailPojo> pd = service.setStudentDetails(exam,sess,pd_fname, pd_lname, pd_dob, pd_father_name, pd_mother_name, pd_gender, pd_mob1, pd_mob2, pd_email, pd_addr, pd_pincode, pd_city, pd_state, pd_pwd);

        pd.enqueue(new Callback<PersonalDetailPojo>() {
            @Override
            public void onResponse(Call<PersonalDetailPojo> call, Response<PersonalDetailPojo> response) {
                PersonalDetailPojo pdp = response.body();
                if (pdp.isSuccess())
                {
                    Toast.makeText(PersonalDetails.this,pdp.getMessage() , Toast.LENGTH_SHORT).show();

                    Bundle b = new Bundle();
                    b.putString("myname", Exam);
                    b.putString("Session", sess);


                    Intent intent = new Intent(getApplicationContext(), FamilyDetails.class);
                    intent.putExtras(b);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    progressDialog.dismiss();
                }

                Toast.makeText(PersonalDetails.this,pdp.getMessage() , Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<PersonalDetailPojo> call, Throwable t) {
                Toast.makeText(PersonalDetails.this, "fail", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }*/
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
