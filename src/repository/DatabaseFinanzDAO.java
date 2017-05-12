package repository;

public class DatabaseFinanzDAO {
	private static final String all = "Select * from Transaktion";
	private static final String delID = "Delete from Transaktion where transaktionsid = ?";
	private static final String upBetrag = "UPDATE Transaktion SET betrag = ? where transaktionsid = ?";
	private static final String ausgabe = "Select * from Transaktion where senderid = ?";
	private static final String addTrans = "INSERT INTO Transaktion (transaktionsid,betreff,senderid,betrag,transaktionsdatum) VALUES (?,?,?,?,?)";
}
