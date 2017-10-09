package com.LeelaGroup.AgrawalFedration.matrimony;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.LeelaGroup.AgrawalFedration.Home;
import com.LeelaGroup.AgrawalFedration.MatrimonySession;
import com.LeelaGroup.AgrawalFedration.R;
import com.LeelaGroup.AgrawalFedration.matrimony.validation.CustomValidator;

import java.io.File;

public class FormPhysicalAttributeActivity extends AppCompatActivity {
    private GestureDetector gestureDetector;
    Spinner sprBdyTyp,sprBldGrp,sprCmplxn,sprDiet,sprSmoke,sprDrink;
    EditText etWeght,etHgtFt;
    RadioGroup rgHandicp;
    RadioButton rbYesOrNo;
    Toolbar toolbar;

    //declare var for basic details
    String mreg_am, mreg_fname, mreg_mname, mreg_lname, mreg_birth_place, mreg_birth_time, mreg_native_place, mreg_dob, mreg_age, mreg_marital_status, mreg_gender, mreg_no_child, mreg_child_leave_status, mreg_mother_tongue, mreg_about_me;
    String mat_reg_religion, mat_reg_caste, mat_reg_subcaste;
    File imageFile;

    //declare var for contact details
    String mreg_landline, mreg_phone, mreg_email, mreg_addr, mreg_country, mreg_state, mreg_city, mreg_pincode, mreg_resid_status;

    //declare var for social details
    String mat_reg_manglik, mat_reg_horoscope_match, mat_reg_gothra_self;

    //declare var for education details
    String mat_reg_edu;

    //declare var for occu details
    String mat_reg_occup,mat_reg_industry,mat_reg_empl,mat_reg_ipa;

    //declare var for family details
    String mat_reg_father_name,mat_reg_mother_name,mat_reg_father_occup,mat_reg_mother_occup,mat_reg_no_brother,mat_reg_no_sister,mat_mar_bro,mat_mar_sis,mat_reg_status,mreg_fam_type,mreg_fam_lpa;

    float dX;
    float dY;
    int lastAction;

    String mat_id;
    String mreg_phy_ht,mreg_phy_wt,mreg_bdy_type,mreg_blood_grp,mreg_complexion,mreg_handicapped,mreg_smoke,mreg_drink,mreg_diet;


