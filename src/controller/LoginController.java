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
	 * wenn als @param login übergeben startet das erste if
	 * zuerst wird geprüft gibt es so einen Nutzer in der Datenbank
	 * Wenn nein dann wird er wieder auf die Index Seite geleitet
	 * Wenn der Login als Mitarbeiter erkannt wird dan wird er auf die indexloggedasMitarbeiter geleitet
	 * Wenn der Login als Kunde erkannt wird wird er auf die Kunden Seite geleitet
	 * Als Sicherheit haben wir noch eine Prüfung die auf die index verweist
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
				if(spez=="Admin"){
					session.setAttribute("marker", spez);
					request.getRequestDispatcher("indexLoggedInAsMitarbeiter.jsp").include(request, response);
				}
				else if(spez.equalsIgnoreCase("It")){
					session.setAttribute("marker", spez);
					request.getRequestDispatcher("indexLoggedInAsMitarbeiter.jsp").include(request, response);
				}
				else if(spez=="Mitarbeiter"){
					session.setAttribute("marker", spez);
					request.getRequestDispatcher("indexLoggedInAsMitarbeiter.jsp").include(request, response);
				}
				else if(spez==null){
					request.getRequestDispatcher("index.jsp").include(request, response);
				}
			}
			else if(value.equalsIgnoreCase("Kunde")){
				request.getRequestDispatcher("indexLoggedInAsKunde.jsp").include(request, response);
			}
			else if(value==null){
				request.getRequestDispatcher("index.jsp").include(request, response);
			}
			
		}
		//doGet(request, response);
	}

}
