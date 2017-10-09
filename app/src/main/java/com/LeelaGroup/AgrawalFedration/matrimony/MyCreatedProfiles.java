package com.LeelaGroup.AgrawalFedration.matrimony;

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

import com.LeelaGroup.AgrawalFedration.MatrimonySession;
import com.LeelaGroup.AgrawalFedration.MyUtility;
import com.LeelaGroup.AgrawalFedration.Network.ApiClient;
import com.LeelaGroup.AgrawalFedration.R;
import com.LeelaGroup.AgrawalFedration.Service.ServiceMatrimony;
import com.LeelaGroup.AgrawalFedration.matrimony.models.ProfileModel;
import com.LeelaGroup.AgrawalFedration.matrimony.models.SussessStoriesPojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyCreatedProfiles extends AppCompatActivity {

    MatrimonySession matrimonySession;
    ArrayList<ProfileModel> arrayList;
    MyCreatedProfilesAdapter adapter;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    Toolbar toolbar;
    String mat_id="";
    LinearLayout layout,checkconn,notfound;
    Button retry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_created_profiles);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("My Profiles");

        layout=(LinearLayout)findViewById(R.id.parent);
        checkconn=(LinearLayout)findViewById(R.id.check_conncection);
        notfound=(LinearLayout)findViewById(R.id.data_not_found);
        retry=(Button)findViewById(R.id.retry);
        matrimonySession=new MatrimonySession(getApplicationContext());
        recyclerView=(RecyclerView)findViewById(R.id.mcp_recyclerview);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
       /* if(matrimonySession.checkLogin())
            finish();
*/
        HashMap<String, String> user = matrimonySession.getUserDetails();
        mat_id=user.get(MatrimonySession.KEY_ID);

        if (MyUtility.isConnected(MyCreatedProfiles.this)){
            getProfiles();
        }else {
            MyUtility.internetProblemm(recyclerView,checkconn);
        }
        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MyUtility.isConnected(MyCreatedProfiles.this)){
                    checkconn.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    getProfiles();
                }else {
                    MyUtility.internetProblemm(recyclerView,checkconn);
                }
            }
        });
    }

    private void getProfiles() {

        ServiceMatrimony serviceMatrimony= ApiClient.getRetrofit().create(ServiceMatrimony.class);
        Call<List<ProfileModel>> listCall=serviceMatrimony.getProfileList(mat_id);
        listCall.enqueue(new Callback<List<ProfileModel>>() {
            @Override
            public void onResponse(Call<List<ProfileModel>> call, Response<List<ProfileModel>> response) {

                arrayList= (ArrayList<ProfileModel>) response.body();
                if (arrayList.isEmpty()){
                    MyUtility.dataNotFound(recyclerView,notfound);
                }else {
                    notfound.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    adapter=new MyCreatedProfilesAdapter(arrayList,MyCreatedProfiles.this);
                    recyclerView.setAdapter(adapter);
                }
                //Toast.makeText(MyCreatedProfiles.this, "success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<ProfileModel>> call, Throwable t) {

                Toast.makeText(MyCreatedProfiles.this, getText(R.string.checkConn), Toast.LENGTH_SHORT).show();
            }
        });
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
