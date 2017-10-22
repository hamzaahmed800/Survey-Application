package com.example.hami.survey.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by HAMI on 06/10/2017.
 */

public class Signup_Model  {


//    @SerializedName("id")
//    @Expose
//    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("fatherName")
    @Expose
    private String fatherName;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("dob")
    @Expose
    private String dob;
    @SerializedName("cnic")
    @Expose
    private String cnic;
    @SerializedName("cast")
    @Expose
    private String cast;
    @SerializedName("religion")
    @Expose
    private String religion;
    @SerializedName("education")
    @Expose
    private String education;
    @SerializedName("age")
    @Expose
    private String age;
    @SerializedName("no_of_children")
    @Expose
    private String noOfChildren;
    @SerializedName("AdminName")
    @Expose
    private String adminName;
    @SerializedName("maritalStatus")
    @Expose
    private String maritalStatus;
    @SerializedName("phonenumber")
    @Expose
    private String phonenumber;
    @SerializedName("place_of_issuence")
    @Expose
    private String placeOfIssuence;
    @SerializedName("cnic_expiry")
    @Expose
    private String cnicExpiry;
    @SerializedName("skill")
    @Expose
    private String skill;
    @SerializedName("willing_for_emp")
    @Expose
    private String willingForEmp;
    @SerializedName("employee_status")
    @Expose
    private String employeeStatus;
    @SerializedName("availabilty")
    @Expose
    private String availabilty;
    @SerializedName("remarks")
    @Expose
    private String remarks;

//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getCnic() {
        return cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getNoOfChildren() {
        return noOfChildren;
    }

    public void setNoOfChildren(String noOfChildren) {
        this.noOfChildren = noOfChildren;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getPlaceOfIssuence() {
        return placeOfIssuence;
    }

    public void setPlaceOfIssuence(String placeOfIssuence) {
        this.placeOfIssuence = placeOfIssuence;
    }

    public String getCnicExpiry() {
        return cnicExpiry;
    }

    public void setCnicExpiry(String cnicExpiry) {
        this.cnicExpiry = cnicExpiry;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getWillingForEmp() {
        return willingForEmp;
    }

    public void setWillingForEmp(String willingForEmp) {
        this.willingForEmp = willingForEmp;
    }

    public String getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(String employeeStatus) {
        this.employeeStatus = employeeStatus;
    }

    public String getAvailabilty() {
        return availabilty;
    }

    public void setAvailabilty(String availabilty) {
        this.availabilty = availabilty;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

}
