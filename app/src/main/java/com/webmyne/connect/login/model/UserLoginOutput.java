package com.webmyne.connect.login.model;

/**
 * Created by priyasindkar on 07-03-2016.
 */
public class UserLoginOutput {
    public String Address, DOB, Email, Gender, Industry, Mobile, Name, ProfilePic, ResponseCode, ResponseMessage, UserReferCode, ZipCode;
    public boolean IsActive, IsActiveLead, IsKYCComplete, IsNewAccount;
    public int UserID;
    public int WalletBalance;

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

    public String getProfilePic() {
        return ProfilePic;
    }

    public void setProfilePic(String profilePic) {
        ProfilePic = profilePic;
    }

    public String getResponseCode() {
        return ResponseCode;
    }

    public void setResponseCode(String responseCode) {
        ResponseCode = responseCode;
    }

    public String getResponseMessage() {
        return ResponseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        ResponseMessage = responseMessage;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getUserReferCode() {
        return UserReferCode;
    }

    public void setUserReferCode(String userReferCode) {
        UserReferCode = userReferCode;
    }

    public int getWalletBalance() {
        return WalletBalance;
    }

    public void setWalletBalance(int walletBalance) {
        WalletBalance = walletBalance;
    }

    public String getZipCode() {
        return ZipCode;
    }

    public void setZipCode(String zipCode) {
        ZipCode = zipCode;
    }

    public boolean isActive() {
        return IsActive;
    }

    public void setActive(boolean active) {
        IsActive = active;
    }

    public boolean isActiveLead() {
        return IsActiveLead;
    }

    public void setActiveLead(boolean activeLead) {
        IsActiveLead = activeLead;
    }

    public boolean isKYCComplete() {
        return IsKYCComplete;
    }

    public void setKYCComplete(boolean KYCComplete) {
        IsKYCComplete = KYCComplete;
    }

    public boolean isNewAccount() {
        return IsNewAccount;
    }

    public void setNewAccount(boolean newAccount) {
        IsNewAccount = newAccount;
    }

    @Override
    public String toString() {
        return "UserLoginOutput{" +
                "Address='" + Address + '\'' +
                ", DOB='" + DOB + '\'' +
                ", Email='" + Email + '\'' +
                ", Industry='" + Industry + '\'' +
                ", Mobile='" + Mobile + '\'' +
                ", Name='" + Name + '\'' +
                ", ProfilePic='" + ProfilePic + '\'' +
                ", ResponseCode='" + ResponseCode + '\'' +
                ", ResponseMessage='" + ResponseMessage + '\'' +
                ", UserID='" + UserID + '\'' +
                ", UserReferCode='" + UserReferCode + '\'' +
                ", WalletBalance='" + WalletBalance + '\'' +
                ", ZipCode='" + ZipCode + '\'' +
                ", IsActive=" + IsActive +
                ", IsActiveLead=" + IsActiveLead +
                ", IsKYCComplete=" + IsKYCComplete +
                ", IsNewAccount=" + IsNewAccount +
                '}';
    }
}
