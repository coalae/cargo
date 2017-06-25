package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

import java.util.*;


import model.Mentoring;
import model.Mitarbeiter;


/**
 * Erstellt die SQL Statements
 * @author Denise
 *
 */
public class DatabaseMentoringDAO implements MentoringDAO {
	
	DatabaseHandlerMongoDB databaseHandler = DatabaseHandlerMongoDB.getInstance();
	DB db;
	
	/**
	 * Konstruktor
	 */
	public DatabaseMentoringDAO() {
		try{
			db = databaseHandler.erstellen();
		}catch(Exception e){e.printStackTrace();}
	}
	
	/**
	 * Gibt die Mentoringliste zurueck
	 * @return ArrayList<Mentoring>: Mentoringliste
	 */
	@Override
	public ArrayList<Mentoring> getMentoringListe() {
		
		ArrayList<Mentoring> mentoringList = new ArrayList<Mentoring>();
		DBCollection mentoringcoll = db.getCollection("Mentoringbeziehung");
		DBCursor cursor = mentoringcoll.find();

			while(cursor.hasNext()) {
				BasicDBObject mentoring = (BasicDBObject) cursor.next();
				try{
					SimpleDateFormat sdf = new SimpleDateFormat("YYYY"); 
					Date mentoringjahr = new Date();
			     	mentoringjahr = sdf.parse(mentoring.getString("jahr"));
			     	int id = Integer.parseInt(mentoring.getString("mentoringid"));
					int mentor = Integer.parseInt(mentoring.getString("mentorid"));
					int mentee = Integer.parseInt(mentoring.getString("menteeid"));
					
					Mentoring ment = new Mentoring(id, mentor, mentee, mentoring.getString("thema"), mentoringjahr);
									mentoringList.add(ment);
				}catch(Exception e){System.out.println(e.getMessage());}
			}		
		
		return mentoringList;
   }	
	
	/**
	 * Gibt Mentoring zu einer bestimmten Id zurueck
	 * @param id: Id des Mentoring
	 * @return Mentoring
	 */
	@Override
	public Mentoring getMentoringById(int id) {
		
		Mentoring mento = null;
		DBCollection mentoringcoll = db.getCollection("Mentoringbeziehung");
		BasicDBObject object = new BasicDBObject("mentoringid", id);
		DBCursor cursor = mentoringcoll.find(object);
		BasicDBObject mentoring = (BasicDBObject) cursor.next();
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("YYYY"); 
			Date mentoringjahr = new Date();
			mentoringjahr = sdf.parse(mentoring.getString("jahr"));
			mento = new Mentoring(mentoring.getInt("mentoringid"), mentoring.getInt("mentorid"),
					mentoring.getInt("menteeid"), mentoring.getString("thema"), mentoringjahr); 
		}catch(Exception e){System.out.println(e.getMessage());}

		return mento;
	}
	
	
	/**
	 * Fuegt eine neue Mentoringbeziehung hinzu
	 * @param mentoring: Mentoring das hinzugefuegt werden soll
	 */
	@Override
	public void addMentoring(Mentoring mentoring) {
           	DBCollection mentoringcoll = db.getCollection("Mentoringbeziehung");
    		    		
    		SimpleDateFormat sdf = new SimpleDateFormat("YYYY"); 
    		 
    		DBCursor cursor = mentoringcoll.find();
    		int id=1;
    		for (int i=0; i<cursor.size(); i++) {
    			id++;
    		}
    		
    		BasicDBObject doc = new BasicDBObject();
    		doc.put("mentoringid", id);
    		doc.put("mentorid", mentoring.getMentorId());
    		doc.put("menteeid", mentoring.getMenteeId());
    		doc.put("thema", mentoring.getThema());
    		doc.put("jahr",  sdf.format(mentoring.getBeginnJahr()));
    		
    		mentoringcoll.insert(doc);
	}
		
	/**
	 * Loescht ein Mentoring
	 * @param id: Id des Mentoring das geloescht werden soll
	 */
	@Override
	public void deleteMentoring(int id) {
		DBCollection mentoringcoll = db.getCollection("Mentoringbeziehung");
		BasicDBObject object = new BasicDBObject("mentoringid", id);
		DBCursor cursor = mentoringcoll.find(object);
		BasicDBObject mentid = (BasicDBObject) cursor.next();
		mentoringcoll.remove(mentid);
		
	}	
	
    
	/**	
	 * Aendert das Thema und / oder Beginnjahr eines Mentoring
	 * @param mentoring: geaendertes Mentoring
	 */
	@Override
	public void updateMentoring(Mentoring mentoring) {
				
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY"); 
		
		DBCollection mentoringcoll = db.getCollection("Mentoringbeziehung");
		BasicDBObject object = new BasicDBObject("mentoringid", mentoring.getMentoringId());
		DBCursor cursor = mentoringcoll.find(object);
		if(cursor.size()>0){
			BasicDBObject update = new BasicDBObject();
						
			update.append("mentoringid", mentoring.getMentoringId());
			update.append("mentorid",  mentoring.getMentorId());
			update.append("menteeid",  mentoring.getMenteeId());
			update.append("thema", mentoring.getThema());
			update.append("jahr", sdf.format(mentoring.getBeginnJahr()));
						
			mentoringcoll.update(new BasicDBObject().append("mentoringid", mentoring.getMentoringId()), update);
		
		}
	}

}
