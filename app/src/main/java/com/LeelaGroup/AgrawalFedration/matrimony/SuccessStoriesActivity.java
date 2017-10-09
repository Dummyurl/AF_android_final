package com.LeelaGroup.AgrawalFedration.matrimony;

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
import com.LeelaGroup.AgrawalFedration.matrimony.models.SussessStoriesPojo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SuccessStoriesActivity extends AppCompatActivity {

    MatrimonySession matrimonySession;
    ArrayList<SussessStoriesPojo> arrayList;
    SuccessStoriesAdapter adapter;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    Toolbar toolbar;
    LinearLayout layout,checkconn,notfound;
    Button retry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_stories);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Success Stories");
        layout = (LinearLayout) findViewById(R.id.parent);
        checkconn = (LinearLayout) findViewById(R.id.check_conncection);
        notfound = (LinearLayout) findViewById(R.id.data_not_found);
        retry = (Button) findViewById(R.id.retry);

        matrimonySession = new MatrimonySession(getApplicationContext());
        recyclerView = (RecyclerView) findViewById(R.id.ss_recyclerview);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        if (matrimonySession.checkLogin())
            finish();
        if (MyUtility.isConnected(SuccessStoriesActivity.this)) {
            getSuccessDetails();
        } else {
            MyUtility.internetProblemm(recyclerView,checkconn);
        }
        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MyUtility.isConnected(SuccessStoriesActivity.this)) {
                    checkconn.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    getSuccessDetails();
                } else {
                    MyUtility.internetProblemm(recyclerView,checkconn);
                }
            }
        });
    }

    private void getSuccessDetails() {

        ServiceMatrimony serviceMatrimony = ApiClient.getRetrofit().create(ServiceMatrimony.class);
        Call<List<SussessStoriesPojo>> call = serviceMatrimony.getSuccessStories();
        call.enqueue(new Callback<List<SussessStoriesPojo>>() {
            @Override
            public void onResponse(Call<List<SussessStoriesPojo>> call, Response<List<SussessStoriesPojo>> response) {
                arrayList = (ArrayList<SussessStoriesPojo>) response.body();
                if (arrayList.isEmpty()){
                    MyUtility.dataNotFound(recyclerView,notfound);
                }else {
                    notfound.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    adapter = new SuccessStoriesAdapter(arrayList, SuccessStoriesActivity.this);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<SussessStoriesPojo>> call, Throwable t) {
                Toast.makeText(SuccessStoriesActivity.this, getText(R.string.checkConn), Toast.LENGTH_LONG).show();
            }
        });
    }
    /*public void goToFullViewSuccessStories(View v){
        startActivity(new Intent(SuccessStoriesActivity.this,SuccessStoriesFullViewActivity.class));
    }*/

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
