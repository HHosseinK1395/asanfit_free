package Structures;

/**
 * Created by N550J on 3/1/2018.
 */

public class StructureWeightAndDoreKamar {
    String registerDate;
    public StructureDashbord dailyrecorddata;


    public StructureWeightAndDoreKamar() {
        if(this.dailyrecorddata == null)
            dailyrecorddata = new StructureDashbord();
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public StructureDashbord getDailyrecorddata() {
        return dailyrecorddata;
    }

    public void setDailyrecorddata(StructureDashbord dailyrecorddata) {
        this.dailyrecorddata = dailyrecorddata;
    }
}
