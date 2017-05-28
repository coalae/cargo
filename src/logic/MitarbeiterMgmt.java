package logic;

import model.Kunde;
import model.Mitarbeiter;
import repository.DatabaseMitarbeiterDAO;

import repository.MitarbeiterDAO;

public class MitarbeiterMgmt {

	private MitarbeiterDAO mitarbeiterdao;

	/**
	 * Konstruktor
	 */
	public MitarbeiterMgmt(){
		setMitarbeiterdao();
	}
	
	/** 
	 * Get-Methode fuer die Instanzvariable kundendao
	 * @return kundendao
	 */
	public MitarbeiterDAO getMitarbeiterdao(){
		return mitarbeiterdao;
	}

	/**
	 * Set-Methode fuer die Instanzvariable kundendao
	 */
	public void setMitarbeiterdao(){
		this.mitarbeiterdao = new DatabaseMitarbeiterDAO();
	}
	
	
	public Mitarbeiter getMitarbeiterById(int id){
		Mitarbeiter mitarbeiter = mitarbeiterdao.getMitarbeiterById(id);
		return mitarbeiter;
	}
}
