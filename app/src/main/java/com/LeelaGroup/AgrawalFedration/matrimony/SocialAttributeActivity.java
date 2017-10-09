package com.LeelaGroup.AgrawalFedration.matrimony;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.LeelaGroup.AgrawalFedration.MatrimonySession;
import com.LeelaGroup.AgrawalFedration.MyUtility;
import com.LeelaGroup.AgrawalFedration.Network.ApiClient;
import com.LeelaGroup.AgrawalFedration.R;
import com.LeelaGroup.AgrawalFedration.Service.ServiceMatrimony;
import com.LeelaGroup.AgrawalFedration.matrimony.models.SocialAndFamilyDetails;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SocialAttributeActivity extends AppCompatActivity {

    MatrimonySession matrimonySession;
    String mat_id;
    Toolbar toolbar;
    TextView mat_reg_manglik, mat_reg_horoscope_match, mat_reg_gothra_self, mat_reg_gothra_mama;
    TextView i_mat_reg_manglik, i_mat_reg_horoscope_match, i_mat_reg_gothra_self;
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_attribute);
        mat_id = getIntent().getStringExtra("mat_id");
        matrimonySession = new MatrimonySession(getApplicationContext());

        init();
        initIcon();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Social Attributes");
        layout = (LinearLayout) findViewById(R.id.parent);
        if (matrimonySession.checkLogin())
            finish();
        if (MyUtility.isConnected(SocialAttributeActivity.this)) {
            getSocialDetails();
        }else {
            MyUtility.internetProblem(layout);
        }
    }

    private void getSocialDetails() {
        ServiceMatrimony serviceMatrimony = ApiClient.getRetrofit().create(ServiceMatrimony.class);
        Call<SocialAndFamilyDetails> getBasicCall = serviceMatrimony.getfFamilyAndSocialDetails(mat_id);
        getBasicCall.enqueue(new Callback<SocialAndFamilyDetails>() {
            @Override
            public void onResponse(Call<SocialAndFamilyDetails> call, Response<SocialAndFamilyDetails> response) {
                SocialAndFamilyDetails socAndFamDetl = response.body();

                mat_reg_manglik.setText(socAndFamDetl.getMatRegManglik());
                mat_reg_horoscope_match.setText(socAndFamDetl.getMatRegHoroscopeMatch());
                mat_reg_gothra_self.setText(socAndFamDetl.getMatRegGothraSelf());
                //mat_reg_gothra_mama.setText(socAndFamDetl.getMatRegGothraMama());

            }

            @Override
            public void onFailure(Call<SocialAndFamilyDetails> call, Throwable t) {

                Toast.makeText(SocialAttributeActivity.this, getText(R.string.checkConn), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void init() {
        mat_reg_manglik = (TextView) findViewById(R.id.d_per_manglik);
        mat_reg_horoscope_match = (TextView) findViewById(R.id.d_per_horoscope);
        mat_reg_gothra_self = (TextView) findViewById(R.id.d_per_gothraself);
        //mat_reg_gothra_mama=(TextView)findViewById(R.id.d_per_gothramama);

    }

    private void initIcon() {

        Typeface icon = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");

        i_mat_reg_manglik = (TextView) findViewById(R.id.per_maglik);
        i_mat_reg_horoscope_match = (TextView) findViewById(R.id.per_horoscope);
        i_mat_reg_gothra_self = (TextView) findViewById(R.id.per_gothra);
        //mat_reg_gothra_mama=(TextView)findViewById(R.id.d_per_gothramama);

        i_mat_reg_manglik.setTypeface(icon);
        i_mat_reg_horoscope_match.setTypeface(icon);
        i_mat_reg_gothra_self.setTypeface(icon);

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
