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
import java.util.ArrayList;

public class DatabaseHelper {

    public static String name; // name of database
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
        String table1 = "CREATE TABLE products (product_name TEXT, sold_price DOUBLE NOT NULL,buy_price DOUBLE NOT NULL,quantity INTEGER NOT NULL, adding_product_date TEXT NOT NULL, category REFERENCES categories(category), PRIMARY KEY(product_name,category))";
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
            String sql = "SELECT product_name,sold_price,buy_price,quantity,adding_product_date,category FROM products WHERE quantity > 0";
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

    public static void updateProductValues(Product product , String oldName){
        String sql = "UPDATE products SET product_name = ?,sold_price = ?,buy_price = ? , quantity = ? , category = ? WHERE product_name = ?";


        System.out.println(product.toString());
        try  {
            Connection conn;
            String url = "jdbc:sqlite:"+ name;
            conn = DriverManager.getConnection(url);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // set the corresponding param
            pstmt.setString(1, product.getName());
            pstmt.setDouble(2, product.getSoldPrice());
            pstmt.setDouble(3, product.getBuyPrice());
            pstmt.setInt(4, product.getQuantity());
            pstmt.setString(5, product.getCategory());
            pstmt.setString(6, oldName);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return;
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

    public static void insertSoldProductDetails(String productName , int quantity , String date, double price , String category){
        String sql = "INSERT INTO soldProducts(product_name,price,quantity,selling_date,category) VALUES(?,?,?,?,?)";

        try  {
            Connection conn;
            String url = "jdbc:sqlite:"+ name;
            conn = DriverManager.getConnection(url);

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, productName);
            pstmt.setDouble(2, price);
            pstmt.setInt(3, quantity);
            pstmt.setString(4, date);
            pstmt.setString(5,category);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void searchForProductsInStoke(String value , String category){
        databaseOperations.searchProducts.clear();
        String sql = "SELECT\n" +
                "\tproduct_name\n" +
                "FROM\n" +
                "\tproducts\n" +
                "WHERE\n" +
                "\t(product_name LIKE '%"+value+"%') AND (category LIKE '"+category+"')";
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
                System.out.println(rs.getString("product_name"));
                /*This is how we can add data and use it in the system*/
                databaseOperations.searchProducts.add(rs.getString("product_name"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static ArrayList<Product> getProductsFromSpecificCategory(String category){
        databaseOperations.listOfProductsOfASpecificCategory.clear();
        String sql = "SELECT\n" +
                "\t*\n" +
                "FROM\n" +
                "\tproducts\n" +
                "WHERE\n" +
                "\tcategory LIKE '" + category+"'";
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
                DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Product p = new Product(rs.getString("product_name"),rs.getDouble("sold_price"),rs.getDouble("buy_price"),rs.getInt("quantity"),formatter.parse(rs.getString("adding_product_date")),rs.getString("category"));
                databaseOperations.listOfProductsOfASpecificCategory.add(p);
                System.out.println(p.getName());
            }
        } catch (SQLException | ParseException e) {
            System.out.println(e.getMessage());
        }
        return databaseOperations.listOfProductsOfASpecificCategory;
    }

    public static void getAllSalesData(){
        String sql = "SELECT * FROM soldProducts";
        System.out.println(sql);
        databaseOperations.salesData.clear();
        try{
            Connection conn;
            // db parameters
            String url = "jdbc:sqlite:"+ name;
            conn = DriverManager.getConnection(url);
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);  ////// return all rows in the table
            // loop through the result set
            while (rs.next()) {

                rs.getString("product_name");
                /*This is how we can add data and use it in the system*/
                Product p = new Product();
                p.setName(rs.getString("product_name"));
                p.setTotalPrice(rs.getDouble("price"));
                p.setQuantity(rs.getInt("quantity"));
                p.setSellingDate(rs.getString("selling_date"));
                p.setCategory(rs.getString("category"));
                p.setSoldPrice(rs.getDouble("price")/rs.getInt("quantity"));
                databaseOperations.salesData.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void getSpecificDateSalesData(String date){
        String sql = "SELECT * FROM soldProducts WHERE selling_date LIKE '%"+date+"%'";
        System.out.println(sql);
        databaseOperations.salesData.clear();
        try{
            Connection conn;
            // db parameters
            String url = "jdbc:sqlite:"+ name;
            conn = DriverManager.getConnection(url);
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);  ////// return all rows in the table
            // loop through the result set
            while (rs.next()) {

                rs.getString("product_name");
                /*This is how we can add data and use it in the system*/
                Product p = new Product();
                p.setName(rs.getString("product_name"));
                p.setTotalPrice(rs.getDouble("price"));
                p.setQuantity(rs.getInt("quantity"));
                p.setSellingDate(rs.getString("selling_date"));
                p.setCategory(rs.getString("category"));
                p.setSoldPrice(rs.getDouble("price")/rs.getInt("quantity"));
                databaseOperations.salesData.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteCategory(String category){
        String sql = "DELETE FROM categories WHERE category = ?";
        String sql2 = "DELETE FROM products WHERE category = ?";
        try  {
            Connection conn;
            String url = "jdbc:sqlite:"+ name;
            conn = DriverManager.getConnection(url);

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, category);

            pstmt.executeUpdate();


            PreparedStatement pstmt2 = conn.prepareStatement(sql2);
            pstmt2.setString(1, category);

            pstmt2.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        getAllCategories();
        getAllData();
    }

    public static void getWantedProducts(){
        String sql = "SELECT product_name,sold_price,buy_price,quantity,adding_product_date,category FROM products WHERE quantity <= 5";
        databaseOperations.wantedProducts.clear();
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
                databaseOperations.wantedProducts.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
