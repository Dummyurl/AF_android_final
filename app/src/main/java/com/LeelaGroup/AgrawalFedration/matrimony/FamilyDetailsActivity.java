package com.LeelaGroup.AgrawalFedration.matrimony;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import com.LeelaGroup.AgrawalFedration.matrimony.models.SocialAndFamilyDetails;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FamilyDetailsActivity extends AppCompatActivity {

    String mat_id;
    ImageView d_person_famildtl_image;
    TextView ftrname, mthrname, mocco, foccu, nob, nos, nobm, nosm, famstat, famtype, famincm;
    TextView i_ftrname, i_mthrname, i_mocco, i_foccu, i_nob, i_nos, i_nobm, i_nosm, i_famstat, i_famtype, i_famincm;
    MatrimonySession matrimonySession;
    Toolbar toolbar;
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mat_family_details);
        matrimonySession=new MatrimonySession(getApplicationContext());

        init();
       // initIcon();

        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Family Details");
        layout=(LinearLayout)findViewById(R.id.parent);
        if(matrimonySession.checkLogin())
            finish();

        mat_id = getIntent().getStringExtra("mat_id");

        if (MyUtility.isConnected(FamilyDetailsActivity.this)) {
            getFamilyDetails();
        } else {
            MyUtility.internetProblem(layout);
        }
    }

    public void getFamilyDetails() {
        ServiceMatrimony serviceMatrimony = ApiClient.getRetrofit().create(ServiceMatrimony.class);
        Call<SocialAndFamilyDetails> getBasicCall = serviceMatrimony.getfFamilyAndSocialDetails(mat_id);
        getBasicCall.enqueue(new Callback<SocialAndFamilyDetails>() {
            @Override
            public void onResponse(Call<SocialAndFamilyDetails> call, Response<SocialAndFamilyDetails> response) {
                SocialAndFamilyDetails socAndFamDetl = response.body();

                ftrname.setText(socAndFamDetl.getMatRegFatherName());
                mthrname.setText(socAndFamDetl.getMatRegMotherName());
                mocco.setText(socAndFamDetl.getMatRegMotherOccup());
                foccu.setText(socAndFamDetl.getMatRegFatherOccup());
                nob.setText(socAndFamDetl.getMatRegNoBrother());
                nos.setText(socAndFamDetl.getMatRegNoSister());
                nobm.setText(socAndFamDetl.getMatMarBro());
                nosm.setText(socAndFamDetl.getMatMarSis());
                famstat.setText(socAndFamDetl.getMatRegStatus());
                famtype.setText(socAndFamDetl.getMregFamType());
                famincm.setText(socAndFamDetl.getMregFamLpa());

            }

            @Override
            public void onFailure(Call<SocialAndFamilyDetails> call, Throwable t) {

                Toast.makeText(FamilyDetailsActivity.this,getText(R.string.checkConn), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void init() {
        //d_person_famildtl_image=(ImageView)findViewById(R.id.d_person_edudetl_image);
        ftrname = (TextView) findViewById(R.id.d_per_fatherfullname);
        mthrname = (TextView) findViewById(R.id.d_per_motherfullname);
        mocco = (TextView) findViewById(R.id.d_per_mtheroccptn);
        foccu = (TextView) findViewById(R.id.d_per_ftheroccptn);
        nob = (TextView) findViewById(R.id.d_per_noofbros);
        nos = (TextView) findViewById(R.id.d_per_sister);
        nobm = (TextView) findViewById(R.id.d_per_noofbrosmaried);
        nosm = (TextView) findViewById(R.id.d_per_noofsismarried);
        famstat = (TextView) findViewById(R.id.d_per_familysatus);
        famtype = (TextView) findViewById(R.id.d_per_familytype);
        famincm = (TextView) findViewById(R.id.d_per_familyanulincome);

    }
    public void initIcon() {
        //d_person_famildtl_image=(ImageView)findViewById(R.id.d_person_edudetl_image);
        Typeface icon = Typeface.createFromAsset(this.getAssets(), "fontawesome-webfont.ttf" );

        i_ftrname = (TextView) findViewById(R.id.per_fatherfullfname);
        i_mthrname = (TextView) findViewById(R.id.per_motherfullname);
        i_mocco = (TextView) findViewById(R.id.per_mtheroccptn);
        i_foccu = (TextView) findViewById(R.id.per_ftheroccptn);
        i_nob = (TextView) findViewById(R.id.per_noofbros);
        i_nos = (TextView) findViewById(R.id.per_noofsis);
        i_nobm = (TextView) findViewById(R.id.per_noofbrosmaried);
        i_nosm = (TextView) findViewById(R.id.per_noofsismaried);
        i_famstat = (TextView) findViewById(R.id.per_familysatus);
        i_famtype = (TextView) findViewById(R.id.per_familytype);
        i_famincm = (TextView) findViewById(R.id.per_fmlyannulincome);

        i_ftrname.setTypeface(icon);
        i_mthrname.setTypeface(icon);
        i_mocco.setTypeface(icon);
        i_foccu.setTypeface(icon);
        i_nob.setTypeface(icon);
        i_nos.setTypeface(icon);
        i_nobm.setTypeface(icon);
        i_nosm.setTypeface(icon);
        i_famstat.setTypeface(icon);
        i_famtype.setTypeface(icon);
        i_famincm.setTypeface(icon);

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
