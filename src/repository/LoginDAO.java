package repository;

public interface LoginDAO {
	String check(String name, String pwd);
	String spez(String name, String pwd);
}
