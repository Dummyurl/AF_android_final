package com.LeelaGroup.AgrawalFedration.matrimony;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.LeelaGroup.AgrawalFedration.MatrimonySession;
import com.LeelaGroup.AgrawalFedration.R;
import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;

public class EventFullViewActivity extends AppCompatActivity {

TextView evName,evAddr,evDate,evTime,evLoc,evDesc;
TextView i_evName,i_evAddr,i_evDate,i_evTime,i_evLoc,i_evDesc;
    ImageView evPatrika;
    ImageView evPic;
String name,addr,date,time,loc,desc,pic,patrika;
    MatrimonySession matrimonySession;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_full_view);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Event Details");

        matrimonySession=new MatrimonySession(getApplicationContext());

        name=getIntent().getStringExtra("evname");
        addr=getIntent().getStringExtra("evAddress");
        loc=getIntent().getStringExtra("evLoc");
        date=getIntent().getStringExtra("evDate");
        time=getIntent().getStringExtra("evTime");
        desc=getIntent().getStringExtra("evDesc");
        pic=getIntent().getStringExtra("evpic");
        patrika=getIntent().getStringExtra("ecpatri");

        init();
       // initIcon();

        if(matrimonySession.checkLogin())
            finish();

        setDetails();
    }

    private void setDetails() {
        evName.setText(name);
        evAddr.setText(addr);
        evDate.setText(date);
        evTime.setText(time);
        evLoc.setText(name);
        evDesc.setText(desc);
        Glide.with(EventFullViewActivity.this).load(pic).placeholder(R.drawable.gallery_back).dontAnimate().into(evPic);
        Glide.with(EventFullViewActivity.this).load(patrika).placeholder(R.drawable.gallery_back).dontAnimate().into(evPatrika);
    }

    private void init() {
        evName=(TextView)findViewById(R.id.evt_name);
        evAddr=(TextView)findViewById(R.id.ev_loc);
        evDate=(TextView)findViewById(R.id.ev_date);
        evTime=(TextView)findViewById(R.id.ev_time);
        evLoc=(TextView)findViewById(R.id.ev_addr);
        evDesc=(TextView)findViewById(R.id.ev_desc);
        evPic=(ImageView) findViewById(R.id.ev_iv_evpic);
        evPatrika=(ImageView)findViewById(R.id.ev_iv_patrika);

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

    public void initIcon(){
        Typeface icon = Typeface.createFromAsset(this.getAssets(), "fontawesome-webfont.ttf" );

        i_evLoc = (TextView) findViewById(R.id.address_icon);
        i_evLoc.setTypeface(icon);

        i_evAddr = (TextView) findViewById(R.id.addr_icon);
        i_evAddr.setTypeface(icon);

        i_evDate = (TextView) findViewById(R.id.calandr_icon);
        i_evDate.setTypeface(icon);

        i_evTime = (TextView) findViewById(R.id.clock_icon);
        i_evTime.setTypeface(icon);

    }

}
