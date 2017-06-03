package model;

public class Fahrschulkonto {  // es darf nur 1 Instanz vom Fahrschulkonto gebene >> Singleton?

	/** 
	 * instanzvariablen
	 */
	private int kontonr;
	private double kontostand;
	private String kontoname ;
	
	/**
	 * konstruktor
	 * @param kontonr
	 * @param kontostand
	 * @param kontoname
	 */
	public Fahrschulkonto(int kontonr, double kontostand, String kontoname) {
		this.kontonr = kontonr;
		this.kontostand = kontostand;
		this.kontoname = kontoname;
	}

	/**
	 * getters & setters
	 */
	public int getKontonr() {
		return kontonr;
	}

	public void setKontonr(int kontonr) {
		this.kontonr = kontonr;
	}

	public double getKontostand() {
		return kontostand;
	}

	public void setKontostand(double kontostand) {
		this.kontostand = kontostand;
	}

	public String getKontoname() {
		return kontoname;
	}

	public void setKontoname(String kontoname) {
		this.kontoname = kontoname;
	}
	
}
