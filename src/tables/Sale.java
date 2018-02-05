package tables;

public class Sale {

    private int id, shoeid, orderid, regionid;

    public Sale(int id, int shoeid, int orderid, int regionid) {
        this.id = id;
        this.shoeid = shoeid;
        this.orderid = orderid;
        this.regionid = regionid;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", shoeid=" + shoeid +
                ", orderid=" + orderid +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getShoeid() {
        return shoeid;
    }

    public void setShoeid(int shoeid) {
        this.shoeid = shoeid;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }
}
