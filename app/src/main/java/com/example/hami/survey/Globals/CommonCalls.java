package com.example.hami.survey.Globals;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by HAMI on 07/10/2017.
 */

public class CommonCalls {

    public static final String SHARE_PREF_NAME = "surveyUser";
    public static final String USER_NAME = "username";
    public static final String USER_SESSION_ID = "user_session_id";
    public static final String name = "name";
    public static final String fname = "father_name";
    public static final String age = "age";
    public static final String dob = "date_of_birth";
    public static final String cnic_no = "cnic_no";
    public static final String cnic_place = "cnic_place";
    public static final String cnic_expi = "cnic_expiry";
    public static final String gender = "gender";
    public static final String religon = "religon";
    public static final String cast = "cast";
    public static final String contact = "contact";
    public static final String skills = "skills";
    public static final String emp_status = "emp_status";
    public static final String emp_will = "emp_will";
    public static final String education = "education";
    public static final String availabilit = "available";
    public static final String martial_Status = "martial_status";
    public static final String remarks = "remarks";
    public static String longitude = "longitude";
    public static String latitude = "latitude";
    public static String image = "image";
    public static String no_male_child = "no_male_child";
    public static String no_female_child = "no_female_child";


    public static final boolean isInternetAvailable(Context context) {
        ConnectivityManager internetConnection;
        internetConnection = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        // ARE WE CONNECTED TO THE NET
        if (internetConnection.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTED ||
                internetConnection.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTED) {
            return true;
        } else if (internetConnection.getNetworkInfo(0).getState() == NetworkInfo.State.DISCONNECTED
                || internetConnection.getNetworkInfo(1).getState() == NetworkInfo.State.DISCONNECTED) {
            return false;
        }
        return false;
    }
}
