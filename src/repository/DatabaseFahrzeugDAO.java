package repository;

public class DatabaseFahrzeugDAO {
	private static final String auslesen = "Select * from Fahrzeug";
	private static final String addKFZ = "Insert into Fahrzeug (fahrzeugid,marke,modell,baujahr,farbe) values (?,?,?,?,?)";
	private static final String delKFZ = "Delete from Fahrzeug wehre fahrzeugid= ?";
	private static final String upMarke = "UPDATE Fahrzeug SET marke = ? where fahrzeugid=?";
	private static final String upModell = "UPDATE Fahrzeug SET modell = ? where fahrzeugid=?";
	private static final String upJahr = "UPDATE Fahrzeug SET baujahr = ? where fahrzeugid=?";
	private static final String upFarbe = "UPDATE Fahrzeug SET farbe = ? where fahrzeugid=?";
	private static final String upID = "UPDATE Fahrzeug SET fahrzeugid = ? where fahrzeugid=?";
}
