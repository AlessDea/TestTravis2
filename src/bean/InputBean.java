package bean;

public class InputBean {
	
		
	 		
	public Boolean checkPass(String psw) {
		
		if(psw.length() < 6) {
			//non va bene, fai qualcosa
			return false;
		}
		
		char c;
		int chars = 0, numbers = 0;
		
		for(int i = 0; i < psw.length(); i++) {
			
			c = psw.charAt(i);

			if(Character.isWhitespace(c)) { 
				System.out.println(psw);
				return false;
			}
			
			if(Character.isLetter(c)) {
				chars++;
			}else if(Character.isDigit(c)) {
				numbers++;
			}
		
		}
		
		if(numbers < 3 || chars < 3) return false;
		System.out.println(psw);
		return true;
		
	}
		
		


}
