package classes;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Product {
    String name;
    double soldPrice;
    double buyPrice;
    int quantity;
    String category;
    String addingToSystemDate;// date of the process /*ده عشان يسادعنا نخزن التواريخ اللي اتباعت فيها المنتجات وهتلاقيني حدثت الداتا كمان راجع عليها برضه*/
    String sellingDate;// date of the process /*ده عشان يسادعنا نخزن التواريخ اللي اتباعت فيها المنتجات وهتلاقيني حدثت الداتا كمان راجع عليها برضه*/
    private static final DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); // format () // ده فورمات بس لشكل التاريخ انا خليته يطبعها فا ابقا شوف شكل التاريخ في الكونسول

    public Product(String name , double soldPrice , double buyPrice , int quantity , Date addingToSystemDate,String category){
        this.name = name;
        this.soldPrice = soldPrice;
        this.buyPrice = buyPrice;
        this.quantity = quantity;
        this.addingToSystemDate = formatter.format(addingToSystemDate);
        this.sellingDate = "";
        this.category = category;
    }

    public Product(){
        this.name = "";
        this.soldPrice = 0.0;
        this.buyPrice = 0.0;
        this.quantity = 0;
        this.addingToSystemDate = formatter.format(new Date());
        this.sellingDate = "";
        this.category = "";
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public void setAddingToSystemDate(String addingToSystemDate) {
        this.addingToSystemDate = addingToSystemDate;
    }

    public String getAddingToSystemDate() {
        return addingToSystemDate;
    }

    public void setSellingDate(String sellingDate) {
        this.sellingDate = sellingDate;
    }

    public String getSellingDate() {
        return sellingDate;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", soldPrice=" + soldPrice +
                ", buyPrice=" + buyPrice +
                ", quantity=" + quantity +
                ", category='" + category + '\'' +
                ", addingToSystemDate='" + addingToSystemDate + '\'' +
                ", sellingDate='" + sellingDate + '\'' +
                '}';
    }
}
