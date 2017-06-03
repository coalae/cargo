package repository;

import java.util.ArrayList;

import model.Mentoring;

public interface MentoringDAO {


	public ArrayList<Mentoring> getMentoringListe();
	
	public Mentoring getMentoringById(int id);
	
	public void addMentoring (Mentoring mentoring);
	
	public void deleteMentoring (int id);
	
	public void updateMentoring(Mentoring mentoring);
	


}
