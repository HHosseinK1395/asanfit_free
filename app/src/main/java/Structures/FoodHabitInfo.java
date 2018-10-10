package Structures;

import com.google.gson.annotations.SerializedName;

/**
 * Created by N550J on 2/26/2018.
 */
public class FoodHabitInfo {

    @SerializedName("میزان مصرف صبحانه شما چقدر است")
    public String b;

    @SerializedName("میزان مصرف ناهار شما چقدر است")
    public String c;

    @SerializedName("میزان مصرف شام شما چقدر است")
    public String d;

    @SerializedName("میزان مصرف میان وعده شما چقدر است")
    public String e;

    @SerializedName("آیا عادت به ریزه خواری دارید")
    public String f;

    @SerializedName("غذاهای مورد علاقه شما کدامند")
    public String g;

    @SerializedName("کدام غذاها را دوست ندارید یا به آن حساسیت دارید")
    public String h;

    @SerializedName("معمولا در روز چند عدد میوه مصرف می کنید")
    public String i;

    @SerializedName("معمولا چقدر سبزیجات مصرف می کنید")
    public String j;

    @SerializedName("معمولا در روز چقدر برنج مصرف می کنید")
    public String k;

    @SerializedName("معمولا چقدر شیر و انواع لبنیات مصرف می کنید")
    public String l;

    @SerializedName("معمولا چقدر انواع گوشت و مرغ و ماهی مصرف می کنید")
    public String m;

    @SerializedName("معمولا چقدر فست فود یا غذاهای رستورانی مصرف می کنید")
    public String n;

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

    public String getF() {
        return f;
    }

    public void setF(String f) {
        this.f = f;
    }

    public String getG() {
        return g;
    }

    public void setG(String g) {
        this.g = g;
    }

    public String getH() {
        return h;
    }

    public void setH(String h) {
        this.h = h;
    }

    public String getI() {
        return i;
    }

    public void setI(String i) {
        this.i = i;
    }

    public String getJ() {
        return j;
    }

    public void setJ(String j) {
        this.j = j;
    }

    public String getK() {
        return k;
    }

    public void setK(String k) {
        this.k = k;
    }

    public String getL() {
        return l;
    }

    public void setL(String l) {
        this.l = l;
    }

    public String getM() {
        return m;
    }

    public void setM(String m) {
        this.m = m;
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }
}
