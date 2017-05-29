package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.MitarbeiterMgmt;

/**
 * Servlet implementation class MitarbeiterAdminController
 */
@WebServlet("/MitarbeiterAdminController")
public class MitarbeiterAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private  MitarbeiterMgmt mitMG;
    /**
     * Default constructor. 
     */
    public MitarbeiterAdminController() {
        mitMG= new MitarbeiterMgmt();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("pageName").equalsIgnoreCase("mitarbeiter")){
			request.getRequestDispatcher("verwaltenMitarbeiter.jsp").include(request, response);
		}
		if(request.getParameter("pageName").equalsIgnoreCase("back")){
			request.getRequestDispatcher("VerwaltungAuswahl.jsp").include(request, response);
		}
		if(request.getParameter("pageName").equalsIgnoreCase("anlegen")){
			String vorname=request.getParameter("vorname");
			String nachname=request.getParameter("nachname");
			String sozi=request.getParameter("svnr");
			String spezi=request.getParameter("spezi");
			String typ=request.getParameter("typ");
			String datum=request.getParameter("gebdate");
			String user=request.getParameter("user");
			String pwd=request.getParameter("pwd");
			String status=request.getParameter("status");
			mitMG.setMitarbeiterdao(vorname, nachname, sozi, spezi, typ, datum, user, pwd, status);
			request.getRequestDispatcher("VerwaltungAuswahl.jsp").include(request, response);
		}
		if(request.getParameter("pageName").equalsIgnoreCase("loeschen")){
			String svnr=request.getParameter("svnr");
			mitMG.delete(svnr);
			request.getRequestDispatcher("VerwaltungAuswahl.jsp").include(request, response);
		}
		if(request.getParameter("pageName").equalsIgnoreCase("aendern")){
			String user=request.getParameter("user");
			String oldpw=request.getParameter("oldpw");
			String newpw=request.getParameter("newpw");
			mitMG.aendern(user, oldpw, newpw);
			request.getRequestDispatcher("VerwaltungAuswahl.jsp").include(request, response);
		}
		//doGet(request, response);
	}

}
