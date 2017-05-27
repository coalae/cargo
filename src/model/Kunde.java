package model;

/** 
 * Die Klasse Kunde bietet das Model fuer den Kunde der Fahrschule CarGo und
 * enthaelt dessen Daten und die Get- und Set-Methoden.
 * @author Cordula Eggerth
 */
public class Kunde {
	
	/**
	 * Instanzvariablen
	 */
	private int id;
	private String vorname;
	private String nachname;
	private int iban;
	private String bic;
	private String username;
	private String password;
	private boolean active;

	/**
	 * Konstruktor
	 * @param id
	 * 		Kunden Id
	 * @param vorname
	 * 		Vorname des Kunde
	 * @param nachname
	 * 		Nachname des Kunde
	 * @param iban
	 * 		IBAN (Kontonummer) des Kunde (als int)
	 * @param bic
	 * 		BIC (Bank Code) des Kunde (als String)
	 * @param username
	 * 		Username des Kunde (unique)
	 * @param password
	 * 		Password des Kunde
	 * @param active
	 * 		Angabe, ob Kunde aktiv oder gesperrt 
	 */
	public Kunde(int id, String vorname, String nachname, int iban, String bic, String username, String password, boolean active) {
		this.id = id;
		this.vorname = vorname;
		this.nachname = nachname;
		this.iban = iban;
		this.bic = bic;
		this.username = username;
		this.password = password;
		this.active = active;
	}

	/**
	 * getters & setters fuer die instanzvariablen
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public int getIban() {
		return iban;
	}

	public void setIban(int iban) {
		this.iban = iban;
	}

	public String getBic() {
		return bic;
	}

	public void setBic(String bic) {
		this.bic = bic;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
