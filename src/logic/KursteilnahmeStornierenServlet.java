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
 * Servlet implementation class KursteilnahmeStornierenServlet.
 * Im KursteilnahmeStornierenServlet kann die Teilnahme (kundenId) eines Kunde von der Liste 
 * der Teilnehmer Ids im jeweiligen Kurs-Objekt geloescht werden.
 */
@WebServlet("/KursteilnahmeStornierenServlet")
public class KursteilnahmeStornierenServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;
 
	    /**
	     * Constructor
	     */
	    public KursteilnahmeStornierenServlet() {
	        super();
	    }

		/**
		 * doGet loescht die uebergebene Kursteilnahme
		 * Wenn Fehler waren: Anzeige, dass Fehler.
		 * Wenn Kursteilnahme geloescht wurde: Nachricht, dass Kursteilnahme geloescht wurde.
		 */
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		

			PrintWriter out = response.getWriter();
			KursMgmt km = new KursMgmt();
			response.setContentType("text/html");

 				
			String kursIdStr = (String) request.getParameter("param1");
			int kursId = Integer.parseInt(kursIdStr);
						
							
						// KURSTEILNAHME loeschen
						try{
							//TODO kundenId vom eingeloggten Kunde nehmen - hier nur Dummy-Id
							int dummyKundenId = 1;
							km.deleteTeilnehmerFromKurs(dummyKundenId, kursId);
							String message = "Kursteilnahme wurde storniert.";
							request.setAttribute("message", message);
							RequestDispatcher view = request.getRequestDispatcher("meineKundenKurse.jsp");
							view.forward(request, response);
						} catch (Exception e) {
							e.printStackTrace();
							out.println("Kursteilnahme existiert nicht - Kein Loeschen durchgefuehrt!");
							String message = "Kursteilnahme existiert nicht - Kein Loeschen durchgefuehrt!";
							request.setAttribute("message", message);
							RequestDispatcher view = request.getRequestDispatcher("meineKundenKurse.jsp");
							view.forward(request, response);
						}		    	   	
	    	
			}

		/**
		 * doPost leitet auf indexLoggedInAsKunde.jsp weiter		 
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	RequestDispatcher view = request.getRequestDispatcher("indexLoggedInAsKunde.jsp");
				view.forward(request, response);
		
	}
}		
