package repository;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import model.Mitarbeiter;

public class DatabaseMitarbeiterDAO {
	private String DBAdresse = "jdbc:mariadb://localhost:3306/cargo";
	private String username = "root";
	private String password = "coalacoala1";
	
	/**
	 * SELECT kundenliste
	 * @return ArrayList
	 */
	public ArrayList<Mitarbeiter> select(){
		Connection con = null;
        ArrayList<Mitarbeiter> mitarbeiterList = new ArrayList<Mitarbeiter>();
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            con = DriverManager.getConnection(DBAdresse, username, password);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql;     
        sql = "SELECT mitarbeiterId, vorname, nachname, svnr, spezialisierung, mitarbeitertyp, geburtsdatum, username, passw, active FROM Mitarbeiter";
        PreparedStatement prest = null;
        try {

            prest = con.prepareStatement(sql);
            ResultSet rs = prest.executeQuery();
            
            while (rs.next()) {
            	System.out.println(rs.getInt(1));
            	System.out.println(rs.getString(2));
            	System.out.println(rs.getString(3));
            	
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
        return mitarbeiterList;
	}
	
	
	/**
	 * INSERT Mitarbeiter
	 */
	public void insert(){
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

        /*
        PreparedStatement preparedStmt = null;
        */
        try {
        	
              // insert statement     
              String query = " insert into Mitarbeiter (vorname, nachname, svnr, spezialisierung, mitarbeitertyp, geburtsdatum," + 
			" username, passw, active) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
              // create insert preparedstatement
              PreparedStatement preparedStmt = con.prepareStatement(query);
              preparedStmt.setString (1, "Lukas");
              preparedStmt.setString (2, "Leier");
              preparedStmt.setInt (3, 111);
              preparedStmt.setString(4, "PKWKurs");
              preparedStmt.setInt(5, 1);
              preparedStmt.setString(6,"01.01.1991"); 
              preparedStmt.setString(7, "lukasl");
              preparedStmt.setString(8, "lukas");
              preparedStmt.setBoolean(9, true); 

              // execute the preparedstatement
              preparedStmt.execute();
              
              con.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }finally {
		        try {
		            
		        	/* if (preparedStmt != null)
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
	 * UPDATE Mitarbeiter (by Id)
	 */
	public void update() {
	
	 // TESTDATEN:	
			GregorianCalendar geburtsdatum = new GregorianCalendar();
			geburtsdatum.setLenient(false);
			geburtsdatum.set(GregorianCalendar.YEAR, 1990);
			geburtsdatum.set(GregorianCalendar.MONTH, 06);
			geburtsdatum.set(GregorianCalendar.DATE, 6);	
		    
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy"); 

	 Mitarbeiter ma1 = new Mitarbeiter(1, "VornameUpdate", "NachnameUpdate", 112, "SpezUpdate", 1, geburtsdatum, "UsernameUpdate", "PWUpdate", true);
		
	 String sql; 
	 sql = "UPDATE Mitarbeiter SET vorname=?, nachname=?, svnr=?, spezialisierung=?, mitarbeitertyp=?, geburtsdatum=?, username=?, passw=?, active=? WHERE mitarbeiterId=?";
	
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
	        preparedStmt.setString(1, ma1.getVorname());
	        preparedStmt.setString(2, ma1.getNachname());
	        preparedStmt.setInt(3, ma1.getSvnr());
	        preparedStmt.setString(4, ma1.getSpezialisierung());
	        preparedStmt.setInt(5, ma1.getMitarbeitertyp());
	        preparedStmt.setString(6, sdf.format(ma1.getGeburtsdatum().getTime()));
	        preparedStmt.setString(7, ma1.getUsername());
	        preparedStmt.setString(8, ma1.getPassword());
	        preparedStmt.setBoolean(9, ma1.isActive());
	        preparedStmt.setInt(10, ma1.getId());
	       
	        /*
	        if (params[0].getHeight() != null)
	            preparedStmt.setInt(8, params[0].getHeight());
	        else
	            preparedStmt.setNull(8, Types.INTEGER);
	        if (params[0].getWeight() != null)
	            preparedStmt.setInt(9, params[0].getWeight());
	        else
	            preparedStmt.setNull(9, Types.INTEGER);
	        if (params[0].getTargetweight() != null)
	            preparedStmt.setInt(10, params[0].getTargetweight());
	        else
	            preparedStmt.setNull(10, Types.INTEGER);
	        if (params[0].getDailyCaloryIntakeAllowance() != null)
	            preparedStmt.setInt(11, params[0].getDailyCaloryIntakeAllowance());
	        else
	            preparedStmt.setNull(11, Types.INTEGER);
	        if (params[0].getCompetitionScore() != null)
	            preparedStmt.setInt(12, params[0].getCompetitionScore());
	        else
	            preparedStmt.setNull(12, Types.INTEGER);
	
	        preparedStmt.setBoolean(13, params[0].isActive());
	        preparedStmt.setString(14, params[0].getUsername());
	        */
	        
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
	
	 /**
     * Get Mitarbeiter by Id (SELECT Mitarbeiter ... WHERE ID ...)
     * @param id
     * @return
     */
    public Mitarbeiter getMitarbeiterById(int id){
  
        		Connection con = null;
        		Mitarbeiter ma1 = null;
        		try {
                    Class.forName("org.mariadb.jdbc.Driver");
                    con = DriverManager.getConnection(DBAdresse, username, password);

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

        	    String sql;  
        	    sql = "SELECT mitarbeiterId, vorname, nachname, svnr, spezialisierung, mitarbeitertyp, geburtsdatum, username, passw, active FROM Mitarbeiter" +
        	    		" WHERE mitarbeiterId='" + id + "'";
        	        PreparedStatement prest = null;
        	        try {

        	            prest = con.prepareStatement(sql);
        	            ResultSet rs = prest.executeQuery();
        	            
        	            while (rs.next()) {
        	            	System.out.println(rs.getInt(1));
        	            	System.out.println(rs.getString(2));
        	            	System.out.println(rs.getString(3));
        	            	
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
        	        return ma1;
        		}
    
    
    			/*
    			PreparedStatement prest = null;
                try {

                    prest = con.prepareStatement(sql);
                    ResultSet rs = prest.executeQuery();
                    if (rs.isBeforeFirst())
                        user = new User();
                    while (rs.next()) {
                        user.setId(rs.getInt(1));
                        user.setUsername(rs.getString(2));
                        user.setPassword(rs.getString(3));
                        user.setEmail(rs.getString(4));
                        user.setGoal(rs.getString(5));
                        user.setFirstname(rs.getString(6));
                        user.setLastname(rs.getString(7));
                        user.setGender(rs.getString(8));
                        user.setBirthday(rs.getString(9));
                        user.setHeight(rs.getInt(10));
                        user.setWeight(rs.getInt(11));
                        user.setTargetweight(rs.getInt(12));
                        user.setDailyCaloryIntakeAllowance(rs.getInt(13));
                        user.setCompetitionScore(rs.getInt(14));
                        user.setActive(rs.getBoolean(15));
                    }
                } catch (SQLException e) {
                    //e.printStackTrace();
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
                return user;
            }


        }
        GetUserById getUserByUsername = new GetUserById();
        User user = null;
        try {
            user = getUserByUsername.execute(id).get();
            //  Log.e("Username", "\n" + user.getUsername() +"\n"+user.getPassword());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return user;
    }
    */
	
    /**
     * DELETE Mitarbeiter
     * @param id
     */
    public void deleteMitarbeiterById(int id) {
    	
   		Connection con = null;
		
		try {
            Class.forName("org.mariadb.jdbc.Driver");
            con = DriverManager.getConnection(DBAdresse, username, password);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	    String sql = "DELETE FROM Mitarbeiter WHERE mitarbeiterId='" + id + "'";

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
	 * main Funktion fuer Tests
	 * @param args
	 */
	public static void main(String[] args){
		DatabaseMitarbeiterDAO dao = new DatabaseMitarbeiterDAO();
		//dao.select();
		// dao.insert();
		// dao.update();
		// Mitarbeiter mitarbeiterById = dao.getMitarbeiterById(2);
		 dao.deleteMitarbeiterById(3);
	}

}
