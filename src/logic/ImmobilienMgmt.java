package logic;

import java.util.ArrayList;

import model.Immobilie;
import repository.DatabaseImmobilienDAO;

public class ImmobilienMgmt {
	
	private DatabaseImmobilienDAO ImmoDAO = new DatabaseImmobilienDAO();
	
	public void deleteGebäude(int id) {
		ImmoDAO.deleteGebäude(id);
	}
	
	

	public void addGebäude (int typ, String name) {
		ImmoDAO.insert(typ, name);
	}
	
	public ArrayList<Immobilie> getGebäudeList () {
		return ImmoDAO.getGebäudeList();
	}
	
	public Immobilie getGebäudebyID(int id) {
		return ImmoDAO.getGebäudebyID(id);
	}
	
	public void updatetyp (int id, int typ) {
		ImmoDAO.updatetyp(id, typ);
	}
	
	public void updatename (int id, String name) {
		ImmoDAO.updatename(id, name);
	}
	
}
