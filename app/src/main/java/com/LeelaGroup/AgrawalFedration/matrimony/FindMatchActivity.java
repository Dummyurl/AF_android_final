package com.LeelaGroup.AgrawalFedration.matrimony;

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

import com.LeelaGroup.AgrawalFedration.MatrimonySession;
import com.LeelaGroup.AgrawalFedration.MyUtility;
import com.LeelaGroup.AgrawalFedration.Network.ApiClient;
import com.LeelaGroup.AgrawalFedration.R;
import com.LeelaGroup.AgrawalFedration.Service.ServiceMatrimony;
import com.LeelaGroup.AgrawalFedration.business.BusinessModuleClick;
import com.LeelaGroup.AgrawalFedration.matrimony.models.FetchFilterDetail;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FindMatchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
   Toolbar toolbar;
    RecyclerView recyclerView;
    PersonDetailsAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<FetchFilterDetail> list=new ArrayList<FetchFilterDetail>();
    private ProgressDialog pDialog;
    AlertDialog alert;
    MatrimonySession matrimonySession;
    LinearLayout layout,checkconn,notfound;
    String lookingFor,city,cast;
    Button retry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_match);

        matrimonySession=new MatrimonySession(getApplicationContext());

        toolbar=(Toolbar)findViewById(R.id.toolbar_matrimony);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Your Match");
        layout=(LinearLayout)findViewById(R.id.parent);
        checkconn=(LinearLayout)findViewById(R.id.check_conncection);
        notfound=(LinearLayout)findViewById(R.id.data_not_found);
        retry=(Button) findViewById(R.id.retry);
        lookingFor=getIntent().getStringExtra("lk_for");
        cast=getIntent().getStringExtra("f_cast");
        city=getIntent().getStringExtra("f_city");

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...!");
        pDialog.setCancelable(false);

        recyclerView = (RecyclerView) findViewById(R.id.fm_recycleview);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        if(matrimonySession.checkLogin())
            finish();

        if (MyUtility.isConnected(FindMatchActivity.this)) {
            getPersonCard();
        } else {
            MyUtility.internetProblemm(recyclerView,checkconn);
        }

        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MyUtility.isConnected(FindMatchActivity.this)) {
                    checkconn.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    getPersonCard();
                } else {
                    MyUtility.internetProblemm(recyclerView,checkconn);
                }
            }
        });
    }

    private void getPersonCard() {
      showpDialog();
        ServiceMatrimony serviceMatrimony= ApiClient.getRetrofit().create(ServiceMatrimony.class);
        Call<List<FetchFilterDetail>> call=serviceMatrimony.getPerCard(lookingFor,cast,city);
        call.enqueue(new Callback<List<FetchFilterDetail>>() {
            @Override
            public void onResponse(Call<List<FetchFilterDetail>> call, Response<List<FetchFilterDetail>> response) {
               hidepDialog();
                try {
                    list=(ArrayList<FetchFilterDetail>)response.body();
                    if (list.isEmpty())
                    {
                        MyUtility.dataNotFound(recyclerView,notfound);
                        //Toast.makeText(FindMatchActivity.this, "No Result Found", Toast.LENGTH_SHORT).show();
                    }else {
                        notfound.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                        adapter=new PersonDetailsAdapter(list,FindMatchActivity.this);
                        recyclerView.setAdapter(adapter);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<FetchFilterDetail>> call, Throwable t) {
                hidepDialog();
                Toast.makeText(FindMatchActivity.this,getText(R.string.checkConn), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setQueryHint("Id/Name/Qualification/Age");
        searchView.setOnQueryTextListener(this);
        return true;
    }
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        try {
            newText=newText.toLowerCase();
            ArrayList<FetchFilterDetail> filterList=new ArrayList<>();
            for (FetchFilterDetail filterDetail:list)
            {
                String name=filterDetail.getName().toLowerCase();
                String id=filterDetail.getId().toLowerCase();
                String age=filterDetail.getAge().toLowerCase();
                String edu=filterDetail.getEducation().toLowerCase();

                if(name.contains(newText)||id.contains(newText)||age.contains(newText)||edu.contains(newText))
                    filterList.add(filterDetail);
            }
            adapter.setFilter(filterList);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

        switch (item.getItemId()){

            case android.R.id.home:

                onBackPressed();
                finish();

                return  true;
        }

        return super.onOptionsItemSelected(item);
    }

}
