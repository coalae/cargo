package repository;

import java.util.ArrayList;

import model.Immobilie;

public interface ImmobilienDAO {

	 public void insert(int typ, String name);
	 
	 public void deleteGeb�ude (int id);
	
	 
	 public ArrayList<Immobilie> getGeb�udeList ();
	 
	 public Immobilie getGeb�udebyID(int id);
	 
	 public void updatetyp (int id, int typ);
	 
	 public void updatename (int id, String name);
	 
	 
	 
	 
	
	
	
}
