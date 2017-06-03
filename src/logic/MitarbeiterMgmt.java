package logic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import model.Kunde;
import model.Mitarbeiter;
import repository.DatabaseMitarbeiterDAO;

import repository.MitarbeiterDAO;

public class MitarbeiterMgmt {

	private MitarbeiterDAO mitarbeiterdao;
	private DatabaseMitarbeiterDAO dbzugang;
	/**
	 * Konstruktor
	 */
	public MitarbeiterMgmt(){
		mitarbeiterdao= new DatabaseMitarbeiterDAO();
		dbzugang= new DatabaseMitarbeiterDAO();
	}
	
	/** 
	 * Get-Methode fuer die Instanzvariable kundendao
	 * @return kundendao
	 */
	public MitarbeiterDAO getMitarbeiterdao(){
		return mitarbeiterdao;
	}
	
	public ArrayList<Mitarbeiter> getMitarbeiterListe(){
		ArrayList<Mitarbeiter> mitarbeiterliste = null;
		try {
			mitarbeiterliste = dbzugang.getMitarbeiterListe();
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mitarbeiterliste;
	}

	/**
	 * Set-Methode fuer die Instanzvariable kundendao
	 */
	public void setMitarbeiterdao(String vor,String nach, String sv, String spezi, String typ,
			String gebdate, String user,String pw, String status){
		int svnr=0;
		int mittyp=0;
		int statusint=0;
			try{
				svnr=Integer.parseInt(sv);
				mittyp=Integer.parseInt(typ);
				if(status.equalsIgnoreCase("Aktiv")){
					statusint=1;
				}
			}
			catch(NumberFormatException e){e.getMessage();}
			System.out.println("Bin hier im Management");
			dbzugang.insert(vor, nach, svnr, spezi, mittyp, gebdate, user, pw, statusint);
	}
	public void delete(String sozial){
		int svnr=0;
		try{
			System.out.println(sozial);
			svnr=Integer.parseInt(sozial);
		}
		catch(NumberFormatException e){e.getMessage();}
		dbzugang.deleteMitarbeiter(svnr);
	}
	
	public Mitarbeiter getMitarbeiterById(int id){
		Mitarbeiter mitarbeiter = dbzugang.getMitarbeiterById(id);
		return mitarbeiter;
	}
	
	public void aendern(String user,String oldpw,String newpw){
		LoginMgmt lmg= new LoginMgmt();
		String test= lmg.check(user, oldpw);
		if(test!=null){
			dbzugang.update(user, oldpw, newpw);
		}
	}
	public Mitarbeiter context(String name,String pwd) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy"); 

	     GregorianCalendar beginncal = new GregorianCalendar();
	     beginncal.setLenient(false);
	     beginncal.setTime(sdf.parse("12.12.1990"));
		Mitarbeiter zurück = new Mitarbeiter(0,name,"", 0,"",0,beginncal, name, pwd, true);
		return zurück;
	}
}
