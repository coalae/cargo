package repository;

import java.util.ArrayList;

import model.Kunde;

public interface KundenDAO {

	public ArrayList<Kunde> getKundenListe();
	
	public Kunde getKundeById(int id);
	
	public void addKunde(Kunde kunde);
	
	public void deleteKunde(int id);
	
	public void updateKunde(Kunde kunde);
	
}
