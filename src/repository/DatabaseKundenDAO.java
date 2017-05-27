package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Kunde;

/**
 * Das Interface KundenDAO bietet Methoden fuer das Speichern, Loeschen, Veraendern von 
 * Instanzen der Klasse Kunde.
 * @author Cordula Eggerth
 */
public class DatabaseKundenDAO implements KundenDAO {
	private String DBAdresse = "jdbc:mariadb://localhost:3306/cargo";
	private String username = "root";
	private String password = "coalacoala1";
	
	/**
	 * get kundenliste (i.e. SELECT * FROM kunde).
	 * Liste aller Kunden aus der DB holen.
	 * @return ArrayList von Kunden
	 */
	@Override
	public ArrayList<Kunde> getKundenListe() {
		Connection con = null;
        ArrayList<Kunde> kundenList = new ArrayList<Kunde>();
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            con = DriverManager.getConnection(DBAdresse, username, password);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql;   
        sql = "SELECT kundenId, vorname, nachname, iban, bic, username, passw, active FROM Kunde";
        PreparedStatement prest = null;
        try {

            prest = con.prepareStatement(sql);
            ResultSet rs = prest.executeQuery();
            
            while (rs.next()) {
            /*	DB CHECK
                System.out.println(rs.getInt(1));
            	System.out.println(rs.getString(2));
            	System.out.println(rs.getString(3));
            */
            
             int id=rs.getInt(1);
             String vorname=rs.getString(2);
             String nachname=rs.getString(3);
             int iban=rs.getInt(4);
             String bic=rs.getString(5);
             String username=rs.getString(6);
             String password=rs.getString(7);
             boolean active=rs.getBoolean(8);

             Kunde kunde=new Kunde(id,vorname,nachname,iban,bic,username,password,active);
             
             kundenList.add(kunde);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (prest != null)
                    prest.close();
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return kundenList;
	}


	/**
     * Get Kunde by Id (SELECT ... FROM kunde WHERE kundenId= ...).
     * Kunde anhand der als int uebergebenen id suchen.
     * @param id
     * @return
     */
	@Override 
	public Kunde getKundeById(int id){
		  
		Connection con = null;
		Kunde suchKunde = null;
		try {
            Class.forName("org.mariadb.jdbc.Driver");
            con = DriverManager.getConnection(DBAdresse, username, password);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

	    String sql;  
	    sql = "SELECT kundenId, vorname, nachname, iban, bic, username, passw, active FROM Kunde WHERE kundenId='" + id + "'";
	        PreparedStatement prest = null;
	        try {

	            prest = con.prepareStatement(sql);
	            ResultSet rs = prest.executeQuery();

	            while (rs.next()) {
	             /* DB CHECK 
	            	System.out.println(rs.getInt(1));
	            	System.out.println(rs.getString(2));
	            	System.out.println(rs.getString(3));
	             */        
	                int kundenId=rs.getInt(1);
	                String vorname=rs.getString(2);
	                String nachname=rs.getString(3);
	                int iban=rs.getInt(4);
	                String bic=rs.getString(5);
	                String username=rs.getString(6);
	                String password=rs.getString(7);
	                boolean active=rs.getBoolean(8);
	                suchKunde=new Kunde(kundenId,vorname,nachname,iban,bic,username,password,active);	
	            }     
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (prest != null)
	                    prest.close();
	                if (con != null)
	                    con.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }

	        }
			return suchKunde;
	}
	    	
	
	/**
	 * addKunde (INSERT INTO kunde ... VALUES ...).
	 * Neuen Kunde hinzufuegen.
	 * @param kunde
	 */
	@Override
	public void addKunde(Kunde kunde) {
		Connection con = null;
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            con = DriverManager.getConnection(DBAdresse, username, password);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
        	
              // insert statement   
              String query = " insert into Kunde (vorname, nachname, iban, bic, username, passw, active)"
                + " values (?, ?, ?, ?, ?, ?, ?)";
              // create insert preparedstatement
              PreparedStatement preparedStmt = con.prepareStatement(query);
              preparedStmt.setString (1, kunde.getVorname());
              preparedStmt.setString (2, kunde.getNachname());
              preparedStmt.setInt (3, kunde.getIban());
              preparedStmt.setString(4, kunde.getBic());
              preparedStmt.setString(5, kunde.getUsername());
              preparedStmt.setString(6, kunde.getPassword()); 
              preparedStmt.setBoolean(7, kunde.isActive()); 

              // execute the preparedstatement
              preparedStmt.execute();
              
              con.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }finally {
		        try {
		            /*
		            if (preparedStmt != null)
		            	preparedStmt.close();
		            */
		            if (con != null)
		                con.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }		
	}
	
	
	/**
	 * deleteKunde (DELETE FROM kunde WHERE kundenId=...).
	 * Bestehenden Kunde loeschen.
	 */
	@Override
	public void deleteKunde(int id) {
  		Connection con = null;
		
		try {
            Class.forName("org.mariadb.jdbc.Driver");
            con = DriverManager.getConnection(DBAdresse, username, password);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	    String sql = "DELETE FROM Kunde WHERE kundenId='" + id + "'";

        try {
        	Statement stmt = con.createStatement();        	         	 
        	stmt.execute(sql);            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
               // if (stmt != null)
               //     stmt.close();
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }		
	}	
	
	
	/**
	 * updateKunde (by Id) (UPDATE kunde SET ... WHERE kundenId=...).
	 * Bestehenden Kunde updaten bzw. aendern.
	 */
	@Override
	public void updateKunde(Kunde kunde) {
		 // TESTDATEN:	 
		 // Kunde kunde1 = new Kunde(3, "VornameUpdate", "NachnameUpdate", 11111, "BICupdate", "usernameUpdate", "pwUpdate", true);
			
		 String sql; 
		 sql = "UPDATE Kunde SET vorname=?, nachname=?, iban=?, bic=?, username=?, passw=?, active=? WHERE kundenId = ?";
		
			Connection con = null;
	        //User user = null;
	        try {
	            Class.forName("org.mariadb.jdbc.Driver");
	            con = DriverManager.getConnection(DBAdresse, username, password);

	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        
		    PreparedStatement preparedStmt = null;
		    try {
		        preparedStmt = con.prepareStatement(sql);
		        preparedStmt.setString(1, kunde.getVorname());
		        preparedStmt.setString(2, kunde.getNachname());
		        preparedStmt.setInt(3, kunde.getIban());
		        preparedStmt.setString(4, kunde.getBic());
		        preparedStmt.setString(5, kunde.getUsername());
		        preparedStmt.setString(6, kunde.getPassword());
		        preparedStmt.setBoolean(7, kunde.isActive());
		        preparedStmt.setInt(8, kunde.getId());
		        
		        preparedStmt.executeUpdate();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }finally {
		        try {
		            if (preparedStmt != null)
		                preparedStmt.close();
		            if (con != null)
		                con.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		
		    }
		    return;		
	}	
	
	


/*}
	UpdateUser updateUser = new UpdateUser();
	boolean check = false;
	try {
	check = updateUser.execute(user).get();
	} catch (InterruptedException e) {
	e.printStackTrace();
	} catch (ExecutionException e) {
	e.printStackTrace();
	}
	return check;
	}
*/  
   
    
	/* 
	 * main Funktion fuer Tests
	 * @param args
	 
	public static void main(String[] args){
		DatabaseKundenDAO dao = new DatabaseKundenDAO();
		 dao.select();
		// dao.insert();
		// dao.update();
		// Kunde kundeById = dao.getKundeById(1);
		// dao.deleteKundeById(3);
	}
   */
}
