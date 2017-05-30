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
import java.util.*;


import model.Mentoring;

public class DatabaseMentoringDAO implements MentoringDAO {
	
	DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
	Connection connection;
	
	public DatabaseMentoringDAO() {
		try{
			connection= DatabaseHandler.getConnection();
		}catch(SQLException e){e.printStackTrace();}
	}
	
	/*
	private static final String all = "Select * from Mentoringbeziehung";
	private static final String add = "INSERT INTO Mentoringbeziehung (mentoringid,mentorid"
	+"menteeid,thema,jahr) VALUES (?,?,?,?,?)";
	private static final String del = "DELETE from Mentoringbeziehung where mentoringid =?";
	private static final String upThema = "UPDATE from Mentoringbeziehung SET thema =? where mentoringid =?";
	private static final String upMentor = "UPDATE from Mentoringbeziehung SET mentorid =? where mentoringid =?";
	private static final String upMentee = "UPDATE from Mentoringbeziehung SET menteeid =? where mentoringid =?";
	private static final String ausMentor = "Select * from Mentoringbeziehung where mentorid=?";
	private static final String ausID= "Select * from Mentoringbeziehung where mentoringid = ?";
	private static final String ausMentee = "Select * from Mentoringbeziehung where menteeid=?";
	*/
	
	

	@Override
	public ArrayList<Mentoring> getMentoringListe() {
		
        ArrayList<Mentoring> mentoringList = new ArrayList<Mentoring>();
        
        String sql;   
        sql = "SELECT mentoringid, mentorid, menteeid, thema, jahr FROM Mentoringbeziehung";
        
        try {

            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            
            while (rs.next()) {
            /*	DB CHECK
                System.out.println(rs.getInt(1));
            	System.out.println(rs.getString(2));
            	System.out.println(rs.getString(3));
            */
             int mentoringId=rs.getInt(1);
             int mentorId=rs.getInt(2);
             int menteeId=rs.getInt(3);
             String thema=rs.getString(4);
             String jahr=rs.getString(5);
                    
    		
             // GregorianCalendar fuer Jahr setzen
             SimpleDateFormat sdf = new SimpleDateFormat("YYYY"); 

     	     Date mentoringjahr = new Date();
     	     mentoringjahr = sdf.parse(jahr);
    	     
    	  
     	     Mentoring mentoring = new Mentoring(mentoringId, mentorId, menteeId, thema, mentoringjahr);
     	    
             mentoringList.add(mentoring);
             
             
             
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
			e.printStackTrace();
		} 
        return mentoringList;
        
     }	
	
	@Override
	public Mentoring getMentoringById(int id) {
		
		Mentoring mentoring = null;
		

	    String sql;
	    sql = "SELECT * from Mentoringbeziehung WHERE mentoringid='" + id + "'";
	        
	        try {

	           PreparedStatement stm = connection.prepareStatement(sql);
	            ResultSet rs = stm.executeQuery();
	            
	            while (rs.next()) {
	            	 int mentoringId=rs.getInt(1);
	                 int mentorId=rs.getInt(2);
	                 int menteeId=rs.getInt(3);
	                 String thema=rs.getString(4);
	                 String jahr=rs.getString(5);
	                                		
	                 // Date fuer Jahr setzen
	                 SimpleDateFormat sdf = new SimpleDateFormat("YYYY"); 

	         	     Date mentoringjahr = new Date();
	         	     mentoringjahr = sdf.parse(jahr);
	             
	             mentoring=new Mentoring(mentoringId, mentorId, menteeId, thema, mentoringjahr); 
	             stm.close();
	             
	            }
	        } catch (SQLException e) {
	        } catch (ParseException e) {
				e.printStackTrace();
			} 
	        return mentoring;
		}

	
	@Override
	public Mentoring getMentoringByMenteeId(int id) {
		
		Mentoring mentoring = null;
		/*try {
            Class.forName("org.mariadb.jdbc.Driver");
            con = DriverManager.getConnection(DBAdresse, username, password);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
*/
	    String sql;
	    sql = "SELECT * from Mentoringbeziehung WHERE menteeid='" + id + "'";
	        
	        try {

	            PreparedStatement stm = connection.prepareStatement(sql);
	            ResultSet rs = stm.executeQuery();
	            
	            while (rs.next()) {
	            	 int mentoringId=rs.getInt(1);
	                 int mentorId=rs.getInt(2);
	                 int menteeId=rs.getInt(3);
	                 String thema=rs.getString(4);
	                 String jahr=rs.getString(5);
	                                		
	                 // Date fuer Jahr setzen
	                 SimpleDateFormat sdf = new SimpleDateFormat("YYYY"); 

	         	     Date mentoringjahr = new Date();
	         	     mentoringjahr = sdf.parse(jahr);
	             
	             mentoring=new Mentoring(mentoringId, mentorId, menteeId, thema, mentoringjahr); 
	             stm.close();
	             
	            }
	        } catch (SQLException e) {
	        } catch (ParseException e) {
				e.printStackTrace();
			}
	        return mentoring;
		}

	
	
	@Override
	public void addMentor(int mentorId) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void addMentee(int menteeId) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void addMentoring(Mentoring mentoring) {


			
        try {
        	
              // insert statement
              String query = "INSERT INTO Mentoringbeziehung (mentoringid,mentorid, menteeid,thema,jahr) VALUES (?,?,?,?,?)";
	
              // create insert preparedstatement
              PreparedStatement stm = connection.prepareStatement(query);
              SimpleDateFormat sdf = new SimpleDateFormat("YYYY"); 
              stm.setInt (1, 0);
              stm.setInt (2, mentoring.getMentorId());
              stm.setInt (3, mentoring.getMenteeId());
              stm.setString(4, mentoring.getThema());
              stm.setString (5, sdf.format(mentoring.getBeginnJahr()));
            
              // execute the preparedstatement
              stm.execute();
              
              // close
              stm.close();
             
		  } catch (SQLException e) {
		        e.printStackTrace();
		  }
		    
	}
		

	@Override
	public void deleteMentoring(int id) {
		// TODO Auto-generated method stub
		
		
		
		/*try {
            Class.forName("org.mariadb.jdbc.Driver");
            con = DriverManager.getConnection(DBAdresse, username, password);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		*/
	    String sql = "DELETE from Mentoringbeziehung WHERE mentoringid ='" + id + "'"; 

        try {
        	PreparedStatement stmt = connection.prepareStatement(sql);        	         	 
        	stmt.execute(sql);            
        } catch (SQLException e) {
            e.printStackTrace();
        } 
            
        return;
	}	
	
    
		
	@Override
	public void updateMentoring(Mentoring mentoring) {
		 String sql = "UPDATE Mentoringbeziehung SET mentorid=?, menteeid=?, thema =?, jahr=? where mentoringid =?";
		
			
	        /*try {
	            Class.forName("org.mariadb.jdbc.Driver");
	            con = DriverManager.getConnection(DBAdresse, username, password);

	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }*/
	        
		    PreparedStatement preparedStmt = null;
		    try {
		    	SimpleDateFormat sdf = new SimpleDateFormat("YYYY"); 
		        preparedStmt = connection.prepareStatement(sql);
		        preparedStmt.setInt(1, mentoring.getMentorId());
		        preparedStmt.setInt(2, mentoring.getMenteeId());
		        preparedStmt.setString(3, mentoring.getThema());
		        preparedStmt.setString(4, sdf.format(mentoring.getBeginnJahr()));
		        preparedStmt.setInt(5, mentoring.getMentoringId());
		        
		      	        
		        preparedStmt.executeUpdate();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		
		    
		    return;		
	}    
		
	
}
