package com.example.hami.survey.Application;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.hami.survey.Globals.CommonCalls;
import com.example.hami.survey.Models.Signup_Model;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


/**
 * Created by HAMI on 08/10/2017.
 */

public class SharedPrefrenceManager {

    private static SharedPrefrenceManager mInstance;
    private static Context mcontext;
    public static ArrayList<Signup_Model> objectList = new ArrayList<>();
    ArrayList<Signup_Model> arrayList;




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

    public void saveDataListToSharedpreference(ArrayList<Signup_Model> dataList) {
        SharedPreferences preferences = mcontext.getSharedPreferences(CommonCalls.SHARE_PREF_OBJECT_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        //convert ArrayList object to String by Gson
        String jsonScore = gson.toJson(dataList);
        //save to shared preference
        editor.putString(CommonCalls.SIGNUP_OBJECT_NAME,jsonScore);
        editor.commit();
    }

    public ArrayList<Signup_Model> getDataListFromSharedPreference() {
        SharedPreferences preferences = mcontext.getSharedPreferences(CommonCalls.SHARE_PREF_OBJECT_NAME,Context.MODE_PRIVATE);
        Gson gson = new Gson();
        //retrieve data from shared preference
        String jsonScore = preferences.getString(CommonCalls.SIGNUP_OBJECT_NAME,null);
        Type type = new TypeToken<ArrayList<Signup_Model>>(){}.getType();
        arrayList = gson.fromJson(jsonScore, type);
        if(arrayList == null){
            arrayList = new ArrayList<>();
        }

        return arrayList;
    }
    public void clearList(){
        SharedPreferences preferences = mcontext.getSharedPreferences(CommonCalls.SHARE_PREF_OBJECT_NAME,Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(CommonCalls.SIGNUP_OBJECT_NAME);
        editor.apply();
    }





}
