package com.LeelaGroup.AgrawalFedration;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.LeelaGroup.AgrawalFedration.education.EducationNews;
import com.LeelaGroup.AgrawalFedration.education.login_education;

import java.util.HashMap;

/**
 * Created by USer on 13-07-2017.
 */

public class EducationSessionManager {

    // Shared Preferences reference
    SharedPreferences edu_pref;

    // Editor reference for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREFER_NAME = "AndroidExamplePref_Edu";

    // All Shared Preferences Keys
    private static final String IS_USER_LOGIN = "IsUserLoggedIn";

    // User name (make variable public to access from outside)
//    public static final String KEY_ID = "name";

    // Email address (make variable public to access from outside)
    public static final String KEY_EMAIL = "email";

    // Constructor
    public EducationSessionManager(Context context){
        this._context = context;
        edu_pref = _context.getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
        editor = edu_pref.edit();
    }

    //Create login session
    public void createUserLoginSession(String email){
        // Storing login value as TRUE
        editor.putBoolean(IS_USER_LOGIN, true);

        // Storing name in pref
//        editor.putString(KEY_ID, name);

        // Storing email in pref
        editor.putString(KEY_EMAIL, email);

        // commit changes
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
            Intent i = new Intent(_context, login_education.class);

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
    //    user.put(KEY_ID, edu_pref.getString(KEY_ID, null));

        // user email id
        user.put(KEY_EMAIL, edu_pref.getString(KEY_EMAIL, null));

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
        Intent i = new Intent(_context, login_education.class);

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

        return edu_pref.getBoolean(IS_USER_LOGIN, false);
    }


    public void goTomain() {


        if (edu_pref.contains(KEY_EMAIL)) {

            Intent loginIntent = new Intent(_context, EducationNews.class);
            _context.startActivity(loginIntent);
            //finish();

        } else {

            Intent loginIntent = new Intent(_context, login_education.class);
            _context.startActivity(loginIntent);
            //finish();
        }

    }
}
