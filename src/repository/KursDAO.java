package repository;

import java.util.ArrayList;

import model.Kurs;

/** 
 * Das Interface KursDAO gibt die Methoden vor, die im DatabaseKursDAO implementiert werden.
 * Die Methoden dienen zum Speichern, Loeschen, Aendern von Instanzen der Klasse Kurs und 
 * zum Hinzufuegen und Loeschen von Teilnehmern an einem Kurs.
 * @author Cordula Eggerth
 */
public interface KursDAO {

	/**
	 * Liste aller Kurse aus der DB holen
	 * @return ArrayList von Kursen
	 */
	public ArrayList<Kurs> getKursListe();
	
	/**
	 * Kurs anhand der als int uebergebenen id suchen
	 * @param id
	 * @return Kurs
	 */
	public Kurs getKursById(int id);
	
	/**
	 * Neuen Kurs hinzufuegen
	 * @param kurs
	 */
	public void addKurs(Kurs kurs);
	
	/**
	 * Bestehenden Kurs loeschen  
	 * @param id
	 */
	public void deleteKurs(int id);
	
	/**
	 * Bestehenden Kurs updaten bzw. aendern 
	 * @param kurs
	 */
	public void updateKurs(Kurs kurs);
	
	/**
	 * Teilnehmer (anhand der kundenId) zu einem bestimmten Kurs hinzufuegen
	 * @param kundenId
	 * @param kursId
	 */
	public void addTeilnehmerToKurs(int kundenId, int kursId);

	/**
	 * Teilnehmer (anhand der kundenId) von einem bestimmten Kurs loeschen
	 * @param kundenId
	 * @param kursId
	 */
	public void deleteTeilnehmerFromKurs(int kundenId, int kursId);
	
	/**
	 * Liste aller Kurse, an denen ein bestimmter Teilnehmer (kundenId) teilnimmt, aus der DB holen
	 * @param kundenId
	 * @return
	 */
	public ArrayList<Integer> getKurslisteForTeilnehmer (int kundenId);
	
}
