package com.LeelaGroup.AgrawalFedration.education;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.LeelaGroup.AgrawalFedration.EducationSessionManager;
import com.LeelaGroup.AgrawalFedration.Education_Pojos.Article;
import com.LeelaGroup.AgrawalFedration.Education_Pojos.EducationNewsPojo;
import com.LeelaGroup.AgrawalFedration.MyUtility;
import com.LeelaGroup.AgrawalFedration.R;
import com.LeelaGroup.AgrawalFedration.Service.ServiceAPIEducation;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EducationNews extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    EducationNewsAdapter educationNewsAdapter;
    List<Article> articles;
    EducationSessionManager educationSessionManager;
    String email,sess;
    ProgressDialog progressDialog;
    LinearLayout checkconn;
    Button retry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edu_news);

        toolbar = (Toolbar) findViewById(R.id.toolbar_matrimony);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("News");

        educationSessionManager = new EducationSessionManager(getApplicationContext());

        retry=(Button)findViewById(R.id.retry);
        checkconn=(LinearLayout)findViewById(R.id.check_conncection);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading News");
        HashMap<String, String> user = educationSessionManager.getUserDetails();
        email=user.get(EducationSessionManager.KEY_EMAIL);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);


        if (MyUtility.isConnected(EducationNews.this)){
            gatMyAddCard();
        }else{
            MyUtility.internetProblemm(recyclerView,checkconn);
        }
        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MyUtility.isConnected(EducationNews.this)){
                    checkconn.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    gatMyAddCard();
                }else{
                    MyUtility.internetProblemm(recyclerView,checkconn);
                }
            }
        });

    }

    private void gatMyAddCard() {

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://newsapi.org/v1/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();

        ServiceAPIEducation  service = retrofit.create(ServiceAPIEducation.class);
        Call<EducationNewsPojo> educationNewsPojoCall=service.getNews();

        progressDialog.show();;
        educationNewsPojoCall.enqueue(new Callback<EducationNewsPojo>() {
            @Override
            public void onResponse(Call<EducationNewsPojo> call, Response<EducationNewsPojo> response) {
                progressDialog.dismiss();
                if(response.isSuccessful()){

                    EducationNewsPojo educationNewsPojo=response.body();
                    articles=new ArrayList<Article>();
                    articles=educationNewsPojo.getArticles();
                    educationNewsAdapter=new EducationNewsAdapter((ArrayList<Article>) articles,EducationNews.this);
                    recyclerView.setAdapter(educationNewsAdapter);
                }
            }

            @Override
            public void onFailure(Call<EducationNewsPojo> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(EducationNews.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });


    }

 /*   @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:

                onBackPressed();

                return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu_education,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int selected_id = item.getItemId();

        if (selected_id == R.id.action_marksheet)
        {
            Bundle b = new Bundle();
            b.putString("myname", email);

            Intent intent = new Intent(getApplicationContext(), Education_Upload_Doc.class);
            intent.putExtras(b);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
//            startActivity(new Intent(this, Education_Upload_Doc.class));
        }else if (selected_id == R.id.action_View_Form)
        {
            Bundle b = new Bundle();
            b.putString("myname", email);

            Intent intent = new Intent(getApplicationContext(), FeatchDetails.class);
            intent.putExtras(b);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            //startActivity(new Intent(this, FeatchDetails.class));
        }else if (selected_id == R.id.education_logout)
        {
            educationSessionManager.logoutUser();
            finish();

            /*Intent intent = new Intent(Profile.this,login_education.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);*/

            EducationNews.this.finish();
        }else if (selected_id == R.id.action_update_marksheet)
        {
            Bundle b = new Bundle();
            b.putString("myname", email);

            Intent intent = new Intent(getApplicationContext(), Document.class);
            intent.putExtras(b);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }else if (selected_id== R.id.scholarships)
        {
            Bundle b = new Bundle();
            b.putString("myname", email);

            Intent intent = new Intent(getApplicationContext(), Education_Scholarships.class);
            intent.putExtras(b);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }else if(selected_id==android.R.id.home){
            onBackPressed();
            finish();
        }


        return true;
    }
}
