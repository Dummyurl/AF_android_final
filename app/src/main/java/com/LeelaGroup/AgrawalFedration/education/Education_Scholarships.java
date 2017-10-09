package com.LeelaGroup.AgrawalFedration.education;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;


import com.LeelaGroup.AgrawalFedration.R;
import com.LeelaGroup.AgrawalFedration.medical.Doctor_fetch;
import com.LeelaGroup.AgrawalFedration.medical.Hospital_fetch;
import com.LeelaGroup.AgrawalFedration.medical.Medical_Fetch;

@SuppressWarnings("deprecation")
public class Education_Scholarships extends AppCompatActivity {

    ViewPager simpleViewPager;
    TabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education__scholarships);

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar_matrimony);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setTitle("Education Scholarship");



        simpleViewPager = (ViewPager) findViewById(R.id.simpleViewPager);
        tabLayout = (TabLayout) findViewById(R.id.simpleTabLayout);

        TabLayout.Tab firstTab = tabLayout.newTab();
       // firstTab.setText("SEED Scholarship");
        tabLayout.addTab(firstTab);

        TabLayout.Tab secondTab = tabLayout.newTab();
        //secondTab.setText("JLEP");
        tabLayout.addTab(secondTab);

        PagerAdapter_Sc adapter = new PagerAdapter_Sc
                (getSupportFragmentManager(), tabLayout.getTabCount());
        simpleViewPager.setAdapter(adapter);
        simpleViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setupWithViewPager(simpleViewPager);
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
