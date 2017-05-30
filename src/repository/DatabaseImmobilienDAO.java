package repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import model.Fahrzeug;
import model.Immobilie;

/**
 * Hier wird die Datenbankverbindung hergestellt und die Immobilie Objekte verwertet
 * @author Nikola Babic
 *
 */

public class DatabaseImmobilienDAO implements ImmobilienDAO {
  private static final String auslesen = "Select * from Immobilie";
  private static final String suche="Select * from Immobilie where id = ?";
  private static final String addImmo =  "Insert into Immobilie (immobilienid, typ, immobilienname) values (?,?,?)";
  private static final String delImmo = 	"Delete from Immobilie where immobilienid= ?";


  DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
  Connection connection;

/**
 * konstruktor
 */
  public DatabaseImmobilienDAO() {
	  try {
		  connection = DatabaseHandler.getConnection();
	  } catch (SQLException e) {
		  e.printStackTrace();
    }
  }
  
     
    
    /**
	 * INSERT Gebäude
	 * @param String Marke, modell, baujahr, farbe
	 */
    
    
    public void insert(int typ, String name) {
    	try {
    		PreparedStatement connect = connection.prepareStatement(addImmo);
    		connect.setInt(1,0);
    		connect.setInt(2, typ);
    		connect.setString(3, name);
    		connect.execute();
    		connect.close();
    		System.out.println("Letzter Wert wurde hinzugefügt");

    		}
    		catch(SQLException e){e.getMessage();}
    	}
    	
    /**
     * DELETE GEbäude
     * @param ImmobilieID
     */
    
    public void deleteGebaude (int id) {
    	try{
    		PreparedStatement connect = connection.prepareStatement(delImmo);
    		connect.setInt(1, id);
    		connect.execute();
    		connect.close();
    		System.out.println("Inhalt wurde gelöscht");
    		}
    		catch(SQLException e){e.getMessage();}
    	}
    	
    /**
     * Retouriert eine Liste von typ Immobilie.    
     * @return returnGebäude
     */

	public ArrayList<Immobilie> getGebaudeList () {
		ArrayList<Immobilie> returnGebaude = new ArrayList<Immobilie>();
		String SQLGebaude=  "SELECT Immobilienid, typ, immobilienname FROM Immobilie";
		
		try {
			PreparedStatement up =connection.prepareStatement(SQLGebaude);
			ResultSet rs = up.executeQuery();
			
			while (rs.next()) {
				
				int immobilienid  = rs.getInt(1);
				int typ 	= rs.getInt(2);
				String name 	= rs.getString(3);
				
				Immobilie gebaude = new Immobilie(immobilienid, typ, name);
				returnGebaude.add(gebaude);
				
			}
			up.close();
    		}
    		catch(SQLException e){e.getMessage();}
			return returnGebaude;
	}

	/**
	 * Holt ein bestimmtes Gebäude
	 * @param int id
	 * @return immobilie
	 */ 
	public Immobilie getGebaudebyID(int id) {
		String SQLGebäude =  "SELECT Immobilienid, typ, name FROM Immobilie WHERE immobilienid=' " + id + " ' ";
		Immobilie gebäude  =null;
		try {
			PreparedStatement up =connection.prepareStatement(SQLGebäude);
			ResultSet rs = up.executeQuery();
			
			while (rs.next()) {
				
				int immobilienid  = rs.getInt(1);
				int typ 	= rs.getInt(2);
				String name 	= rs.getString(3);
				
				gebäude = new Immobilie(immobilienid, typ, name);
				
				
			}
			up.close();
    		}
    		catch(SQLException e){e.getMessage();}
			
		return gebäude;
	}
    
	/**
	 * Ändern die Fahrzeugdaten
	 * @param int id
	 * @return typ
	 */ 
	public void updatetyp (int id, int typ) {
		try {
			String update =" UPDATE Immobilie SET typ =' " + typ + " 'where immobilienid=' " +id+ " ' ";  
			PreparedStatement connect = connection.prepareStatement(update);
			connect.execute();
			connect.close();
		}
		catch(SQLException e){e.getStackTrace();}
		
	}
    
	/**
	 * Ändern die Fahrzeugdaten
	 * @param int id
	 * @return name
	 */ 
	public void updatename (int id, String name) {
		try {
			String update =" UPDATE Immobilie SET immobilienname =' " + name + " ' where immobilienid='"+id+" ' "; 
			PreparedStatement connect = connection.prepareStatement(update);
			connect.execute();
			connect.close();
		}
		catch(SQLException e){e.getStackTrace();}
		
	}
	
	
  }


 