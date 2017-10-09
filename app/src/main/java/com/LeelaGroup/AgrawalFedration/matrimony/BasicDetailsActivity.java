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
import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BasicDetailsActivity extends AppCompatActivity {
    MatrimonySession matrimonySession;
    Toolbar toolbar;
    String mat_id;
    CircleImageView profilepic;
    TextView fname, mname, lname, dob, tob, birplc, ntvplc, mrtlsts, noofchild, chdlivsts, gndr, mtrtng, rlgn, cst, sbcst, abtme;
    TextView i_fname, i_mname, i_lname, i_dob, i_tob, i_birplc, i_ntvplc, i_mrtlsts, i_noofchild, i_chdlivsts, i_gndr, i_mtrtng, i_rlgn, i_cst, i_sbcst, i_abtme;
    String mat_sess, mreg_prof_pic, mreg_am, mreg_fname, mreg_mname, mreg_lname, mreg_birth_place, mreg_birth_time, mreg_native_place, mreg_dob, mreg_age, mreg_marital_status, mreg_gender, mreg_no_child, mreg_child_leave_status, mreg_mother_tongue, mreg_about_me;

    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_details);
        matrimonySession = new MatrimonySession(getApplicationContext());
        toolbar = (Toolbar) findViewById(R.id.toolbar_matrimony);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Basic Details");

        mat_id = getIntent().getStringExtra("mat_id");

        initIcon();
        init();
        layout = (LinearLayout) findViewById(R.id.parent);
        if (matrimonySession.checkLogin())
            finish();

        if (MyUtility.isConnected(BasicDetailsActivity.this)) {

            getBasicDetails();
        } else {
            MyUtility.internetProblem(layout);
        }
    }

    public void getBasicDetails() {
        ServiceMatrimony serviceMatrimony = ApiClient.getRetrofit().create(ServiceMatrimony.class);
        Call<BasicDetailAndContactInfo> getBasicCall = serviceMatrimony.getBasicDetailsAndContact(mat_id);
        getBasicCall.enqueue(new Callback<BasicDetailAndContactInfo>() {
            @Override
            public void onResponse(Call<BasicDetailAndContactInfo> call, Response<BasicDetailAndContactInfo> response) {
                BasicDetailAndContactInfo basicDetailAndContactInfo = response.body();
                try {

                    Glide.with(BasicDetailsActivity.this).load(basicDetailAndContactInfo.getMreg_prof_pic()).placeholder(R.drawable.profile_circle).dontAnimate().into(profilepic);
                } catch (Exception e) {

                }
                fname.setText(basicDetailAndContactInfo.getMreg_fname());
                mname.setText(basicDetailAndContactInfo.getMreg_mname());
                lname.setText(basicDetailAndContactInfo.getMreg_lname());
                dob.setText(basicDetailAndContactInfo.getMreg_dob());
                tob.setText(basicDetailAndContactInfo.getMreg_birth_time());
                birplc.setText(basicDetailAndContactInfo.getMreg_birth_place());
                ntvplc.setText(basicDetailAndContactInfo.getMreg_native_place());
                mrtlsts.setText(basicDetailAndContactInfo.getMreg_marital_status());
                noofchild.setText(basicDetailAndContactInfo.getMreg_no_child());
                chdlivsts.setText(basicDetailAndContactInfo.getMreg_child_leave_status());
                gndr.setText(basicDetailAndContactInfo.getMreg_gender());
                mtrtng.setText(basicDetailAndContactInfo.getMreg_mother_tongue());
                rlgn.setText(basicDetailAndContactInfo.getMat_reg_religion());
                cst.setText(basicDetailAndContactInfo.getMat_reg_caste());
                sbcst.setText(basicDetailAndContactInfo.getMat_reg_subcaste());
                abtme.setText(basicDetailAndContactInfo.getMreg_about_me());

            }

            @Override
            public void onFailure(Call<BasicDetailAndContactInfo> call, Throwable t) {

                Toast.makeText(BasicDetailsActivity.this, getText(R.string.checkConn), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void init() {
        profilepic = (CircleImageView) findViewById(R.id.d_person_image);
        fname = (TextView) findViewById(R.id.d_per_fname);
        mname = (TextView) findViewById(R.id.d_per_mname);
        lname = (TextView) findViewById(R.id.d_per_lname);
        dob = (TextView) findViewById(R.id.d_per_dob);
        tob = (TextView) findViewById(R.id.d_per_brthtime);
        birplc = (TextView) findViewById(R.id.d_per_brthplace);
        ntvplc = (TextView) findViewById(R.id.d_per_ntvplace);
        mrtlsts = (TextView) findViewById(R.id.d_per_maritalstatus);
        noofchild = (TextView) findViewById(R.id.frm_d_per_no_of_children);
        chdlivsts = (TextView) findViewById(R.id.frm_d_per_children_living_status);
        gndr = (TextView) findViewById(R.id.d_per_gender);
        mtrtng = (TextView) findViewById(R.id.d_per_mothertounge);
        rlgn = (TextView) findViewById(R.id.d_per_religion);
        cst = (TextView) findViewById(R.id.d_per_cast);
        sbcst = (TextView) findViewById(R.id.d_per_subcast);
        abtme = (TextView) findViewById(R.id.d_per_abtme);


    }

    public void initIcon() {
        Typeface icon = Typeface.createFromAsset(this.getAssets(), "fontawesome-webfont.ttf");

        i_fname = (TextView) findViewById(R.id.per_fname);
        i_fname.setTypeface(icon);

        i_mname = (TextView) findViewById(R.id.per_mname);
        i_mname.setTypeface(icon);

        i_lname = (TextView) findViewById(R.id.per_lname);
        i_lname.setTypeface(icon);

        i_dob = (TextView) findViewById(R.id.per_dob);
        i_dob.setTypeface(icon);

        i_tob = (TextView) findViewById(R.id.per_brthtime);
        i_tob.setTypeface(icon);

        i_birplc = (TextView) findViewById(R.id.per_brthplace);
        i_birplc.setTypeface(icon);

        i_ntvplc = (TextView) findViewById(R.id.per_ntvplace);
        i_ntvplc.setTypeface(icon);

        i_mrtlsts = (TextView) findViewById(R.id.per_maritalstatus);
        i_mrtlsts.setTypeface(icon);

        i_noofchild = (TextView) findViewById(R.id.frm_per_no_of_children);
        i_noofchild.setTypeface(icon);

        i_chdlivsts = (TextView) findViewById(R.id.frm_per_children_living_status);
        i_chdlivsts.setTypeface(icon);

        i_gndr = (TextView) findViewById(R.id.per_gender);
        i_gndr.setTypeface(icon);

        i_mtrtng = (TextView) findViewById(R.id.per_mothertounge);
        i_mtrtng.setTypeface(icon);

        i_rlgn = (TextView) findViewById(R.id.per_religion);
        i_rlgn.setTypeface(icon);

        i_cst = (TextView) findViewById(R.id.per_cast);
        i_cst.setTypeface(icon);

        i_sbcst = (TextView) findViewById(R.id.per_subcast);
        i_sbcst.setTypeface(icon);

        i_abtme = (TextView) findViewById(R.id.per_abtme);
        i_abtme.setTypeface(icon);

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
