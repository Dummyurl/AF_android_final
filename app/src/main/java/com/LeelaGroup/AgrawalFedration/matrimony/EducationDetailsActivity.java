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

public class EducationDetailsActivity extends AppCompatActivity {
    String mat_id;
    ImageView d_person_edudetl_image;
    TextView grad,pstgtrad,dctrate,cetfn;
    TextView i_grad;
    LinearLayout layout;
    Toolbar toolbar;
    MatrimonySession matrimonySession;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education_details_matrimony);
        matrimonySession =new MatrimonySession(getApplicationContext());

        init();

        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Education Details");
        layout=(LinearLayout)findViewById(R.id.parent);
        if(matrimonySession.checkLogin())
            finish();

        mat_id= getIntent().getStringExtra("mat_id");

        if (MyUtility.isConnected(EducationDetailsActivity.this)) {
            getEducationDetails();
        } else {
            MyUtility.internetProblem(layout);
        }
    }

    private void getEducationDetails() {
        ServiceMatrimony serviceMatrimony= ApiClient.getRetrofit().create(ServiceMatrimony.class);
        Call<EducationAndOccupationDetails> geteduacationCall=serviceMatrimony.getEduAndOccuDetails(mat_id);
        geteduacationCall.enqueue(new Callback<EducationAndOccupationDetails>() {
            @Override
            public void onResponse(Call<EducationAndOccupationDetails> call, Response<EducationAndOccupationDetails> response) {
                EducationAndOccupationDetails getEducation = response.body();

                grad.setText(getEducation.getMat_reg_edu());
               /* pstgtrad.setText(getEducation.getMat_reg_pg());
                dctrate.setText(getEducation.getMat_reg_docto());
                cetfn.setText(getEducation.getMat_reg_certifi());*/
            }

            @Override
            public void onFailure(Call<EducationAndOccupationDetails> call, Throwable t) {

                Toast.makeText(EducationDetailsActivity.this, getText(R.string.checkConn), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void init()
    {
        //d_person_edudetl_image=(ImageView)findViewById(R.id.d_person_edudetl_image);
       grad=(TextView)findViewById(R.id.d_per_educaion);
        /*pstgtrad=(TextView)findViewById(R.id.d_per_postgrad);
        dctrate=(TextView)findViewById(R.id.d_per_doctrate);
        cetfn=(TextView)findViewById(R.id.d_per_certfcion);*/

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
