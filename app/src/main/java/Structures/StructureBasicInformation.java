package Structures;

/**
 * Created by N550J on 2/15/2018.
 */

public class StructureBasicInformation {
    String weight;
    String height;
    String age;
    int gender;

    public StructureBasicInformation() {
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public int getMaleOrFemale() {
        return gender;
    }

    public void setMaleOrFemale(int maleOrFemale) {
        gender = maleOrFemale;
    }

}
