package com.LeelaGroup.AgrawalFedration.education;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.LeelaGroup.AgrawalFedration.Education_Pojos.CETDetailPojo;
import com.LeelaGroup.AgrawalFedration.Network.ApiClient;
import com.LeelaGroup.AgrawalFedration.R;
import com.LeelaGroup.AgrawalFedration.Service.ServiceAPIEducation;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CET extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener{

    Button bt_Prev,bt_Next;
    ProgressDialog progressDialog;

    String Exam,sess,pd_fname, pd_lname, pd_dob, pd_father_name, pd_mother_name, pd_gender, pd_mob1, pd_mob2, pd_email, pd_addr, pd_pincode, pd_city, pd_state, pd_pwd,fd_fathr_occup, fd_fathr_telephone, fd_fathr_mob, fd_fathr_desig, fd_fathr_income, fd_fathr_pan, fa_mothr_occup, fa_mothr_telephone, fa_mothr_mob, fa_mothr_religion,ed_ssc_board, ed_ssc_name, ed_ssc_roll, ed_ssc_year, ed_ssc_percent, ed_ssc_rank, ed_hsc_board, ed_hsc_name, ed_hsc_roll, ed_hsc_year, ed_hsc_percent, ed_hsc_rank, ed_gd_university, ed_gd_inst, ed_gd_degree_name, ed_gd_year, ed_gd_percent, ed_gd_rank, ed_gd_appear_final, ed_pg_university, ed_pg_inst, ed_pg_degree_name, ed_pg_year, ed_pg_percent, ed_pg_rank, ed_pg_menu;
    String exam,Sess, cet_get_facility, cd_cm_from, cd_lang, cd_schedule, cd_exam_center;
    RadioGroup Cet_facility,Lang;

    RadioButton selectedfacility,selectedLang;

    Spinner spinnerCenter,spinnerSchedule;

    CheckBox checkBox1,checkBox2,checkBox3,checkBox4,checkBox5,checkBox6,checkBox7;

    String outputString;

    TextView output;

   // List<CheckBox> items = new ArrayList<CheckBox>();

    String text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cet);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_matrimony);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setTitle("CET Details");

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Inserting...");

        Intent goto_CET = getIntent();

        Bundle b = goto_CET.getExtras();

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


        spinnerCenter=(Spinner) findViewById(R.id.spinnerCenter);

        spinnerSchedule=(Spinner) findViewById(R.id.spinnerSchedule);

        checkBox1=(CheckBox)findViewById(R.id.checkBox1);
        checkBox2=(CheckBox)findViewById(R.id.checkBox2);
        checkBox3=(CheckBox)findViewById(R.id.checkBox3);
        checkBox4=(CheckBox)findViewById(R.id.checkBox4);
        checkBox5=(CheckBox)findViewById(R.id.checkBox5);
        checkBox6=(CheckBox)findViewById(R.id.checkBox6);
        checkBox7 = (CheckBox)findViewById(R.id.checkBox7);

        checkBox1.setOnCheckedChangeListener(this);
        checkBox2.setOnCheckedChangeListener(this);
        checkBox3.setOnCheckedChangeListener(this);
        checkBox4.setOnCheckedChangeListener(this);
        checkBox5.setOnCheckedChangeListener(this);
        checkBox6.setOnCheckedChangeListener(this);
        checkBox7.setOnCheckedChangeListener(this);

        output = (TextView)findViewById(R.id.output);

        outputString="";

        Cet_facility=(RadioGroup) findViewById(R.id.Cet_facility);
        Lang=(RadioGroup) findViewById(R.id.Lang);

       // bt_Prev=(Button)findViewById(R.id.bt_Prev);

        bt_Next=(Button)findViewById(R.id.bt_Next);

       /* bt_Prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });*/

        bt_Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!outputString.equals(""))
                {
                    outputString = outputString.substring(0,outputString.length()-1) + "";
                    output.setText(outputString);
                }
                outputString="";
                checkBox1.setChecked(false);
                checkBox2.setChecked(false);
                checkBox3.setChecked(false);
                checkBox4.setChecked(false);
                checkBox5.setChecked(false);
                checkBox6.setChecked(false);
                checkBox7.setChecked(false);



                //CETDetailsForm();

                Bundle();


            }
        });

