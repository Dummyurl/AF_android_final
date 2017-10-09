package com.LeelaGroup.AgrawalFedration.matrimony;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.LeelaGroup.AgrawalFedration.MatrimonySession;
import com.LeelaGroup.AgrawalFedration.MyUtility;
import com.LeelaGroup.AgrawalFedration.Network.ApiClient;
import com.LeelaGroup.AgrawalFedration.R;
import com.LeelaGroup.AgrawalFedration.Service.ServiceMatrimony;
import com.LeelaGroup.AgrawalFedration.matrimony.models.EventsDetails;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventsActivity extends AppCompatActivity {

    ArrayList<EventsDetails> arrayList;
    EventActivityAdapter adapter;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    MatrimonySession matrimonySession;
    Toolbar toolbar;
    LinearLayout layout,checkconn,notfound;
    Button retry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Events");
        layout=(LinearLayout)findViewById(R.id.parent);
        checkconn=(LinearLayout)findViewById(R.id.check_conncection);
        notfound=(LinearLayout)findViewById(R.id.data_not_found);
        retry=(Button) findViewById(R.id.retry);
        matrimonySession=new MatrimonySession(getApplicationContext());

        recyclerView = (RecyclerView) findViewById(R.id.ev_recyclerview);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        if(matrimonySession.checkLogin())
            finish();

        if (MyUtility.isConnected(EventsActivity.this)) {
            getEventdetails();
        } else {
            MyUtility.internetProblemm(recyclerView,checkconn);
        }
        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MyUtility.isConnected(EventsActivity.this)) {
                    checkconn.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    getEventdetails();
                } else {
                    MyUtility.internetProblemm(recyclerView,checkconn);
                }
            }
        });
    }

    private void getEventdetails() {
        ServiceMatrimony serviceMatrimony= ApiClient.getRetrofit().create(ServiceMatrimony.class);
        Call<List<EventsDetails>> evCall=serviceMatrimony.getEventDetails();
        evCall.enqueue(new Callback<List<EventsDetails>>() {
            @Override
            public void onResponse(Call<List<EventsDetails>> call, Response<List<EventsDetails>> response) {
                arrayList= (ArrayList<EventsDetails>) response.body();
                if(arrayList.isEmpty()){
                    MyUtility.dataNotFound(recyclerView,notfound);
                }else {
                    recyclerView.setVisibility(View.VISIBLE);
                    notfound.setVisibility(View.GONE);
                    adapter=new EventActivityAdapter(arrayList,EventsActivity.this);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<EventsDetails>> call, Throwable t) {
                Toast.makeText(EventsActivity.this, getText(R.string.checkConn), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void goToFullViewEvents(View v){
        startActivity(new Intent(EventsActivity.this,EventFullViewActivity.class));
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
