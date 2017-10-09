package com.LeelaGroup.AgrawalFedration.matrimony;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.LeelaGroup.AgrawalFedration.MatrimonySession;
import com.LeelaGroup.AgrawalFedration.MyUtility;
import com.LeelaGroup.AgrawalFedration.Network.ApiClient;
import com.LeelaGroup.AgrawalFedration.R;
import com.LeelaGroup.AgrawalFedration.Service.ServiceMatrimony;
import com.LeelaGroup.AgrawalFedration.matrimony.models.AllDetails;
import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyProfileActivity extends AppCompatActivity {

    CircleImageView profilepic;
    Toolbar toolbar;
    TextView fname, mname, lname, dob, tob, birplc, ntvplc, mrtlsts, gndr, mtrtng, rlgn, cst, sbcst, abtme;
    TextView mobno, landno, email, addr, cntry, city, state, pincode, resdsts, mnglk, hrscp, gothraself, gothramama;
    TextView graduate, ocuuptn, indsry, servEmpAt, pesnlIncome;
    TextView fthrName, mthrName, fthrOccu, mthrOccu, noofbro, noofsis, noofbromar, noofsismar, famsts, famtype, famincome;
    TextView hght, wt, bldgrp, bdytype, complxn, diet, smoke, drink, handicap, profcreateby, hobies, intrest;
    TextView lkngfor, cntrylivin, statelivin, citylivein, minage, maxage, minheight, maxheight, ppcompexn, education, pprlgn, ppcast;

    TextView i_fname, i_mname, i_lname, i_dob, i_tob, i_birplc, i_ntvplc, i_mrtlsts, i_gndr, i_mtrtng, i_rlgn, i_cst, i_sbcst, i_abtme;
    TextView i_mobno, i_landno, i_email, i_addr, i_cntry, i_city, i_state, i_pincode, i_resdsts, i_mnglk, i_hrscp, i_gothraself, i_gothramama;
    TextView i_graduate, i_ocuuptn, i_indsry, i_servEmpAt, i_pesnlIncome;
    TextView i_fthrName, i_mthrName, i_fthrOccu, i_mthrOccu, i_noofbro, i_noofsis, i_noofbromar, i_noofsismar, i_famsts, i_famtype, i_famincome;
    TextView i_hght, i_wt, i_bldgrp, i_bdytype, i_complxn, i_diet, i_smoke, i_drink, i_handicap, i_profcreateby, i_hobies, i_intrest;
    TextView i_lkngfor, i_cntrylivin, i_statelivin, i_citylivein, i_minage, i_maxage, i_minheight, i_maxheight, i_ppcompexn, i_education, i_pprlgn, i_ppcast;


    String mat_id = "", pid = "";

    MatrimonySession matrimonySession;
    LinearLayout layout,checkconn;
Button retry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile_matrimony);

        matrimonySession = new MatrimonySession(getApplicationContext());

        initIcon();
        init();

        layout = (LinearLayout) findViewById(R.id.parent);
        checkconn = (LinearLayout) findViewById(R.id.check_conncection);
        retry = (Button) findViewById(R.id.retry);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("My Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        if (matrimonySession.checkLogin())
            finish();

        pid = getIntent().getStringExtra("pid");
        if (MyUtility.isConnected(MyProfileActivity.this)) {
            fetchAllInfo();
        } else {
            MyUtility.internetProblemm(layout,checkconn);
        }
        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MyUtility.isConnected(MyProfileActivity.this)) {
                    checkconn.setVisibility(View.GONE);
                    layout.setVisibility(View.VISIBLE);
                    fetchAllInfo();
                } else {
                    MyUtility.internetProblemm(layout,checkconn);
                }
            }
        });

    }

    private void fetchAllInfo() {
        ServiceMatrimony serviceMatrimony = ApiClient.getRetrofit().create(ServiceMatrimony.class);
        Call<AllDetails> call = serviceMatrimony.getAllProfileDetails(pid);

        call.enqueue(new Callback<AllDetails>() {
            @Override
            public void onResponse(Call<AllDetails> call, Response<AllDetails> response) {
                AllDetails allDetails = response.body();

                // Basic details
                try {
                    if (allDetails != null) {
                        Glide.with(MyProfileActivity.this).load(allDetails.getMregProfPic()).placeholder(R.drawable.profile_circle).dontAnimate().into(profilepic);

                        fname.setText(allDetails.getMregFname());
                        mname.setText(allDetails.getMregMname());
                        lname.setText(allDetails.getMregLname());
                        dob.setText(allDetails.getMregDob());
                        tob.setText(allDetails.getMregBirthTime());
                        birplc.setText(allDetails.getMregBirthPlace());
                        ntvplc.setText(allDetails.getMregNativePlace());
                        mrtlsts.setText(allDetails.getMregMaritalStatus());
                        gndr.setText(allDetails.getMregGender());
                        mtrtng.setText(allDetails.getMregMotherTongue());
                        rlgn.setText(allDetails.getMatRegReligion());
                        //religion details
                        cst.setText(allDetails.getMatRegCaste());
                        sbcst.setText(allDetails.getMatRegSubcaste());
                        abtme.setText(allDetails.getMregAboutMe());

                        //contact details
                        mobno.setText(allDetails.getMregPhone());
                        landno.setText(allDetails.getMregLandline());
                        email.setText(allDetails.getMregEmail());
                        addr.setText(allDetails.getMregAddr());
                        cntry.setText(allDetails.getMregCountry());
                        city.setText(allDetails.getMregCity());
                        state.setText(allDetails.getMregState());
                        pincode.setText(allDetails.getMregPincode());
                        resdsts.setText(allDetails.getMregResidStatus());

                        //physical details
                        hght.setText(allDetails.getMregPhyHt());
                        wt.setText(allDetails.getMregPhyWt());
                        bldgrp.setText(allDetails.getMregBloodGrp());
                        bdytype.setText(allDetails.getMregBdyType());
                        complxn.setText(allDetails.getMregComplexion());
                        diet.setText(allDetails.getMregDiet());
                        smoke.setText(allDetails.getMregSmoke());
                        drink.setText(allDetails.getMregDrink());
                        handicap.setText(allDetails.getMregHandicapped());

                        //other details
                        profcreateby.setText(allDetails.getMregProfCreateFor());
                        hobies.setText(allDetails.getMregHobby());
                        intrest.setText(allDetails.getMregInterest());

                        //social details
                        mnglk.setText(allDetails.getMatRegManglik());
                        hrscp.setText(allDetails.getMatRegHoroscopeMatch());
                        gothraself.setText(allDetails.getMatRegGothraSelf());
                        //gothramama.setText(allDetails.getMatRegGothraMama());

                        //family details
                        fthrName.setText(allDetails.getMatRegFatherName());
                        mthrName.setText(allDetails.getMatRegMotherName());
                        fthrOccu.setText(allDetails.getMatRegFatherOccup());
                        mthrOccu.setText(allDetails.getMatRegMotherOccup());
                        noofbro.setText(allDetails.getMatRegNoBrother());
                        noofsis.setText(allDetails.getMatRegNoSister());
                        noofbromar.setText(allDetails.getMatMarBro());
                        noofsismar.setText(allDetails.getMatMarSis());
                        famsts.setText(allDetails.getMatRegStatus());
                        famtype.setText(allDetails.getMregFamType());
                        famincome.setText(allDetails.getMregFamStatus());

                        //education details
                        graduate.setText(allDetails.getMregEduc());
//                postgraduate.setText(allDetails.getMatRegPg());
//                docorate.setText(allDetails.getMatRegDocto());
//                crtfication.setText(allDetails.getMatRegCertifi());

                        //occupation details
                        ocuuptn.setText(allDetails.getMatRegOccup());
                        indsry.setText(allDetails.getMatRegIndustry());
                        servEmpAt.setText(allDetails.getMatRegEmpl());
                        pesnlIncome.setText(allDetails.getMatRegIpa());

                        //partner pref details
                        lkngfor.setText(allDetails.getMregLookingFor());
                        cntrylivin.setText(allDetails.getMregCountryLeaving());
                        statelivin.setText(allDetails.getMregStateLeaving());
                        citylivein.setText(allDetails.getMregCityLeaving());
                        minage.setText(allDetails.getMregRegMinage());
                        maxage.setText(allDetails.getMregRegMaxage());
                        minheight.setText(allDetails.getMregMinHt());
                        maxheight.setText(allDetails.getMregMaxHt());
                        ppcompexn.setText(allDetails.getMregGroomComplexion());
                        education.setText(allDetails.getMregEduc());
                        pprlgn.setText(allDetails.getMregReligion());
                        ppcast.setText(allDetails.getMregCaste());
                    }
                } catch (NullPointerException npe) {

                }


            }

            @Override
            public void onFailure(Call<AllDetails> call, Throwable t) {

                Toast.makeText(MyProfileActivity.this, getText(R.string.checkConn), Toast.LENGTH_SHORT).show();

            }
        });


    }

    public void init() {
        profilepic = (CircleImageView) findViewById(R.id.iv_profile);
        fname = (TextView) findViewById(R.id.d_per_fname);
        mname = (TextView) findViewById(R.id.d_per_mname);
        lname = (TextView) findViewById(R.id.d_per_lname);
        dob = (TextView) findViewById(R.id.d_per_dob);
        tob = (TextView) findViewById(R.id.d_per_brthtime);
        birplc = (TextView) findViewById(R.id.d_per_brthplace);
        ntvplc = (TextView) findViewById(R.id.d_per_ntvplace);
        mrtlsts = (TextView) findViewById(R.id.d_per_maritalstatus);
        gndr = (TextView) findViewById(R.id.d_per_gender);
        mtrtng = (TextView) findViewById(R.id.d_per_mothertounge);
        rlgn = (TextView) findViewById(R.id.d_per_ep_religion);
        cst = (TextView) findViewById(R.id.d_per_ep_cast);
        sbcst = (TextView) findViewById(R.id.d_per_subcast);
        abtme = (TextView) findViewById(R.id.d_per_abtme);
        mobno = (TextView) findViewById(R.id.d_per_mobno);
        landno = (TextView) findViewById(R.id.d_per_landline_no);
        email = (TextView) findViewById(R.id.d_per_email);
        addr = (TextView) findViewById(R.id.d_per_address);
        cntry = (TextView) findViewById(R.id.d_per_country);
        city = (TextView) findViewById(R.id.d_per_city);
        state = (TextView) findViewById(R.id.d_per_state);
        pincode = (TextView) findViewById(R.id.d_per_pincode);
        resdsts = (TextView) findViewById(R.id.d_per_residential_sttus);

        mnglk = (TextView) findViewById(R.id.d_per_manglik);
        hrscp = (TextView) findViewById(R.id.d_per_horoscope);
        gothraself = (TextView) findViewById(R.id.d_per_gothraself);
        //gothramama=(TextView)findViewById(R.id.d_per_gothramama);
        graduate = (TextView) findViewById(R.id.d_per_graduatein);
       /* postgraduate=(TextView)findViewById(R.id.d_per_postgrad);
        docorate=(TextView)findViewById(R.id.d_per_doctrate);
        crtfication=(TextView)findViewById(R.id.d_per_certfcion);*/

        ocuuptn = (TextView) findViewById(R.id.d_per_occupation);
        indsry = (TextView) findViewById(R.id.d_per_industry);
        servEmpAt = (TextView) findViewById(R.id.d_per_bsnsempat);
        pesnlIncome = (TextView) findViewById(R.id.d_per_psnalanulincome);

        fthrName = (TextView) findViewById(R.id.d_per_fatherfullname);
        mthrName = (TextView) findViewById(R.id.d_per_motherfullname);
        fthrOccu = (TextView) findViewById(R.id.d_per_ftheroccptn);
        mthrOccu = (TextView) findViewById(R.id.d_per_mthr_occptn);
        noofbro = (TextView) findViewById(R.id.d_per_noofbros);
        noofsis = (TextView) findViewById(R.id.d_per_sister);
        noofbromar = (TextView) findViewById(R.id.d_per_noofbrosmaried);
        noofsismar = (TextView) findViewById(R.id.d_per_noofsismarried);
        famsts = (TextView) findViewById(R.id.d_per_familysatus);
        famtype = (TextView) findViewById(R.id.d_per_familytype);
        famincome = (TextView) findViewById(R.id.d_per_familyanulincome);


        hght = (TextView) findViewById(R.id.d_per_height);
        wt = (TextView) findViewById(R.id.d_per_weight);
        bldgrp = (TextView) findViewById(R.id.d_per_bloodgrp);
        bdytype = (TextView) findViewById(R.id.d_per_bdyype);
        complxn = (TextView) findViewById(R.id.d_per_complexion);
        diet = (TextView) findViewById(R.id.d_per_dirt);
        smoke = (TextView) findViewById(R.id.d_per_smoke);
        drink = (TextView) findViewById(R.id.d_per_drink);
        handicap = (TextView) findViewById(R.id.d_per_handicap);
        profcreateby = (TextView) findViewById(R.id.d_per_profilecreatedby);
        hobies = (TextView) findViewById(R.id.d_per_ep_postgrad);
        intrest = (TextView) findViewById(R.id.d_per_intrests);

        lkngfor = (TextView) findViewById(R.id.d_per_lookngfor);
        cntrylivin = (TextView) findViewById(R.id.d_per_contrylvgin);
        statelivin = (TextView) findViewById(R.id.d_per_statelvgin);
        citylivein = (TextView) findViewById(R.id.d_per_citylvgin);
        minage = (TextView) findViewById(R.id.d_agefrm);
        maxage = (TextView) findViewById(R.id.d_ageto);

        minheight = (TextView) findViewById(R.id.d_per_minhtfrmft);
        maxheight = (TextView) findViewById(R.id.d_per_htfrmft);
        ppcompexn = (TextView) findViewById(R.id.d_per_cmplxn);
        education = (TextView) findViewById(R.id.d_per_educton);
        pprlgn = (TextView) findViewById(R.id.d_per_religion);
        ppcast = (TextView) findViewById(R.id.d_per_cast);
    }

    public void initIcon() {

        Typeface icon = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");

        i_fname = (TextView) findViewById(R.id.per_fname);
        i_mname = (TextView) findViewById(R.id.per_mname);
        i_lname = (TextView) findViewById(R.id.per_lname);
        i_dob = (TextView) findViewById(R.id.per_dob);
        i_tob = (TextView) findViewById(R.id.per_brthtime);
        i_birplc = (TextView) findViewById(R.id.per_brthplace);
        i_ntvplc = (TextView) findViewById(R.id.per_ntvplace);
        i_mrtlsts = (TextView) findViewById(R.id.per_maritalstatus);
        i_gndr = (TextView) findViewById(R.id.per_gender);
        i_mtrtng = (TextView) findViewById(R.id.per_mothertounge);
        i_rlgn = (TextView) findViewById(R.id.per_religion);
        i_cst = (TextView) findViewById(R.id.per_cast);
        i_sbcst = (TextView) findViewById(R.id.per_subcast);
        i_abtme = (TextView) findViewById(R.id.per_abtme);

        i_mobno = (TextView) findViewById(R.id.per_mobile_no);
        i_landno = (TextView) findViewById(R.id.per_landline_no);
        i_email = (TextView) findViewById(R.id.per_email);
        i_addr = (TextView) findViewById(R.id.per_address);
        i_cntry = (TextView) findViewById(R.id.per_country);
        i_city = (TextView) findViewById(R.id.per_city);
        i_state = (TextView) findViewById(R.id.per_state);
        i_pincode = (TextView) findViewById(R.id.per_pincode);
        i_resdsts = (TextView) findViewById(R.id.per_residential_sttus);

        i_mnglk = (TextView) findViewById(R.id.per_maglik);
        i_hrscp = (TextView) findViewById(R.id.per_horoscope);
        i_gothraself = (TextView) findViewById(R.id.per_gothra);
        //gothramama=(TextView)findViewById(R.id.d_per_gothramama);
        i_graduate = (TextView) findViewById(R.id.per_graduatein);
       /* postgraduate=(TextView)findViewById(R.id.d_per_postgrad);
        docorate=(TextView)findViewById(R.id.d_per_doctrate);
        crtfication=(TextView)findViewById(R.id.d_per_certfcion);*/

        i_ocuuptn = (TextView) findViewById(R.id.per_occupatn);
        i_indsry = (TextView) findViewById(R.id.per_industry);
        i_servEmpAt = (TextView) findViewById(R.id.per_bsnsempat);
        i_pesnlIncome = (TextView) findViewById(R.id.per_psnalanulincome);

        i_fthrName = (TextView) findViewById(R.id.per_fatherfullfname);
        i_mthrName = (TextView) findViewById(R.id.per_motherfullname);
        i_fthrOccu = (TextView) findViewById(R.id.per_ftheroccptn);
        i_mthrOccu = (TextView) findViewById(R.id.per_mthe_roccptn);
        i_noofbro = (TextView) findViewById(R.id.per_noofbros);
        i_noofsis = (TextView) findViewById(R.id.per_noofsis);
        i_noofbromar = (TextView) findViewById(R.id.per_noofbrosmaried);
        i_noofsismar = (TextView) findViewById(R.id.per_noofsismaried);
        i_famsts = (TextView) findViewById(R.id.per_familysatus);
        i_famtype = (TextView) findViewById(R.id.per_familytype);
        i_famincome = (TextView) findViewById(R.id.per_fmlyannulincome);


        i_hght = (TextView) findViewById(R.id.per_height);
        i_wt = (TextView) findViewById(R.id.per_weight);
        i_bldgrp = (TextView) findViewById(R.id.per_bloodgrp);
        i_bdytype = (TextView) findViewById(R.id.per_bdytype);
        i_complxn = (TextView) findViewById(R.id.per_complexion);
        i_diet = (TextView) findViewById(R.id.per_diet);
        i_smoke = (TextView) findViewById(R.id.per_smoke);
        i_drink = (TextView) findViewById(R.id.per_drink);
        i_handicap = (TextView) findViewById(R.id.per_handicap);

        i_profcreateby = (TextView) findViewById(R.id.per_profl_crtedby);
        i_hobies = (TextView) findViewById(R.id.per_hobbies);
        i_intrest = (TextView) findViewById(R.id.per_interests);

        i_lkngfor = (TextView) findViewById(R.id.per_profl_lookngfor);
        i_cntrylivin = (TextView) findViewById(R.id.per_countrylvgin);
        i_statelivin = (TextView) findViewById(R.id.per_statlvgin);
        i_citylivein = (TextView) findViewById(R.id.per_citylvgin);
        i_minage = (TextView) findViewById(R.id.per_prtnrprf_age);
        i_maxage = (TextView) findViewById(R.id.per_minage);

        i_minheight = (TextView) findViewById(R.id.tv_minheight);
        i_maxheight = (TextView) findViewById(R.id.tv_height);
        i_ppcompexn = (TextView) findViewById(R.id.tv_per_complxn);
        i_education = (TextView) findViewById(R.id.tv_per_eduction);
        i_pprlgn = (TextView) findViewById(R.id.tv_per_religion);
        i_ppcast = (TextView) findViewById(R.id.tv_per_cast);

        i_fname.setTypeface(icon);
        i_mname.setTypeface(icon);
        i_lname.setTypeface(icon);
        i_dob.setTypeface(icon);
        i_tob.setTypeface(icon);
        i_birplc.setTypeface(icon);
        i_ntvplc.setTypeface(icon);
        i_mrtlsts.setTypeface(icon);
        i_gndr.setTypeface(icon);
        i_mtrtng.setTypeface(icon);
        i_rlgn.setTypeface(icon);
        i_cst.setTypeface(icon);
        i_sbcst.setTypeface(icon);
        i_abtme.setTypeface(icon);

        i_mobno.setTypeface(icon);
        i_landno.setTypeface(icon);
        i_email.setTypeface(icon);
        i_addr.setTypeface(icon);
        i_cntry.setTypeface(icon);
        i_city.setTypeface(icon);
        i_state.setTypeface(icon);
        i_pincode.setTypeface(icon);
        i_resdsts.setTypeface(icon);
        i_mnglk.setTypeface(icon);
        i_hrscp.setTypeface(icon);
        i_gothraself.setTypeface(icon);
        // i_gothramama.setTypeface(icon);

        i_graduate.setTypeface(icon);

        i_ocuuptn.setTypeface(icon);
        i_indsry.setTypeface(icon);
        i_servEmpAt.setTypeface(icon);
        i_pesnlIncome.setTypeface(icon);


        i_fthrName.setTypeface(icon);
        i_mthrName.setTypeface(icon);
        i_fthrOccu.setTypeface(icon);
        i_mthrOccu.setTypeface(icon);
        i_noofbro.setTypeface(icon);
        i_noofsis.setTypeface(icon);
        i_noofbromar.setTypeface(icon);
        i_noofsismar.setTypeface(icon);
        i_famsts.setTypeface(icon);
        i_famtype.setTypeface(icon);
        i_famincome.setTypeface(icon);

        i_hght.setTypeface(icon);
        i_wt.setTypeface(icon);
        i_bldgrp.setTypeface(icon);
        i_bdytype.setTypeface(icon);
        i_complxn.setTypeface(icon);
        i_diet.setTypeface(icon);
        i_smoke.setTypeface(icon);
        i_drink.setTypeface(icon);
        i_handicap.setTypeface(icon);
        i_profcreateby.setTypeface(icon);
        i_hobies.setTypeface(icon);
        i_intrest.setTypeface(icon);

        i_lkngfor.setTypeface(icon);
        i_cntrylivin.setTypeface(icon);
        i_statelivin.setTypeface(icon);
        i_citylivein.setTypeface(icon);
        i_minage.setTypeface(icon);
        i_maxage.setTypeface(icon);
        i_minheight.setTypeface(icon);
        i_maxheight.setTypeface(icon);
        i_ppcompexn.setTypeface(icon);
        i_education.setTypeface(icon);
        i_pprlgn.setTypeface(icon);
        i_ppcast.setTypeface(icon);

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
