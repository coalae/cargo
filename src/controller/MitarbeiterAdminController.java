package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MitarbeiterAdminController
 */
@WebServlet("/MitarbeiterAdminController")
public class MitarbeiterAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public MitarbeiterAdminController() {
        // TODO Auto-generated constructor stub
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
			
		}
		//doGet(request, response);
	}

}
