package com.example.hami.survey.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by HAMI on 06/10/2017.
 */

public class SignIn_Model  {


        @SerializedName("success")
        @Expose
        private Integer success;
        @SerializedName("message")
        @Expose
        private String message;
        @SerializedName("session_response")
        @Expose
        private String sessionResponse;
        @SerializedName("userName")
        @Expose
        private String userName;

        public Integer getSuccess() {
            return success;
        }

        public void setSuccess(Integer success) {
            this.success = success;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getSessionResponse() {
            return sessionResponse;
        }

        public void setSessionResponse(String sessionResponse) {
            this.sessionResponse = sessionResponse;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

}
