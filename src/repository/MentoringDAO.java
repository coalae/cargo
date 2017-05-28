package repository;

import java.util.ArrayList;

import model.Mentoring;

public interface MentoringDAO {


	public ArrayList<Mentoring> getMentoringListe();
	
	public Mentoring getMentoringById(int id);
	
	public Mentoring getMentoringByMenteeId(int id);
	
	public void addMentor(int mentorId);
	
	public void addMentee(int menteeId);
	
	public void addMentoring (Mentoring mentoring);
	
	public void deleteMentoring (int id);
	
	public void updateMentoring(Mentoring mentoring);
	


}
