package entities;

public class Follow {
	
	String follower;
	String followed;
	String cognomeFollower;
	String nomeFollower;
	
	public Follow(User fl, User flwd) {
		this.follower = fl.getUsername();
		this.followed = flwd.getUsername();
		
	}
	
	public Follow(String cogn, String nom) {
		this.cognomeFollower = cogn;
		this.nomeFollower = nom;
	}
	
	public String getFl(){
		return this.follower;
	}
	
	public void setFl(String usr){
		this.follower = usr;
	}
	
	public void setFlwd(String usr) {
		this.followed = usr;
	} 
	
	public String getFlwd() {
		return this.followed;
	}

}
