package com.LeelaGroup.AgrawalFedration.business;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.LeelaGroup.AgrawalFedration.Business_Pojo.BusinessImage;
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

public class BusinessActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<BusinessImage> arrayList;
    BusinessModuleAdapter adapter;
    private ProgressDialog pDialog;
    Business_Medical_Session business_Medical_session;
    LinearLayout layout, notFound, checkConn;
    Button retry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);

        business_Medical_session = new Business_Medical_Session(getApplicationContext());
        layout = (LinearLayout) findViewById(R.id.activity_business);
        notFound = (LinearLayout) findViewById(R.id.data_not_found);
        checkConn = (LinearLayout) findViewById(R.id.check_conncection);
        retry = (Button) findViewById(R.id.retry);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Business Home");

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);


        /***************************************************************************************************************************/

        if (MyUtility.isConnected(BusinessActivity.this)) {
            getImageData();
        } else {
            MyUtility.internetProblemm(recyclerView, checkConn);
            {

            }
        }
        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MyUtility.isConnected(BusinessActivity.this)) {
                    checkConn.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    getImageData();
                } else {
                    MyUtility.internetProblemm(recyclerView, checkConn);
                    {

                    }
                }
            }
        });

        /**********************************************************************************************************************/

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_item_business, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {

            case R.id.item_apply_franchisee:
                if (MyUtility.isConnected(BusinessActivity.this)) {
                    Intent intent1 = new Intent(BusinessActivity.this, BusinessFranchisee.class);
                    startActivity(intent1);
                } else {
                    MyUtility.internetProblem(layout);
                }

                break;

            case R.id.item_add_addvertized:
                if (MyUtility.isConnected(BusinessActivity.this)) {
                    Intent intent2 = new Intent(BusinessActivity.this, BusinessAddAdvertized.class);
                    startActivity(intent2);
                } else {
                    MyUtility.internetProblem(layout);
                }
                break;
            case R.id.item_my_add:
                if (MyUtility.isConnected(BusinessActivity.this)) {
                    Intent intent3 = new Intent(BusinessActivity.this, Business_My_Add.class);
                    startActivity(intent3);
                } else {
                    MyUtility.internetProblem(layout);
                }
                break;

            case android.R.id.home:

                onBackPressed();

                return true;


            /*case R.id.item_logout:
                business_Medical_session.logoutUser();
                finish();
                break;*/
            default:


        }
        return super.onOptionsItemSelected(item);
    }


    private void getImageData() {
        showpDialog();
        Business_ServiceAPI service = ApiClient.getRetrofit().create(Business_ServiceAPI.class);
        Call<List<BusinessImage>> call = service.getBusinessImage();

        call.enqueue(new Callback<List<BusinessImage>>() {
            @Override
            public void onResponse(Call<List<BusinessImage>> call, Response<List<BusinessImage>> response) {
                hidepDialog();
                try {
                    if (response.isSuccessful()) {
                        arrayList = (ArrayList<BusinessImage>) response.body();
                        if (arrayList.isEmpty()) {
                            MyUtility.dataNotFound(layout, notFound);

                        } else {
                            adapter = new BusinessModuleAdapter(arrayList, BusinessActivity.this);
                            recyclerView.setAdapter(adapter);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<BusinessImage>> call, Throwable t) {
                hidepDialog();
            }
        });


    }

    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }


}
