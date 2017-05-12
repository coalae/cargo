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

import model.Kurs;

public class DatabaseKursDAO {
	
	private static final String all = "Select * from Kurs";
	private static final String delID = "DELTE from Kurs where kursid=?";
	private static final String upName = "UPDATE Kurs SET kursname =? where kursid =?";
	private static final String uplevel = "UPDATE Kurs SET kurslevel =? where kursid =?";
	private static final String upEnde = "UPDATE Kurs SET ende =? where kursid =?";
	private static final String upBeginn ="UPDATE Kurs SET beginn =? where kursid =?";
	private static final String upPreis ="UPDATE Kurs SET preis = ? where krusid =?";
	private static final String ausgabe ="Select * from Kurs where kursid =?";
	private static final String add = "INSERT INTO Kurs (kursid,nursname,kurslevel,"
	+"beginn,ende,preis,betreuerid,maxAnazahl,immobilie) VALUES (?,?,?,?,?,?,?,?,?)";
	
	private String DBAdresse = "jdbc:mariadb://localhost:3306/cargo";
	private String username = "root";
	private String password = "coalacoala1";
	
	/**
	 * SELECT kursliste
	 * @return ArrayList
	 */
	public ArrayList<Kurs> select(){
		Connection con = null;
        ArrayList<Kurs> kursList = new ArrayList<Kurs>();
        try {
            Class.forName("org.mariadb.jdbc.Driver");
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
            	System.out.println(rs.getInt(1));
            	System.out.println(rs.getString(2));
            	System.out.println(rs.getString(3));
            	
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
        return kursList;
	}
	
	
	/**
	 * INSERT kurs
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
              String query = " insert into Kurs (kursname, kurslevel, beginn, ende, preis, betreuerId, maxAnzahl, immobilie)"
                + " values (?, ?, ?, ?, ?, ?, ?, ?)";

              // create insert preparedstatement
              PreparedStatement preparedStmt = con.prepareStatement(query);
              preparedStmt.setString (1, "PWK-Kurs 3");
              preparedStmt.setString (2, "Fortgeschritten");
              preparedStmt.setString (3, "03.06.2017");
              preparedStmt.setString(4, "04.07.2017");
              preparedStmt.setDouble(5, 197);
              preparedStmt.setInt(6, 2); 
              preparedStmt.setInt(7, 11);
              preparedStmt.setInt(8, 1);

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
	 * UPDATE kurs (by Id)
	 */
	public void update() {
	
	 // TESTDATEN:
		GregorianCalendar beginncal = new GregorianCalendar();
	    beginncal.setLenient(false);
	    beginncal.set(GregorianCalendar.YEAR, 2017);
	    beginncal.set(GregorianCalendar.MONTH, 06);
	    beginncal.set(GregorianCalendar.DATE, 3);	
	    
	    GregorianCalendar endecal = new GregorianCalendar();
	    endecal.setLenient(false);
	    endecal.set(GregorianCalendar.YEAR, 2017);
	    endecal.set(GregorianCalendar.MONTH, 06);
	    endecal.set(GregorianCalendar.DATE, 12);
	    
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy"); 

	    ArrayList<Integer> teilnehmerliste = new ArrayList<Integer>();
	    teilnehmerliste = null;
		
		Kurs kurs1 = new Kurs(2, "NameUpdate", "LevelUpdate", beginncal, endecal, 30, 1, 5, 1, teilnehmerliste);
		
	 String sql; 
	 sql = "UPDATE Kurs SET kursname=?, kurslevel=?, beginn=?, ende=?, preis=?, betreuerId=?, maxAnzahl=?, immobilie=? WHERE kursid = ?";
	
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
	        preparedStmt.setString(1, kurs1.getKursname());
	        preparedStmt.setString(2, kurs1.getLevel());
	        preparedStmt.setString(3, sdf.format(kurs1.getBeginn().getTime()));
	        preparedStmt.setString(4, sdf.format(kurs1.getEnde().getTime()));
	        preparedStmt.setDouble(5, kurs1.getPreis());
	        preparedStmt.setInt(6, kurs1.getBetreuerId());
	        preparedStmt.setInt(7, kurs1.getMaxAnzahl());
	        preparedStmt.setInt(8, kurs1.getImmobilie());
	        preparedStmt.setInt(9, kurs1.getId());
	       
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
     * Get Kurs by Id (SELECT kurs ... WHERE ID ...)
     * @param id
     * @return
     */
    public Kurs getKursById(int id){
  
        		Connection con = null;
        		Kurs kurs = null;
        		try {
                    Class.forName("org.mariadb.jdbc.Driver");
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
        	            	System.out.println(rs.getInt(1));
        	            	System.out.println(rs.getString(2));
        	            	System.out.println(rs.getString(3));
        	            	
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
        	        return kurs;
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
     * DELETE kurs
     * @param id
     */
    public void deleteKursById(int id) {
    	
   		Connection con = null;
		
		try {
            Class.forName("org.mariadb.jdbc.Driver");
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
	 * main Funktion fuer Tests
	 * @param args
	 */
	public static void main(String[] args){
		DatabaseKursDAO dao = new DatabaseKursDAO();
		// dao.select();
		// dao.insert();
		// dao.update();
		// Kurs kursById = dao.getKursById(1);
		 dao.deleteKursById(5);
	}

}
