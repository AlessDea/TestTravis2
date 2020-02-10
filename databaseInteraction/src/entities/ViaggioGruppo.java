package entities;

import java.util.Date;

import travelsFactory.TravelBase;

public class ViaggioGruppo implements TravelBase{
	
	int idViaggioGr;
	String creatore;
	float prezzo;
	int numMaxUt;
	Boolean pagAnt;
	float impAnt;
	Date scadenza;
	Date dataV;
	String descr;
	
	public ViaggioGruppo () {}

	public ViaggioGruppo (int idV) {
		this.idViaggioGr = idV;
	}

	
//	creazione di un viaggio
	public ViaggioGruppo(User usr, float pr, int numMax, Boolean pagA, float impA, Date sc, Date dV, String descr) {
		
		this.creatore = usr.getUsername();
		this.prezzo = pr;
		this.numMaxUt = numMax;
		this.pagAnt = pagA;
		this.impAnt = impA;
		this.scadenza = sc;
		this.dataV = dV;
		this.descr = descr;
		
	}
	

//	in caso di caricamento di un viaggio si usa questo
	public ViaggioGruppo(int idV, String usr, float pr, int numMax, Boolean pagA, float impA, Date sc, Date dV, String descr) {
		
		this.idViaggioGr = idV;
		this.creatore = usr;
		this.prezzo = pr;
		this.numMaxUt = numMax;
		this.pagAnt = pagA;
		this.impAnt = impA;
		this.scadenza = sc;
		this.dataV = dV;
		this.descr = descr;
		
	}
	
	public void useTravel() {
		System.out.println("Creato Viaggio di gruppo");
	}
	
	public int getIdV() {
		return this.idViaggioGr;
	}
	
	public void setIdV(int id) {
		this.idViaggioGr = id;
	} 
	
	public String getCr() {
		return this.creatore;
	}
	
	public void setCr(String cr) {
		this.creatore = cr;
	}
	
	public float getPre() {
		return this.prezzo;
	}
	
	public void setPre(float pr) {
		this.prezzo = pr;
	}
	
	public int getNm() {
		return this.numMaxUt;
	}
	
	public void setNm(int nm) {
		this.numMaxUt = nm;
	}
	
	public Boolean getPagA() {
		return this.pagAnt;
	}
	
	public void setPagA(Boolean p) {
		this.pagAnt = p;
	}
	
	public float getImpA() {
		return this.impAnt;
	}
	
	public void setImpA(float pag) {
		this.impAnt = pag;
	}
	
	public Date getScad() {
		return this.scadenza;
	}
	
	public void setScad(Date d) {
		this.scadenza = d;
	}
	
	public Date getDate() {
		return this.dataV;
	}
	
	public void setDate(Date d) {
		this.dataV = d;
	}
	
	public String getDesc() {
		return this.descr;
	}
	
	public void setDesc(String d) {
		this.descr = d;
	}
}
