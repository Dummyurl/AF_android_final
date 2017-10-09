package com.LeelaGroup.AgrawalFedration.business;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
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
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.LeelaGroup.AgrawalFedration.Business_Pojo.BusinessGetCategoryDataPOJO;
import com.LeelaGroup.AgrawalFedration.Business_Pojo.BusinessGetSet;
import com.LeelaGroup.AgrawalFedration.Business_Pojo.C_S_C_Pojo;
import com.LeelaGroup.AgrawalFedration.Business_Medical_Session;
import com.LeelaGroup.AgrawalFedration.Compressor;
import com.LeelaGroup.AgrawalFedration.FilePicker;
import com.LeelaGroup.AgrawalFedration.MyUtility;
import com.LeelaGroup.AgrawalFedration.Network.ApiClient;
import com.LeelaGroup.AgrawalFedration.R;
import com.LeelaGroup.AgrawalFedration.Service.Business_ServiceAPI;
import com.LeelaGroup.AgrawalFedration.matrimony.FormBasicDetailsActivity;
import com.LeelaGroup.AgrawalFedration.medical.Medical_Posting;
import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BusinessAddAdvertized extends AppCompatActivity implements View.OnClickListener {


    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 23;
    public static int RESULT_LOAD_IMAGE_Logo = 1;
    private static final int SELECTED_PICTURE = 2;
    String filePath;
Bitmap bitmap;

    public boolean isImageAdded = false;

    EditText et_company_name, et_tag_line, et_email, et_mobile, et_contact_code, et_contact_no, et_address_one,
            et_address_two, et_short_desc, et_long_desc, et_year_established, et_website, et_certification,
            et_license, et_payment_method, et_language, et_speciality;

    File file;

    CircleImageView iv_Logo;
    TextView ImageTitle;
    CircleImageView btnChooseImage;

    TextInputLayout tl_company_name, tl_tag_line, tl_email, tl_mobile, tl_contact_code, tl_contact_no, tl_address_one,
            tl_address_two, tl_short_desc, tl_long_desc, tl_year_established, tl_website, tl_certification,
            tl_license, tl_payment_method, tl_language, tl_speciality;

    Spinner sp_category, sp_sub_category, sp_locale, sp_service_area, sp_working_days, sp_hour_operation;

    Spinner sp_country, sp_state, sp_city;
    Spinner sp_country2, sp_state2, sp_city2;

    Button bt_submit, bt_reset;

    String Name, TagLine, Email, Mobile, Contact_Number, Address1, Address2, Country1, Country2, State1, State2, City1, City2,
            Description, Long_Description, Year, Website, Certification, Licenses, Payment_Method, Languages, Specialities,
            Category, Subcategory, BusinessType = "", AdvertiserType = "", mediaPath, Locale, Service_Area, Working_days, Hours_of_Operation;
    CheckBox ck_tender, ck_manufacture, ck_professional, ck_service_provider, ck_Basic, ck_Premium, ck_Superlisting;
    String split = ",", blank = "";
    private ProgressDialog pDialog;
    Business_Medical_Session business_Medical_session;

    List<C_S_C_Pojo> countrydata;
    String[] nameListcountry;
    ArrayAdapter<String> dataAdaptercountry;

    List<C_S_C_Pojo> s_countrydata;
    String[] s_nameListcountry;
    ArrayAdapter<String> s_dataAdaptercountry;

    List<C_S_C_Pojo> statedata;
    String[] nameListstate;
    ArrayAdapter<String> dataAdapterstate;

    List<C_S_C_Pojo> s_statedata;
    String[] s_nameListstate;
    ArrayAdapter<String> s_dataAdapterstate;

    List<C_S_C_Pojo> citydata;
    String[] nameListcity;
    ArrayAdapter<String> dataAdaptercity;

    List<C_S_C_Pojo> s_citydata;
    String[] s_nameListcity;
    ArrayAdapter<String> s_dataAdaptercity;
    LinearLayout layout;

    @RequiresApi(api = Build.VERSION_CODES.M)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_add_advertized);

        business_Medical_session = new Business_Medical_Session(getApplicationContext());

