package com.LeelaGroup.AgrawalFedration.medical;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.LeelaGroup.AgrawalFedration.Medical_Pojos.Medical;
import com.LeelaGroup.AgrawalFedration.MyUtility;
import com.LeelaGroup.AgrawalFedration.Network.ApiClient;
import com.LeelaGroup.AgrawalFedration.R;
import com.LeelaGroup.AgrawalFedration.Service.MedicalServiceAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Medical_Module extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    // Medical_Session medical__session;
    private ViewFlipper simpleViewFlipper;


    Spinner spin;
    AutoCompleteTextView autoCompleteTextView;

    String city, med_type;


    Button search;
    List<Medical> citydata;
    String[] nameList;
    ArrayAdapter<String> dataAdapter1;

    Intent intentcity;

    boolean processClick = true;
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical__module);
       /* medical__session = new Medical_Session(getApplicationContext());

        if (medical__session.checkLogin())
            finish();
*/
      /*  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
*/
        setTitle("Medical Services");

        getCityData();

//       *********************************************SPINNER**********************************************
        //Getting the instance of Spinner and applying OnItemSelectedListener on it

        search = (Button) findViewById(R.id.searchMedical);

        spin = (Spinner) findViewById(R.id.simpleSpinner);
        spin.setOnItemSelectedListener(this);
        layout = (LinearLayout) findViewById(R.id.activity_medical__module);

        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.simpleSpinnerLocation);


        TextView imageview = (TextView) findViewById(R.id.Plus_medical);
        imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MyUtility.isConnected(Medical_Module.this)){
                    Intent intent = new Intent(Medical_Module.this, Medical_Posting.class);
                    startActivity(intent);
                }else {
                    MyUtility.internetProblem(layout);
                }
            }
        });
        TextView take = (TextView) findViewById(R.id.browse_medical);
        take.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MyUtility.isConnected(Medical_Module.this)){

                    Intent intent = new Intent(Medical_Module.this, Demo.class);
                    startActivity(intent);
                }else {
                    MyUtility.internetProblem(layout);
                }
            }
        });


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MyUtility.isConnected(Medical_Module.this)) {
                    city = autoCompleteTextView.getText().toString();
                    med_type = spin.getSelectedItem().toString();

                    if (city.isEmpty()) {
                        autoCompleteTextView.setError("please enter city");
                    } else {
                        intentcity = new Intent(Medical_Module.this, TopSearch.class);
                        intentcity.putExtra("city", city);
                        intentcity.putExtra("type", med_type);
                        startActivity(intentcity);
                    }
                } else {
                    MyUtility.internetProblem(layout);
                }
            }
        });

        autoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (autoCompleteTextView.getText().toString().trim().length() > 0) {
                    autoCompleteTextView.setError(null);

                }
            }
        });

        getSpinnerdata();
    }

    private void getSpinnerdata() {
        ArrayAdapter<CharSequence> spindata = ArrayAdapter.createFromResource(this, R.array.search_type, R.layout.spinner_text);
        spindata.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spin.setAdapter(spindata);
    }


    private void getCityData() {

        final MedicalServiceAPI service = ApiClient.getRetrofit().create(MedicalServiceAPI.class);
        Call<List<Medical>> call = service.getCity();
        call.enqueue(new Callback<List<Medical>>() {
            @Override
            public void onResponse(Call<List<Medical>> call, Response<List<Medical>> response) {


                try {
                    citydata = response.body();
                    nameList = new String[citydata.size()];

                    for (int i = 0; i < citydata.size(); i++) {
                        nameList[i] = citydata.get(i).getCity_name();
                    }
                    dataAdapter1 = new ArrayAdapter<String>(Medical_Module.this, android.R.layout.simple_list_item_1, nameList);
                    autoCompleteTextView.setAdapter(dataAdapter1);
                } catch (Exception e) {

                }


            }

            @Override
            public void onFailure(Call<List<Medical>> call, Throwable t) {
                //Toast.makeText(Medical_Module.this, "fail", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
       /* Toast.makeText(getApplicationContext(), countryNames[position], Toast.LENGTH_LONG).show();*/
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /*@Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        ListViewAdapter adapter = null;
        adapter.filter(text);
        return false;
    }
*/
   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.medical_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int res_id=item.getItemId();
         if (res_id==R.id.action_logout){

             Business_Medical_Session business_medical_session=new Business_Medical_Session(getApplicationContext());
             business_medical_session.logoutUser();
             finish();
        }
        return true;
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