//        for (CheckBox item : items){
//            if(item.isChecked())
//              text = item.getText().toString();
//
//        }
        getSpinnerdata();
    }

    private void getSpinnerdata() {
        ArrayAdapter<CharSequence> schedulearray=ArrayAdapter.createFromResource(this,R.array.Schedule,R.layout.spinner_text);
        schedulearray.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spinnerSchedule.setAdapter(schedulearray);

        ArrayAdapter<CharSequence> centerarray=ArrayAdapter.createFromResource(this,R.array.ExaminationCenter,R.layout.spinner_text);
        centerarray.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spinnerCenter.setAdapter(centerarray);
    }

    public void Bundle()
    {
        int selectedGenderId = Cet_facility.getCheckedRadioButtonId();
        selectedfacility = (RadioButton) findViewById(selectedGenderId);

        int selectedPgFinal = Lang.getCheckedRadioButtonId();
        selectedLang = (RadioButton) findViewById(selectedPgFinal);

        cd_cm_from = output.getText().toString();
        cet_get_facility = selectedfacility.getText().toString();
        cd_lang= selectedLang.getText().toString();
        cd_exam_center=spinnerCenter.getSelectedItem().toString();
        cd_schedule=spinnerSchedule.getSelectedItem().toString();

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


        Intent goto_OtherDetails = new Intent(getApplicationContext(), ExamSubject.class);
        goto_OtherDetails.putExtras(b);
        goto_OtherDetails.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(goto_OtherDetails);
        CET.this.finish();
    }

    @Override
    public void onBackPressed()
    {
        finish();
        Intent intent = new Intent(CET.this, Registration.class);
        startActivity(intent);
    }

    private void CETDetailsForm() {

        progressDialog.show();

        int selectedGenderId = Cet_facility.getCheckedRadioButtonId();
        selectedfacility = (RadioButton) findViewById(selectedGenderId);

        int selectedPgFinal = Lang.getCheckedRadioButtonId();
        selectedLang = (RadioButton) findViewById(selectedPgFinal);

        ServiceAPIEducation service = ApiClient.getRetrofit().create(ServiceAPIEducation.class);
        CETDetailPojo cet = new CETDetailPojo();

        exam=Exam;
        Sess = sess;
        cd_cm_from = output.getText().toString();
        cet_get_facility = selectedfacility.getText().toString();
        cd_lang= selectedLang.getText().toString();
        cd_exam_center=spinnerCenter.getSelectedItem().toString();
        cd_schedule=spinnerSchedule.getSelectedItem().toString();

        cet.setExam(exam);
        cet.setSess(Sess);
        cet.setCd_cm_from(cd_cm_from);
        cet.setCet_get_facility(cet_get_facility);
        cet.setCd_lang(cd_lang);
        cet.setCd_exam_center(cd_exam_center);
        cet.setCd_schedule(cd_schedule);

        Call<CETDetailPojo> Cet = service.setCETDetails(exam,Sess,cet_get_facility, cd_cm_from, cd_lang, cd_schedule, cd_exam_center);

        Cet.enqueue(new Callback<CETDetailPojo>() {
            @Override
            public void onResponse(Call<CETDetailPojo> call, Response<CETDetailPojo> response) {
                //Toast.makeText(CET.this, "success", Toast.LENGTH_SHORT).show();
                Bundle b = new Bundle();
                b.putString("myname", Exam);
                b.putString("Sess", sess);

                Intent goto_OtherDetails = new Intent(getApplicationContext(), ExamSubject.class);
                goto_OtherDetails.putExtras(b);
                goto_OtherDetails.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(goto_OtherDetails);
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<CETDetailPojo> call, Throwable t) {
                Toast.makeText(CET.this,getText(R.string.checkConn), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if(buttonView.isChecked())
        {
            if(!outputString.contains(buttonView.getText()))
            {
                outputString = outputString+" "+buttonView.getText()+",";
            }

        }
        else if(!buttonView.isChecked())
        {
            if(outputString.contains(buttonView.getText()))
            {
                outputString.replace(" "+buttonView.getText()+",","");
            }
        }
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

    // public void startNext(View v){startActivity(new Intent(CET.this,ExamSubject.class));}
}
