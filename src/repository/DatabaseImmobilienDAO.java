package repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import model.Fahrzeug;
import model.Immobilie;


public class DatabaseImmobilienDAO implements ImmobilienDAO {
  private static final String auslesen = "Select * from Immobilie";
  private static final String addImmo =  "Insert into Immobilie (immobilienid, typ, immobilienname) values (?,?,?)";
  private static final String delImmo = 	"Delete from Immobilie where immobilienid= ?";


  DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
  Connection connection;


  public DatabaseImmobilienDAO() {
	  try {
		  connection = DatabaseHandler.getConnection();
	  } catch (SQLException e) {
		  e.printStackTrace();
    }
  }
  
     
    
    /**
	 * INSERT Geb�ude
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
    		System.out.println("Letzter Wert wurde hinzugef�gt");

    		}
    		catch(SQLException e){e.getMessage();}
    	}
    	
    /**
     * DELETE Fahrzeug
     * @param FahrzeugID
     */
    
    public void deleteGeb�ude (int id) {
    	try{
    		PreparedStatement connect = connection.prepareStatement(delImmo);
    		connect.setInt(1, id);
    		connect.close();
    		System.out.println("Inhalt wurde gel�scht");
    		}
    		catch(SQLException e){e.getMessage();}
    	}
    	
    

	public ArrayList<Immobilie> getGeb�udeList () {
		ArrayList<Immobilie> returnGeb�ude = null;
		String SQLGeb�ude=  "SELECT fahrzeugid, marke, modell, baujahr, farbe FROM Fahrzeug";
		
		try {
			PreparedStatement up =connection.prepareStatement(SQLGeb�ude);
			ResultSet rs = up.executeQuery();
			
			while (rs.next()) {
				
				int immobilienid  = rs.getInt(1);
				int typ 	= rs.getInt(2);
				String name 	= rs.getString(3);
				
				Immobilie geb�ude = new Immobilie(immobilienid, typ, name);
				returnGeb�ude.add(geb�ude);
				
			}
			up.close();
    		}
    		catch(SQLException e){e.getMessage();}
			return returnGeb�ude;
	}

	
	public Immobilie getGeb�udebyID(int id) {
		String SQLGeb�ude =  "SELECT fahrzeugid, marke, modell, baujahr, farbe FROM Fahrzeug WHERE fahrzeugid=" + id;
		Immobilie geb�ude  =null;
		try {
			PreparedStatement up =connection.prepareStatement(SQLGeb�ude);
			ResultSet rs = up.executeQuery();
			
			while (rs.next()) {
				
				int immobilienid  = rs.getInt(1);
				int typ 	= rs.getInt(2);
				String name 	= rs.getString(3);
				
				geb�ude = new Immobilie(immobilienid, typ, name);
				
				
			}
			up.close();
    		}
    		catch(SQLException e){e.getMessage();}
			
		return geb�ude;
	}
    
	
	public void updatetyp (int id, int typ) {
		try {
			String update =" UPDATE Immobilie SET typ =" + typ + "where immobilienid='"+id;  
			PreparedStatement connect = connection.prepareStatement(update);
			connect.execute();
			connect.close();
		}
		catch(SQLException e){e.getStackTrace();}
		
	}
    
	public void updatename (int id, String name) {
		try {
			String update =" UPDATE Immobilie SET immobilienname =" + name + "where immobilienid='"+id;  
			PreparedStatement connect = connection.prepareStatement(update);
			connect.execute();
			connect.close();
		}
		catch(SQLException e){e.getStackTrace();}
		
	}
	
	
  }


