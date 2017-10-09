package com.LeelaGroup.AgrawalFedration.education;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.LeelaGroup.AgrawalFedration.R;

public class Education_Loan_Program extends Fragment {
    TextView SeedLoan,EducationLoan;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.activity_education__loan__program, container, false);
    }
}
