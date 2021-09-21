package Database;

import classes.Product;

import java.util.ArrayList;
import java.util.Date;

public class databaseOperations {
    /*The array list that will get data from database (please check getAllData function in DatabaseHelper Class)*/

    public static ArrayList<Product> data = new ArrayList<Product>();

    public static ArrayList<String>searchProducts = new ArrayList<String>();

    public databaseOperations(){

        //try to run after deleting system.db
        DatabaseHelper.setName("system.db"); // db is extension for database file
        DatabaseHelper.createNewDatabase();
        DatabaseHelper.insertData(new Product("تيست",1000.0,1200.0,50,new Date()));
        DatabaseHelper.insertData(new Product("Test1",1000.0,1200.0,30,new Date()));
        DatabaseHelper.insertData(new Product("Test2",1000.0,1200.0,30,new Date()));
        DatabaseHelper.insertData(new Product("Test3",1000.0,1200.0,30,new Date()));
        DatabaseHelper.insertData(new Product("Test4",1000.0,1200.0,30,new Date()));
        DatabaseHelper.insertData(new Product("Test5",1000.0,1200.0,30,new Date()));
        DatabaseHelper.insertData(new Product("Test6",1000.0,1200.0,30,new Date()));
        DatabaseHelper.insertData(new Product("Test7",1000.0,1200.0,30,new Date()));
        DatabaseHelper.insertData(new Product("Test8",1000.0,1200.0,30,new Date()));
        DatabaseHelper.insertData(new Product("Test9",1000.0,1200.0,30,new Date()));
        DatabaseHelper.insertData(new Product("Test10",1000.0,1200.0,30,new Date()));

        DatabaseHelper.deleteData("Test10");
        DatabaseHelper.getAllData();

    }
    public static int search(String productName){

        for (int i = 0; i < data.toArray().length; i++){
            if(data.get(i).getName().equals(productName)){
                return i;
            }
        }

        return -1; // عشان لو في product فعلا في اول index هيعملك مشكله هناك
    }
}
