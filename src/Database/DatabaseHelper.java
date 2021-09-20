package Database;

/*
* some imports for data base*/


//make sure to download this first https://repo1.maven.org/maven2/org/xerial/sqlite-jdbc/3.8.11/sqlite-jdbc-3.8.11.jar

// after download and extract -- move this file to the file of project before you going to the src

//hit ctrl + shift + alt + s (add the sqlite-jdbc-3.8.11.jar) -- apply -- ok

//try this code if the console print (connection to SQLite has been established) then it works , if not that it doesn't work

import classes.Product;

import java.sql.*;

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
        String table1 = "CREATE TABLE products (product_name TEXT PRIMARY KEY, sold_price DOUBLE NOT NULL,buy_price DOUBLE NOT NULL,quantity INTEGER NOT NULL)";
        String table2 = "CREATE TABLE soldProduct (product_name REFERENCES products(product_name), sold_price DOUBLE NOT NULL,buy_price DOUBLE NOT NULL,quantity integer NOT NULL)";
        try {
            Connection conn;
            // db parameters
            String url = "jdbc:sqlite:"+ name;  // the database file is in the same path of this class
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();

            stmt.execute(table1);
            stmt.execute(table2);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insertData(Product product){
        String sql = "INSERT INTO products(product_name,sold_price,buy_price,quantity) VALUES(?,?,?,?)";

        try  {
            Connection conn;
            String url = "jdbc:sqlite:"+ name;
            conn = DriverManager.getConnection(url);

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, product.getName());
            pstmt.setDouble(2, product.getSoldPrice());
            pstmt.setDouble(3, product.getBuyPrice());
            pstmt.setDouble(4, product.getQuantity());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void getAllData(){
            String sql = "SELECT product_name,sold_price,buy_price,quantity FROM products";

            try{
                Connection conn;
                // db parameters
                String url = "jdbc:sqlite:"+ name;  //
                conn = DriverManager.getConnection(url);
                Statement stmt  = conn.createStatement();
                ResultSet rs    = stmt.executeQuery(sql);
                // loop through the result set
                while (rs.next()) {
                    System.out.println(rs.getString("product_name") +  "\t" +
                            rs.getDouble("sold_price") + "\t" +rs.getDouble("buy_price")+"\t" +
                            rs.getDouble("quantity"));
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
    }
    public static void deleteData(String name){

    }

    public static void updateData (Product product){

    }

}