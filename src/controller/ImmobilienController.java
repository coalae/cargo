package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.ImmobilienMgmt;

public class ImmobilienController extends HttpServlet {
	private static final long serialVersionUID = 3L;
	private ImmobilienMgmt immobilienM =  null;
	
	
	
	
	public ImmobilienController() {
		immobilienM = new ImmobilienMgmt();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("pageName").equalsIgnoreCase("anlegen")){
			String typ=request.getParameter("typ");
			String name=request.getParameter("name");
		
			
			int typ2 = Integer.parseInt(typ);
			
			immobilienM.addGebäude(typ2, name);;
			request.getRequestDispatcher("indexLoggedInAsMitarbeiter.jsp").include(request, response);
			
		}
		
		if(request.getParameter("pageName").equalsIgnoreCase("loeschen")){
			String immobilienid = request.getParameter("id");
			int id = Integer.parseInt(immobilienid);
			immobilienM.deleteGebäude(id);
			request.getRequestDispatcher("indexLoggedInAsMitarbeiter.jsp").include(request, response);
		}
		
		if(request.getParameter("pageName").equalsIgnoreCase("aendern")){
			String immobilienid = request.getParameter("immobilienid");
			String typ=request.getParameter("typ");
			String name=request.getParameter("name");
			
			if(typ != null) {
				int typ1 = Integer.parseInt(typ);
				int id = Integer.parseInt(immobilienid);
				immobilienM.updatetyp(id, typ1);
			}
			if(name != null) {
				int id = Integer.parseInt(immobilienid);
				immobilienM.updatename(id, name);
			}

		}
	}
}
