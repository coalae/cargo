package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Kunde;

public class DatabaseKundenDAO {
	private String DBAdresse = "jdbc:mariadb://localhost:3306/cargo";
	private String username = "root";
	private String password = "coalacoala1";
	
	/**
	 * SELECT kundenliste
	 * @return ArrayList
	 */
	public ArrayList<Kunde> select(){
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
        return kundenList;
	}
	
	
	/**
	 * INSERT Kunde
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
              String query = " insert into Kunde (vorname, nachname, iban, bic, username, passw, active)"
                + " values (?, ?, ?, ?, ?, ?, ?)";
              // create insert preparedstatement
              PreparedStatement preparedStmt = con.prepareStatement(query);
              preparedStmt.setString (1, "Lisa");
              preparedStmt.setString (2, "Maier");
              preparedStmt.setInt (3, 111111);
              preparedStmt.setString(4, "RLNWKELA");
              preparedStmt.setString(5, "lisam");
              preparedStmt.setString(6, "lisa"); 
              preparedStmt.setBoolean(7, true); 

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
	 * UPDATE Kunde (by Id)
	 */
	public void update() {
	
	 // TESTDATEN:	 
	 Kunde kunde1 = new Kunde(3, "VornameUpdate", "NachnameUpdate", 11111, "BICupdate", "usernameUpdate", "pwUpdate", true);
		
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
	        preparedStmt.setString(1, kunde1.getVorname());
	        preparedStmt.setString(2, kunde1.getNachname());
	        preparedStmt.setInt(3, kunde1.getIban());
	        preparedStmt.setString(4, kunde1.getBic());
	        preparedStmt.setString(5, kunde1.getUsername());
	        preparedStmt.setString(6, kunde1.getPassword());
	        preparedStmt.setBoolean(7, kunde1.isActive());
	        preparedStmt.setInt(8, kunde1.getId());
	       
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
     * Get Kunde by Id (SELECT Kunde ... WHERE ID ...)
     * @param id
     * @return
     */
    public Kunde getKundeById(int id){
  
        		Connection con = null;
        		Kunde kunde = null;
        		try {
                    Class.forName("org.mariadb.jdbc.Driver");
                    con = DriverManager.getConnection(DBAdresse, username, password);

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

        	    String sql;  
        	    sql = "SELECT kundenId, vorname, nachname, iban, bic, username, passw, active FROM Kunde" +
        	    		" WHERE kundenId='" + id + "'";
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
        	        return kunde;
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
     * DELETE Kunde
     * @param id
     */
    public void deleteKundeById(int id) {
    	
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
        return;
	}

    

    
    
	/**
	 * main Funktion fuer Tests
	 * @param args
	 */
	public static void main(String[] args){
		DatabaseKundenDAO dao = new DatabaseKundenDAO();
		 dao.select();
		// dao.insert();
		// dao.update();
		// Kunde kundeById = dao.getKundeById(1);
		// dao.deleteKundeById(3);
	}

}
