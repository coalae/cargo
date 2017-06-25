package repository;


import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;




import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import org.bson.Document;
import org.eclipse.jdt.internal.compiler.parser.ParserBasicInformation;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.client.MongoCollection;

import model.Mentoring;

import model.Mitarbeiter;
/**
 * 
 * @author Gregor Langner
 * Klasse mit der verbindung zur Datenbank um alle Mitarbeiter aktionen durchführen zu können
 *
 */
public class DatabaseMitarbeiterDAO implements MitarbeiterDAO {
	
	//DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
	//Connection connection;
	DatabaseHandlerMongoDB databaseHandler = DatabaseHandlerMongoDB.getInstance();
	DB db;
	/**
	 * {@code DatabaseMitarbeiterDAO()}
	 * Der Konstruktor :)
	 */
	public DatabaseMitarbeiterDAO() {
		try{
			db = databaseHandler.erstellen();
		}catch(Exception e){e.printStackTrace();}
	}
	/**
	 * SELECT kundenliste
	 * @return ArrayList
	 */
	public ArrayList<Mitarbeiter> select(){
		
        ArrayList<Mitarbeiter> mitarbeiterList=null;
		return mitarbeiterList;
	}
	
	
	/**
	 * Einfügen eines neuen Mitarbeiters in die Datenbank
	 */
	public void insert(String vor,String nach,int svnr,String spezi,int mittyp,String gebdate,
			String user,String pw,int statusint){ 
		DBCollection mitarbeiter = db.getCollection("Mitarbeiter");
		DBCursor cur = mitarbeiter.find();
		
		System.out.println(mitarbeiter.count());
		long next = mitarbeiter.count();
		next = next +1;
		BasicDBObject doc = new BasicDBObject();
		doc.put("mitarbeiterid", next);
		doc.put("vorname", vor);
		doc.put("nachname", nach);
		doc.put("svnr", svnr);
		doc.put("spezialisierung", spezi);
		doc.put("mitarbeitertyp", mittyp);
		doc.put("geburtsdatum", gebdate);
		doc.put("username", user);
		doc.put("passw", pw);
		doc.put("activ", statusint);
		mitarbeiter.insert(doc);
	}
		 
 
	/**
	 * UPDATE Mitarbeiter neues Passwort 
	 */
	public void update(String user,String oldpw,String newpw) {
		
		DBCollection coll = db.getCollection("Mitarbeiter");
		BasicDBObject query = new BasicDBObject("username", user);
		DBCursor cur = coll.find(query);
		if(cur.size()>0){
				for(int i=0;i<cur.size();i++){
					BasicDBObject next = (BasicDBObject) cur.next();
					if(user.equalsIgnoreCase(next.getString("username")) && oldpw.equalsIgnoreCase(next.getString("passw"))){
						BasicDBObject up = new BasicDBObject();
						System.out.println(next.getString("mitarbeiterid"));
						up.append("id", next.getLong("mitarbeiterid"));
						up.append("vorname", next.getString("vorname"));
						up.append("nachname", next.getString("nachname"));
						up.append("svnr", next.getInt("svnr"));
						up.append("spezialisierung", next.getString("spezialisierung"));
						up.append("mitarbeitertyp", next.getInt("mitarbeitertyp"));
						up.append("geburtsdatum", next.getString("geburtsdatum"));
						up.append("username", next.getString("username"));
						up.append("passw", newpw);
						up.append("activ", next.getInt("activ"));
						
						coll.update(new BasicDBObject().append("mitarbeiterid", next.getLong("mitarbeiterid")),up);
					}

				}
		}
	}

	
    /**
     * DELETE Mitarbeiter
     * @param id
     */
    public void deleteMitarbeiter(int id) {
    	   DBCollection mitarbeiter = db.getCollection("Mitarbeiter");
    	   BasicDBObject query = new BasicDBObject("svnr", id);
    	   DBCursor cur = mitarbeiter.find(query);
    	   for(int i=0;i<cur.size();i++){
    		   BasicDBObject act = (BasicDBObject) cur.next();
    		   if(id == act.getInt("svnr")){
    			   mitarbeiter.remove(act);
    		   }
    	   }
	}
	
	
	public ArrayList<Mitarbeiter> getMitarbeiterListe() {
		ArrayList<Mitarbeiter> mitarbeiterlist = new ArrayList<Mitarbeiter>();
		DBCollection Mitarbeitercoll = db.getCollection("Mitarbeiter");
		DBCursor cur = Mitarbeitercoll.find();
			while(cur.hasNext()) {
				BasicDBObject mit = (BasicDBObject) cur.next();
					boolean active = Boolean.parseBoolean(mit.getString("active"));
					GregorianCalendar cal = new GregorianCalendar();
					try{
					cal.getTime();
					Mitarbeiter tempo = new Mitarbeiter(mit.getInt("mitarbeiterid"), mit.getString("vorname"),
							mit.getString("nachname"), mit.getInt("svnr"), mit.getString("spezialisierung"),
							mit.getInt("mitarbeitertyp"), cal, mit.getString("username"), mit.getString("passw"),
							active);
					mitarbeiterlist.add(tempo);
					}catch(Exception e){System.out.println(e.getMessage());}
				}
			return mitarbeiterlist;

	}
    
    
	@Override
	public Mitarbeiter getMitarbeiterById(int id) {
		Mitarbeiter mitarbeiter =null;
	     DBCollection suche = db.getCollection("Mitarbeiter");
	     BasicDBObject query = new BasicDBObject("mitarbeiterid",id);
	     DBCursor cur = suche.find(query);
	     BasicDBObject mit =(BasicDBObject) cur.next();
	     boolean active =Boolean.parseBoolean(mit.getString("active"));
	     GregorianCalendar cal = new GregorianCalendar();
	     cal.getTime();
	     mitarbeiter =new Mitarbeiter(mit.getInt("mitarbeiterid"), mit.getString("vorname"),
					mit.getString("nachname"), mit.getInt("svnr"), mit.getString("spezialisierung"),
					mit.getInt("mitarbeitertyp"), cal, mit.getString("username"), mit.getString("passw"),
					active);
	     return mitarbeiter;

	}
}
