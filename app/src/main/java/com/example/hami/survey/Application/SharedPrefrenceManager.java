package com.example.hami.survey.Application;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.hami.survey.Globals.CommonCalls;

/**
 * Created by HAMI on 08/10/2017.
 */

public class SharedPrefrenceManager {

    private static SharedPrefrenceManager mInstance;
    private static Context mcontext;




    public SharedPrefrenceManager(Context context) {

        mcontext = context;
    }

    public static SharedPrefrenceManager getInstance(Context context){
        if(mInstance == null){
            mInstance = new SharedPrefrenceManager(context);
        }
        return  mInstance;
    }

    public boolean userLogin(String user_name,String user_session_id){

        SharedPreferences preferences = mcontext.getSharedPreferences(CommonCalls.SHARE_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString(CommonCalls.USER_NAME,user_name);
        editor.putString(CommonCalls.USER_SESSION_ID,user_session_id);
        editor.apply();
        return true;

    }
    public boolean isUserLoggedIn(){
        SharedPreferences preferences = mcontext.getSharedPreferences(CommonCalls.SHARE_PREF_NAME,Context.MODE_PRIVATE);
        if(preferences.getString(CommonCalls.USER_NAME,null) != null && preferences.getString(CommonCalls.USER_SESSION_ID,null) != null){
            return  true;
        }
        return false;
    }

    public boolean Logout(){
        SharedPreferences preferences = mcontext.getSharedPreferences(CommonCalls.SHARE_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
        return true;
    }

    public String user_session_id(){
        SharedPreferences preferences = mcontext.getSharedPreferences(CommonCalls.SHARE_PREF_NAME,Context.MODE_PRIVATE);
        String session_id = preferences.getString(CommonCalls.USER_SESSION_ID,null);
        return session_id;
    }

    public String admin_Name(){
        SharedPreferences preferences = mcontext.getSharedPreferences(CommonCalls.SHARE_PREF_NAME,Context.MODE_PRIVATE);
        String name = preferences.getString(CommonCalls.USER_NAME,null);
        return name;
    }
}
