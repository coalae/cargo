package logic;

import java.text.SimpleDateFormat;
import java.util.ArrayList;


import model.Mentoring;
import repository.DatabaseKursDAO;
import repository.DatabaseMentoringDAO;
import repository.KursDAO;
import repository.MentoringDAO;

public class MentoringMgmt {
	
	private MentoringDAO mentoringdao;
	
	// konstruktor
		public MentoringMgmt(){
			setMentoringdao();
		}
		
		// getter & setter		
		public MentoringDAO getMentoringdao(){
			return mentoringdao;
		}

		public void setMentoringdao(){
			this.mentoringdao = new DatabaseMentoringDAO();
		}	
		
		// methoden
		public ArrayList<Mentoring> getMentoringListe(){
			ArrayList<Mentoring> mentoringliste= mentoringdao.getMentoringListe();
			return mentoringliste;
		}
		
		public Mentoring getMentoringById(int id){
			Mentoring mentoring = mentoringdao.getMentoringById(id);
			return mentoring;
		}
		
		public Mentoring getKursByThema(String thema){
			Mentoring suchMentoring=null;
			ArrayList<Mentoring> mentoringliste=getMentoringListe();
			for(int i=0;i<mentoringliste.size();i++){
				if(mentoringliste.get(i).getThema().equals(thema)){
					suchMentoring=mentoringliste.get(i);
				}
			}
			return suchMentoring;
		}
		
		public ArrayList<Mentoring> getMentoringByMentorId(int mentorId){
	        //??
		}
		
		public Mentoring getMentoringByMenteeId(int menteeId){ 
			Mentoring mentoring = mentoringdao.getMentoringByMenteeId(menteeId);
			return mentoring;
			
		}
			
		public void addMentoring(Mentoring mentoring){
			mentoringdao.addMentoring(mentoring);	
		}
		
		public void addMentee (){
			//??
		}
		
		public void addMentor(){
			//??
		}
		
		
		public void deleteMentoring(int id){
			mentoringdao.deleteMentoring(id);
		}
		
		public void updateMentoring (Mentoring mentoring){ 
			mentoringdao.updateMentoring(mentoring);;	
		}
}
