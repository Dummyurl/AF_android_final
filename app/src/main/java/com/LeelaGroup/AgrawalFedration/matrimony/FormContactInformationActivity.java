package com.LeelaGroup.AgrawalFedration.matrimony;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;

import com.LeelaGroup.AgrawalFedration.Home;
import com.LeelaGroup.AgrawalFedration.MatrimonySession;
import com.LeelaGroup.AgrawalFedration.Network.ApiClient;
import com.LeelaGroup.AgrawalFedration.R;
import com.LeelaGroup.AgrawalFedration.Service.ServiceMatrimony;
import com.LeelaGroup.AgrawalFedration.matrimony.models.CountryStateCity;
import com.LeelaGroup.AgrawalFedration.matrimony.validation.CustomValidator;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormContactInformationActivity extends AppCompatActivity {
    EditText etMobNo,etLandline,etEmail,etAddress,etPin;
    String id;

    Toolbar toolbar;
    List<CountryStateCity> listCountries;
    String[] countryName;
    ArrayAdapter<String> dataAdaptercountry;

    List<CountryStateCity> listCities;
    String[] cityName;
    ArrayAdapter<String> dataAdapterCity;

    List<CountryStateCity> listStates;
    String[] stateName;
    ArrayAdapter<String> dataAdapterState;

    //initiliazations for basic details
    String mreg_am, mreg_fname, mreg_mname, mreg_lname, mreg_birth_place, mreg_birth_time, mreg_native_place, mreg_dob, mreg_age, mreg_marital_status, mreg_gender, mreg_no_child, mreg_child_leave_status, mreg_mother_tongue, mreg_about_me;
    String mat_reg_religion,mat_reg_caste,mat_reg_subcaste;
    File imageFile;

    String mreg_landline="",mreg_phone,mreg_email,mreg_addr,mreg_country,mreg_state,mreg_city,mreg_pincode,mreg_resid_status;
    String mat_id;
    AutoCompleteTextView sprCountry,etCity,sprState;
    Spinner sprResdStats;

    MatrimonySession matrimonySession;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_contact_information);

        matrimonySession =new MatrimonySession(getApplicationContext());

        init();
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Contact Information");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        if(matrimonySession.checkLogin())
            finish();

        getcity();
        getCountries();
        getstate();
        catchBasicDetails();

        getSpinnerdata();
    }



    private void getSpinnerdata() {
        ArrayAdapter<CharSequence> resstarray = ArrayAdapter.createFromResource(this, R.array.resident_status, R.layout.spinner_text);
        resstarray.setDropDownViewResource(android.R.layout.simple_list_item_1);
        sprResdStats.setAdapter(resstarray);
    }

    public boolean validateFirst(){

        CustomValidator validator=new CustomValidator();

        final String mob=etMobNo.getText().toString();
        if(!validator.isValidMobile(mob)){
            etMobNo.requestFocus();
            etMobNo.setError("Please Enter Valid Mobile Number");
            return false;
        }
        etMobNo.setError(null);

        final String email=etEmail.getText().toString();
        if(!validator.isValidEmail(email)){
            etEmail.requestFocus();
            etEmail.setError("Please Enter Valid Email");
            return false;
        }
        etEmail.setError(null);

        final String addr=etAddress.getText().toString();
        if(!validator.isEmptyField(addr)){
            etAddress.requestFocus();
            etAddress.setError("Address Should Not Empty");
            return false;
        }
        etAddress.setError(null);

        final String country=sprCountry.getText().toString();
        List cnry= Arrays.asList(countryName);
        if(!cnry.contains(country)){
            sprCountry.requestFocus();
            sprCountry.setError("Please Enter valid Country Name");
            return false;
        }
        sprCountry.setError(null);

        final String state=sprState.getText().toString();
        List st= Arrays.asList(stateName);
        if(!st.contains(state)){
            sprState.requestFocus();
            sprState.setError("Please Enter valid State Name");
            return false;
        }
        sprState.setError(null);

        final String city=etCity.getText().toString();
        List ct= Arrays.asList(cityName);
        if(!ct.contains(city)){
            etCity.requestFocus();
            etCity.setError("Please Enter Valid City Name");
            return false;
        }
        etCity.setError(null);

        final String pincode=etPin.getText().toString();
        if(!validator.isValidPincode(pincode)){
            etPin.requestFocus();
            etPin.setError("Pin IS 6 Digits Number Only");
            return false;
        }
        etPin.setError(null);

        final String ressts=sprResdStats.getSelectedItem().toString();
        if(!validator.isEmptyField(ressts)){

            return false;
        }

        return true;
    }

    public void goToFormSocialAttribute(View v)
    {
        if (validateFirst())
        {
            getContactDetails();

            Intent intent = new Intent(FormContactInformationActivity.this, FormSocialAttributeActivity.class);
            //basic detail
            intent.putExtra("mat_id",mat_id);
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
            startActivity(intent);
            this.finish();

           //startActivity(new Intent(this,FormSocialAttributeActivity.class));
        }
    }


  public void getcity()
  {
      ServiceMatrimony serviceMatrimony=ApiClient.getRetrofit().create(ServiceMatrimony.class);
      Call<List<CountryStateCity>> call=serviceMatrimony.getCity();
      call.enqueue(new Callback<List<CountryStateCity>>() {
          @Override
          public void onResponse(Call<List<CountryStateCity>> call, Response<List<CountryStateCity>> response) {
                listCities=response.body();
              cityName=new String[listCities.size()];
              for(int i=0;i<listCities.size();i++)
              {
                  cityName[i]=listCities.get(i).getCity();
              }
              dataAdapterCity=new ArrayAdapter<String>(FormContactInformationActivity.this,android.R.layout.simple_list_item_1,cityName);
                etCity.setAdapter(dataAdapterCity);

          }

          @Override
          public void onFailure(Call<List<CountryStateCity>> call, Throwable t) {

             // Toast.makeText(FormContactInformationActivity.this,getText(R.string.checkConn), Toast.LENGTH_SHORT).show();
          }
      });
  }
    public void getstate()
    {
        ServiceMatrimony serviceMatrimony=ApiClient.getRetrofit().create(ServiceMatrimony.class);
        Call<List<CountryStateCity>> call=serviceMatrimony.getStates();

        call.enqueue(new Callback<List<CountryStateCity>>() {
            @Override
            public void onResponse(Call<List<CountryStateCity>> call, Response<List<CountryStateCity>> response) {
                listStates=response.body();
                stateName=new String[listStates.size()];
                for(int i=0;i<listStates.size();i++)
                {
                    stateName[i]=listStates.get(i).getState();
                }
                dataAdapterState=new ArrayAdapter<String>(FormContactInformationActivity.this,android.R.layout.simple_list_item_1,stateName);
                sprState.setAdapter(dataAdapterState);
            }

            @Override
            public void onFailure(Call<List<CountryStateCity>> call, Throwable t) {
                //Toast.makeText(FormContactInformationActivity.this,getText(R.string.checkConn), Toast.LENGTH_SHORT).show();

            }
        });

    }
    public void getCountries()
    {
        ServiceMatrimony serviceMatrimony=ApiClient.getRetrofit().create(ServiceMatrimony.class);
        Call<List<CountryStateCity>> call=serviceMatrimony.getCountries();

        call.enqueue(new Callback<List<CountryStateCity>>() {
            @Override
            public void onResponse(Call<List<CountryStateCity>> call, Response<List<CountryStateCity>> response) {
                listCountries=response.body();
                countryName=new String[listCountries.size()];
                for (int i=0;i<listCountries.size();i++)
                {
                    countryName[i]=listCountries.get(i).getCountry();
                }
                dataAdaptercountry=new ArrayAdapter<String>(FormContactInformationActivity.this,android.R.layout.simple_list_item_1,countryName);
                sprCountry.setAdapter(dataAdaptercountry);
            }

            @Override
            public void onFailure(Call<List<CountryStateCity>> call, Throwable t) {
               // Toast.makeText(FormContactInformationActivity.this,t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    public  void  catchBasicDetails()
    {

        // basic details data
        mat_id=getIntent().getStringExtra("mat_id");
        imageFile=(File)getIntent().getExtras().get("imageFile");
      //  double size=imageFile.length();
        mreg_am=getIntent().getStringExtra("mreg_am");
        mreg_fname=getIntent().getStringExtra("mreg_fname");
        mreg_mname=getIntent().getStringExtra("mreg_mname");
        mreg_lname=getIntent().getStringExtra("mreg_lname");
        mreg_birth_place=getIntent().getStringExtra("mreg_birth_place");
        mreg_birth_time=getIntent().getStringExtra("mreg_birth_time");
        mreg_native_place=getIntent().getStringExtra("mreg_native_place");
        mreg_dob=getIntent().getStringExtra("mreg_dob");
        mreg_age=getIntent().getStringExtra("mreg_age");
        mreg_marital_status=getIntent().getStringExtra("mreg_marital_status");
        mreg_gender=getIntent().getStringExtra("mreg_gender");
        mreg_no_child=getIntent().getStringExtra("mreg_no_child");
        mreg_child_leave_status=getIntent().getStringExtra("mreg_child_leave_status");
        mreg_mother_tongue=getIntent().getStringExtra("mreg_mother_tongue");
        mreg_about_me=getIntent().getStringExtra("mreg_about_me");
        mat_reg_religion=getIntent().getStringExtra("mat_reg_religion");
        mat_reg_caste=getIntent().getStringExtra("mat_reg_caste");
        mat_reg_subcaste=getIntent().getStringExtra("mat_reg_subcaste");
    }
    public void getContactDetails()
    {
        //contact data
        //catchBasicDetails();
        mreg_landline=etLandline.getText().toString();
        mreg_phone=etMobNo.getText().toString();
        mreg_email=etEmail.getText().toString();
        mreg_addr=etAddress.getText().toString();
        mreg_country=sprCountry.getText().toString();
        mreg_state=sprState.getText().toString();
        mreg_city=etCity.getText().toString();
        mreg_pincode=etPin.getText().toString();
        mreg_resid_status=sprResdStats.getSelectedItem().toString();


    }
    public void init()
    {
        etMobNo=(EditText)findViewById(R.id.frm_d_per_mobile);
        etLandline=(EditText)findViewById(R.id.frm_d_per_landlnno);
        etEmail=(EditText)findViewById(R.id.frm_d_per_email);
        etAddress=(EditText)findViewById(R.id.frm_d_per_addr);

        etPin=(EditText)findViewById(R.id.frm_d_per_pincode);
        sprResdStats=(Spinner)findViewById(R.id.frm_d_per_ressts);

        sprState=(AutoCompleteTextView) findViewById(R.id.frm_d_per_state);
        sprCountry=(AutoCompleteTextView) findViewById(R.id.frm_d_per_country);
        etCity=(AutoCompleteTextView) findViewById(R.id.frm_d_per_city);
    }

    @Override
    public void onBackPressed()
    {
        //finish();
        Intent intent = new Intent(FormContactInformationActivity.this, Home.class);
        intent.putExtra("mat_id",mat_id);
        startActivity(intent);
        FormContactInformationActivity.this.finish();
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
