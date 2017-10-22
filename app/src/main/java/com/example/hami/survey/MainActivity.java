package com.example.hami.survey;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hami.survey.Application.SharedPrefrenceManager;
import com.example.hami.survey.Globals.CommonCalls;
import com.example.hami.survey.Interface.ApiInterface;
import com.example.hami.survey.Models.SignIn_Model;
import com.example.hami.survey.Network.API_Client;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar mToolbar;
    Button login;
    EditText email, password;
    ProgressDialog mDilouge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (SharedPrefrenceManager.getInstance(this).isUserLoggedIn()) {
            startActivity(new Intent(MainActivity.this, HomeActivity.class));
            finish();
            return;
        }
        initView();
        login = (Button) findViewById(R.id.loginButton);
        login.setOnClickListener(this);


    }

    public void initView() {

        mToolbar = (Toolbar) findViewById(R.id.main_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Login");
        mToolbar.setTitleTextColor(Color.WHITE);

        email = (EditText) findViewById(R.id.getEmail);
        password = (EditText) findViewById(R.id.getPass);


        mDilouge = new ProgressDialog(this);
        mDilouge.setTitle("Logining");
        mDilouge.setMessage("Signing in progress...");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.loginButton:
                mDilouge.show();
                checkUser();
                //startActivity(new Intent(MainActivity.this, HomeActivity.class));
                //finish();
                break;
        }
    }

    private void checkUser() {

        String user_email = email.getText().toString();
        String user_pass = password.getText().toString();

        if (CommonCalls.isInternetAvailable(getApplicationContext())) {

            if (!TextUtils.isEmpty(user_email) && !TextUtils.isEmpty(user_pass)) {

                Retrofit client = API_Client.getApiClient();
                ApiInterface api = client.create(ApiInterface.class);

                Call<SignIn_Model> call = api.getUserInfo(user_email, user_pass);
                call.enqueue(new Callback<SignIn_Model>() {
                    @Override
                    public void onResponse(Call<SignIn_Model> call, Response<SignIn_Model> response) {
                        if (!response.isSuccessful()) {

                            Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG).show();

                        } else {

                            SignIn_Model sign_in = response.body();
                            if (sign_in.getSuccess() == 1) {
                                mDilouge.dismiss();
                                SharedPrefrenceManager.getInstance(getApplicationContext())
                                        .userLogin(sign_in.getUserName(), sign_in.getSessionResponse());
                              //  Toast.makeText(MainActivity.this,sign_in.getUserName()+ sign_in.getSessionResponse(),Toast.LENGTH_LONG).show();
                                startActivity(new Intent(MainActivity.this, HomeActivity.class));
                                finish();

                            } else {
                                mDilouge.dismiss();
                                Toast.makeText(getApplicationContext(), "Un-authorized User!", Toast.LENGTH_LONG).show();
                            }


                        }
                    }

                    @Override
                    public void onFailure(Call<SignIn_Model> call, Throwable t) {
                        Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                        Toast.makeText(MainActivity.this, "Server Response Failure", Toast.LENGTH_LONG).show();
                    }
                });


//        startActivity(new Intent(MainActivity.this,HomeActivity.class));
//        finish();

            } else {
                mDilouge.dismiss();
                Toast.makeText(getApplicationContext(), "username or password required", Toast.LENGTH_LONG).show();
            }

        } else {
            mDilouge.dismiss();
            Toast.makeText(getApplicationContext(), "Internet unavialable!", Toast.LENGTH_LONG).show();
        }


    }
}
