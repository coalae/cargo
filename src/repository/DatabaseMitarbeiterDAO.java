package repository;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import org.eclipse.jdt.internal.compiler.parser.ParserBasicInformation;

import model.Mentoring;
import model.Mitarbeiter;

public class DatabaseMitarbeiterDAO implements MitarbeiterDAO {
	
	private static final String all = "Select * from Mitarbeiter";
	private static final String delID = "DELETE from Mitarbeiter where svnr = ?";
	private static final String ausID = "Select * from Mitarbeiter where mitarbeiterid =?";
	private static final String upPassw = "UPDATE from Mitarbeiter SET passw = ? where username=?";
	private static final String upActiv = "UPDATE from Mitarbeiter SET active = ? where username =?";
	private static final String add = "Insert into Mitarbeiter values (?,?,?,?,?,?,?,?,?,?)";
	DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
	Connection connection;
	
	public DatabaseMitarbeiterDAO() {
		try{
			connection= DatabaseHandler.getConnection();
		}catch(SQLException e){e.printStackTrace();}
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
	 * INSERT Mitarbeiter
	 */
	public void insert(String vor,String nach,int svnr,String spezi,int mittyp,String gebdate,
			String user,String pw,int statusint){
		try{
		PreparedStatement ein = connection.prepareStatement(add);
		ein.setInt(1,0);
		ein.setString(2,vor);
		ein.setString(3,nach);
		ein.setInt(4,svnr);
		ein.setString(5,spezi);
		ein.setInt(6,mittyp);
		ein.setString(7,gebdate);
		ein.setString(8,user);
		ein.setString(9,pw);
		ein.setInt(10,statusint);
		ein.execute();
		ein.close();
		System.out.println("Hab hinzugefügt letzter wert");

		}
		catch(SQLException e){e.getMessage();}
	}
		 
 
	/**
	 * UPDATE Mitarbeiter (by Id)
	 */
	public void update(String user,String oldpw,String newpw) {
		try{
			String update="UPDATE Mitarbeiter SET passw ='"+ newpw +"' where username='"+user+"'";
			System.out.println(update);
			PreparedStatement up =connection.prepareStatement(update);
			up.execute();
			up.close();
		}catch(Exception e){e.getMessage();}
	}

	
    /**
     * DELETE Mitarbeiter
     * @param id
     */
    public void deleteMitarbeiter(int id) {
    	try{
    		PreparedStatement ein = connection.prepareStatement(delID);
    		ein.setInt(1, id);
    		ein.execute();
    		ein.close();
    	}
    	catch(SQLException e){e.getStackTrace();}
	}
    
    
    
	public ArrayList<Mitarbeiter> getMiarbeiterListe() {
		
        ArrayList<Mitarbeiter> mitarbeiterliste = new ArrayList<Mitarbeiter>();
        
        String sql;   
        sql = "SELECT mitarbeiterid, vorname, nachname, svnr, spezialisierung, mitarbeitertyp, geburtsdatum, username, passw, active FROM Mitarbeiter";
        
        try {

            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            
            while (rs.next()) {
             int mitarbeiterid=rs.getInt(1);
             String vorname=rs.getString(2);
             String nachname=rs.getString(3);
             int svnr = rs.getInt(4);
             String spezialisierung=rs.getString(5);
             int mitarbeitertyp=rs.getInt(6);
             String geburtsdatum = rs.getString(7);
             String username = rs.getString(8);
             String passw = rs.getString(9);
             String active = rs.getString(10);
             
             boolean active_ = Boolean.parseBoolean(active);
             SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy"); 
             GregorianCalendar cal = new GregorianCalendar();
     	     Date geburtsdatum_ = sdf.parse(geburtsdatum);
     	     cal.setTime(geburtsdatum_);
                    
    		Mitarbeiter mitarbeiter = new Mitarbeiter(mitarbeiterid, vorname, nachname, svnr, spezialisierung, mitarbeitertyp, cal, username, passw, active_);
    		
             mitarbeiterliste.add(mitarbeiter);
             
             System.out.println(mitarbeiter);
             
             
             
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
			e.printStackTrace();
		} 
        return mitarbeiterliste;
        
     }	
    
    
	@Override
	public Mitarbeiter getMitarbeiterById(int id) {
		String sql;
		Mitarbeiter mitarbeiter = null;
	    sql = "SELECT mitarbeiterid, vorname, nachname, svnr, spezialisierung, mitarbeitertyp, geburtsdatum, username, passw, active from Mitarbeiter WHERE mitarbeiterid='" + id + "'";
	        
	        try {

	           PreparedStatement stm = connection.prepareStatement(sql);
	            ResultSet rs = stm.executeQuery();
	            
	            while (rs.next()){
	            int mitarbeiterid=rs.getInt(1);
	             String vorname=rs.getString(2);
	             String nachname=rs.getString(3);
	             int svnr = rs.getInt(4);
	             String spezialisierung=rs.getString(5);
	             int mitarbeitertyp=rs.getInt(6);
	             String geburtsdatum = rs.getString(7);
	             String username = rs.getString(8);
	             String passw = rs.getString(9);
	             String active = rs.getString(10);
	             
	             boolean active_ = Boolean.parseBoolean(active);
	             SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy"); 
	             GregorianCalendar cal = new GregorianCalendar();
	     	     Date geburtsdatum_ = sdf.parse(geburtsdatum);
	     	     cal.setTime(geburtsdatum_);
	                    
	     	     mitarbeiter = new Mitarbeiter(mitarbeiterid, vorname, nachname, svnr, spezialisierung, mitarbeitertyp, cal, username, passw, active_);
	             
	            }
	            stm.close();
	              
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			} 
	        
	        
	        return mitarbeiter;

	}	

}
