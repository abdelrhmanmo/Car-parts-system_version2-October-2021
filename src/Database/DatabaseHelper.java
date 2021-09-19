package Database;

/*
* some imports for data base*/


//make sure to download this first https://repo1.maven.org/maven2/org/xerial/sqlite-jdbc/3.8.11/sqlite-jdbc-3.8.11.jar

// after download and extract -- move this file to the file of project before you going to the src

//hit ctrl + shift + alt + s (add the sqlite-jdbc-3.8.11.jar) -- apply -- ok

//try this code if the console print (connection to SQLite has been established) then it works , if not that it doesn't work

import java.sql.*;

public class DatabaseHelper {

    private static String name; // name of database

    public static void setName(String name) {
        DatabaseHelper.name = name; // set the name
    }

    public static void connectToDatabase() {
        Connection conn = null; // here we make a null connection for make sure that we started from an unknown path
        try {
            // db parameters
            String url = "jdbc:sqlite:"+ name;  // the database file is in the same path of this class
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            /* if the console print this that mean it is work and he has connect to the database*/
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            /*if the console print this that mean there is an exception*/
            System.out.println(e.getMessage());
        } finally {
            try {
                //** close the connection after finish*////
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}