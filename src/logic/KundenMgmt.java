package logic;

import java.util.ArrayList;

import model.Kunde;
import model.Kurs;
import repository.DatabaseKundenDAO;
import repository.KundenDAO;

/**
 * Die Klasse KundenMgmt enthaelt die Methoden, mit denen auf die Klasse DatabaseKundenDAO zugegriffen
 * werden kann und die die Berechnung der Werte macht, die an die Controller (bzw. Servlets)
 * weitergeleitet werden. 
 * @author Cordula Eggerth
 */
public class KundenMgmt {
	
	/**
	 * Instanzvariable
	 */
	private KundenDAO kundendao;

	/**
	 * Konstruktor
	 */
	public KundenMgmt(){
		setKundendao();
	}
	
	/** 
	 * Get-Methode fuer die Instanzvariable kundendao
	 * @return kundendao
	 */
	public KundenDAO getKundendao(){
		return kundendao;
	}

	/**
	 * Set-Methode fuer die Instanzvariable kundendao
	 */
	public void setKundendao(){
		this.kundendao = new DatabaseKundenDAO();
	}	
	
	/**
	 * getKundenListe gibt eine Liste aller Kunden zurueck.
	 * @return ArrayList von Kunden
	 */	
	public ArrayList<Kunde> getKundenListe(){
		ArrayList<Kunde> kundenliste=kundendao.getKundenListe();
		return kundenliste;
	}
	
	/**
	 * getKundeById gibt einen Kunde anhand der uebergebenen Id zurueck.
	 * @param id
	 * @return kunde
	 */
	public Kunde getKundeById(int id){
		Kunde kunde = kundendao.getKundeById(id);
		return kunde;
	}
	
	/**
	 * getKundeByUsername gibt einen Kunde anhand des uebergebenen Username zurueck.
	 * @param username
	 * @return kunde
	 */
	public Kunde getKundeByUsername(String username){
		Kunde suchKunde=null;
		ArrayList<Kunde> kundenliste=getKundenListe();
		for(int i=0;i<kundenliste.size();i++){
			if(kundenliste.get(i).getUsername().equals(username)){
				suchKunde=kundenliste.get(i);
			}
		}
		return suchKunde;
	}

	/**
	 * getKundeByKeyword gibt einen Kunde anhand des uebergebenen keyword zurueck.
	 * @param keyword
	 * @return suchliste
	 */
	public ArrayList<Kunde> getKundeByKeyword(String keyword){ // sucht in Vorname, Nachname und Username nach Keyword
		ArrayList<Kunde> suchliste=new ArrayList<Kunde>();
		ArrayList<Kunde> gesamtliste=getKundenListe();
		for(int i=0;i<gesamtliste.size();i++){
			if((gesamtliste.get(i).getVorname().toLowerCase().contains(keyword.toLowerCase()) || (gesamtliste.get(i).getVorname().toLowerCase().contains(keyword.toLowerCase()) || gesamtliste.get(i).getNachname().toLowerCase().contains(keyword.toLowerCase())))){
				suchliste.add(gesamtliste.get(i));
			}
		}
		return suchliste;
	}
	
	/**
	 * Kunde hinzufuegen.
	 * @param kunde
	 */
	public void addKunde(Kunde kunde){
		kundendao.addKunde(kunde);
	}
	
	/**
	 * Kunde loeschen.
	 * @param id
	 */
	public void deleteKunde(int id){
		kundendao.deleteKunde(id);
	}
	
	/**
	 * Kunde updaten bzw. aendern.
	 * @param kunde
	 */
	public void updateKunde(Kunde kunde){
		kundendao.updateKunde(kunde);	
	}
	
	/**
	 * Check, ob Kunden (username) in der DB schon existiert.
	 * @param username
	 * @return
	 */
	public boolean checkKundeExists(String username){
		boolean exists=false;
		
		ArrayList<Kunde> kundenliste=getKundenListe();
		
		for(int i=0;i<kundenliste.size();i++){
			if(kundenliste.get(i).getUsername().equals(username)){
				exists=true;
			}
		}	
		return exists;
	}
	

	/* main Funktion fuer Tests	*/ 
	public static void main(String[] args){
		KundenMgmt kundenmgmt = new KundenMgmt();
		/* TEST getKundenListe
		ArrayList<Kunde> liste=kundenmgmt.getKundenListe();
		for(int i=0;i<liste.size();i++){
			System.out.println(liste.get(i).getId());
			System.out.println(liste.get(i).getVorname());
			System.out.println(liste.get(i).getNachname());
		}
		*/
		
		/* TEST getKundeById
		Kunde kunde = kundenmgmt.getKundeById(1);
		System.out.println(kunde.getId());
		System.out.println(kunde.getVorname());
		System.out.println(kunde.getNachname());
		*/	
		
		/* TEST getKundeByUsername 
		String username="lisam";
		Kunde unkunde=kundenmgmt.getKundeByUsername(username);
		System.out.println(unkunde.getId());
		System.out.println(unkunde.getVorname());
		System.out.println(unkunde.getNachname());
		*/

		/* TEST addKunde - kunde mit dummyId 0 uebergeben 
		Kunde neuerKunde=new Kunde(0,"Tara","Tisch",12141,"meinBIC","tarat","tara",true);
		kundenmgmt.addKunde(neuerKunde);
		*/

		/* TEST deleteKunde */
		// kundenmgmt.deleteKunde(6);

		/* TEST updateKunde 
		Kunde kundeToUpdate=kundenmgmt.getKundeById(7);
		kundeToUpdate.setPassword("taraUpdated");
		kundenmgmt.updateKunde(kundeToUpdate);
		*/
		
		/* TEST checkKundeExists 
		System.out.println(kundenmgmt.checkKundeExists("tarat"));
		*/
	}
   
}
