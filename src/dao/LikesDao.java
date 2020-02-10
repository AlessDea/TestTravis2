package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entities.Likes;
import entities.User;
import entities.Viaggio;
import queries.CRUDQueries;
import queries.SimpleQueries;

public class LikesDao {
	
	
	private final static String DB_URL = "jdbc:mysql://127.0.0.1:3306/mydb_progettoISPW";
	private final static String USER = "root";
	private final static String PASS = "";

    private static String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
	
	public static Scanner scan;
	
	
	

    public static void like(User usr, Viaggio v) throws Exception {
    	
    	Statement stmt = null;
    	Connection conn = null;
    	
    	
    	Likes lks = new Likes(usr, v);
    	
    	
    	try {
            // STEP 2: loading dinamico del driver mysql
            Class.forName(DRIVER_CLASS_NAME);

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 4.1: creazione ed esecuzione della query
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            CRUDQueries.insertLike(stmt, lks);
            
            
            //ora devo incrementare il contatore (Atrributo 'Likes') in Viaggi 
            
            CRUDQueries.increaseLike(stmt, v);
            
            
            
            
        } finally {
            // STEP 5.2: Clean-up dell'ambiente        	
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
        }
    	
    }
    
    
    public static void dislike(User usr, Viaggio v) throws Exception {
    	
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
            CRUDQueries.delLike(stmt, usr, v);
            
            //ora devo incrementare il contatore (Atrributo 'Likes') in Viaggi 

            CRUDQueries.decreaseLike(stmt, v);

            
        } finally {
            // STEP 5.2: Clean-up dell'ambiente        	
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
        }
    	
    	
    }
    
    
    public static void getNumOfLikes(Viaggio v) throws Exception {
    	
    	Statement stmt = null;
    	Connection conn = null;
    	int ret;

    	
    	try {
            // STEP 2: loading dinamico del driver mysql
			Class.forName(DRIVER_CLASS_NAME);
        	
            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 4: creazione ed esecuzione della query
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ret = SimpleQueries.getLikes(stmt, v);
            
            System.out.println("Likes: "+ret);

            
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
    
    
    
    public static List<Likes> getLikesByName(Viaggio v) throws Exception {
    
	    Statement stmt = null;
	    Connection conn = null;
	    List<Likes> listOfLikes = new ArrayList<Likes>();
	    
	    try {
	        // STEP 2: loading dinamico del driver mysql
			Class.forName(DRIVER_CLASS_NAME);
	    	
	        // STEP 3: apertura connessione
	        conn = DriverManager.getConnection(DB_URL, USER, PASS);
	
	        // STEP 4: creazione ed esecuzione della query
	        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
	                ResultSet.CONCUR_READ_ONLY);
	        ResultSet rs = SimpleQueries.selectAllLikes(stmt, v);
	
	        if (!rs.first()){ // rs empty
	        	Exception e = new Exception("No Likes Found matching for travel: "+v.getIdV());
	        	throw e;
	        }
	        
	        // riposizionamento del cursore
	        rs.first();
	        do{
	            // lettura delle colonne "by name"
	            String Cognome = rs.getString("Cognome");
	            String Nome = rs.getString("Nome");
	            
	            Likes a = new Likes(Cognome, Nome);
	            
	            listOfLikes.add(a);
	
	        }while(rs.next());
	        
	        // STEP 5.1: Clean-up dell'ambiente
	        rs.close();
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
	    
		
	    return listOfLikes;    
    }

}
