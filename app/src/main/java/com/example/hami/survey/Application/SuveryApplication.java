package com.example.hami.survey.Application;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.example.hami.survey.Globals.CommonCalls;
import com.example.hami.survey.Interface.ApiInterface;
import com.example.hami.survey.Models.Signup_Model;
import com.example.hami.survey.Models.Signup_Response_Model;
import com.example.hami.survey.Network.API_Client;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by HAMI on 22/10/2017.
 */

public class SuveryApplication extends Application {

    ArrayList<Signup_Model> arrayList;
    @Override
    public void onCreate() {
        super.onCreate();
        new Thread(new Runnable() {
            @Override
            public void run() {
                arrayList = new ArrayList<>();
                arrayList = SharedPrefrenceManager.getInstance(getApplicationContext()).getDataListFromSharedPreference();
                if( arrayList.size()!= 0 && CommonCalls.isInternetAvailable(getApplicationContext())) {
                    sendResumeData(arrayList);
                    SharedPrefrenceManager.getInstance(getApplicationContext()).clearList();

                }else{
                    Log.e("USER: ","No data");

                }
            }
        }).start();


    }

    public void sendResumeData(ArrayList<Signup_Model> arrayList) {

        Retrofit client = API_Client.getApiClient();
        ApiInterface api = client.create(ApiInterface.class);
        Signup_Model signup_model;
        for (int i = 0; i < arrayList.size(); i++) {
            signup_model = arrayList.get(i);

            Call<Signup_Response_Model> call = api.setUserInfo(
                    signup_model.getAdminName(),
                    signup_model.getName(),
                    signup_model.getFatherName(),
                    signup_model.getAge(),
                    signup_model.getDob(),
                    signup_model.getGender(),
                    signup_model.getCnic(),
                    signup_model.getPlaceOfIssuence(),
                    signup_model.getCnicExpiry(),
                    signup_model.getCast(),
                    signup_model.getReligion(),
                    signup_model.getPhonenumber(),
                    signup_model.getEducation(),
                    signup_model.getSkill(),
                    signup_model.getMaritalStatus(),
                    signup_model.getEmployeeStatus(),
                    signup_model.getWillingForEmp(),
                    signup_model.getMale_child(),
                    signup_model.getFemale_child(),
                    signup_model.getAvailabilty(),
                    signup_model.getRemarks(),
                    signup_model.getLongitude(),
                    signup_model.getLatitude(),
                    signup_model.getImage()

            );
            Log.e("Data-"+signup_model.getName(),signup_model.getAdminName()+","+signup_model.getName()+","+signup_model.getFatherName());

            call.enqueue(new Callback<Signup_Response_Model>() {
                @Override
                public void onResponse(Call<Signup_Response_Model> call, Response<Signup_Response_Model> response) {
                    if (response.isSuccessful()) {
                        Signup_Response_Model signup_response_model = response.body();
                        if (signup_response_model.getSuccess() == 1) {
                         //   mDiloge.dismiss();
                            Log.e("Data Status","Resumed Data Added");
                        }

                    }
                }

                @Override
                public void onFailure(Call<Signup_Response_Model> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    Log.e("Server Status","Server Response Failure");
                }
            });

        }



    }


}
