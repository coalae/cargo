package repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import model.Fahrzeug;

/**
 * Hier wird die Datenbankverbindung hergestellt und die fahrzeug Objekte verwertet
 * @author Nikola Babic
 *
 */

public class DatabaseFahrzeugDAO implements FahrzeugDAO {
  private static final String auslesen = "Select * from Fahrzeug";
  private static final String addKFZ =  "Insert into Fahrzeug (fahrzeugid,marke,modell,baujahr,farbe) values (?,?,?,?,?)";
  private static final String delKFZ = 	"Delete from Fahrzeug where fahrzeugid= ?";
  private static final String upMarke = 	"UPDATE Fahrzeug SET marke = ? where fahrzeugid=?";
  private static final String upModell = "UPDATE Fahrzeug SET modell = ? where fahrzeugid=?";
  private static final String upJahr = 	"UPDATE Fahrzeug SET baujahr = ? where fahrzeugid=?";
  private static final String upFarbe = 	"UPDATE Fahrzeug SET farbe = ? where fahrzeugid=?";
  private static final String upID = 		"UPDATE Fahrzeug SET fahrzeugid = ? where fahrzeugid=?";

  DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
  Connection connection;

/**
 * Konstruktor
 */
  public DatabaseFahrzeugDAO() {
	  try {
		  connection = DatabaseHandler.getConnection();
	  } catch (SQLException e) {
		  e.printStackTrace();
    }
  }
  
     
    
    /**
	 * INSERT Fahrzeug
	 * @param String Marke, modell, baujahr, farbe
	 */
    
    
    public void insert(String marke, String modell, String baujahr, String farbe) {
    	try {
    		PreparedStatement connect = connection.prepareStatement(addKFZ);
    		connect.setInt(1,0);
    		connect.setString(2, marke);
    		connect.setString(3, modell);
    		connect.setString(4, baujahr);
    		connect.setString(5, farbe);
    		connect.execute();
    		connect.close();
    		System.out.println("Letzter Wert wurde hinzugefügt");

    		}
    		catch(SQLException e){e.getMessage();}
    	}
    	
    /**
     * DELETE Fahrzeug
     * @param FahrzeugID
     */
    
    public void deleteFahrzeug (int id) {
    	try{
    		PreparedStatement connect = connection.prepareStatement(delKFZ);
    		connect.setInt(1, id);
    		connect.execute();
    		connect.close();
    		System.out.println("Inhalt wurde gelöscht");
    		
    		}
    		catch(SQLException e){e.getMessage();}
    	}
    	
/**
 * Retouriert eine Liste von typ Fahrzeug.    
 * @return returnFahrzeug
 */
	public ArrayList<Fahrzeug> getFahrzeugList () {
		ArrayList<Fahrzeug> returnFahrzeug = new ArrayList<Fahrzeug>();
		String SQLFahrzeug =  "SELECT fahrzeugid, marke, modell, baujahr, farbe FROM Fahrzeug";
		
		try {
			PreparedStatement up =connection.prepareStatement(SQLFahrzeug);
			ResultSet rs = up.executeQuery();
			
			while (rs.next()) {
				
				int fahrzeugid  = rs.getInt(1);
				String marke 	= rs.getString(2);
				String modell 	= rs.getString(3);
				String baujahr	= rs.getString(4);
				String farbe 		= rs.getString(5);
				
				Fahrzeug fahrzeug = new Fahrzeug(fahrzeugid, marke, modell, baujahr, farbe);
				returnFahrzeug.add(fahrzeug);
			}
			up.close();
    		}
    		catch(SQLException e){e.getMessage();}
			return returnFahrzeug;
	}

	/**
	 * Holt ein bestimmtes Fahrzeug
	 * @param int id
	 * @return fahrzeug
	 */ 
	public Fahrzeug getFahrzeugbyID(int id) {
		String SQLFahrzeug =  "SELECT fahrzeugid, marke, modell, baujahr, farbe FROM Fahrzeug WHERE fahrzeugid=' " + id + " ' ";
		Fahrzeug fahrzeug  = null;
		try {
			PreparedStatement up =connection.prepareStatement(SQLFahrzeug);
			ResultSet rs = up.executeQuery();
			
			while (rs.next()) {
				
				int fahrzeugid  = rs.getInt(1);
				String marke 	= rs.getString(2);
				String modell 	= rs.getString(3);
				String baujahr	= rs.getString(4);
				String farbe 		= rs.getString(5);
				
				fahrzeug = new Fahrzeug(fahrzeugid, marke, modell, baujahr, farbe);
				
			}
			
			up.close();
    		}
    		catch(SQLException e){e.getMessage();}
			
		return fahrzeug;
		
	}
    
	/**
	 * Aktualisiert die Marke
	 * @param id
	 * @param upmarke
	 */
	public void updateMarke (int id, String upmarke) {
		try {
			String update =" UPDATE Fahrzeug SET marke =' " + upmarke + " 'where fahrzeugid=' " +id+ " ' ";  
			PreparedStatement connect = connection.prepareStatement(update);
			connect.execute();
			connect.close();
		}
		catch(SQLException e){e.getStackTrace();}
		
	}
    
	
	/**
	 * Aktualisiert die Modell
	 * @param id
	 * @param upmodell
	 */
	public void updateModell (int id, String upmodel) {
		try {
			String update =" UPDATE Fahrzeug SET modell =' " + upmodel + " 'where fahrzeugid=' " +id+ " ' ";  
			PreparedStatement connect = connection.prepareStatement(update);
		
			connect.execute();
			connect.close();
			
			//PreparedStatement stm = connection.prepareStatement(update);
            //ResultSet rs = stm.executeQuery();
		}
		catch(Exception e){e.getStackTrace();}
		
	}
	
	
	/**
	 * Aktualisiert die Jahre
	 * @param id
	 * @param jahr
	 */
	public void updateJahr (int id, String jahr) {
		try {
			String update =" UPDATE Fahrzeug SET baujahr =' " + jahr + " 'where fahrzeugid=' " +id+ " ' ";  
			PreparedStatement connect = connection.prepareStatement(update);

			connect.execute();
			connect.close();
		}
		catch(SQLException e){e.getStackTrace();}
		
	}
    
	/**
	 * Aktualisiert die Farbe
	 * @param id
	 * @param upfarbe
	 */
	public void updateFarbe (int id, String upfarbe) {
		try {
			String update =" UPDATE Fahrzeug SET typ =' " + upfarbe + " 'where immobilienid=' " +id+ " ' ";  
			PreparedStatement connect = connection.prepareStatement(update);
			connect.execute();
			connect.close();
		}
		catch(SQLException e){e.getStackTrace();}
		
	}
	
	
	
  }


