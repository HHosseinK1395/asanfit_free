package Structures;

/**
 * Created by N550J on 1/18/2018.
 */

public class LoginInformation {

    //Apllication
    String version;
    String releaseDate;
    String futures;

    //User
    String firstName;
    String lastName;
    String userName;
    String emailAddress;
    String mobileNumber;

    public LoginInformation(String version, String releaseDate, String futures, String firstName, String lastName, String userName, String emailAddress, String mobileNumber) {
        this.version = version;
        this.releaseDate = releaseDate;
        this.futures = futures;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.emailAddress = emailAddress;
        this.mobileNumber = mobileNumber;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getFutures() {
        return futures;
    }

    public void setFutures(String futures) {
        this.futures = futures;
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
}
