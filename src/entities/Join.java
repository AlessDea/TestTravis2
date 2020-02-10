package entities;

public class Join {

	String usr;
	int idV;
	Boolean accepted;
	
	//crea
	public Join(User u, int v) {
		this.usr = u.getUsername();
		this.idV = v;
		this.accepted = false;
	}
	
	//carica
		public Join(String u, int v) {
			this.usr = u;
			this.idV = v;
		}
	
	//carica
	public Join(String u, int v, Boolean a) {
		this.usr = u;
		this.idV = v;
		this.accepted = a;
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
	
	
	public Boolean getAc() {
		return this.accepted;
	}
	
	
	public void setAc(Boolean ac) {
		this.accepted = ac;
	}
}
