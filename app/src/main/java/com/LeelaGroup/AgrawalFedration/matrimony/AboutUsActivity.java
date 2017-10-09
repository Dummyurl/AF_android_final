package com.LeelaGroup.AgrawalFedration.matrimony;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.LeelaGroup.AgrawalFedration.AboutUs;
import com.LeelaGroup.AgrawalFedration.MatrimonySession;
import com.LeelaGroup.AgrawalFedration.MyUtility;
import com.LeelaGroup.AgrawalFedration.Network.ApiClient;
import com.LeelaGroup.AgrawalFedration.R;
import com.LeelaGroup.AgrawalFedration.Service.ServiceMatrimony;
import com.LeelaGroup.AgrawalFedration.matrimony.models.AboutUsPojo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AboutUsActivity extends AppCompatActivity {
    TextView abouthead,abtcontnt,tvneed,tvconcept;
    TextView need,concept;
    MatrimonySession matrimonySession;
Toolbar toolbar;
CoordinatorLayout layout;
    String mat_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        matrimonySession=new MatrimonySession(getApplicationContext());

        init();

        if(matrimonySession.checkLogin())
            finish();

        mat_id=getIntent().getStringExtra("mat_id");
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("About Us");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // enabling action bar app icon and behaving it as toggle button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        layout=(CoordinatorLayout)findViewById(R.id.parent);

        if (MyUtility.isConnected(AboutUsActivity.this)){
            getAboutUsData();
        }else {
            MyUtility.internetProblem(layout);
        }
    }

    private void getAboutUsData() {
        ServiceMatrimony serviceMatrimony = ApiClient.getRetrofit().create(ServiceMatrimony.class);
        Call<AboutUsPojo> call=serviceMatrimony.getAboutDetails();

        call.enqueue(new Callback<AboutUsPojo>() {
            @Override
            public void onResponse(Call<AboutUsPojo> call, Response<AboutUsPojo> response) {
                AboutUsPojo aboutUsPojo=response.body();

                abouthead.setText(aboutUsPojo.getMatAbtName());
                abtcontnt.setText(aboutUsPojo.getMatAbtDesc());
                tvneed.setText(aboutUsPojo.getMatAbtNeedDesc());
                tvconcept.setText(aboutUsPojo.getMatAbtConcDesc());
            }

            @Override
            public void onFailure(Call<AboutUsPojo> call, Throwable t) {

                Toast.makeText(AboutUsActivity.this,getText(R.string.checkConn), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void init() {
        abouthead=(TextView)findViewById(R.id.tv_abtus);
        abtcontnt=(TextView)findViewById(R.id.tv_abtus_content);
        tvneed=(TextView)findViewById(R.id.tv_need);
        tvconcept=(TextView)findViewById(R.id.tv_concept);

        need=(TextView) findViewById(R.id.btn_need);
        concept=(TextView) findViewById(R.id.btn_concept);

    }


    public void goToLeelaGroupWebsite(View v){
        Uri uri = Uri.parse("http://www.theleelagroup.com/");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
        finish();
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
