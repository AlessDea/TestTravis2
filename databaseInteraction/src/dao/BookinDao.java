package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entities.Booking;
import entities.User;
import entities.Viaggio;
import queries.CRUDQueries;
import queries.SimpleQueries;

public class BookinDao {
	
	private static String USER = "root";
    private static String PASS = "";
    
	private final static String DB_URL = "jdbc:mysql://127.0.0.1:3306/mydb_progettoISPW";
    private static String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    
    public static Scanner scan;
	
	
    
    public static void bookTravel(Viaggio v, User u) throws Exception {
    	
    	//non potendo utilizzare le api il book si limita ad inserire il viaggio fra quelli effettuatu/prenotati dall'utente
    	
    	Statement stmt = null;
    	Connection conn = null;
    	
    	Booking b = new Booking(u, v);

    	scan = new Scanner(System.in);
    	scan.useDelimiter("\n");
    	
    	System.out.print("Specify how many passengers you are booking for: ");
//    	int numPassengers = scan.nextInt();
    	b.setNv(scan.nextInt());
    	

    	scan.close();
    	
    	
    	try {
            // STEP 2: loading dinamico del driver mysql
			Class.forName(DRIVER_CLASS_NAME);
        	
            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 4: creazione ed esecuzione della query
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            CRUDQueries.bookTravel(stmt, b);

            
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
    
    
    
    public static List<Booking> retreiveReservations(User u) throws Exception {
    	
   	 	Statement stmt = null;
        Connection conn = null;
        List<Booking> listOfReservations = new ArrayList<Booking>();
        
        try {
            // STEP 2: loading dinamico del driver mysql
			Class.forName(DRIVER_CLASS_NAME);
        	
            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 4: creazione ed esecuzione della query
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = SimpleQueries.selectAllReservations(stmt, u);

            if (!rs.first()){ // rs empty
            	Exception e = new Exception("No Travells Found matching for user: "+u.getUsername());
            	throw e;
            }
            
            // riposizionamento del cursore
            rs.first();
            do{
                // lettura delle colonne "by name"
           	 	int idv = rs.getInt("idViaggio");
                String usr = rs.getString("Utente");
                int nv = rs.getInt("NumViaggiatori");
                //per adesso non gestisco il file
                
                Booking a = new Booking(usr, idv, nv);
                
                listOfReservations.add(a);

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
        
    	
        return listOfReservations;
    }
		

}
