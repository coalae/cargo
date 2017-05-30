package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import model.Kurs;

/** 
 * Die Klasse DatabaseKursDAO implementiert die Methoden des Interface KursDAO.
 * Die Methoden dienen zum Speichern, Loeschen, Aendern von Instanzen der Klasse Kurs und 
 * zum Hinzufuegen und Loeschen von Teilnehmern an einem Kurs.
 * @author Cordula Eggerth
 */
public class DatabaseKursDAO implements KursDAO {
	private String DBAdresse = "jdbc:mysql://langnerg86.mysql.univie.ac.at/langnerg86?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";  // jdbc:mariadb://localhost:3306/cargo
	private String username = "langnerg86"; // root
	private String password = "dbkzlxlq1"; // coalacoala1

	
	/**
	 * getKursListe (i.e. SELECT * FROM kurs) holt eine Liste aller Kurse aus der DB.
	 * @return ArrayList von Kursen
	 */
	@Override
	public ArrayList<Kurs> getKursListe() {
		Connection con = null;
        ArrayList<Kurs> kursList = new ArrayList<Kurs>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // org.mariadb.jdbc.Driver
            con = DriverManager.getConnection(DBAdresse, username, password);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql;   
        sql = "SELECT kursId, kursname, kurslevel, beginn, ende, preis, betreuerId, maxAnzahl, immobilie FROM Kurs";
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
             int kursId=rs.getInt(1);
             String kursname=rs.getString(2);
             String level=rs.getString(3);
             String beginnstr=rs.getString(4);
             String endestr=rs.getString(5);
             double preis=rs.getDouble(6);
             int betreuerId=rs.getInt(7);
             int maxAnzahl=rs.getInt(8);
             int immobilie=rs.getInt(9);
             
             // Teilnehmerliste des Kurses setzen
             ArrayList<Integer> teilnehmerliste = getTeilnehmerListe(kursId);
    		
             // GregorianCalendar fuer beginn und ende setzen
             SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy"); 

     	     GregorianCalendar beginncal = new GregorianCalendar();
     	     beginncal.setLenient(false);
     	     beginncal.setTime(sdf.parse(beginnstr));
    	     
    	     GregorianCalendar endecal = new GregorianCalendar();
    	     endecal.setLenient(false);
     	     beginncal.setTime(sdf.parse(endestr));
     	     
			 // TEST MIT DUMMY-betreuerId und DUMMY-immobilienId
             Kurs kurs=new Kurs(kursId,kursname,level,beginncal,endecal,preis,betreuerId,maxAnzahl,immobilie,teilnehmerliste);
             
             kursList.add(kurs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
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
        return kursList;
	}	
	

	/**
     * Get Kurs by Id (SELECT ... FROM kurs WHERE kursId= ...).
     * Kurs anhand der als int uebergebenen id suchen.
     * @param id
     * @return
     */
	@Override
    public Kurs getKursById(int id){
    	  
		Connection con = null;
		Kurs kurs = null;
		try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // org.mariadb.jdbc.Driver
            con = DriverManager.getConnection(DBAdresse, username, password);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

	    String sql;
	    sql = "SELECT kursId, kursname, kurslevel, beginn, ende, preis, betreuerId, maxAnzahl, immobilie FROM Kurs" +
	    		" WHERE kursId='" + id + "'";
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
	             int kursId=rs.getInt(1);
	             String kursname=rs.getString(2);
	             String level=rs.getString(3);
	             String beginnstr=rs.getString(4);
	             String endestr=rs.getString(5);
	             double preis=rs.getDouble(6);
	             int betreuerId=rs.getInt(7);
	             int maxAnzahl=rs.getInt(8);
	             int immobilie=rs.getInt(9);
	             
	             // Teilnehmerliste des Kurses setzen
	             ArrayList<Integer> teilnehmerliste = null;
	               if(getTeilnehmerListe(kursId)!=null || getTeilnehmerListe(kursId).size()!=0){
	            	 teilnehmerliste = getTeilnehmerListe(kursId);
	               }

	             // GregorianCalendar fuer beginn und ende setzen
	             SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy"); 

	     	     GregorianCalendar beginncal = new GregorianCalendar();
	     	     beginncal.setTime(sdf.parse(beginnstr));
	    	     
	    	     GregorianCalendar endecal = new GregorianCalendar();
	     	     beginncal.setTime(sdf.parse(endestr));
	     	     
				 // TEST MIT DUMMY-betreuerId und DUMMY-immobilienId
	             kurs=new Kurs(kursId,kursname,level,beginncal,endecal,preis,betreuerId,maxAnzahl,immobilie,teilnehmerliste); 
	            }
	        } catch (SQLException e) {
	        } catch (ParseException e) {
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
	        return kurs;
		}


