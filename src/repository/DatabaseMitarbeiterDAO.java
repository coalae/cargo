package repository;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
	@Override
	public Mitarbeiter getMitarbeiterById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}
