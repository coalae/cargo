package repository;

import java.util.ArrayList;

import model.Kurs;

public interface KursDAO {

	public ArrayList<Kurs> getKursListe();
	
	public Kurs getKursById(int id);
	
	public void addKurs(Kurs kurs);
	
	public void deleteKurs(int id);
	
	public void updateKurs(Kurs kurs);
	
	public void addTeilnehmerToKurs(int kundenId, int kursId);

	public void deleteTeilnehmerFromKurs(int kundenId, int kursId);
	
}
