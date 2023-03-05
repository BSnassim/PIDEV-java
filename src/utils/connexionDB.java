/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Siwar Ahmadi
 */
public class connexionDB { 
     private static String HOST = "localhost";
        private static int PORT = 3306;
        private static String DB_NAME = "soundon";
        private static String USERNAME = "root";
        private static String PASSWORD = "";
        private static Connection conn ;
        
    private static connexionDB instance;
    
    
    
    public static Connection connexionDB(){
        
        try {
            conn = DriverManager.getConnection(String.format("jdbc:mysql://%s:%d/%s", HOST,PORT,DB_NAME),USERNAME,PASSWORD);
            System.out.println("Connexion établie!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
 return  conn;
    }
    
    public static connexionDB getInstance(){
    if (instance == null)
        instance = new connexionDB();
    return instance;
    }

    public Connection getConnexion() {
        return conn;
    }
    
}
