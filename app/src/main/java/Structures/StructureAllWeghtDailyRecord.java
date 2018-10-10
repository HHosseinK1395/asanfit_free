package Structures;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by N550J on 3/1/2018.
 */

public class StructureAllWeghtDailyRecord {
    int totalCount;
    List<StructureWeghtDailyRecord> items;

    public StructureAllWeghtDailyRecord() {
        this.items = new ArrayList<>();
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<StructureWeghtDailyRecord> getItems() {
        return items;
    }

    public void setItems(List<StructureWeghtDailyRecord> items) {
        this.items = items;
    }
}
