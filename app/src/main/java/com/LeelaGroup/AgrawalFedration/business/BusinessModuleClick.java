package com.LeelaGroup.AgrawalFedration.business;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.LeelaGroup.AgrawalFedration.Business_Pojo.BusinessCardPojo;
import com.LeelaGroup.AgrawalFedration.Business_Medical_Session;
import com.LeelaGroup.AgrawalFedration.MyUtility;
import com.LeelaGroup.AgrawalFedration.Network.ApiClient;
import com.LeelaGroup.AgrawalFedration.R;
import com.LeelaGroup.AgrawalFedration.Service.Business_ServiceAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BusinessModuleClick extends AppCompatActivity implements SearchView.OnQueryTextListener {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<BusinessCardPojo> arrayList;
    BusinessModuleClickAdapter adapter;
    String cat_id, cat_name;
    private ProgressDialog pDialog;
    AlertDialog alert;

    Business_Medical_Session business_Medical_session;
    LinearLayout layout,checkconn,notfound;
    Button retry;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_module_click);

        business_Medical_session = new Business_Medical_Session(getApplicationContext());

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...!");
        pDialog.setCancelable(false);

        cat_id = getIntent().getStringExtra("cat_id");
        cat_name = getIntent().getStringExtra("category_name");

        layout = (LinearLayout) findViewById(R.id.activity_second);
        checkconn = (LinearLayout) findViewById(R.id.check_conncection);
        notfound = (LinearLayout) findViewById(R.id.data_not_found);
        retry = (Button) findViewById(R.id.retry);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (toolbar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle(cat_name);
        }

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        if (MyUtility.isConnected(BusinessModuleClick.this)) {
            getImageData();
        } else {
            MyUtility.internetProblemm(recyclerView,checkconn);
        }
        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MyUtility.isConnected(BusinessModuleClick.this)) {
                    checkconn.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    getImageData();
                } else {
                    MyUtility.internetProblemm(recyclerView,checkconn);
                }
            }
        });
    }

    private void getImageData() {
        showpDialog();
        Business_ServiceAPI service = ApiClient.getRetrofit().create(Business_ServiceAPI.class);
        Call<List<BusinessCardPojo>> call = service.getCard(cat_id);
        call.enqueue(new Callback<List<BusinessCardPojo>>() {
            @Override
            public void onResponse(Call<List<BusinessCardPojo>> call, Response<List<BusinessCardPojo>> response) {
                hidepDialog();

                try {
                    arrayList = (ArrayList<BusinessCardPojo>) response.body();

                    if (arrayList.isEmpty()) {
                        MyUtility.dataNotFound(recyclerView, notfound);
                        // Toast.makeText(Business_My_Add.this, "No Result Found", Toast.LENGTH_SHORT).show();
                    } else {
                        notfound.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                        adapter = new BusinessModuleClickAdapter(arrayList, BusinessModuleClick.this);
                        recyclerView.setAdapter(adapter);
                        //Toast.makeText(Business_My_Add.this, "success", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<BusinessCardPojo>> call, Throwable t) {
                hidepDialog();
                Toast.makeText(BusinessModuleClick.this, getText(R.string.checkConn), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setQueryHint("Company Name/City/Email/Mob.no");
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        newText = newText.toLowerCase();
        ArrayList<BusinessCardPojo> newList = new ArrayList<>();
        for (BusinessCardPojo businessCardPojo : arrayList) {
            String name = businessCardPojo.getName().toLowerCase();
            String city = businessCardPojo.getCityName().toLowerCase();
            if (name.contains(newText) || city.contains(newText))
                newList.add(businessCardPojo);
        }
        adapter.setFilter(newList);
        return true;
    }

    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
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



