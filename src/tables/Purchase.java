package tables;

public class Purchase {

    private int id, costumerid;

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", costumerid=" + costumerid +
                '}';
    }

    public Purchase(int id, int costumerid) {
        this.id = id;
        this.costumerid = costumerid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCostumerid() {
        return costumerid;
    }

    public void setCostumerid(int costumerid) {
        this.costumerid = costumerid;
    }
}
