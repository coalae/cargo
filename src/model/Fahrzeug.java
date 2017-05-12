package model;

public class Fahrzeug {
	
	/**
	 * instanzvariablen
	 */
	private int id;
	private String marke;
	private String modell;
	private String baujahr;
	private String farbe;

	/**
	 * konstruktor
	 * @param fahrzeugId
	 * @param marke
	 * @param modell
	 * @param baujahr
	 * @param farbe
	 */
	public Fahrzeug(int id, String marke, String modell, String baujahr, String farbe) {
		this.id = id;
		this.marke = marke;
		this.modell = modell;
		this.baujahr = baujahr;
		this.farbe = farbe;
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

	public String getMarke() {
		return marke;
	}

	public void setMarke(String marke) {
		this.marke = marke;
	}

	public String getModell() {
		return modell;
	}

	public void setModell(String modell) {
		this.modell = modell;
	}

	public String getBaujahr() {
		return baujahr;
	}

	public void setBaujahr(String baujahr) {
		this.baujahr = baujahr;
	}

	public String getFarbe() {
		return farbe;
	}

	public void setFarbe(String farbe) {
		this.farbe = farbe;
	}	

}
