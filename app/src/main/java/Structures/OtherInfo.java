package Structures;

import com.google.gson.annotations.SerializedName;

/**
 * Created by N550J on 2/26/2018.
 */
public class OtherInfo {
    @SerializedName("میزان خواب شبانه شما به طور متوسط چقدر است")
    String a;

    @SerializedName("آیا قبلا برای رژیم گرفتن اقدام کرده اید")
    String b;

    @SerializedName("به موفقیت خود در رژیم قبلی تان از 1 تا 5 چه نمره ای می دهید")
    String c;

    @SerializedName("عواملی که باعث عدم موفقیت شما در رژیم قبلی بود را ذکر کنید")
    String d;

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

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }
}
