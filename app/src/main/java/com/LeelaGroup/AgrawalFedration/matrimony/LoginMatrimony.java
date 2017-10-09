package com.LeelaGroup.AgrawalFedration.matrimony;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.LeelaGroup.AgrawalFedration.Home;
import com.LeelaGroup.AgrawalFedration.MatrimonySession;
import com.LeelaGroup.AgrawalFedration.MyUtility;
import com.LeelaGroup.AgrawalFedration.Network.ApiClient;
import com.LeelaGroup.AgrawalFedration.R;
import com.LeelaGroup.AgrawalFedration.Service.ServiceMatrimony;
import com.LeelaGroup.AgrawalFedration.matrimony.models.LoginModel;
import com.LeelaGroup.AgrawalFedration.matrimony.models.ProfileIdPojo;
import com.LeelaGroup.AgrawalFedration.matrimony.models.ProfileModel;
import com.LeelaGroup.AgrawalFedration.matrimony.validation.CustomValidator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginMatrimony extends AppCompatActivity {

    TextView newregister,frogotPass;
    EditText email,password;
    ProgressBar progress;
    Button login;
    SharedPreferences preferences;
    private ProgressDialog pDialog;
    String mat_id="",mat_fname="",mat_lname="",pid="";
    String getEmail="",userid="",otp="",pass="";
    String mat_email,mat_pwd;
    MatrimonySession matrimonySession;
    List<ProfileModel> profileList;
    Intent intent;
    LinearLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_matrimony);

       matrimonySession=new MatrimonySession(getApplicationContext());

        login =(Button)findViewById(R.id.button);
        email =(EditText)findViewById(R.id.emailphone);
        password = (EditText)findViewById(R.id.logpass);
        frogotPass=(TextView)findViewById(R.id.mat_forgot_pass);
        newregister = (TextView)findViewById(R.id.newregister);

        layout=(LinearLayout)findViewById(R.id.activity_login_matrimony);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyUtility.hideKeyboard(layout,LoginMatrimony.this);
                if (validateFirst())
                {
                    if (MyUtility.isConnected(LoginMatrimony.this)) {
                        setLogin();
                    }else {
                        MyUtility.internetProblem(layout);
                    }

                }
            }
        });


        newregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginMatrimony.this,MatrimonyRegistration.class);

                startActivity(intent);
                finish();
            }
        });

        frogotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(LoginMatrimony.this,ForgotPasswordActivity.class);
                startActivity(intent);
                finish();
            }
        });

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);


    }

    @Override
    public boolean onSupportNavigateUp() {
        Intent intent=new Intent(LoginMatrimony.this,Home.class);
        startActivity(intent);
        finish();
        return true;
    }

    public void isProfileCreated()
    {

        ServiceMatrimony serviceMatrimony =ApiClient.getRetrofit().create(ServiceMatrimony.class);
        Call<ProfileIdPojo> profileIdPojoCall=serviceMatrimony.isPRofileIDExist(mat_id);
        profileIdPojoCall.enqueue(new Callback<ProfileIdPojo>() {
            @Override
            public void onResponse(Call<ProfileIdPojo> call, Response<ProfileIdPojo> response) {
                hidepDialog();
                ProfileIdPojo profileIdPojo= new ProfileIdPojo();
                profileIdPojo=response.body();
                try{

                    boolean pid=profileIdPojo.getSuccess();
                    String spid=String.valueOf(pid);
                    matrimonySession.storePid(pid);
                    if (!pid)
                    {
                        Intent forms= new Intent(getApplicationContext(),FormBasicDetailsActivity.class);
                        startActivity(forms);
                        finish();
                    }
                    else {
                        startActivity(intent);
                        finish();
                    }
                }catch (Exception e)
                {

                }

            }

            @Override
            public void onFailure(Call<ProfileIdPojo> call, Throwable t) {

            }
        });
    }

    public void setLogin()
    {
        showpDialog();
        mat_email= email.getText().toString();
       // Toast.makeText(this, mat_email, Toast.LENGTH_SHORT).show();
        mat_pwd= password.getText().toString();
        //Toast.makeText(this, mat_pwd, Toast.LENGTH_SHORT).show();
        ServiceMatrimony serviceMatrimony= ApiClient.getRetrofit().create(ServiceMatrimony.class);
        Call<LoginModel> loginCall=serviceMatrimony.setLoginDetails(mat_email,mat_pwd);

        loginCall.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
               hidepDialog();
               LoginModel loginModel=response.body();

                if (loginModel.getSuccess())
                {
                    
                    Toast.makeText(LoginMatrimony.this, loginModel.getMessage(), Toast.LENGTH_LONG).show();
                    intent = new Intent(getApplicationContext(), MatrimonyActivity.class);
                    mat_id=loginModel.getMatId();
                    mat_fname=loginModel.getMatFname();
                    mat_lname=loginModel.getMatLname();
                    //intent.putExtra("mat_id",mat_id);
                    matrimonySession.createUserLoginSession(mat_id,mat_fname+" "+mat_lname);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    email.setText("");
                    password.setText("");
                    email.requestFocus();
                    isProfileCreated();
                   // finish();
                }
                else {


                   Toast.makeText(LoginMatrimony.this, loginModel.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                hidepDialog();

                Toast.makeText(LoginMatrimony.this, "Please Check Internet Connection", Toast.LENGTH_LONG).show();
            }
        });

    }
    private boolean validateFirst() {

        CustomValidator validator = new CustomValidator();

        final String Email = email.getText().toString();
        if (!validator.isValidEmail(Email)) {
            email.requestFocus();
            email.setError("Please Enter Valid Email");
            return false;
        }
        email.setError(null);

        final String pass = password.getText().toString();
        if (!validator.isEmptyField(pass)) {
            password.requestFocus();
            password.setError("Please Enter Valid Password");
            return false;
        }
            return true;
    }


    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }


}
