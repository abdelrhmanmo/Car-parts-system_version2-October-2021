package classes;

public class Product {
    String name;
    double soldPrice;
    double buyPrice;
    int quantity;

    public Product(String name , double soldPrice , double buyPrice , int quantity){
        this.name = name;
        this.soldPrice = soldPrice;
        this.buyPrice = buyPrice;
        this.quantity = quantity;
    }

    public Product(){
        this.name = "";
        this.soldPrice = 0.0;
        this.buyPrice = 0.0;
        this.quantity = 0;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setSoldPrice(double soldPrice) {
        this.soldPrice = soldPrice;
    }

    public String getName() {
        return name;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public double getSoldPrice() {
        return soldPrice;
    }

    public int getQuantity() {
        return quantity;
    }
}
