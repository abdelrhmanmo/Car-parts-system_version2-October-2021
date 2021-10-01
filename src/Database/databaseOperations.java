package Database;

import classes.Product;

import java.util.ArrayList;
import java.util.Date;

public class databaseOperations {
    /*The array list that will get data from database (please check getAllData function in DatabaseHelper Class)*/

    public static ArrayList<Product> data = new ArrayList<Product>();

    public static ArrayList<String>searchProducts = new ArrayList<String>();

    public static ArrayList<String> allCategories = new ArrayList<String>();

    public static ArrayList<Product> listOfProductsOfASpecificCategory = new ArrayList<>();

    public static  ArrayList<Product> salesData = new ArrayList<>();

    public databaseOperations(){

        //try to run after deleting system.db
        DatabaseHelper.setName("system.db"); // db is extension for database file
        DatabaseHelper.createNewDatabase();
/*      DatabaseHelper.insertData(new Product("تيست",1000.0,1200.0,50,new Date(),"Cat1"));
        DatabaseHelper.insertData(new Product("Test1",1000.0,1200.0,30,new Date(),"Cat2"));
        DatabaseHelper.insertData(new Product("Test2",1000.0,1200.0,30,new Date(), "Cat1"));*/
        DatabaseHelper.getAllData();
        DatabaseHelper.getAllCategories();
        DatabaseHelper.getAllSalesData();
    }
    public static int search(String productName , String category){

        for (int i = 0; i < data.toArray().length; i++){
            if(data.get(i).getName().equals(productName) && data.get(i).getCategory().equals(category)){
                return i;
            }
        }

        return -1; // عشان لو في product فعلا في اول index هيعملك مشكله هناك
    }

    public static String searchForCategory(String category) {
        for (int i = 0; i < allCategories.toArray().length; i++) {
            if (allCategories.get(i).equals(category)) {
                return "This Category Already Exist";
            }
        }
        return "";
    }

    public static int numberOfProductsFromASpecificCategories(){
        return listOfProductsOfASpecificCategory.toArray().length;
    }
}
