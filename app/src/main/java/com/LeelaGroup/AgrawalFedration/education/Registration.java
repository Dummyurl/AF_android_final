package com.LeelaGroup.AgrawalFedration.education;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.LeelaGroup.AgrawalFedration.R;


public class Registration extends AppCompatActivity {

    Button button2,button4;
    String CET = "CET";
    String BEAT = "BEAT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_matrimony);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setTitle("Registration");

        button2= (Button)findViewById(R.id.button2);
        button4= (Button)findViewById(R.id.button4);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle b = new Bundle();
                b.putString("myname", CET);

                Intent intent = new Intent(getApplicationContext(),Education_Rules.class);
                 intent.putExtras(b);
                startActivity(intent);
                finish();
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle b = new Bundle();
                b.putString("myname", BEAT);

                Intent intent = new Intent(getApplicationContext(),Education_Rules.class);
                intent.putExtras(b);
                startActivity(intent);
                finish();
            }
        });
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
