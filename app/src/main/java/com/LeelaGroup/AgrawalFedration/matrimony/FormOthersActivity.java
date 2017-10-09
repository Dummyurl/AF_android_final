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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.LeelaGroup.AgrawalFedration.Home;
import com.LeelaGroup.AgrawalFedration.MatrimonySession;
import com.LeelaGroup.AgrawalFedration.R;
import com.LeelaGroup.AgrawalFedration.matrimony.validation.CustomValidator;

import java.io.File;

public class FormOthersActivity extends AppCompatActivity {
    private GestureDetector gestureDetector;
    Spinner sprWhrHearUS, sprPrflCrtBy;
    CheckBox cbRead, cbDance, cbPlay, cbListnSong, cbActing;
    EditText etOther, etintrest;
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
    String mat_reg_occup, mat_reg_industry, mat_reg_empl, mat_reg_ipa;

    //declare var for family details
    String mat_reg_father_name, mat_reg_mother_name, mat_reg_father_occup, mat_reg_mother_occup, mat_reg_no_brother, mat_reg_no_sister, mat_mar_bro, mat_mar_sis, mat_reg_status, mreg_fam_type, mreg_fam_lpa;

    //declare var for physical details
    String mreg_phy_ht, mreg_phy_wt, mreg_bdy_type, mreg_blood_grp, mreg_complexion, mreg_handicapped, mreg_smoke, mreg_drink, mreg_diet;

    String hobbies, mat_id;
    String mreg_come_frm, mreg_prof_create_for, mreg_hobby, mreg_interest, mreg_pwd="";

    float dX;
    float dY;
    int lastAction;