//        if(business_Medical_session.checkLogin());
//        finish();

        layout = (LinearLayout) findViewById(R.id.parent);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);

       /* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("Business Registration");*/

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Business Registration");

        getCategory();
        getCountry();
        s_getCountry();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);

        }

        iv_Logo = (CircleImageView) findViewById(R.id.iv_logo);
        ImageTitle = (TextView) findViewById(R.id.tv_imageTitle);
        // btnChooseImage = (CircleImageView) findViewById(R.id.bn_chooselogo);

        ck_tender = (CheckBox) findViewById(R.id.ck_tender);
        ck_manufacture = (CheckBox) findViewById(R.id.ck_manufacture);
        ck_professional = (CheckBox) findViewById(R.id.ck_professional);
        ck_service_provider = (CheckBox) findViewById(R.id.ck_service_provider);

        ck_Basic = (CheckBox) findViewById(R.id.ck_Basic);
        ck_Premium = (CheckBox) findViewById(R.id.ck_Premium);
        ck_Superlisting = (CheckBox) findViewById(R.id.ck_Superlisting);


        et_company_name = (EditText) findViewById(R.id.et_companyName);
        et_tag_line = (EditText) findViewById(R.id.et_tagLine);
        et_email = (EditText) findViewById(R.id.et_email);
        et_mobile = (EditText) findViewById(R.id.et_mobile);
        // et_contact_code = (EditText) findViewById(R.id.et_contact_code);
        et_contact_no = (EditText) findViewById(R.id.et_contactNo);
        et_address_one = (EditText) findViewById(R.id.et_address1);
        et_address_two = (EditText) findViewById(R.id.et_address2);
        et_short_desc = (EditText) findViewById(R.id.et_desc);
        et_long_desc = (EditText) findViewById(R.id.et_longDesc);
        et_year_established = (EditText) findViewById(R.id.et_year_establish);
        et_website = (EditText) findViewById(R.id.et_website);
        et_certification = (EditText) findViewById(R.id.et_certification);
        et_license = (EditText) findViewById(R.id.et_licence);
        et_payment_method = (EditText) findViewById(R.id.et_pay_method);
        et_language = (EditText) findViewById(R.id.et_lang);
        et_speciality = (EditText) findViewById(R.id.et_speciality);

        tl_company_name = (TextInputLayout) findViewById(R.id.tl_companyName);
        tl_tag_line = (TextInputLayout) findViewById(R.id.tl_tagLine);
        tl_email = (TextInputLayout) findViewById(R.id.tl_email);
        tl_mobile = (TextInputLayout) findViewById(R.id.tl_mobile);
        //tl_contact_code = (TextInputLayout) findViewById(R.id.tl_contact_code);
        tl_contact_no = (TextInputLayout) findViewById(R.id.tl_contactNo);
        tl_address_one = (TextInputLayout) findViewById(R.id.tl_address1);
        tl_address_two = (TextInputLayout) findViewById(R.id.tl_address2);
        tl_short_desc = (TextInputLayout) findViewById(R.id.tl_desc);
        tl_long_desc = (TextInputLayout) findViewById(R.id.tl_longDesc);
        tl_year_established = (TextInputLayout) findViewById(R.id.tl_year_establish);
        tl_website = (TextInputLayout) findViewById(R.id.tl_website);
        tl_certification = (TextInputLayout) findViewById(R.id.tl_certification);
        tl_license = (TextInputLayout) findViewById(R.id.tl_licence);
        tl_payment_method = (TextInputLayout) findViewById(R.id.tl_pay_method);
        tl_language = (TextInputLayout) findViewById(R.id.tl_lang);
        tl_speciality = (TextInputLayout) findViewById(R.id.tl_speciality);


        sp_country = (Spinner) findViewById(R.id.sp_country1);
        sp_state = (Spinner) findViewById(R.id.sp_state1);
        sp_city = (Spinner) findViewById(R.id.sp_city1);
        sp_country2 = (Spinner) findViewById(R.id.sp_country2);
        sp_state2 = (Spinner) findViewById(R.id.sp_state2);
        sp_city2 = (Spinner) findViewById(R.id.sp_city2);


        sp_category = (Spinner) findViewById(R.id.sp_category);
        sp_sub_category = (Spinner) findViewById(R.id.sp_sub_category);
        sp_locale = (Spinner) findViewById(R.id.sp_locale);
        sp_service_area = (Spinner) findViewById(R.id.sp_service_area);
        sp_working_days = (Spinner) findViewById(R.id.sp_work_days);
        sp_hour_operation = (Spinner) findViewById(R.id.sp_hour_desc);


        bt_submit = (Button) findViewById(R.id.bt_submit);
        bt_reset = (Button) findViewById(R.id.bt_reset);

        et_company_name.addTextChangedListener(new MyTextWatcher(et_company_name));
        et_tag_line.addTextChangedListener(new MyTextWatcher(et_tag_line));
        et_email.addTextChangedListener(new MyTextWatcher(et_email));
        et_mobile.addTextChangedListener(new MyTextWatcher(et_mobile));
        // et_contact_code.addTextChangedListener(new MyTextWatcher(et_contact_code));
        et_contact_no.addTextChangedListener(new MyTextWatcher(et_contact_no));
        et_address_one.addTextChangedListener(new MyTextWatcher(et_address_one));


        getSpinnerData();

        bt_submit.setOnClickListener(this);

        iv_Logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE_Logo);
