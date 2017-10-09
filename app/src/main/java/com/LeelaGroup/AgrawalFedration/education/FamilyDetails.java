package com.LeelaGroup.AgrawalFedration.education;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.LeelaGroup.AgrawalFedration.Education_Pojos.FamilyDetailPojo;
import com.LeelaGroup.AgrawalFedration.Network.ApiClient;
import com.LeelaGroup.AgrawalFedration.R;
import com.LeelaGroup.AgrawalFedration.Service.ServiceAPIEducation;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FamilyDetails extends AppCompatActivity {

    String Exam,sess,pd_fname, pd_lname, pd_dob, pd_father_name, pd_mother_name, pd_gender, pd_mob1, pd_mob2, pd_email, pd_addr, pd_pincode, pd_city, pd_state, pd_pwd;;

    String exam,Sess, fd_fathr_occup, fd_fathr_telephone, fd_fathr_mob, fd_fathr_desig, fd_fathr_income, fd_fathr_pan, fa_mothr_occup, fa_mothr_telephone, fa_mothr_mob, fa_mothr_religion;

    EditText Father_occupation,Telephone,Father_MobileNo,Designation,Anual_Income,PAN,Mother_Occupation,Mother_Telephone,Mother_MobileNo,Religion1;

    TextInputLayout layout_Father_occupation,layout_Telephone,layout_Father_MobileNo,layout_Designation,layout_Anual_Income,layout_PAN,layout_Mother_Occupation,layout_Mother_Telephone,layout_Mother_MobileNo,layout_Religion1;

    Button F_Prev,button5;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_matrimony);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setTitle("Family Details");

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Inserting...");

        Intent goto_Family = getIntent();

        Bundle b = goto_Family.getExtras();

        Exam = b.getString("myname");
        sess = b.getString("Session");
        pd_fname = b.getString("FirstName");
        pd_lname = b.getString("LastName");
        pd_dob = b.getString("DOB");
        pd_father_name = b.getString("FatherName");
        pd_mother_name = b.getString("MotherName");
        pd_gender = b.getString("Gender");
        pd_mob1 = b.getString("MobileNo1");
        pd_mob2 = b.getString("MobileNo2");
        pd_email = b.getString("Email");
        pd_addr = b.getString("Address");
        pd_pincode = b.getString("Pincode");
        pd_city = b.getString("city");
        pd_state = b.getString("State");
        pd_pwd = b.getString("password");

        Father_occupation = (EditText) findViewById(R.id.Father_occupation);
        Telephone = (EditText) findViewById(R.id.Telephone);
        Father_MobileNo = (EditText) findViewById(R.id.Father_MobileNo);
        Designation = (EditText) findViewById(R.id.Designation);
        Anual_Income = (EditText) findViewById(R.id.Anual_Income);
        PAN = (EditText) findViewById(R.id.PAN);
        Mother_Occupation = (EditText) findViewById(R.id.Mother_Occupation);
        Mother_Telephone = (EditText) findViewById(R.id.Mother_Telephone);
        Mother_MobileNo = (EditText) findViewById(R.id.Mother_MobileNo);
        Religion1 = (EditText) findViewById(R.id.Religion1);

        layout_Father_occupation = (TextInputLayout) findViewById(R.id.layout_Father_occupation);

        layout_Telephone = (TextInputLayout) findViewById(R.id.layout_Telephone);

        layout_Father_MobileNo = (TextInputLayout) findViewById(R.id.layout_Father_MobileNo);

        layout_Designation = (TextInputLayout) findViewById(R.id.layout_Designation);

        layout_Anual_Income = (TextInputLayout) findViewById(R.id.layout_Anual_Income);

        layout_PAN = (TextInputLayout) findViewById(R.id.layout_PAN);

        layout_Mother_Occupation = (TextInputLayout) findViewById(R.id.layout_Mother_Occupation);

        layout_Mother_Telephone = (TextInputLayout) findViewById(R.id.layout_Mother_Telephone);

        layout_Mother_MobileNo = (TextInputLayout) findViewById(R.id.layout_Mother_MobileNo);

        layout_Religion1 = (TextInputLayout) findViewById(R.id.layout_Religion1);


        Father_occupation.addTextChangedListener(new MyTextWatcher(Father_occupation));
        Telephone.addTextChangedListener(new MyTextWatcher(Telephone));
        Father_MobileNo.addTextChangedListener(new MyTextWatcher(Father_MobileNo));
        Designation.addTextChangedListener(new MyTextWatcher(Designation));
        Anual_Income.addTextChangedListener(new MyTextWatcher(Anual_Income));
        PAN.addTextChangedListener(new MyTextWatcher(PAN));
        Mother_Occupation.addTextChangedListener(new MyTextWatcher(Mother_Occupation));
        Mother_Telephone.addTextChangedListener(new MyTextWatcher(Mother_Telephone));
        Mother_MobileNo.addTextChangedListener(new MyTextWatcher(Mother_MobileNo));
        Religion1.addTextChangedListener(new MyTextWatcher(Religion1));




      //  F_Prev=(Button)findViewById(R.id.F_Prev);

        button5=(Button)findViewById(R.id.button5);

       button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (submitUser())
                {
                   // FamilyDetailsForm();

                    Bundle();

                    //startActivity(new Intent(FamilyDetails.this,EducationDetails.class));

                }
            }
        });
    }

    public void Bundle()
    {
        exam= Exam;
        Sess = sess;
        fd_fathr_occup=Father_occupation.getText().toString();
        fd_fathr_telephone=Telephone.getText().toString();
        fd_fathr_mob=Father_MobileNo.getText().toString();
        fd_fathr_desig=Designation.getText().toString();
        fd_fathr_income=Anual_Income.getText().toString();
        fd_fathr_pan=PAN.getText().toString();
        fa_mothr_occup=Mother_Occupation.getText().toString();
        fa_mothr_telephone=Mother_Telephone.getText().toString();
        fa_mothr_mob=Mother_MobileNo.getText().toString();
        fa_mothr_religion=Religion1.getText().toString();

        Bundle b = new Bundle();
        b.putString("myname", Exam);
        b.putString("Sess", sess);
        b.putString("FirstName",pd_fname);
        b.putString("LastName",pd_lname);
        b.putString("DOB",pd_dob);
        b.putString("FatherName",pd_father_name);
        b.putString("MotherName",pd_mother_name);
        b.putString("Gender",pd_gender);
        b.putString("MobileNo1",pd_mob1);
        b.putString("MobileNo2",pd_mob2);
        b.putString("Email",pd_email);
        b.putString("Address",pd_addr);
        b.putString("Pincode",pd_pincode);
        b.putString("city",pd_city);
        b.putString("State",pd_state);
        b.putString("password",pd_pwd);

        b.putString("Father_occupation", fd_fathr_occup);
        b.putString("Telephone",fd_fathr_telephone);
        b.putString("Father_MobileNo",fd_fathr_mob);
        b.putString("Designation",fd_fathr_desig);
        b.putString("Anual_Income",fd_fathr_income);
        b.putString("PAN",fd_fathr_pan);
        b.putString("Mother_Occupation",fa_mothr_occup);
        b.putString("Mother_Telephone",fa_mothr_telephone);
        b.putString("Mother_MobileNo",fa_mothr_mob);
        b.putString("Religion1",fa_mothr_religion);

        Intent goto_Education = new Intent(getApplicationContext(), EducationDetails.class);
        goto_Education.putExtras(b);
        goto_Education.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(goto_Education);
        FamilyDetails.this.finish();

    }

    @Override
    public void onBackPressed()
    {
        //finish();
        Intent intent = new Intent(FamilyDetails.this, Registration.class);
        startActivity(intent);
        FamilyDetails.this.finish();
    }




    private boolean submitUser() {

        if(!validateFather_occupation())
        {
            return false;
        }

        if(!validateFather_MobileNo())
        {
            return false;
        }

        if(!validateAnual_Income())
        {
            return false;
        }

        if(!validateMother_Occupation())
        {
            return false;
        }

        if(!validateReligion1())
        {
            return false;
        }

        return true;
    }

    private boolean validateFather_occupation() {

        if (Father_occupation.getText().toString().trim().isEmpty())
        {
            layout_Father_occupation.setError("Enter Father Occupation");
            requestFocus(Father_occupation);
            Father_occupation.setError(null);
            return false;
        }else{
            layout_Father_occupation.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePAN() {

        String pan = PAN.getText().toString().trim();

        if (!Pattern.matches("[A-Z]{5}[0-9]{4}[A-Z]{1}",pan))
        {
            layout_PAN.setError("Enter Valid PAN Number");
            requestFocus(PAN);
            PAN.setError(null);
            return false;
        }else{
            layout_PAN.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateFather_MobileNo() {

        String Father_MobileNo1 = Father_MobileNo.getText().toString().trim();

        if (Father_MobileNo1.isEmpty() && Father_MobileNo1.length()<10 && Father_MobileNo1.matches("[7-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]"))
        {
            layout_Father_MobileNo.setError(getString(R.string.err_msg_mobile));
            Father_MobileNo.setError(null);
            requestFocus(Father_MobileNo);

            return false;
        }else{
            layout_Father_MobileNo.setErrorEnabled(false);
        }

        return true;

    }

    private boolean validateMother_MobileNo() {

        String Mother_MobileNo1 = Mother_MobileNo.getText().toString().trim();

        if (Mother_MobileNo1.isEmpty() && Mother_MobileNo1.length()<10 && Mother_MobileNo1.matches("[7-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]"))
        {
            layout_Mother_MobileNo.setError(getString(R.string.err_msg_mobile));
            Mother_MobileNo.setError(null);
            requestFocus(Mother_MobileNo);

            return false;
        }else{
            layout_Mother_MobileNo.setErrorEnabled(false);
        }

        return true;

    }

    private boolean validateAnual_Income() {
        String AnualIncome = Anual_Income.getText().toString().trim();
            if (AnualIncome.isEmpty())
            {
                layout_Anual_Income.setError("Enter Annual Income");
                Anual_Income.setError(null);
                Anual_Income.requestFocus();
                
                return false;
            }else{
                layout_Anual_Income.setErrorEnabled(false);
            }
        
        return true;
    }

    private boolean validateMother_Occupation() {

        if (Mother_Occupation.getText().toString().trim().isEmpty())
        {
            layout_Mother_Occupation.setError("Enter Mother Occupation");
            Mother_Occupation.setError(null);
            Mother_Occupation.requestFocus();
            return false;
        }else{
            layout_Mother_Occupation.setErrorEnabled(false);
        }

        return true;
    }


    private boolean validateReligion1() {
        if (Religion1.getText().toString().trim().isEmpty())        {
            layout_Religion1.setError("Enter Religion");
            Religion1.requestFocus();
            Religion1.setError(null);
            return false;
        }else{
            layout_Religion1.setErrorEnabled(false);
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

            switch(view.getId())
            {
                case R.id.Father_occupation : validateFather_occupation();
                    break;
                case R.id.Telephone : //validateTelephone();
                    break;
                case R.id.Father_MobileNo :validateFather_MobileNo();
                    break;
                case R.id.Designation ://validateDesignation();
                    break;
                case R.id.Anual_Income : validateAnual_Income();
                    break;
                case R.id.PAN : validatePAN();
                    break;
                case R.id.Mother_Occupation:validateMother_Occupation();
                    break;
                case R.id.Mother_Telephone : //validateMother_Telephone();
                    break;
                case R.id.Mother_MobileNo : validateMother_MobileNo();
                    break;
                case R.id.Religion1 : validateReligion1();
                    break;

            }

        }
    }

    private void FamilyDetailsForm() {

        progressDialog.show();

        ServiceAPIEducation service = ApiClient.getRetrofit().create(ServiceAPIEducation.class);
        FamilyDetailPojo FDP = new FamilyDetailPojo();

        exam= Exam;
        Sess = sess;
        fd_fathr_occup=Father_occupation.getText().toString();
        fd_fathr_telephone=Telephone.getText().toString();
        fd_fathr_mob=Father_MobileNo.getText().toString();
        fd_fathr_desig=Designation.getText().toString();
        fd_fathr_income=Anual_Income.getText().toString();
        fd_fathr_pan=PAN.getText().toString();
        fa_mothr_occup=Mother_Occupation.getText().toString();
        fa_mothr_telephone=Mother_Telephone.getText().toString();
        fa_mothr_mob=Mother_MobileNo.getText().toString();
        fa_mothr_religion=Religion1.getText().toString();

        FDP.setExam(exam);
        FDP.setSess(Sess);
        FDP.setFd_fathr_occup(fd_fathr_occup);
        FDP.setFd_fathr_telephone(fd_fathr_telephone);
        FDP.setFd_fathr_mob(fd_fathr_mob);
        FDP.setFd_fathr_desig(fd_fathr_desig);
        FDP.setFd_fathr_income(fd_fathr_income);
        FDP.setFd_fathr_pan(fd_fathr_pan);
        FDP.setFa_mothr_occup(fa_mothr_occup);
        FDP.setFa_mothr_telephone(fa_mothr_telephone);
        FDP.setFa_mothr_mob(fa_mothr_mob);
        FDP.setFa_mothr_religion(fa_mothr_religion);

        Call<FamilyDetailPojo> fd = service.setFamilyDetails(exam,Sess, fd_fathr_occup, fd_fathr_telephone, fd_fathr_mob, fd_fathr_desig, fd_fathr_income, fd_fathr_pan, fa_mothr_occup, fa_mothr_telephone, fa_mothr_mob, fa_mothr_religion);

        fd.enqueue(new Callback<FamilyDetailPojo>() {
            @Override
            public void onResponse(Call<FamilyDetailPojo> call, Response<FamilyDetailPojo> response) {
              //  Toast.makeText(FamilyDetails.this, "success", Toast.LENGTH_SHORT).show();
                Bundle b = new Bundle();
                b.putString("myname", Exam);
                b.putString("Sess", sess);
                b.putString("FirstName",pd_fname);
                b.putString("LastName",pd_lname);
                b.putString("DOB",pd_dob);
                b.putString("FatherName",pd_father_name);
                b.putString("MotherName",pd_mother_name);
                b.putString("Gender",pd_gender);
                b.putString("MobileNo1",pd_mob1);
                b.putString("MobileNo2",pd_mob2);
                b.putString("Email",pd_email);
                b.putString("Address",pd_addr);
                b.putString("Pincode",pd_pincode);
                b.putString("city",pd_city);
                b.putString("State",pd_state);
                b.putString("password",pd_pwd);

                Intent goto_Education = new Intent(getApplicationContext(), EducationDetails.class);
                goto_Education.putExtras(b);
                goto_Education.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(goto_Education);
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<FamilyDetailPojo> call, Throwable t) {
                Toast.makeText(FamilyDetails.this,getText(R.string.checkConn), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
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
    /*public void startSecond(View v){

        finish();
    }
*/

}
