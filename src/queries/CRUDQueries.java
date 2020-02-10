package queries;

import java.sql.*;

import entities.Booking;
import entities.Comment;
import entities.Join;
import entities.Wishlist;
import entities.Likes;
import entities.Review;
import entities.User;
import entities.Viaggio;
import entities.ViaggioGruppo;


public class CRUDQueries {

	public static int inserisciNuovoUtente(Statement stmt, User u) throws SQLException {
		
		String insertStatement = String.format("INSERT INTO `Users` (`Cognome`, `Nome`, `Username`, `Password`, `email`, `DomandaSegreta`, `RispostaSegreta`) VALUES('%s' , '%s', '%s', '%s', '%s', '%s', '%s')", u.getSecondName(), u.getFirstName(), u.getUsername(), u.getPassword(), u.getEmail(), u.getSQ(), u.getSA());
		System.out.println(insertStatement);
		return stmt.executeUpdate(insertStatement);
	}
	
	
	public static int modifPassword(Statement stmt, User usr) throws SQLException {
		
		String updateStatement = String.format("UPDATE  Users set Password='%s' WHERE Username = '%s'", usr.getPassword(), usr.getUsername());
        System.out.println(updateStatement);
        return stmt.executeUpdate(updateStatement);
	
	}
	
	
	public static int createTravel(Statement stmt, Viaggio v) throws SQLException {
		
		String updateStatement = String.format("INSERT INTO `Viaggi` (`Creatore`, `Destinazione`, `Descrizione`, `Fatto`) VALUES ('%s', '%s', '%s', '%b')", v.getIdU(), v.getDst(), v.getDesc(), v.getStatus());
        System.out.println(updateStatement);
        return stmt.executeUpdate(updateStatement);
		
	}
	
	
	
	public static int bookTravel(Statement stmt, Booking b) throws SQLException {
		
		String updateStatement = String.format("INSERT INTO `Book` (`Utente`, `Viaggio`, `numViaggiatori`) VALUES ('%s', '%d', '%d')", b.getIdU(), b.getIdV(), b.getNv());
        System.out.println(updateStatement);
        return stmt.executeUpdate(updateStatement);
		
	}
	
	
	public static int insertIntoWishlist(Statement stmt, Wishlist w) throws SQLException {
		
		String updateStatement = String.format("INSERT INTO `Wishlist` (`Utente`, `Viaggio`) VALUES ('%s', '%d')", w.getU(), w.getV());
        System.out.println(updateStatement);
        return stmt.executeUpdate(updateStatement);
		
	}
	
	
	public static int removeFromWishlist(Statement stmt, Wishlist w) throws SQLException {
		
		String updateStatement = String.format("DELETE FROM `Wishlist` WHERE Utente = '%s' and idViaggio = '%d'", w.getU(), w.getV());
        System.out.println(updateStatement);
        return stmt.executeUpdate(updateStatement);
		
	}
	
	
	public static int insertComment(Statement stmt, Comment cm) throws SQLException {
		
		String updateStatement = String.format("INSERT INTO `Comments` (`Utente`, `Viaggio`, `Commento`) VALUES ('%s', '%d', '%s')", cm.getU(), cm.getV(), cm.getC());
        System.out.println(updateStatement);
        return stmt.executeUpdate(updateStatement);
		
	}
	
	
	public static int delComment(Statement stmt, User u, Viaggio v) throws SQLException {
		
		String updateStatement = String.format("DELETE FROM `Comments` WHERE Utente = '%s' and idViaggio = '%d'", u.getUsername(), v.getIdV());
        System.out.println(updateStatement);
        return stmt.executeUpdate(updateStatement);
		
	}
	
	
	public static int modifComment(Statement stmt, User u, Viaggio v, String cm) throws SQLException {
		
		String updateStatement = String.format("UPDATE `Comments` SET `Comment` = '%s' WHERE Utente = '%s' and idViaggio = '%d'", cm, u.getUsername(), v.getIdV());
        System.out.println(updateStatement);
        return stmt.executeUpdate(updateStatement);
		
	}
	
	
	public static int insertReview(Statement stmt, Review rv) throws SQLException {
		
		String updateStatement = String.format("INSERT INTO `Reviews` (`Utente`, `Viaggio`, `Review`) VALUES ('%s', '%d', '%s')", rv.getU(), rv.getV(), rv.getR());
        System.out.println(updateStatement);
        return stmt.executeUpdate(updateStatement);
		
	}
	
