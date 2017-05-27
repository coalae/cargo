package model;

import java.util.ArrayList;
import java.util.GregorianCalendar;

/** 
 * Die Klasse Kurs bietet das Model fuer einen Fahrkurs der Fahrschule CarGo und
 * enthaelt dessen Daten und die Get- und Set-Methoden.
 * @author Cordula Eggerth
 */
public class Kurs {
	
	/**
	 * Instanzvariablen
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
	 * Konstruktor
	 * @param id
	 * 		KursId
	 * @param kursname
	 * 		Kursname (unique)
	 * @param level
	 * 		Kurslevel (zB Anfaenger, Mittel, Fortgeschritten)
	 * @param beginn
	 * 		Tag des Kursbeginns
	 * @param ende
	 * 		Tag des Kursendes
	 * @param preis
	 * 		Gesamtpreis des Kurs in Euro
	 * @param betreuerId
	 * 		Id des Mitarbeiters, der den Kurs betreut
	 * @param maxAnzahl
	 * 		Max. Teilnehmeranzahl
	 * @param immobilie
	 * 		Id der Immobilie, wo der Kurs stattfindet
	 * @param teilnehmerliste
	 * 		Id-Liste der Teilnehmer (Kunden), die den Kurs gebucht haben
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
	 * getters & setters fuer die instanzvariablen
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
