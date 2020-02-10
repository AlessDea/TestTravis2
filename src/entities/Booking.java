package entities;

public class Booking {
	
	String usr;
	int idV;
	int viaggiatori;
	long pdf;		//per un eventuale file che vogli caricare l'utente
					//vedere come gestire questo file...
	
	
	public Booking(User u, Viaggio v) {
		this.usr = u.getUsername();
		this.idV = v.getIdV();
	}
	
	public Booking(String u, int v, int n) {
		this.usr = u;
		this.idV = v;
		this.viaggiatori = n;
	}
	
	
	public void setIdV(int id) {
		this.idV = id;
	} 
	
	
	public int getIdV() {
		return this.idV;
	}
	
	
	public void setIdU(String u) {
		this.usr = u;
	}
	
	
	public String getIdU() {
		return this.usr;
	}
	
	
	public int getNv() {
		return this.viaggiatori;
	}
	
	
	public void setNv(int nv) {
		this.viaggiatori = nv;
	}
	

}
