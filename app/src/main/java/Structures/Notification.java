package Structures;

/**
 * Created by N550J on 3/15/2018.
 */

public class Notification {
    String validFromTime;
    String validToTime;
    String tipMessage;
    boolean isActive;
    String id;


    public String getValidFromTime() {
        return validFromTime;
    }

    public void setValidFromTime(String validFromTime) {
        this.validFromTime = validFromTime;
    }

    public String getValidToTime() {
        return validToTime;
    }

    public void setValidToTime(String validToTime) {
        this.validToTime = validToTime;
    }

    public String getTipMessage() {
        return tipMessage;
    }

    public void setTipMessage(String tipMessage) {
        this.tipMessage = tipMessage;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
