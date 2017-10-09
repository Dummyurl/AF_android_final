package com.LeelaGroup.AgrawalFedration;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.design.internal.NavigationMenuItemView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.LeelaGroup.AgrawalFedration.Business_Pojo.ProfileImageFetchPojo;
import com.LeelaGroup.AgrawalFedration.Business_Pojo.ProfileImagePojo;
import com.LeelaGroup.AgrawalFedration.Network.ApiClient;
import com.LeelaGroup.AgrawalFedration.Service.Business_ServiceAPI;
import com.LeelaGroup.AgrawalFedration.notification.UserNotification;
import com.bumptech.glide.Glide;
import com.google.firebase.iid.FirebaseInstanceId;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static junit.framework.Assert.assertEquals;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    String token;
    Business_Medical_Session business_medical_session;
    CircleImageView profilePic;
    TextView username, useremail;
    public static final int PROFILE_PICTURE = 1;

    Bitmap bitmap;

    String profilePath, uid, uname, uemail;
    File imageFile;
    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 23;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_drawer);
        // setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        business_medical_session = new Business_Medical_Session(getApplicationContext());

        if (business_medical_session.checkLogin())
            finish();


        token = FirebaseInstanceId.getInstance().getToken();
        // Toast.makeText(Home.this, token, Toast.LENGTH_SHORT).show();
        storeId();

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
       /* profilePic=(CircleImageView)findViewById(R.id.profilePic);
        username=(TextView)*/
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View view = navigationView.inflateHeaderView(R.layout.nav_header_main_drawer);
        profilePic = (CircleImageView) view.findViewById(R.id.profilePic);
        profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MyUtility.isConnected(Home.this)) {

                    Intent intent = new Intent();
                    // Show only images, no videos or anything else
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    // Always show the chooser (if there are multiple options available)
                    //startActivityForResult(Intent.createChooser(intent, "selectProfile"), PROFILE_PICTURE);
                    startActivityForResult(intent, PROFILE_PICTURE);
                } else {
                    MyUtility.internetProblem(drawer);
                }
            }
        });

        username = (TextView) view.findViewById(R.id.user_name);
        useremail = (TextView) view.findViewById(R.id.user_email);

        // NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //add this line to display menu1 when the activity is loaded
        displaySelectedScreen(R.id.nav_home);

        HashMap<String, String> user = business_medical_session.getUserDetails();

        //String name = user.get(Business_Medical_Session.KEY_NAME);
        uid = user.get(Business_Medical_Session.KEY_ID);
        uemail = user.get(Business_Medical_Session.KEY_Email);
        uname = user.get(Business_Medical_Session.KEY_Name);

        username.setText(uname);
        useremail.setText(uemail);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);

        }
        if (MyUtility.isConnected(Home.this)) {
            fetchProfileImage();
        } else {
            MyUtility.internetProblem(drawer);
        }
    }

    private void storeId() {
        if (business_medical_session.isIdStore()) {
            HashMap<String, String> user = business_medical_session.getUserDetails();
            String id = user.get(Business_Medical_Session.KEY_ID);

            Business_ServiceAPI business_serviceAPI = ApiClient.getRetrofit().create(Business_ServiceAPI.class);
            Call<NotificationPojo> notificationPojoCall = business_serviceAPI.updateToken(id, token);
            notificationPojoCall.enqueue(new Callback<NotificationPojo>() {
                @Override
                public void onResponse(Call<NotificationPojo> call, Response<NotificationPojo> response) {
                    NotificationPojo notificationPojo = response.body();
                    // Toast.makeText(Home.this, notificationPojo.getResponse(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<NotificationPojo> call, Throwable t) {

                }
            });

        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        int backstack = getSupportFragmentManager().getBackStackEntryCount();
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (backstack > 0) {

            for (int i = 0; i < backstack; i++) {

                getSupportFragmentManager().popBackStackImmediate();
                //getSupportActionBar().setTitle("SalaryIn");
            }
            setTitle("Home");

        } else {

            this.finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //  getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void displaySelectedScreen(int itemId) {

        //creating fragment object
        Fragment fragment = null;

        //initializing the fragment object which is selected
        switch (itemId) {
            case R.id.nav_home:
                HomePageModule homePageModule = new HomePageModule();
                FragmentManager manager = getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.relative_layout_for_fragment, homePageModule, homePageModule.getTag()).commit();
                setTitle("Home");
                break;

            case R.id.nav_profile:
                HomeProfile homeProfile = new HomeProfile();
                FragmentManager manager1 = getSupportFragmentManager();
                manager1.beginTransaction().replace(R.id.relative_layout_for_fragment, homeProfile, homeProfile.getTag()).addToBackStack(null).commit();
                setTitle("Profile");
                break;
            case R.id.nav_notif:

               /* UserNotification userNotification = new UserNotification();
                FragmentManager manager3 = getSupportFragmentManager();
                manager3.beginTransaction().replace(R.id.relative_layout_for_fragment, userNotification, userNotification.getTag()).commit();*/
                Intent intent = new Intent(Home.this, UserNotification.class);
                startActivity(intent);
                break;
            case R.id.nav_about:

                AboutUs aboutUs = new AboutUs();
                FragmentManager managerAboutUs = getSupportFragmentManager();
                managerAboutUs.beginTransaction().replace(R.id.relative_layout_for_fragment, aboutUs).addToBackStack(null).commit();
                setTitle("About Us");


                break;

            case R.id.nav_logout:
                business_medical_session.logoutUser();
                finish();
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        //calling the method displayselectedscreen and passing the id of selected menu
        displaySelectedScreen(item.getItemId());
        //make this method blank
        return true;
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == PROFILE_PICTURE && resultCode == RESULT_OK && null != data) {
            super.onActivityResult(requestCode, resultCode, data);

            Uri uri = data.getData();
            try {
                Glide.with(Home.this).load(uri).into(profilePic);
                String filepath = FilePicker.getPath(Home.this, uri);
                File afile = new File(filepath);
                imageFile = new Compressor(this).compressToFile(afile);
                setProfileImage();
            } catch (Exception e) {

            }
        }
    }



    private void setProfileImage() {

        RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), imageFile);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", imageFile.getName(), requestBody);
        RequestBody id = RequestBody.create(MediaType.parse("text/plain"), uid);
        Business_ServiceAPI service = ApiClient.getRetrofit().create(Business_ServiceAPI.class);
        Call<ProfileImagePojo> profilePicture = service.setProfileImage(fileToUpload, id);


        profilePicture.enqueue(new Callback<ProfileImagePojo>() {
            @Override
            public void onResponse(Call<ProfileImagePojo> call, Response<ProfileImagePojo> response) {
                ProfileImagePojo pictureProfile = response.body();
                String imagePath = pictureProfile.getImageName();
                Glide.with(Home.this).load(imagePath).into(profilePic);



                Intent intent = new Intent("image_message");
                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
            }

            @Override
            public void onFailure(Call<ProfileImagePojo> call, Throwable t) {

            }
        });
    }

    public void fetchProfileImage() {

        Business_ServiceAPI serviceAPI = ApiClient.getRetrofit().create(Business_ServiceAPI.class);
        Call<ProfileImageFetchPojo> call = serviceAPI.getProfilePic(uid);
        call.enqueue(new Callback<ProfileImageFetchPojo>() {
            @Override
            public void onResponse(Call<ProfileImageFetchPojo> call, Response<ProfileImageFetchPojo> response) {
                ProfileImageFetchPojo picture = response.body();
                String img = picture.getPicture();
                Glide.with(Home.this).load(img).placeholder(R.drawable.profile_circle).dontAnimate().into(profilePic);
            }

            @Override
            public void onFailure(Call<ProfileImageFetchPojo> call, Throwable t) {

                // Toast.makeText(Home.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
