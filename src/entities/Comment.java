package entities;

public class Comment {
	
	String usr;
	int vg;
	String comment;
	
	public Comment(String u, int v, String c) {
		
		this.usr = u;
		this.vg = v;
		this.comment = c;
	
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
	
	public void setC(String c) {
		this.comment = c;
	}
	
	public String getC() {
		return this.comment;
	}

}
