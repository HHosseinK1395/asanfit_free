package Structures;

import com.google.gson.annotations.SerializedName;

/**
 * Created by N550J on 2/26/2018.
 */

public class ActivityInfo {

    @SerializedName("میزان فعالیت بدنی شما چقدر است")
    String a;

    @SerializedName("به چه ورزش هایی علاقه دارید")
    String b;

    @SerializedName("آیا مشکلی برای انجام ورزش دارید")
    String c;

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }
}
