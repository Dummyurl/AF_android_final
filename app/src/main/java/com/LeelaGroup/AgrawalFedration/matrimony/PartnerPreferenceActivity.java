package com.LeelaGroup.AgrawalFedration.matrimony;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.LeelaGroup.AgrawalFedration.MatrimonySession;
import com.LeelaGroup.AgrawalFedration.MyUtility;
import com.LeelaGroup.AgrawalFedration.Network.ApiClient;
import com.LeelaGroup.AgrawalFedration.R;
import com.LeelaGroup.AgrawalFedration.Service.ServiceMatrimony;
import com.LeelaGroup.AgrawalFedration.matrimony.models.PartnrPrefdetails;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PartnerPreferenceActivity extends AppCompatActivity {

   /* String ptnerpref_image,lookngfor,prefcontrylvgin,prefstatelvgin
            ,prefcitylvgin,prefagefrm,prefhtfrmft,prefwtfrm,prefcmplxn,prefeducton,
            prefreligion,prefcast;*/

    String mat_id;
    TextView d_per_lookngfor, d_per_contrylvgin, d_per_statelvgin, d_per_citylvgin, d_agefrm, d_ageto, d_per_htfrm, d_perMaxht, d_per_cmplxn, d_per_educton,
            d_per_religion, d_per_cast;
    TextView i_d_per_lookngfor, i_d_per_contrylvgin, i_d_per_statelvgin, i_d_per_citylvgin, i_d_agefrm, i_d_ageto, i_d_per_htfrm, i_d_perMaxht, i_d_per_cmplxn, i_d_per_educton,
            i_d_per_religion, i_d_per_cast;
    ImageView d_person_ptnerpref_image;

    Toolbar toolbar;

    MatrimonySession matrimonySession;
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_preference);

        matrimonySession = new MatrimonySession(getApplicationContext());

        init();
      //  initIcon();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Partner Preference Details");
        layout = (LinearLayout) findViewById(R.id.parent);
        if (matrimonySession.checkLogin())
            finish();

        mat_id = getIntent().getStringExtra("mat_id");
        if (MyUtility.isConnected(PartnerPreferenceActivity.this)) {
            getParnrPrefdetails();
        } else {
            MyUtility.internetProblem(layout);
        }
    }

    private void getParnrPrefdetails() {
        ServiceMatrimony serviceMatrimony = ApiClient.getRetrofit().create(ServiceMatrimony.class);
        Call<PartnrPrefdetails> parnerCall = serviceMatrimony.gerPartnrPrefDetails(mat_id);
        parnerCall.enqueue(new Callback<PartnrPrefdetails>() {
            @Override
            public void onResponse(Call<PartnrPrefdetails> call, Response<PartnrPrefdetails> response) {
                PartnrPrefdetails getPtnrDetl = response.body();
                d_per_lookngfor.setText(getPtnrDetl.getMregLookingFor());
                d_per_contrylvgin.setText(getPtnrDetl.getMregCountryLeaving());
                d_per_statelvgin.setText(getPtnrDetl.getMregStateLeaving());
                d_per_citylvgin.setText(getPtnrDetl.getMregCityLeaving());
                d_agefrm.setText(getPtnrDetl.getMregRegMinage());
                d_ageto.setText(getPtnrDetl.getMregRegMaxage());
                d_per_htfrm.setText(getPtnrDetl.getMregMaxHt());
                d_perMaxht.setText(getPtnrDetl.getMregMinHt());
                d_per_cmplxn.setText(getPtnrDetl.getMregGroomComplexion());
                d_per_educton.setText(getPtnrDetl.getMregEduc());
                d_per_religion.setText(getPtnrDetl.getMregReligion());
                d_per_cast.setText(getPtnrDetl.getMregCaste());
            }

            @Override
            public void onFailure(Call<PartnrPrefdetails> call, Throwable t) {

                Toast.makeText(PartnerPreferenceActivity.this, getText(R.string.checkConn), Toast.LENGTH_LONG).show();
            }
        });
    }


    public void init() {
        //d_person_ptnerpref_image =(ImageView)findViewById(R.id.d_person_ptnerpref_image);
        d_per_lookngfor = (TextView) findViewById(R.id.d_per_lookngfor);
        d_per_contrylvgin = (TextView) findViewById(R.id.d_per_contrylvgin);
        d_per_statelvgin = (TextView) findViewById(R.id.d_per_statelvgin);
        d_per_citylvgin = (TextView) findViewById(R.id.d_per_citylvgin);
        d_agefrm = (TextView) findViewById(R.id.d_agefrom);
        d_ageto = (TextView) findViewById(R.id.d_ageto);
        d_per_htfrm = (TextView) findViewById(R.id.d_per_htfrm);
        d_perMaxht = (TextView) findViewById(R.id.d_maxht);
        d_per_cmplxn = (TextView) findViewById(R.id.d_per_cmplxn);
        d_per_educton = (TextView) findViewById(R.id.d_per_educton);
        d_per_religion = (TextView) findViewById(R.id.d_per_religion);
        d_per_cast = (TextView) findViewById(R.id.d_per_cast);
    }

    public void initIcon() {
        Typeface icon = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        //d_person_ptnerpref_image =(ImageView)findViewById(R.id.d_person_ptnerpref_image);
        i_d_per_lookngfor = (TextView) findViewById(R.id.per_profl_lookngfor);
        i_d_per_contrylvgin = (TextView) findViewById(R.id.per_countrylvgin);
        i_d_per_statelvgin = (TextView) findViewById(R.id.per_statlvgin);
        i_d_per_citylvgin = (TextView) findViewById(R.id.per_citylvgin);
        i_d_agefrm = (TextView) findViewById(R.id.per_minage);
        i_d_ageto = (TextView) findViewById(R.id.per_maxage);
        i_d_per_htfrm = (TextView) findViewById(R.id.tv_minheight);
        i_d_perMaxht = (TextView) findViewById(R.id.tv_per_maxht);
        i_d_per_cmplxn = (TextView) findViewById(R.id.tv_per_complxn);
        i_d_per_educton = (TextView) findViewById(R.id.tv_per_eduction);
        i_d_per_religion = (TextView) findViewById(R.id.tv_per_religion);
        i_d_per_cast = (TextView) findViewById(R.id.tv_per_cast);

        i_d_per_lookngfor.setTypeface(icon);
        i_d_per_contrylvgin.setTypeface(icon);
        i_d_per_statelvgin.setTypeface(icon);
        i_d_per_citylvgin.setTypeface(icon);
        i_d_agefrm.setTypeface(icon);
        i_d_ageto.setTypeface(icon);
        i_d_per_htfrm.setTypeface(icon);
        i_d_perMaxht.setTypeface(icon);
        i_d_per_cmplxn.setTypeface(icon);
        i_d_per_educton.setTypeface(icon);
        i_d_per_religion.setTypeface(icon);
        i_d_per_cast.setTypeface(icon);

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

