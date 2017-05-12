package model;

public class Immobilie {
	
	/**
	 * instanzvariablen
	 */
	private int id;
	private int typ;
	private String immobilienname;
	
	/**
	 * konstruktor
	 * @param id
	 * @param typ
	 * @param immobilienname
	 */
	public Immobilie(int id, int typ, String immobilienname) {
		this.id = id;
		this.typ = typ;
		this.immobilienname = immobilienname;
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
	public int getTyp() {
		return typ;
	}
	public void setTyp(int typ) {
		this.typ = typ;
	}
	public String getImmobilienname() {
		return immobilienname;
	}
	public void setImmobilienname(String immobilienname) {
		this.immobilienname = immobilienname;
	}
	
}
