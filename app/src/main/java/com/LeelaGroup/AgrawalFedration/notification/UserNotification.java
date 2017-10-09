package com.LeelaGroup.AgrawalFedration.notification;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.LeelaGroup.AgrawalFedration.Business_Medical_Session;
import com.LeelaGroup.AgrawalFedration.MyUtility;
import com.LeelaGroup.AgrawalFedration.Network.ApiClient;
import com.LeelaGroup.AgrawalFedration.R;
import com.LeelaGroup.AgrawalFedration.Service.Business_ServiceAPI;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by USer on 09-09-2017.
 */

public class UserNotification extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    Business_Medical_Session business_medical_session;
    String id;
    NotificationAdapter notificationAdapter;
    ArrayList<NotifyPojo> arraylist;
    LinearLayout checkconn, notFound;
    Button retry;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_matrimony);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("User Notification");
        }

        business_medical_session = new Business_Medical_Session(getApplicationContext());
        HashMap<String, String> user = business_medical_session.getUserDetails();
        id = user.get(Business_Medical_Session.KEY_ID);

        retry=(Button)findViewById(R.id.retry);
        checkconn = (LinearLayout) findViewById(R.id.check_conncection);
        notFound = (LinearLayout) findViewById(R.id.data_not_found);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        if (MyUtility.isConnected(UserNotification.this)) {
            getNotification();
        } else {
            MyUtility.internetProblemm(recyclerView, checkconn);
        }
        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MyUtility.isConnected(UserNotification.this)) {
                    getNotification();
                    checkconn.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                } else {
                    MyUtility.internetProblemm(recyclerView, checkconn);
                }
            }
        });
    }

    private void getNotification() {

        Business_ServiceAPI business_serviceAPI = ApiClient.getRetrofit().create(Business_ServiceAPI.class);
        Call<ArrayList<NotifyPojo>> listCall = business_serviceAPI.getNotification(id);
        listCall.enqueue(new Callback<ArrayList<NotifyPojo>>() {
            @Override
            public void onResponse(Call<ArrayList<NotifyPojo>> call, Response<ArrayList<NotifyPojo>> response) {
                arraylist = response.body();
                if (arraylist.isEmpty()) {
                    MyUtility.dataNotFound(recyclerView, notFound);
                } else {
                    notificationAdapter = new NotificationAdapter(arraylist, getApplicationContext());
                    recyclerView.setAdapter(notificationAdapter);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<NotifyPojo>> call, Throwable t) {
                Toast.makeText(UserNotification.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
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
