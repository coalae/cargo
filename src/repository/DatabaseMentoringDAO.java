package repository;

public class DatabaseMentoringDAO {
	private static final String all = "Select * from Mentoringbeziehung";
	private static final String add = "INSERT INTO Mentoringbeziehung (mentoringid,mentorid"
	+"menteeid,thema,jahr) VALUES (?,?,?,?,?)";
	private static final String del = "DELETE from Mentoringbeziehung where mentoringid =?";
	private static final String upThema = "UPDATE from Mentoringbeziehung SET thema =? where mentoringid =?";
	private static final String upMentor = "UPDATE from Mentoringbeziehung SET mentorid =? where mentoringid =?";
	private static final String upMentee = "UPDATE from Mentoringbeziehung SET menteeid =? where mentoringid =?";
	private static final String ausMentor = "Select * from Mentoringbeziehung where mentorid=?";
	private static final String ausID= "Select * from Mentoringbeziehung where mentoringid = ?";
	private stativ final String ausMentee = "Select * from Mentoringbeziehung where menteeid=?";
}