	public static int delReview(Statement stmt, User u, ViaggioGruppo v) throws SQLException {
		
		String updateStatement = String.format("DELETE FROM `Review` WHERE Utente = '%s' and ViaggioG = '%d'", u.getUsername(), v.getIdV());
        System.out.println(updateStatement);
        return stmt.executeUpdate(updateStatement);
		
	}
	
	
	public static int modifReview(Statement stmt, User u, ViaggioGruppo v, String rv) throws SQLException {
		
		String updateStatement = String.format("UPDATE `Review` SET `Review` = '%s' WHERE Utente = '%s' and ViaggioG = '%d'", rv, u.getUsername(), v.getIdV());
        System.out.println(updateStatement);
        return stmt.executeUpdate(updateStatement);
		
	}
	
	
	public static int insertLike(Statement stmt, Likes lk) throws SQLException {
		
		String updateStatement = String.format("INSERT INTO `Likes` (`Utente`, `Viaggio`) VALUES ('%s', '%d')", lk.getU(), lk.getIdV());
        System.out.println(updateStatement);
        return stmt.executeUpdate(updateStatement);
		
	}
	
	
	public static int delLike(Statement stmt, User u, Viaggio v) throws SQLException {
		
		String updateStatement = String.format("DELETE FROM `Likes` WHERE idViaggio = '%d' and Utente = '%s'", v.getIdV(), u.getUsername());
        System.out.println(updateStatement);
        return stmt.executeUpdate(updateStatement);
		
	}
	
	
	public static int increaseLike(Statement stmt, Viaggio v) throws SQLException {
		
		String updateStatement = String.format("UPDATE 'Viaggi' SET Likes = Likes + 1 WHERE idViaggio = '%d'", v.getIdV());
        System.out.println(updateStatement);
        return stmt.executeUpdate(updateStatement);
		
	}
	
	public static int decreaseLike(Statement stmt, Viaggio v) throws SQLException {
		
		String updateStatement = String.format("UPDATE 'Viaggi' SET Likes = Likes - 1 WHERE idViaggio = '%d'", v.getIdV());
        System.out.println(updateStatement);
        return stmt.executeUpdate(updateStatement);
		
	}
	
	
	public static int addFollower(Statement stmt, User f1, User f2) throws SQLException {
		
		String updateStatement = String.format("INSERT INTO `Follow` (`Seguito`, `Seguace`) VALUES ('%s', '%s')", f1.getUsername(), f2.getUsername());
        System.out.println(updateStatement);
        return stmt.executeUpdate(updateStatement);
		
	}
	
	public static int delFollower(Statement stmt, User f1, User f2) throws SQLException {
		
		String updateStatement = String.format("DELETE FROM `Follow` WHERE Seguito = '%s' and Seguace = '%s'", f1.getUsername(), f2.getUsername());
        System.out.println(updateStatement);
        return stmt.executeUpdate(updateStatement);
		
	}

	public static int createGrTravel(Statement stmt, ViaggioGruppo v) throws SQLException {
		
		String updateStatement = String.format("INSERT INTO `Viaggi_gruppo` (`Creatore`, `Prezzo`, `NumMaxUt`, `PagAnt`, `ImpAnt`, `Scadenza`, `DataV`, `Descrizione`) VALUES ('%s', '%f', '%d', '%b', '%f', '%td/%tm/%ty', '%td/%tm/%ty', '%s')", v.getCr(), v.getPre(), v.getNm(), v.getPagA(), v.getImpA(), v.getScad(), v.getDate(), v.getDesc());
        System.out.println(updateStatement);
        return stmt.executeUpdate(updateStatement);
		
	}
	
	public static int joinGrTravel(Statement stmt, Join j) throws SQLException {
		
		String updateStatement = String.format("INSERT INTO `Join` (`Utente`, `Viaggio`, `Accettato`) VALUES ('%s', '%d', '0')", j.getIdU(), j.getIdV());
        System.out.println(updateStatement);
        return stmt.executeUpdate(updateStatement);
		
	}
	
	
	public static int acceptJoinGrTravel(Statement stmt, Join j) throws SQLException {
		
		String updateStatement = String.format("UPDATE 'Join' SET Accettato = 1 WHERE idViaggioG = '%d' and Utente = '%s'", j.getIdV(), j.getIdU());
        System.out.println(updateStatement);
        return stmt.executeUpdate(updateStatement);
		
	}
	
	public static int declineJoinGrTravel(Statement stmt, Join j) throws SQLException {
		
		String updateStatement = String.format("UPDATE 'Join' SET Accettato = 0 WHERE idViaggioG = '%d' and Utente = '%s'", j.getIdV(), j.getIdU());
        System.out.println(updateStatement);
        return stmt.executeUpdate(updateStatement);
		
	}
	
	
	public static int deleteMyJoinGrTravel(Statement stmt, Join j) throws SQLException {
		
		String updateStatement = String.format("DELETE FROM `Join` WHERE Utente = '%s' and idViaggioG = '%d'", j.getIdU(), j.getIdV());
        System.out.println(updateStatement);
        return stmt.executeUpdate(updateStatement);
		
	}
	
	
}
