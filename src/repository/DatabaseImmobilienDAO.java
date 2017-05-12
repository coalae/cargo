package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Immobilie;

public class DatabaseImmobilienDAO {
	private static final String delImmo = "Delete from Immobilie wehre immobilienid = ?";
	private static final String addImmo = "INSERT INTO Immobilie (immobilienid,typ,immobielienname) values (?,?,?)";
	private static final String uptyp = "UPDATE Fahrzeug SET typ = ? where immobielienid=?";
	private static final String upname = "UPDATE Fahrzeug SET immobilienname = ? where immobielienid=?";
	private static final String upid = "UPDATE Fahrzeug SET immobielienid = ? where immobielienid=?";
	private static final String all = "Select * from Immobilie";
	private static final String aus1 = "Select * from Immobilie where typ = ?";
	private static final String aus2 = "Select * from Immobilie where immobielienname = ? ";
	private static final String aus3 = "Select * from Immobilie where immobielienid = ? ";
	

		private String DBAdresse = "jdbc:mariadb://localhost:3306/cargo";
		private String username = "root";
		private String password = "coalacoala1";
		
		/**
		 * SELECT immobilienliste
		 * @return ArrayList
		 */
		public ArrayList<Immobilie> select(){
			Connection con = null;
	        ArrayList<Immobilie> immoList = new ArrayList<Immobilie>();
	        try {
	            Class.forName("org.mariadb.jdbc.Driver");
	            con = DriverManager.getConnection(DBAdresse, username, password);

	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        String sql; 
	        sql = "SELECT immobilienId, typ, immobilienname FROM Immobilie";
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
	        return immoList;
		}
		
		
		/**
		 * INSERT Immobilie
		 */
		public void insert(){
			Connection con = null;
	        //Immobilie immo = null;
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
	              String query = " insert into Immobilie (typ, immobilienname)"
	                + " values (?, ?)";

	              // create insert preparedstatement
	              PreparedStatement preparedStmt = con.prepareStatement(query);
	              preparedStmt.setInt (1, 1);
	              preparedStmt.setString (2, "Gebaeude4");

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
		 * UPDATE Immobilie (by Id)
		 */
		public void update() {
		
		 // TESTDATEN:
			Immobilie immo1 = new Immobilie(1, 1, "NameUpdate");
			
		 String sql; 
		 sql = "UPDATE Immobilie SET typ=?, immobilienname=? WHERE immobilienid = ?";
		
			Connection con = null;
	        //Immobilie immo = null;
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
		        preparedStmt.setInt(1, immo1.getTyp());
		        preparedStmt.setString(2, immo1.getImmobilienname());
		        preparedStmt.setInt(3, immo1.getId());
		       
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
	     * Get Immobilie by Id (SELECT immobilie ... WHERE ID ...)
	     * @param id
	     * @return
	     */
	    public Immobilie getImmobilieById(int id){
	  
	        		Connection con = null;
	        		Immobilie immo = null;
	        		try {
	                    Class.forName("org.mariadb.jdbc.Driver");
	                    con = DriverManager.getConnection(DBAdresse, username, password);

	                } catch (ClassNotFoundException e) {
	                    e.printStackTrace();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }

	        	    String sql;
	        	    sql = "SELECT immobilienId, typ, immobilienname FROM Immobilie" +
	        	    		" WHERE immobilienId='" + id + "'";
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
	        	        return immo;
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
	    public void deleteImmobilieById(int id) {
	    	
	   		Connection con = null;
			
			try {
	            Class.forName("org.mariadb.jdbc.Driver");
	            con = DriverManager.getConnection(DBAdresse, username, password);

	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
			
		    String sql = "DELETE FROM immobilie WHERE immobilienId='" + id + "'";

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
			DatabaseImmobilienDAO dao = new DatabaseImmobilienDAO();
			 dao.select();
			// dao.insert();
			// dao.update();
			// Immobilie ImmoById = dao.getImmobilieById(1);
			// dao.deleteImmobilieById(4);
		}

	}