	/**
	 * Neuen Kurs hinzufuegen
	 * @param kurs
	 */
	@Override
	public void addKurs(Kurs kurs) {
		Connection con = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // org.mariadb.jdbc.Driver
            con = DriverManager.getConnection(DBAdresse, username, password);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
        	
              // insert statement
              String query = " insert into Kurs (kursname, kurslevel, beginn, ende, preis, betreuerId, maxAnzahl, immobilie)"
                + " values (?, ?, ?, ?, ?, ?, ?, ?)";

              // create insert preparedstatement
              PreparedStatement preparedStmt = con.prepareStatement(query);
              SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy"); 
              preparedStmt.setString (1, kurs.getKursname());
              preparedStmt.setString (2, kurs.getLevel());
              preparedStmt.setString (3, sdf.format(kurs.getBeginn().getTime()));
              preparedStmt.setString(4, sdf.format(kurs.getEnde().getTime()));
              preparedStmt.setDouble(5, kurs.getPreis());
              preparedStmt.setInt(6, kurs.getBetreuerId()); 
              preparedStmt.setInt(7, kurs.getMaxAnzahl());
              preparedStmt.setInt(8, kurs.getImmobilie());

              // execute the preparedstatement
              preparedStmt.execute();
              
              // close
              // preparedStmt.close();
              con.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }finally {
		        try {
		            if (con != null)
		                con.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }		
	}

	/**
	 * Bestehenden Kurs loeschen  
	 * @param id
	 */	
	@Override
	public void deleteKurs(int id) {
  		Connection con = null;
		
		try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // org.mariadb.jdbc.Driver
            con = DriverManager.getConnection(DBAdresse, username, password);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	    String sql = "DELETE FROM kurs WHERE kursId='" + id + "'";

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
        return;
	}	
	
