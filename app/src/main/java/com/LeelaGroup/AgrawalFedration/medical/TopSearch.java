package com.LeelaGroup.AgrawalFedration.medical;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.LeelaGroup.AgrawalFedration.Business_Medical_Session;
import com.LeelaGroup.AgrawalFedration.Medical_Pojos.Medical;
import com.LeelaGroup.AgrawalFedration.MyUtility;
import com.LeelaGroup.AgrawalFedration.Network.ApiClient;
import com.LeelaGroup.AgrawalFedration.R;
import com.LeelaGroup.AgrawalFedration.Service.MedicalServiceAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TopSearch extends AppCompatActivity {

    String location, medical_type;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    List<Medical> list;
    TopSearchAdapter adapter;
    Business_Medical_Session medical__session;
    AlertDialog alert;
    LinearLayout layout, checkconn, notfound;
    Button retry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_search);


        medical__session = new Business_Medical_Session(getApplicationContext());

        if (medical__session.checkLogin())
            finish();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_matrimony);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setTitle("Top Search");
        location = getIntent().getStringExtra("city");
        medical_type = getIntent().getStringExtra("type");

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_top);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        layout = (LinearLayout) findViewById(R.id.parent);
        checkconn = (LinearLayout) findViewById(R.id.check_conncection);
        notfound = (LinearLayout) findViewById(R.id.data_not_found);
        retry = (Button) findViewById(R.id.retry);

        if (MyUtility.isConnected(TopSearch.this)) {
            getImageData();
        } else {
            MyUtility.internetProblemm(recyclerView, checkconn);
        }

        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MyUtility.isConnected(TopSearch.this)) {
                    checkconn.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    getImageData();
                } else {
                    MyUtility.internetProblemm(recyclerView, checkconn);
                }
            }
        });
    }

    private void getImageData() {
        MedicalServiceAPI service = ApiClient.getRetrofit().create(MedicalServiceAPI.class);
        Call<List<Medical>> call = service.postTopSearch(location, medical_type);
        call.enqueue(new Callback<List<Medical>>() {

            @Override
            public void onResponse(Call<List<Medical>> call, Response<List<Medical>> response) {


                try {
                    list = response.body();
                    if (list.isEmpty()) {
                        MyUtility.dataNotFound(recyclerView, notfound);
                        // Toast.makeText(TopSearch.this, "No result found", Toast.LENGTH_SHORT).show();
                    } else {
                        notfound.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                        adapter = new TopSearchAdapter(list, TopSearch.this);
                        recyclerView.setAdapter(adapter);
                    }
                } catch (NullPointerException e) {

                }

            }

            @Override
            public void onFailure(Call<List<Medical>> call, Throwable t) {
                Toast.makeText(TopSearch.this, getText(R.string.checkConn), Toast.LENGTH_SHORT).show();
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
