package com.LeelaGroup.AgrawalFedration.matrimony;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.LeelaGroup.AgrawalFedration.MatrimonySession;
import com.LeelaGroup.AgrawalFedration.R;

public class ContactUsActivity extends AppCompatActivity {

    MatrimonySession matrimonySession;
    Toolbar toolbar;
    TextView i_address,i_contact,i_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Forgot Password");

       // initIcon();

        matrimonySession = new MatrimonySession(getApplicationContext());
        if (matrimonySession.checkLogin())
            finish();
    }

    private void initIcon() {
        Typeface icon = Typeface.createFromAsset(this.getAssets(), "fontawesome-webfont.ttf");

        i_address = (TextView) findViewById(R.id.con_addr_icon);
        i_address.setTypeface(icon);

        i_contact = (TextView) findViewById(R.id.con_contact_icon);
        i_contact.setTypeface(icon);

        i_email = (TextView) findViewById(R.id.con_email_icon);
        i_email.setTypeface(icon);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:

                onBackPressed();
                finish();

                return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
