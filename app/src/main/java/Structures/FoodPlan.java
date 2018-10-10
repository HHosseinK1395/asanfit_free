package Structures;

/**
 * Created by N550J on 3/4/2018.
 */

public class FoodPlan {
    private String assignmentId;
    private String dietData;
    private String validFromTime;
    private String validToTime;
    private String id;

    public String getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(String assignmentId) {
        this.assignmentId = assignmentId;
    }

    public String getDietData() {
        return dietData;
    }

    public void setDietData(String dietData) {
        this.dietData = dietData;
    }

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
