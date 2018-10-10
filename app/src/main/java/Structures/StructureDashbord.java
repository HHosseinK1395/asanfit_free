package Structures;

/**
 * Created by N550J on 2/22/2018.
 */

public class StructureDashbord {
    private String weight;
    private String height;
    private String idealWeight;
    private String overweight;
    private String currentWeight;
    private String doreKamar;
    private String kaheshVaznTaKonoon;

    public String getKaheshVaznTaKonoon() {
        return kaheshVaznTaKonoon;
    }

    public void setKaheshVaznTaKonoon(String kaheshVaznTaKonoon) {
        kaheshVaznTaKonoon = kaheshVaznTaKonoon;
    }

    public String getDoreKamar() {
        return doreKamar;
    }

    public void setDoreKamar(String doreKamar) {
        doreKamar = doreKamar;
    }

    public String getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(String currentWeight) {
        this.currentWeight = currentWeight;
    }

    public String getOverweight() {
        return overweight;
    }

    public void setOverweight(String overweight) {
        this.overweight = overweight;
    }

    public String getShakhes() {
        return Shakhes;
    }

    public void setShakhes(String shakhes) {
        Shakhes = shakhes;
    }

    String Shakhes;

    public String getIdealWeight() {
        return idealWeight;
    }

    public void setIdealWeight(String idealWeight) {
        this.idealWeight = idealWeight;
    }

    public StructureDashbord() {
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
}
