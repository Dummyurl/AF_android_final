package com.LeelaGroup.AgrawalFedration.medical;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.LeelaGroup.AgrawalFedration.Business_Medical_Session;
import com.LeelaGroup.AgrawalFedration.MatrimonySession;
import com.LeelaGroup.AgrawalFedration.Medical_Pojos.Medical;
import com.LeelaGroup.AgrawalFedration.MyUtility;
import com.LeelaGroup.AgrawalFedration.Network.ApiClient;
import com.LeelaGroup.AgrawalFedration.R;
import com.LeelaGroup.AgrawalFedration.Service.MedicalServiceAPI;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Hospital_fetch extends Fragment implements View.OnClickListener {


     RecyclerView recyclerView;
     RecyclerView.LayoutManager layoutManager;
     List<Medical> list;
    MedicalAdapter adapter;
    View rootView;
    Business_Medical_Session business_medical_session;
    String user_id;
    Button retry;
    LinearLayout notfound, checkconn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        business_medical_session=new Business_Medical_Session(getContext());
        HashMap<String, String> user = business_medical_session.getUserDetails();
        user_id = user.get(MatrimonySession.KEY_ID);

        // Initialize dataset, this data would usually come from a local content provider or
        // remote server.

    }

    private void getImageData() {
        MedicalServiceAPI service= ApiClient.getRetrofit().create(MedicalServiceAPI.class);
        Call<List<Medical>> call=service.getImageHospital(user_id);
        call.enqueue(new Callback<List<Medical>>() {

            @Override
            public void onResponse(Call<List<Medical>> call, Response<List<Medical>> response) {
                try{
                list=response.body();
                    if (list.isEmpty()) {
                        MyUtility.dataNotFound(recyclerView, notfound);
                    } else {
                        notfound.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                        adapter = new MedicalAdapter(list, getActivity());
                        recyclerView.setAdapter(adapter);
                    }
                }catch (NullPointerException e){

                }
                // Toast.makeText(Hospital_fetch.this, "success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Medical>> call, Throwable t) {
                Toast.makeText(getActivity(),getText(R.string.checkConn), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_hospital_fetch, container, false);

        recyclerView=(RecyclerView) rootView.findViewById(R.id.recycler_medical);
        notfound = (LinearLayout) rootView.findViewById(R.id.data_not_found);
        checkconn = (LinearLayout) rootView.findViewById(R.id.check_conncection);
        retry = (Button) rootView.findViewById(R.id.retry);
        layoutManager =new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        retry.setOnClickListener(this);
        if (MyUtility.isConnected(getContext())) {
            getImageData();
        } else {
            MyUtility.internetProblemm(recyclerView, checkconn);
        }

        return rootView;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.retry:
                if (MyUtility.isConnected(getContext())) {
                    checkconn.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    getImageData();
                } else {
                    MyUtility.internetProblemm(recyclerView, checkconn);
                }
                break;
            default:
                break;
        }
    }
}
