package com.LeelaGroup.AgrawalFedration.Social_Refurn;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.LeelaGroup.AgrawalFedration.R;

import java.util.ArrayList;
import java.util.List;

import in.goodiebag.carouselpicker.CarouselPicker;

public class Social_Refurn extends AppCompatActivity {

    CarouselPicker imageCarousel;
    TextView tvSelected,socialText,leela_info;
    private ViewFlipper simpleViewFlipper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social__refurn);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Social News");

          tvSelected = (TextView)findViewById(R.id.leela_info);





        int[] images = {/*R.drawable.logo_leela, R.drawable.rajtilak_logo, R.drawable.interlink_logo, R.drawable.lawyer_logo1,


                R.drawable.tours_logo,  R.drawable.m2_logo,  R.drawable.psac_logo, R.drawable.job_logo*/};     // array of images



            // get The references of ViewFlipper
            simpleViewFlipper = (ViewFlipper) findViewById(R.id.simpleViewFlipper); // get the reference of ViewFlipper

            // loop for creating ImageView's
            for (int i = 0; i < images.length; i++) {
                // create the object of ImageView
                ImageView imageView = new ImageView(this);
                imageView.setImageResource(images[i]); // set image in ImageView
                simpleViewFlipper.addView(imageView); // add the created ImageView in ViewFlipper
            }
            // Declare in and out animations and load them using AnimationUtils class
            Animation in = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
            Animation out = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);
            // set the animation type's to ViewFlipper
            simpleViewFlipper.setInAnimation(in);
            simpleViewFlipper.setOutAnimation(out);
            // set interval time for flipping between views
            simpleViewFlipper.setFlipInterval(3000);
            // set auto start for flipping between views
            simpleViewFlipper.setAutoStart(true);
        }
      /*  imageCarousel = (CarouselPicker) findViewById(R.id.imageCarousel);
     //   tvSelected = (TextView)findViewById(R.id.tvSelectedItem);
      //  socialText = (TextView)findViewById(R.id.social_heading);
        leela_info = (TextView)findViewById(R.id.leela_info);
        List<CarouselPicker.PickerItem> imageItems = new ArrayList<>();
        imageItems.add(new CarouselPicker.DrawableItem(R.drawable.logo_leela));
        imageItems.add(new CarouselPicker.DrawableItem(R.drawable.rajtilak_logo));
        imageItems.add(new CarouselPicker.DrawableItem(R.drawable.interlink_logo));
        imageItems.add(new CarouselPicker.DrawableItem(R.drawable.lawyer_logo1));
        imageItems.add(new CarouselPicker.DrawableItem(R.drawable.tours_logo));
        imageItems.add(new CarouselPicker.DrawableItem(R.drawable.m2_logo));
        imageItems.add(new CarouselPicker.DrawableItem(R.drawable.psac_logo));
        imageItems.add(new CarouselPicker.DrawableItem(R.drawable.job_logo));
        CarouselPicker.CarouselViewAdapter imageAdapter = new CarouselPicker.CarouselViewAdapter(this, imageItems, 0);
        imageCarousel.setAdapter(imageAdapter);



       *//* CarouselPicker.CarouselViewAdapter mixAdapter = new CarouselPicker.CarouselViewAdapter(this, mixItems, 0);
        mixCarousel.setAdapter(mixAdapter);*//*

        imageCarousel.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
               // tvSelected.setText("Selected item in image carousel is  : "+position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:

                onBackPressed();

                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
