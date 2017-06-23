package repository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.text.StyledEditorKit.BoldAction;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
/**
 * 
 * @author Gregor Langner
 *Diese Klasse eralisiert den Login
 */
public class DatabaseLoginDAO implements LoginDAO{
	private static String checkKunde="Select * from Kunde where username=? and passw=?";
	private static String checkMitarbeiter="Select * from Mitarbeiter where username= ? and passw= ?";
	private static String spezi="Select * from Mitarbeiter where username= ? and passw= ?";
	//DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
	//private Connection connection;
	DatabaseHandlerMongoDB databaseHandler = DatabaseHandlerMongoDB.getInstance();
	private DB db;
	/**
	 * {@code DatabaseLoginDAO}
	 * Hier wird eine connection zur Datenbank erstellt
	 */
	public DatabaseLoginDAO(){
		try{
			db=databaseHandler.erstellen();
		}
		catch(Exception e){e.getMessage();}
	}
/**
 * {@code String check (String name,String pw)}
 * @param name  ist der username der geprüft werden muss
 * @param pw das eingegebene passwort dazu 
 */
	@Override
	public String check(String name, String pwd) {
		String check="";
		
		try{
			DBCollection kunde=db.getCollection("Kunde");
			BasicDBObject query = new BasicDBObject("name", name);
			DBCursor cur = kunde.find(query);
			System.out.println(cur.size());
			if(cur.size()>0){
				for(int i=0;i<cur.size();i++){
					BasicDBObject test = (BasicDBObject) cur.next();
					System.out.println(test.getString("pwd"));
					if(pwd.equalsIgnoreCase(test.getString("pwd"))){
						check =  "Kunde";
					}
				}
			}
			
				DBCollection mitarbeiter=db.getCollection("Mitarbeiter");
				BasicDBObject queryM = new BasicDBObject("username",name);
				DBCursor curM = mitarbeiter.find(queryM);
				System.out.println("Vor Mitarbeiter");
				System.out.println(curM.size());
					for(int i=0;i<curM.size();i++){
						System.out.println("Mitarbeiter");
						BasicDBObject testM = (BasicDBObject) curM.next();
						if(pwd.equalsIgnoreCase(testM.getString("passw"))){
							check=  "Mitarbeiter";
						}
					}
		}
		catch(Exception e){}
		
		return check;
	}
/**
 * Funktion die Prüfen soll welche Spezialisierung der Mitarbeiter hat
 * {@code String spez (String name,String pwd}
 * @param String username des Mitarbeiters
 * @param String pwd eingegebene Passwort
 * @return als return wird die Spezialisierung geben wenn nichts gefunden wurde null
 */
	@Override
	public String spez(String name, String pwd) {
		return null;
	}
}
