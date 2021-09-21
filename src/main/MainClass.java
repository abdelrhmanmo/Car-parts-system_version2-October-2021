package main;

import Database.databaseOperations;
import pages.Menu;




public class MainClass {
    public static void main(String[] args) {
        new databaseOperations();
        new Menu();
    }
}
