package com.LeelaGroup.AgrawalFedration.education;

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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.LeelaGroup.AgrawalFedration.Education_Pojos.RefferencePojo;
import com.LeelaGroup.AgrawalFedration.MyUtility;
import com.LeelaGroup.AgrawalFedration.Network.ApiClient;
import com.LeelaGroup.AgrawalFedration.R;
import com.LeelaGroup.AgrawalFedration.Service.ServiceAPIEducation;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class B_Refference extends AppCompatActivity {

    LinearLayout term_and_condition;
    CheckBox condition;

    ProgressDialog progressDialog;

    String Exam,sess,pd_fname, pd_lname, pd_dob, pd_father_name, pd_mother_name, pd_gender, pd_mob1, pd_mob2, pd_email, pd_addr, pd_pincode, pd_city, pd_state, pd_pwd,fd_fathr_occup, fd_fathr_telephone, fd_fathr_mob, fd_fathr_desig, fd_fathr_income, fd_fathr_pan, fa_mothr_occup, fa_mothr_telephone, fa_mothr_mob, fa_mothr_religion,ed_ssc_board, ed_ssc_name, ed_ssc_roll, ed_ssc_year, ed_ssc_percent, ed_ssc_rank, ed_hsc_board, ed_hsc_name, ed_hsc_roll, ed_hsc_year, ed_hsc_percent, ed_hsc_rank, ed_gd_university, ed_gd_inst, ed_gd_degree_name, ed_gd_year, ed_gd_percent, ed_gd_rank, ed_gd_appear_final, ed_pg_university, ed_pg_inst, ed_pg_degree_name, ed_pg_year, ed_pg_percent, ed_pg_rank, ed_pg_menu,cet_get_facility, cd_cm_from, cd_lang, cd_schedule, cd_exam_center,es_select_exam, es_upsc_exam, es_upsc_prel_year, es_upsc_op_sub, es_upsc_coach_sub, es_upsc_lang, es_state_exam, es_state_prel_year, es_state_prel_state, es_state_op_sub, es_state_coach_sub, es_state_lang, es_past_attn_upsc,other_activity, other_national_level, other_year, other_pos, other_achieve, other_hobbies;

    Button Save;

    String exam,Sess, ref1_name, ref1_addr, ref1_city, ref1_mobile, ref1_telephone, ref1_email, ref2_name, ref2_addr, ref2_city, ref2_mobile, ref2_telephone, ref2_email;

    EditText R_Name1,R_Address1,R_City1,R_Mobile1,R_Telephone1,R_Email1,R_Name2,R_Address2,R_City2,R_Mobile2,R_Telephone2,R_Email2;

    TextInputLayout layout_R_Email1,layout_R_Email2,layout_R_Name1,layout_R_Address1,layout_R_City1,layout_R_Mobile1,layout_R_Telephone1,layout_R_Name2,layout_R_Address2,layout_R_City2,layout_R_Mobile2,layout_R_Telephone2;

    TextView tv_conditons;
LinearLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b__refference);

        layout=(LinearLayout)findViewById(R.id.parent);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_matrimony);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setTitle("Refference");

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Inserting...");

        term_and_condition = (LinearLayout) findViewById(R.id.term_and_condition);

        condition = (CheckBox) findViewById(R.id.condition);

        Intent goto_Refference = getIntent();

        Bundle b = goto_Refference.getExtras();

        Exam = b.getString("myname");
        sess = b.getString("Sess");

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

        fd_fathr_occup = b.getString("Father_occupation");
        fd_fathr_telephone = b.getString("Telephone");
        fd_fathr_mob = b.getString("Father_MobileNo");
        fd_fathr_desig = b.getString("Designation");
        fd_fathr_income = b.getString("Anual_Income");
        fd_fathr_pan = b.getString("PAN");
        fa_mothr_occup = b.getString("Mother_Occupation");
        fa_mothr_telephone = b.getString("Mother_Telephone");
        fa_mothr_mob = b.getString("Mother_MobileNo");
        fa_mothr_religion = b.getString("Religion1");

        ed_ssc_board = b.getString("ssc_board");

        ed_ssc_name = b.getString("SchoolName");
        ed_ssc_roll = b.getString("RollNo");
        ed_ssc_year = b.getString("SSCYear");
        ed_ssc_percent = b.getString("SSC_Percent");
        ed_ssc_rank = b.getString("SSC_Rank");

        ed_hsc_board = b.getString("hsc_board");

        ed_hsc_name = b.getString("collegeName");
        ed_hsc_roll = b.getString("HSC_RollNo");
        ed_hsc_year = b.getString("HSCYear");
        ed_hsc_percent = b.getString("HSC_Percent");
        ed_hsc_rank = b.getString("HSC_Rank");

        ed_gd_university = b.getString("gd_university");

        ed_gd_inst = b.getString("collegeName1");
        ed_gd_degree_name = b.getString("Graduation_RollNo");
        ed_gd_year = b.getString("Graduation_Year");
        ed_gd_percent = b.getString("Graduation_Percent");
        ed_gd_rank = b.getString("Graduation_Rank");

        ed_gd_appear_final = b.getString("gd_appear_final");
        ed_pg_university = b.getString("pg_university");

        ed_pg_inst = b.getString("collegeName2");
        ed_pg_degree_name = b.getString("Poat_Graduation_RollNo");
        ed_pg_year = b.getString("Post_Graduation_Year");
        ed_pg_percent = b.getString("Post_Graduation_Percent");
        ed_pg_rank = b.getString("Post_Graduation_Rank");
        ed_pg_menu = b.getString("pg_menu");

        cd_cm_from=b.getString("output");
        cet_get_facility=b.getString("selectedfacility");
        cd_lang=b.getString("selectedLang");
        cd_exam_center=b.getString("spinnerCenter");
        cd_schedule=b.getString("spinnerSchedule");

       /* es_select_exam=b.getString("output1");
        es_upsc_exam=b.getString("UPSC_exam");
        es_upsc_prel_year=b.getString("spinneryear");
        es_upsc_op_sub=b.getString("EditText02");
        es_upsc_coach_sub=b.getString("EditText03");
        es_upsc_lang=b.getString("Upsc_language");

        es_state_exam=b.getString("State_exam");
        es_state_prel_year=b.getString("spinnerCenter_Year");
        es_state_prel_state=b.getString("spinnerCenterState");
        es_state_op_sub=b.getString("State_Optional_Subject_textArea");
        es_state_coach_sub=b.getString("Main_Optional_Subject1");
        es_state_lang=b.getString("State_language");
        es_past_attn_upsc=b.getString("No_of_attempt");*/

        other_activity=b.getString("Extra_Activity");
        other_national_level=b.getString("Level");
        other_year=b.getString("Year");
        other_pos=b.getString("Position");
        other_achieve=b.getString("AnyOtherAchivment");
        other_hobbies=b.getString("Hobbies");
