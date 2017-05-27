package controller;

import logic.KursMgmt;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

      

/**
 * Servlet implementation class KursSucheServlet.
 * Im KursSucheServlet kann ein Kurs anhand des kursname gesucht werden in der Gesamtliste.
 */
@WebServlet("/KursSucheServlet")
public class KursSucheServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;
 
	    /**
	     * Constructor
	     */
	    public KursSucheServlet() {
	        super();
	    }

		/**
		 * doGet leitet auf index.jsp weiter
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
			//TODO: anpassung der weiterleitung, je nachdem ob und als was man eingeloggt ist
			RequestDispatcher view = request.getRequestDispatcher("index.jsp");
			view.forward(request, response);
		}

		/**
		 * doPost nimmt die Eingaben von Kursname entgegen und sucht den Kurs in der Gesamtliste.
		 * Wenn Eingabefelder leer: wird "Eingabe erforderlich" angezeigt.
		 * Wenn Fehler waren: Anzeige, dass Fehler.
		 * Wenn Kurs gefunden wurde: Kurs(e) anzeigen.
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			PrintWriter out = response.getWriter();
			KursMgmt km = new KursMgmt();
			response.setContentType("text/html");

 				
			String keywordSuche = (String) request.getParameter("keywordSuche");
						
							
						// KURS suchen
						try{
							request.setAttribute("keywordSuche",keywordSuche);
							RequestDispatcher view = request.getRequestDispatcher("kursSucheErgebnis.jsp");
							view.forward(request, response);
						} catch (Exception e) {
							e.printStackTrace();
							String message = "Fehler!";
							request.setAttribute("message", message);
							RequestDispatcher view = request.getRequestDispatcher("kursSucheErgbnis.jsp");
							view.forward(request, response);
						}		
	}
}		
