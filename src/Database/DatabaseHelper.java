package Database;

/*
* some imports for data base*/


//make sure to download this first https://repo1.maven.org/maven2/org/xerial/sqlite-jdbc/3.8.11/sqlite-jdbc-3.8.11.jar

// after download and extract -- move this file to the file of project before you going to the src

//hit ctrl + shift + alt + s (add the sqlite-jdbc-3.8.11.jar) -- apply -- ok

//try this code if the console print (connection to SQLite has been established) then it works , if not that it doesn't work

import classes.Product;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DatabaseHelper {

    private static String name; // name of database
    public static void setName(String name) {
        DatabaseHelper.name = name; // set the name
    }

    public static void createNewDatabase() {
        String url = "jdbc:sqlite:"+ name;

        try (Connection conn = DriverManager.getConnection(url)) {

            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
                createTables();
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void createTables(){

        String table3 = "CREATE TABLE categories (category TEXT PRIMARY KEY)";
        String table1 = "CREATE TABLE products (product_name TEXT PRIMARY KEY, sold_price DOUBLE NOT NULL,buy_price DOUBLE NOT NULL,quantity INTEGER NOT NULL, adding_product_date TEXT NOT NULL, category REFERENCES categories(category))";
        String table2 = "CREATE TABLE soldProducts (product_name REFERENCES products(product_name), price DOUBLE NOT NULL,quantity integer NOT NULL, selling_date TEXT NOT NULL, category TEXT NOT NULL)";
        try {
            Connection conn;
            // db parameters
            String url = "jdbc:sqlite:"+ name;
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();

            stmt.execute(table3);
            stmt.execute(table1);
            stmt.execute(table2);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String insertData(Product product){
        String sql = "INSERT INTO products(product_name,sold_price,buy_price,quantity,adding_product_date,category) VALUES(?,?,?,?,?,?)";

        try  {
            Connection conn;
            String url = "jdbc:sqlite:"+ name;
            conn = DriverManager.getConnection(url);

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, product.getName());
            pstmt.setDouble(2, product.getSoldPrice());
            pstmt.setDouble(3, product.getBuyPrice());
            pstmt.setDouble(4, product.getQuantity());
            pstmt.setString(5, product.getAddingToSystemDate());
            pstmt.setString(6, product.getCategory());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return "This Product Already Exist";
        }
        getAllData();
        return "Inserted Successfully!";
    }
    public static void getAllData(){
            String sql = "SELECT product_name,sold_price,buy_price,quantity,adding_product_date,category FROM products";
            databaseOperations.data.clear();
            try{
                Connection conn;
                // db parameters
                String url = "jdbc:sqlite:"+ name;
                conn = DriverManager.getConnection(url);
                Statement stmt  = conn.createStatement();
                ResultSet rs    = stmt.executeQuery(sql);  ////// return all rows in the table
                // loop through the result set
                while (rs.next()) {
                    System.out.println(rs.getString("product_name") +  "\t" +
                            rs.getDouble("sold_price") + "\t" +rs.getDouble("buy_price")+"\t" +
                            rs.getInt("quantity") + "\t" +rs.getString("adding_product_date") + "\t"+rs.getString("category"));

                    /*This is how we can add data and use it in the system*/
                    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    Product p = new Product(rs.getString("product_name"),rs.getDouble("sold_price"),rs.getDouble("buy_price"),rs.getInt("quantity"),formatter.parse(rs.getString("adding_product_date")),rs.getString("category"));
                    databaseOperations.data.add(p);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } catch (ParseException e) {
                e.printStackTrace();
            }
    }
    public static void deleteData(String productName){
        String sql = "DELETE FROM products WHERE product_name = ?";

        try  {
            Connection conn;
            String url = "jdbc:sqlite:"+ name;
            conn = DriverManager.getConnection(url);

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, productName);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        getAllData();
    }

    public static void getAllCategories(){
        String sql = "SELECT * FROM categories";
        databaseOperations.allCategories.clear();
        try{
            Connection conn;
            // db parameters
            String url = "jdbc:sqlite:"+ name;
            conn = DriverManager.getConnection(url);
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);  ////// return all rows in the table
            // loop through the result set
            while (rs.next()) {
              databaseOperations.allCategories.add(rs.getString("category"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateProductValues(Product product){
        String sql = "UPDATE products SET sold_price = ?,buy_price = ? , quantity = ? , category = ? WHERE product_name = ?";


        System.out.println(product.toString());
        try  {
            Connection conn;
            String url = "jdbc:sqlite:"+ name;
            conn = DriverManager.getConnection(url);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // set the corresponding param
            pstmt.setDouble(1, product.getSoldPrice());
            pstmt.setDouble(2, product.getBuyPrice());
            pstmt.setInt(3, product.getQuantity());
            pstmt.setString(4, product.getCategory());
            pstmt.setString(5, product.getName());
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        getAllData();
        getAllCategories();
    }

    public static void updateProductQuantity (Product product , int index , int newQuantity){
        String sql = "UPDATE products SET quantity = ? WHERE product_name = ?";

        try  {
            Connection conn;
            String url = "jdbc:sqlite:"+ name;
            conn = DriverManager.getConnection(url);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // set the corresponding param
            pstmt.setInt(1, newQuantity);
            pstmt.setString(2, product.getName());
            // update
            pstmt.executeUpdate();
            databaseOperations.data.get(index).setQuantity(newQuantity);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insertNewCategory(String category){
        String sql = "INSERT INTO categories(category) VALUES(?)";
        try  {
            Connection conn;
            String url = "jdbc:sqlite:"+ name;
            conn = DriverManager.getConnection(url);

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, category);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        getAllCategories();
    }

    public static void insertSoldProductDetails(String productName , int quantity , String date, double price){
        String sql = "INSERT INTO soldProducts(product_name,price,quantity,selling_date) VALUES(?,?,?,?)";

        try  {
            Connection conn;
            String url = "jdbc:sqlite:"+ name;
            conn = DriverManager.getConnection(url);

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, productName);
            pstmt.setDouble(2, price);
            pstmt.setInt(3, quantity);
            pstmt.setString(4, date);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void searchForProductsInStoke(String value){
        databaseOperations.searchProducts.clear();
        databaseOperations.searchProducts.add(" ");
        String sql = "SELECT\n" +
                "\tproduct_name\n" +
                "FROM\n" +
                "\tproducts\n" +
                "WHERE\n" +
                "\tproduct_name LIKE '%"+value+"%'";
        System.out.println(sql);
        try{
            Connection conn;
            // db parameters
            String url = "jdbc:sqlite:"+ name;
            conn = DriverManager.getConnection(url);
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);  ////// return all rows in the table
            // loop through the result set
            while (rs.next()) {
                /*This is how we can add data and use it in the system*/
                databaseOperations.searchProducts.add(rs.getString("product_name"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}