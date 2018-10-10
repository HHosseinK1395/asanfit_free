package Structures;

/**
 * Created by N550J on 3/16/2018.
 */

public class StructureWeightForUpdate {
    StructureWeightBasicInfo basicInfo;

    public StructureWeightForUpdate() {
        this.basicInfo = new StructureWeightBasicInfo();
    }

    public StructureWeightBasicInfo getBasicInfo() {
        return basicInfo;
    }

    public void setBasicInfo(StructureWeightBasicInfo basicInfo) {
        this.basicInfo = basicInfo;
    }
}
