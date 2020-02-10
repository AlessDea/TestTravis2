package queries;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import entities.User;
import entities.Viaggio;
import entities.ViaggioGruppo;


public class SimpleQueries {

	public static Boolean loginPswChecker(Statement stmt, User usr) throws SQLException  {
		String sql = "SELECT Password FROM Users WHERE Username = '" + usr.getUsername() + "';";
//        System.out.println(sql);
        ResultSet rs = stmt.executeQuery(sql);
        
        if(!rs.first()) {
        	System.out.println("User not found");
        	return false;
        }
        
        String pw = rs.getString(1);
        
        if(pw.contentEquals(usr.getPassword())) { 
        	return true;
        }
        return false;
	}
	
	
	public static Boolean loginUsrnChecker(Statement stmt, User usr) throws SQLException  {
		String sql = "SELECT Username FROM Users WHERE Username = '" + usr.getUsername() + "';";
//        System.out.println(sql);
        ResultSet rs = stmt.executeQuery(sql);
        
        if(!rs.first()) {
        	System.out.println("User not found");
        	return false;
        }
        
        String us = rs.getString(1);
        
        if(us.contentEquals(usr.getUsername())) { 
        	return true;
        }
        return false;
	}
	
	
	
	public static Boolean controlUniqueUsername(Statement stmt, String username) throws SQLException {
		String sql = "SELECT Username FROM Users WHERE Username = '" + username + "';";
//        System.out.println(sql);
        ResultSet rs = stmt.executeQuery(sql);
        
        if(rs.first()) {
        	System.out.println("Username '"+username+"' is already used");
        	return false;
        }
		
        System.out.println("Username '"+username+"' is ok");
		return true;
	}
	
	
	
	public static ResultSet getPersInfo(Statement stmt, String username) throws SQLException {
		
		String sql = "SELECT * FROM Users WHERE Username = '" + username + "';";
//        System.out.println(sql);
        ResultSet rs = stmt.executeQuery(sql);
        
        return rs;
		
	}
	
	public static ResultSet loadShowC(Statement stmt, User usr) throws SQLException {
	
		String sql = "SELECT * FROM Book WHERE Utente = '" + usr.getUsername() + "';";
        ResultSet rs = stmt.executeQuery(sql);
        return rs;

		
	}
	
	public static ResultSet selectAllTravels(Statement stmt, String username) throws SQLException {
		
		String sql = "SELECT * FROM Viaggi WHERE Creatore = '" + username + "';";
		ResultSet rs = stmt.executeQuery(sql);
      
		return rs;
	}
	
	public static ResultSet selectAllGrTravels(Statement stmt, String username) throws SQLException {
		
		String sql = "SELECT * FROM Viaggi_gruppo WHERE Creatore = '" + username + "';";
		ResultSet rs = stmt.executeQuery(sql);
      
		return rs;
	}
	
	public static ResultSet selectOneDateGrTravel(Statement stmt, ViaggioGruppo v) throws SQLException {
		
		String sql = "SELECT Scadenza FROM Viaggi_gruppo WHERE idViaggioG = '" + v.getIdV() + "';";
		ResultSet rs = stmt.executeQuery(sql);
      
		return rs;
	}
	
	public static ResultSet getComment(Statement stmt, User u, Viaggio v) throws SQLException {
		
		String updateStatement = String.format("SELECT Comment FROM `Comments` WHERE Utente = '%s' and idViaggio = '%d'", u.getUsername(), v.getIdV());
        System.out.println(updateStatement);
        ResultSet rs = stmt.executeQuery(updateStatement);
		return rs;
        
	}
	
	
	public static ResultSet allowReview(Statement stmt, User usr, ViaggioGruppo v) throws SQLException {
		
		String sql = "SELECT * FROM Join WHERE Utente = '" + usr.getUsername() + "' and ViaggioG = '" + v.getIdV()+"';";
//      System.out.println(sql);
		ResultSet rs = stmt.executeQuery(sql);
      
		return rs;
		
	}
	
	public static ResultSet getReview(Statement stmt, User u, ViaggioGruppo v) throws SQLException {
		
		String updateStatement = String.format("SELECT Review FROM `Review` WHERE Utente = '%s' and ViaggioG = '%d'", u.getUsername(), v.getIdV());
        System.out.println(updateStatement);
        ResultSet rs = stmt.executeQuery(updateStatement);
		return rs;
        
	}
	
	public static int getLikes(Statement stmt, Viaggio v) throws SQLException {
		String updateStatement = String.format("SELECT Likes FROM `Viaggi` WHERE idViaggio = '%d'", v.getIdV());
        System.out.println(updateStatement);
        ResultSet rs = stmt.executeQuery(updateStatement);
        rs.first();
		return rs.getInt("Likes");
		
	}
	
	
	public static ResultSet selectAllLikes(Statement stmt, Viaggio v) throws SQLException {
		String updateStatement = String.format("SELECT Cognome, Nome FROM `Viaggi` inner join `Likes` on (`Viaggi`.idViaggio = `Likes`.idViaggio) inner join `Users` on (`Users`.Username = `Likes`.User) WHERE idViaggio = '%d'", v.getIdV());
        System.out.println(updateStatement);
        ResultSet rs = stmt.executeQuery(updateStatement);
        
        return rs;
		
	}
	
	
	public static ResultSet getFollowers(Statement stmt, User u) throws SQLException {
		
		String sql = "SELECT Cognome, Nome FROM Users inner join Follow on (Users.Username = Follow.Seguito) WHERE Seguito = '" + u.getUsername() + "';";
//        System.out.println(sql);
        ResultSet rs = stmt.executeQuery(sql);
        
        return rs;
		
	}
	
	
	public static ResultSet selectAllReservations(Statement stmt, User usr) throws SQLException {
		
		String sql = "SELECT * FROM Book WHERE Utente = '" + usr.getUsername() + "';";
//      System.out.println(sql);
		ResultSet rs = stmt.executeQuery(sql);
      
		return rs;
	}
	
	
	public static ResultSet selectAllTravelsByLikes(Statement stmt) throws SQLException {
		
		String sql = "SELECT * FROM Viaggi ORDER BY Likes DESC "
				+ "LIMIT 50;";
		ResultSet rs = stmt.executeQuery(sql);
      
		return rs;
	}
	
	
	public static ResultSet selectAllTravelsByFoll(Statement stmt, User u) throws SQLException {
		
		String sql = "SELECT * FROM Viaggi WHERE Creatore in (SELECT Seguito"
															+ "FROM Follow"
															+ "WHERE Seguace = '"+ u.getUsername() +"')"
				+ " LIMIT 50;";
		ResultSet rs = stmt.executeQuery(sql);
      
		return rs;
	}
	
	
	
}