    MatrimonySession matrimonySession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_others);
        matrimonySession = new MatrimonySession(getApplicationContext());

        init();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Other Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        if (matrimonySession.checkLogin())
            finish();

        catchBasicContactSocialEducationOccupationFamilyPhysicalDetails();

        getSpinnerdata();
    }

    private void getSpinnerdata() {
        ArrayAdapter<CharSequence> whruhrarray = ArrayAdapter.createFromResource(this, R.array.where_u_hear, R.layout.spinner_text);
        whruhrarray.setDropDownViewResource(android.R.layout.simple_list_item_1);
        sprWhrHearUS.setAdapter(whruhrarray);

        ArrayAdapter<CharSequence> prcrarray = ArrayAdapter.createFromResource(this, R.array.profile_created_for, R.layout.spinner_text);
        prcrarray.setDropDownViewResource(android.R.layout.simple_list_item_1);
        sprPrflCrtBy.setAdapter(prcrarray);

    }
    public boolean validateFirst() {

        hobbies = etOther.getText().toString();
        if (cbRead.isChecked()) {
            hobbies = hobbies + "," + cbRead.getText();
        }
        if (cbDance.isChecked()) {
            hobbies = hobbies + "," + cbDance.getText();
        }
        if (cbListnSong.isChecked()) {
            hobbies = hobbies + "," + cbListnSong.getText();
        }
        if (cbActing.isChecked()) {
            hobbies = hobbies + "," + cbActing.getText();
        }
        if (cbPlay.isChecked()) {
            hobbies = hobbies + "," + cbPlay.getText();
        }


        CustomValidator validator = new CustomValidator();
        final String wherhearus = sprWhrHearUS.getSelectedItem().toString();
        if (!validator.isEmptyField(wherhearus)) {
            return false;
        }
        final String prflcrtby = sprPrflCrtBy.getSelectedItem().toString();
        if (!validator.isEmptyField(prflcrtby)) {
            return false;
        }

        return true;
    }


    private void init() {
//        password = (EditText) findViewById(R.id.frm_other_et_pass);
//        cnfpassword = (EditText) findViewById(R.id.frm_other_et_cnfpssword);
        etOther = (EditText) findViewById(R.id.frm_other_et_hobbies);
        etintrest = (EditText) findViewById(R.id.frm_other_et_interst);
        sprWhrHearUS = (Spinner) findViewById(R.id.frm_other_spr_whreuhereus);
        sprPrflCrtBy = (Spinner) findViewById(R.id.frm_other_spr_prflcrteby);
        cbRead = (CheckBox) findViewById(R.id.frm_other_ckbx_hobbies_reading);
        cbDance = (CheckBox) findViewById(R.id.frm_other_ckbx_hobbies_dancing);
        cbListnSong = (CheckBox) findViewById(R.id.frm_other_ckbx_hobbies_listinigsongs);
        cbActing = (CheckBox) findViewById(R.id.frm_other_ckbx_hobbies_acting);
        cbPlay = (CheckBox) findViewById(R.id.frm_other_ckbx_hobbies_playing);
    }

    public void goToFormPhysicalAttribute(View v) {
        startActivity(new Intent(this, FormPhysicalAttributeActivity.class));
    }

    public void goToFormPartnerPreference(View v) {
        if (validateFirst()) {
            setOtherDetails();
            Intent intent = new Intent(FormOthersActivity.this, FormPartnerPreferenceActivity.class);
            intent.putExtra("mat_id", mat_id);

            //basic detail
            intent.putExtra("imageFile", imageFile);
            intent.putExtra("mreg_am", mreg_am);
            intent.putExtra("mreg_fname", mreg_fname);
            intent.putExtra("mreg_mname", mreg_mname);
            intent.putExtra("mreg_lname", mreg_lname);
            intent.putExtra("mreg_birth_place", mreg_birth_place);
            intent.putExtra("mreg_birth_time", mreg_birth_time);
            intent.putExtra("mreg_dob", mreg_dob);
            intent.putExtra("mreg_age", mreg_age);
            intent.putExtra("mreg_marital_status", mreg_marital_status);
            intent.putExtra("mreg_native_place", mreg_native_place);
            intent.putExtra("mreg_gender", mreg_gender);
            intent.putExtra("mreg_no_child", mreg_no_child);
            intent.putExtra("mreg_child_leave_status", mreg_child_leave_status);
            intent.putExtra("mreg_mother_tongue", mreg_mother_tongue);
            intent.putExtra("mreg_about_me", mreg_about_me);
            intent.putExtra("mat_reg_religion", mat_reg_religion);
            intent.putExtra("mat_reg_caste", mat_reg_caste);
            intent.putExtra("mat_reg_subcaste", mat_reg_subcaste);

            //contact details
            intent.putExtra("mreg_landline", mreg_landline);
            intent.putExtra("mreg_phone", mreg_phone);
            intent.putExtra("mreg_email", mreg_email);
            intent.putExtra("mreg_addr", mreg_addr);
            intent.putExtra("mreg_country", mreg_country);
            intent.putExtra("mreg_state", mreg_state);
            intent.putExtra("mreg_city", mreg_city);
            intent.putExtra("mreg_pincode", mreg_pincode);
            intent.putExtra("mreg_resid_status", mreg_resid_status);

            //social attribute details
            intent.putExtra("mat_reg_manglik", mat_reg_manglik);
            intent.putExtra("mat_reg_horoscope_match", mat_reg_horoscope_match);
            intent.putExtra("mat_reg_gothra_self", mat_reg_gothra_self);
            //intent.putExtra("mat_reg_gothra_mama",mat_reg_gothra_mama);

            //education details
            intent.putExtra("mat_reg_edu", mat_reg_edu);
//            intent.putExtra("mat_reg_pg",mat_reg_pg);
//            intent.putExtra("mat_reg_docto",mat_reg_docto);
//            intent.putExtra("mat_reg_certifi",mat_reg_certifi);
            //occupation details
            intent.putExtra("mat_reg_occup", mat_reg_occup);
            intent.putExtra("mat_reg_industry", mat_reg_industry);
            intent.putExtra("mat_reg_empl", mat_reg_empl);
            intent.putExtra("mat_reg_ipa", mat_reg_ipa);

            //family details
            intent.putExtra("mat_reg_father_name", mat_reg_father_name);
            intent.putExtra("mat_reg_mother_name", mat_reg_mother_name);
            intent.putExtra("mat_reg_father_occup", mat_reg_father_occup);
            intent.putExtra("mat_reg_mother_occup", mat_reg_mother_occup);
            intent.putExtra("mat_reg_no_brother", mat_reg_no_brother);
            intent.putExtra("mat_reg_no_sister", mat_reg_no_sister);
            intent.putExtra("mat_mar_bro", mat_mar_bro);
            intent.putExtra("mat_mar_sis", mat_mar_sis);
            intent.putExtra("mat_reg_status", mat_reg_status);
            intent.putExtra("mreg_fam_type", mreg_fam_type);
            intent.putExtra("mreg_fam_lpa", mreg_fam_lpa);

            //physical details
            intent.putExtra("mreg_phy_ht", mreg_phy_ht);
            intent.putExtra("mreg_phy_wt", mreg_phy_wt);
            intent.putExtra("mreg_bdy_type", mreg_bdy_type);
            intent.putExtra("mreg_blood_grp", mreg_blood_grp);
            intent.putExtra("mreg_complexion", mreg_complexion);
            intent.putExtra("mreg_complexion", mreg_complexion);
            intent.putExtra("mreg_handicapped", mreg_handicapped);
            intent.putExtra("mreg_smoke", mreg_smoke);
            intent.putExtra("mreg_drink", mreg_drink);
            intent.putExtra("mreg_diet", mreg_diet);

            //other details
            intent.putExtra("mreg_come_frm", mreg_come_frm);
            intent.putExtra("mreg_prof_create_for", mreg_prof_create_for);
            intent.putExtra("mreg_smoke", mreg_smoke);
            intent.putExtra("mreg_hobby", mreg_hobby);
            intent.putExtra("mreg_interest", mreg_interest);
            intent.putExtra("mreg_pwd", mreg_pwd);
            startActivity(intent);
            this.finish();
            //startActivity(new Intent(this,FormPartnerPreferenceActivity.class));
        }
    }

    public void catchBasicContactSocialEducationOccupationFamilyPhysicalDetails() {

        mat_id = getIntent().getStringExtra("mat_id");
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

        //physical info data
        mreg_phy_ht = getIntent().getStringExtra("mreg_phy_ht");
        mreg_phy_wt = getIntent().getStringExtra("mreg_phy_wt");
        mreg_bdy_type = getIntent().getStringExtra("mreg_bdy_type");
        mreg_blood_grp = getIntent().getStringExtra("mreg_blood_grp");
        mreg_complexion = getIntent().getStringExtra("mreg_complexion");
        mreg_handicapped = getIntent().getStringExtra("mreg_handicapped");
        mreg_smoke = getIntent().getStringExtra("mreg_smoke");
        mreg_drink = getIntent().getStringExtra("mreg_drink");
        mreg_diet = getIntent().getStringExtra("mreg_diet");

    }

    public void setOtherDetails() {
        //catchBasicContactSocialEducationOccupationFamilyPhysicalDetails();

        mreg_come_frm = sprWhrHearUS.getSelectedItem().toString();
        mreg_prof_create_for = sprPrflCrtBy.getSelectedItem().toString();
        mreg_hobby = hobbies;
        mreg_interest = etintrest.getText().toString();
        //mreg_pwd = password.getText().toString();

    }

    @Override
    public void onBackPressed() {
        //finish();
        Intent intent = new Intent(FormOthersActivity.this, Home.class);
        intent.putExtra("mat_id", mat_id);
        startActivity(intent);
        FormOthersActivity.this.finish();
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
