package Structures;

import com.google.gson.annotations.SerializedName;

/**
 * Created by N550J on 2/26/2018.
 */
public class BasicInfo {

    @SerializedName("weight")
    int weight;
    @SerializedName("height")
    int height;
    @SerializedName("age")
    int age;
    @SerializedName("wrist")
    int wrist;//Dooore moch
    @SerializedName("waist")
    int waist;// Doore Kamar
    @SerializedName("gender")
    int gender;
    @SerializedName("bmi")
    float bmi;
    @SerializedName("birthDate")
    String birthDate;

    public BasicInfo() {
        //this.basicInfo = new BasicInfo();
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWrist() {
        return wrist;
    }

    public void setWrist(int wrist) {
        this.wrist = wrist;
    }

    public int getWaist() {
        return waist;
    }

    public void setWaist(int waist) {
        this.waist = waist;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public float getBmi() {
        return bmi;
    }

    public void setBmi(float bmi) {
        this.bmi = bmi;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }



    /*
    {"BasicInfo": { "Age": 28, "Height": 150, "Weight": 70, "Wrist": 10, "Waist": 60, "BirthDate": "1990/1/1", "BMI":27.4 },
     "GoalInfo":{ "TargetWeight": 65, "TargetDuration" : 45},
     "ActivityInfo": { "میزان فعالیت بدنی": 123 },
     "MedicalInfo": { "a": "b", "c": "d", "e": "f"},
     "FoodHabitInfo": { "a": "b" },
     "OtherInfo": { "g": "h"}}
     */
}