    MatrimonySession matrimonySession;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_physical_attribute);

        matrimonySession=new MatrimonySession(getApplicationContext());

        init();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Physical Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        if(matrimonySession.checkLogin())
            finish();

        catchBasicContactSocialEducationOccupationFamilyDetails();
        getSpinnerdata();
    }

    private void getSpinnerdata() {
        ArrayAdapter<CharSequence> bdtyparray = ArrayAdapter.createFromResource(this, R.array.body_type, R.layout.spinner_text);
        bdtyparray.setDropDownViewResource(android.R.layout.simple_list_item_1);
        sprBdyTyp.setAdapter(bdtyparray);

        ArrayAdapter<CharSequence> bldgrarray = ArrayAdapter.createFromResource(this, R.array.blood_group, R.layout.spinner_text);
        bldgrarray.setDropDownViewResource(android.R.layout.simple_list_item_1);
        sprBldGrp.setAdapter(bldgrarray);

        ArrayAdapter<CharSequence> cmparray = ArrayAdapter.createFromResource(this, R.array.complexion, R.layout.spinner_text);
        cmparray.setDropDownViewResource(android.R.layout.simple_list_item_1);
        sprCmplxn.setAdapter(cmparray);

        ArrayAdapter<CharSequence> ditarray = ArrayAdapter.createFromResource(this, R.array.diet, R.layout.spinner_text);
        ditarray.setDropDownViewResource(android.R.layout.simple_list_item_1);
        sprDiet.setAdapter(ditarray);

        ArrayAdapter<CharSequence> smokearray = ArrayAdapter.createFromResource(this, R.array.smoke, R.layout.spinner_text);
        smokearray.setDropDownViewResource(android.R.layout.simple_list_item_1);
        sprSmoke.setAdapter(smokearray);

        ArrayAdapter<CharSequence> drikarray = ArrayAdapter.createFromResource(this, R.array.drink, R.layout.spinner_text);
        drikarray.setDropDownViewResource(android.R.layout.simple_list_item_1);
        sprDrink.setAdapter(drikarray);
    }
    public boolean validateFirst(){

        CustomValidator validator=new CustomValidator();

        final String htft=etHgtFt.getText().toString();
        if(!validator.isEmptyField(htft)){
                validator.isEmptyField(htft);
                etHgtFt.setError("Please Enter Height");
            return false;
        }
        etHgtFt.setError(null);

        final String wght=etWeght.getText().toString();
        if(!validator.isValidWeight(wght)){
            etWeght.requestFocus();
            etWeght.setError("Please Enter Proper Weight");
            return false;
        }
        etWeght.setError(null);

        final String bdytyp=sprBdyTyp.getSelectedItem().toString();
        if(!validator.isEmptyField(bdytyp)){


            return false;
        }

        final String bldgrp=sprBldGrp.getSelectedItem().toString();
        if(!validator.isEmptyField(bldgrp)){


            return false;
        }
        final String cmplxn=sprCmplxn.getSelectedItem().toString();
        if(!validator.isEmptyField(cmplxn)){


            return false;
        }
        final String diet=sprDiet.getSelectedItem().toString();
        if(!validator.isEmptyField(diet)){


            return false;
        }
        final String drink=sprDrink.getSelectedItem().toString();
        if(!validator.isEmptyField(drink)){


            return false;
        }
        final String smoke=sprSmoke.getSelectedItem().toString();
        if(!validator.isEmptyField(smoke)){


            return false;
        }

        return true;
    }
    public void goToFamillyDetails(View v){
        startActivity(new Intent(this,FormFamilyDetailsActivity.class));
    }
    public void goToOtherInfrmation(View v){
        if (validateFirst()){
            setPhysicalDetails();
            Intent intent = new Intent(getApplicationContext(), FormOthersActivity.class);
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
//            intent.putExtra("mat_reg_pg",mat_reg_pg);
//            intent.putExtra("mat_reg_docto",mat_reg_docto);
//            intent.putExtra("mat_reg_certifi",mat_reg_certifi);

            //occupation details
            intent.putExtra("mat_reg_occup",mat_reg_occup);
            intent.putExtra("mat_reg_industry",mat_reg_industry);
            intent.putExtra("mat_reg_empl",mat_reg_empl);
            intent.putExtra("mat_reg_ipa",mat_reg_ipa);

            //family details
            intent.putExtra("mat_reg_father_name",mat_reg_father_name);
            intent.putExtra("mat_reg_mother_name",mat_reg_mother_name);
            intent.putExtra("mat_reg_father_occup",mat_reg_father_occup);
            intent.putExtra("mat_reg_mother_occup",mat_reg_mother_occup);
            intent.putExtra("mat_reg_no_brother",mat_reg_no_brother);
            intent.putExtra("mat_reg_no_sister",mat_reg_no_sister);
            intent.putExtra("mat_mar_bro",mat_mar_bro);
            intent.putExtra("mat_mar_sis",mat_mar_sis);
            intent.putExtra("mat_reg_status",mat_reg_status);
            intent.putExtra("mreg_fam_type",mreg_fam_type);
            intent.putExtra("mreg_fam_lpa",mreg_fam_lpa);

            //physical details
            intent.putExtra("mreg_phy_ht",mreg_phy_ht);
            intent.putExtra("mreg_phy_wt",mreg_phy_wt);
            intent.putExtra("mreg_bdy_type",mreg_bdy_type);
            intent.putExtra("mreg_blood_grp",mreg_blood_grp);
            intent.putExtra("mreg_complexion",mreg_complexion);
            //intent.putExtra("mreg_complexion",mreg_complexion);
            intent.putExtra("mreg_handicapped",mreg_handicapped);
            intent.putExtra("mreg_smoke",mreg_smoke);
            intent.putExtra("mreg_drink",mreg_drink);
            intent.putExtra("mreg_diet",mreg_diet);

            startActivity(intent);
            this.finish();
           // startActivity(new Intent(this,FormOthersActivity.class));
        }
    }

    /*private void setPhysicalDetail()
    {
        mreg_phy_ht=etHgtFt.getText().toString();
        mreg_phy_wt=etWeght.getText().toString();
        mreg_bdy_type=sprBdyTyp.getSelectedItem().toString();
        mreg_blood_grp=sprBldGrp.getSelectedItem().toString();
        mreg_complexion=sprCmplxn.getSelectedItem().toString();
        mreg_handicapped=rbYesOrNo.getText().toString();
        mreg_smoke=sprSmoke.getSelectedItem().toString();
        mreg_drink=sprDrink.getSelectedItem().toString();
        mreg_diet=sprDiet.getSelectedItem().toString();

        ServiceMatrimony serviceMatrimony =ApiClient.getRetrofit().create(ServiceMatrimony.class);

        Call<PhysicalAndOtherDetails> physicalDetailsCall=serviceMatrimony.setPhysicalDetails(mreg_phy_ht,mreg_phy_wt,mreg_bdy_type,mreg_blood_grp,mreg_complexion,mreg_handicapped,mreg_smoke,mreg_drink,mreg_diet,id);

        physicalDetailsCall.enqueue(new Callback<PhysicalAndOtherDetails>() {
            @Override
            public void onResponse(Call<PhysicalAndOtherDetails> call, Response<PhysicalAndOtherDetails> response) {


                Toast.makeText(FormPhysicalAttributeActivity.this, "Success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<PhysicalAndOtherDetails> call, Throwable t) {

                Toast.makeText(FormPhysicalAttributeActivity.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });



    }*/

    public void getHandicap()
    {
        rgHandicp=(RadioGroup)findViewById(R.id.frm_phyclattr_rdgrp_hndcped);
        int isIdHandicaped=rgHandicp.getCheckedRadioButtonId();
        rbYesOrNo=(RadioButton)findViewById(isIdHandicaped);
    }
    public void init()
    {

        etWeght=(EditText)findViewById(R.id.frm_phyclattr_et_wegt);
        etHgtFt=(EditText)findViewById(R.id.frm_phyclattr_et_higt_ft);
        sprBdyTyp=(Spinner)findViewById(R.id.frm_phyclattr_spr_bdtype);
        sprBldGrp=(Spinner)findViewById(R.id.frm_phyclattr_spr_bldgrp);
        sprCmplxn=(Spinner)findViewById(R.id.frm_phyclattr_spr_complxn);
        sprDiet=(Spinner)findViewById(R.id.frm_phyclattr_spr_diet);
        sprDrink=(Spinner)findViewById(R.id.frm_phyclattr_spr_drink);
        sprSmoke=(Spinner)findViewById(R.id.frm_phyclattr_spr_smoke);

    }
    public void catchBasicContactSocialEducationOccupationFamilyDetails() {

        mat_id=getIntent().getStringExtra("mat_id");
        // basic details data
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

        //education info data
        mat_reg_edu = getIntent().getStringExtra("mat_reg_edu");
//        mat_reg_pg = getIntent().getStringExtra("mat_reg_pg");
//        mat_reg_docto = getIntent().getStringExtra("mat_reg_docto");
//        mat_reg_certifi = getIntent().getStringExtra("mat_reg_certifi");

        //occupation details
        mat_reg_occup = getIntent().getStringExtra("mat_reg_occup");
        mat_reg_industry = getIntent().getStringExtra("mat_reg_industry");
        mat_reg_empl = getIntent().getStringExtra("mat_reg_empl");
        mat_reg_ipa = getIntent().getStringExtra("mat_reg_ipa");

        //family info data
        mat_reg_father_name = getIntent().getStringExtra("mat_reg_father_name");
        mat_reg_mother_name = getIntent().getStringExtra("mat_reg_mother_name");
        mat_reg_father_occup = getIntent().getStringExtra("mat_reg_father_occup");
        mat_reg_mother_occup = getIntent().getStringExtra("mat_reg_mother_occup");
        mat_reg_no_brother = getIntent().getStringExtra("mat_reg_no_brother");
        mat_reg_no_sister = getIntent().getStringExtra("mat_reg_no_sister");
        mat_mar_bro = getIntent().getStringExtra("mat_mar_bro");
        mat_mar_sis = getIntent().getStringExtra("mat_mar_sis");
        mat_reg_status = getIntent().getStringExtra("mat_reg_status");
        mreg_fam_type = getIntent().getStringExtra("mreg_fam_type");
        mreg_fam_lpa = getIntent().getStringExtra("mreg_fam_lpa");

    }
    public void setPhysicalDetails()
    {
        getHandicap();
        //catchBasicContactSocialEducationOccupationFamilyDetails();

        mreg_phy_ht=etHgtFt.getText().toString();
        mreg_phy_wt=etWeght.getText().toString();
        mreg_bdy_type=sprBdyTyp.getSelectedItem().toString();
        mreg_blood_grp=sprBldGrp.getSelectedItem().toString();
        mreg_complexion=sprCmplxn.getSelectedItem().toString();
        mreg_handicapped=rbYesOrNo.getText().toString();
        mreg_smoke=sprSmoke.getSelectedItem().toString();
        mreg_drink=sprDrink.getSelectedItem().toString();
        mreg_diet=sprDiet.getSelectedItem().toString();
    }
    @Override
    public void onBackPressed()
    {
        //finish();
        Intent intent = new Intent(FormPhysicalAttributeActivity.this, Home.class);
        intent.putExtra("mat_id",mat_id);
        startActivity(intent);
        FormPhysicalAttributeActivity.this.finish();
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
