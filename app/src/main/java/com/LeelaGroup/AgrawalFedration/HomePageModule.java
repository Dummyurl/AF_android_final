package com.LeelaGroup.AgrawalFedration;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;

import static java.security.AccessController.getContext;

/**
 * Created by USer on 09-09-2017.
 */

public class HomePageModule extends Fragment {
    ModuleCustomAdapter moduleCustomAdapter;
    ArrayList personImages = new ArrayList<>(Arrays.asList(R.drawable.education_af, R.drawable.business , R.drawable.medical_af,
            R.drawable.matrimony));


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.activity_main_modules,container,false);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        // set a GridLayoutManager with default vertical orientation and 2 number of columns
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
        //  call the constructor of ModuleCustomAdapter to send the reference and data to Adapter
        moduleCustomAdapter= new ModuleCustomAdapter(getContext(), personImages);
        recyclerView.setAdapter(moduleCustomAdapter); // set the Adapter to RecyclerView



        return rootView;
    }

 /*   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.medical_menu, menu);
        return true;
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int res_id = item.getItemId();
        switch (res_id){
            case R.id.action_med_logout:

                Business_Medical_Session business_medical_session = new Business_Medical_Session(getContext());
                MatrimonySession matrimonySession=new MatrimonySession(getContext());
                EducationSessionManager educationSessionManager=new EducationSessionManager(getContext());
                business_medical_session.logoutUser();
                matrimonySession.logoutFromMain();
                educationSessionManager.logoutFromMain();
                //  finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}

