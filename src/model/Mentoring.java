package model;

public class Mentoring {
	
	/**
	 * instanzvariablen
	 */
	private int mentorId; // derjenige, der mentor ist
	private int menteeId; // derjenige, der mentoring bekommt
	private String thema;
	private int beginnJahr;

	/**
	 * konstruktor
	 * @param mentorId
	 * @param menteeId
	 * @param thema
	 * @param beginnJahr
	 */
	public Mentoring(int mentorId, int menteeId, String thema, int beginnJahr) {
		this.mentorId = mentorId;
		this.menteeId = menteeId;
		this.thema = thema;
		this.beginnJahr = beginnJahr;
	}

	/**
	 * getters & setters
	 */
	public int getMentorId() {
		return mentorId;
	}

	public void setMentorId(int mentorId) {
		this.mentorId = mentorId;
	}

	public int getMenteeId() {
		return menteeId;
	}

	public void setMenteeId(int menteeId) {
		this.menteeId = menteeId;
	}

	public String getThema() {
		return thema;
	}

	public void setThema(String thema) {
		this.thema = thema;
	}

	public int getBeginnJahr() {
		return beginnJahr;
	}

	public void setBeginnJahr(int beginnJahr) {
		this.beginnJahr = beginnJahr;
	}	

}