*/
                Intent intent = new Intent();
                // Show only images, no videos or anything else
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                // Always show the chooser (if there are multiple options available)
                startActivityForResult(intent, 2);
            }
        });

    }

    private void getSpinnerData() {

        ArrayAdapter<CharSequence> sub_cataegorY_adapter = ArrayAdapter.createFromResource(BusinessAddAdvertized.this, R.array.Sub_Category, R.layout.spinner_text);
        sub_cataegorY_adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        sp_sub_category.setAdapter(sub_cataegorY_adapter);

        ArrayAdapter<CharSequence> locale = ArrayAdapter.createFromResource(BusinessAddAdvertized.this, R.array.Select_Locale, R.layout.spinner_text);
        locale.setDropDownViewResource(android.R.layout.simple_list_item_1);
        sp_locale.setAdapter(locale);


        ArrayAdapter<CharSequence> service_area = ArrayAdapter.createFromResource(BusinessAddAdvertized.this, R.array.Service_Area, R.layout.spinner_text);
        service_area.setDropDownViewResource(android.R.layout.simple_list_item_1);
        sp_service_area.setAdapter(service_area);

        ArrayAdapter<CharSequence> working_days = ArrayAdapter.createFromResource(BusinessAddAdvertized.this, R.array.No_working_days, R.layout.spinner_text);
        working_days.setDropDownViewResource(android.R.layout.simple_list_item_1);
        sp_working_days.setAdapter(working_days);

        ArrayAdapter<CharSequence> hour_operation = ArrayAdapter.createFromResource(BusinessAddAdvertized.this, R.array.No_working_days, R.layout.spinner_text);
        hour_operation.setDropDownViewResource(android.R.layout.simple_list_item_1);
        sp_hour_operation.setAdapter(hour_operation);

    }


    private void getCountry() {
        Business_ServiceAPI serviceAPI = ApiClient.getRetrofit().create(Business_ServiceAPI.class);
        Call<List<C_S_C_Pojo>> call = serviceAPI.getCountryName();
        call.enqueue(new Callback<List<C_S_C_Pojo>>() {
            @Override
            public void onResponse(Call<List<C_S_C_Pojo>> call, Response<List<C_S_C_Pojo>> response) {

                countrydata = response.body();
                try {
                    if (countrydata != null) {
                        nameListcountry = new String[countrydata.size()];
                        for (int i = 0; i < countrydata.size(); i++) {
                            nameListcountry[i] = countrydata.get(i).getCname();

                        }

                        dataAdaptercountry = new ArrayAdapter<String>(BusinessAddAdvertized.this, R.layout.spinner_text, nameListcountry);
                        dataAdaptercountry.setDropDownViewResource(android.R.layout.simple_list_item_1);
                        sp_country.setAdapter(dataAdaptercountry);
                        sp_country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                Country1 = sp_country.getSelectedItem().toString();
                                getState();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<C_S_C_Pojo>> call, Throwable t) {

            }
        });


    }

    private void s_getCountry() {
        Business_ServiceAPI serviceAPI = ApiClient.getRetrofit().create(Business_ServiceAPI.class);
        Call<List<C_S_C_Pojo>> call = serviceAPI.getCountryName();
        call.enqueue(new Callback<List<C_S_C_Pojo>>() {
            @Override
            public void onResponse(Call<List<C_S_C_Pojo>> call, Response<List<C_S_C_Pojo>> response) {
                s_countrydata = response.body();
                try {
                    if (s_countrydata != null) {
                        s_nameListcountry = new String[s_countrydata.size()];
                        for (int i = 0; i < s_countrydata.size(); i++) {
                            s_nameListcountry[i] = s_countrydata.get(i).getCname();

                        }

                        s_dataAdaptercountry = new ArrayAdapter<String>(BusinessAddAdvertized.this, R.layout.spinner_text, s_nameListcountry);
                        s_dataAdaptercountry.setDropDownViewResource(android.R.layout.simple_list_item_1);
                        sp_country2.setAdapter(s_dataAdaptercountry);
                        sp_country2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                Country2 = sp_country2.getSelectedItem().toString();
                                s_getState();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<List<C_S_C_Pojo>> call, Throwable t) {

            }
        });
    }


    private void getState() {


        Business_ServiceAPI serviceAPI = ApiClient.getRetrofit().create(Business_ServiceAPI.class);
        Call<List<C_S_C_Pojo>> call = serviceAPI.getStateName(Country1);
        call.enqueue(new Callback<List<C_S_C_Pojo>>() {
            @Override
            public void onResponse(Call<List<C_S_C_Pojo>> call, Response<List<C_S_C_Pojo>> response) {
                statedata = response.body();
                try {
                    if (statedata != null) {
                        nameListstate = new String[statedata.size()];

                        for (int i = 0; i < statedata.size(); i++) {
                            nameListstate[i] = statedata.get(i).getSname();

                        }
                        dataAdapterstate = new ArrayAdapter<String>(BusinessAddAdvertized.this, R.layout.spinner_text, nameListstate);
                        dataAdapterstate.setDropDownViewResource(android.R.layout.simple_list_item_1);
                        sp_state.setAdapter(dataAdapterstate);
                        sp_state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                State1 = sp_state.getSelectedItem().toString();
                                getCity();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        //  sp_state2.setAdapter(dataAdapterstate);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<C_S_C_Pojo>> call, Throwable t) {

            }
        });
    }

    private void s_getState() {
        Business_ServiceAPI serviceAPI = ApiClient.getRetrofit().create(Business_ServiceAPI.class);
        Call<List<C_S_C_Pojo>> call = serviceAPI.getStateName(Country2);
        call.enqueue(new Callback<List<C_S_C_Pojo>>() {
            @Override
            public void onResponse(Call<List<C_S_C_Pojo>> call, Response<List<C_S_C_Pojo>> response) {
                s_statedata = response.body();
                try {
                    if (s_statedata != null) {
                        s_nameListstate = new String[s_statedata.size()];

                        for (int i = 0; i < s_statedata.size(); i++) {
                            s_nameListstate[i] = s_statedata.get(i).getSname();

                        }
                        s_dataAdapterstate = new ArrayAdapter<String>(BusinessAddAdvertized.this, R.layout.spinner_text, s_nameListstate);
                        s_dataAdapterstate.setDropDownViewResource(android.R.layout.simple_list_item_1);
                        sp_state2.setAdapter(s_dataAdapterstate);
                        sp_state2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                State2 = sp_state2.getSelectedItem().toString();
                                s_getcity();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<C_S_C_Pojo>> call, Throwable t) {

            }
        });
    }


    private void getCity() {


        Business_ServiceAPI serviceAPI = ApiClient.getRetrofit().create(Business_ServiceAPI.class);
        Call<List<C_S_C_Pojo>> call = serviceAPI.getCityName(State1);
        call.enqueue(new Callback<List<C_S_C_Pojo>>() {
            @Override
            public void onResponse(Call<List<C_S_C_Pojo>> call, Response<List<C_S_C_Pojo>> response) {

                citydata = response.body();

                try {
                    if (citydata != null) {
                        nameListcity = new String[citydata.size()];

                        for (int i = 0; i < citydata.size(); i++) {
                            nameListcity[i] = citydata.get(i).getCity_name();
                        }
                        dataAdaptercity = new ArrayAdapter<String>(BusinessAddAdvertized.this, R.layout.spinner_text, nameListcity);
                        dataAdaptercity.setDropDownViewResource(android.R.layout.simple_list_item_1);
                        sp_city.setAdapter(dataAdaptercity);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<C_S_C_Pojo>> call, Throwable t) {

            }
        });
    }

    private void s_getcity() {
        Business_ServiceAPI serviceAPI = ApiClient.getRetrofit().create(Business_ServiceAPI.class);
        Call<List<C_S_C_Pojo>> call = serviceAPI.getCityName(State2);
        call.enqueue(new Callback<List<C_S_C_Pojo>>() {
            @Override
            public void onResponse(Call<List<C_S_C_Pojo>> call, Response<List<C_S_C_Pojo>> response) {
                s_citydata = response.body();

                try {
                    if (s_citydata != null) {
                        s_nameListcity = new String[s_citydata.size()];
                        for (int i = 0; i < s_citydata.size(); i++) {
                            s_nameListcity[i] = s_citydata.get(i).getCity_name();
                        }

                        s_dataAdaptercity = new ArrayAdapter<String>(BusinessAddAdvertized.this, R.layout.spinner_text, s_nameListcity);
                        s_dataAdaptercity.setDropDownViewResource(android.R.layout.simple_list_item_1);
                        sp_city2.setAdapter(s_dataAdaptercity);
                        City2 = sp_city2.getSelectedItem().toString();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<List<C_S_C_Pojo>> call, Throwable t) {

            }
        });
    }

    private void getCategory() {
        Business_ServiceAPI serviceAPI = ApiClient.getRetrofit().create(Business_ServiceAPI.class);
        Call<List<BusinessGetCategoryDataPOJO>> call = serviceAPI.getCtegory();
        call.enqueue(new Callback<List<BusinessGetCategoryDataPOJO>>() {
            @Override
            public void onResponse(Call<List<BusinessGetCategoryDataPOJO>> call, Response<List<BusinessGetCategoryDataPOJO>> response) {

                List<BusinessGetCategoryDataPOJO> pojo = response.body();
                try {
                    if (pojo != null) {
                        String[] categoryList = new String[pojo.size()];

                        for (int i = 0; i < pojo.size(); i++) {
                            categoryList[i] = pojo.get(i).getCatName(); //create array of name
                        }

                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(BusinessAddAdvertized.this, R.layout.spinner_text, categoryList);
                        //drop down layout style - list view with radio button
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
                        //attaching data adapter to spinner
                        sp_category.setAdapter(dataAdapter);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<BusinessGetCategoryDataPOJO>> call, Throwable t) {

            }
        });
    }


    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch (view.getId()) {
            case R.id.ck_tender:
                if (checked) {
                    BusinessType = ck_tender.getText().toString();
                }
                BusinessType = BusinessType + blank;
                break;
            case R.id.ck_manufacture:
                if (checked) {
                    if (BusinessType.isEmpty()) {
                        BusinessType = BusinessType + ck_manufacture.getText().toString();
                    }
                    BusinessType = BusinessType + split + ck_manufacture.getText().toString();
                }
                BusinessType = BusinessType + blank;
                break;

            case R.id.ck_professional:
                if (checked) {
                    if (BusinessType.isEmpty()) {
                        BusinessType = BusinessType + ck_professional.getText().toString();
                    }
                    BusinessType = BusinessType + split + ck_professional.getText().toString();
                }
                BusinessType = BusinessType + blank;
                break;

            case R.id.ck_service_provider:
                if (checked) {
                    if (BusinessType.isEmpty()) {
                        BusinessType = BusinessType + ck_professional.getText().toString();
                    }
                    BusinessType = BusinessType + split + ck_service_provider.getText().toString();
                }
                BusinessType = BusinessType + blank;
                break;
            case R.id.ck_Basic:
                if (checked) {
                    AdvertiserType = ck_Basic.getText().toString();
                }
                AdvertiserType = AdvertiserType + blank;
                break;

            case R.id.ck_Premium:
                if (checked) {
                    if (AdvertiserType.isEmpty()) {
                        AdvertiserType = AdvertiserType + ck_Premium.getText().toString();
                    }
                    AdvertiserType = AdvertiserType + split + ck_Premium.getText().toString();
                }
                AdvertiserType = AdvertiserType + blank;
                break;
            case R.id.ck_Superlisting:
                if (checked) {
                    if (AdvertiserType.isEmpty()) {
                        AdvertiserType = AdvertiserType + ck_Superlisting.getText().toString();
                    }
                    AdvertiserType = AdvertiserType + split + ck_Superlisting.getText().toString();
                }
                AdvertiserType = AdvertiserType + blank;
                break;

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_submit:
                MyUtility.hideKeyboard(layout, BusinessAddAdvertized.this);
                if (submiForm()) {
                    if (MyUtility.isConnected(BusinessAddAdvertized.this))
                        addAddvertised();
                } else {
                    MyUtility.internetProblem(layout);
                }
                break;
            case R.id.bn_chooselogo:

                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE_Logo);
                break;
        }

    }


    private void addAddvertised() {

        showpDialog();

        HashMap<String, String> user = business_Medical_session.getUserDetails();
        final String id = user.get(Business_Medical_Session.KEY_ID);

        Name = et_company_name.getText().toString();
        TagLine = et_tag_line.getText().toString();
        Email = et_email.getText().toString();
        Mobile = et_mobile.getText().toString();
        Contact_Number = et_contact_no.getText().toString();
        Address1 = et_address_one.getText().toString();
        Address2 = et_address_two.getText().toString();
        Country1 = sp_country.getSelectedItem().toString();
        Country2 = sp_country2.getSelectedItem().toString();
        State1 = sp_state.getSelectedItem().toString();
        State2 = sp_state2.getSelectedItem().toString();
        City1 = sp_city.getSelectedItem().toString();
        City2 = sp_city2.getSelectedItem().toString();
        Description = et_short_desc.getText().toString();
        Long_Description = et_long_desc.getText().toString();
        Year = et_year_established.getText().toString();
        Website = et_website.getText().toString();
        Certification = et_certification.getText().toString();
        Licenses = et_license.getText().toString();
        Languages = et_language.getText().toString();
        Specialities = et_speciality.getText().toString();
        Payment_Method = et_payment_method.getText().toString();
        Category = sp_category.getSelectedItem().toString();
        Subcategory = sp_sub_category.getSelectedItem().toString();
        Locale = sp_locale.getSelectedItem().toString();
        Service_Area = sp_service_area.getSelectedItem().toString();
        Working_days = sp_working_days.getSelectedItem().toString();
        Hours_of_Operation = sp_hour_operation.getSelectedItem().toString();


        RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", file.getName(), requestBody);

        RequestBody reg_id = RequestBody.create(MediaType.parse("text/plain"), id);
        RequestBody name = RequestBody.create(MediaType.parse("text/plain"), Name);
        RequestBody tagLine = RequestBody.create(MediaType.parse("text/plain"), TagLine);
        RequestBody email = RequestBody.create(MediaType.parse("text/plain"), Email);
        RequestBody mobile = RequestBody.create(MediaType.parse("text/plain"), Mobile);
        RequestBody contact_number = RequestBody.create(MediaType.parse("text/plain"), Contact_Number);
        RequestBody address1 = RequestBody.create(MediaType.parse("text/plain"), Address1);
        RequestBody address2 = RequestBody.create(MediaType.parse("text/plain"), Address2);
        RequestBody countryone = RequestBody.create(MediaType.parse("text/plain"), Country1);
        RequestBody countrytwo = RequestBody.create(MediaType.parse("text/plain"), Country2);
        RequestBody stateone = RequestBody.create(MediaType.parse("text/plain"), State1);
        RequestBody statetwo = RequestBody.create(MediaType.parse("text/plain"), State2);
        RequestBody cityone = RequestBody.create(MediaType.parse("text/plain"), City1);
        RequestBody citytwo = RequestBody.create(MediaType.parse("text/plain"), City2);
        RequestBody description = RequestBody.create(MediaType.parse("text/plain"), Description);
        RequestBody description_long = RequestBody.create(MediaType.parse("text/plain"), Long_Description);
        RequestBody year = RequestBody.create(MediaType.parse("text/plain"), Year);
        RequestBody website = RequestBody.create(MediaType.parse("text/plain"), Website);
        RequestBody certification = RequestBody.create(MediaType.parse("text/plain"), Certification);
        RequestBody licenses = RequestBody.create(MediaType.parse("text/plain"), Licenses);
        RequestBody languages = RequestBody.create(MediaType.parse("text/plain"), Languages);
        RequestBody specialities = RequestBody.create(MediaType.parse("text/plain"), Specialities);
        RequestBody payment_Method = RequestBody.create(MediaType.parse("text/plain"), Payment_Method);
        RequestBody category = RequestBody.create(MediaType.parse("text/plain"), Category);
        RequestBody subcategory = RequestBody.create(MediaType.parse("text/plain"), Subcategory);
        RequestBody locale = RequestBody.create(MediaType.parse("text/plain"), Locale);
        RequestBody service_Area = RequestBody.create(MediaType.parse("text/plain"), Service_Area);
        RequestBody working_days = RequestBody.create(MediaType.parse("text/plain"), Working_days);
        RequestBody hours_of_Operation = RequestBody.create(MediaType.parse("text/plain"), Hours_of_Operation);
        RequestBody AdvertiserTypeone = RequestBody.create(MediaType.parse("text/plain"), AdvertiserType);
        RequestBody BusinessTypeone = RequestBody.create(MediaType.parse("text/plain"), BusinessType);

        Business_ServiceAPI serviceAPI = ApiClient.getRetrofit().create(Business_ServiceAPI.class);
        Call<BusinessGetSet> call = serviceAPI.AdvertiseRegistrationBusiness(fileToUpload, reg_id, name, tagLine, email, mobile,
                contact_number, address1, address2, countryone, countrytwo, stateone, statetwo, cityone, citytwo,
                description, description_long, year, website, certification, licenses, payment_Method, languages,
                category, subcategory, locale, service_Area, working_days, hours_of_Operation, specialities, BusinessTypeone, AdvertiserTypeone);
        call.enqueue(new Callback<BusinessGetSet>() {
            @Override
            public void onResponse(Call<BusinessGetSet> call, Response<BusinessGetSet> response) {
                hidepDialog();

                BusinessGetSet businessGetSet = response.body();
                if (businessGetSet != null) {
                    // Toast.makeText(BusinessAddAdvertized.this, businessGetSet.getMessage(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(BusinessAddAdvertized.this, BusinessActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<BusinessGetSet> call, Throwable t) {
                hidepDialog();
                Toast.makeText(BusinessAddAdvertized.this, getText(R.string.checkConn), Toast.LENGTH_SHORT).show();

            }
        });

    }


    private boolean submiForm() {
        if (!isImageAdded) {
            iv_Logo.requestFocus();
          /*  AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle("Alert");
            builder.setMessage("Please Choose Company Logo");
            builder.show();*/
            Toast.makeText(this, "Please Choose Company Logo", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!validateCompanyName()) {
            return false;
        }
        /*if (!validateTagLine()) {
            return false;
        }*/
        if (!validateEmail()) {
            return false;
        }
        if (!validateMobile()) {
            return false;
        }
       /* if (!validateContactCode()) {
            return false;
        }*/
       /* if (!validateContactNo()) {
            return false;
        }*/
        if (!validateAddressFirst()) {
            return false;
        }


        // Toast.makeText(getApplicationContext(), "Thank You!", Toast.LENGTH_SHORT).show();

        return true;
    }


    private boolean validateCompanyName() {
        String companyName = et_company_name.getText().toString().trim();

        if (companyName.isEmpty()) {
            tl_company_name.setError(getString(R.string.err_company_name));
            requestFocus(et_company_name);
            et_company_name.setError(null);
            return false;
        } else {
            tl_company_name.setErrorEnabled(false);
        }

        return true;
    }

   /* private boolean validateTagLine() {
        String tagLine = et_tag_line.getText().toString().trim();

        if (tagLine.isEmpty()) {
            tl_tag_line.setError(getString(R.string.err_tag_line));
            requestFocus(et_tag_line);
            return false;
        } else {
            tl_tag_line.setErrorEnabled(false);
        }

        return true;
    }*/

    private boolean validateEmail() {
        String email = et_email.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            tl_email.setError(getString(R.string.err_email));
            requestFocus(et_email);
            et_email.setError(null);
            return false;
        } else {
            tl_email.setErrorEnabled(false);
        }

        return true;
    }

    private boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean validateMobile() {
        String mobile = et_mobile.getText().toString().trim();

        if (mobile.isEmpty() && mobile.length() < 10 && mobile.matches("[7-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]")) {
            tl_mobile.setError(getString(R.string.err_mobile));
            requestFocus(et_mobile);
            et_mobile.setError(null);
            return false;
        } else {
            tl_mobile.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateAddressFirst() {
        String address1 = et_address_one.getText().toString().trim();

        if (address1.isEmpty()) {
            tl_address_one.setError(getString(R.string.err_address_one));
            requestFocus(et_address_one);
            et_address_one.setError(null);
            return false;
        } else {
            tl_address_one.setErrorEnabled(false);
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

        private MyTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            switch (view.getId()) {

                case R.id.et_companyName:
                    validateCompanyName();
                    break;
                case R.id.et_tagLine:
                    //validateTagLine();
                    break;
                case R.id.et_email:
                    validateEmail();
                    break;
                case R.id.et_mobile:
                    validateMobile();
                    break;
               /* case R.id.et_contact_code:
                    //validateContactCode();
                    break;*/
                case R.id.et_contactNo:
                    //validateContactNo();
                    break;
                case R.id.et_address1:
                    validateAddressFirst();
                    break;

            }
        }
    }

    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:

                onBackPressed();

                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @TargetApi(Build.VERSION_CODES.M)
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 2 && resultCode == RESULT_OK && data != null) {

            super.onActivityResult(requestCode, resultCode, data);

            Uri uri = data.getData();
            try {
                Glide.with(BusinessAddAdvertized.this).load(uri).into(iv_Logo);
                String filepath = FilePicker.getPath(BusinessAddAdvertized.this, uri);
                File afile = new File(filepath);
                file =new Compressor(this).compressToFile(afile);
                // personImage.setVisibility(View.VISIBLE);
                isImageAdded = true;
            }catch (Exception e){

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
}
