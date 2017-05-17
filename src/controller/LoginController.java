package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logic.LoginMgmt;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginMgmt lmg= new LoginMgmt();
    /**
     * Default constructor. 
     */
    public LoginController() {
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("pageName").equalsIgnoreCase("login")){
			HttpSession session = request.getSession(true);
			String name=request.getParameter("name");
			String pwd=request.getParameter("pwd");
			String value=lmg.check(name, pwd);
			if(value==null){request.getRequestDispatcher("index.jsp").include(request, response);}
			if(value.equalsIgnoreCase("Mitarbeiter")){
				//Weiter zur MitarbeiterSeite bzw zum Servlet
				String spez=lmg.spezi(name, pwd);
				System.out.println(spez+" hallo");
				if(spez=="Admin"){
					session.setAttribute("marker", spez);
				}
				else if(spez.equalsIgnoreCase("It")){
					System.out.println("Bin hier");
					session.setAttribute("marker", spez);
					request.getRequestDispatcher("VerwaltungAuswahl.jsp").include(request, response);
				}
				else if(spez=="Mitarbeiter"){
					session.setAttribute("marker", spez);
				}
				else if(spez==null){
					
				}
			}
			else if(value.equalsIgnoreCase("Kunde")){
				//weiter zur Kunden Seite bzw zum Servlet
			}
			else if(value==null){
				//Keiner Gefunden Fehler ausgeben
			}
			
		}
		//doGet(request, response);
	}

}
