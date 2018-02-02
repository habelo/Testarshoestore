package tables;

import java.util.HashMap;
import java.util.Map;

public class CategoryPairing {

    private int id;
    private Map<Integer, Integer>categoryIDshoeID;

    public CategoryPairing(int id, int catID, int shoeID) {
        categoryIDshoeID = new HashMap<>();
        this.id = id;
        categoryIDshoeID.put(catID, shoeID);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
