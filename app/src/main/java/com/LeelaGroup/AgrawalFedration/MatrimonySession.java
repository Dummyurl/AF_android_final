package com.LeelaGroup.AgrawalFedration;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.LeelaGroup.AgrawalFedration.matrimony.FormBasicDetailsActivity;
import com.LeelaGroup.AgrawalFedration.matrimony.LoginMatrimony;
import com.LeelaGroup.AgrawalFedration.matrimony.MatrimonyActivity;

import java.util.HashMap;

/**
 * Created by USer on 03-08-2017.
 */

public class MatrimonySession {
    // Shared Preferences reference
    SharedPreferences pref;

    // Editor reference for Shared preferences
    SharedPreferences.Editor editor;

    private boolean cpid=false;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREFER_NAME = "AndroidExamplePref_Mat";

    // All Shared Preferences Keys
    private static final String IS_USER_LOGIN = "IsUserLoggedIn";

    // User name (make variable public to access from outside)
    public static final String KEY_ID = "id";
    public static final String PROFILE_ID = "pid";

    // Email address (make variable public to access from outside)
    public static final String KEY_NAME= "email";

    // Constructor
    public MatrimonySession(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    //Create login session
    public void createUserLoginSession(String id, String pid){
        // Storing login value as TRUE
        editor.putBoolean(IS_USER_LOGIN, true);

        // Storing name in pref
        editor.putString(KEY_ID, id);

        // Storing email in pref
        editor.putString(PROFILE_ID, pid);

        // commit changes
        editor.commit();
    }


    public void storePid(boolean pid)
    {
        cpid=pid;
        editor.putBoolean(PROFILE_ID,cpid)  ;
        editor.commit();
    }

    /**
     * Check login method will check user login status
     * If false it will redirect user to login page
     * Else do anything
     * */
    public boolean checkLogin(){
        // Check login status
        if(!this.isUserLoggedIn()){

            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, LoginMatrimony.class);

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
     * */
    public HashMap<String, String> getUserDetails(){

        //Use hashmap to store user credentials
        HashMap<String, String> user = new HashMap<String, String>();

        // user name
        user.put(KEY_ID, pref.getString(KEY_ID, null));

        //user.put(PROFILE_ID,pref.getString(PROFILE_ID,null));

        // user email id
        user.put(KEY_NAME, pref.getString(KEY_NAME, null));

        // return user
        return user;
    }

    /**
     * Clear session details
     * */
    public void logoutUser(){

        // Clearing all user data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Login Activity
        Intent i = new Intent(_context, LoginMatrimony.class);

        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        _context.startActivity(i);
    }
    public void logoutFromMain(){

        // Clearing all user data from Shared Preferences
        editor.clear();
        editor.commit();

    }


    // Check for login
    public boolean isUserLoggedIn(){

        return pref.getBoolean(IS_USER_LOGIN, false);
    }


    public void goTomain() {


                if (pref.contains(KEY_ID)) {

                   boolean profID=pref.getBoolean(PROFILE_ID,false);
                    if (profID)
                    {
                        Intent loginIntent = new Intent(_context, MatrimonyActivity.class);
                        _context.startActivity(loginIntent);
                        //finish();
                    }else {
                        Intent loginIntent = new Intent(_context, FormBasicDetailsActivity.class);
                        _context.startActivity(loginIntent);
                        //finish();
                  }


                } else {

                    Intent loginIntent = new Intent(_context, LoginMatrimony.class);
                    _context.startActivity(loginIntent);
                    //finish();
                }

            }
}
