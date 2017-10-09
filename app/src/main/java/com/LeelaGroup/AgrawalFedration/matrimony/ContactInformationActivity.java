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
import com.LeelaGroup.AgrawalFedration.matrimony.models.BasicDetailAndContactInfo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactInformationActivity extends AppCompatActivity {

    String mat_id;
    ImageView profilepic;
    Toolbar toolbar;
    TextView mob,lndline,email,addr,cntry,state,city,pincode,resstae;
    TextView i_mob,i_lndline,i_email,i_addr,i_cntry,i_state,i_city,i_pincode,i_resstae;

    MatrimonySession matrimonySession;
    LinearLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_information);
        matrimonySession=new MatrimonySession(getApplicationContext());

        init();
       // initIcon();
        layout=(LinearLayout)findViewById(R.id.parent);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Contact Information");

        if(matrimonySession.checkLogin())
            finish();

        mat_id=getIntent().getStringExtra("mat_id");

        if (MyUtility.isConnected(ContactInformationActivity.this)) {
            getContactInfo();
        } else {
            MyUtility.internetProblem(layout);
        }
    }

    private void getContactInfo() {
        ServiceMatrimony serviceMatrimony = ApiClient.getRetrofit().create(ServiceMatrimony.class);
        Call<BasicDetailAndContactInfo> getBasicCall=serviceMatrimony.getBasicDetailsAndContact(mat_id);
        getBasicCall.enqueue(new Callback<BasicDetailAndContactInfo>() {
            @Override
            public void onResponse(Call<BasicDetailAndContactInfo> call, Response<BasicDetailAndContactInfo> response) {
                BasicDetailAndContactInfo basicDetailAndContactInfo=response.body();

                mob.setText(basicDetailAndContactInfo.getMregPhone());
                lndline.setText(basicDetailAndContactInfo.getMregLandline());
                email.setText(basicDetailAndContactInfo.getMregEmail());
                addr.setText(basicDetailAndContactInfo.getMregAddr());
                cntry.setText(basicDetailAndContactInfo.getMregCountry());
                state.setText(basicDetailAndContactInfo.getMregState());
                city.setText(basicDetailAndContactInfo.getMregCity());
                pincode.setText(basicDetailAndContactInfo.getMregPincode());
                resstae.setText(basicDetailAndContactInfo.getMregResidStatus());


            }

            @Override
            public void onFailure(Call<BasicDetailAndContactInfo> call, Throwable t) {

                Toast.makeText(ContactInformationActivity.this, getText(R.string.checkConn), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void init()
    {
        //profilepic=(ImageView)findViewById(R.id.d_person_continfo_image);
        mob=(TextView)findViewById(R.id.d_per_mob);
        lndline=(TextView)findViewById(R.id.d_per_landline_no);
        email=(TextView)findViewById(R.id.d_per_email);
        addr=(TextView)findViewById(R.id.d_per_address);
        cntry=(TextView)findViewById(R.id.d_per_country);
        state=(TextView)findViewById(R.id.d_per_state);
        city=(TextView)findViewById(R.id.d_per_city);
        pincode=(TextView)findViewById(R.id.d_per_pincode);
        resstae=(TextView)findViewById(R.id.d_per_residential_sttus);

    }
    public void initIcon(){
        Typeface icon = Typeface.createFromAsset(this.getAssets(), "fontawesome-webfont.ttf" );

        i_mob = (TextView) findViewById(R.id.per_mobile_no);
        i_mob.setTypeface(icon);

        i_lndline = (TextView) findViewById(R.id.per_landline_no);
        i_lndline.setTypeface(icon);

        i_email = (TextView) findViewById(R.id.per_email);
        i_email.setTypeface(icon);

        i_addr = (TextView) findViewById(R.id.per_address);
        i_addr.setTypeface(icon);

        i_cntry = (TextView) findViewById(R.id.per_country);
        i_cntry.setTypeface(icon);

        i_state = (TextView) findViewById(R.id.per_state);
        i_state.setTypeface(icon);

        i_city = (TextView) findViewById(R.id.per_city);
        i_city.setTypeface(icon);

        i_pincode = (TextView) findViewById(R.id.per_pincode);
        i_pincode.setTypeface(icon);

        i_resstae = (TextView) findViewById(R.id.per_residential_sttus);
        i_resstae.setTypeface(icon);

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
