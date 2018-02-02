package tables;

import java.util.ArrayList;
import java.util.List;

public class PurchaseOrder {

    private int id, purchaseid;
    private String purchaseDate;
    private boolean dispatched;

    List<Sale> allSalesInOrder= new ArrayList<>();

    public PurchaseOrder(int id, int purchaseid, String purchaseDate) {
        this.id = id;
        this.purchaseid = purchaseid;
        this.purchaseDate = purchaseDate;
        this.dispatched = false;
    }

    public boolean isDispatched() {
        return dispatched;
    }

    public void setDispatched(boolean dispatched) {
        this.dispatched = dispatched;
    }

    public List<Sale> getallSalesInOrder() {
        return allSalesInOrder;
    }

    public void setOrderSale(Sale s) {
        this.allSalesInOrder.add(s);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPurchaseid() {
        return purchaseid;
    }

    public void setPurchaseid(int purchaseid) {
        this.purchaseid = purchaseid;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public void printOut() {
        System.out.println("PurchaseOrder{" +
                "id=" + id +
                ", purchaseid=" + purchaseid +
                ", purchaseDate='" + purchaseDate + '\'' +
                ", allSalesInOrder=" + allSalesInOrder.toString() +
                '}');
    }
}
