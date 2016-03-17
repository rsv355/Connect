package com.webmyne.connect.user.model;

/**
 * Created by priyasindkar on 07-03-2016.
 */
public class UserUpdateInput {
    public String Address, DOB, Email, Gender, Industry, Mobile, Name,  ZipCode, ReferCode;
    public long UserID;

    public UserUpdateInput() {
        this.Address = "";
        this.DOB = "";
        this.Email = "";
        this.Gender = "";
        this.Industry = "";
        this.Mobile = "";
        this.Name = "";
        this.ZipCode = "";
        this.ReferCode = "";
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getIndustry() {
        return Industry;
    }

    public void setIndustry(String industry) {
        Industry = industry;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }


    public String getZipCode() {
        return ZipCode;
    }

    public void setZipCode(String zipCode) {
        ZipCode = zipCode;
    }

    public String getReferCode() {
        return ReferCode;
    }

    public void setReferCode(String referCode) {
        ReferCode = referCode;
    }

    public long getUserID() {
        return UserID;
    }

    public void setUserID(long userID) {
        UserID = userID;
    }


    @Override
    public String toString() {
        return "UserProfile{" +
                "Address:'" + Address + '\'' +
                ", DOB:'" + DOB + '\'' +
                ", UserID:'" + UserID + '\'' +
                ", Industry:'" + Industry + '\'' +
                ", Email:'" + Email + '\'' +
                ", Gender:'" + Gender + '\'' +
                ", Mobile:'" + Mobile + '\'' +
                ", Name:'" + Name + '\'' +
                ", ZipCode:'" + ZipCode + '\'' +
                ", ReferCode:'" + ReferCode + '\'' +
                '}';
    }
}