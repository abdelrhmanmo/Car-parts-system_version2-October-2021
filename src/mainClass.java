public class mainClass {
    public static void main(String[] args) {

        //try to run after deleting system.db
        DatabaseHelper.setName("system.db"); // db is extension for database file
        DatabaseHelper.createNewDatabase();
        DatabaseHelper.insertData(new Product("Test",1000.0,1200.0,30));
        DatabaseHelper.getAllData();

         /*At the first time the table will created and data will be added successfully
          In Second time the table will not be created again and the console will give you a message that the tables is already exsist
          Try to add another data and test it (hope it work successfully)
          **/
        
        new Menu();


    }
}
