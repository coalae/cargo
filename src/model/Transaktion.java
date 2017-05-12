package model;

import java.util.GregorianCalendar;

public class Transaktion {
	
	/**
	 * instanzvariablen
	 */
	private int id;
	private String betreff;
	private int senderId;
	private double betrag;
	private GregorianCalendar transaktionsDatum;

	/**
	 * konstruktor
	 * @param id
	 * @param betreff
	 * @param senderId
	 * @param betrag
	 * @param transaktionsDatum
	 */
	public Transaktion(int id, String betreff, int senderId, double betrag, GregorianCalendar transaktionsDatum) {
		this.id = id;
		this.betreff = betreff;
		this.senderId = senderId;
		this.betrag = betrag;
		this.transaktionsDatum = transaktionsDatum;
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

	public String getBetreff() {
		return betreff;
	}

	public void setBetreff(String betreff) {
		this.betreff = betreff;
	}

	public int getSenderId() {
		return senderId;
	}

	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}

	public double getBetrag() {
		return betrag;
	}

	public void setBetrag(double betrag) {
		this.betrag = betrag;
	}

	public GregorianCalendar getTransaktionsDatum() {
		return transaktionsDatum;
	}

	public void setTransaktionsDatum(GregorianCalendar transaktionsDatum) {
		this.transaktionsDatum = transaktionsDatum;
	}
	
}
