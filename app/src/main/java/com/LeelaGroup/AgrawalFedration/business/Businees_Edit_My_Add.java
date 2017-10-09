package com.LeelaGroup.AgrawalFedration.business;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.LeelaGroup.AgrawalFedration.Business_Medical_Session;
import com.LeelaGroup.AgrawalFedration.R;

public class Businees_Edit_My_Add extends AppCompatActivity {

Business_Medical_Session business_Medical_session;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_businees_edit_my_add);

        business_Medical_session =new Business_Medical_Session(getApplicationContext());



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(business_Medical_session.checkLogin())
            finish();

    }


}
