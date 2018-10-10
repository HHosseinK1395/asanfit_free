package Structures;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by N550J on 3/13/2018.
 */

public class StructurePurchase {
    int totalCount;
    List<Purchase> items;

    public StructurePurchase() {
        this.items = new ArrayList<>();
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<Purchase> getItems() {
        return items;
    }

    public void setItems(List<Purchase> items) {
        this.items = items;
    }
}
