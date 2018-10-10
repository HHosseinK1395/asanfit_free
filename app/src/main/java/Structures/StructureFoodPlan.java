package Structures;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by h.kardgar on 2/18/2018.
 */

public class StructureFoodPlan {

    private int totalCount;
    private List<FoodPlan> items;

    public StructureFoodPlan() {
        items = new ArrayList<>();
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<FoodPlan> getItems() {
        return items;
    }

    public void setItems(List<FoodPlan> items) {
        this.items = items;
    }


}
