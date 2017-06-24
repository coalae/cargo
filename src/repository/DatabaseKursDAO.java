package repository;

import java.text.SimpleDateFormat;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import model.Kurs;

/** 
 * Die Klasse DatabaseKursDAO implementiert die Methoden des Interface KursDAO.
 * Die Methoden dienen zum Speichern, Loeschen, Aendern von Instanzen der Klasse Kurs und 
 * zum Hinzufuegen und Loeschen von Teilnehmern an einem Kurs.
 * @author Cordula Eggerth
 */
public class DatabaseKursDAO implements KursDAO {

	/**
	 * Instanzvariablen
	 */
	DatabaseHandlerMongoDB databaseHandler = DatabaseHandlerMongoDB.getInstance();
	DB db;
	
	/**
	 * Konstruktor
	 */
	public DatabaseKursDAO() {
		try{
			db = databaseHandler.erstellen();
		} catch(Exception e) {e.printStackTrace();}
	}
	
	/**
	 * getKursListe  
	 * @return ArrayList von Kursen
	 */
	@Override
	public ArrayList<Kurs> getKursListe() {
		
		ArrayList<Kurs> kursList = new ArrayList<Kurs>();
		DBCollection kurscoll = db.getCollection("Kurs");
		DBCursor cursor = kurscoll.find();

			while(cursor.hasNext()) {
				// System.out.println(cursor.next());
				
				BasicDBObject kursObject = (BasicDBObject) cursor.next();
				 						
				try{
							 
		             // Teilnehmerliste des Kurses setzen
		             ArrayList<Integer> teilnehmerliste = getTeilnehmerListe(kursObject.getInt("kursid"));
		    		
		             // GregorianCalendar fuer beginn und ende setzen
		             SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy"); 

		     	     GregorianCalendar beginncal = new GregorianCalendar();
		     	     beginncal.setLenient(false);
		     	     beginncal.setTime(sdf.parse(kursObject.getString("beginn")));
		    	     
		    	     GregorianCalendar endecal = new GregorianCalendar();
		    	     endecal.setLenient(false);
		     	     beginncal.setTime(sdf.parse(kursObject.getString("ende")));
		     	     
		     	     // neuen Kurs anlegen
		             Kurs kurs=new Kurs(kursObject.getInt("kursid"),kursObject.getString("kursname"),kursObject.getString("kurslevel"),
		            		 beginncal,endecal,kursObject.getDouble("preis"),kursObject.getInt("betreuerid"),kursObject.getInt("maxanzahl"),
		            		 kursObject.getInt("immobilie"),teilnehmerliste);
		             
		             // Kurs zur Kursliste hinzufuegen
		             kursList.add(kurs);

			    }catch(Exception e){
						System.out.println(e.getMessage());
				}	
				
				// CHECK
				for (Kurs kurs : kursList) {
					System.out.println(kurs.getKursname());
				}				             		             
			             
			}
			
			return kursList;

	}	
	

	/**
     * Get Kurs by Id  
     * Kurs anhand der als int uebergebenen id suchen.
     * @param id
     * @return
     */
	@Override
    public Kurs getKursById(int id){
    	  
		Kurs suchKurs = null;
		
		DBCollection kurscoll = db.getCollection("Kurs");

		BasicDBObject idObject = new BasicDBObject("id", id);
		
		DBCursor cursor = kurscoll.find(idObject);
		for (int i=0; i<cursor.size(); i++) {
			BasicDBObject kursObject = (BasicDBObject) cursor.next();
			if(id == kursObject.getInt("kursid")){
				try{
					
			        // Teilnehmerliste des Kurses setzen
			        ArrayList<Integer> teilnehmerliste = null;
			          if(getTeilnehmerListe(id)!=null || getTeilnehmerListe(id).size()!=0){
			        	  teilnehmerliste = getTeilnehmerListe(id);
			          }
					
					// GregorianCalendar fuer beginn und ende setzen
		             SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy"); 

		     	     GregorianCalendar beginncal = new GregorianCalendar();
		     	     beginncal.setTime(sdf.parse(kursObject.getString("beginn")));
		    	     
		    	     GregorianCalendar endecal = new GregorianCalendar();
		     	     beginncal.setTime(sdf.parse(kursObject.getString("ende")));

					suchKurs=new Kurs(kursObject.getInt("kursid"),kursObject.getString("kursname"),kursObject.getString("kurslevel"),
		            		 beginncal,endecal,kursObject.getDouble("preis"),kursObject.getInt("betreuerid"),
		            		 kursObject.getInt("maxanzahl"),kursObject.getInt("immobilie"),teilnehmerliste);
					
			    } catch(Exception e){
						System.out.println(e.getMessage());
				}	
			}

		}
		
		return suchKurs;	

	}


