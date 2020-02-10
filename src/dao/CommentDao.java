package dao;

import java.util.Scanner;
import java.sql.*;


import entities.Comment;
import entities.User;
import entities.Viaggio;
import queries.CRUDQueries;
import queries.SimpleQueries;


public class CommentDao {
    
	private final static String DB_URL = "jdbc:mysql://127.0.0.1:3306/mydb_progettoISPW";
	private final static String USER = "root";
	private final static String PASS = "";

    private static String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
	
	public static Scanner scan;
	
	
	

    public static void writeComment(User usr, Viaggio v) throws Exception {
    	
    	Statement stmt = null;
    	Connection conn = null;
    	
    	scan = new Scanner(System.in);
    	scan.useDelimiter("\n");

    	
    	String cmnt = scan.next();
    	scan.close();
    	Comment comment = new Comment(usr.getUsername(), v.getIdV(), cmnt);
    	
    	try {
            // STEP 2: loading dinamico del driver mysql
            Class.forName(DRIVER_CLASS_NAME);

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 4.1: creazione ed esecuzione della query
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            CRUDQueries.insertComment(stmt, comment);
            
        } finally {
            // STEP 5.2: Clean-up dell'ambiente        	
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
        }
    	
    }
    
    
    public static void deleteComment(User usr, Viaggio v) throws Exception {
    	
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
            CRUDQueries.delComment(stmt, usr, v);
            
        } finally {
            // STEP 5.2: Clean-up dell'ambiente        	
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
        }
    	
    	System.out.println("Comment deleted successfully!");
    	
    }
    
    
    
    public static void modifyComment(User usr, Viaggio v) throws Exception {
    	
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
            
            ResultSet rs = SimpleQueries.getComment(stmt, usr, v);
            
            String comment = rs.getString("Comment");
            
            scan = new Scanner(System.in);
        	scan.useDelimiter("\n");

        	
        	System.out.println("This is your comment: '"+ comment + "'\n Insert the new comment:\n");
        	String newComment = scan.next();
        	scan.close();
            
            CRUDQueries.modifComment(stmt, usr, v, newComment);
            
        } finally {
            // STEP 5.2: Clean-up dell'ambiente        	
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
        }
    	
    	
    }
    
    
    
    
	
}
