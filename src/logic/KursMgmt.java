package logic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import model.Kurs;
import repository.DatabaseKursDAO;
import repository.KursDAO;

/**
 * Die Klasse KursMgmt enthaelt die Methoden, mit denen auf die Klasse DatabaseDAO zugegriffen
 * werden kann und die die Berechnung der Werte macht, die an die Controller (bzw. Servlets)
 * weitergeleitet werden. 
 * @author Cordula Eggerth
 */
public class KursMgmt {
	
	/**
	 * Instanzvariable
	 */
	private KursDAO kursdao;

	/**
	 * Konstruktor
	 */
	public KursMgmt(){
		setKursdao();
	}
	
	/** 
	 * Get-Methode fuer die Instanzvariable kursdao
	 * @return kursdao
	 */
	public KursDAO getKursdao(){
		return kursdao;
	}

	/**
	 * Set-Methode fuer die Instanzvariable kursdao
	 */
	public void setKursdao(){
		this.kursdao = new DatabaseKursDAO();
	}	
	
	/**
	 * getKursListe gibt eine Liste aller Kurse zurueck.
	 * @return ArrayList von Kurse
	 */
	public ArrayList<Kurs> getKursListe(){
		ArrayList<Kurs> kursliste=kursdao.getKursListe();
		return kursliste;
	}
	
	/**
	 * getKursById gibt einen Kurs anhand der uebergebenen Id zurueck.
	 * @param id
	 * @return kurs
	 */
	public Kurs getKursById(int id){
		Kurs kurs = kursdao.getKursById(id);
		return kurs;
	}
	
	/**
	 * getKursByKursname gibt einen Kurs anhand des uebergebenen Kursname zurueck.
	 * @param kursname
	 * @return kurs
	 */
	public Kurs getKursByKursname(String kursname){
		Kurs suchKurs=null;
		ArrayList<Kurs> kursliste=getKursListe();
		for(int i=0;i<kursliste.size();i++){
			if(kursliste.get(i).getKursname().equals(kursname)){
				suchKurs=kursliste.get(i);
			}
		}
		return suchKurs;
	}
	
	/**
	 * getKursByKursnameAndBeginn gibt einen Kurs anhand des uebergebenen Kursname und Beginndatum zurueck.
	 * @param kursname
	 * @param beginn
	 * @return kursliste
	 */
	public ArrayList<Kurs> getKursByKursnameAndBeginn(String kursname, String beginn){
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy"); 
		ArrayList<Kurs> suchListe=null;
		ArrayList<Kurs> kursliste=getKursListe();
		for(int i=0;i<kursliste.size();i++){
			if(kursliste.get(i).getKursname().equals(kursname) && sdf.format(getKursByKursname(kursname).getBeginn().getTime()).equals(beginn)){
				suchListe.add(kursliste.get(i));
			}
		}
		return suchListe;
	}
	
	/**
	 * getKursByKeyword sucht in Kursname und Level Instanzvariablen nach dem Keyword und gibt
	 * eine Liste von Kursen zurueck.
	 * @param keyword
	 * @return kursliste
	 */
	public ArrayList<Kurs> getKursByKeyword(String keyword){ // sucht in Kursname und Level nach Keyword
		ArrayList<Kurs> suchliste=new ArrayList<Kurs>();
		ArrayList<Kurs> gesamtliste=getKursListe();
		for(int i=0;i<gesamtliste.size();i++){
			if((gesamtliste.get(i).getKursname().toLowerCase().contains(keyword.toLowerCase()) || gesamtliste.get(i).getLevel().toLowerCase().contains(keyword.toLowerCase()))){
				suchliste.add(gesamtliste.get(i));
			}
		}
		return suchliste;
	}
	
	/**
	 * Kurs hinzufuegen
	 * @param kurs
	 */
	public void addKurs(Kurs kurs){
		kursdao.addKurs(kurs);
	}
	
	/**
	 * Kurs loeschen
	 * @param id
	 */
	public void deleteKurs(int id){
		kursdao.deleteKurs(id);
	}
	
	/**
	 * Kurs updaten bzw. aendern
	 * @param kurs
	 */
	public void updateKurs (Kurs kurs){ // instanzvariablen update ohne teilnehmerliste-update
		kursdao.updateKurs(kurs);	
	}
	
	/**
	 * Check, ob Kurs schon existiert anhand der uebergebenen Kursname.
	 * @param kursname
	 * @return boolean
	 */
	public boolean checkKursExists(String kursname){
		boolean exists=false;
		
		ArrayList<Kurs> kursliste=getKursListe();
		
		for(int i=0;i<kursliste.size();i++){
			if(kursliste.get(i).getKursname().equals(kursname)){
				exists=true;
			}
		}	
		return exists;
	}
	
	/**
	 * Teilnehmer (kundenId) zum Kurs hinzufuegen - Kurs buchen.
	 * @param kundenId
	 * @param kursId
	 */
	public void addTeilnehmerToKurs(int kundenId, int kursId){
		kursdao.addTeilnehmerToKurs(kundenId, kursId);
	}
	
