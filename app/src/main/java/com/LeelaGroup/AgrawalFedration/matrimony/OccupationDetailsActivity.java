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
import com.LeelaGroup.AgrawalFedration.matrimony.models.EducationAndOccupationDetails;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OccupationDetailsActivity extends AppCompatActivity {

    String mat_id;
    ImageView profilepic;
    TextView occup, industry, buisnemp, pAnlIncm;
    TextView i_occup, i_industry, i_buisnemp, i_pAnlIncm;
    MatrimonySession matrimonySession;
    Toolbar toolbar;
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_occupation_details);
        mat_id = getIntent().getStringExtra("mat_id");
        matrimonySession = new MatrimonySession(getApplicationContext());

        init();
       // initIcon();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Occupation Details");
        layout = (LinearLayout) findViewById(R.id.parent);
        if (matrimonySession.checkLogin())
            finish();

        if (MyUtility.isConnected(OccupationDetailsActivity.this)) {
            getOccuDetail();
        } else {
            MyUtility.internetProblem(layout);
        }
    }

    private void getOccuDetail() {
        ServiceMatrimony serviceMatrimony = ApiClient.getRetrofit().create(ServiceMatrimony.class);
        Call<EducationAndOccupationDetails> ocuuptnCall = serviceMatrimony.getEduAndOccuDetails(mat_id);
        ocuuptnCall.enqueue(new Callback<EducationAndOccupationDetails>() {
            @Override
            public void onResponse(Call<EducationAndOccupationDetails> call, Response<EducationAndOccupationDetails> response) {
                EducationAndOccupationDetails occuDetl = response.body();
                occup.setText(occuDetl.getMat_reg_occup());
                industry.setText(occuDetl.getMat_reg_industry());
                buisnemp.setText(occuDetl.getMat_reg_empl());
                pAnlIncm.setText(occuDetl.getMat_reg_ipa());
            }

            @Override
            public void onFailure(Call<EducationAndOccupationDetails> call, Throwable t) {

                Toast.makeText(OccupationDetailsActivity.this, getText(R.string.checkConn), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void init() {
        //profilepic=(ImageView)findViewById(R.id.d_person_occupatn_image);
        occup = (TextView) findViewById(R.id.d_per_occupation);
        industry = (TextView) findViewById(R.id.d_per_industry);
        buisnemp = (TextView) findViewById(R.id.d_per_bsnsempat);
        pAnlIncm = (TextView) findViewById(R.id.d_per_psnalanulincome);

    }

    public void initIcon() {
        Typeface icon = Typeface.createFromAsset(this.getAssets(), "fontawesome-webfont.ttf");
        //profilepic=(ImageView)findViewById(R.id.d_person_occupatn_image);
        i_occup = (TextView) findViewById(R.id.per_occupatn);
        i_industry = (TextView) findViewById(R.id.per_industry);
        i_buisnemp = (TextView) findViewById(R.id.per_bsnsempat);
        i_pAnlIncm = (TextView) findViewById(R.id.per_psnalanulincome);

        i_occup.setTypeface(icon);
        i_industry.setTypeface(icon);
        i_buisnemp.setTypeface(icon);
        i_pAnlIncm.setTypeface(icon);
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
