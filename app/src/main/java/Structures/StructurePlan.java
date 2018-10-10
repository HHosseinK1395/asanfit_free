package Structures;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by N550J on 2/16/2018.
 */

public class StructurePlan {
    int totalCount;
    List<Plans> items;

    public StructurePlan() {
        this.items = new ArrayList<Plans>();
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<Plans> getItems() {
        return items;
    }

    public void setItems(List<Plans> items) {
        this.items = items;
    }
}
