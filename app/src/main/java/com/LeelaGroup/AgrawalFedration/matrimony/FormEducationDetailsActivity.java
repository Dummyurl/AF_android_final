package com.LeelaGroup.AgrawalFedration.matrimony;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.LeelaGroup.AgrawalFedration.Home;
import com.LeelaGroup.AgrawalFedration.MatrimonySession;
import com.LeelaGroup.AgrawalFedration.R;
import com.LeelaGroup.AgrawalFedration.matrimony.validation.CustomValidator;

import java.io.File;

public class FormEducationDetailsActivity extends AppCompatActivity {
    private GestureDetector gestureDetector;
    EditText sprEducation;
    EditText etPostGrad,etGrad,etPg,etCertfcn,etDoctrate;
    Toolbar toolbar;
    float dX;
    float dY;
    int lastAction;

    //declare var for basic details
    String mreg_am, mreg_fname, mreg_mname, mreg_lname, mreg_birth_place, mreg_birth_time, mreg_native_place, mreg_dob, mreg_age, mreg_marital_status, mreg_gender, mreg_no_child, mreg_child_leave_status, mreg_mother_tongue, mreg_about_me;
    String mat_reg_religion, mat_reg_caste, mat_reg_subcaste;
    File imageFile;

    //declare var for contact details
    String mreg_landline, mreg_phone, mreg_email, mreg_addr, mreg_country, mreg_state, mreg_city, mreg_pincode, mreg_resid_status;

    //declare vsr for social details
    String mat_reg_manglik, mat_reg_horoscope_match, mat_reg_gothra_self;

