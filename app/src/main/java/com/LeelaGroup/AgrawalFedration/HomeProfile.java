package com.LeelaGroup.AgrawalFedration;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.LeelaGroup.AgrawalFedration.Business_Pojo.ProfileImageFetchPojo;
import com.LeelaGroup.AgrawalFedration.Network.ApiClient;
import com.LeelaGroup.AgrawalFedration.Service.Business_ServiceAPI;
import com.bumptech.glide.Glide;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by USer on 09-09-2017.
 */

public class HomeProfile extends Fragment {
    TextView name, Email, Mobile;
    TextView useremail, usermobile, ufullname;
    CircleImageView ivProfilePic;
    LinearLayout layout, chekconn;
    Business_Medical_Session businessMedicalSession;
    Button retry;

    String userid = "";


    public HomeProfile() {
        // Required empty public constructor
    }

    public BroadcastReceiver mMessageReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            getData();
        }
    };
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mMessageReceiver, new IntentFilter("image_message"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.my_profile_home, container, false);


        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "font/fontawesome-webfont.ttf");

        layout = (LinearLayout) view.findViewById(R.id.parent);
        chekconn = (LinearLayout) view.findViewById(R.id.check_conncection);
        name = (TextView) view.findViewById(R.id.nameIcon);
        name.setTypeface(font);
        Email = (TextView) view.findViewById(R.id.emailIcon);
        Email.setTypeface(font);
        Mobile = (TextView) view.findViewById(R.id.mobileIcon);
        Mobile.setTypeface(font);
        useremail = (TextView) view.findViewById(R.id.useremail);
        usermobile = (TextView) view.findViewById(R.id.usermobile);
        ufullname = (TextView) view.findViewById(R.id.username);
        ivProfilePic = (CircleImageView) view.findViewById(R.id.iv_profile);
        retry = (Button) view.findViewById(R.id.retry);
        businessMedicalSession = new Business_Medical_Session(getContext());


        HashMap<String, String> user = businessMedicalSession.getUserDetails();
        userid = user.get(businessMedicalSession.KEY_ID);


        if (MyUtility.isConnected(getContext())) {
            getData();
        } else {
            MyUtility.internetProblemm(layout, chekconn);
        }

        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MyUtility.isConnected(getContext())) {
                    layout.setVisibility(View.VISIBLE);
                    chekconn.setVisibility(View.GONE);
                    getData();
                } else {
                    MyUtility.internetProblemm(layout, chekconn);
                }
            }
        });

        return view;
    }

    public void getData() {

        Business_ServiceAPI service = ApiClient.getRetrofit().create(Business_ServiceAPI.class);

        Call<ProfileImageFetchPojo> reg = service.getProfilePic(userid);

        reg.enqueue(new Callback<ProfileImageFetchPojo>() {
            @Override
            public void onResponse(Call<ProfileImageFetchPojo> call, Response<ProfileImageFetchPojo> response) {


                try {
                    ProfileImageFetchPojo pdf = response.body();
                    String fullname = pdf.getUserFname() + " " + pdf.getUserLname();
                    ufullname.setText(fullname);
                    useremail.setText(pdf.getUserEmail());
                    usermobile.setText(pdf.getUserPhone());
                    Glide.with(getContext()).load(pdf.getPicture()).placeholder(R.drawable.profile_circle).dontAnimate().into(ivProfilePic);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ProfileImageFetchPojo> call, Throwable t) {
                //   Toast.makeText(getContext(),getText(R.string.checkConn), Toast.LENGTH_SHORT).show();

            }
        });


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Profile");
    }
}