	/**
	 * Bestehenden Kurs updaten bzw. aendern 
	 * @param kurs
	 */
	@Override
	public void updateKurs(Kurs kurs) {
		
		 SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy"); 

		 String sql;  // update ohne datum update
		 sql = "UPDATE Kurs SET kursname=?, kurslevel=?, preis=?, betreuerId=?, maxAnzahl=?, immobilie=? WHERE kursid = ?";
		
			Connection con = null;
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver"); // org.mariadb.jdbc.Driver
	            con = DriverManager.getConnection(DBAdresse, username, password);

	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        
		    PreparedStatement preparedStmt = null;
		    try {
		        preparedStmt = con.prepareStatement(sql);
		        preparedStmt.setString(1, kurs.getKursname());
		        preparedStmt.setString(2, kurs.getLevel());
		        // preparedStmt.setString(3, sdf.format(kurs.getBeginn().getTime()));
		        // preparedStmt.setString(4, sdf.format(kurs.getBeginn().getTime()));
		        preparedStmt.setDouble(3, kurs.getPreis());
		        preparedStmt.setInt(4, kurs.getBetreuerId());
		        preparedStmt.setInt(5, kurs.getMaxAnzahl());
		        preparedStmt.setInt(6, kurs.getImmobilie());
		        preparedStmt.setInt(7, kurs.getId());
		        
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
	
	/**
	 * Teilnehmer (anhand der kundenId) zu einem bestimmten Kurs hinzufuegen
	 * @param kundenId
	 * @param kursId
	 */
	@Override
	public void addTeilnehmerToKurs(int kundenId, int kursId){
		Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // org.mariadb.jdbc.Driver
            con = DriverManager.getConnection(DBAdresse, username, password);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
        	
              // insert statement
              String query = " insert into Teilnehmer (kundenId,kursId)"
                + " values (?, ?)";

              // create insert preparedstatement
              PreparedStatement preparedStmt = con.prepareStatement(query);
              preparedStmt.setInt(1, kundenId); 
              preparedStmt.setInt(2, kursId);

              // execute the preparedstatement
              preparedStmt.execute();
              
              // close
              con.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }finally {
		        try {
		            if (con != null)
		                con.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }				
		
	}
	
	/**
	 * Teilnehmer (anhand der kundenId) von einem bestimmten Kurs loeschen
	 * @param kundenId
	 * @param kursId
	 */
	@Override
	public void deleteTeilnehmerFromKurs(int kundenId, int kursId){
  		Connection con = null;
		
		try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // org.mariadb.jdbc.Driver
            con = DriverManager.getConnection(DBAdresse, username, password);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	    String sql = "DELETE FROM teilnehmer WHERE kundenId='" + kundenId + "' AND kursId='" + kursId + "'";

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
        return;			
	}
	
	
	/**
	 * Teilnehmerliste (Liste von kundeIds) fuer einen Kurs anhand kursId suchen
	 * @param kursId
	 * @return
	 */
	public ArrayList<Integer> getTeilnehmerListe(int kursId) {
		Connection con = null;
    	ArrayList<Integer> teilnehmerliste=new ArrayList<Integer>();
		 
		try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // org.mariadb.jdbc.Driver
            con = DriverManager.getConnection(DBAdresse, username, password);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

	    String sql;  
	    sql = "SELECT kundenId FROM teilnehmer WHERE kursId='"+kursId+"'";
	        PreparedStatement prest = null;
	        try {
	            prest = con.prepareStatement(sql);
	            ResultSet rs = prest.executeQuery();

	            while (rs.next()) {
 	            	// TEST CHECK DATA: System.out.println(rs.getInt(1));
	                    
	                int kundenId=rs.getInt(1);
	                // TEST CHECK DATA: System.out.println(kundenId);
	                teilnehmerliste.add(kundenId);
	            }
	        }catch (SQLException e) {
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
			return teilnehmerliste;
	}
  
	
	/**
	 * Kursliste (Liste von kursIds) fuer einen Teilnehmer (Kunde) anhand kundenId suchen
	 * @param kundenId
	 * @return
	 */
	@Override
	public ArrayList<Integer> getKurslisteForTeilnehmer (int kundenId) {
		Connection con = null;
    	ArrayList<Integer> kursliste=new ArrayList<Integer>();
		 
		try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // org.mariadb.jdbc.Driver
            con = DriverManager.getConnection(DBAdresse, username, password);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

	    String sql;  
	    sql = "SELECT kursId FROM teilnehmer WHERE kundenId='"+kundenId+"'";
	        PreparedStatement prest = null;
	        try {
	            prest = con.prepareStatement(sql);
	            ResultSet rs = prest.executeQuery();

	            while (rs.next()) {
 	            	// TEST CHECK DATA: System.out.println(rs.getInt(1));
	                    
	                int kursId=rs.getInt(1);
	                // TEST CHECK DATA: System.out.println(kundenId);
	                kursliste.add(kursId);
	            }
	        }catch (SQLException e) {
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
			return kursliste;
	}
	
	
	/**
	 * main Funktion fuer Tests
	 * @param args
	 */ 
	public static void main(String[] args){
		DatabaseKursDAO dao = new DatabaseKursDAO();
		// dao.select();
		// dao.insert();
		// dao.update();
		// Kurs kursById = dao.getKursById(1);
		// dao.deleteKursById(5);
		/*
		int kursId=2;
		ArrayList<Integer> teilnehmerliste=dao.getTeilnehmerListe(kursId);
		
		for(int i=0;i<teilnehmerliste.size();i++){
			System.out.println(teilnehmerliste.get(i));
		}
		*/
		
		/*		 
		int kundenId=1;
		ArrayList<Integer> kursliste=dao.getKurslisteForTeilnehmer(kundenId);
		
		for(int i=0;i<kursliste.size();i++){
			System.out.println(kursliste.get(i));
		}
		*/
		
	}
	
}
