package Structures;

/**
 * Created by N550J on 3/15/2018.
 */

public class MoshaverAdviceData {

    String[] adviceData;
    String id;

    public MoshaverAdviceData() {
        adviceData = new String[5];
    }

    public String[] getAdviceData() {
        return adviceData;
    }

    public void setAdviceData(String[] adviceData) {
        this.adviceData = adviceData;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
