package com.LeelaGroup.AgrawalFedration.business;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.LeelaGroup.AgrawalFedration.R;
import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;

public class BusinessCardClick extends AppCompatActivity {


    TextView B_name,B_category,B_address,B_city,B_mobile,B_email,B_website;
    TextView name,category,Address,city,mobile,email,Website;
    CircleImageView circleImageView;
    Typeface font;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_card_click);

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar_matrimony);
        setSupportActionBar(toolbar);
        if(toolbar!=null) {
            setTitle("AdvertisDetails");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        font = Typeface.createFromAsset( getAssets(), "fontawesome-webfont.ttf" );

        fontAwesome();
        init();

        Glide.with(this).load(getIntent().getStringExtra("B_logo")).into(circleImageView);
        name.setText(getIntent().getStringExtra("B_name"));
        category.setText(getIntent().getStringExtra("B_cat_name"));
        Address.setText(getIntent().getStringExtra("B_address"));
        city.setText(getIntent().getStringExtra("B_city"));
        mobile.setText(getIntent().getStringExtra("B_mobile"));
        email.setText(getIntent().getStringExtra("B_email"));
        Website.setText(getIntent().getStringExtra("B_website"));


    }

    private void fontAwesome() {

       // B_name,B_category,B_address,B_city,B_mobile,B_email,B_website
        B_name=(TextView)findViewById(R.id.B_name);
        B_name.setTypeface(font);

        B_category=(TextView)findViewById(R.id.B_category);
        B_category.setTypeface(font);

        B_address=(TextView)findViewById(R.id.B_address);
        B_address.setTypeface(font);

        B_city=(TextView)findViewById(R.id.B_city);
        B_city.setTypeface(font);

        B_mobile=(TextView)findViewById(R.id.B_mobile);
        B_mobile.setTypeface(font);

        B_email=(TextView)findViewById(R.id.B_email);
        B_email.setTypeface(font);

        B_website=(TextView)findViewById(R.id.B_website);
        B_website.setTypeface(font);
    }

    private void init() {
        //name,category,Address,city,mobile,email,Website
        circleImageView=(CircleImageView)findViewById(R.id.img_card);
        name=(TextView)findViewById(R.id.name);
        category=(TextView)findViewById(R.id.category);
        Address=(TextView)findViewById(R.id.Address);
        city=(TextView)findViewById(R.id.city);
        mobile=(TextView)findViewById(R.id.mobile);
        email=(TextView)findViewById(R.id.email);
        Website=(TextView)findViewById(R.id.Website);

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
