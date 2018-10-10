package Structures;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by N550J on 3/14/2018.
 */

public class StructureMoshaverRecommended {
    int totalCount;
    List<MoshaverRecommend> items;

    public StructureMoshaverRecommended() {
        this.items = new ArrayList<>();
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<MoshaverRecommend> getItems() {
        return items;
    }

    public void setItems(List<MoshaverRecommend> items) {
        this.items = items;
    }
}
