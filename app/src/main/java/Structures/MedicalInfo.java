package Structures;

import com.google.gson.annotations.SerializedName;
/**
 * Created by N550J on 2/26/2018.
 */
public class MedicalInfo {
    @SerializedName("آیا به بیماری خاصی مبتلا هستید")
    String a;

    @SerializedName("آیا دارو یا مکمل خاصی مصرف میکنید")
    String b;

    @SerializedName("آیا اخیرا سابقه عمل جراحی داشته اید")
    String c;

    @SerializedName("آیا در پارامتر های آزمایشگاهی زیر دارای مشکل هستید")
    String d;

    @SerializedName("آیا شرایط فیزیولوژیکی خاصی دارید نظیر بارداری یا شیردهی")
    String e;


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

    public String getE() {
        return e;
    }

    public void setE(String e) {
        this.e = e;
    }
}
