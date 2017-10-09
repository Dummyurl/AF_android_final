package com.LeelaGroup.AgrawalFedration.education;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.LeelaGroup.AgrawalFedration.Education_Pojos.ExamSubjectPojo;
import com.LeelaGroup.AgrawalFedration.Network.ApiClient;
import com.LeelaGroup.AgrawalFedration.R;
import com.LeelaGroup.AgrawalFedration.Service.ServiceAPIEducation;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ExamSubject extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener{

    LinearLayout upsc_layout,state_layout;
    CheckBox checkbox_UPSC,checkBox_State;
    ProgressDialog progressDialog;
    TextView output1;
    String outputString1;
    CheckBox cb_upsc,cb_state;
    Button S_Prev,S_Next;
    RadioGroup UPSC_radio,State_radio,Upsc_lang,State_lang;
    RadioButton UPSC_exam,State_exam,Upsc_language,State_language;
    EditText No_of_attempt,State_Optional_Subject_textArea,Main_Optional_Subject1,EditText02,EditText03;
    Spinner spinnerCenter,spinnerCenter_Year,spinnerCenterState;
    String exam,Sess, es_select_exam, es_upsc_exam, es_upsc_prel_year, es_upsc_op_sub, es_upsc_coach_sub, es_upsc_lang, es_state_exam, es_state_prel_year, es_state_prel_state, es_state_op_sub, es_state_coach_sub, es_state_lang, es_past_attn_upsc;

    String Exam,sess,pd_fname, pd_lname, pd_dob, pd_father_name, pd_mother_name, pd_gender, pd_mob1, pd_mob2, pd_email, pd_addr, pd_pincode, pd_city, pd_state, pd_pwd,fd_fathr_occup, fd_fathr_telephone, fd_fathr_mob, fd_fathr_desig, fd_fathr_income, fd_fathr_pan, fa_mothr_occup, fa_mothr_telephone, fa_mothr_mob, fa_mothr_religion,ed_ssc_board, ed_ssc_name, ed_ssc_roll, ed_ssc_year, ed_ssc_percent, ed_ssc_rank, ed_hsc_board, ed_hsc_name, ed_hsc_roll, ed_hsc_year, ed_hsc_percent, ed_hsc_rank, ed_gd_university, ed_gd_inst, ed_gd_degree_name, ed_gd_year, ed_gd_percent, ed_gd_rank, ed_gd_appear_final, ed_pg_university, ed_pg_inst, ed_pg_degree_name, ed_pg_year, ed_pg_percent, ed_pg_rank, ed_pg_menu,cet_get_facility, cd_cm_from, cd_lang, cd_schedule, cd_exam_center;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_subject);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_matrimony);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setTitle("Exam Subject");

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Inserting...");

        cb_upsc=(CheckBox)findViewById(R.id.checkbox_UPSC);
        cb_state=(CheckBox)findViewById(R.id.checkBox_State);
        upsc_layout=(LinearLayout)findViewById(R.id.id_layout_upsc);
        state_layout=(LinearLayout)findViewById(R.id.id_layout_state);

        checkbox_UPSC = (CheckBox)findViewById(R.id.checkbox_UPSC);

        checkBox_State = (CheckBox)findViewById(R.id.checkBox_State);

        output1 = (TextView)findViewById(R.id.output1);
        outputString1="";
        checkbox_UPSC.setOnCheckedChangeListener(this);
        checkBox_State.setOnCheckedChangeListener(this);

        Intent goto_Exam = getIntent();

        Bundle b = goto_Exam.getExtras();

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


        UPSC_radio = (RadioGroup) findViewById(R.id.UPSC_radio);
        State_radio = (RadioGroup) findViewById(R.id.State_radio);
        Upsc_lang = (RadioGroup) findViewById(R.id.Upsc_lang);
        State_lang = (RadioGroup) findViewById(R.id.State_lang);

        No_of_attempt= (EditText) findViewById(R.id.No_of_attempt);
        State_Optional_Subject_textArea= (EditText) findViewById(R.id.State_Optional_Subject_textArea);
        Main_Optional_Subject1= (EditText) findViewById(R.id.Main_Optional_Subject1);
        EditText02= (EditText) findViewById(R.id.EditText02);
        EditText03= (EditText) findViewById(R.id.EditText03);

        spinnerCenter = (Spinner) findViewById(R.id.spinnerCenter);
        spinnerCenter_Year = (Spinner) findViewById(R.id.spinnerCenter_Year);
        spinnerCenterState = (Spinner) findViewById(R.id.spinnerCenterState);

        cb_upsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()){
                    upsc_layout.setVisibility(View.VISIBLE);
                }
                else {
                    upsc_layout.setVisibility(View.GONE);
                }
            }
        });

        cb_state.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()){
                    state_layout.setVisibility(View.VISIBLE);
                }
                else {
                    state_layout.setVisibility(View.GONE);
                }
            }
        });

        //S_Prev=(Button)findViewById(R.id.S_Prev);

        S_Next=(Button)findViewById(R.id.S_Next);

        /*S_Prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });*/

        S_Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!outputString1.equals(""))
                {
                    outputString1 = outputString1.substring(0,outputString1.length()-1) + "";
                    output1.setText(outputString1);
                }
                outputString1="";
                checkbox_UPSC.setChecked(false);
                checkBox_State.setChecked(false);

                //ExamDetailsForm();

                Bundle();

               // startActivity(new Intent(ExamSubject.this,OtherDetails.class));

            }
        });
    }

    public void Bundle()
    {
        int selected_UPSC_Exam = UPSC_radio.getCheckedRadioButtonId();
        UPSC_exam = (RadioButton) findViewById(selected_UPSC_Exam);

        int selected_State_Exam = State_radio.getCheckedRadioButtonId();
        State_exam = (RadioButton) findViewById(selected_State_Exam);

        int selected_UPSC_Lang = Upsc_lang.getCheckedRadioButtonId();
        Upsc_language = (RadioButton) findViewById(selected_UPSC_Lang);

        int selected_State_Lang = State_lang.getCheckedRadioButtonId();
        State_language = (RadioButton) findViewById(selected_State_Lang);

        exam=Exam;
        Sess = sess;
        es_select_exam= output1.getText().toString();
        es_upsc_exam=UPSC_exam.getText().toString();
        es_upsc_prel_year=spinnerCenter.getSelectedItem().toString();
        es_upsc_op_sub=EditText02.getText().toString();
        es_upsc_coach_sub=EditText03.getText().toString();
        es_upsc_lang=Upsc_language.getText().toString();

        es_state_exam=State_exam.getText().toString();
        es_state_prel_year=spinnerCenter_Year.getSelectedItem().toString();
        es_state_prel_state=spinnerCenterState.getSelectedItem().toString();
        es_state_op_sub=State_Optional_Subject_textArea.getText().toString();
        es_state_coach_sub=Main_Optional_Subject1.getText().toString();
        es_state_lang=State_language.getText().toString();
        es_past_attn_upsc=No_of_attempt.getText().toString();

        Bundle b = new Bundle();
        b.putString("myname", Exam);
        b.putString("Sess", sess);

        b.putString("ssc_board",ed_ssc_board);
        b.putString("SchoolName",ed_ssc_name);
        b.putString("RollNo",ed_ssc_roll);
        b.putString("SSCYear",ed_ssc_year);
        b.putString("SSC_Percent",ed_ssc_percent);
        b.putString("SSC_Rank",ed_ssc_rank);
        b.putString("hsc_board",ed_hsc_board);
        b.putString("collegeName",ed_hsc_name);
        b.putString("HSC_RollNo",ed_hsc_roll);
        b.putString("HSCYear",ed_hsc_year);
        b.putString("HSC_Percent",ed_hsc_percent);
        b.putString("HSC_Rank",ed_hsc_rank);
        b.putString("gd_university",ed_gd_university);
        b.putString("collegeName1",ed_gd_inst);
        b.putString("Graduation_RollNo",ed_gd_degree_name);
        b.putString("Graduation_Year",ed_gd_year);
        b.putString("Graduation_Percent",ed_gd_percent);
        b.putString("Graduation_Rank",ed_gd_rank);
        b.putString("gd_appear_final",ed_gd_appear_final);
        b.putString("pg_university",ed_pg_university);
        b.putString("collegeName2",ed_pg_inst);
        b.putString("Poat_Graduation_RollNo",ed_pg_degree_name);
        b.putString("Post_Graduation_Year",ed_pg_year);
        b.putString("Post_Graduation_Percent",ed_pg_percent);
        b.putString("Post_Graduation_Rank",ed_pg_rank);
        b.putString("pg_menu",ed_pg_menu);

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

        b.putString("output",cd_cm_from);
        b.putString("selectedfacility",cet_get_facility);
        b.putString("selectedLang",cd_lang);
        b.putString("spinnerCenter",cd_exam_center);
        b.putString("spinnerSchedule",cd_schedule);

        b.putString("output1",es_select_exam);
        b.putString("UPSC_exam",es_upsc_exam);
        b.putString("spinneryear",es_upsc_prel_year);
        b.putString("EditText02",es_upsc_op_sub);
        b.putString("EditText03",es_upsc_coach_sub);
        b.putString("Upsc_language",es_upsc_lang);

        b.putString("State_exam",es_state_exam);
        b.putString("spinnerCenter_Year",es_state_prel_year);
        b.putString("spinnerCenterState",es_state_prel_state);
        b.putString("State_Optional_Subject_textArea",es_state_op_sub);
        b.putString("Main_Optional_Subject1",es_state_coach_sub);
        b.putString("State_language",es_state_lang);
        b.putString("No_of_attempt",es_past_attn_upsc);



        Intent goto_Other = new Intent(getApplicationContext(), OtherDetails.class);
        goto_Other.putExtras(b);
        goto_Other.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(goto_Other);
        ExamSubject.this.finish();
    }

    @Override
    public void onBackPressed()
    {
        finish();
        Intent intent = new Intent(ExamSubject.this, Registration.class);
        startActivity(intent);
    }

    private void ExamDetailsForm() {

        progressDialog.show();

        int selected_UPSC_Exam = UPSC_radio.getCheckedRadioButtonId();
        UPSC_exam = (RadioButton) findViewById(selected_UPSC_Exam);

        int selected_State_Exam = State_radio.getCheckedRadioButtonId();
        State_exam = (RadioButton) findViewById(selected_State_Exam);

        int selected_UPSC_Lang = Upsc_lang.getCheckedRadioButtonId();
        Upsc_language = (RadioButton) findViewById(selected_UPSC_Lang);

        int selected_State_Lang = State_lang.getCheckedRadioButtonId();
        State_language = (RadioButton) findViewById(selected_State_Lang);


        exam=Exam;
        Sess = sess;
        es_select_exam= output1.getText().toString();
        es_upsc_exam=UPSC_exam.getText().toString();
        es_upsc_prel_year=spinnerCenter.getSelectedItem().toString();
        es_upsc_op_sub=EditText02.getText().toString();
        es_upsc_coach_sub=EditText03.getText().toString();
        es_upsc_lang=Upsc_language.getText().toString();

        es_state_exam=State_exam.getText().toString();
        es_state_prel_year=spinnerCenter_Year.getSelectedItem().toString();
        es_state_prel_state=spinnerCenterState.getSelectedItem().toString();
        es_state_op_sub=State_Optional_Subject_textArea.getText().toString();
        es_state_coach_sub=Main_Optional_Subject1.getText().toString();
        es_state_lang=State_language.getText().toString();
        es_past_attn_upsc=No_of_attempt.getText().toString();

        ServiceAPIEducation service = ApiClient.getRetrofit().create(ServiceAPIEducation.class);
        ExamSubjectPojo examSubject = new ExamSubjectPojo();

        examSubject.setExam(exam);
        examSubject.setSess(Sess);
        examSubject.setEs_select_exam(es_select_exam);
        examSubject.setEs_upsc_exam(es_upsc_exam);
        examSubject.setEs_upsc_prel_year(es_upsc_prel_year);
        examSubject.setEs_upsc_op_sub(es_upsc_op_sub);
        examSubject.setEs_upsc_coach_sub(es_upsc_coach_sub);
        examSubject.setEs_upsc_lang(es_upsc_lang);

        examSubject.setEs_state_exam(es_state_exam);
        examSubject.setEs_state_prel_year(es_state_prel_year);
        examSubject.setEs_state_prel_state(es_state_prel_state);
        examSubject.setEs_state_op_sub(es_state_op_sub);
        examSubject.setEs_state_coach_sub(es_state_coach_sub);
        examSubject.setEs_state_lang(es_state_lang);
        examSubject.setEs_past_attn_upsc(es_past_attn_upsc);

        Call<ExamSubjectPojo> es = service.setExamDetails(exam,Sess, es_select_exam, es_upsc_exam, es_upsc_prel_year, es_upsc_op_sub, es_upsc_coach_sub, es_upsc_lang, es_state_exam, es_state_prel_year, es_state_prel_state, es_state_op_sub, es_state_coach_sub, es_state_lang, es_past_attn_upsc);

        es.enqueue(new Callback<ExamSubjectPojo>() {
            @Override
            public void onResponse(Call<ExamSubjectPojo> call, Response<ExamSubjectPojo> response) {
                //Toast.makeText(ExamSubject.this, "success", Toast.LENGTH_SHORT).show();

                Bundle b = new Bundle();
                b.putString("myname", Exam);
                b.putString("Sess", sess);

                Intent goto_Other = new Intent(getApplicationContext(), OtherDetails.class);
                goto_Other.putExtras(b);
                goto_Other.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(goto_Other);
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ExamSubjectPojo> call, Throwable t) {
                Toast.makeText(ExamSubject.this, getText(R.string.checkConn), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if(buttonView.isChecked())
        {
            if(!outputString1.contains(buttonView.getText()))
            {
                outputString1 = outputString1+" "+buttonView.getText()+",";
            }

        }
        else if(!buttonView.isChecked())
        {
            if(outputString1.contains(buttonView.getText()))
            {
                outputString1.replace(" "+buttonView.getText()+",","");
            }
        }

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
