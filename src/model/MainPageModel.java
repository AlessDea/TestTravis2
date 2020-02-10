package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.JoinDao;
import dao.UserDao;
import dao.ViaggiDao;
import dao.ViaggiGrDao;
import entities.User;
import entities.Viaggio;
import entities.ViaggioGruppo;
import exceptions.DuplicateRecord;


public class MainPageModel {
	
	private User me;
	private Viaggio v;
	private ViaggioGruppo vg;

	
	
	private boolean isCreateble;

	
	public MainPageModel() {
		isCreateble = true;
	}

	
	
	
	public void changePswd(String newPswd, String oldPswd) throws Exception {
		
		if (me.getPassword().contentEquals(oldPswd)) {
			
			me.setPassword(newPswd);
			UserDao.modifPsw(me);
		
		}
		
	}
	
	
	
	public void createNewUser(String firstName, String secondName, int age, String username, String email, String password, String secretQuestion, String secretAnswer) throws Exception {
		if(isCreateble == true) {
			
			try {
				
//se viene lanciata l'eccezione da usernameChecker vuol dire che 
//bisogna cambiare username
				UserDao.usernameChecker(username);
			
			} catch (DuplicateRecord de) {
			
				System.out.println(de.getMessage());
			
			} catch (Exception e) {
			
				e.printStackTrace();
				//qua bisognerebbe lanciare un eccezione per far inserire nuovamente l'username
			
			}
			
//altrimenti vai avanti e crea l user
			me = UserDao.registerUsr(firstName, secondName, age, username, email, password, secretQuestion, secretAnswer);
			System.out.println("Created new user: " + me.getUsername());
			
		}else {
			
			System.out.println("Enable to create a new user");
		}
		
	}	

	
	
	
	//bisogna vedere se deve tornare qualcosa al controller
	public void login(String u, String p) throws Exception{
		
		me = UserDao.logUsr(u, p);
		
		System.out.println("Welcome back in EasyTravel " + me.getUsername() + "!");
		
	}
	
	
	
	
	
	
	
	/* Crea un oggetto di tipo viaggio che non verrà scritto sul database fino a quando 
	 * non verrà salavato.
	 * Ovviamente può esserci solo un viaggio alla volta in 'costruzione'.
	 * Questo metodo viene chiamato quando l'utente, dopo aver inserito i dati preliminari (budget, nome viaggio, ...) 
	 * nel form della main page, clicca sul button 'Prosegui'
	*/
	public void createV(String dest) {
		
		v = new Viaggio(me.getUsername(), dest);
	
	}
	
	
	
	public void saveV(String desc, Boolean done){
		
		
		v.setDesc(desc);
		//un utente potrebbe caricare un viaggio che ha gia fatto
		v.setStatus(done);
		
		try {
			ViaggiDao.CreateTravel(v);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public List<Viaggio> loadMyOldT(){
		
		List<Viaggio> lst = new ArrayList<Viaggio>();
		try {
			
			lst = ViaggiDao.retreiveTravells(me);
		
		} catch (Exception e) {

			e.printStackTrace();
			
		}
		
		return lst;
	}
	
	
	
	public List<Viaggio> loadTravells(int by) throws Exception{
		
		List<Viaggio> lst = new ArrayList<Viaggio>();
		
		switch(by) {
			case 1:
				//by more likes
				lst = ViaggiDao.retreiveTravellsByLikes();
				break;
			case 2:
				//my followers
				lst = ViaggiDao.retreiveTravellsByFoll(me);
				break;
			default:
				//questo potrei toglierlo perche dico alla classe bean di controllare l'input
				Exception e = new Exception("Invalid input");
            	throw e;
				
		}	
		
		return lst;
	}
	
	
	
	public void createVG(String dest, float pr, int numPass, Boolean pagAnt, float impAnt, Date scad, Date dV, String desc) {
		//se pag (pagamento anticipato) è false, allora impAnt è 0,00.
		if(pagAnt == true)
			vg = new ViaggioGruppo(me, pr, numPass, pagAnt, impAnt, scad, dV, desc);
		else
			vg = new ViaggioGruppo(me, pr, numPass, pagAnt, 0.00f, scad, dV, desc);

	}
	
	
	
	public void saveVG() {
		
		try {
			ViaggiGrDao.CreateTravel(vg);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
	
	
	public void joinVg(int idVg) {
		
		try {
			JoinDao.joinVGruppo(me, idVg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public void acceptGrJoin(String usr, int idVg) {
		
		try {
			JoinDao.acceptJoin(usr, idVg);
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		//deve esserci un metodo che invii una mail/notifica all'utente appena inserito fra i partecipanti
		//sendConfirmation(User u);
		
	}
	
	
	public void declineGrJoin(String usr, int idVg) {
		
		try {
			JoinDao.declineJoin(usr, idVg);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		//notificare la mancata accettazione
		//sendDecline();
		
	}
	
	
	public void deleteJoin(int idVg) {
		//cancellare la prenotazione al join
		
		vg = new ViaggioGruppo(idVg);
		
		//prima controlla che l'utente sia in tempo per cancellarsi (confrontanto la scadenza del viaggio
		//e la data "odierna")
		try {
			ViaggiGrDao.retreiveOneGrTravel(vg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//se sopra tutto va bene cancella il 'Join'
		try {
			JoinDao.deleteMyJoin(me, vg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
}
