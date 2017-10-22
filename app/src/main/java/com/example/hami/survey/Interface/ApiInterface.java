package com.example.hami.survey.Interface;

import com.example.hami.survey.ConnectionAPIs.ConnectionURLS;
import com.example.hami.survey.Models.Logout_Model;
import com.example.hami.survey.Models.SignIn_Model;
import com.example.hami.survey.Models.Signup_Response_Model;

import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by HAMI on 06/10/2017.
 */

public interface ApiInterface {

   // @Headers("Content-Type: application/json")
    @POST(ConnectionURLS.main_Package_api + ConnectionURLS.webAPI_view_records)
    Call<SignIn_Model> getUserInfo(@Query("userName") String email, @Query("password")String password);

//    //@Headers("Content-Type: application/json")
//    @POST(ConnectionURLS.main_Package_api + ConnectionURLS.webAPI_add_records)
//    Call<Signup_Response_Model> setUserInfo(@Body Signup_Model signup_model);

    @Headers("Content-Type: application/json")
    @POST(ConnectionURLS.main_Package_api + ConnectionURLS.webAPI_add_records)
    Call<Signup_Response_Model> setUserInfo(@Query("AdminName") String admin_name,
                                            @Query("name") String name,
                                            @Query("fatherName") String fathername,
                                            @Query("age") String age,
                                            @Query("dob") String dob,
                                            @Query("gender")String gender,
                                            @Query("cnic") String cnic,
                                            @Query("place_of_issuence") String cnic_p,
                                            @Query("cnic_expiry") String cinc_ex,
                                            @Query("cast") String cast,
                                            @Query("religion") String religon,
                                            @Query("phonenumber") String phone,
                                            @Query("education") String education,
                                            @Query("skill") String skill,
                                            @Query("maritalStatus") String martial,
                                            @Query("employee_status") String emp_status,
                                            @Query("willing_for_emp") String wll_emp,
                                            @Query("no_of_male_children") String no_male_children,
                                            @Query("no_of_female_children") String no_female_children,
                                            @Query("availabilty") String avail,
                                            @Query("remarks") String remark,
                                            @Query("longitude") String longitude,
                                            @Query("latitude") String latitude,
                                            @Query("image") String image
                                            );

    @POST(ConnectionURLS.main_Package_api + ConnectionURLS.webAPI_logout)
    Call<Logout_Model> UserLogout(@Query("response") String session_id);
}