    String mat_id;
    String mat_reg_edu="";

MatrimonySession matrimonySession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_education_details);
        matrimonySession =new MatrimonySession(getApplicationContext());
        init();

        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Education Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        if(matrimonySession.checkLogin())
            finish();

    }


    public boolean validateFirst(){

        CustomValidator validator=new CustomValidator();

              return true;
    }

    public void init()
    {
        sprEducation=(EditText) findViewById(R.id.frm_edctndtl_spr_educton);

    }

    public void goToFormSocialAttribute(View v){
        startActivity(new Intent(this,FormSocialAttributeActivity.class));
    }
    public void goToFormOccupationDetails(View v){
        if (validateFirst())
        {
            setEducationDetails();
            Intent intent = new Intent(getApplicationContext(), FormOccupationDetailsActivity.class);
            intent.putExtra("mat_id",mat_id);
            //basic detail
            intent.putExtra("imageFile",imageFile);
            intent.putExtra("mreg_am",mreg_am);
            intent.putExtra("mreg_fname",mreg_fname);
            intent.putExtra("mreg_mname",mreg_mname);
            intent.putExtra("mreg_lname",mreg_lname);
            intent.putExtra("mreg_birth_place",mreg_birth_place);
            intent.putExtra("mreg_birth_time",mreg_birth_time);
            intent.putExtra("mreg_dob",mreg_dob);
            intent.putExtra("mreg_age",mreg_age);
            intent.putExtra("mreg_marital_status",mreg_marital_status);
            intent.putExtra("mreg_native_place",mreg_native_place);
            intent.putExtra("mreg_gender",mreg_gender);
            intent.putExtra("mreg_no_child",mreg_no_child);
            intent.putExtra("mreg_child_leave_status",mreg_child_leave_status);
            intent.putExtra("mreg_mother_tongue",mreg_mother_tongue);
            intent.putExtra("mreg_about_me",mreg_about_me);
            intent.putExtra("mat_reg_religion",mat_reg_religion);
            intent.putExtra("mat_reg_caste",mat_reg_caste);
            intent.putExtra("mat_reg_subcaste",mat_reg_subcaste);

            //contact details
            intent.putExtra("mreg_landline",mreg_landline);
            intent.putExtra("mreg_phone",mreg_phone);
            intent.putExtra("mreg_email",mreg_email);
            intent.putExtra("mreg_addr",mreg_addr);
            intent.putExtra("mreg_country",mreg_country);
            intent.putExtra("mreg_state",mreg_state);
            intent.putExtra("mreg_city",mreg_city);
            intent.putExtra("mreg_pincode",mreg_pincode);
            intent.putExtra("mreg_resid_status",mreg_resid_status);

            //social attribute details
            intent.putExtra("mat_reg_manglik",mat_reg_manglik);
            intent.putExtra("mat_reg_horoscope_match",mat_reg_horoscope_match);
            intent.putExtra("mat_reg_gothra_self",mat_reg_gothra_self);
            //intent.putExtra("mat_reg_gothra_mama",mat_reg_gothra_mama);

            //education details
            intent.putExtra("mat_reg_edu",mat_reg_edu);
            //intent.putExtra("mat_reg_pg",mat_reg_pg);
            //intent.putExtra("mat_reg_docto",mat_reg_docto);
            //intent.putExtra("mat_reg_certifi",mat_reg_certifi);

            startActivity(intent);
            this.finish();

        }
    }
    public void catchBasicContactSocialDetails() {

        // basic details data
        mat_id=getIntent().getStringExtra("mat_id");
        imageFile = (File) getIntent().getExtras().get("imageFile");
        mreg_am = getIntent().getStringExtra("mreg_am");
        mreg_fname = getIntent().getStringExtra("mreg_fname");
        mreg_mname = getIntent().getStringExtra("mreg_mname");
        mreg_lname = getIntent().getStringExtra("mreg_lname");
        mreg_birth_place = getIntent().getStringExtra("mreg_birth_place");
        mreg_birth_time = getIntent().getStringExtra("mreg_birth_time");
        mreg_native_place = getIntent().getStringExtra("mreg_native_place");
        mreg_dob = getIntent().getStringExtra("mreg_dob");
        mreg_age = getIntent().getStringExtra("mreg_age");
        mreg_marital_status = getIntent().getStringExtra("mreg_marital_status");
        mreg_gender = getIntent().getStringExtra("mreg_gender");
        mreg_no_child = getIntent().getStringExtra("mreg_no_child");
        mreg_child_leave_status = getIntent().getStringExtra("mreg_child_leave_status");
        mreg_mother_tongue = getIntent().getStringExtra("mreg_mother_tongue");
        mreg_about_me = getIntent().getStringExtra("mreg_about_me");
        mat_reg_religion = getIntent().getStringExtra("mat_reg_religion");
        mat_reg_caste = getIntent().getStringExtra("mat_reg_caste");
        mat_reg_subcaste = getIntent().getStringExtra("mat_reg_subcaste");

        //contact details data
        mreg_landline = getIntent().getStringExtra("mreg_landline");
        mreg_phone = getIntent().getStringExtra("mreg_phone");
        mreg_email = getIntent().getStringExtra("mreg_email");
        mreg_addr = getIntent().getStringExtra("mreg_addr");
        mreg_country = getIntent().getStringExtra("mreg_country");
        mreg_state = getIntent().getStringExtra("mreg_state");
        mreg_city = getIntent().getStringExtra("mreg_city");
        mreg_pincode = getIntent().getStringExtra("mreg_pincode");
        mreg_resid_status = getIntent().getStringExtra("mreg_resid_status");

        //social attr data
        mat_reg_manglik = getIntent().getStringExtra("mat_reg_manglik");
        mat_reg_horoscope_match = getIntent().getStringExtra("mat_reg_horoscope_match");
        mat_reg_gothra_self = getIntent().getStringExtra("mat_reg_gothra_self");
        //mat_reg_gothra_mama = getIntent().getStringExtra("mat_reg_gothra_mama");

    }
    public void  setEducationDetails()
    {
        catchBasicContactSocialDetails();
        mat_reg_edu=sprEducation.getText().toString();
//        mat_reg_pg=etPostGrad.getText().toString();
//        mat_reg_docto=etDoctrate.getText().toString();
//        mat_reg_certifi=etCertfcn.getText().toString();

    }
    @Override
    public void onBackPressed()
    {
        //finish();
        Intent intent = new Intent(FormEducationDetailsActivity.this, Home.class);
        intent.putExtra("mat_id",mat_id);
        startActivity(intent);
        FormEducationDetailsActivity.this.finish();
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