/*
        condition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                *//*if (((CheckBox) v).isChecked()){
                    term_and_condition.setVisibility(View.VISIBLE);
                }
                else {
                    term_and_condition.setVisibility(View.GONE);
                }*//*
            }
        });*/


        R_Name1=(EditText)findViewById(R.id.R_Name1);
        R_Address1=(EditText)findViewById(R.id.R_Address1);
        R_City1=(EditText)findViewById(R.id.R_City1);
        R_Mobile1=(EditText)findViewById(R.id.R_Mobile1);
        R_Telephone1=(EditText)findViewById(R.id.R_Telephone1);
        R_Name2=(EditText)findViewById(R.id.R_Name2);
        R_Address2=(EditText)findViewById(R.id.R_Address2);
        R_City2=(EditText)findViewById(R.id.R_City2);
        R_Mobile2=(EditText)findViewById(R.id.R_Mobile2);
        R_Telephone2=(EditText)findViewById(R.id.R_Telephone2);
        R_Email1=(EditText)findViewById(R.id.R_Email1);
        R_Email2=(EditText)findViewById(R.id.R_Email2);

        tv_conditons=(TextView)findViewById(R.id.tv_conditions);

        layout_R_Name1=(TextInputLayout)findViewById(R.id.layout_R_Name1);
        layout_R_Address1=(TextInputLayout)findViewById(R.id.layout_R_Address1);
        layout_R_City1=(TextInputLayout)findViewById(R.id.layout_R_City1);
        layout_R_Mobile1=(TextInputLayout)findViewById(R.id.layout_R_Mobile1);
        layout_R_Telephone1=(TextInputLayout)findViewById(R.id.layout_R_Telephone1);
        layout_R_Name2=(TextInputLayout)findViewById(R.id.layout_R_Name2);
        layout_R_Address2=(TextInputLayout)findViewById(R.id.layout_R_Address2);
        layout_R_City2=(TextInputLayout)findViewById(R.id.layout_R_City2);
        layout_R_Mobile2=(TextInputLayout)findViewById(R.id.layout_R_Mobile2);
        layout_R_Telephone2=(TextInputLayout)findViewById(R.id.layout_R_Telephone2);
        layout_R_Email1 = (TextInputLayout)findViewById(R.id.layout_R_Email1);
        layout_R_Email2 = (TextInputLayout)findViewById(R.id.layout_R_Email2);

        R_Name1.addTextChangedListener(new MyTextWatcher(R_Name1));
        R_Address1.addTextChangedListener(new MyTextWatcher(R_Address1));
        R_City1.addTextChangedListener(new MyTextWatcher(R_City1));
        R_Mobile1.addTextChangedListener(new MyTextWatcher(R_Mobile1));
        R_Telephone1.addTextChangedListener(new MyTextWatcher(R_Telephone1));
        R_Name2.addTextChangedListener(new MyTextWatcher(R_Name2));
        R_Address2.addTextChangedListener(new MyTextWatcher(R_Address2));
        R_City2.addTextChangedListener(new MyTextWatcher(R_City2));
        R_Mobile2.addTextChangedListener(new MyTextWatcher(R_Mobile2));
        R_Telephone2.addTextChangedListener(new MyTextWatcher(R_Telephone2));
        R_Email1.addTextChangedListener(new MyTextWatcher(R_Email1));
        R_Email2.addTextChangedListener(new MyTextWatcher(R_Email2));



        // R_Prev=(Button)findViewById(R.id.R_Prev);

        Save =(Button)findViewById(R.id.Save);

        /*R_Prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });*/

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyUtility.hideKeyboard(layout,B_Refference.this);
                if (submitUser()){
                    if (MyUtility.isConnected(B_Refference.this)){
                        RefferenceForm();
                    }else {
                        MyUtility.internetProblem(layout);
                    }
                }
            }
        });


    }

    @Override
    public void onBackPressed()
    {
        finish();
        Intent intent = new Intent(B_Refference.this, Registration.class);
        startActivity(intent);
    }



    private boolean submitUser()
    {
        /*if(!validateR_Name1())
        {return false;}
        if(!validateR_Address1())
        {return false;}
        if(!validateR_City1())
        {return false;}
        if(!validateR_Mobile1())
        {return false;}
        if(!validateR_Telephone1())
        {return false;}
        if(!validateR_Name2())
        {return false;}
        if(!validateR_Address2())
        {return false;}
        if(!validateR_City2())
        {return false;}
        if(!validateR_Mobile2())
        {return false;}
        if(!validateR_Telephone2())
        {return false;}
        */
        condition.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    tv_conditons.setError(null);
                }
                else {
                    tv_conditons.requestFocus();
                    tv_conditons.setError("Please Accept Terms And Conditions");
                }
            }
        });
        if (!condition.isChecked()){
            tv_conditons.requestFocus();
            tv_conditons.setError("Please Accept Terms And Conditions");
           // Toast.makeText(this, "Please Accept Terms And Conditions", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private boolean validateR_Name1() {
        if (R_Name1.getText().toString().trim().isEmpty())
        {
            R_Name1.setError("Enter name");
            requestFocus(R_Name1);
            return false;
        }else{
            layout_R_Name1.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateR_Name2() {

        if (R_Name2.getText().toString().trim().isEmpty())
        {
            R_Name2.setError("Enter name");
            requestFocus(R_Name2);
            return false;
        }else{
            layout_R_Name2.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateR_Address1() {
        if (R_Address1.getText().toString().trim().isEmpty())
        {
            R_Address1.setError("Enter address");
            requestFocus(R_Address1);
            return false;
        }else{
            layout_R_Address1.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateR_Address2() {
        if (R_Address2.getText().toString().trim().isEmpty())
        {
            R_Address2.setError("Enter address");
            requestFocus(R_Address2);
            return false;
        }else{
            layout_R_Address2.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateR_City1() {

        if (R_City1.getText().toString().trim().isEmpty())
        {
            R_City1.setError("Enter city");
            requestFocus(R_City1);
            return false;
        }else{
            layout_R_City1.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateR_City2() {
        if (R_City2.getText().toString().trim().isEmpty())
        {
            R_City2.setError("Enter City");
            requestFocus(R_City2);
            return false;
        }else{
            layout_R_City2.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateR_Mobile1() {
        String R_Mobile11 = R_Mobile1.getText().toString().trim();

        if (R_Mobile11.isEmpty() && R_Mobile11.length()<10 && R_Mobile11.matches("[7-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]"))
        {
            R_Mobile1.setError("Enter valid mobile number");

            requestFocus(R_Mobile1);

            return false;
        }else{
            layout_R_Mobile1.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateR_Mobile2() {

        String R_Mobile21 = R_Mobile2.getText().toString().trim();

        if (R_Mobile21.isEmpty() && R_Mobile21.length()<10 && R_Mobile21.matches("[7-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]"))
        {
            R_Mobile2.setError("Enter valid mobile number");

            requestFocus(R_Mobile2);

            return false;
        }else{
            layout_R_Mobile2.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateR_Telephone1() {

        String R_Telephone11 = R_Telephone1.getText().toString().trim();

        if (R_Telephone11.isEmpty() && R_Telephone11.length()<11)
        {
            R_Telephone1.setError("Enter valid telephone number");

            requestFocus(R_Telephone1);

            return false;
        }else{
            layout_R_Telephone1.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateR_Telephone2() {
        String R_Telephone21 = R_Telephone2.getText().toString().trim();

        if (R_Telephone21.isEmpty() && R_Telephone21.length()<11)
        {
            R_Telephone2.setError("Enter valid telephone number");

            requestFocus(R_Telephone2);

            return false;
        }else{
            layout_R_Telephone2.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateEmail() {

        String email1 = R_Email1.getText().toString().trim();

        if (email1.isEmpty() || !isValidateEmail(email1)){

            layout_R_Email1.setError(getString(R.string.err_msg_email));

            R_Email1.requestFocus();

            return false;
        }else{

            layout_R_Email1.setErrorEnabled(false);
        }

        return true;
    }

    private boolean isValidateEmail(String email1) {

        return !TextUtils.isEmpty(email1) && android.util.Patterns.EMAIL_ADDRESS.matcher(email1).matches();
    }

    private boolean validateEmail2() {

        String email2 = R_Email2.getText().toString().trim();

        if (email2.isEmpty() || !isValidateEmail(email2)){

            layout_R_Email2.setError(getString(R.string.err_msg_email));

            R_Email2.requestFocus();

            return false;
        }else{

            layout_R_Email2.setErrorEnabled(false);
        }

        return true;
    }

    private boolean isValidateEmail2(String email2) {

        return !TextUtils.isEmpty(email2) && android.util.Patterns.EMAIL_ADDRESS.matcher(email2).matches();
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
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable editable) {

            switch(view.getId())
            {
                case R.id.R_Telephone1 : validateR_Telephone1();
                    break;

                case R.id.R_Telephone2 :validateR_Telephone2();
                    break;

                case R.id.R_Mobile2 : validateR_Mobile2();
                    break;

                case R.id.R_Mobile1:validateR_Mobile1();
                    break;

                case R.id.R_Email1 : validateEmail();
                    break;


                case R.id.R_Email2 : validateEmail();
                    break;
                    /*
                case R.id.Religion1 : validateReligion1();
                    break;*/

            }

        }
    }

    private void RefferenceForm() {

        progressDialog.show();

        ServiceAPIEducation service = ApiClient.getRetrofit().create(ServiceAPIEducation.class);
        RefferencePojo refference = new RefferencePojo();

        exam=Exam;
        Sess = sess;
        ref1_name=R_Name1.getText().toString();
        ref1_addr=R_Address1.getText().toString();
        ref1_city=R_City1.getText().toString();
        ref1_mobile=R_Mobile1.getText().toString();
        ref1_telephone=R_Telephone1.getText().toString();
        ref1_email=R_Email1.getText().toString();
        ref2_name=R_Name2.getText().toString();
        ref2_addr=R_Address2.getText().toString();
        ref2_city=R_City2.getText().toString();
        ref2_mobile=R_Mobile2.getText().toString();
        ref2_telephone=R_Telephone2.getText().toString();
        ref2_email=R_Email2.getText().toString();

        refference.setExam(exam);
        refference.setSess(Sess);
        refference.setRef1_name(ref1_name);
        refference.setRef1_addr(ref1_addr);
        refference.setRef1_city(ref1_city);
        refference.setRef1_mobile(ref1_mobile);
        refference.setRef1_telephone(ref1_telephone);
        refference.setRef1_email(ref1_email);
        refference.setRef2_name(ref2_name);
        refference.setRef2_addr(ref2_addr);
        refference.setRef2_city(ref2_city);
        refference.setRef2_mobile(ref2_mobile);
        refference.setRef2_telephone(ref2_telephone);
        refference.setRef2_email(ref2_email);

        Call<RefferencePojo> rd = service.setRefferenceDetails(exam,sess,pd_fname, pd_lname, pd_dob, pd_father_name, pd_mother_name, pd_gender, pd_mob1, pd_mob2, pd_email, pd_addr, pd_pincode, pd_city, pd_state, pd_pwd,fd_fathr_occup, fd_fathr_telephone, fd_fathr_mob, fd_fathr_desig, fd_fathr_income, fd_fathr_pan, fa_mothr_occup, fa_mothr_telephone, fa_mothr_mob, fa_mothr_religion,ed_ssc_board, ed_ssc_name, ed_ssc_roll, ed_ssc_year, ed_ssc_percent, ed_ssc_rank, ed_hsc_board, ed_hsc_name, ed_hsc_roll, ed_hsc_year, ed_hsc_percent, ed_hsc_rank, ed_gd_university, ed_gd_inst, ed_gd_degree_name, ed_gd_year, ed_gd_percent, ed_gd_rank, ed_gd_appear_final, ed_pg_university, ed_pg_inst, ed_pg_degree_name, ed_pg_year, ed_pg_percent, ed_pg_rank, ed_pg_menu,cet_get_facility, cd_cm_from, cd_lang, cd_schedule, cd_exam_center,es_select_exam, es_upsc_exam, es_upsc_prel_year, es_upsc_op_sub, es_upsc_coach_sub, es_upsc_lang, es_state_exam, es_state_prel_year, es_state_prel_state, es_state_op_sub, es_state_coach_sub, es_state_lang, es_past_attn_upsc,other_activity, other_national_level, other_year, other_pos, other_achieve, other_hobbies,ref1_name, ref1_addr, ref1_city, ref1_mobile, ref1_telephone, ref1_email, ref2_name, ref2_addr, ref2_city, ref2_mobile, ref2_telephone, ref2_email);

        rd.enqueue(new Callback<RefferencePojo>() {
            @Override
            public void onResponse(Call<RefferencePojo> call, Response<RefferencePojo> response) {
               // Toast.makeText(B_Refference.this, "success", Toast.LENGTH_SHORT).show();
                Intent Refference = new Intent(B_Refference.this,Education.class);
                Refference.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(Refference);
                B_Refference.this.finish();

                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<RefferencePojo> call, Throwable t) {
                Toast.makeText(B_Refference.this, getText(R.string.checkConn), Toast.LENGTH_SHORT).show();

                progressDialog.dismiss();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case android.R.id.home:

                onBackPressed();

                return  true;
        }

        return super.onOptionsItemSelected(item);
    }
}
