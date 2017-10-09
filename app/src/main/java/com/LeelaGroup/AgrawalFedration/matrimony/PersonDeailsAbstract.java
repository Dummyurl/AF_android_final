package com.LeelaGroup.AgrawalFedration.matrimony;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.LeelaGroup.AgrawalFedration.MatrimonySession;
import com.LeelaGroup.AgrawalFedration.R;

import java.util.ArrayList;
import java.util.Arrays;

public class PersonDeailsAbstract extends AppCompatActivity {

    String mat_id;
    //ArrayList personNames = new ArrayList<>(Arrays.asList("Person 1", "Person 2", "Person 3", "Person 4", "Person 5", "Person 6", "Person 7","Person 8", "Person 9", "Person 10"));
    ArrayList personImages = new ArrayList<>(Arrays.asList(R.drawable.img_basic_details, R.drawable.img_contact_deatails, R.drawable.img_social_attributee, R.drawable.img_education_details, R.drawable.img_occuption_deatils, R.drawable.img_family_details, R.drawable.img_phycical_attribt, R.drawable.img_other_details, R.drawable.img_partner_prefernce));
    MatrimonySession matrimonySession;
    Toolbar toolbar;
    LinearLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_deails_abstract);
        matrimonySession =new MatrimonySession(getApplicationContext());

        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("All Information");

        mat_id = getIntent().getStringExtra("mat_id");

        if(matrimonySession.checkLogin())
            finish();

        // get the reference of RecyclerView
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.matrirecyclerView);
         layout = (LinearLayout) findViewById(R.id.parent);
        // set a GridLayoutManager with default vertical orientation and 2 number of columns
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
        //  call the constructor of MatriCustomAdapter to send the reference and data to Adapter
        MatriCustomAdapter matriCustomAdapter = new MatriCustomAdapter(PersonDeailsAbstract.this, personImages, mat_id);
        recyclerView.setAdapter(matriCustomAdapter); // set the Adapter to RecyclerView
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
