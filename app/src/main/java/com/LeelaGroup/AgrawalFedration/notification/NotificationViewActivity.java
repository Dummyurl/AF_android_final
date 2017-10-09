package com.LeelaGroup.AgrawalFedration.notification;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.LeelaGroup.AgrawalFedration.R;

public class NotificationViewActivity extends AppCompatActivity {
     TextView title,message,time;
     ImageView imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_view);

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar_matrimony);
        if(toolbar!=null){
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("User Notification");
        }

        imageview=(ImageView) findViewById(R.id.icon);
        title=(TextView)findViewById(R.id.title);
        message=(TextView)findViewById(R.id.message);
        time=(TextView)findViewById(R.id.time);


        title.setText(getIntent().getStringExtra("title"));
        message.setText(getIntent().getStringExtra("message"));
        time.setText(getIntent().getStringExtra("time"));
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
