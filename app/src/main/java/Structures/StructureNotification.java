package Structures;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by N550J on 3/15/2018.
 */

public class StructureNotification {
    int totalCount;
    List<Notification> items;

    public StructureNotification() {
        this.items = new ArrayList<>();
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<Notification> getItems() {
        return items;
    }

    public void setItems(List<Notification> items) {
        this.items = items;
    }
}
