package Structures;

/**
 * Created by N550J on 3/14/2018.
 */

public class MoshaverRecommend {

    private boolean isActive;
    private int consultantId;
    private String purchaseId;
    private String recipeData;
    private String adviceData;
    private String id;

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getConsultantId() {
        return consultantId;
    }

    public void setConsultantId(int consultantId) {
        this.consultantId = consultantId;
    }

    public String getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(String purchaseId) {
        this.purchaseId = purchaseId;
    }

    public String getRecipeData() {
        return recipeData;
    }

    public void setRecipeData(String recipeData) {
        this.recipeData = recipeData;
    }

    public String getAdviceData() {
        return adviceData;
    }

    public void setAdviceData(String adviceData) {
        this.adviceData = adviceData;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
