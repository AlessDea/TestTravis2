package exceptions;

public class LoginErrorExc extends Exception{

	private static final long serialVersionUID = 1L;
	
	public LoginErrorExc (String message){
		super("An error has occured :" + message);
	}

}
