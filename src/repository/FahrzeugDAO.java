package repository;

import java.util.ArrayList;

import model.Fahrzeug;

/** 
 * Das Interface FahrzeugDAO gibt die Methoden vor, die im DatabaseFahrzeugDAO implementiert werden.
 * Die Methoden dienen zum Speichern, Loeschen, Aendern von Instanzen der Klasse Fahrzeug.
 * @author Nikola Babic
 */


public interface FahrzeugDAO {
	 
	
	public void insert(String marke, String modell, String baujahr, String farbe);
	 
	
	public void deleteFahrzeug (int id);
	
	public ArrayList<Fahrzeug> getFahrzeugList ();
	
	public Fahrzeug getFahrzeugbyID(int id);
	
	public void updateMarke (int id, String upmarke);
	
	public void updateModell (int id, String upmodel);
	
	public void updateJahr (int id, String jahr);
	
	public void updatefarbe (int id, String upfarbe);
	
	
	
	
	
}
