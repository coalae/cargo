package repository;


import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.*;

import java.util.ArrayList;

import com.mongodb.client.model.CreateCollectionOptions;
import com.mongodb.client.model.ValidationOptions;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

import model.Fahrzeug;
import model.Immobilie;

public class DatabaseImmobilienDAO {

	DatabaseHandlerMongoDB databasehandler = DatabaseHandlerMongoDB.getInstance();
	DB db;

	public DatabaseImmobilienDAO() {
		try {
			db = databasehandler.erstellen();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
  
     
    
    /**
	 * INSERT Gebäude
	 * @param String Marke, modell, baujahr, farbe
	 */
    
    
    public void insert(int typ, String name) {
    	
		DBCollection immobilie = db.getCollection("Immobilie");
		DBCursor cur = immobilie.find();
		long next = immobilie.count();
		next = next + 1;
		BasicDBObject doc = new BasicDBObject();
		doc.put("immobilienid", next);
		doc.put("typ", typ);
		doc.put("name", name);

		immobilie.insert(doc);
    	
    	
    	}
    	
    /**
     * DELETE GEbäude
     * @param ImmobilieID
     */
    
    public void deleteGebaude (int id) {
		DBCollection immobilie = db.getCollection("Immobilie");
        BasicDBObject query = new BasicDBObject("immobilienid", id);
        DBCursor cur = immobilie.find(query);
        for(int i=0;i<cur.size();i++){
         BasicDBObject act = (BasicDBObject) cur.next();
         if(id == act.getInt("fahrzeug")){
        	 immobilie.remove(act);
         }
        }
	}
    	
    /**
     * Retouriert eine Liste von typ Immobilie.    
     * @return returnGebäude
     */

	public ArrayList<Immobilie> getGebaudeList () {
		ArrayList<Immobilie> returnGebaude = new ArrayList<Immobilie>();
	
		DBCollection Immobiliecollection = db.getCollection("Immobilie");
		DBCursor cur = Immobiliecollection.find();

		if (cur.size() > 0) {
			for (int i = 0; i <= cur.size(); i++) {
				BasicDBObject immobilie = (BasicDBObject) cur.next();
				Immobilie tempo = new Immobilie(immobilie.getInt("immobilienid"), immobilie.getInt("typ"),
						immobilie.getString("name"));
				returnGebaude.add(tempo);
			}
			return returnGebaude;
		} else {
			System.out.println("Keine Gebaude gefunden");
		}
		return null;
	}

	/**
	 * Holt ein bestimmtes Gebäude
	 * @param int id
	 * @return immobilie
	 */ 
	public Immobilie getGebaudebyID(int id) {
		DBCollection Immobiliecollection = db.getCollection("Immobilie");
		BasicDBObject query = new BasicDBObject("immobilienid", id);
		DBCursor cur = Immobiliecollection.find(query);
		if (cur.size() > 0) {
			BasicDBObject immobilie = (BasicDBObject) cur.next();
			Immobilie neu = new Immobilie(immobilie.getInt("immobilienid"), immobilie.getInt("typ"),
					immobilie.getString("name"));
			return neu;
		} else {
			System.out.println("Keine Immobilie gefunden");
		}
		return null;
	}
    
	/**
	 * Ändern die Fahrzeugdaten
	 * @param int id
	 * @return typ
	 */ 
	public void updatetyp (int id, int typ) {
		DBCollection coll = db.getCollection("Immobilie");
		BasicDBObject query = new BasicDBObject("immobilieid", id);
		DBCursor cur = coll.find(query);
		if (cur.size() > 0) {
			for (int i = 0; i < cur.size(); i++) {
				BasicDBObject next = (BasicDBObject) cur.next();
					BasicDBObject up = new BasicDBObject();
					up.append("immobilienid", next.getLong("immobilienid"));
					up.append("typ", typ);
					up.append("name", next.getString("name"));
					coll.update(new BasicDBObject().append("immobilienid", next.getLong("immobilienid")), up);
				}
			}	
		
	}
    
	/**
	 * Ändern die Fahrzeugdaten
	 * @param int id
	 * @return name
	 */ 
	public void updatename (int id, String name) {
			DBCollection coll = db.getCollection("Immobilie");
			BasicDBObject query = new BasicDBObject("immobilieid", id);
			DBCursor cur = coll.find(query);
			if (cur.size() > 0) {
				for (int i = 0; i < cur.size(); i++) {
					BasicDBObject next = (BasicDBObject) cur.next();
						BasicDBObject up = new BasicDBObject();
						up.append("immobilienid", next.getLong("immobilienid"));
						up.append("typ", next.getString("typ"));
						up.append("name", name);
						coll.update(new BasicDBObject().append("immobilienid", next.getLong("immobilienid")), up);
					}
				}	
			
		}
	
	
  }


 