package com.example.hami.survey;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.location.Criteria;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hami.survey.Application.SharedPrefrenceManager;
import com.example.hami.survey.Globals.CommonCalls;
import com.example.hami.survey.Interface.ApiInterface;
import com.example.hami.survey.Models.Logout_Model;
import com.example.hami.survey.Models.Signup_Model;
import com.example.hami.survey.Models.Signup_Response_Model;
import com.example.hami.survey.Network.API_Client;
import com.mvc.imagepicker.ImagePicker;

import java.io.ByteArrayOutputStream;

import im.delight.android.location.SimpleLocation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    Button p_button, submit,done;
    SharedPreferences preferences;
    ProgressDialog mDiloge;
    TextView location;
    Button btnLocation;
    private double longitude;
    private double latitude;
    private LocationManager locationManager;
    private String provider;
   // public  ArrayList<Signup_Model> arrayList;
    public static Boolean exit = false;
    ImageView image;
    private Bitmap bitmap = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        initView();

    }

    //Initializing view
    public void initView() {

        Toolbar mToolbar = (Toolbar) findViewById(R.id.main_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Home");
        mToolbar.setTitleTextColor(Color.WHITE);

        p_button = (Button) findViewById(R.id.personal_info);
        p_button.setOnClickListener(this);
        submit = (Button) findViewById(R.id.submit_info);
        submit.setOnClickListener(this);
        done = (Button) findViewById(R.id.done_info);
        done.setOnClickListener(this);



        image = (ImageView) findViewById(R.id.image);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPickImage(view);
            }
        });

        location = (TextView) findViewById(R.id.location);
        btnLocation = (Button) findViewById(R.id.btnLocation);
        btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

                provider = locationManager.getBestProvider(new Criteria(), false);

                if (ContextCompat.checkSelfPermission(HomeActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        && ContextCompat.checkSelfPermission(HomeActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(HomeActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 0);
                } else {

                    // construct a new instance of SimpleLocation
                    SimpleLocation SLocation = new SimpleLocation(HomeActivity.this);

                    // if we can't access the location yet
                    if (!SLocation.hasLocationEnabled()) {
                        // ask the user to enable location access
                        SimpleLocation.openSettings(HomeActivity.this);
                    }

                    SLocation.beginUpdates();
                    latitude = SLocation.getLatitude();
                    longitude = SLocation.getLongitude();
                    //Location SLocation = locationManager.getLastKnownLocation(provider);
                    setLocation(longitude, latitude);
                }

            }
        });



    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        bitmap = ImagePicker.getImageFromResult(this, requestCode, resultCode, data);
        // TODO do something with the bitmap
        //Bitmap bitmap2 = Bitmap.createScaledBitmap(bitmap, 200, 200, true);
        //Log.d("aaaa",bitmap.getConfig().name());
        try {
            image.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 120, 120, false));
        } catch (Exception e) {
            e.printStackTrace();
        }

        //preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString(CommonCalls.image, getStringImage(bitmap));
        editor.apply();
    }

    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp = Bitmap.createScaledBitmap(bmp, 150, 150, false);

        bmp.compress(Bitmap.CompressFormat.JPEG, 70, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
      //  Log.d("aaa", encodedImage);
        return encodedImage;
    }

    public void onPickImage(View view) {
        // Click on image button
        ImagePicker.pickImage(this, "Select your image:");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 0: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(HomeActivity.this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {
                        // construct a new instance of SimpleLocation
                        SimpleLocation SLocation = new SimpleLocation(HomeActivity.this);

                        // if we can't access the location yet
                        if (!SLocation.hasLocationEnabled()) {
                            // ask the user to enable location access
                            SimpleLocation.openSettings(HomeActivity.this);
                        }

                        SLocation.beginUpdates();
                        latitude = SLocation.getLatitude();
                        longitude = SLocation.getLongitude();
                        setLocation(longitude, latitude);
                    }
                } else {
                }
                return;
            }
        }
    }

    private void setLocation(double longi, double latit) {

        location.setText("Longitude : " + longi + " , Latitude : " + latit);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(CommonCalls.longitude, longitude + "");
        editor.putString(CommonCalls.latitude, latitude + "");

        editor.apply();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.personal_info:

                FragmentTransaction manger = getSupportFragmentManager().beginTransaction();
                manger.replace(R.id.home_activity, new Personal_Info_Fragment()).commit();
                manger.addToBackStack(null);

                break;


            case R.id.submit_info:


                //Data insertion
                if (CommonCalls.isInternetAvailable(getApplicationContext())) {
                    if (SharedPrefrenceManager.getInstance(this).admin_Name() != null) {
                        mDiloge = new ProgressDialog(this);
                        mDiloge.setTitle("Data Upload");
                        mDiloge.setMessage("please wait..");
                        mDiloge.show();
                        insertUserDetails();
                    } else {
                        Toast.makeText(HomeActivity.this, "First Insert Data", Toast.LENGTH_LONG).show();

                    }
                } else {

                    SharedPrefrenceManager.objectList.add(setUserDetail());
                   // arrayList.add(setUserDetail());
                 //   SharedPrefrenceManager.getInstance(this).saveDataListToSharedpreference(arrayList);
                    Toast.makeText(HomeActivity.this, "Data Saved!", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.done_info:

              //  Toast.makeText(HomeActivity.this,String.valueOf(SharedPrefrenceManager.objectList.size()),Toast.LENGTH_LONG).show();
                SharedPrefrenceManager.getInstance(this).saveDataListToSharedpreference(SharedPrefrenceManager.objectList);
                Toast.makeText(HomeActivity.this, "Survey Complete", Toast.LENGTH_SHORT).show();
                break;
        }


    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//
//   }


    @Override
    public void onBackPressed() {
        if (exit) {
            finish(); // finish activity
        } else {
            Toast.makeText(this, "Press Back again to Exit.",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 2 * 1000);

        }

    }

    //INSERT user details in Database
    private void insertUserDetails() {
        Retrofit client = API_Client.getApiClient();
        ApiInterface api = client.create(ApiInterface.class);

        //      Call<Signup_Response_Model> call = api.setUserInfo(setUserDetail());
        Call<Signup_Response_Model> call = api.setUserInfo(
                SharedPrefrenceManager.getInstance(this).admin_Name(),
                preferences.getString(CommonCalls.name, null),
                preferences.getString(CommonCalls.fname, null),
                preferences.getString(CommonCalls.age, null),
                preferences.getString(CommonCalls.dob, null),
                preferences.getString(CommonCalls.gender, null),
                preferences.getString(CommonCalls.cnic_no, null),
                preferences.getString(CommonCalls.cnic_place, null),
                preferences.getString(CommonCalls.cnic_expi, null),
                preferences.getString(CommonCalls.cast, null),
                preferences.getString(CommonCalls.religon, null),
                preferences.getString(CommonCalls.contact, null),
                preferences.getString(CommonCalls.education, null),
                preferences.getString(CommonCalls.skills, null),
                preferences.getString(CommonCalls.martial_Status, null),
                preferences.getString(CommonCalls.emp_status, null),
                preferences.getString(CommonCalls.emp_will, null),
                preferences.getInt(CommonCalls.no_male_child, 0) + "",
                preferences.getInt(CommonCalls.no_female_child, 0) + "",
                preferences.getString(CommonCalls.availabilit, null),
                preferences.getString(CommonCalls.remarks, null),
                preferences.getString(CommonCalls.longitude, null),
                preferences.getString(CommonCalls.latitude, null),
                preferences.getString(CommonCalls.image, null)

        );

        call.enqueue(new Callback<Signup_Response_Model>() {
            @Override
            public void onResponse(Call<Signup_Response_Model> call, Response<Signup_Response_Model> response) {
                if (response.isSuccessful()) {
                    Signup_Response_Model signup_response_model = response.body();
                    if (signup_response_model.getSuccess() == 1) {
                        mDiloge.dismiss();
                        Toast.makeText(HomeActivity.this, "Data Added", Toast.LENGTH_LONG).show();
                    } else {
                        mDiloge.dismiss();
                        Toast.makeText(HomeActivity.this, "First Insert Data!", Toast.LENGTH_LONG).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<Signup_Response_Model> call, Throwable t) {
                Toast.makeText(HomeActivity.this, t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                Toast.makeText(HomeActivity.this, "Server Response Failure", Toast.LENGTH_LONG).show();
            }
        });


    }

    //Logging out User
    private void userLogout() {

        Retrofit client = API_Client.getApiClient();
        ApiInterface api = client.create(ApiInterface.class);
        if (CommonCalls.isInternetAvailable(this)) {
            Call<Logout_Model> call = api.UserLogout(SharedPrefrenceManager.getInstance(this).user_session_id());
            call.enqueue(new Callback<Logout_Model>() {
                @Override
                public void onResponse(Call<Logout_Model> call, Response<Logout_Model> response) {
                    if (response.isSuccessful()) {
                        Logout_Model body = response.body();
                        if (body.getSuccess() == 1) {

                            SharedPrefrenceManager.getInstance(HomeActivity.this).Logout();
                            startActivity(new Intent(HomeActivity.this, MainActivity.class));
                            finish();
                        }
                    }
                }

                @Override
                public void onFailure(Call<Logout_Model> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(), "Server Response Failure", Toast.LENGTH_LONG).show();
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "Unable to logout, Internet not available", Toast.LENGTH_LONG).show();
        }

    }

    //setting up the user model
    private Signup_Model setUserDetail() {

        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Signup_Model sign_up_model = new Signup_Model();

        sign_up_model.setAdminName(SharedPrefrenceManager.getInstance(this).admin_Name());
        sign_up_model.setName(preferences.getString(CommonCalls.name, null));
        sign_up_model.setFatherName(preferences.getString(CommonCalls.fname, null));
        sign_up_model.setAge(null); //for age
        sign_up_model.setDob(preferences.getString(CommonCalls.dob, null));
        sign_up_model.setCnic(preferences.getString(CommonCalls.cnic_no, null));
        sign_up_model.setPlaceOfIssuence(preferences.getString(CommonCalls.cnic_place, null));
        sign_up_model.setCnicExpiry(preferences.getString(CommonCalls.cnic_expi, null));
        sign_up_model.setGender(preferences.getString(CommonCalls.gender, null));
        sign_up_model.setMaritalStatus(preferences.getString(CommonCalls.martial_Status, null));
        sign_up_model.setCast(preferences.getString(CommonCalls.cast, null));
        sign_up_model.setPhonenumber(preferences.getString(CommonCalls.contact, null));
        sign_up_model.setSkill(preferences.getString(CommonCalls.skills, null));
        sign_up_model.setEducation(preferences.getString(CommonCalls.education, null));
        sign_up_model.setReligion(preferences.getString(CommonCalls.religon, null));
        sign_up_model.setEmployeeStatus(preferences.getString(CommonCalls.emp_status, null));
        sign_up_model.setWillingForEmp(preferences.getString(CommonCalls.emp_will, null));
        sign_up_model.setAvailabilty(preferences.getString(CommonCalls.availabilit, null));
        sign_up_model.setRemarks(preferences.getString(CommonCalls.remarks, null));
        sign_up_model.setLatitude(preferences.getString(CommonCalls.longitude, null));
        sign_up_model.setLongitude(preferences.getString(CommonCalls.longitude, null));
        sign_up_model.setImage(preferences.getString(CommonCalls.image, null));


        return sign_up_model;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_new_Logout:
                userLogout();
                break;
        }
        return true;
    }



}
