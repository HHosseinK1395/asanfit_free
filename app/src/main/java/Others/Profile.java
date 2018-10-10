package Others;

import org.json.JSONStringer;

/**
 * Created by N550J on 1/18/2018.
 */

public class Profile {
    String firstName;
    String lastName;
    String userName;
    String emailAddress;
    String mobileNumber;
    String password;
    JSONStringer profileData;
    String captchaResponse;

    public Profile(String firstName, String lastName, String userName, String emailAddress, String mobileNumber, String password, JSONStringer profileData, String captchaResponse) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.emailAddress = emailAddress;
        this.mobileNumber = mobileNumber;
        this.password = password;
        this.profileData = profileData;
        this.captchaResponse = captchaResponse;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public JSONStringer getProfileData() {
        return profileData;
    }

    public void setProfileData(JSONStringer profileData) {
        this.profileData = profileData;
    }

    public String getCaptchaResponse() {
        return captchaResponse;
    }

    public void setCaptchaResponse(String captchaResponse) {
        this.captchaResponse = captchaResponse;
    }
}
