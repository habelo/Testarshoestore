package tables;

import java.util.ArrayList;
import java.util.List;

public class Costumer {

    private int id;
    private String firstname, lastname;
    private List<Purchase> costumerPurchases;

    public Costumer(int id, String firstname, String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        costumerPurchases = new ArrayList<>();
    }

@Override
    public String toString() {
        return "Costumer: "  + id +
                "\t" + firstname +" "+ lastname;
        //ville ha ID så att man kunde välja vem man va, enkelt att ta bort om fel
    }

    public List<Purchase> getCostumerPurchases() {
        return costumerPurchases;
    }

    public void setCostumerPurchase(Purchase p) {
        costumerPurchases.add(p);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
