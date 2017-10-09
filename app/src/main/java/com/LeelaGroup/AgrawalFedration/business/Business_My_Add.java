package com.LeelaGroup.AgrawalFedration.business;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.LeelaGroup.AgrawalFedration.Business_Pojo.My_Add_Card_Pojo;
import com.LeelaGroup.AgrawalFedration.Business_Medical_Session;
import com.LeelaGroup.AgrawalFedration.MyUtility;
import com.LeelaGroup.AgrawalFedration.Network.ApiClient;
import com.LeelaGroup.AgrawalFedration.R;
import com.LeelaGroup.AgrawalFedration.Service.Business_ServiceAPI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Business_My_Add extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<My_Add_Card_Pojo> arrayList;
    Business_My_Add_Adapter adapter;
    private ProgressDialog pDialog;
    Business_Medical_Session business_Medical_session;
    String arid;
    Button retry;
    LinearLayout layout,notfound,checkconn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bisiness_my_add);
        business_Medical_session = new Business_Medical_Session(getApplicationContext());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        retry=(Button)findViewById(R.id.retry);
        layout = (LinearLayout) findViewById(R.id.parent);
        notfound = (LinearLayout) findViewById(R.id.data_not_found);
        checkconn = (LinearLayout) findViewById(R.id.check_conncection);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Your Advertisement");
        if (business_Medical_session.checkLogin())
            finish();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        if (MyUtility.isConnected(Business_My_Add.this)) {
            gatMyAddCard();
        } else {
            MyUtility.internetProblemm(recyclerView,checkconn);
        }
        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MyUtility.isConnected(Business_My_Add.this)) {
                    checkconn.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    gatMyAddCard();
                } else {
                    MyUtility.internetProblemm(recyclerView,checkconn);
                }
            }
        });

    }


    private void gatMyAddCard() {

        HashMap<String, String> user = business_Medical_session.getUserDetails();
        String id = user.get(Business_Medical_Session.KEY_ID);
        Log.d("message", id);

        Business_ServiceAPI service = ApiClient.getRetrofit().create(Business_ServiceAPI.class);
        Call<List<My_Add_Card_Pojo>> call = service.getMyAdd(id);

        call.enqueue(new Callback<List<My_Add_Card_Pojo>>() {
            @Override
            public void onResponse(Call<List<My_Add_Card_Pojo>> call, Response<List<My_Add_Card_Pojo>> response) {
                if (response.isSuccessful()) {
                    arrayList = (ArrayList<My_Add_Card_Pojo>) response.body();
                    if (arrayList.isEmpty()) {
                        MyUtility.dataNotFound(recyclerView,notfound);
                       // Toast.makeText(Business_My_Add.this, "No Result Found", Toast.LENGTH_SHORT).show();
                    } else {
                        notfound.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                        adapter = new Business_My_Add_Adapter(arrayList, Business_My_Add.this);
                        recyclerView.setAdapter(adapter);
                        //Toast.makeText(Business_My_Add.this, "success", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<My_Add_Card_Pojo>> call, Throwable t) {
                Toast.makeText(Business_My_Add.this, getText(R.string.checkConn), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:

                onBackPressed();

                return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
