package controller;

import logic.KundenMgmt;
import logic.KursMgmt;
import model.Kunde;
import model.Kurs;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

      

/**
 * Servlet implementation class KundeRegistrierenServlet.
 * Im KundeRegistrierenServlet kann ein neuer Kunde angelegt werden bzw. ein Kundenaccount erstellt werden.
 */
@WebServlet("/KundeRegistrierenServlet")
public class KundeRegistrierenServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;
 
	    /**
	     * Constructor
	     */
	    public KundeRegistrierenServlet() {
	        super();
	    }

		/**
		 * doGet leitet auf index.jsp weiter
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
			RequestDispatcher view = request.getRequestDispatcher("index.jsp");
			view.forward(request, response);
		}

		/**
		 * doPost nimmt die Eingaben der Kundendaten entgegen und versucht, den Kunde zu erstellen.
		 * Wenn Eingabefelder leer: wird "Eingabe erforderlich" angezeigt.
		 * Wenn Fehler waren: Anzeige, dass Fehler.
		 * Wenn Kunde erstellt wurde: Nachricht, dass Kundenaccount erstellt wurde.
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			PrintWriter out = response.getWriter();
			KundenMgmt km = new KundenMgmt();
			response.setContentType("text/html");
			
			String vorname = (String) request.getParameter("vorname");
			String nachname = (String) request.getParameter("nachname");
			int iban = Integer.parseInt(request.getParameter("iban"));
			String bic = (String) request.getParameter("bic");		
			String username = (String) request.getParameter("username");
			String password = (String) request.getParameter("password");
						

						// KUNDENACCOUNT anlegen
						try{ // anlegen immer mit dummy-Id 0, weil auto-increment in der DB
							Kunde neuerKunde = new Kunde(0, vorname, nachname, iban, bic, username, password, true);
							
							if(km.checkKundeExists(username)){
								String message = "Kunde existiert schon - Account konnte nicht erstellt werden." + 
							"\n" + " Bitte anderen Username waehlen!";
								request.setAttribute("message", message);
								RequestDispatcher view = request.getRequestDispatcher("kundeRegistrieren.jsp");
								view.forward(request, response);								
							}
							
							km.addKunde(neuerKunde);
							String message = "Kundenaccount wurde angelegt." + "\n" + " Sie koennen sich nun einloggen.";
							request.setAttribute("message", message);
							RequestDispatcher view = request.getRequestDispatcher("kundeRegistrieren.jsp");
							view.forward(request, response);
						} catch (Exception e) {
							e.printStackTrace();
							out.println("Eingabefehler!");
							String message = "Eingabefehler! Kundenaccount nicht erstellt.";
							request.setAttribute("message", message);
							RequestDispatcher view = request.getRequestDispatcher("kundeRegistrieren.jsp");
							view.forward(request, response);
						}	
		
	}
}		
