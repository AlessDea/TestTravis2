package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


import entities.User;
import queries.*;
import exceptions.*;

public class UserDao {

	private final static String DB_URL = "jdbc:mysql://127.0.0.1:3306/mydb_progettoISPW";
	private final static String USER = "root";
	private final static String PASS = "";

    private static String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";

    public static Scanner scan;
	
    
    
    public static Boolean usernameChecker(String username) throws Exception {
		
		Statement stmt = null;
    	Connection conn = null;
    	boolean ret;
    	

		try {
            // STEP 2: loading dinamico del driver mysql
			Class.forName(DRIVER_CLASS_NAME);
        	
            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 4: creazione ed esecuzione della query
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ret = SimpleQueries.controlUniqueUsername(stmt, username);
            
            //se ritorna falso vuol dire che è gia in uso, allora mando l'eccezione
            if (!ret) {
            	
            	throw new DuplicateRecord("This username is already used by another user. Please try with a new one."); 
            
            }
            
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
				
		return true;
		
    }
    
    
    
    
    
    
    public static User registerUsr(String firstName, String secondName, int age, String username, String email, String password, String secretQuestion, String secretAnswer) throws Exception {
    	
    	
    	
    	Statement stmt = null;
    	Connection conn = null;
 
    	
    	User usr = new User(firstName, secondName, age, username, email, password, secretQuestion, secretAnswer);

    	
    	try {
            // STEP 2: loading dinamico del driver mysql
            Class.forName(DRIVER_CLASS_NAME);

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 4.1: creazione ed esecuzione della query
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            CRUDQueries.inserisciNuovoUtente(stmt, usr);
            
        } finally {
            // STEP 5.2: Clean-up dell'ambiente        	
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
        }
    	
    	return usr;
    }
    
    
    
    
    
    
    
    
    
    public static void modifPsw(User usr) throws Exception {
    
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
            CRUDQueries.modifPassword(stmt, usr);

            
        } finally {
            // STEP 5.2: Clean-up dell'ambiente        	
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
        }
    	    	
    	
    }
    
    
       
    
    
    
    public static User logUsr(String username, String password) throws Exception {
    	
    	
		Statement stmt = null;
    	Connection conn = null;
    	boolean ret, ret1;
    	
    	
		User usr = new User(username, password);

		try {
            // STEP 2: loading dinamico del driver mysql
			Class.forName(DRIVER_CLASS_NAME);
        	
            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 4: creazione ed esecuzione della query
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ret = SimpleQueries.loginPswChecker(stmt, usr);
            ret1 = SimpleQueries.loginUsrnChecker(stmt, usr);

            if (!(ret && ret1)) {
            	throw new LoginErrorExc("Wrong username or password. Please try again."); 
            }
            
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
				
		return usr;
		
    }
    
    
    
    
}
