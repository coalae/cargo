package logic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import model.Kurs;
import repository.DatabaseKursDAO;
import repository.KursDAO;

public class KursMgmt {
	
	// instanzvariablen
	private KursDAO kursdao;

	// konstruktor
	public KursMgmt(){
		setKursdao();
	}
	
	// getter & setter		
	public KursDAO getKursdao(){
		return kursdao;
	}

	public void setKursdao(){
		this.kursdao = new DatabaseKursDAO();
	}	
	
	// methoden
	public ArrayList<Kurs> getKursListe(){
		ArrayList<Kurs> kursliste=kursdao.getKursListe();
		return kursliste;
	}
	
	public Kurs getKursById(int id){
		Kurs kurs = kursdao.getKursById(id);
		return kurs;
	}
	
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
	
	public void addKurs(Kurs kurs){
		kursdao.addKurs(kurs);
	}
	
	public void deleteKurs(int id){
		kursdao.deleteKurs(id);
	}
	
	public void updateKurs (Kurs kurs){ // instanzvariablen update ohne teilnehmerliste-update
		kursdao.updateKurs(kurs);	
	}
	
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
	
	public void addTeilnehmerToKurs(int kundenId, int kursId){
		kursdao.addTeilnehmerToKurs(kundenId, kursId);
	}
	
	public void deleteTeilnehmerFromKurs(int kundenId, int kursId){
		kursdao.deleteTeilnehmerFromKurs(kundenId, kursId);
	}
	
	public ArrayList<Integer> getTeilnehmerlisteForKurs(int kursId){
		Kurs kurs=kursdao.getKursById(2);
		// if(kurs.getTeilnehmerliste()!=null || kurs.getTeilnehmerliste().size()!=0){
			ArrayList<Integer> teilnehmerliste=kurs.getTeilnehmerliste();	
		// }
		return teilnehmerliste;
	}

	/* main Funktion fuer Tests	*/ 
	public static void main(String[] args){
		KursMgmt kursmgmt = new KursMgmt();
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
	}

}
