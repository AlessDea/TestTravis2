package entities;

public class Likes {
	
	String user;
	int idViaggio;
	String Cognome;
	String Nome;

	public Likes(User usr, Viaggio v) {
		this.user = usr.getUsername();
		this.idViaggio = v.getIdV();
	}
	
	public Likes(String cogn, String nom) {
		this.Cognome = cogn;
		this.Nome = nom;
	}
	
	public String getU(){
		return this.user;
	}
	
	public void setU(String usr){
		this.user = usr;
	}
	
	public void setIdV(int id) {
		this.idViaggio = id;
	} 
	
	
	public int getIdV() {
		return this.idViaggio;
	}
	
	

}
