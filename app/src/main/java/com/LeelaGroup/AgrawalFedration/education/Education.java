package com.LeelaGroup.AgrawalFedration.education;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.LeelaGroup.AgrawalFedration.R;


public class Education extends AppCompatActivity {

    Button button,LoginToEducation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education);

        button=(Button)findViewById(R.id.button);
        LoginToEducation=(Button)findViewById(R.id.LoginToEducation);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Education.this,Registration.class));
            }
        });

        LoginToEducation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Education.this,login_education.class));
                finish();
            }
        });
    }
//    public void startSecond(View v){startActivity(new Intent(Education.this,Registration.class));}
}
