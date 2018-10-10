package Structures;

import com.google.gson.annotations.SerializedName;

/**
 * Created by N550J on 2/26/2018.
 */
public class GoalInfo {

    @SerializedName("TargetWeight")
    int TargetWeight;

    @SerializedName("TargetDuration")
    int TargetDuration;


    public int getTargetWeight() {
        return TargetWeight;
    }

    public void setTargetWeight(int targetWeight) {
        TargetWeight = targetWeight;
    }

    public int getTargetDuration() {
        return TargetDuration;
    }

    public void setTargetDuration(int targetDuration) {
        TargetDuration = targetDuration;
    }

    public class ActivityInfo {

    }
}
