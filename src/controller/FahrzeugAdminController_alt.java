package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.FahrzeugMgmt;

public class FahrzeugAdminController_alt extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FahrzeugMgmt fahrzeugM =  null;
	
	
	
	public FahrzeugAdminController_alt() {
		fahrzeugM = new FahrzeugMgmt();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		if(request.getParameter("pageName").equalsIgnoreCase("anlegen")){
			System.out.println("Bin hier im anlegen");
			String marke=request.getParameter("Marke");
			String modell=request.getParameter("Modell");
			String baujahr=request.getParameter("Baujahr");
			String farbe=request.getParameter("farbe");
			
			//int baujahr2 = Integer.parseInt(baujahr);
			
			fahrzeugM.addFahrzeug(marke, modell, baujahr, farbe);
			request.getRequestDispatcher("indexLoggedInAsMitarbeiter.jsp").include(request, response);
			
		}
		
		if(request.getParameter("pageName").equalsIgnoreCase("loeschen")){
			String fahrzeugid = request.getParameter("id");
			int id = Integer.parseInt(fahrzeugid);
			fahrzeugM.deleteFahrzeug(id);
			request.getRequestDispatcher("indexLoggedInAsMitarbeiter.jsp").include(request, response);
		}
		
		if(request.getParameter("pageName").equalsIgnoreCase("aendern")){
			String fahrzeugid = request.getParameter("fahrzeugid");
			String marke=request.getParameter("marke");
			String modell=request.getParameter("modell");
			String baujahr=request.getParameter("baujahr");
			String farbe=request.getParameter("farbe");
			
			if(marke != null) {
				int id = Integer.parseInt(fahrzeugid);
				fahrzeugM.updateMarke(id, marke);
			}
			if(modell != null) {
				int id = Integer.parseInt(fahrzeugid);
				fahrzeugM.updateModell(id, modell);
			}
			if(baujahr != null) {
				int jahr = Integer.parseInt(baujahr);
				fahrzeugM.updateJahr(jahr, marke);
			}
			
			if(farbe != null) {
				int id = Integer.parseInt(fahrzeugid);
				fahrzeugM.updateFarbe(id, farbe);
			}	
		}
		
		
		
		
		
	}
	
	

}
