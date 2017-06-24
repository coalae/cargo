package repository;

import java.util.ArrayList;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

import model.Kunde;

/**
 * Das Interface KundenDAO bietet Methoden fuer das Speichern, Loeschen, Veraendern von 
 * Instanzen der Klasse Kunde.
 * @author Cordula Eggerth
 */
public class DatabaseKundenDAO implements KundenDAO {

	/**
	 * Instanzvariablen
	 */
	DatabaseHandlerMongoDB databaseHandler = DatabaseHandlerMongoDB.getInstance();
	DB db;
	
	/**
	 * Konstruktor
	 */
	public DatabaseKundenDAO() {
		try{
			db = databaseHandler.erstellen();
		}catch(Exception e){e.printStackTrace();}
	}
	
	/**
	 * get kundenliste 
	 * Liste aller Kunden aus der DB holen.
	 * @return ArrayList von Kunden
	 */
	@Override
	public ArrayList<Kunde> getKundenListe() {

		ArrayList<Kunde> kundenList = new ArrayList<Kunde>();
		DBCollection kundencoll = db.getCollection("Kunde");
		DBCursor cursor = kundencoll.find();

			while(cursor.hasNext()) {
				// System.out.println(cursor.next());
				
				BasicDBObject kundeObj = (BasicDBObject) cursor.next();
						
				boolean active = Boolean.parseBoolean(kundeObj.getString("active"));
						
				try{
							 
					Kunde kunde=new Kunde(kundeObj.getInt("kundenid"),kundeObj.getString("vorname"),
				              	kundeObj.getString("nachname"),kundeObj.getInt("iban"),kundeObj.getString("bic"),
				              	kundeObj.getString("username"),kundeObj.getString("passw"),active);

					kundenList.add(kunde);
			    }catch(Exception e){
						System.out.println(e.getMessage());
				}	
				
				// CHECK
				for (Kunde kunde : kundenList) {
					System.out.println(kunde.getNachname());
				}				             		             
			             
			}
			
			return kundenList;

	}


	/**
     * Get Kunde by Id
     * Kunde anhand der als int uebergebenen id suchen.
     * @param id
     * @return
     */
	@Override 
	public Kunde getKundeById(int id){
		 
		Kunde suchKunde = null;
		
		DBCollection kundencoll = db.getCollection("Kunde");

		BasicDBObject idObject = new BasicDBObject("id", id);
		
		DBCursor cursor = kundencoll.find(idObject);
		for (int i=0; i<cursor.size(); i++) {
			BasicDBObject kundeObj = (BasicDBObject) cursor.next();
			if(id == kundeObj.getInt("kundenid")){
				try{
					
					boolean active = Boolean.parseBoolean(kundeObj.getString("active"));					 
					suchKunde=new Kunde(kundeObj.getInt("kundenid"),kundeObj.getString("vorname"),
				              	kundeObj.getString("nachname"),kundeObj.getInt("iban"),kundeObj.getString("bic"),
				              	kundeObj.getString("username"),kundeObj.getString("passw"),active);

			    } catch(Exception e){
						System.out.println(e.getMessage());
				}	
			}

		}
		
		return suchKunde;	
		
	}
	    	
	
	/**
	 * addKunde 
	 * Neuen Kunde hinzufuegen.
	 * @param kunde
	 */
	@Override
	public void addKunde(Kunde kunde) {

    	DBCollection kundencoll = db.getCollection("Kunde");
		DBCursor cursor = kundencoll.find();
				
		// System.out.println(kundencoll.count());
		long next = kundencoll.count();
		next = next +1;

		BasicDBObject doc = new BasicDBObject();
		doc.put("kundenid", next);
		doc.put("vorname", kunde.getVorname());
		doc.put("nachname", kunde.getNachname());
		doc.put("iban", kunde.getIban());
		doc.put("bic", kunde.getBic());
		doc.put("username", kunde.getUsername());
		doc.put("passw", kunde.getPassword());
		doc.put("active", 1);

		kundencoll.insert(doc);

	}
	
	
	/**
	 * deleteKunde 
	 * Bestehenden Kunde loeschen.
	 */
	@Override
	public void deleteKunde(int id) {

 	   DBCollection kunde = db.getCollection("Kunde");
 	   BasicDBObject query = new BasicDBObject("kundenid", id);
 	   DBCursor cursor = kunde.find(query);
 	   for(int i=0;i<cursor.size();i++){
 		   BasicDBObject act = (BasicDBObject) cursor.next();
 		   if(id == act.getInt("kundenid")){
 			   kunde.remove(act);
 		   }
 	   }
		
	}	
	
	
	/**
	 * updateKunde (by Id)  
	 * Bestehenden Kunde updaten bzw. aendern.
	 */
	@Override
	public void updateKunde(Kunde kunde) {
			
		DBCollection kundencoll = db.getCollection("Kunde");
		BasicDBObject object = new BasicDBObject("kundenid", kunde.getId());
		DBCursor cursor = kundencoll.find(object);

		if(cursor.size()>0){
				for(int i=0; i<cursor.size(); i++){
					BasicDBObject next = (BasicDBObject) cursor.next();
					if(kunde.getId() == next.getInt("kundenid")){
						
						BasicDBObject update = new BasicDBObject();
						
						update.append("kundenid", kunde.getId());
						update.append("vorname", kunde.getVorname());
						update.append("nachname", kunde.getNachname());
						update.append("iban", kunde.getIban());
						update.append("bic", kunde.getBic());
						update.append("username", kunde.getUsername());
						update.append("bic", kunde.getBic());
						update.append("passw", kunde.getPassword());
						update.append("active", 1);
						
						kundencoll.update(new BasicDBObject().append("kundenid", kunde.getId()), update);
					}

			    }
	     }
	}
		   
    
	/*
	 * main Funktion fuer Tests
	 * @param args
	*/ 
	/*
	public static void main(String[] args){
		DatabaseKundenDAO dao = new DatabaseKundenDAO();
    	System.out.println("UN: " + kundeById.getUsername() + " PW " + kundeById.getPassword());
	}
	*/
   
}
