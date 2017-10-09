package com.LeelaGroup.AgrawalFedration.education;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.LeelaGroup.AgrawalFedration.Compressor;
import com.LeelaGroup.AgrawalFedration.EducationSessionManager;
import com.LeelaGroup.AgrawalFedration.Education_Pojos.ServerResponse;
import com.LeelaGroup.AgrawalFedration.FilePicker;
import com.LeelaGroup.AgrawalFedration.MyUtility;
import com.LeelaGroup.AgrawalFedration.Network.ApiClient;
import com.LeelaGroup.AgrawalFedration.R;
import com.LeelaGroup.AgrawalFedration.Service.ServiceAPIEducation;
import com.LeelaGroup.AgrawalFedration.business.BusinessAddAdvertized;
import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Education_Upload_Doc extends AppCompatActivity implements View.OnClickListener {

    static final int SELECTED_PICTURE_SSC = 1;
    static final int SELECTED_PICTURE_HSC = 5;
    static final int SELECTED_PICTURE_GRAD = 2;
    static final int SELECTED_PICTURE_POST = 3;
    static final int SELECTED_PICTURE_EXTRA = 7;
    static final int SELECTED_PICTURE_CERTIFICATE = 8;
    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 23;

    File cfile,cfile1,cfile2,cfile3,cfile4,cfile5;
    ProgressDialog progressDialog;

    ImageView ssc_btn_Result, hsc_btn_Result, grad_btn_Result, post_grad_btn_Result, Profile_btn_Result, Sign_btn_Result;

    Toolbar toolbar;

    String email;

    String filePath, filePath1, filePath2, filePath3, filePath5, filePath6;
    EducationSessionManager educationSessionManager;
    Button bt_ssc_bws, bt_hsc_bws, bt_ug_bws, bt_pg_bws, bt_pass_bws, bt_sign_bws;
    ImageView ssc_Result, hsc_Result, graduation_Result, post_graduate_Result, other_Certificate, other_Result;

    TextView ssc_Result_name, hsc_Result_name, graduate_text, post_graduation_text, other_activity, extra_Activity;
LinearLayout layout;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education__upload__doc);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Uploading...");

        educationSessionManager = new EducationSessionManager(getApplicationContext());

        if (educationSessionManager.checkLogin())
            finish();

        Intent intent = getIntent();

        Bundle b = intent.getExtras();

        email = b.getString("myname");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_matrimony);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setTitle("Upload Documents");

        layout=(LinearLayout)findViewById(R.id.parent);
        bt_ssc_bws = (Button) findViewById(R.id.browse_ssc);
        bt_hsc_bws = (Button) findViewById(R.id.browse_hsc);
        bt_ug_bws = (Button) findViewById(R.id.browse_UG);
        bt_pg_bws = (Button) findViewById(R.id.browse_PG);
        bt_pass_bws = (Button) findViewById(R.id.browse_pass);
        bt_sign_bws = (Button) findViewById(R.id.browse_sign);

        ssc_Result = (ImageView) findViewById(R.id.ssc_Result);
        hsc_Result = (ImageView) findViewById(R.id.hsc_Result);
        graduation_Result = (ImageView) findViewById(R.id.graduation_Result);
        post_graduate_Result = (ImageView) findViewById(R.id.post_graduate_Result);
        other_Certificate = (ImageView) findViewById(R.id.other_Certificate);
        other_Result = (ImageView) findViewById(R.id.other_Result);

        // ssc_btn_Result = (Button)findViewById(R.id.ssc_btn_Result);
        /*hsc_btn_Result = (Button)findViewById(R.id.hsc_btn_Result);*/
        ssc_Result_name = (TextView) findViewById(R.id.ten_ssc);
        hsc_Result_name = (TextView) findViewById(R.id.twelve_hsc);
        graduate_text = (TextView) findViewById(R.id.graduate_text);
        post_graduation_text = (TextView) findViewById(R.id.post_graduation_text);
        other_activity = (TextView) findViewById(R.id.other_activity);
        extra_Activity = (TextView) findViewById(R.id.extra_Activity);

        //upload_image = (Button) findViewById(R.id.upload_image);
        ssc_btn_Result = (ImageView) findViewById(R.id.ssc_btn_Result);
        hsc_btn_Result = (ImageView) findViewById(R.id.hsc_btn_Result);
        grad_btn_Result = (ImageView) findViewById(R.id.grad_btn_Result);
        post_grad_btn_Result = (ImageView) findViewById(R.id.post_grad_btn_Result);
        Sign_btn_Result = (ImageView) findViewById(R.id.sign_btn_Result);
        Profile_btn_Result = (ImageView) findViewById(R.id.Profile_btn_Result);

        ssc_btn_Result.setOnClickListener(this);
        Profile_btn_Result.setOnClickListener(this);
        hsc_btn_Result.setOnClickListener(this);
        grad_btn_Result.setOnClickListener(this);
        post_grad_btn_Result.setOnClickListener(this);
        Sign_btn_Result.setOnClickListener(this);


        bt_ssc_bws.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                // Show only images, no videos or anything else
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                // Always show the chooser (if there are multiple options available)
                startActivityForResult(Intent.createChooser(intent,"selected pic"), SELECTED_PICTURE_SSC);

            }
        });
        bt_hsc_bws.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                // Show only images, no videos or anything else
                intent1.setType("image/*");
                intent1.setAction(Intent.ACTION_GET_CONTENT);
                // Always show the chooser (if there are multiple options available)
                startActivityForResult(Intent.createChooser(intent1,"selected pic"), SELECTED_PICTURE_HSC);

            }
        });
        bt_ug_bws.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent();
                // Show only images, no videos or anything else
                intent2.setType("image/*");
                intent2.setAction(Intent.ACTION_GET_CONTENT);
                // Always show the chooser (if there are multiple options available)
                startActivityForResult(Intent.createChooser(intent2,"selected pic"), SELECTED_PICTURE_GRAD);

            }
        });
        bt_pg_bws.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent();
                // Show only images, no videos or anything else
                intent3.setType("image/*");
                intent3.setAction(Intent.ACTION_GET_CONTENT);
                // Always show the chooser (if there are multiple options available)
                startActivityForResult(Intent.createChooser(intent3,"selected pic"), SELECTED_PICTURE_POST);

            }
        });
        bt_pass_bws.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent();
                // Show only images, no videos or anything else
                intent4.setType("image/*");
                intent4.setAction(Intent.ACTION_GET_CONTENT);
                // Always show the chooser (if there are multiple options available)
                startActivityForResult(Intent.createChooser(intent4,"selected pic"), SELECTED_PICTURE_EXTRA);
            }
        });
        bt_sign_bws.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5 = new Intent();
                // Show only images, no videos or anything else
                intent5.setType("image/*");
                intent5.setAction(Intent.ACTION_GET_CONTENT);
                // Always show the chooser (if there are multiple options available)
                startActivityForResult(Intent.createChooser(intent5,"selected pic"), SELECTED_PICTURE_CERTIFICATE);

            }
        });

        /*upload_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  uploadMultipleFiles();

               // startActivity(new Intent(Education_Upload_Doc.this,EducationNews.class));
            }
        });*/

        ssc_btn_Result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if (MyUtility.isConnected(Education_Upload_Doc.this)){
                  sscResult();
              }else {
                  MyUtility.internetProblem(layout);
              }
            }
        });

        hsc_btn_Result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MyUtility.isConnected(Education_Upload_Doc.this)){
                    hscResult();
                }else {
                    MyUtility.internetProblem(layout);
                }
            }
        });

        Profile_btn_Result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MyUtility.isConnected(Education_Upload_Doc.this)){
                    ProfileResult();
                }else {
                    MyUtility.internetProblem(layout);
                }
            }
        });

        grad_btn_Result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MyUtility.isConnected(Education_Upload_Doc.this)){
                    gradResult();
                }else {
                    MyUtility.internetProblem(layout);
                }

            }
        });

        post_grad_btn_Result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MyUtility.isConnected(Education_Upload_Doc.this)){
                    pgResult();
                }else {
                    MyUtility.internetProblem(layout);
                }
            }
        });

        Sign_btn_Result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MyUtility.isConnected(Education_Upload_Doc.this)){
                    sign();
                }else {
                    MyUtility.internetProblem(layout);
                }
            }
        });

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);

        }

    }



  /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu_education,menu);
        return true;
    }*/


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.ssc_Result:
                // Toast.makeText(Education_Upload_Doc.this, "10th Result", Toast.LENGTH_LONG).show();
