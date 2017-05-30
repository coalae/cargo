package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.text.StyledEditorKit.BoldAction;
/**
 * 
 * @author Gregor Langner
 *Diese Klasse eralisiert den Login
 */
public class DatabaseLoginDAO implements LoginDAO{
	private static String checkKunde="Select * from Kunde where username=? and passw=?";
	private static String checkMitarbeiter="Select * from Mitarbeiter where username= ? and passw= ?";
	private static String spezi="Select * from Mitarbeiter where username= ? and passw= ?";
	DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
	private Connection connection;
	/**
	 * {@code DatabaseLoginDAO}
	 * Hier wird eine connection zur Datenbank erstellt
	 */
	public DatabaseLoginDAO(){
		try {
			connection = DatabaseHandler.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
/**
 * {@code String check (String name,String pw)}
 * @param name  ist der username der geprüft werden muss
 * @param pw das eingegebene passwort dazu 
 */
	@Override
	public String check(String name, String pwd) {
		String check=null;
		try{
		PreparedStatement ein = connection.prepareStatement(checkKunde);
		ein.setString(1, name);
		ein.setString(2, pwd);
		ResultSet rs= ein.executeQuery();
		boolean test=(rs.next());
			if(test==true){
				System.out.println("Kunde");
				return "Kunde";
			}
		PreparedStatement ein1 = connection.prepareStatement(checkMitarbeiter);
		ein1.setString(1, name);
		ein1.setString(2, pwd);
		ResultSet rs1= ein1.executeQuery();
		test=(rs1.next());
			if(test==true){
				System.out.println("Mitarbeiter");
				return "Mitarbeiter";
			}
			System.out.println(check);
		}
		catch(Exception e){System.out.println("Fehler" +e.getMessage());} //noch leer
		return check;
	}
/**
 * Funktion die Prüfen soll welche Spezialisierung der Mitarbeiter hat
 * {@code String spez (String name,String pwd}
 * @param String username des Mitarbeiters
 * @param String pwd eingegebene Passwort
 * @return als return wird die Spezialisierung geben wenn nichts gefunden wurde null
 */
	@Override
	public String spez(String name, String pwd) {
		try{
			PreparedStatement aus=connection.prepareStatement(spezi);
			aus.setString(1, name);
			aus.setString(2, pwd);
			ResultSet rs= aus.executeQuery();

			boolean test=rs.next();
			if(test==true){
				return rs.getString(5);
			}
		}catch(Exception e){}
		return null;
	}
}
