package com.LeelaGroup.AgrawalFedration.medical;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.LeelaGroup.AgrawalFedration.Business_Medical_Session;
import com.LeelaGroup.AgrawalFedration.Compressor;
import com.LeelaGroup.AgrawalFedration.FilePicker;
import com.LeelaGroup.AgrawalFedration.MatrimonySession;
import com.LeelaGroup.AgrawalFedration.Medical_Pojos.Medical;
import com.LeelaGroup.AgrawalFedration.MyUtility;
import com.LeelaGroup.AgrawalFedration.Network.ApiClient;
import com.LeelaGroup.AgrawalFedration.R;
import com.LeelaGroup.AgrawalFedration.Service.MedicalServiceAPI;
import com.LeelaGroup.AgrawalFedration.education.Education_Upload_Doc;
import com.LeelaGroup.AgrawalFedration.matrimony.FormBasicDetailsActivity;
import com.LeelaGroup.AgrawalFedration.matrimony.validation.CustomValidator;
import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.LeelaGroup.AgrawalFedration.business.BusinessAddAdvertized.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE;


public class Medical_Posting extends AppCompatActivity {

    Business_Medical_Session medical__session;
    boolean isImageAdded = false;
    File file;
    CircleImageView iv;
    TextView time, businessCloseTime;
    Bitmap bitmap;
    String format;
    static int ohour, omin, chour, cmin;
    Button save;
    //ImageView businessBrowseImage;
    Spinner ac_state, ac_country, ac_city;
    TextInputLayout layout_BusinessName, layout_BusinessAddress, layout_BusinessPincode, layout_BusinessContact,
            layout_BusinessQualification, layout_BusinessEmail, layout_AboutBusiness, layout_personName, layout_personNumber, layout_personDesig, layout_personMail;

    EditText businessName, businessAddress, businessPincode, businessContact, businessQualification,
            aboutBusiness, businessWebsite, contactperson_name, contactperson_number, contactperson_designation, contactperson_email;

    Medical medical;

    RadioGroup radioGroup, rediogroupcontact;
    RadioButton selectedCategory, selectContactDetail;


    private static final int SELECTED_PICTURE = 1;
    String filePath;

    LinearLayout layout;
    String state, country, city, CloseTime, openTime, Category, businessName1,
            businessAddress1, businessPincode1, businessContact1, businessQualification1, aboutBusiness1, businessWebsite1;

    String c_name, c_mobile, c_desc, c_email, c_contact, user_id;
    ProgressDialog progressDialog;

    List<Medical> citydata;
    String[] nameListcity;
    ArrayAdapter<String> dataAdaptercity;

    List<Medical> statedata;
    String[] nameListstate;
    ArrayAdapter<String> dataAdapterstate;

    List<Medical> countrydata;
    String[] nameListcountry;
    ArrayAdapter<String> dataAdaptercountry;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical__posting);

        medical__session = new Business_Medical_Session(getApplicationContext());

        HashMap<String, String> user = medical__session.getUserDetails();
        user_id = user.get(MatrimonySession.KEY_ID);
