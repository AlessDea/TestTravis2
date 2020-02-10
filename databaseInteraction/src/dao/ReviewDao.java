package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import entities.Review;
import entities.User;
import entities.ViaggioGruppo;
import queries.CRUDQueries;
import queries.SimpleQueries;

public class ReviewDao {
	
	private final static String DB_URL = "jdbc:mysql://127.0.0.1:3306/mydb_progettoISPW";
	private final static String USER = "root";
	private final static String PASS = "";

    private static String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
	
	
	public static Scanner scan;
	
	
	
	
	public static void writeReview(User usr, ViaggioGruppo v) throws Exception {
    	
    	Statement stmt = null;
    	Connection conn = null;
    	
    	
    	//prima devo verificare che l'utente abbia veramente fatto quel viaggio, altrimenti non può scrivere una recensione
    	
    	
    	scan = new Scanner(System.in);
    	scan.useDelimiter("\n");

    	
    	try {
            // STEP 2: loading dinamico del driver mysql
            Class.forName(DRIVER_CLASS_NAME);

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 4.1: creazione ed esecuzione della query
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            
            ResultSet rs = SimpleQueries.allowReview(stmt, usr, v);
            if(!rs.next()) {
            	System.out.println("You've never booked this travel, so you can not leave a review.");
            	return;
            }            
            
            
            String rev = scan.next();
        	scan.close();
        	Review review = new Review(usr, v, rev);
        	
             
            
            CRUDQueries.insertReview(stmt, review);
            
            
        } finally {
            // STEP 5.2: Clean-up dell'ambiente        	
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
        }
    	
    }

	
	
	 public static void deleteReview(User usr, ViaggioGruppo v) throws Exception {
	    	
	    	Statement stmt = null;
	    	Connection conn = null;
	    	
	    	
	    	try {
	            // STEP 2: loading dinamico del driver mysql
	            Class.forName(DRIVER_CLASS_NAME);

	            // STEP 3: apertura connessione
	            conn = DriverManager.getConnection(DB_URL, USER, PASS);

	            // STEP 4.1: creazione ed esecuzione della query
	            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
	                    ResultSet.CONCUR_READ_ONLY);
	            CRUDQueries.delReview(stmt, usr, v);
	            
	        } finally {
	            // STEP 5.2: Clean-up dell'ambiente        	
	                if (stmt != null)
	                    stmt.close();
	                if (conn != null)
	                    conn.close();
	        }
	    	
	    	System.out.println("Review deleted successfully!");
	    	
	 }
	 
	 
	 
	 
	 public static void modifyReview(User usr, ViaggioGruppo v) throws Exception {
	    	
	    	Statement stmt = null;
	    	Connection conn = null;
	    	  	
	    	
	    	try {
	            // STEP 2: loading dinamico del driver mysql
	            Class.forName(DRIVER_CLASS_NAME);

	            // STEP 3: apertura connessione
	            conn = DriverManager.getConnection(DB_URL, USER, PASS);

	            // STEP 4.1: creazione ed esecuzione della query
	            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
	                    ResultSet.CONCUR_READ_ONLY);
	            
	            ResultSet rs = SimpleQueries.getReview(stmt, usr, v);
	            
	            String review = rs.getString("Review");
	            
	            scan = new Scanner(System.in);
	        	scan.useDelimiter("\n");

	        	
	        	System.out.println("This is your Review: '"+ review + "'\n Insert the new review:\n");
	        	String newRev = scan.next();
	        	scan.close();
	            
	            CRUDQueries.modifReview(stmt, usr, v, newRev);
	            
	        } finally {
	            // STEP 5.2: Clean-up dell'ambiente        	
	                if (stmt != null)
	                    stmt.close();
	                if (conn != null)
	                    conn.close();
	        }
	    	
	    	
	    }
	
	
	
	
}
