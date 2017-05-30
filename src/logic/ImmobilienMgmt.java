package logic;

import java.util.ArrayList;

import model.Immobilie;
import repository.DatabaseImmobilienDAO;

public class ImmobilienMgmt {
	
	private DatabaseImmobilienDAO ImmoDAO = new DatabaseImmobilienDAO();
	
	public void deleteGeb�ude(int id) {
		ImmoDAO.deleteGebaude(id);
	}
	
	

	public void addGeb�ude (int typ, String name) {
		ImmoDAO.insert(typ, name);
	}
	
	public ArrayList<Immobilie> getGebaudeList () {
		return ImmoDAO.getGebaudeList();
	}
	
	public Immobilie getGeb�udebyID(int id) {
		return ImmoDAO.getGebaudebyID(id);
	}
	
	public void updatetyp (int id, int typ) {
		ImmoDAO.updatetyp(id, typ);
	}
	
	public void updatename (int id, String name) {
		ImmoDAO.updatename(id, name);
	}
	
}
