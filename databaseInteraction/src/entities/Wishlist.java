package entities;

public class Wishlist {
	
	String usr;
	int vg;
	
	
	public Wishlist(User u, Viaggio v) {
		this.usr = u.getUsername();
		this.vg = v.getIdV();
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
	

}
