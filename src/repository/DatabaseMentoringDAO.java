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
	
	/*DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
	Connection connection;*/
	
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
		
       /* ArrayList<Mentoring> mentoringList = new ArrayList<Mentoring>();
        
        String sql;   
        sql = "SELECT mentoringid, mentorid, menteeid, thema, jahr FROM Mentoringbeziehung";
        
        try {

            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            
            while (rs.next()) {
             int mentoringId=rs.getInt(1);
             int mentorId=rs.getInt(2);
             int menteeId=rs.getInt(3);
             String thema=rs.getString(4);
             String jahr=rs.getString(5);
                    
    		
             // GregorianCalendar fuer Jahr setzen
             SimpleDateFormat sdf = new SimpleDateFormat("YYYY"); 

     	     Date mentoringjahr = new Date();
     	     mentoringjahr = sdf.parse(jahr);
    	     
    	  
     	     Mentoring mentoring = new Mentoring(mentoringId, mentorId, menteeId, thema, mentoringjahr);
     	    
             mentoringList.add(mentoring);
             
             
             
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
			e.printStackTrace();
		} 
        return mentoringList;*/
		
		
		
		ArrayList<Mentoring> mentoringList = new ArrayList<Mentoring>();
		DBCollection mentoringcoll = db.getCollection("Mentoringbeziehung");
		DBCursor cursor = mentoringcoll.find();

			while(cursor.hasNext()) {
				BasicDBObject mentoring = (BasicDBObject) cursor.next();
		
					
					
					try{
						 SimpleDateFormat sdf = new SimpleDateFormat("YYYY"); 

			     	     Date mentoringjahr = new Date();
			     	     mentoringjahr = sdf.parse(mentoring.getString("jahr"));
					
					
					Mentoring ment = new Mentoring(mentoring.getInt("mentoringid"), mentoring.getInt("mentorid"),
							mentoring.getInt("menteeid"), mentoring.getString("thema"), mentoringjahr);
					mentoringList.add(ment);
					}catch(Exception e){System.out.println(e.getMessage());}
				}
			
			for (Mentoring s : mentoringList) {
				System.out.println(s.getMentoringId());
			
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
		
		
		

	   /* String sql;
	    sql = "SELECT * from Mentoringbeziehung WHERE mentoringid='" + id + "'";
	        
	        try {

	           PreparedStatement stm = connection.prepareStatement(sql);
	            ResultSet rs = stm.executeQuery();
	            
	            while (rs.next()) {
	            	 int mentoringId=rs.getInt(1);
	                 int mentorId=rs.getInt(2);
	                 int menteeId=rs.getInt(3);
	                 String thema=rs.getString(4);
	                 String jahr=rs.getString(5);
	                                		
	                 // Date fuer Jahr setzen
	                 SimpleDateFormat sdf = new SimpleDateFormat("YYYY"); 

	         	     Date mentoringjahr = new Date();
	         	     mentoringjahr = sdf.parse(jahr);
	             
	             mentoring=new Mentoring(mentoringId, mentorId, menteeId, thema, mentoringjahr); 
	             stm.close();
	             
	            }
	        } catch (SQLException e) {
	        } catch (ParseException e) {
				e.printStackTrace();
			} 
	        return mentoring;*/
		
		
		Mentoring mento = null;
		DBCollection mentoringcoll = db.getCollection("Mentoringbeziehung");
		BasicDBObject object = new BasicDBObject("id", id);
		DBCursor cursor = mentoringcoll.find(object);
		for (int i=0; i<cursor.size(); i++) {
			BasicDBObject mentoring = (BasicDBObject) cursor.next();
			if(id == mentoring.getInt("id")){
				try{
					SimpleDateFormat sdf = new SimpleDateFormat("YYYY"); 

					Date mentoringjahr = new Date();
					mentoringjahr = sdf.parse(mentoring.getString("jahr"));
					mento = new Mentoring(mentoring.getInt("mentoringid"), mentoring.getInt("mentorid"),
						mentoring.getInt("menteeid"), mentoring.getString("thema"), mentoringjahr); 
				}catch(Exception e){System.out.println(e.getMessage());}
			}

		}
		return mento;
	}
	
	
	/**
	 * Fuegt eine neue Mentoringbeziehung hinzu
	 * @param mentoring: Mentoring das hinzugefuegt werden soll
	 */
	@Override
	public void addMentoring(Mentoring mentoring) {
       /* try {
        	
              // insert statement
              String query = "INSERT INTO Mentoringbeziehung (mentoringid,mentorid, menteeid,thema,jahr) VALUES (?,?,?,?,?)";
	
              // create insert preparedstatement
              PreparedStatement stm = connection.prepareStatement(query);
              SimpleDateFormat sdf = new SimpleDateFormat("YYYY"); 
              stm.setInt (1, 0);
              stm.setInt (2, mentoring.getMentorId());
              stm.setInt (3, mentoring.getMenteeId());
              stm.setString(4, mentoring.getThema());
              stm.setString (5, sdf.format(mentoring.getBeginnJahr()));
            
              // execute the preparedstatement
              stm.execute();
              
              // close
              stm.close();
             
		  } catch (SQLException e) {
		        e.printStackTrace();
		  }*/
        	
        	
        	DBCollection mentoringcoll = db.getCollection("Mentoringbeziehung");
    		DBCursor cur = mentoringcoll.find();
    		
    		SimpleDateFormat sdf = new SimpleDateFormat("YYYY"); 
    		
    		System.out.println(mentoringcoll.count());
    		long next = mentoringcoll.count();
    		next = next +1;
    		BasicDBObject doc = new BasicDBObject();
    		doc.put("mentoringid", next);
    		doc.put("mentorid", mentoring.getMentorId());
    		doc.put("menteeid", mentoring.getMenteeId());
    		doc.put("thema", mentoring.getThema());
    		doc.put("jahr",  sdf.format(mentoring.getBeginnJahr()));
    		
    		mentoringcoll.insert(doc);
    		//getMitarbeiterListe();
		    
	}
		
	/**
	 * Loescht ein Mentoring
	 * @param id: Id des Mentoring das geloescht werden soll
	 */
	@Override
	public void deleteMentoring(int id) {
		
	    /*String sql = "DELETE from Mentoringbeziehung WHERE mentoringid ='" + id + "'"; 

        try {
        	PreparedStatement stmt = connection.prepareStatement(sql);        	         	 
        	stmt.execute(sql);            
        } catch (SQLException e) {
            e.printStackTrace();
        } 
            
        return;*/
		
		DBCollection mentoringcoll = db.getCollection("Mentoringbeziehung");
		BasicDBObject object = new BasicDBObject("id", id);
		DBCursor cursor = mentoringcoll.find(object);
		for (int i=0; i<cursor.size(); i++) {
			BasicDBObject mentid = (BasicDBObject) cursor.next();
			if(id == mentid.getInt("id")){
				mentoringcoll.remove(mentid);
			}
		}
	}	
	
    
	/**	
	 * Aendert das Thema und / oder Beginnjahr eines Mentoring
	 * @param mentoring: geaendertes Mentoring
	 */
	@Override
	public void updateMentoring(Mentoring mentoring) {
		/* String sql = "UPDATE Mentoringbeziehung SET mentorid=?, menteeid=?, thema =?, jahr=? where mentoringid =?";
		       
		    PreparedStatement preparedStmt = null;
		    try {
		    	SimpleDateFormat sdf = new SimpleDateFormat("YYYY"); 
		        preparedStmt = connection.prepareStatement(sql);
		        preparedStmt.setInt(1, mentoring.getMentorId());
		        preparedStmt.setInt(2, mentoring.getMenteeId());
		        preparedStmt.setString(3, mentoring.getThema());
		        preparedStmt.setString(4, sdf.format(mentoring.getBeginnJahr()));
		        preparedStmt.setInt(5, mentoring.getMentoringId());
		        
		      	        
		        preparedStmt.executeUpdate();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		
		    
		    return;		
	}    */
		
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY"); 
		
		DBCollection mentoringcoll = db.getCollection("Mentoringbeziehung");
		BasicDBObject object = new BasicDBObject("mentoringid", mentoring.getMentoringId());
		DBCursor cursor = mentoringcoll.find(object);
		if(cursor.size()>0){
				for(int i=0; i<cursor.size(); i++){
					BasicDBObject next = (BasicDBObject) cursor.next();
					if(mentoring.getMentoringId() == next.getInt("id")){
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
	}

}
