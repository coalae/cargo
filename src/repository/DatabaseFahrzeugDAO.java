package repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.*;
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

public class DatabaseFahrzeugDAO {

	DatabaseHandlerMongoDB databasehandler = DatabaseHandlerMongoDB.getInstance();
	DB db;

	public DatabaseFahrzeugDAO() {
		try {
			db = databasehandler.erstellen();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Fahrzeug getFahrzeugbyID(int id) {
		DBCollection Fahrzeugcollection = db.getCollection("Fahrzeug");
		BasicDBObject query = new BasicDBObject("Fahrzeugid", id);
		DBCursor cur = Fahrzeugcollection.find(query);
		if (cur.size() > 0) {
			BasicDBObject fahrzeug = (BasicDBObject) cur.next();
			Fahrzeug neu = new Fahrzeug(fahrzeug.getInt("fahrzeugid"), fahrzeug.getString("marke"),
					fahrzeug.getString("modell"), fahrzeug.getString("baujahr"), fahrzeug.getString("farbe"));
			return neu;
		} else {
			System.out.println("Keine Fahrzeuge gefunden");
		}
		return null;
	}

	public ArrayList<Fahrzeug> getFahrzeugList() {
		ArrayList<Fahrzeug> returnFahrzeug = new ArrayList<Fahrzeug>();
		DBCollection Fahrzeugcollection = db.getCollection("Fahrzeug");
		DBCursor cur = Fahrzeugcollection.find();

		if (cur.size() > 0) {
			for (int i = 0; i <= cur.size(); i++) {
				BasicDBObject fahrzeug = (BasicDBObject) cur.next();
				Fahrzeug tempo = new Fahrzeug(fahrzeug.getInt("fahrzeugid"), fahrzeug.getString("marke"),
						fahrzeug.getString("modell"), fahrzeug.getString("baujahr"), fahrzeug.getString("farbe"));
				returnFahrzeug.add(tempo);
			}
			return returnFahrzeug;
		} else {
			System.out.println("Keine Fahrzeuge gefunden");
		}
		return null;
	}

	public void insert(String marke, String modell, String baujahr, String farbe) {

		DBCollection fahrzeug = db.getCollection("Fahrzeug");
		DBCursor cur = fahrzeug.find();
		long next = fahrzeug.count();
		next = next + 1;
		BasicDBObject doc = new BasicDBObject();
		doc.put("fahrzeugrid", next);
		doc.put("marke", marke);
		doc.put("modell", modell);
		doc.put("baujahr", baujahr);
		doc.put("farbe", farbe);
		fahrzeug.insert(doc);
	}

	public void deleteFahrzeug(int id) {
		DBCollection fahrzeug = db.getCollection("fahrzeug");
        BasicDBObject query = new BasicDBObject("fahrzeugid", id);
        DBCursor cur = fahrzeug.find(query);
        for(int i=0;i<cur.size();i++){
         BasicDBObject act = (BasicDBObject) cur.next();
         if(id == act.getInt("fahrzeug")){
          fahrzeug.remove(act);
         }
        }
	}

	public void updateMarke(int id, String upmarke) {
		DBCollection coll = db.getCollection("Fahrzeug");
		BasicDBObject query = new BasicDBObject("fahrzeugid", id);
		DBCursor cur = coll.find(query);
		if (cur.size() > 0) {
			for (int i = 0; i < cur.size(); i++) {
				BasicDBObject next = (BasicDBObject) cur.next();
					BasicDBObject up = new BasicDBObject();
					up.append("fahrzeugid", next.getLong("fahrzeugid"));
					up.append("marke", upmarke);
					up.append("modell", next.getString("modell"));
					up.append("baujahr", next.getInt("baujahr"));
					up.append("farbe", next.getString("farbe"));
					coll.update(new BasicDBObject().append("fahrzeugid", next.getLong("fahrzeugid")), up);
				}
			}
		}

	public void updateModell (int id, String upmodel) {

		DBCollection coll = db.getCollection("Fahrzeug");
		BasicDBObject query = new BasicDBObject("fahrzeugid", id);
		DBCursor cur = coll.find(query);
		if (cur.size() > 0) {
			for (int i = 0; i < cur.size(); i++) {
				BasicDBObject next = (BasicDBObject) cur.next();
					BasicDBObject up = new BasicDBObject();
					up.append("fahrzeugid", next.getLong("fahrzeugid"));
					up.append("marke", next.getLong("marke"));
					up.append("modell", upmodel);
					up.append("baujahr", next.getInt("baujahr"));
					up.append("farbe", next.getString("farbe"));
					coll.update(new BasicDBObject().append("fahrzeugid", next.getLong("fahrzeugid")), up);
				}
			}	
		
	}
	
	
	public void updateJahr (int id, String jahr) {
		DBCollection coll = db.getCollection("Fahrzeug");
		BasicDBObject query = new BasicDBObject("fahrzeugid", id);
		DBCursor cur = coll.find(query);
		if (cur.size() > 0) {
			for (int i = 0; i < cur.size(); i++) {
				BasicDBObject next = (BasicDBObject) cur.next();
					BasicDBObject up = new BasicDBObject();
					up.append("fahrzeugid", next.getLong("fahrzeugid"));
					up.append("marke", next.getLong("marke"));
					up.append("modell", next.getString("modell"));
					up.append("baujahr", jahr);
					up.append("farbe", next.getString("farbe"));
					coll.update(new BasicDBObject().append("fahrzeugid", next.getLong("fahrzeugid")), up);
				}
			}	
	}
	
	public void updateFarbe (int id, String upfarbe) {
		DBCollection coll = db.getCollection("Fahrzeug");
		BasicDBObject query = new BasicDBObject("fahrzeugid", id);
		DBCursor cur = coll.find(query);
		if (cur.size() > 0) {
			for (int i = 0; i < cur.size(); i++) {
				BasicDBObject next = (BasicDBObject) cur.next();
					BasicDBObject up = new BasicDBObject();
					up.append("fahrzeugid", next.getLong("fahrzeugid"));
					up.append("marke", next.getLong("marke"));
					up.append("modell", next.getString("modell"));
					up.append("baujahr",next.getString("baujahr"));
					up.append("farbe", upfarbe);
					coll.update(new BasicDBObject().append("fahrzeugid", next.getLong("fahrzeugid")), up);
				}
			}	
		
	}
	
	
	

}
