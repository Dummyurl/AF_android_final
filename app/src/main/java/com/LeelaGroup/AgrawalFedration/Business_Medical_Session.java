package com.LeelaGroup.AgrawalFedration;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;

import java.util.HashMap;

/**
 * Created by USer on 18-07-2017.
 */

public class Business_Medical_Session {
    // Shared Preferences reference
    SharedPreferences pref;

    // Editor reference for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    public static final String PREFER_NAME = "AndroidExamplePref_Buss";

    // All Shared Preferences Keys
    protected static final String IS_USER_LOGIN = "IsUserLoggedIn";

    // User name (make variable public to access from outside)
    public static final String KEY_ID = "id";

    // Email address (make variable public to access from outside)
    public static final String KEY_Email = "email";

    public static final String KEY_Name = "name";

    public static final String Key_Token = "token";




    // Constructor
    public Business_Medical_Session(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    //Create login session
    public void createUserLoginSession(String id, String email,String fname) {
        // Storing login value as TRUE
        editor.putBoolean(IS_USER_LOGIN, true);

        // Storing name in pref
        editor.putString(KEY_ID, id);

        // Storing email in pref
        editor.putString(KEY_Email, email);

        //Storing fname in pref
        editor.putString(KEY_Name, fname);

        // commit changes
        editor.commit();
    }

    /**
     * Check login method will check user login status
     * If false it will redirect user to login page
     * Else do anything
     */
    public boolean checkLogin() {
        // Check login status
        if (!this.isUserLoggedIn()) {

            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, MainLoginActivity.class);

            // Closing all the Activities from stack
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);

            return true;
        }
        return false;
    }


    /**
     * Get stored session data
     */
    public HashMap<String, String> getUserDetails() {

        //Use hashmap to store user credentials
        HashMap<String, String> user = new HashMap<String, String>();

        // user name
        user.put(KEY_ID, pref.getString(KEY_ID, null));

        // user email id
        user.put(KEY_Email, pref.getString(KEY_Email, null));

        // user fname
        user.put(KEY_Name, pref.getString(KEY_Name, null));


        user.put(Key_Token, pref.getString(Key_Token, null));


        // return user
        return user;
    }


    /**
     * Clear session details
     */
    public void logoutUser() {

        // Clearing all user data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Login Activity
        Intent i = new Intent(_context, MainLoginActivity.class);

        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        _context.startActivity(i);

    }


    // Check for login
    public boolean isUserLoggedIn() {

        return pref.getBoolean(IS_USER_LOGIN, false);
    }


    public void goTomain() {

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {


                if (pref.contains(KEY_ID)) {

                    Intent loginIntent = new Intent(_context, Home.class);
                    _context.startActivity(loginIntent);
                    //finish();

                } else {

                    Intent loginIntent = new Intent(_context, MainLoginActivity.class);
                    _context.startActivity(loginIntent);
                    //finish();
                }

            }
        }, 4000);
    }

    public boolean isIdStore()
    {
        if (pref.contains(KEY_ID))
        {
            return true;
        }
        else{
            return false;
        }
    }


    public void insertToken(String token) {
        // Storing name in pref
        editor.putString(Key_Token, token);

        editor.commit();
    }

}