	/**
	 * Neuen Kurs hinzufuegen
	 * @param kurs
	 */
	@Override
	public void addKurs(Kurs kurs) {

    	DBCollection kurscoll = db.getCollection("Kurs");
		DBCursor cursor = kurscoll.find();
				
		// System.out.println(kurscoll.count());
		long next = kurscoll.count();
		next = next +1;

		BasicDBObject doc = new BasicDBObject();
		doc.put("kursid", next);
		doc.put("kursname", kurs.getKursname());
		doc.put("kurslevel", kurs.getLevel());
		doc.put("beginn", kurs.getBeginn());
		doc.put("ende", kurs.getEnde());
		doc.put("preis", kurs.getPreis());
		doc.put("betreuerid", kurs.getPreis());
		doc.put("maxanzahl", kurs.getMaxAnzahl());
		doc.put("immobilie", kurs.getImmobilie());

		kurscoll.insert(doc);
	
	}
	

	/**
	 * Bestehenden Kurs loeschen  
	 * @param id
	 */	
	@Override
	public void deleteKurs(int id) {

	 	   DBCollection kurs = db.getCollection("Kurs");
	 	   BasicDBObject query = new BasicDBObject("kursid", id);
	 	   DBCursor cursor = kurs.find(query);
	 	   for(int i=0;i<cursor.size();i++){
	 		   BasicDBObject act = (BasicDBObject) cursor.next();
	 		   if(id == act.getInt("kursid")){
	 			   kurs.remove(act);
	 		   }
	 	   }		

	}	
	
	/**
	 * Bestehenden Kurs updaten bzw. aendern 
	 * @param kurs
	 */
	@Override
	public void updateKurs(Kurs kurs) {
		
		 // update ohne datumsaenderung (beginn und ende gleich)
		 SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy"); 

			DBCollection kurscoll = db.getCollection("Kurs");
			BasicDBObject object = new BasicDBObject("kursid", kurs.getId());
			DBCursor cursor = kurscoll.find(object);

			if(cursor.size()>0){
					for(int i=0; i<cursor.size(); i++){
						BasicDBObject next = (BasicDBObject) cursor.next();
						if(kurs.getId() == next.getInt("kursid")){
							
							BasicDBObject update = new BasicDBObject();
							
							update.append("kursid", kurs.getId());
							update.append("kursname", kurs.getKursname());
							update.append("kurslevel", kurs.getLevel());
							update.append("beginn", kurs.getBeginn());
							update.append("ende", kurs.getEnde());
							update.append("preis", kurs.getPreis());
							update.append("betreuerid", kurs.getBetreuerId());
							update.append("maxanzahl", kurs.getMaxAnzahl());
							update.append("kurslevel", kurs.getImmobilie());
							
							kurscoll.update(new BasicDBObject().append("kursid", kurs.getId()), update);
						}

				    }
		     }
	}    

	
	/**
	 * Teilnehmer (anhand der kundenId) zu einem bestimmten Kurs hinzufuegen
	 * @param kundenId
	 * @param kursId
	 */
	@Override
	public void addTeilnehmerToKurs(int kundenId, int kursId){
 
        	
    	DBCollection teilnehmercoll = db.getCollection("Teilnehmer");
		DBCursor cursor = teilnehmercoll.find();
				
		// System.out.println(teilnehmercoll.count());
		long next = teilnehmercoll.count();
		next = next +1;

		BasicDBObject doc = new BasicDBObject();
		doc.put("kundenid", kundenId);
		doc.put("kursid", kursId);

		teilnehmercoll.insert(doc);
		
	}
	
