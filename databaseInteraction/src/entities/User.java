package entities;

public class User {
	
	String username;
	String password;
	String firstName;
	String secondName;
	String email;
	String secretQuestion;
	String secretAnswer;
	int age;
	

	public User(String username, String password) {
		//solo username e password, quindi è un login
		this.username = username;
		this.password = password;
		
	}
	
	public User(String firstName, String secondName, int age, String username, String email, String password, String secretQuestion, String secretAnswer) {
		this.firstName = firstName;
		this.secondName = secondName;
		this.age = age;
		this.username = username;
		this.email = email;
		this.password = password;
		this.secretQuestion = secretQuestion;
		this.secretAnswer = secretAnswer;
	}
	
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getSecondName() {
		return secondName;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String psw) {
		this.password = psw;
	}
	
	
	public String getSQ() {
		return secretQuestion;
	}
	
	public String getSA() {
		return secretAnswer;
	}
	
	public int getAge() {
		return age;
	}

	
	
}
