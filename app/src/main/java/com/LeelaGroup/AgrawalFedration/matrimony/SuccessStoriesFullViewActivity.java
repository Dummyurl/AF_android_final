package com.LeelaGroup.AgrawalFedration.matrimony;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.LeelaGroup.AgrawalFedration.MatrimonySession;
import com.LeelaGroup.AgrawalFedration.R;
import com.bumptech.glide.Glide;

public class SuccessStoriesFullViewActivity extends AppCompatActivity {

    MatrimonySession matrimonySession;
    ImageView bridePic,groomPic,weddingPic;
    TextView brideName,groomName,wedDate,wedLoc,help;
    String bname,gname,wdate,wloc,whelp,wpic,gpic,bpic;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_stories_full_view);
        matrimonySession=new MatrimonySession(getApplicationContext());

        bname=getIntent().getStringExtra("name_bride");
        gname=getIntent().getStringExtra("name_groom");
        wdate=getIntent().getStringExtra("mr_date");
        wloc=getIntent().getStringExtra("mr_loc");
        whelp=getIntent().getStringExtra("help");
        wpic=getIntent().getStringExtra("wed_pic");
        gpic=getIntent().getStringExtra("pic_groom");
        bpic=getIntent().getStringExtra("pic_bride");

        init();

        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Success Stories");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        if(matrimonySession.checkLogin())
            finish();

        setdetails();


    }

    private void setdetails() {
        brideName.setText(bname);
        groomName.setText(gname);
        wedDate.setText(wdate);
        wedLoc.setText(wloc);
        help.setText(whelp);

        Glide.with(SuccessStoriesFullViewActivity.this).load(wpic).placeholder(R.drawable.gallery_back).dontAnimate().into(weddingPic);
        Glide.with(SuccessStoriesFullViewActivity.this).load(gpic).placeholder(R.drawable.gallery_back).dontAnimate().into(groomPic);
        Glide.with(SuccessStoriesFullViewActivity.this).load(bpic).placeholder(R.drawable.gallery_back).dontAnimate().into(bridePic);

    }

    private void init() {
        brideName=(TextView)findViewById(R.id.tv_bride_name);
        groomName=(TextView)findViewById(R.id.groom_name);
        wedDate=(TextView)findViewById(R.id.tv_wed_date);
        wedLoc=(TextView)findViewById(R.id.tv_wed_loc);
        help=(TextView)findViewById(R.id.tv_help);

        bridePic=(ImageView)findViewById(R.id.iv_bride);
        groomPic=(ImageView)findViewById(R.id.iv_groom);
        weddingPic=(ImageView)findViewById(R.id.iv_wed_pic);

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
