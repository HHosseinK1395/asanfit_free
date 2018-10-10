package Structures;

import com.google.gson.annotations.SerializedName;

/**
 * Created by N550J on 2/26/2018.
 */

public class AdditionalInfo {

    @SerializedName("city")
    int city;

    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }
}