	/**
	 * Teilnehmer (anhand der kundenId) von einem bestimmten Kurs loeschen
	 * @param kundenId
	 * @param kursId
	 */
	@Override
	public void deleteTeilnehmerFromKurs(int kundenId, int kursId){

	 	   DBCollection teilnehmer = db.getCollection("Teilnehmer");
	 	   BasicDBObject query1 = new BasicDBObject("kundenid", kundenId);
	 	   BasicDBObject query2 = new BasicDBObject("kursid", kursId);
	 	   DBCursor cursor = teilnehmer.find(query1,query2);
	 	   for(int i=0;i<cursor.size();i++){
	 		   BasicDBObject act = (BasicDBObject) cursor.next();
	 		   if(kundenId == act.getInt("kundenid") && kursId == act.getInt("kursid")){
	 			   teilnehmer.remove(act);
	 		   }
	 	   }
	}
	
	
	/**
	 * Teilnehmerliste (Liste von kundeIds) fuer einen Kurs anhand kursId suchen
	 * @param kursId
	 * @return
	 */
	public ArrayList<Integer> getTeilnehmerListe(int kursId) {

		ArrayList<Integer> teilnehmerList = new ArrayList<Integer>();
		DBCollection teilnehmercoll = db.getCollection("Teilnehmer");
		DBCursor cursor = teilnehmercoll.find();

			while(cursor.hasNext()) {
				// System.out.println(cursor.next());
				
				BasicDBObject teilnehmerObject = (BasicDBObject) cursor.next();
												
				try{
							 
					teilnehmerList.add(teilnehmerObject.getInt("kundenid"));
					
			    }catch(Exception e){
						System.out.println(e.getMessage());
				}	
				
				// CHECK
				for (int i=0;i<teilnehmerList.size();i++) {
					System.out.println(teilnehmerList.get(i));
				}				             		             
			             
			}
			
			return teilnehmerList;

	}
  
	
	/**
	 * Kursliste (Liste von kursIds) fuer einen Teilnehmer (Kunde) anhand kundenId suchen
	 * @param kundenId
	 * @return
	 */
	@Override
	public ArrayList<Integer> getKurslisteForTeilnehmer (int kundenId) {

		ArrayList<Integer> kursList=new ArrayList<Integer>();
		 
		DBCollection teilnehmercoll = db.getCollection("Teilnehmer");

	 	BasicDBObject query = new BasicDBObject("kundenid", kundenId);
	 	   DBCursor cursor = teilnehmercoll.find(query);
	 	   for(int i=0;i<cursor.size();i++){
	 		   BasicDBObject act = (BasicDBObject) cursor.next();
	 		   if(kundenId == act.getInt("kundenid")){
	 			   kursList.add(act.getInt("kursid"));
	 		   }
	 	   }
					
				// CHECK
				for (int i=0;i<kursList.size();i++) {
					System.out.println(kursList.get(i));
				}				             		          
			
			return kursList;
	}
	
	
	/**
	 * main Funktion fuer Tests
	 * @param args
	 */ 
	// public static void main(String[] args){
	//	DatabaseKursDAO dao = new DatabaseKursDAO();
	
		// Kurs kursById = dao.getKursById(1);
		// dao.deleteKursById(5);
		/*
		int kursId=2;
		ArrayList<Integer> teilnehmerliste=dao.getTeilnehmerListe(kursId);
		
		for(int i=0;i<teilnehmerliste.size();i++){
			System.out.println(teilnehmerliste.get(i));
		}
		*/
		
		/*		 
		int kundenId=1;
		ArrayList<Integer> kursliste=dao.getKurslisteForTeilnehmer(kundenId);
		
		for(int i=0;i<kursliste.size();i++){
			System.out.println(kursliste.get(i));
		}
		*/
		
	//}
	
}
