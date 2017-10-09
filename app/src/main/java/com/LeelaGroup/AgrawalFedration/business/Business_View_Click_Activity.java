package com.LeelaGroup.AgrawalFedration.business;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.LeelaGroup.AgrawalFedration.Business_Pojo.Business_ViewFull_Add_POJO;
import com.LeelaGroup.AgrawalFedration.Business_Medical_Session;
import com.LeelaGroup.AgrawalFedration.MyUtility;
import com.LeelaGroup.AgrawalFedration.Network.ApiClient;
import com.LeelaGroup.AgrawalFedration.R;
import com.LeelaGroup.AgrawalFedration.Service.Business_ServiceAPI;
import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Business_View_Click_Activity extends AppCompatActivity {

    TextView tv_company_name, tv_tag_line, tv_email, tv_mobile, tv_contact_no, tv_address1, tv_address2, tv_country1, tv_state1,
            tv_city1, tv_country2, tv_state2, tv_city2, tv_description, tv_long_description, tv_establist_year,
            tv_website, tv_certification, tv_licenses, tv_payment_method, tv_languages, tv_category, tv_subcategory, tv_locale,
            tv_service_area, tv_working_day, tv_hours_operation, tv_Specialities, tv_business_type, tv_advertiser_type;


    CircleImageView imageView;
    String arid;
    LinearLayout layout;
    Business_Medical_Session business_Medical_session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bisiness_view_click);

        layout = (LinearLayout) findViewById(R.id.parent);

        Typeface font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        TextView button = (TextView) findViewById(R.id.na);
        TextView textView = (TextView) findViewById(R.id.tag_icon);
        TextView email_icon = (TextView) findViewById(R.id.email_icon);
        TextView jjj = (TextView) findViewById(R.id.m_icon);
        TextView land = (TextView) findViewById(R.id.landline_icon);
        TextView country = (TextView) findViewById(R.id.cun);
        TextView state = (TextView) findViewById(R.id.s_icon);
        TextView city = (TextView) findViewById(R.id.city_icon);
        TextView cuntry1 = (TextView) findViewById(R.id.c1);
        TextView s1 = (TextView) findViewById(R.id.s1);
        TextView c1 = (TextView) findViewById(R.id.c2);
        TextView established = (TextView) findViewById(R.id.established);
        TextView description = (TextView) findViewById(R.id.dec_icon);
        TextView l_description = (TextView) findViewById(R.id.lD_icon);
        TextView web = (TextView) findViewById(R.id.web);
        TextView certificate = (TextView) findViewById(R.id.cer);
        TextView payment = (TextView) findViewById(R.id.payment);
        TextView locale_icon = (TextView) findViewById(R.id.locale_icon);
        TextView wor = (TextView) findViewById(R.id.working);
        TextView logo = (TextView) findViewById(R.id.logo);
        TextView spec = (TextView) findViewById(R.id.spec);
        TextView business = (TextView) findViewById(R.id.business);
        TextView add = (TextView) findViewById(R.id.add);
        TextView t9 = (TextView) findViewById(R.id.textView9);
        TextView categry = (TextView) findViewById(R.id.categry);
        TextView sub_c = (TextView) findViewById(R.id.sub_c);
        TextView serve_area = (TextView) findViewById(R.id.serve_area);
        TextView clock = (TextView) findViewById(R.id.clock);
        TextView license = (TextView) findViewById(R.id.lic_icon);

        button.setTypeface(font);
        textView.setTypeface(font);
        email_icon.setTypeface(font);
        jjj.setTypeface(font);
        land.setTypeface(font);
        country.setTypeface(font);
        state.setTypeface(font);
        city.setTypeface(font);
        cuntry1.setTypeface(font);
        s1.setTypeface(font);
        c1.setTypeface(font);
        established.setTypeface(font);
        description.setTypeface(font);
        l_description.setTypeface(font);
        web.setTypeface(font);
        certificate.setTypeface(font);
        license.setTypeface(font);
        payment.setTypeface(font);
        locale_icon.setTypeface(font);
        wor.setTypeface(font);
        logo.setTypeface(font);
        spec.setTypeface(font);
        business.setTypeface(font);
        add.setTypeface(font);
        t9.setTypeface(font);
        categry.setTypeface(font);
        sub_c.setTypeface(font);
        serve_area.setTypeface(font);
        clock.setTypeface(font);


        business_Medical_session = new Business_Medical_Session(getApplicationContext());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Business Details");

        arid = getIntent().getStringExtra("ar_id");

        imageView = (CircleImageView) findViewById(R.id.img_logo);

        tv_company_name = (TextView) findViewById(R.id.tv_company_name);
        tv_tag_line = (TextView) findViewById(R.id.tv_tag_line);
        tv_email = (TextView) findViewById(R.id.tv_email);
        tv_mobile = (TextView) findViewById(R.id.tv_mobile);
        tv_contact_no = (TextView) findViewById(R.id.tv_contact_no);
        tv_address1 = (TextView) findViewById(R.id.tv_address1);
        tv_address2 = (TextView) findViewById(R.id.tv_address2);
        tv_country1 = (TextView) findViewById(R.id.tv_country1);
        tv_state1 = (TextView) findViewById(R.id.tv_state1);
        tv_city1 = (TextView) findViewById(R.id.tv_city1);
        tv_country2 = (TextView) findViewById(R.id.tv_country2);
        tv_state2 = (TextView) findViewById(R.id.tv_state2);
        tv_city2 = (TextView) findViewById(R.id.tv_city2);
        tv_description = (TextView) findViewById(R.id.tv_description);
        tv_long_description = (TextView) findViewById(R.id.tv_long_description);
        tv_establist_year = (TextView) findViewById(R.id.tv_establist_year);
        tv_website = (TextView) findViewById(R.id.tv_website);
        tv_certification = (TextView) findViewById(R.id.tv_certification);
        tv_licenses = (TextView) findViewById(R.id.tv_licenses);
        tv_payment_method = (TextView) findViewById(R.id.tv_payment_method);
        tv_languages = (TextView) findViewById(R.id.tv_languages);
        tv_category = (TextView) findViewById(R.id.tv_category);
        tv_subcategory = (TextView) findViewById(R.id.tv_subcategory);
        tv_locale = (TextView) findViewById(R.id.tv_locale);
        tv_service_area = (TextView) findViewById(R.id.tv_service_area);
        tv_working_day = (TextView) findViewById(R.id.tv_working_day);
        tv_hours_operation = (TextView) findViewById(R.id.tv_hours_operation);
        tv_Specialities = (TextView) findViewById(R.id.tv_Specialities);
        tv_business_type = (TextView) findViewById(R.id.tv_business_type);
        tv_advertiser_type = (TextView) findViewById(R.id.tv_advertiser_type);


        if (business_Medical_session.checkLogin())
            finish();

        if (MyUtility.isConnected(Business_View_Click_Activity.this)){

            getView();
        }else {
            MyUtility.internetProblem(layout);
        }
    }

    private void getView() {
        Business_ServiceAPI service = ApiClient.getRetrofit().create(Business_ServiceAPI.class);
        Call<Business_ViewFull_Add_POJO> call = service.getFullAdd(arid);
        call.enqueue(new Callback<Business_ViewFull_Add_POJO>() {
            @Override
            public void onResponse(Call<Business_ViewFull_Add_POJO> call, Response<Business_ViewFull_Add_POJO> response) {
                Business_ViewFull_Add_POJO pojo = response.body();


                Glide.with(Business_View_Click_Activity.this).load(pojo.getLogo()).into(imageView);

                tv_company_name.setText(pojo.getName());
                tv_tag_line.setText(pojo.getTagLine());
                tv_email.setText(pojo.getEmail());
                tv_mobile.setText(pojo.getMobile());
                tv_contact_no.setText(pojo.getContactNumber());
                tv_address1.setText(pojo.getAddress1());
                tv_address2.setText(pojo.getAddress2());
                tv_country1.setText(pojo.getCountry());
                tv_state1.setText(pojo.getState());
                tv_city1.setText(pojo.getCityname());
                tv_country2.setText(pojo.getSecondaryCountry());
                tv_state2.setText(pojo.getSecondaryState());
                tv_city2.setText(pojo.getSecondaryCity());
                tv_description.setText(pojo.getDescription());
                tv_long_description.setText(pojo.getLongDescription());
                tv_establist_year.setText(pojo.getYear());
                tv_website.setText(pojo.getWebsite());
                tv_certification.setText(pojo.getCertification());
                tv_licenses.setText(pojo.getLicenses());
                tv_payment_method.setText(pojo.getPaymentMethod());
                tv_languages.setText(pojo.getLanguages());
                tv_category.setText(pojo.getCategory());
                tv_subcategory.setText(pojo.getSubcategory());
                tv_locale.setText(pojo.getLocale());
                tv_service_area.setText(pojo.getServiceArea());
                tv_working_day.setText(pojo.getWorkingDays());
                tv_hours_operation.setText(pojo.getHoursOfOperation());
                tv_Specialities.setText(pojo.getSpecialities());
                tv_business_type.setText(pojo.getBusinessType());
                tv_advertiser_type.setText(pojo.getAdvertiserType());

                // Toast.makeText(Business_View_Click_Activity.this, "success", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<Business_ViewFull_Add_POJO> call, Throwable t) {

                Toast.makeText(Business_View_Click_Activity.this, getText(R.string.checkConn), Toast.LENGTH_SHORT).show();
            }
        });
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
}
