package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.Follow;
import entities.User;
import queries.CRUDQueries;
import queries.SimpleQueries;

public class FollowDao {
	
	private final static String DB_URL = "jdbc:mysql://127.0.0.1:3306/mydb_progettoISPW";
	private final static String USER = "root";
	private final static String PASS = "";

    private static String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    
    
  //fl: seguace, flwd: seguito
    public void follow(User fl, User flwd) throws Exception {
    	
    	Statement stmt = null;
    	Connection conn = null;
    	
    	//con questa if faccio in modo che un utente non si segua da solo
    	if(!(fl.getUsername().contentEquals(flwd.getUsername()))){
	    	try {
	            // STEP 2: loading dinamico del driver mysql
	            Class.forName(DRIVER_CLASS_NAME);
	
	            // STEP 3: apertura connessione
	            conn = DriverManager.getConnection(DB_URL, USER, PASS);
	
	            // STEP 4.1: creazione ed esecuzione della query
	            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
	                    ResultSet.CONCUR_READ_ONLY);
	            CRUDQueries.addFollower(stmt, flwd, fl);
	
	            
	        } finally {
	            // STEP 5.2: Clean-up dell'ambiente        	
	                if (stmt != null)
	                    stmt.close();
	                if (conn != null)
	                    conn.close();
	        }
    	}else {
    		System.out.println("You are trying to follow yourself.\nWe are sorry, you can not do this.");
    	}
    	
    }
    
  //fl: seguace, flwd: seguito
    public void unfollow(User fl, User flwd) throws Exception {
    	
    	Statement stmt = null;
    	Connection conn = null;
    	
    	//con questa if faccio in modo che un utente non si segua da solo
    	if(!(fl.getUsername().contentEquals(flwd.getUsername()))){
	    	try {
	            // STEP 2: loading dinamico del driver mysql
	            Class.forName(DRIVER_CLASS_NAME);
	
	            // STEP 3: apertura connessione
	            conn = DriverManager.getConnection(DB_URL, USER, PASS);
	
	            // STEP 4.1: creazione ed esecuzione della query
	            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
	                    ResultSet.CONCUR_READ_ONLY);
	            CRUDQueries.delFollower(stmt, flwd, fl);
	
	            
	        } finally {
	            // STEP 5.2: Clean-up dell'ambiente        	
	                if (stmt != null)
	                    stmt.close();
	                if (conn != null)
	                    conn.close();
	        }
    	}else {
    		System.out.println("You are trying to unfollow yourself.\nWe are sorry, you can not do this.");
    	}
    	
    }
    
    
    
    public static List<Follow> retrieveFollowers(User u) throws Exception {
    	
   	 	Statement stmt = null;
        Connection conn = null;
        List<Follow> followersList = new ArrayList<Follow>();
        
        try {
            // STEP 2: loading dinamico del driver mysql
			Class.forName(DRIVER_CLASS_NAME);
        	
            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 4: creazione ed esecuzione della query
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = SimpleQueries.getFollowers(stmt, u);

            if (!rs.first()){ // rs empty
            	Exception e = new Exception("No Followers Found matching for user: "+u.getUsername());
            	throw e;
            }
            
            // riposizionamento del cursore
            rs.first();
            do{
                // lettura delle colonne "by name"
            	
                String cognome = rs.getString("Cognome");
                String nome = rs.getString("Nome");
                
                
                Follow a = new Follow(cognome, nome);
                
                followersList.add(a);

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
        
    	
        return followersList;
    }
}
