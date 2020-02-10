package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entities.User;
import entities.ViaggioGruppo;
import queries.CRUDQueries;
import queries.SimpleQueries;

public class ViaggiGrDao {
	
	
	private static String USER = "root";
    private static String PASS = "";

	private final static String DB_URL = "jdbc:mysql://127.0.0.1:3306/mydb_progettoISPW";
    private static String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";

    
    
    
    public static void CreateTravel(ViaggioGruppo vg) throws Exception {
    	
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
            CRUDQueries.createGrTravel(stmt, vg);


            
        } finally {
            // STEP 5.2: Clean-up dell'ambiente        	
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
        }

    	
    }
    
    
    
    public static List<ViaggioGruppo> retreiveGrTravells(User u) throws Exception {
    	
   	 	Statement stmt = null;
        Connection conn = null;
        List<ViaggioGruppo> listOfGrTravells = new ArrayList<ViaggioGruppo>();
        
        try {
            // STEP 2: loading dinamico del driver mysql
			Class.forName(DRIVER_CLASS_NAME);
        	
            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 4: creazione ed esecuzione della query
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = SimpleQueries.selectAllGrTravels(stmt, u.getUsername());

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
                float pr = rs.getFloat("Prezzo");
                int nMaxP = rs.getInt("NumMaxUt");
                boolean pagA = rs.getBoolean("PagAnt");
                float impA = rs.getFloat("impAnt");
                Date scad = rs.getDate("Scadenza");
                Date dateV = rs.getDate("DataV");
                String desc = rs.getString("Descrizione");
                
                ViaggioGruppo a = new ViaggioGruppo(idv, usr, pr, nMaxP, pagA, impA, scad, dateV, desc);
                
                listOfGrTravells.add(a);

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
        
    	
        return listOfGrTravells;
    }
    
    
    
    
    public static void retreiveOneGrTravel(ViaggioGruppo vg) throws Exception {
    	
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
               ResultSet rs = SimpleQueries.selectOneDateGrTravel(stmt, vg);

               if (!rs.first()){ // rs empty
               	Exception e = new Exception("No Travel Found matching with this id");
               	throw e;
               }
               
               // riposizionamento del cursore
               rs.first();
               

               DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
       		   Date date = new Date();
       		   String dt = dateFormat.format(date);
       		   

               if(!(rs.getString("Scadenza").contentEquals(dt))) {
            	   Exception e = new Exception("You are out of time to delete your reservation for the travel! We are sorry, be quickly next time.");
            	   throw e;
              	}
               
                   

               
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
           
       	
           return;
       }
    


    
    
    
    
    
        
    
    
    
    
    
    
}