//                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                i.setType("*/*");
//                i.setAction(i.ACTION_GET_CONTENT);
//                startActivityForResult(i.createChooser(i,"select image"), SELECTED_PICTURE_SSC);
                Intent intent = new Intent();
                // Show only images, no videos or anything else
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                // Always show the chooser (if there are multiple options available)
                startActivityForResult(intent, SELECTED_PICTURE_SSC);

                break;


            case R.id.hsc_Result:
                // Toast.makeText(Education_Upload_Doc.this, "12th Result", Toast.LENGTH_SHORT).show();
//                Intent j = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                startActivityForResult(j, SELECTED_PICTURE_HSC);
                Intent intent1 = new Intent();
                // Show only images, no videos or anything else
                intent1.setType("image/*");
                intent1.setAction(Intent.ACTION_GET_CONTENT);
                // Always show the chooser (if there are multiple options available)
                startActivityForResult(intent1, SELECTED_PICTURE_HSC);
                break;

            case R.id.graduation_Result:
                // Toast.makeText(Education_Upload_Doc.this, "Graduation Result", Toast.LENGTH_SHORT).show();
               /* Intent k = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(k, SELECTED_PICTURE_GRAD);*/
                Intent intent2 = new Intent();
                // Show only images, no videos or anything else
                intent2.setType("image/*");
                intent2.setAction(Intent.ACTION_GET_CONTENT);
                // Always show the chooser (if there are multiple options available)
                startActivityForResult(intent2, SELECTED_PICTURE_GRAD);
                break;

            case R.id.post_graduate_Result:
                //Toast.makeText(Education_Upload_Doc.this, "Post Graduation Result", Toast.LENGTH_SHORT).show();
