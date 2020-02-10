package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.User;
import entities.Viaggio;
import queries.CRUDQueries;
import queries.SimpleQueries;

public class ViaggiDao {
	
	private static String USER = "root";
    private static String PASS = "";

	private final static String DB_URL = "jdbc:mysql://127.0.0.1:3306/mydb_progettoISPW";
    private static String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    
    
    
    
    
    public static void CreateTravel(Viaggio vg) throws Exception {
    	//questo metodo va a 'braccetto' con l'interfaccia utente più di altri, 
    	
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
            CRUDQueries.createTravel(stmt, vg);


            
        } finally {
            // STEP 5.2: Clean-up dell'ambiente        	
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
        }

    	
    	
    }
    
    
    
    
    public static List<Viaggio> retreiveTravells(User u) throws Exception {
    	
    	 Statement stmt = null;
         Connection conn = null;
         List<Viaggio> listOfTravells = new ArrayList<Viaggio>();
         
         try {
             // STEP 2: loading dinamico del driver mysql
 			Class.forName(DRIVER_CLASS_NAME);
         	
             // STEP 3: apertura connessione
             conn = DriverManager.getConnection(DB_URL, USER, PASS);

             // STEP 4: creazione ed esecuzione della query
             stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = SimpleQueries.selectAllTravels(stmt, u.getUsername());

             if (!rs.first()){ // rs empty
             	Exception e = new Exception("No Travells Found matching for user: "+u.getUsername());
             	throw e;
             }
             
             // riposizionamento del cursore
             rs.first();
             do{
                 // lettura delle colonne "by name"
            	 int idv = rs.getInt("idViaggio");
                 String usr = rs.getString("Creatore");
                 String dest = rs.getString("Destinazione");
                 String desc = rs.getString("Descrizione");
                 Boolean sts = rs.getBoolean("Fatto");
                 
                 Viaggio a = new Viaggio(idv, usr, dest, desc, sts);
                 
                 listOfTravells.add(a);

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
         
     	
         return listOfTravells;
     }
    
    
    public static List<Viaggio> retreiveTravellsByLikes( ) throws Exception {
    	
   	 	Statement stmt = null;
        Connection conn = null;
        List<Viaggio> listOfTravells = new ArrayList<Viaggio>();
        
        try {
            // STEP 2: loading dinamico del driver mysql
			Class.forName(DRIVER_CLASS_NAME);
        	
            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 4: creazione ed esecuzione della query
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = SimpleQueries.selectAllTravelsByLikes(stmt);

            if (!rs.first()){ // rs empty
            	Exception e = new Exception("No Travells Found");
            	throw e;
            }
            
            // riposizionamento del cursore
            rs.first();
            do{
                // lettura delle colonne "by name"
           	 int idv = rs.getInt("idViaggio");
                String usr = rs.getString("Creatore");
                String dest = rs.getString("Destinazione");
                String desc = rs.getString("Descrizione");
                Boolean sts = rs.getBoolean("Fatto");
                
                Viaggio a = new Viaggio(idv, usr, dest, desc, sts);
                
                listOfTravells.add(a);

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
        
    	
        return listOfTravells;
    }
    
    
    
    
    
    public static List<Viaggio> retreiveTravellsByFoll(User u) throws Exception {
    	
   	 	Statement stmt = null;
        Connection conn = null;
        List<Viaggio> listOfTravells = new ArrayList<Viaggio>();
        
        try {
            // STEP 2: loading dinamico del driver mysql
			Class.forName(DRIVER_CLASS_NAME);
        	
            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 4: creazione ed esecuzione della query
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = SimpleQueries.selectAllTravelsByFoll(stmt, u);

            if (!rs.first()){ // rs empty
            	Exception e = new Exception("No Travells Found");
            	throw e;
            }
            
            // riposizionamento del cursore
            rs.first();
            do{
                // lettura delle colonne "by name"
           	 int idv = rs.getInt("idViaggio");
                String usr = rs.getString("Creatore");
                String dest = rs.getString("Destinazione");
                String desc = rs.getString("Descrizione");
                Boolean sts = rs.getBoolean("Fatto");
                
                Viaggio a = new Viaggio(idv, usr, dest, desc, sts);
                
                listOfTravells.add(a);

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
        
    	
        return listOfTravells;
    }
    
    
}