	/**
	 * Teilnehmer (kundenId) vom Kurs loeschen - Kursteilnahme stornieren.
	 * @param kundenId
	 * @param kursId
	 */
	public void deleteTeilnehmerFromKurs(int kundenId, int kursId){
		kursdao.deleteTeilnehmerFromKurs(kundenId, kursId);
	}
	
	/**
	 * Teilnehmerliste von kundenIds, die am Kurs teilnehmen, generieren.
	 * @param kursId
	 * @return teilnehmerliste (liste von Ids)
	 */
	public ArrayList<Integer> getTeilnehmerlisteForKurs(int kursId){
		Kurs kurs=kursdao.getKursById(2);
		// if(kurs.getTeilnehmerliste()!=null || kurs.getTeilnehmerliste().size()!=0){
			ArrayList<Integer> teilnehmerliste=kurs.getTeilnehmerliste();	
		// }
		return teilnehmerliste;
	}
	
	/**
	 * Liste von Kursen, an denen ein bestimmter Kunde (kundenId) teilnimmt, generieren.
	 * @param kundenId
	 * @return meineKurse
	 */
	public ArrayList<Kurs> getKurslisteForTeilnehmer(int kundenId){
		ArrayList<Integer> meineKurslisteIds = kursdao.getKurslisteForTeilnehmer(kundenId);
		
		ArrayList<Kurs> meineKurse = new ArrayList<Kurs>();
		for(int i=0;i<meineKurslisteIds.size();i++){
			meineKurse.add(kursdao.getKursById(meineKurslisteIds.get(i)));
		}
		return meineKurse;
	}
		

	/* main Funktion fuer Tests	*/ 
	// public static void main(String[] args){
	// 	KursMgmt kursmgmt = new KursMgmt();
	
		/* TEST getKursListe
		ArrayList<Kurs> liste=kursmgmt.getKursListe();
		for(int i=0;i<liste.size();i++){
			System.out.println(liste.get(i).getId());
			System.out.println(liste.get(i).getKursname());
			System.out.println(liste.get(i).getLevel());
		}
		*/
		
		/* TEST getKursById 
		Kurs kurs = kursmgmt.getKursById(1);
		System.out.println(kurs.getId());
		System.out.println(kurs.getKursname());
		System.out.println(kurs.getLevel());
		*/	
		
		/* TEST getKursByKursname 
		String kursname="PWK-Kurs2";
		Kurs unkurs=kursmgmt.getKursByKursname(kursname);
		System.out.println(unkurs.getId());
		System.out.println(unkurs.getKursname());
		System.out.println(unkurs.getLevel());
		*/

		/* TEST addKurs - kurs mit dummyId 0 uebergeben 
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy"); 
        String beginnstr="02.06.2017";
        GregorianCalendar beginncal = new GregorianCalendar();
	    beginncal.setLenient(false);
	    try {
			beginncal.setTime(sdf.parse(beginnstr));
		} catch (ParseException e) {
			e.printStackTrace();
		}
        String endstr="20.06.2017";
        GregorianCalendar endcal = new GregorianCalendar();
	    endcal.setLenient(false);
	    try {
			endcal.setTime(sdf.parse(endstr));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Kurs neuerKurs=new Kurs(0,"Kursname3", "Level3", beginncal, endcal, 111, 1, 11, 1,null);
		kursmgmt.addKurs(neuerKurs);
		*/

		/* TEST deleteKurs */
		// kursmgmt.deleteKurs(3);

		/* TEST updateKurs 
		Kurs kursToUpdate=kursmgmt.getKursById(2);
		kursToUpdate.setPreis(22);
		kursmgmt.updateKurs(kursToUpdate);
		*/
		
		/* TEST checkKursExists 
		System.out.println(kursmgmt.checkKursExists("Kursname1"));
		*/
		
		/* TEST addTeilnehmerToKurs */
		// kursmgmt.addTeilnehmerToKurs(1,2);
		
		/* TEST deleteTeilnehmerFromKurs */
		// kursmgmt.deleteTeilnehmerFromKurs(1,2);
		
		/* TEST getTeilnehmerlisteForKurs 
		ArrayList<Integer> teilnehmerliste=kursmgmt.getTeilnehmerlisteForKurs(2);
		System.out.println("Das sind die Teilnehmer");
		for(int i=0;i<teilnehmerliste.size();i++){
			System.out.println(teilnehmerliste.get(i));
		} 
		*/
		
		/* TEST getKursByKursnameAndBeginn 
		ArrayList<Kurs> sl = kursmgmt.getKursByKursnameAndBeginn("deinKurs1","31.05.2017");
		for(int i=0;i<sl.size();i++){
			System.out.println(sl.get(i).getKursname());
		}
		*/
		
		/* TEST getKursByKeyword 
		ArrayList<Kurs> sl = kursmgmt.getKursByKeyword("ein");
		for(int i=0;i<sl.size();i++){
			System.out.println(sl.get(i).getKursname());			
		} 
		*/
	// }

}