//                Intent l = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                startActivityForResult(l, SELECTED_PICTURE_POST);
                Intent intent3 = new Intent();
                // Show only images, no videos or anything else
                intent3.setType("image/*");
                intent3.setAction(Intent.ACTION_GET_CONTENT);
                // Always show the chooser (if there are multiple options available)
                startActivityForResult(intent3, SELECTED_PICTURE_POST);
                break;

            case R.id.other_Certificate:
                // Toast.makeText(Education_Upload_Doc.this, "Extra Activity Result", Toast.LENGTH_SHORT).show();
//                Intent m = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                startActivityForResult(m, SELECTED_PICTURE_EXTRA);
                Intent intent4 = new Intent();
                // Show only images, no videos or anything else
                intent4.setType("image/*");
                intent4.setAction(Intent.ACTION_GET_CONTENT);
                // Always show the chooser (if there are multiple options available)
                startActivityForResult(intent4, SELECTED_PICTURE_EXTRA);
                break;

            case R.id.other_Result:
                // Toast.makeText(Education_Upload_Doc.this, "Extra Activity Result", Toast.LENGTH_SHORT).show();
               /* Intent n = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(n, SELECTED_PICTURE_CERTIFICATE);*/
                Intent intent5 = new Intent();
                // Show only images, no videos or anything else
                intent5.setType("image/*");
                intent5.setAction(Intent.ACTION_GET_CONTENT);
                // Always show the chooser (if there are multiple options available)
                startActivityForResult(intent5, SELECTED_PICTURE_CERTIFICATE);
                break;

        }

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SELECTED_PICTURE_SSC && resultCode == RESULT_OK && null != data) {
            Uri uri = data.getData();
            try {
                Glide.with(Education_Upload_Doc.this).load(uri).into(ssc_Result);
                String filepath = FilePicker.getPath(Education_Upload_Doc.this, uri);
                File afile = new File(filepath);
                cfile =new Compressor(this).compressToFile(afile);
                bt_ssc_bws.setVisibility(View.GONE);
                ssc_Result.setVisibility(View.VISIBLE);

            }catch (Exception e){

            }



        } else if (requestCode == SELECTED_PICTURE_HSC && resultCode == RESULT_OK && null != data) {
            Uri uri = data.getData();
            try {
                Glide.with(Education_Upload_Doc.this).load(uri).into(hsc_Result);
                String filepath = FilePicker.getPath(Education_Upload_Doc.this, uri);
                File afile = new File(filepath);
                cfile1 =new Compressor(this).compressToFile(afile);
                hsc_Result.setVisibility(View.VISIBLE);
                bt_hsc_bws.setVisibility(View.GONE);
            }catch (Exception e){

            }


        } else if (requestCode == SELECTED_PICTURE_GRAD && resultCode == RESULT_OK && null != data) {

            Uri uri = data.getData();
            try {
                Glide.with(Education_Upload_Doc.this).load(uri).into(graduation_Result);
                String filepath = FilePicker.getPath(Education_Upload_Doc.this, uri);
                File afile = new File(filepath);
                cfile2 =new Compressor(this).compressToFile(afile);
                graduation_Result.setVisibility(View.VISIBLE);
                bt_ug_bws.setVisibility(View.GONE);
            }catch (Exception e){

            }

        } else if (requestCode == SELECTED_PICTURE_POST && resultCode == RESULT_OK && null != data) {
            Uri uri = data.getData();
            try {
                Glide.with(Education_Upload_Doc.this).load(uri).into(post_graduate_Result);
                String filepath = FilePicker.getPath(Education_Upload_Doc.this, uri);
                File afile = new File(filepath);
                cfile3 =new Compressor(this).compressToFile(afile);
                post_graduate_Result.setVisibility(View.VISIBLE);
                bt_pg_bws.setVisibility(View.GONE);
            }catch (Exception e){

            }

        } else if (requestCode == SELECTED_PICTURE_EXTRA && resultCode == RESULT_OK && null != data) {

            Uri uri = data.getData();
            try {
                Glide.with(Education_Upload_Doc.this).load(uri).into(other_Certificate);
                String filepath = FilePicker.getPath(Education_Upload_Doc.this, uri);
                File afile = new File(filepath);
                cfile4 =new Compressor(this).compressToFile(afile);
                other_Certificate.setVisibility(View.VISIBLE);
                bt_pass_bws.setVisibility(View.GONE);
            }catch (Exception e){

            }


        } else if (requestCode == SELECTED_PICTURE_CERTIFICATE && resultCode == RESULT_OK && null != data) {

            Uri uri = data.getData();
            try {
                Glide.with(Education_Upload_Doc.this).load(uri).into(other_Result);
                String filepath = FilePicker.getPath(Education_Upload_Doc.this, uri);
                File afile = new File(filepath);
                cfile5 =new Compressor(this).compressToFile(afile);
                other_Result.setVisibility(View.VISIBLE);
                bt_sign_bws.setVisibility(View.GONE);
            }catch (Exception e){

            }


        }
    }
       @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {

                }
                return;
            }
        }
    }

   // Compressor compressor=new Compressor(getApplicationContext());
    public void sscResult() {
        progressDialog.show();



        RequestBody requestBody1 = null;
        if (cfile != null && !cfile.equals("null")) {
            requestBody1 = RequestBody.create(MediaType.parse("*/*"), cfile);

        }
        RequestBody sess = RequestBody.create(MediaType.parse("text/plain"), email);

        MultipartBody.Part fileToUpload1 = null;
        if (cfile != null && !cfile.equals("null")) {
            fileToUpload1 = MultipartBody.Part.createFormData("file1", cfile.getName(), requestBody1);

        }

        ServiceAPIEducation service = ApiClient.getRetrofit().create(ServiceAPIEducation.class);

        Call<ServerResponse> ssc = service.SSCPicture(sess, fileToUpload1);

        ssc.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {

                ServerResponse ssc = response.body();

                if (ssc != null) {
                    if (ssc.isSuccess()) {
                         /*   AlertDialog.Builder builder=new AlertDialog.Builder(Education_Upload_Doc.this);
                            builder.setTitle("Message");
                            builder.setMessage(ssc.getMessage());
                            builder.show();*/
                        Toast.makeText(getApplicationContext(), "Successfully uploaded", Toast.LENGTH_SHORT).show();

                       /* AlertDialog.Builder alert = new AlertDialog.Builder(Education_Upload_Doc.this);

                        alert.setTitle("MSG");
                        alert.setMessage(ssc.getMessage());

                        alert.show();*/

                    } else {
                        Toast.makeText(getApplicationContext(), "Upload Failed", Toast.LENGTH_SHORT).show();

                    /*    AlertDialog.Builder alert = new AlertDialog.Builder(Education_Upload_Doc.this);
                        alert.setTitle("Message");
                        alert.setMessage(ssc.getMessage());
                        alert.show();*/
                    }
                } else {
                    assert ssc != null;
                    Log.v("Response", ssc.toString());
                }
                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
               /* AlertDialog.Builder alert = new AlertDialog.Builder(Education_Upload_Doc.this);
                alert.setTitle("Message");
                alert.setMessage(getText(R.string.checkConn));
                alert.show();*/
                Toast.makeText(Education_Upload_Doc.this, "Failed to upload", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        });
    }

    public void hscResult() {
        progressDialog.show();


        RequestBody requestBody2 = null;
        if (cfile1 != null && !cfile1.equals("null")) {
            requestBody2 = RequestBody.create(MediaType.parse("*/*"), cfile1);

        }
        RequestBody sess = RequestBody.create(MediaType.parse("text/plain"), email);

        MultipartBody.Part fileToUpload2 = null;

        if (cfile1 != null && !cfile1.equals("null")) {

            fileToUpload2 = MultipartBody.Part.createFormData("file2", cfile1.getName(), requestBody2);

        }

        ServiceAPIEducation service = ApiClient.getRetrofit().create(ServiceAPIEducation.class);

        Call<ServerResponse> hsc = service.HSCPicture(sess, fileToUpload2);

        hsc.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {

                ServerResponse hsc = response.body();

                if (hsc != null) {
                    if (hsc.isSuccess()) {
                      /*  AlertDialog.Builder builder=new AlertDialog.Builder(Education_Upload_Doc.this);
                        builder.setTitle("Message");
                        builder.setMessage(hsc.getMessage());
                        builder.show();*/
                        Toast.makeText(getApplicationContext(), "Succssfully Uploaded", Toast.LENGTH_SHORT).show();

                        /*AlertDialog.Builder alert = new AlertDialog.Builder(Education_Upload_Doc.this);

                        alert.setTitle("MSG");
                        alert.setMessage(hsc.getMessage());

                        alert.show();*/

                    } else {
                        Toast.makeText(getApplicationContext(),"Failed to upload", Toast.LENGTH_SHORT).show();

                       /* AlertDialog.Builder alert = new AlertDialog.Builder(Education_Upload_Doc.this);

                        alert.setTitle("Message");
                        alert.setMessage(hsc.getMessage());

                        alert.show();*/
                    }
                } else {
                    assert hsc != null;
                    Log.v("Response", hsc.toString());
                }
                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

              /*  AlertDialog.Builder alert = new AlertDialog.Builder(Education_Upload_Doc.this);

                alert.setTitle("Message");
                alert.setMessage(getText(R.string.checkConn));

                alert.show();*/
                Toast.makeText(getApplicationContext(),"Failed to upload", Toast.LENGTH_SHORT).show();

                progressDialog.dismiss();

            }
        });
    }

    public void gradResult() {
        progressDialog.show();


        RequestBody requestBody3 = null;
        if (cfile2 != null && !cfile2.equals("null")) {
            requestBody3 = RequestBody.create(MediaType.parse("*/*"), cfile2);
        }

        RequestBody sess = RequestBody.create(MediaType.parse("text/plain"), email);

        MultipartBody.Part fileToUpload3 = null;

        if (cfile2 != null && !cfile2.equals("null")) {

            fileToUpload3 = MultipartBody.Part.createFormData("file3", cfile2.getName(), requestBody3);

        }

        ServiceAPIEducation service = ApiClient.getRetrofit().create(ServiceAPIEducation.class);

        Call<ServerResponse> grad = service.GradPicture(sess, fileToUpload3);

        grad.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                ServerResponse grad = response.body();

                if (grad != null) {
                    if (grad.isSuccess()) {
                       Toast.makeText(getApplicationContext(), "Successfully uploaded", Toast.LENGTH_SHORT).show();
                       /* AlertDialog.Builder builder=new AlertDialog.Builder(Education_Upload_Doc.this);
                        builder.setTitle("Message");
                        builder.setMessage(grad.getMessage());
                        builder.show();*/

                    } else {
                        Toast.makeText(getApplicationContext(),"Upload Failed", Toast.LENGTH_SHORT).show();

                       /* AlertDialog.Builder alert = new AlertDialog.Builder(Education_Upload_Doc.this);

                        alert.setTitle("Message");
                        alert.setMessage(grad.getMessage());

                        alert.show();*/
                    }
                } else {
                    assert grad != null;
                    Log.v("Response", grad.toString());
                }
                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Upload Failed", Toast.LENGTH_SHORT).show();

             /*   AlertDialog.Builder alert = new AlertDialog.Builder(Education_Upload_Doc.this);

                alert.setTitle("Message");
                alert.setMessage(getText(R.string.checkConn));

                alert.show();
*/
                progressDialog.dismiss();

            }
        });

    }

    public void pgResult() {
        progressDialog.show();


        RequestBody requestBody4 = null;
        if (cfile3 != null && !cfile3.equals("null")) {

            requestBody4 = RequestBody.create(MediaType.parse("*/*"), cfile3);

        }

        RequestBody sess = RequestBody.create(MediaType.parse("text/plain"), email);

        MultipartBody.Part fileToUpload4 = null;

        if (cfile3 != null && !cfile3.equals("null")) {

            fileToUpload4 = MultipartBody.Part.createFormData("file4", cfile3.getName(), requestBody4);

        }

        ServiceAPIEducation service = ApiClient.getRetrofit().create(ServiceAPIEducation.class);

        Call<ServerResponse> pg = service.PostGradPicture(sess, fileToUpload4);

        pg.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {

                ServerResponse pg = response.body();

                if (pg != null) {
                    if (pg.isSuccess()) {
                        Toast.makeText(getApplicationContext(), "Successfully uploaded", Toast.LENGTH_SHORT).show();
                      /*  AlertDialog.Builder builder=new AlertDialog.Builder(Education_Upload_Doc.this);
                        builder.setTitle("Message");
                        builder.setMessage(pg.getMessage());
                        builder.show();*/

                    } else {
                        Toast.makeText(getApplicationContext(),"Upload Failed", Toast.LENGTH_SHORT).show();

                       /* AlertDialog.Builder alert = new AlertDialog.Builder(Education_Upload_Doc.this);

                        alert.setTitle("Message");
                        alert.setMessage(pg.getMessage());

                        alert.show();*/
                    }
                } else {
                    assert pg != null;
                    Log.v("Response", pg.toString());
                }
                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

                /*AlertDialog.Builder alert = new AlertDialog.Builder(Education_Upload_Doc.this);

                alert.setTitle("Message");
                alert.setMessage(getText(R.string.checkConn));

                alert.show();*/
                Toast.makeText(Education_Upload_Doc.this, "Failed to upload", Toast.LENGTH_SHORT).show();

                progressDialog.dismiss();
            }
        });


    }

    public void ProfileResult() {
        progressDialog.show();


        RequestBody requestBody5 = null;
        if (cfile4 != null && !cfile4.equals("null")) {

            requestBody5 = RequestBody.create(MediaType.parse("*/*"), cfile4);
        }

        RequestBody sess = RequestBody.create(MediaType.parse("text/plain"), email);

        MultipartBody.Part fileToUpload5 = null;

        if (cfile4 != null && !cfile4.equals("null")) {

            fileToUpload5 = MultipartBody.Part.createFormData("file5", cfile4.getName(), requestBody5);

        }

        ServiceAPIEducation service = ApiClient.getRetrofit().create(ServiceAPIEducation.class);

        Call<ServerResponse> profile = service.ProfilePicture(sess, fileToUpload5);

        profile.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {

                ServerResponse serverResponse = response.body();

                if (serverResponse != null) {
                    if (serverResponse.isSuccess()) {
                       /* AlertDialog.Builder builder=new AlertDialog.Builder(Education_Upload_Doc.this);
                        builder.setTitle("Message");
                        builder.setMessage(serverResponse.getMessage());
                        builder.show();*/
                       Toast.makeText(getApplicationContext(), "Successfully uploaded", Toast.LENGTH_SHORT).show();

                        /*AlertDialog.Builder alert = new AlertDialog.Builder(Education_Upload_Doc.this);

                        alert.setTitle("MSG");
                        alert.setMessage(serverResponse.getMessage());

                        alert.show();*/

                    } else {

                      /*  AlertDialog.Builder builder=new AlertDialog.Builder(Education_Upload_Doc.this);
                        builder.setTitle("Message");
                        builder.setMessage(serverResponse.getMessage());
                        builder.show();*/
                       Toast.makeText(getApplicationContext(), "Failed To Upload", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    assert serverResponse != null;
                    Log.v("Response", serverResponse.toString());
                }
                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

               Toast.makeText(Education_Upload_Doc.this,  "Failed To Upload", Toast.LENGTH_SHORT).show();
              /*  AlertDialog.Builder builder=new AlertDialog.Builder(Education_Upload_Doc.this);
                builder.setTitle("Message");
                builder.setMessage(getText(R.string.checkConn));
                builder.show();
                progressDialog.dismiss();*/

            }
        });


    }

    public void sign() {
        progressDialog.show();

        RequestBody requestBody6 = null;
        if (cfile5 != null && !cfile5.equals("null")) {
            requestBody6 = RequestBody.create(MediaType.parse("*/*"), cfile5);
        }

        RequestBody sess = RequestBody.create(MediaType.parse("text/plain"), email);

        MultipartBody.Part fileToUpload6 = null;

        if (cfile5 != null && !cfile5.equals("null")) {

            fileToUpload6 = MultipartBody.Part.createFormData("file6", cfile5.getName(), requestBody6);

        }

        ServiceAPIEducation service = ApiClient.getRetrofit().create(ServiceAPIEducation.class);

        Call<ServerResponse> sign = service.SignPicture(sess, fileToUpload6);

        sign.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {

                ServerResponse sign = response.body();

                if (sign != null) {
                    if (sign.isSuccess()) {
                       /* AlertDialog.Builder builder=new AlertDialog.Builder(Education_Upload_Doc.this);
                        builder.setTitle("Message");
                        builder.setMessage(sign.getMessage());
                        builder.show();
*/
                        Toast.makeText(getApplicationContext(),"Successfully uploaded", Toast.LENGTH_SHORT).show();

                        /*AlertDialog.Builder alert = new AlertDialog.Builder(Education_Upload_Doc.this);

                        alert.setTitle("MSG");
                        alert.setMessage(sign.getMessage());

                        alert.show();*/

                    } else {
                        Toast.makeText(getApplicationContext(),"Failed To Upload", Toast.LENGTH_SHORT).show();
/*
                        AlertDialog.Builder alert = new AlertDialog.Builder(Education_Upload_Doc.this);

                        alert.setTitle("Message");
                        alert.setMessage(sign.getMessage());

                        alert.show();*/
                    }
                } else {
                    assert sign != null;
                    Log.v("Response", sign.toString());
                }
                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Failed To Upload", Toast.LENGTH_SHORT).show();
              /*  AlertDialog.Builder alert = new AlertDialog.Builder(Education_Upload_Doc.this);

                alert.setTitle("Message");
                alert.setMessage(getText(R.string.checkConn));

                alert.show();
*/
                progressDialog.dismiss();

            }
        });
    }

    private void uploadMultipleFiles() {
        progressDialog.show();

        File file5 = null;
        File file = new File(filePath);
        File file1 = new File(filePath1);
        File file2 = new File(filePath2);
        File file3 = new File(filePath3);
        File file4 = new File(filePath5);
        if (filePath6 != null && !filePath6.equals("null")) {
            file5 = new File(filePath6);
        }
        RequestBody requestBody1 = RequestBody.create(MediaType.parse("*/*"), file);
        RequestBody requestBody2 = RequestBody.create(MediaType.parse("*/*"), file1);
        RequestBody requestBody3 = RequestBody.create(MediaType.parse("*/*"), file2);
        RequestBody requestBody4 = RequestBody.create(MediaType.parse("*/*"), file3);
        RequestBody requestBody5 = RequestBody.create(MediaType.parse("*/*"), file4);
        RequestBody requestBody6 = null;
        if (file5 != null && !file5.equals("null")) {
            requestBody6 = RequestBody.create(MediaType.parse("*/*"), file5);
        }
        RequestBody sess = RequestBody.create(MediaType.parse("text/plain"), email);
        MultipartBody.Part fileToUpload6 = null;
        MultipartBody.Part fileToUpload1 = MultipartBody.Part.createFormData("file1", file.getName(), requestBody1);
        MultipartBody.Part fileToUpload2 = MultipartBody.Part.createFormData("file2", file1.getName(), requestBody2);
        MultipartBody.Part fileToUpload3 = MultipartBody.Part.createFormData("file3", file2.getName(), requestBody3);
        MultipartBody.Part fileToUpload4 = MultipartBody.Part.createFormData("file4", file3.getName(), requestBody4);

        MultipartBody.Part fileToUpload5 = MultipartBody.Part.createFormData("file5", file4.getName(), requestBody5);
        if (file5 != null && !file5.equals("null")) {
            fileToUpload6 = MultipartBody.Part.createFormData("file6", file5.getName(), requestBody6);
        }

        ServiceAPIEducation service = ApiClient.getRetrofit().create(ServiceAPIEducation.class);

        Call<ServerResponse> call = service.uploadMulFile
                (sess, fileToUpload1, fileToUpload2, fileToUpload3, fileToUpload4, fileToUpload5, fileToUpload6);

        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {

                ServerResponse serverResponse = response.body();

                if (serverResponse != null) {
                    if (serverResponse.isSuccess()) {
                      //  Toast.makeText(getApplicationContext(), serverResponse.getMessage(), Toast.LENGTH_SHORT).show();

                        Bundle b = new Bundle();
                        b.putString("myname", email);

                        Intent intent = new Intent(getApplicationContext(), EducationNews.class);
                        intent.putExtras(b);
                        startActivity(intent);

                    } else {
                       // Toast.makeText(getApplicationContext(), serverResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    assert serverResponse != null;
                    Log.v("Response", serverResponse.toString());
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

               // Toast.makeText(Education_Upload_Doc.this, "unsucessfull uploading", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        });
    }


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
