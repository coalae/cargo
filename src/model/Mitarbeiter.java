package model;

import java.util.GregorianCalendar;

public class Mitarbeiter {
	
	/**
	 * instanzvariablen
	 */
	private int id;
	private String vorname;
	private String nachname;
	private int svnr;
	private String spezialisierung;
	private int mitarbeitertyp; // typ1: fahrlehrer, typ2: admin
	private GregorianCalendar geburtsdatum;
	private String username;
	private String password;
	private boolean active;

	/**
	 * konstruktor
	 * @param id
	 * @param vorname
	 * @param nachname
	 * @param svnr
	 * @param spezialisierung
	 * @param mitarbeitertyp
	 * @param geburtsdatum
	 * @param username
	 * @param password
	 * @param active
	 */
	public Mitarbeiter(int id, String vorname, String nachname, int svnr, String spezialisierung, int mitarbeitertyp,
			GregorianCalendar geburtsdatum, String username, String password, boolean active) {
		this.id = id;
		this.vorname = vorname;
		this.nachname = nachname;
		this.svnr = svnr;
		this.spezialisierung = spezialisierung;
		this.mitarbeitertyp = mitarbeitertyp;
		this.geburtsdatum = geburtsdatum;
		this.username = username;
		this.password = password;
		this.active = active;
	}

	/**
	 * getters & setters
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

	public int getSvnr() {
		return svnr;
	}

	public void setSvnr(int svnr) {
		this.svnr = svnr;
	}

	public String getSpezialisierung() {
		return spezialisierung;
	}

	public void setSpezialisierung(String spezialisierung) {
		this.spezialisierung = spezialisierung;
	}

	public int getMitarbeitertyp() {
		return mitarbeitertyp;
	}

	public void setMitarbeitertyp(int mitarbeitertyp) {
		this.mitarbeitertyp = mitarbeitertyp;
	}

	public GregorianCalendar getGeburtsdatum() {
		return geburtsdatum;
	}

	public void setGeburtsdatum(GregorianCalendar geburtsdatum) {
		this.geburtsdatum = geburtsdatum;
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
