package logic;

import java.util.ArrayList;

import model.Kunde;
import repository.DatabaseKundenDAO;
import repository.KundenDAO;

public class KundenMgmt {
	
	// instanzvariablen
	private KundenDAO kundendao;

	// konstruktor
	public KundenMgmt(){
		setKundendao();
	}
	
	// getter & setter		
	public KundenDAO getKundendao(){
		return kundendao;
	}

	public void setKundendao(){
		this.kundendao = new DatabaseKundenDAO();
	}	
	
	// methoden
	public ArrayList<Kunde> getKundenListe(){
		ArrayList<Kunde> kundenliste=kundendao.getKundenListe();
		return kundenliste;
	}
	
	public Kunde getKundeById(int id){
		Kunde kunde = kundendao.getKundeById(id);
		return kunde;
	}
	
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
	
	public void addKunde(Kunde kunde){
		kundendao.addKunde(kunde);
	}
	
	public void deleteKunde(int id){
		kundendao.deleteKunde(id);
	}
	
	public void updateKunde(Kunde kunde){
		kundendao.updateKunde(kunde);	
	}
	
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
