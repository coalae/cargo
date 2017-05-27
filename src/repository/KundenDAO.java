package repository;

import java.util.ArrayList;

import model.Kunde;

/**
 * Das Interface KundenDAO bietet Methoden fuer das Speichern, Loeschen, Veraendern von 
 * Instanzen der Klasse Kunde.
 * @author Cordula Eggerth
 */
public interface KundenDAO {

	/**
	 * Liste aller Kunden aus der DB holen
	 * @return ArrayList von Kunden
	 */
	public ArrayList<Kunde> getKundenListe();
	
	/**
	 * Kunde anhand der als int uebergebenen id suchen
	 * @param id
	 * @return Kunde
	 */
	public Kunde getKundeById(int id);

	/**
	 * Neuen Kunde hinzufuegen
	 * @param Kunde
	 */
	public void addKunde(Kunde kunde);
	
	/**
	 * Bestehenden Kunde loeschen  
	 * @param id
	 */
	public void deleteKunde(int id);
	
	/**
	 * Bestehenden Kunde updaten bzw. aendern 
	 * @param Kunde
	 */
	public void updateKunde(Kunde kunde);
	
}