//        if (medical__session.checkLogin())
//            finish();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_matrimony);
        setSupportActionBar(toolbar);
        setTitle("Medical Posting");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getCountryData();


        layout_BusinessName = (TextInputLayout) findViewById(R.id.layout_BusinessName);
        layout_BusinessAddress = (TextInputLayout) findViewById(R.id.layout_BusinessAddress);
        layout_BusinessPincode = (TextInputLayout) findViewById(R.id.layout_BusinessPincode);
        layout_BusinessContact = (TextInputLayout) findViewById(R.id.layout_BusinessContact);
        layout_BusinessQualification = (TextInputLayout) findViewById(R.id.layout_BusinessQualification);
        layout_BusinessEmail = (TextInputLayout) findViewById(R.id.layout_BusinessEmail);
        layout_AboutBusiness = (TextInputLayout) findViewById(R.id.layout_AboutBusiness);

        layout_personName = (TextInputLayout) findViewById(R.id.layout_personName);
        layout_personNumber = (TextInputLayout) findViewById(R.id.layout_personNumber);
        layout_personDesig = (TextInputLayout) findViewById(R.id.layout_personDesig);
        layout_personMail = (TextInputLayout) findViewById(R.id.layout_personMail);


        //businessBrowseImage = (ImageView) findViewById(R.id.btnBrowse);
        businessName = (EditText) findViewById(R.id.Id_Name);
        businessAddress = (EditText) findViewById(R.id.Id_add);
        businessPincode = (EditText) findViewById(R.id.Id_pin);
        businessContact = (EditText) findViewById(R.id.Id_mobile);
        businessQualification = (EditText) findViewById(R.id.Id_post_edu);
        time = (TextView) findViewById(R.id.time);
        businessCloseTime = (TextView) findViewById(R.id.timesec);
        aboutBusiness = (EditText) findViewById(R.id.Id_AboutUs);
        businessWebsite = (EditText) findViewById(R.id.Id_website);

        contactperson_name = (EditText) findViewById(R.id.contactperson_name);
        contactperson_number = (EditText) findViewById(R.id.contactperson_number);
        contactperson_designation = (EditText) findViewById(R.id.contactperson_designation);
        contactperson_email = (EditText) findViewById(R.id.contactperson_email);
        save = (Button) findViewById(R.id.btn_postingSave);
        layout = (LinearLayout) findViewById(R.id.activity_medical__posting);



        ac_city = (Spinner) findViewById(R.id.posting_city);
        ac_state = (Spinner) findViewById(R.id.posting_stat);
        ac_country = (Spinner) findViewById(R.id.posting_country);

        iv = (CircleImageView) findViewById(R.id.imgVwMedical_posting);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroupDetail);
        rediogroupcontact = (RadioGroup) findViewById(R.id.radioGroupContactDetail);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Inserting");

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                ohour = calendar.get(Calendar.HOUR_OF_DAY);
                omin = calendar.get(Calendar.MINUTE);
                TimePickerDialog timepickerdialog = new TimePickerDialog(Medical_Posting.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                if (hourOfDay == 0) {
                                    hourOfDay += 12;
                                    format = "AM";
                                } else if (hourOfDay == 12) {
                                    format = "PM";
                                } else if (hourOfDay > 12) {
                                    hourOfDay -= 12;
                                    format = "PM";
                                } else {
                                    format = "AM";
                                }
                                if (minute < 10) {
                                    time.setText(hourOfDay + ":" + "0" + String.valueOf(minute) + format);
                                } else {
                                    time.setText(hourOfDay + ":" + minute + format);
                                }
                            }
                        }, ohour, omin, false);
                timepickerdialog.show();
            }
        });

        businessCloseTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                chour = calendar.get(Calendar.HOUR_OF_DAY);
                cmin = calendar.get(Calendar.MINUTE);
                TimePickerDialog timepickerdialog = new TimePickerDialog(Medical_Posting.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                if (hourOfDay == 0) {
                                    hourOfDay += 12;
                                    format = "AM";
                                } else if (hourOfDay == 12) {
                                    format = "PM";
                                } else if (hourOfDay > 12) {
                                    hourOfDay -= 12;
                                    format = "PM";
                                } else {
                                    format = "AM";
                                }
                                if (minute < 10) {
                                    businessCloseTime.setText(hourOfDay + ":" + "0" + String.valueOf(minute) + format);
                                } else {
                                    businessCloseTime.setText(hourOfDay + ":" + minute + format);
                                }
                            }
                        }, chour, cmin, false);
                timepickerdialog.show();
            }
        });

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                browse_btn();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyUtility.hideKeyboard(layout,Medical_Posting.this);
                if(validateFirst()){
                    if (MyUtility.isConnected(Medical_Posting.this)){
                        MedicalServices();
                    }else {
                        MyUtility.internetProblem(layout);
                    }
                }
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void getCountryData() {

        final MedicalServiceAPI service = ApiClient.getRetrofit().create(MedicalServiceAPI.class);
        Call<List<Medical>> call = service.getCountryName();
        call.enqueue(new Callback<List<Medical>>() {
            @Override
            public void onResponse(Call<List<Medical>> call, Response<List<Medical>> response) {
                countrydata = response.body();

                try {
                    if (countrydata != null) {
                        nameListcountry = new String[countrydata.size()];

                        for (int i = 0; i < countrydata.size(); i++) {
                            nameListcountry[i] = countrydata.get(i).getCname();
                        }
                        dataAdaptercountry = new ArrayAdapter<String>(Medical_Posting.this, R.layout.spinner_text, nameListcountry);
                        dataAdaptercountry.setDropDownViewResource(android.R.layout.simple_list_item_1);
                        ac_country.setAdapter(dataAdaptercountry);
                        ac_country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                country = ac_country.getSelectedItem().toString();
                                getStateData();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

                        country = ac_country.getSelectedItem().toString();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<Medical>> call, Throwable t) {

            }
        });

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);

        }

    }

    private void getStateData() {

        final MedicalServiceAPI service = ApiClient.getRetrofit().create(MedicalServiceAPI.class);
        Call<List<Medical>> call = service.getStateName(country);
        call.enqueue(new Callback<List<Medical>>() {
            @Override
            public void onResponse(Call<List<Medical>> call, Response<List<Medical>> response) {
                statedata = response.body();

                try {
                    if (statedata != null) {
                        nameListstate = new String[statedata.size()];

                        for (int i = 0; i < statedata.size(); i++) {
                            nameListstate[i] = statedata.get(i).getSname();

                        }
                        dataAdapterstate = new ArrayAdapter<String>(Medical_Posting.this, R.layout.spinner_text, nameListstate);
                        dataAdapterstate.setDropDownViewResource(android.R.layout.simple_list_item_1);
                        ac_state.setAdapter(dataAdapterstate);

                        state = ac_state.getSelectedItem().toString();
                        getCityData();

                        ac_state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                state = ac_state.getSelectedItem().toString();
                                getCityData();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

                        state = ac_state.getSelectedItem().toString();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<Medical>> call, Throwable t) {

            }
        });

    }


    private void getCityData() {


        final MedicalServiceAPI service = ApiClient.getRetrofit().create(MedicalServiceAPI.class);
        Call<List<Medical>> call = service.getCityName(state);
        call.enqueue(new Callback<List<Medical>>() {
            @Override
            public void onResponse(Call<List<Medical>> call, Response<List<Medical>> response) {
                citydata = response.body();
                try {
                    if (citydata != null) {
                        nameListcity = new String[citydata.size()];

                        for (int i = 0; i < citydata.size(); i++) {
                            nameListcity[i] = citydata.get(i).getCity_name();
                        }
                        dataAdaptercity = new ArrayAdapter<String>(Medical_Posting.this, R.layout.spinner_text, nameListcity);
                        dataAdaptercity.setDropDownViewResource(android.R.layout.simple_list_item_1);
                        ac_city.setAdapter(dataAdaptercity);

                        ac_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                city = ac_city.getSelectedItem().toString();

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        city = ac_city.getSelectedItem().toString();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<Medical>> call, Throwable t) {
                // Toast.makeText(Medical_Posting.this, "fail", Toast.LENGTH_SHORT).show();
            }
        });

    }


    public boolean validateFirst() {

        CustomValidator validator = new CustomValidator();

        if (!isImageAdded) {
            iv.requestFocus();
            Toast.makeText(this, "Pick your business photo", Toast.LENGTH_SHORT).show();
            return false;
        }

        final String B_nme = businessName.getText().toString();
        if (!validator.isValidName(B_nme)) {
            businessName.requestFocus();
            businessName.setError("Please Enter Valid Business Name");
            return false;
        }
        businessName.setError(null);

        final String B_Address = businessAddress.getText().toString();
        if (!validator.isEmptyField(B_Address)) {
            businessAddress.requestFocus();
            businessAddress.setError("Please Enter Valid Address");
            return false;
        }
        businessAddress.setError(null);

        final String B_Pincode = businessPincode.getText().toString();
        if (!validator.isValidPincode(B_Pincode)) {
            businessPincode.requestFocus();
            businessPincode.setError("PinCode Must Be 6 Digits Only");
            return false;
        }
        businessPincode.setError(null);

        final String B_Contact = businessContact.getText().toString();
        if (!validator.isValidMobile(B_Contact)) {
            businessContact.requestFocus();
            businessContact.setError("Please Enter valid contact number");
            return false;
        }
        businessContact.setError(null);

        final String B_Qualification = businessQualification.getText().toString();
        if (!validator.isEmptyField(B_Qualification)) {
            businessQualification.requestFocus();
            businessQualification.setError("Please Enter Qualification");

            return false;
        }
        businessQualification.setError(null);


        final String p_name = contactperson_name.getText().toString();
        if (!validator.isValidName(p_name)) {
            contactperson_name.requestFocus();
            contactperson_name.setError("Please Enter Valid Name");

            return false;
        }
        contactperson_name.setError(null);


        final String p_number = contactperson_number.getText().toString();
        if (!validator.isValidMobile(p_number)) {
            contactperson_number.requestFocus();
            contactperson_number.setError("Please Enter 10 digit mobile number");

            return false;
        }
        contactperson_number.setError(null);


        final String p_designation = contactperson_designation.getText().toString();
        if (!validator.isEmptyField(p_designation)) {
            contactperson_designation.requestFocus();
            contactperson_designation.setError("Please Enter Designation");

            return false;
        }
        contactperson_designation.setError(null);


        final String p_email = contactperson_email.getText().toString();
        if (!validator.isValidEmail(p_email)) {
            contactperson_email.requestFocus();
            contactperson_email.setError("Please Enter Valid Email");

            return false;
        }
        contactperson_email.setError(null);

        return true;
    }

    public void browse_btn() {
        Intent intent = new Intent();
        // Show only images, no videos or anything else
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        // Always show the chooser (if there are multiple options available)
        startActivityForResult(intent, SELECTED_PICTURE);

    }

    @TargetApi(Build.VERSION_CODES.M)
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == SELECTED_PICTURE && resultCode == RESULT_OK && null != data) {
            super.onActivityResult(requestCode, resultCode, data);
            Uri uri = data.getData();
            try {
                Glide.with(Medical_Posting.this).load(uri).into(iv);
                String filepath = FilePicker.getPath(Medical_Posting.this, uri);
                File afile = new File(filepath);
                file = new Compressor(this).compressToFile(afile);
                // personImage.setVisibility(View.VISIBLE);
                isImageAdded = true;
            } catch (Exception e) {

            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {

                }
                return;
            }
        }
    }

    public void MedicalServices() {

        progressDialog.show();

        int SelectedCategoryId = radioGroup.getCheckedRadioButtonId();
        selectedCategory = (RadioButton) findViewById(SelectedCategoryId);

        int SelectedConatctId = rediogroupcontact.getCheckedRadioButtonId();
        selectContactDetail = (RadioButton) findViewById(SelectedConatctId);

        Category = selectedCategory.getText().toString();
        businessName1 = businessName.getText().toString();
        businessAddress1 = businessAddress.getText().toString();
        businessPincode1 = businessPincode.getText().toString();
        businessContact1 = businessContact.getText().toString();
        businessQualification1 = businessQualification.getText().toString();
        aboutBusiness1 = aboutBusiness.getText().toString();
        businessWebsite1 = businessWebsite.getText().toString();
        openTime = time.getText().toString();
        CloseTime = businessCloseTime.getText().toString();


        c_name = contactperson_name.getText().toString();
        c_mobile = contactperson_number.getText().toString();
        c_desc = contactperson_designation.getText().toString();
        c_email = contactperson_email.getText().toString();
        c_contact = selectContactDetail.getText().toString();


        RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", file.getName(), requestBody);

        RequestBody Category1 = RequestBody.create(MediaType.parse("text/plain"), Category);
        RequestBody business_Name = RequestBody.create(MediaType.parse("text/plain"), businessName1);
        RequestBody business_Address = RequestBody.create(MediaType.parse("text/plain"), businessAddress1);
        RequestBody business_Pincode = RequestBody.create(MediaType.parse("text/plain"), businessPincode1);
        RequestBody business_Contact = RequestBody.create(MediaType.parse("text/plain"), businessContact1);
        RequestBody business_Qualification = RequestBody.create(MediaType.parse("text/plain"), businessQualification1);
        RequestBody about_Business = RequestBody.create(MediaType.parse("text/plain"), aboutBusiness1);
        RequestBody business_Website = RequestBody.create(MediaType.parse("text/plain"), businessWebsite1);
        RequestBody open_Time = RequestBody.create(MediaType.parse("text/plain"), openTime);
        RequestBody Close_Time = RequestBody.create(MediaType.parse("text/plain"), CloseTime);
        RequestBody country1 = RequestBody.create(MediaType.parse("text/plain"), country);
        RequestBody state1 = RequestBody.create(MediaType.parse("text/plain"), state);
        RequestBody city1 = RequestBody.create(MediaType.parse("text/plain"), city);

        RequestBody r_name = RequestBody.create(MediaType.parse("text/plain"), c_name);
        RequestBody r_mobile = RequestBody.create(MediaType.parse("text/plain"), c_mobile);
        RequestBody r_deg = RequestBody.create(MediaType.parse("text/plain"), c_desc);
        RequestBody r_email = RequestBody.create(MediaType.parse("text/plain"), c_email);
        RequestBody r_contact = RequestBody.create(MediaType.parse("text/plain"), c_contact);
        RequestBody r_userid = RequestBody.create(MediaType.parse("text/plain"), user_id);

        MedicalServiceAPI getResponse = ApiClient.getRetrofit().create(MedicalServiceAPI.class);
        Call<Medical> call = getResponse.update_uploade_file(fileToUpload, Category1, business_Name, business_Address,
                business_Pincode, business_Contact, country1, state1, city1, business_Qualification, open_Time,
                Close_Time, about_Business, business_Website, r_name, r_mobile, r_email, r_deg, r_contact, r_userid);

        call.enqueue(new Callback<Medical>() {
            @Override
            public void onResponse(Call<Medical> call, Response<Medical> response) {
                medical = response.body();
                progressDialog.dismiss();

                if (medical.getSuccess()) {
                    Toast.makeText(getApplicationContext(), medical.getMessage(), Toast.LENGTH_SHORT).show();

                    finish();
                } else {

                    Toast.makeText(getApplicationContext(), medical.getMessage(), Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<Medical> call, Throwable t) {

                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), getText(R.string.checkConn), Toast.LENGTH_SHORT).show();
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
