package logic;

import repository.DatabaseLoginDAO;
import repository.LoginDAO;

public class LoginMgmt {
	LoginDAO lDAO=new DatabaseLoginDAO();
	
	public LoginMgmt() {
	}
	public String check(String name,String pwd){
		String test=lDAO.check(name, pwd);
		return test;
	}
	public String spezi(String name, String pwd){
		return lDAO.spez(name, pwd);
	}
}
