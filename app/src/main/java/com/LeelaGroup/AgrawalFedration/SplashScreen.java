package com.LeelaGroup.AgrawalFedration;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.LeelaGroup.AgrawalFedration.R;

/**
 * Created by Admin on 8/8/2017.
 */

public class SplashScreen extends Activity {
  //  private static final String TAG = SplashScreenActivity.class.getSimpleName();
    private ImageView backgroundImage;
    public static final String MyPREFERENCES = "MyPrefs" ;
    private static final String IS_USER_LOGIN = "IsUserLoggedIn";

    public static final String KEY_ID = "name";

    public static final String KEY_EMAIL = "email";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.splashscreen);
        goTomain();


       /* Business_Medical_Session business_medical_session=new Business_Medical_Session(getApplicationContext());
        business_medical_session.goTomain();*/
    }

    private void goTomain() {

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {


                Business_Medical_Session business_medical_session=new Business_Medical_Session(getApplicationContext());

                if (!business_medical_session.checkLogin()) {

                    Intent loginIntent = new Intent(SplashScreen.this, Home.class);
                    startActivity(loginIntent);
                    finish();

                } else {

                  /*  Intent loginIntent = new Intent(SplashScreen.this, MainLoginActivity.class);
                    startActivity(loginIntent);*/
                    finish();
                }

            }
        }, 4000);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

       // Log.e(TAG, "onActivityResult");


    }
}
