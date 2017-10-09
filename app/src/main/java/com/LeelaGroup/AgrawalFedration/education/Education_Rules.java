package com.LeelaGroup.AgrawalFedration.education;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;


import com.LeelaGroup.AgrawalFedration.MyUtility;
import com.LeelaGroup.AgrawalFedration.R;

import java.util.ArrayList;


public class Education_Rules extends AppCompatActivity
{
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    EducationRulesContact educationRulesContact;
    Toolbar toolbar;
    String Exam;
    Button npd;
    LinearLayout layout;

    ArrayList<EducationRulesContact> arrayList=new ArrayList<EducationRulesContact>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education__rules);


        Resources res = getResources();
        String[] rule = res.getStringArray(R.array.rule);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_matrimony);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        layout=(LinearLayout)findViewById(R.id.parent);

        setTitle("Rules");


        Intent intent = getIntent();

        Bundle b = intent.getExtras();

        Exam = b.getString("myname");

        npd = (Button) findViewById(R.id.npd);

        npd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyUtility.hideKeyboard(layout,Education_Rules.this);
                if (MyUtility.isConnected(Education_Rules.this)){
                    Bundle b = new Bundle();
                    b.putString("myname", Exam);
                    Intent intent = new Intent(getApplicationContext(),PersonalDetails.class);
                    intent.putExtras(b);

                    startActivity(intent);
                    Education_Rules.this.finish();
                }else {
                    MyUtility.internetProblem(layout);
                }
            }
        });

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Rules For Entrance Exam");
         recyclerView=(RecyclerView) findViewById(R.id.RulesCard_recycler_view);
         for (int cont=0;cont<rule.length;cont++){
             educationRulesContact=new EducationRulesContact(rule[cont]);
             arrayList.add(educationRulesContact);
         }
          adapter=new RulesAdapter(arrayList,this);
          recyclerView.setHasFixedSize(true);
          layoutManager=new LinearLayoutManager(this);
          recyclerView.setLayoutManager(layoutManager);
          recyclerView.setAdapter(adapter);

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




