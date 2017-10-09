package com.LeelaGroup.AgrawalFedration.education;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.LeelaGroup.AgrawalFedration.Education_Pojos.ImageFeatcPojo;
import com.LeelaGroup.AgrawalFedration.MyUtility;
import com.LeelaGroup.AgrawalFedration.Network.ApiClient;
import com.LeelaGroup.AgrawalFedration.R;
import com.LeelaGroup.AgrawalFedration.Service.ServiceAPIEducation;
import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Document extends AppCompatActivity {

    String email;

    ImageView ssc_doc, hsc_doc, grad_doc, post_grad_doc, profile_doc, sign_doc;
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_matrimony);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setTitle("Uploaded Documents");

        Intent intent = getIntent();

        Bundle b = intent.getExtras();

        email = b.getString("myname");

        ssc_doc = (ImageView) findViewById(R.id.ssc_doc);
        hsc_doc = (ImageView) findViewById(R.id.hsc_doc);
        grad_doc = (ImageView) findViewById(R.id.grad_doc);
        post_grad_doc = (ImageView) findViewById(R.id.post_grad_doc);
        profile_doc = (ImageView) findViewById(R.id.profile_doc);
        sign_doc = (ImageView) findViewById(R.id.sign_doc);
        layout = (LinearLayout) findViewById(R.id.parent);
        if (MyUtility.isConnected(Document.this))
        {
            featchImage();
        }else {
            MyUtility.internetProblem(layout);
        }

    }

    public void featchImage() {
        ServiceAPIEducation service = ApiClient.getRetrofit().create(ServiceAPIEducation.class);

        Call<ImageFeatcPojo> ifp = service.getImageDeTail(email);

        ifp.enqueue(new Callback<ImageFeatcPojo>() {
            @Override
            public void onResponse(Call<ImageFeatcPojo> call, Response<ImageFeatcPojo> response) {
                ImageFeatcPojo ifp = response.body();

                String img = ifp.getEdProfOtherCert();

                Glide.with(Document.this).load(ifp.getEdProfOtherCert()).placeholder(R.drawable.gallery_back).dontAnimate().into(profile_doc);
                Glide.with(Document.this).load(ifp.getEdProfSscMarksheet()).placeholder(R.drawable.gallery_back).dontAnimate().into(ssc_doc);
                Glide.with(Document.this).load(ifp.getEdProfHscMarksheet()).placeholder(R.drawable.gallery_back).dontAnimate().into(hsc_doc);
                Glide.with(Document.this).load(ifp.getEdProfGdMarksheet()).placeholder(R.drawable.gallery_back).dontAnimate().into(grad_doc);
                Glide.with(Document.this).load(ifp.getEdProfPgMarksheet()).placeholder(R.drawable.gallery_back).dontAnimate().into(post_grad_doc);
                Glide.with(Document.this).load(ifp.getEdProfScanSign()).placeholder(R.drawable.gallery_back).dontAnimate().into(sign_doc);
            }

            @Override
            public void onFailure(Call<ImageFeatcPojo> call, Throwable t) {

              /*  AlertDialog.Builder alert = new AlertDialog.Builder(Document.this);

                alert.setTitle(getText(R.string.checkConn));
                alert.setMessage(t.getMessage());

                alert.show();
*/
                Toast.makeText(Document.this, getText(R.string.internet_error), Toast.LENGTH_SHORT).show();

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
