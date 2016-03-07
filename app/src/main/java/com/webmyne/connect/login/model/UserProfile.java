package com.webmyne.connect.login.model;

/**
 * Created by priyasindkar on 04-03-2016.
 */
public class UserProfile {
    private String DOB, DeviceID, GCMID,Email, Gender,Mobile, Name, ProfilePic, SignupById, SignupWith;

    public UserProfile() {
        this.DOB = "";
        this.DeviceID = "";
        this.GCMID = "";this.Email = "";
        this.Gender = "";
        this.Mobile = "";
        this.Name = "";
        this.ProfilePic = "";
        this.SignupById = "";
        this.SignupWith = "";
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getDeviceID() {
        return DeviceID;
    }

    public void setDeviceID(String deviceID) {
        DeviceID = deviceID;
    }

    public String getGCMID() {
        return GCMID;
    }

    public void setGCMID(String GCMID) {
        this.GCMID = GCMID;
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

    public String getSignupById() {
        return SignupById;
    }

    public void setSignupById(String signupById) {
        SignupById = signupById;
    }

    public String getSignupWith() {
        return SignupWith;
    }

    public void setSignupWith(String signupWith) {
        SignupWith = signupWith;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "DOB='" + DOB + '\'' +
                ", DeviceID='" + DeviceID + '\'' +
                ", GCMID='" + GCMID + '\'' +
                ", Email='" + Email + '\'' +
                ", Gender='" + Gender + '\'' +
                ", Mobile='" + Mobile + '\'' +
                ", Name='" + Name + '\'' +
                ", ProfilePic='" + ProfilePic + '\'' +
                ", SignupById='" + SignupById + '\'' +
                ", SignupWith='" + SignupWith + '\'' +
                '}';
    }
}
