package com.LeelaGroup.AgrawalFedration.education;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.regex.*;

import com.LeelaGroup.AgrawalFedration.Education_Pojos.EducationDetailPojo;
import com.LeelaGroup.AgrawalFedration.Network.ApiClient;
import com.LeelaGroup.AgrawalFedration.R;
import com.LeelaGroup.AgrawalFedration.Service.ServiceAPIEducation;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EducationDetails extends AppCompatActivity {

    String Exam, sess, pd_fname, pd_lname, pd_dob, pd_father_name, pd_mother_name, pd_gender, pd_mob1, pd_mob2, pd_email, pd_addr, pd_pincode, pd_city, pd_state, pd_pwd, fd_fathr_occup, fd_fathr_telephone, fd_fathr_mob, fd_fathr_desig, fd_fathr_income, fd_fathr_pan, fa_mothr_occup, fa_mothr_telephone, fa_mothr_mob, fa_mothr_religion;

    ProgressDialog progressDialog;

    RadioGroup graduation_final, post_graduation_final;

    RadioButton selectedGraduation, selectedPostGraduation;

    Spinner spinnerstate, HSC_spinnerstate, Graduation_spinnerstate, Post_Graduation_spinnerstate;

    String examination, Session, ed_ssc_board, ed_ssc_name, ed_ssc_roll, ed_ssc_year, ed_ssc_percent, ed_ssc_rank, ed_hsc_board, ed_hsc_name, ed_hsc_roll, ed_hsc_year, ed_hsc_percent, ed_hsc_rank, ed_gd_university, ed_gd_inst, ed_gd_degree_name, ed_gd_year, ed_gd_percent, ed_gd_rank, ed_gd_appear_final, ed_pg_university, ed_pg_inst, ed_pg_degree_name, ed_pg_year, ed_pg_percent, ed_pg_rank, ed_pg_menu;

    EditText SchoolName, RollNo, SSCYear, SSC_Percent, SSC_Rank, collegeName, HSC_RollNo, HSCYear, HSC_Percent, HSC_Rank, collegeName1, Graduation_RollNo, Graduation_Year, Graduation_Percent, Graduation_Rank, collegeName2, Poat_Graduation_RollNo, Post_Graduation_Year, Post_Graduation_Percent, Post_Graduation_Rank;

    TextInputLayout layout_SchoolName, layout_RollNo, layout_SSCYear, layout_SSC_Percent, layout_SSC_Rank, layout_collegeName, layout_HSC_RollNo, layout_HSCYear, layout_HSC_Percent, layout_HSC_Rank, layout_collegeName1, layout_Graduation_RollNo, layout_Graduation_Year, layout_Graduation_Percent, layout_Graduation_Rank, layout_collegeName2, layout_Poat_Graduation_RollNo, layout_Post_Graduation_Year, layout_Post_Graduation_Percent, layout_Post_Graduation_Rank;
    Button E_Prev;

    String CET = "CET";
    String BEAT = "BEAT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_matrimony);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setTitle("Education Details");

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Inserting...");

        Intent goto_Family = getIntent();

        Bundle b = goto_Family.getExtras();

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

        SchoolName = (EditText) findViewById(R.id.SchoolName);
        RollNo = (EditText) findViewById(R.id.RollNo);
        SSCYear = (EditText) findViewById(R.id.SSCYear);
        SSC_Percent = (EditText) findViewById(R.id.SSC_Percent);
        SSC_Rank = (EditText) findViewById(R.id.SSC_Rank);
        collegeName = (EditText) findViewById(R.id.collegeName);
        HSC_RollNo = (EditText) findViewById(R.id.HSC_RollNo);
        HSCYear = (EditText) findViewById(R.id.HSCYear);
        HSC_Percent = (EditText) findViewById(R.id.HSC_Percent);
        HSC_Rank = (EditText) findViewById(R.id.HSC_Rank);
        collegeName1 = (EditText) findViewById(R.id.collegeName1);
        Graduation_RollNo = (EditText) findViewById(R.id.Graduation_RollNo);
        Graduation_Year = (EditText) findViewById(R.id.Graduation_Year);
        Graduation_Percent = (EditText) findViewById(R.id.Graduation_Percent);
        Graduation_Rank = (EditText) findViewById(R.id.Graduation_Rank);
        collegeName2 = (EditText) findViewById(R.id.collegeName2);
        Poat_Graduation_RollNo = (EditText) findViewById(R.id.Poat_Graduation_RollNo);
        Post_Graduation_Year = (EditText) findViewById(R.id.Post_Graduation_Year);
        Post_Graduation_Percent = (EditText) findViewById(R.id.Post_Graduation_Percent);
        Post_Graduation_Rank = (EditText) findViewById(R.id.Post_Graduation_Rank);

        spinnerstate = (Spinner) findViewById(R.id.spinnerstate);
        HSC_spinnerstate = (Spinner) findViewById(R.id.HSC_spinnerstate);
        Graduation_spinnerstate = (Spinner) findViewById(R.id.Graduation_spinnerstate);
        Post_Graduation_spinnerstate = (Spinner) findViewById(R.id.Post_Graduation_spinnerstate);

        graduation_final = (RadioGroup) findViewById(R.id.graduation_final);
        post_graduation_final = (RadioGroup) findViewById(R.id.post_graduation_final);


        layout_SchoolName = (TextInputLayout) findViewById(R.id.layout_SchoolName);
        layout_RollNo = (TextInputLayout) findViewById(R.id.layout_RollNo);
        layout_SSCYear = (TextInputLayout) findViewById(R.id.layout_SSCYear);
        layout_SSC_Percent = (TextInputLayout) findViewById(R.id.layout_SSC_Percent);
        layout_SSC_Rank = (TextInputLayout) findViewById(R.id.layout_SSC_Rank);
        layout_collegeName = (TextInputLayout) findViewById(R.id.layout_collegeName);
        layout_HSC_RollNo = (TextInputLayout) findViewById(R.id.layout_HSC_RollNo);
        layout_HSCYear = (TextInputLayout) findViewById(R.id.layout_HSCYear);
        layout_HSC_Percent = (TextInputLayout) findViewById(R.id.layout_HSC_Percent);
        layout_HSC_Rank = (TextInputLayout) findViewById(R.id.layout_HSC_Rank);
        layout_collegeName1 = (TextInputLayout) findViewById(R.id.layout_collegeName1);
        layout_Graduation_RollNo = (TextInputLayout) findViewById(R.id.layout_Graduation_RollNo);
        layout_Graduation_Year = (TextInputLayout) findViewById(R.id.layout_Graduation_Year);
        layout_Graduation_Percent = (TextInputLayout) findViewById(R.id.layout_Graduation_Percent);
        layout_Graduation_Rank = (TextInputLayout) findViewById(R.id.layout_Graduation_Rank);
        layout_collegeName2 = (TextInputLayout) findViewById(R.id.layout_collegeName2);
        layout_Poat_Graduation_RollNo = (TextInputLayout) findViewById(R.id.layout_Poat_Graduation_RollNo);
        layout_Post_Graduation_Year = (TextInputLayout) findViewById(R.id.layout_Post_Graduation_Year);
        layout_Post_Graduation_Percent = (TextInputLayout) findViewById(R.id.layout_Post_Graduation_Percent);
        layout_Post_Graduation_Rank = (TextInputLayout) findViewById(R.id.layout_Post_Graduation_Rank);


        SchoolName.addTextChangedListener(new MyTextWatcher(SchoolName));
        RollNo.addTextChangedListener(new MyTextWatcher(RollNo));
        SSCYear.addTextChangedListener(new MyTextWatcher(SSCYear));
        SSC_Percent.addTextChangedListener(new MyTextWatcher(SSC_Percent));
        SSC_Rank.addTextChangedListener(new MyTextWatcher(SSC_Rank));
        collegeName.addTextChangedListener(new MyTextWatcher(collegeName));
        HSC_RollNo.addTextChangedListener(new MyTextWatcher(HSC_RollNo));
        HSCYear.addTextChangedListener(new MyTextWatcher(HSCYear));
        HSC_Percent.addTextChangedListener(new MyTextWatcher(HSC_Percent));
        HSC_Rank.addTextChangedListener(new MyTextWatcher(HSC_Rank));
        collegeName1.addTextChangedListener(new MyTextWatcher(collegeName1));
        Graduation_RollNo.addTextChangedListener(new MyTextWatcher(Graduation_RollNo));
        Graduation_Year.addTextChangedListener(new MyTextWatcher(Graduation_Year));
        Graduation_Percent.addTextChangedListener(new MyTextWatcher(Graduation_Percent));
        Graduation_Rank.addTextChangedListener(new MyTextWatcher(Graduation_Rank));
        collegeName2.addTextChangedListener(new MyTextWatcher(collegeName2));
        Poat_Graduation_RollNo.addTextChangedListener(new MyTextWatcher(Poat_Graduation_RollNo));
        Post_Graduation_Year.addTextChangedListener(new MyTextWatcher(Post_Graduation_Year));
        Post_Graduation_Percent.addTextChangedListener(new MyTextWatcher(Post_Graduation_Percent));
        Post_Graduation_Rank.addTextChangedListener(new MyTextWatcher(Post_Graduation_Rank));



       /* E_Prev=(Button)findViewById(R.id.E_Prev);

        E_Prev.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });*/


        getSpinnerdata();
    }

    private void getSpinnerdata() {
        ArrayAdapter<CharSequence> sprstarray = ArrayAdapter.createFromResource(this, R.array.Board_array, R.layout.spinner_text);
        sprstarray.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spinnerstate.setAdapter(sprstarray);

        ArrayAdapter<CharSequence> hscstarray = ArrayAdapter.createFromResource(this, R.array.Board_array, R.layout.spinner_text);
        hscstarray.setDropDownViewResource(android.R.layout.simple_list_item_1);
        HSC_spinnerstate.setAdapter(hscstarray);

        ArrayAdapter<CharSequence> gradstarray = ArrayAdapter.createFromResource(this, R.array.University_array, R.layout.spinner_text);
        gradstarray.setDropDownViewResource(android.R.layout.simple_list_item_1);
        Graduation_spinnerstate.setAdapter(gradstarray);

        ArrayAdapter<CharSequence> pgstarray = ArrayAdapter.createFromResource(this, R.array.University_array, R.layout.spinner_text);
        pgstarray.setDropDownViewResource(android.R.layout.simple_list_item_1);
        Post_Graduation_spinnerstate.setAdapter(pgstarray);
    }

    public void Bundle() {
        int selectedGenderId = graduation_final.getCheckedRadioButtonId();
        selectedGraduation = (RadioButton) findViewById(selectedGenderId);

        int selectedPgFinal = graduation_final.getCheckedRadioButtonId();
        selectedPostGraduation = (RadioButton) findViewById(selectedPgFinal);

        examination = Exam;
        Session = sess;
        ed_ssc_board = spinnerstate.getSelectedItem().toString();
        ed_ssc_name = SchoolName.getText().toString();
        ed_ssc_roll = RollNo.getText().toString();
        ed_ssc_year = SSCYear.getText().toString();
        ed_ssc_percent = SSC_Percent.getText().toString();
        ed_ssc_rank = SSC_Rank.getText().toString();
        ed_hsc_board = HSC_spinnerstate.getSelectedItem().toString();
        ed_hsc_name = collegeName.getText().toString();
        ed_hsc_roll = HSC_RollNo.getText().toString();
        ed_hsc_year = HSCYear.getText().toString();
        ed_hsc_percent = HSC_Percent.getText().toString();
        ed_hsc_rank = HSC_Rank.getText().toString();
        ed_gd_university = Graduation_spinnerstate.getSelectedItem().toString();
        ed_gd_inst = collegeName1.getText().toString();
        ed_gd_degree_name = Graduation_RollNo.getText().toString();
        ed_gd_year = Graduation_Year.getText().toString();
        ed_gd_percent = Graduation_Percent.getText().toString();
        ed_gd_rank = Graduation_Rank.getText().toString();
        ed_gd_appear_final = selectedGraduation.getText().toString();
        ed_pg_university = Post_Graduation_spinnerstate.getSelectedItem().toString();
        ed_pg_inst = collegeName2.getText().toString();
        ed_pg_degree_name = Poat_Graduation_RollNo.getText().toString();
        ed_pg_year = Post_Graduation_Year.getText().toString();
        ed_pg_percent = Post_Graduation_Percent.getText().toString();
        ed_pg_rank = Post_Graduation_Rank.getText().toString();
        ed_pg_menu = selectedPostGraduation.getText().toString();

        Bundle b = new Bundle();
        b.putString("myname", Exam);
        b.putString("Sess", sess);
        b.putString("ssc_board", ed_ssc_board);
        b.putString("SchoolName", ed_ssc_name);
        b.putString("RollNo", ed_ssc_roll);
        b.putString("SSCYear", ed_ssc_year);
        b.putString("SSC_Percent", ed_ssc_percent);
        b.putString("SSC_Rank", ed_ssc_rank);
        b.putString("hsc_board", ed_hsc_board);
        b.putString("collegeName", ed_hsc_name);
        b.putString("HSC_RollNo", ed_hsc_roll);
        b.putString("HSCYear", ed_hsc_year);
        b.putString("HSC_Percent", ed_hsc_percent);
        b.putString("HSC_Rank", ed_hsc_rank);
        b.putString("gd_university", ed_gd_university);
        b.putString("collegeName1", ed_gd_inst);
        b.putString("Graduation_RollNo", ed_gd_degree_name);
        b.putString("Graduation_Year", ed_gd_year);
        b.putString("Graduation_Percent", ed_gd_percent);
        b.putString("Graduation_Rank", ed_gd_rank);
        b.putString("gd_appear_final", ed_gd_appear_final);
        b.putString("pg_university", ed_pg_university);
        b.putString("collegeName2", ed_pg_inst);
        b.putString("Poat_Graduation_RollNo", ed_pg_degree_name);
        b.putString("Post_Graduation_Year", ed_pg_year);
        b.putString("Post_Graduation_Percent", ed_pg_percent);
        b.putString("Post_Graduation_Rank", ed_pg_rank);
        b.putString("pg_menu", ed_pg_menu);

        b.putString("myname", Exam);
        b.putString("Sess", sess);
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

        b.putString("Father_occupation", fd_fathr_occup);
        b.putString("Telephone", fd_fathr_telephone);
        b.putString("Father_MobileNo", fd_fathr_mob);
        b.putString("Designation", fd_fathr_desig);
        b.putString("Anual_Income", fd_fathr_income);
        b.putString("PAN", fd_fathr_pan);
        b.putString("Mother_Occupation", fa_mothr_occup);
        b.putString("Mother_Telephone", fa_mothr_telephone);
        b.putString("Mother_MobileNo", fa_mothr_mob);
        b.putString("Religion1", fa_mothr_religion);

        Log.d("Msg", Exam);
        if (Exam.equals(CET)) {
            Intent goto_Exam = new Intent(getApplicationContext(), CET.class);
            goto_Exam.putExtras(b);
            goto_Exam.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(goto_Exam);
            EducationDetails.this.finish();
        } else {
            Intent goto_Exam = new Intent(getApplicationContext(), BEAT.class);
            goto_Exam.putExtras(b);
            goto_Exam.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(goto_Exam);
            EducationDetails.this.finish();
        }
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(EducationDetails.this, Registration.class);
        startActivity(intent);
    }

    private boolean submitUser() {

        /*if(!validateSSC_Rank())
        {return false;}*/
        if (!validateSchoolName()) {
            return false;
        }
        if (!validateRollNo()) {
            return false;
        }
        if (!validateSSCYear()) {
            return false;
        }
        if (!validateSSC_Percent()) {
            return false;
        }

        if (!validatecollegeName()) {
            return false;
        }
        if (!validateHSC_RollNo()) {
            return false;
        }
        if (!validateHSCYear()) {
            return false;
        }
        if (!validateHSC_Percent()) {
            return false;
        }
       /* if(!validateHSC_Rank())
        {return false;}*/

        if (!validatecollegeName1()) {
            return false;
        }
        if (!validateGraduation_RollNo()) {
            return false;
        }
        if (!validateGraduation_Year()) {
            return false;
        }
        if (!validateGraduation_Percent()) {
            return false;
        }


        return true;
    }

    private boolean validateSchoolName() {
        if (SchoolName.getText().toString().trim().isEmpty()) {
            layout_SchoolName.setError("Enter School Name");
            SchoolName.requestFocus();
            SchoolName.setError(null);
            return false;
        } else {
            layout_SchoolName.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateRollNo() {

        if (RollNo.getText().toString().trim().isEmpty()) {
            layout_RollNo.setError("Enter SSC Roll Number");
            RollNo.requestFocus();
            RollNo.setError(null);
            return false;
        } else {
            layout_RollNo.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateSSCYear() {

        String S_Year = SSCYear.getText().toString().trim();
        if (S_Year.length() < 4) {
            layout_SSCYear.setError("Enter SCC Year");
            SSCYear.setError(null);
            SSCYear.requestFocus();

            return false;
        } else {
            layout_SSCYear.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateSSC_Percent() {
        String ssc_Percent = SSC_Percent.getText().toString().trim();
        boolean b = !Pattern.matches("[0-9]{2}[.]{1}[0-9]{2}", ssc_Percent);
        Log.d("message", String.valueOf(b));
        if (!Pattern.matches("[0-9]{2}[.]{1}[0-9]{2}", ssc_Percent)) {
            layout_SSC_Percent.setError("Enter SSC Percentage");
            SSC_Percent.requestFocus();
            SSC_Percent.setError(null);
            return false;
        } else {
            layout_SSC_Percent.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateSSC_Rank() {
        if (SSC_Rank.getText().toString().trim().isEmpty()) {
            layout_SSC_Rank.setError("Enter SSC Rank");
            SSC_Rank.requestFocus();
            SSC_Rank.setError(null);
            return false;
        } else {
            layout_SSC_Rank.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatecollegeName() {

        if (collegeName.getText().toString().trim().isEmpty()) {
            layout_collegeName.setError("Enter HSC College Name");
            collegeName.requestFocus();
            collegeName.setError(null);
            return false;
        } else {
            layout_collegeName.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateHSC_RollNo() {

        if (HSC_RollNo.getText().toString().trim().isEmpty()) {
            layout_HSC_RollNo.setError("Enter HSC Roll Number");
            HSC_RollNo.requestFocus();
            HSC_RollNo.setError(null);
            return false;
        } else {
            layout_HSC_RollNo.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateHSCYear() {

        String H_Year = HSCYear.getText().toString().trim();
        if (H_Year.isEmpty() || H_Year.length() < 4) {
            layout_HSCYear.setError("Enter HSC Year");
            HSCYear.requestFocus();
            HSCYear.setError(null);
            return false;
        } else {
            layout_HSCYear.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateHSC_Percent() {

        String H_Percent = HSC_Percent.getText().toString().trim();
        if (!Pattern.matches("[0-9]{2}[.]{1}[0-9]{2}", H_Percent)) {
            layout_HSC_Percent.setError("Enter HSC Percentage");
            HSC_Percent.requestFocus();
            HSC_Percent.setError(null);
            return false;
        } else {
            layout_HSC_Percent.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateHSC_Rank() {
        if (HSC_Rank.getText().toString().trim().isEmpty()) {
            layout_HSC_Rank.setError("Enter Graduation Rank");
            HSC_Rank.requestFocus();
            return false;
        } else {
            layout_HSC_Rank.setErrorEnabled(false);
        }

        return true;
    }


    private boolean validatecollegeName1() {

        if (collegeName1.getText().toString().trim().isEmpty()) {
            layout_collegeName1.setError("Enter Graduation College Name");
            collegeName1.requestFocus();
            collegeName1.setError(null);
            return false;
        } else {
            layout_collegeName1.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateGraduation_RollNo() {
        if (Graduation_RollNo.getText().toString().trim().isEmpty()) {
            layout_Graduation_RollNo.setError("Enter Graduation Degree Name");
            Graduation_RollNo.requestFocus();
            Graduation_RollNo.setError(null);
            return false;
        } else {
            layout_Graduation_RollNo.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateGraduation_Year() {
        String G_Year = Graduation_Year.getText().toString().trim();
        if (G_Year.isEmpty() || G_Year.length() < 4) {
            layout_Graduation_Year.setError("Enter Graduation Year");
            Graduation_Year.requestFocus();
            Graduation_Year.setError(null);

            return false;
        } else {
            layout_Graduation_Year.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateGraduation_Percent() {
       /* String G_Percent = Graduation_Percent.getText().toString().trim();
        if (!Pattern.matches("[0-9]{2}.[0-9].{2}",G_Percent) )  {
            layout_Graduation_Percent.setError("Enter Graduation percentage");

            Graduation_Percent.requestFocus();

            return false;
        } else {
            layout_Graduation_Percent.setErrorEnabled(false);
        }

        return true;*/

        String G_Percent = Graduation_Percent.getText().toString().trim();

        if (!Pattern.matches("[0-9]{2}[.]{1}[0-9]{2}", G_Percent)) {
            layout_Graduation_Percent.setError("Enter Correct Percentage");
            requestFocus(Graduation_Percent);
            Graduation_Percent.setError(null);
            return false;
        } else {
            layout_Graduation_Percent.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateGraduation_Rank() {
        if (Graduation_Rank.getText().toString().trim().isEmpty()) {
            layout_Graduation_Rank.setError("Enter Graduation Rank");
            Graduation_Rank.requestFocus();
            return false;
        } else {
            layout_Graduation_Rank.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatecollegeName2() {
        if (collegeName2.getText().toString().trim().isEmpty()) {
            layout_collegeName2.setError("Enter Post Graduation College Name");
            collegeName2.requestFocus();
            return false;
        } else {
            layout_collegeName2.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePoat_Graduation_RollNo() {
        if (Poat_Graduation_RollNo.getText().toString().trim().isEmpty()) {
            layout_Poat_Graduation_RollNo.setError("Enter Post Graduation Degree Name");
            Poat_Graduation_RollNo.requestFocus();
            return false;
        } else {
            layout_Poat_Graduation_RollNo.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePost_Graduation_Year() {
        String PG_Year = Post_Graduation_Year.getText().toString().trim();
        if (PG_Year.isEmpty() || PG_Year.length() != 4) {
            layout_Post_Graduation_Year.setError("Enter Post Graduation Year");

            Post_Graduation_Year.requestFocus();

            return false;
        } else {
            layout_Post_Graduation_Year.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePost_Graduation_Percent() {
        String PG_Percent = Post_Graduation_Percent.getText().toString().trim();
        if (!Pattern.matches("[0-9]{2}[.]{1}[0-9]{2}", PG_Percent)) {
            layout_Post_Graduation_Percent.setError("Enter Valid Percentage");
            Post_Graduation_Percent.requestFocus();
            Post_Graduation_Percent.setError(null);

            return false;
        } else {
            layout_Post_Graduation_Percent.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePost_Graduation_Rank() {

        if (Post_Graduation_Rank.getText().toString().trim().isEmpty()) {
            layout_Post_Graduation_Rank.setError("Enter Post Graduation Rank");
            Post_Graduation_Rank.requestFocus();
            return false;
        } else {
            layout_Post_Graduation_Rank.setErrorEnabled(false);
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
        public void onTextChanged(CharSequence s, int i, int i2, int i3) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.SchoolName:
                    validateSchoolName();
                    break;
                case R.id.RollNo:
                    validateRollNo();
                    break;
                case R.id.SSCYear:
                    validateSSCYear();
                    break;
                case R.id.SSC_Percent:
                    validateSSC_Percent();
                    break;
               /* case R.id.SSC_Rank:validateSSC_Rank();
                    break;*/


                case R.id.collegeName:
                    validatecollegeName();
                    break;
                case R.id.HSC_RollNo:
                    validateHSC_RollNo();
                    break;
                case R.id.HSCYear:
                    validateHSCYear();
                    break;
                case R.id.HSC_Percent:
                    validateHSC_Percent();
                    break;
              /*  case R.id.HSC_Rank:validateHSC_Rank();
                    break;*/

                case R.id.collegeName1:
                    validatecollegeName1();
                    break;
                case R.id.Graduation_RollNo:
                    validateGraduation_RollNo();
                    break;
                case R.id.Graduation_Year:
                    validateGraduation_Year();
                    break;
                case R.id.Graduation_Percent:
                    validateGraduation_Percent();
                    break;
               /* case R.id.Graduation_Rank:validateGraduation_Rank();
                    break;*/

               /* case R.id.collegeName2:validatecollegeName2();
                    break;
                case R.id.Poat_Graduation_RollNo:validatePoat_Graduation_RollNo();
                    break;
                case R.id.Post_Graduation_Year:validatePost_Graduation_Year();
                    break;*/
                case R.id.Post_Graduation_Percent:
                    validatePost_Graduation_Percent();
                    break;
               /* case R.id.Post_Graduation_Rank:validatePost_Graduation_Rank();
                    break;*/

            }

        }
    }

    public void startSecond(View v) {
        if (submitUser()) {
            //EducationDetailsForm();

            Bundle();

            // startActivity(new Intent(EducationDetails.this,CET.class));
        }
    }

    private void EducationDetailsForm() {

        progressDialog.show();

        int selectedGenderId = graduation_final.getCheckedRadioButtonId();
        selectedGraduation = (RadioButton) findViewById(selectedGenderId);

        int selectedPgFinal = graduation_final.getCheckedRadioButtonId();
        selectedPostGraduation = (RadioButton) findViewById(selectedPgFinal);

        ServiceAPIEducation service = ApiClient.getRetrofit().create(ServiceAPIEducation.class);
        EducationDetailPojo EDP = new EducationDetailPojo();

        examination = Exam;
        Session = sess;
        ed_ssc_board = spinnerstate.getSelectedItem().toString();
        ed_ssc_name = SchoolName.getText().toString();
        ed_ssc_roll = RollNo.getText().toString();
        ed_ssc_year = SSCYear.getText().toString();
        ed_ssc_percent = SSC_Percent.getText().toString();
        ed_ssc_rank = SSC_Rank.getText().toString();
        ed_hsc_board = HSC_spinnerstate.getSelectedItem().toString();
        ed_hsc_name = collegeName.getText().toString();
        ed_hsc_roll = HSC_RollNo.getText().toString();
        ed_hsc_year = HSCYear.getText().toString();
        ed_hsc_percent = HSC_Percent.getText().toString();
        ed_hsc_rank = HSC_Rank.getText().toString();
        ed_gd_university = Graduation_spinnerstate.getSelectedItem().toString();
        ed_gd_inst = collegeName1.getText().toString();
        ed_gd_degree_name = Graduation_RollNo.getText().toString();
        ed_gd_year = Graduation_Year.getText().toString();
        ed_gd_percent = Graduation_Percent.getText().toString();
        ed_gd_rank = Graduation_Rank.getText().toString();
        ed_gd_appear_final = selectedGraduation.getText().toString();
        ed_pg_university = Post_Graduation_spinnerstate.getSelectedItem().toString();
        ed_pg_inst = collegeName2.getText().toString();
        ed_pg_degree_name = Poat_Graduation_RollNo.getText().toString();
        ed_pg_year = Post_Graduation_Year.getText().toString();
        ed_pg_percent = Post_Graduation_Percent.getText().toString();
        ed_pg_rank = Post_Graduation_Rank.getText().toString();
        ed_pg_menu = selectedPostGraduation.getText().toString();


        EDP.setExam(examination);
        EDP.setSess(Session);
        EDP.setEd_ssc_board(ed_ssc_board);
        EDP.setEd_ssc_name(ed_ssc_name);
        EDP.setEd_ssc_roll(ed_ssc_roll);
        EDP.setEd_ssc_year(ed_ssc_year);
        EDP.setEd_ssc_percent(ed_ssc_percent);
        EDP.setEd_ssc_rank(ed_ssc_rank);
        EDP.setEd_hsc_board(ed_hsc_board);
        EDP.setEd_hsc_name(ed_hsc_name);
        EDP.setEd_hsc_roll(ed_hsc_roll);
        EDP.setEd_hsc_year(ed_hsc_year);
        EDP.setEd_hsc_percent(ed_hsc_percent);
        EDP.setEd_hsc_rank(ed_hsc_rank);
        EDP.setEd_gd_university(ed_gd_university);
        EDP.setEd_gd_inst(ed_gd_inst);
        EDP.setEd_gd_degree_name(ed_gd_degree_name);
        EDP.setEd_gd_year(ed_gd_year);
        EDP.setEd_gd_percent(ed_gd_percent);
        EDP.setEd_gd_rank(ed_gd_rank);
        EDP.setEd_gd_appear_final(ed_gd_appear_final);
        EDP.setEd_pg_university(ed_pg_university);
        EDP.setEd_pg_inst(ed_pg_inst);
        EDP.setEd_pg_degree_name(ed_pg_degree_name);
        EDP.setEd_pg_year(ed_pg_year);
        EDP.setEd_pg_percent(ed_pg_percent);
        EDP.setEd_pg_rank(ed_pg_rank);
        EDP.setEd_pg_menu(ed_pg_menu);

        Call<EducationDetailPojo> ed = service.setEducationDetails(examination, Session, ed_ssc_board, ed_ssc_name, ed_ssc_roll, ed_ssc_year, ed_ssc_percent, ed_ssc_rank, ed_hsc_board, ed_hsc_name, ed_hsc_roll, ed_hsc_year, ed_hsc_percent, ed_hsc_rank, ed_gd_university, ed_gd_inst, ed_gd_degree_name, ed_gd_year, ed_gd_percent, ed_gd_rank, ed_gd_appear_final, ed_pg_university, ed_pg_inst, ed_pg_degree_name, ed_pg_year, ed_pg_percent, ed_pg_rank, ed_pg_menu);

        ed.enqueue(new Callback<EducationDetailPojo>() {
            @Override
            public void onResponse(Call<EducationDetailPojo> call, Response<EducationDetailPojo> response) {
                // Toast.makeText(EducationDetails.this, "success", Toast.LENGTH_SHORT).show();


                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<EducationDetailPojo> call, Throwable t) {
                //Toast.makeText(EducationDetails.this, "fail", Toast.LENGTH_SHORT).show();

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
