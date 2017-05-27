package controller;

import logic.KursMgmt;
import model.Kurs;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

      

/**
 * Servlet implementation class KursLoeschenServlet.
 * Im KursLoeschenServlet kann ein Kurs geloescht werden.
 */
@WebServlet("/KursLoeschenServlet")
public class KursLoeschenServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;
 
	    /**
	     * Constructor
	     */
	    public KursLoeschenServlet() {
	        super();
	    }

		/**
		 * doGet leitet auf index.jsp weiter
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
			RequestDispatcher view = request.getRequestDispatcher("indexLoggedInAsMitarbeiter.jsp");
			view.forward(request, response);
		}

		/**
		 * doPost nimmt die Eingaben von Kursname und Beginndatum entgegen und versucht, den Kurs zu loeschen.
		 * Wenn Eingabefelder leer: wird "Eingabe erforderlich" angezeigt.
		 * Wenn Fehler waren: Anzeige, dass Fehler.
		 * Wenn Kurs geloescht wurde: Nachricht, dass Kurs geloescht wurde.
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			PrintWriter out = response.getWriter();
			KursMgmt km = new KursMgmt();
			response.setContentType("text/html");

 				
			String kursname = (String) request.getParameter("kursname");
						
							
						// KURS loeschen
						try{
							Kurs zuLoeschenderKurs = km.getKursByKursname(kursname);
							km.deleteKurs(zuLoeschenderKurs.getId());
							String message = "Kurs wurde geloescht.";
							request.setAttribute("message", message);
							RequestDispatcher view = request.getRequestDispatcher("kursLoeschen.jsp");
							view.forward(request, response);
						} catch (Exception e) {
							e.printStackTrace();
							out.println("Kurs existiert nicht - Kein Loeschen durchgefuehrt!");
							String message = "Kurs existiert nicht - Kein Loeschen durchgefuehrt!";
							request.setAttribute("message", message);
							RequestDispatcher view = request.getRequestDispatcher("kursLoeschen.jsp");
							view.forward(request, response);
						}		
	}
}		
