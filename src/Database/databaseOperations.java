package Database;

import classes.Product;

import java.util.ArrayList;

public class databaseOperations {
    /*The array list that will get data from database (please check getAllData function in DatabaseHelper Class)*/
    public static ArrayList<Product> data = new ArrayList<>();
    public databaseOperations(){
        //try to run after deleting system.db
        DatabaseHelper.setName("system.db"); // db is extension for database file
        DatabaseHelper.createNewDatabase();
        DatabaseHelper.insertData(new Product("تيست",1000.0,1200.0,50));
        DatabaseHelper.insertData(new Product("Test1",1000.0,1200.0,30));
        DatabaseHelper.insertData(new Product("Test2",1000.0,1200.0,30));
        //DatabaseHelper.deleteData("Test2");
        DatabaseHelper.getAllData();
        System.out.println(data.get(0).getQuantity()); // expected //30
         /*At the first time the table will created and data will be added successfully
          In Second time the table will not be created again and the console will give you a message that the tables is already exists
          Try to add another data and test it (hope it work successfully)
          **/
    }
    public static int search(String productName){

        for (int i = 0; i < DatabaseHelper.getNumberOfRows(); i++){
            if(data.get(i).getName().equals(productName)){
                return i;
            }
        }

        return 0;
    }
}
