package Structures;

/**
 * Created by N550J on 3/1/2018.
 */

public class StructureWeghtDailyRecord {
    private int customerId;
    private String registerTime;
    private String dailyRecordData;
    private String id;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public String getDailyRecordData() {
        return dailyRecordData;
    }

    public void setDailyRecordData(String dailyRecordData) {
        this.dailyRecordData = dailyRecordData;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
