package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import entities.Join;
import entities.User;
import entities.ViaggioGruppo;
import queries.CRUDQueries;

public class JoinDao {
	
	private static String USER = "root";
    private static String PASS = "";

	private final static String DB_URL = "jdbc:mysql://127.0.0.1:3306/mydb_progettoISPW";
    private static String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";

    

    public static void joinVGruppo(User u, int idV) throws Exception {
    	
    	Statement stmt = null;
    	Connection conn = null;
    	
    	Join jv = new Join(u, idV);
    	
    	try {
            // STEP 2: loading dinamico del driver mysql
            Class.forName(DRIVER_CLASS_NAME);

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 4.1: creazione ed esecuzione della query
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            CRUDQueries.joinGrTravel(stmt, jv);
            

            
        } finally {
            // STEP 5.2: Clean-up dell'ambiente        	
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
        }
    	
    }
    
    
    public static void acceptJoin(String usrn, int idV) throws Exception {
    	
    	Statement stmt = null;
    	Connection conn = null;
    	
    	Join jv = new Join(usrn, idV);
    	
    	try {
            // STEP 2: loading dinamico del driver mysql
            Class.forName(DRIVER_CLASS_NAME);

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 4.1: creazione ed esecuzione della query
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            CRUDQueries.acceptJoinGrTravel(stmt, jv);
            

            
        } finally {
            // STEP 5.2: Clean-up dell'ambiente        	
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
        }
    	
    }
    
    public static void declineJoin(String usrn, int idV) throws Exception {
    	
    	Statement stmt = null;
    	Connection conn = null;
    	
    	Join jv = new Join(usrn, idV);
    	
    	try {
            // STEP 2: loading dinamico del driver mysql
            Class.forName(DRIVER_CLASS_NAME);

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 4.1: creazione ed esecuzione della query
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            CRUDQueries.declineJoinGrTravel(stmt, jv);
            

            
        } finally {
            // STEP 5.2: Clean-up dell'ambiente        	
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
        }
    	
    }
    
    
    
    public static void deleteMyJoin(User u, ViaggioGruppo v) throws Exception {
    	
    	Statement stmt = null;
    	Connection conn = null;
    	
    	
    	Join jv = new Join(u.getUsername(), v.getIdV());
    	
    	
//		prima devo verificare la data di scadenza per l'eliminazione....
    	
    	try {
            // STEP 2: loading dinamico del driver mysql
            Class.forName(DRIVER_CLASS_NAME);

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 4.1: creazione ed esecuzione della query
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            CRUDQueries.deleteMyJoinGrTravel(stmt, jv);
            

            
        } finally {
            // STEP 5.2: Clean-up dell'ambiente        	
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
        }
    	
    }
    
    
}
