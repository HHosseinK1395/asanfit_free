package Structures;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by N550J on 2/16/2018.
 */

public class StructureMoshaverProfile {
    int totalCount;
    List<Moshaver> items;


    public StructureMoshaverProfile() {
        this.items = new ArrayList<>();
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<Moshaver> getItems() {
        return items;
    }

    public void setItems(List<Moshaver> items) {
        this.items = items;
    }
}
