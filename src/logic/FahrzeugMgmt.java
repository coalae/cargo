package logic;

import java.util.ArrayList;

import model.Fahrzeug;
import repository.DatabaseFahrzeugDAO;
import repository.FahrzeugDAO;


/**
 * Die Klasse FahrzeugMgmt enthaelt die Methoden, mit denen auf die Klasse
 * DatabaseDAO zugegriffen werden kann und die jweiligen Methode zur Verwaltung
 * der Fahrzeuge die an die Controller (bzw. Servlets) weitergeleitet werden.
 * 
 * @author Nikola Babic
 */

public class FahrzeugMgmt {

	private FahrzeugDAO fahrzeugDAO;
	
	
	public FahrzeugMgmt() {
		fahrzeugDAO = new DatabaseFahrzeugDAO();
	}
	
	public ArrayList<Fahrzeug> getfahrzeugListe() {
		ArrayList<Fahrzeug> fahrzeugListe = fahrzeugDAO.getFahrzeugList();
		
		return fahrzeugListe;
	}
	
	
	public void deleteFahrzeug(int id) {
		fahrzeugDAO.deleteFahrzeug(id);
	}
	
	public void addFahrzeug(String marke, String modell, String baujahr, String farbe) {
		fahrzeugDAO.insert(marke, modell, baujahr, farbe);
	}
	
	public Fahrzeug getFahrzeugbyID (int id) {
		return fahrzeugDAO.getFahrzeugbyID(id);	
	}
	
	public void updateMarke(int id, String upmarke) {
		fahrzeugDAO.updateMarke(id, upmarke);
	}
	
	public void updateModell(int id, String upmodell) {
		fahrzeugDAO.updateModell(id, upmodell);
		System.out.println("Bin im modell update Mgmt"+ upmodell + id);
	}

	public void updateJahr(int id, String jahr) {
		fahrzeugDAO.updateMarke(id, jahr);
	}
	
	public void updateFarbe(int id, String upfarbe) {
		fahrzeugDAO.updateFarbe(id, upfarbe);
	}
	
	
	
}
