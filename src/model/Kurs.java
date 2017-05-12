package model;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Kurs {
	
	/**
	 * instanzvariablen
	 */
	private int id;
	private String kursname;
	private String level;
	private GregorianCalendar beginn;
	private GregorianCalendar ende;
	private double preis;  // gesamtpreis des kurs
	private int betreuerId;
	private int maxAnzahl; // maximale anzahl an teilnehmern
	private int immobilie;
	private ArrayList<Integer> teilnehmerliste;

	/**
	 * konstruktor
	 * @param id
	 * @param kursname
	 * @param level
	 * @param beginn
	 * @param ende
	 * @param preis
	 * @param betreuerId
	 * @param maxAnzahl
	 * @param immobilie
	 * @param teilnehmerliste
	 */
	public Kurs(int id, String kursname, String level, GregorianCalendar beginn, GregorianCalendar ende, double preis, int betreuerId,
			int maxAnzahl, int immobilie, ArrayList<Integer> teilnehmerliste) {
		this.id = id;
		this.kursname = kursname;
		this.level = level;
		this.beginn = beginn;
		this.ende = ende;
		this.preis = preis;
		this.betreuerId = betreuerId;
		this.maxAnzahl = maxAnzahl;
		this.immobilie = immobilie;
		this.teilnehmerliste = teilnehmerliste;
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

	public String getKursname() {
		return kursname;
	}

	public void setKursname(String kursname) {
		this.kursname = kursname;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public GregorianCalendar getBeginn() {
		return beginn;
	}

	public void setBeginn(GregorianCalendar beginn) {
		this.beginn = beginn;
	}

	public GregorianCalendar getEnde() {
		return ende;
	}

	public void setEnde(GregorianCalendar ende) {
		this.ende = ende;
	}

	public double getPreis() {
		return preis;
	}

	public void setPreis(double preis) {
		this.preis = preis;
	}

	public int getBetreuerId() {
		return betreuerId;
	}

	public void setBetreuerId(int betreuerId) {
		this.betreuerId = betreuerId;
	}

	public int getMaxAnzahl() {
		return maxAnzahl;
	}

	public void setMaxAnzahl(int maxAnzahl) {
		this.maxAnzahl = maxAnzahl;
	}

	public int getImmobilie() {
		return immobilie;
	}

	public void setImmobilie(int immobilie) {
		this.immobilie = immobilie;
	}

	public ArrayList<Integer> getTeilnehmerliste() {
		return teilnehmerliste;
	}

	public void setTeilnehmerliste(ArrayList<Integer> teilnehmerliste) {
		this.teilnehmerliste = teilnehmerliste;
	}

}
