package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import logic.ImmobilienMgmt;

/**
 * Servlet implementation class FahrzeugAdminController
 */
@WebServlet("/ImmobilienController")
public class ImmobilienController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ImmobilienMgmt immoM =  null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImmobilienController() {
    	immoM = new ImmobilienMgmt();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * Hier werden die einzelnen requested geholt. Hier werden Immobilien gelöscht, geändert und erzeugt
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("pageName").equalsIgnoreCase("anlegen")){
			System.out.println("Bin hier im anlegen");

			String typ=request.getParameter("typ");
			String name=request.getParameter("name");
			
			int typ1 = Integer.parseInt(typ);
			immoM.addGebäude(typ1, name);
			request.getRequestDispatcher("immobilienListe.jsp").include(request, response);
			
		}
		
		if(request.getParameter("pageName").equalsIgnoreCase("loeschen")){
			String immobilienid = request.getParameter("id");
			int id = Integer.parseInt(immobilienid);
			immoM.deleteGebäude(id);
			request.getRequestDispatcher("immobilienListe.jsp").include(request, response);
		}
		
		if(request.getParameter("pageName").equalsIgnoreCase("aendern")){
			String immobilienid = request.getParameter("id");
			String typ=request.getParameter("typ");
			String name=request.getParameter("name");

			
		//	if(marke != " ") {
				int id = Integer.parseInt(immobilienid);
				int typ1 =  Integer.parseInt(typ);
				immoM.updatetyp(id, typ1);
				System.out.println("bin noch im Controller");
	//		}
			
	//		if(modell != "") {
			//	int id = Integer.parseInt(fahrzeugid);
				immoM.updatename(id, name);
	//		}
			
	
			request.getRequestDispatcher("immobilienListe.jsp").include(request, response);
	}

	}
}
