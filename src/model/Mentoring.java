package model;

import java.util.Date;

public class Mentoring {
	

	/**
	 * MentoringId
	 */
	private int mentoringId;
	/**
	 * Id des Mentors
	 */
	private int mentorId;
	
	/**
	 * Id des Mentee
	 */
	private int menteeId; 
	
	/**
	 * Thema des Mentorings
	 */
	private String thema;
	
	/**
	 * Beginnjahr des Mentorings
	 */
	private Date beginnJahr;

	/**
	 * Konstruktor
	 * @param mentoringId
	 * @param mentorId
	 * @param menteeId
	 * @param thema
	 * @param beginnJahr
	 */
	public Mentoring(int mentoringId, int mentorId, int menteeId, String thema, Date beginnJahr) {
		this.mentoringId= mentoringId;
		this.mentorId = mentorId;
		this.menteeId = menteeId;
		this.thema = thema;
		this.beginnJahr = beginnJahr;
	}

	/**
	 * getters & setters
	 */
	
	public int getMentoringId(){
		return mentoringId;
	}
	
	public void setMentoringId (int mentoringId){
		this.mentoringId = mentoringId;
	}
	
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

	public Date getBeginnJahr() {
		return beginnJahr;
	}

	public void setBeginnJahr(Date beginnJahr) {
		this.beginnJahr = beginnJahr;
	}	

}
