package tables;

public class Shoe {

    private int id, size, price, stockAmount;
    private String color, brand;

    public Shoe(int id, int size, int price, String color, String brand, int stockAmount) {
        this.id = id;
        this.size = size;
        this.price = price;
        this.color = color;
        this.brand = brand;
        this.stockAmount = stockAmount;
    }
    @Override
    public String toString() {
        return ""+ id+". "+ "Shoe-size:  "+ size+" costs: "+
                price+" "+color+" "+ brand + " amount in stock: "+ stockAmount;
    }


    public int getStockAmount() {
        return stockAmount;
    }

    public void setStockAmount(int stockAmount) {
        this.stockAmount = stockAmount;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
