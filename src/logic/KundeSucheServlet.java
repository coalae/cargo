package controller;

import logic.KundenMgmt;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

      

/**
 * Servlet implementation class KundeSucheServlet.
 * Im KundeSucheServlet kann ein Kunde anhand keyword gesucht werden. Das keyword wird 
 * in Vorname, Nachname und Username Instanzvariablen der Kunden-Objekte gesucht.
 */
@WebServlet("/KundeSucheServlet")
public class KundeSucheServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;
 
	    /**
	     * Constructor
	     */
	    public KundeSucheServlet() {
	        super();
	    }

		/**
		 * doGet leitet auf indexLoggedInAsMitarbeiter.jsp weiter
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
			RequestDispatcher view = request.getRequestDispatcher("indexLoggedInAsMitarbeiter.jsp");
			view.forward(request, response);
		}

		/**
		 * doPost nimmt die Eingaben von Keyword (Vorname, Nachname, Username) entgegen und sucht den Kunde in der Gesamtliste.
		 * Wenn Eingabefelder leer: wird "Eingabe erforderlich" angezeigt.
		 * Wenn Fehler waren: Anzeige, dass Fehler.
		 * Wenn Kunde gefunden wurde: Kunde(n) anzeigen.
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			PrintWriter out = response.getWriter();
			KundenMgmt km = new KundenMgmt();
			response.setContentType("text/html");

 				
			String keywordSuche = (String) request.getParameter("keywordSuche");
						
							
						// KUNDE suchen
						try{
							request.setAttribute("keywordSuche",keywordSuche);
							RequestDispatcher view = request.getRequestDispatcher("kundeSucheErgebnis.jsp");
							view.forward(request, response);
						} catch (Exception e) {
							e.printStackTrace();
							String message = "Fehler!";
							request.setAttribute("message", message);
							RequestDispatcher view = request.getRequestDispatcher("kundeSucheErgbnis.jsp");
							view.forward(request, response);
						}		
	}
}		
