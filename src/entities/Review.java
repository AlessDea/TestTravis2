package entities;

public class Review {

	String usr;
	int vg;
	String review;
	
	public Review(User u, ViaggioGruppo v, String c) {
		
		this.usr = u.getUsername();
		this.vg = v.getIdV();
		this.review = c;
	
	}
	
	public void setU(String u) {
		this.usr = u;
	}
	
	public String getU() {
		return this.usr;
	}
	
	public void setV(int v) {
		this.vg = v;
	}
	
	public int getV() {
		return this.vg;
	}
	
	public void setR(String c) {
		this.review = c;
	}
	
	public String getR() {
		return this.review;
	}
	
	
	
}
