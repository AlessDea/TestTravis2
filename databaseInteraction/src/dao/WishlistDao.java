package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import entities.Wishlist;
import queries.CRUDQueries;

public class WishlistDao {
	
	private static String USER = "root";
    private static String PASS = "";

	private final static String DB_URL = "jdbc:mysql://127.0.0.1:3306/mydb_progettoISPW";
    private static String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    
    public static Scanner scan;
    
	
	
    public static void addToWishlist(Wishlist w) throws Exception {
    	
    	Statement stmt = null;
    	Connection conn = null;

  
    	try {
            // STEP 2: loading dinamico del driver mysql
			Class.forName(DRIVER_CLASS_NAME);
        	
            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 4: creazione ed esecuzione della query
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            CRUDQueries.insertIntoWishlist(stmt, w);

            
        } finally {
            // STEP 5.2: Clean-up dell'ambiente
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    	
    	
    }
    
    
    
    public static void delFromWishlist(Wishlist w) throws Exception {
    	
    	Statement stmt = null;
    	Connection conn = null;

  
    	try {
            // STEP 2: loading dinamico del driver mysql
			Class.forName(DRIVER_CLASS_NAME);
        	
            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 4: creazione ed esecuzione della query
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            CRUDQueries.removeFromWishlist(stmt, w);

            
        } finally {
            // STEP 5.2: Clean-up dell'ambiente
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    	
    	
    }

}
