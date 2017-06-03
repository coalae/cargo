package logic;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import model.Kurs;
import model.Mentoring;
import repository.DatabaseKursDAO;
import repository.DatabaseMentoringDAO;
import repository.KursDAO;
import repository.MentoringDAO;


/**
 * MentoringMgmt Klasse greift auf MentoringDAO zu und gibt diese Daten anschlieﬂend an die Servlets zurueck
 * @author Denise Gall
 *
 */
public class MentoringMgmt {
	
	/**
	 * mentoringddao
	 */
	private MentoringDAO mentoringdao;
	
	/**
	 * Konsturktor
	 */
		public MentoringMgmt(){
			setMentoringdao();
		}
		
		/**
		 * 
		 * @return mentoringdao: MentorinDao
		 */
		public MentoringDAO getMentoringdao(){
			return mentoringdao;
		}
		
		/**
		 * setzt mentoringdao
		 */
		public void setMentoringdao(){
			this.mentoringdao = new DatabaseMentoringDAO();
		}	
		
		/**
		 * Gibt eine Liste mit allen Mentoringbeziehungen zurueck
		 * @return ArrayList<Mentoring>: ArryList mit Mentoringbeziehungen
		 */
		public ArrayList<Mentoring> getMentoringListe(){
			ArrayList<Mentoring> mentoringliste= mentoringdao.getMentoringListe();
			return mentoringliste;
		}
		
		
		/**
		 * Gibt das Mentoring zu der gewaehlten Id zurueck
		 * @param id: Mentoring Id
		 * @return Mentoring: Mentoring zu Id
		 */
		public Mentoring getMentoringById(int id){
			Mentoring mentoring = mentoringdao.getMentoringById(id);
			return mentoring;
		}
		
		/**
		 * Gibt das Mentoring mit passendem Thema zurueck
		 * @param thema: Thema zu dem Mentoring gesucht werden soll
		 * @return suchMentoring: Mentoring, dass das entsprechende Thema behandelt
		 */
		public Mentoring getMentoringByThema(String thema){
			Mentoring suchMentoring=null;
			ArrayList<Mentoring> mentoringliste=getMentoringListe();
			for(int i=0;i<mentoringliste.size();i++){
				if(mentoringliste.get(i).getThema().equals(thema)){
					suchMentoring=mentoringliste.get(i);
				}
			}
			return suchMentoring;
		}
		
		/**
		 * F¸gt eine neue Mentoringbeziehung hinzu		
		 * @param mentoring: Mentoring das hinzugefuegt werden soll
		 */
		public void addMentoring(Mentoring mentoring){
			mentoringdao.addMentoring(mentoring);	
		}
		
		/**	
		 * Loescht ein Mentoring mit entsprechender Id
		 * @param id: Id des Mentoring das geloescht werden soll
		 */
		public void deleteMentoring(int id){
			mentoringdao.deleteMentoring(id);
		}
		
		/**
		 * Aendert eine bestehende Mentoringbeziehung 
		 * @param mentoring: Mentoring das geaendert werden soll
		 */
		public void updateMentoring (Mentoring mentoring){ 
			mentoringdao.updateMentoring(mentoring);	
		}
		
		
		/**
		 * Prueft ob eine Mentoringbeziehung, mit der Mentorid und Menteeid bereits exisitert und somit nicht mehr hinzugefuegt werden kann
		 * @param mentorid: Mentorid, die zu einer neuen Mentoringbeziehung hinzugefuegt werden soll
		 * @param menteeid: Menteeid, die zu einer neuen Mentoringbeziehung hinzugefuegt werden soll
		 * @return boolean
		 */
		public boolean checkMentoringExists(int mentorid, int menteeid ){
			boolean exists=false;
			
			ArrayList<Mentoring> mentoring=getMentoringListe();
			
			for(int i=0;i<mentoring.size();i++){
				if(mentoring.get(i).getMentorId() == mentorid && mentoring.get(i).getMenteeId() == menteeid){
					exists=true;
				}
			}	
			return exists;
		}
		
}
