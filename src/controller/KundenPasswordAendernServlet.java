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

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

      

/**
 * Servlet implementation class KundenPasswordAendernServlet.
 * Im KundenPasswordAendernServlet kann das password eines Kunde geaendert werden.
 */
@WebServlet("/KundenPasswordAendernServlet")
public class KundenPasswordAendernServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;
 
	    /**
	     * Constructor
	     */
	    public KundenPasswordAendernServlet() {
	        super();
	    }

		/**
		 * doGet leitet auf index.jsp weiter
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
			RequestDispatcher view = request.getRequestDispatcher("indexLoggedInAsKunde.jsp");
			view.forward(request, response);
		}

		/**
		 * doPost nimmt die Eingabe des neuen Password entgegen und versucht, den Kunde zu aendern.
		 * Wenn Eingabefelder leer: diese Felder werden nicht geaendert.
		 * Wenn Fehler waren: Anzeige, dass Fehler.
		 * Wenn Kundenpassword geaendert wurde: Nachricht, dass Password geaendert wurde.
		 */
		@SuppressWarnings("unused")
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			PrintWriter out = response.getWriter();
			KundenMgmt km = new KundenMgmt();
			response.setContentType("text/html");
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy"); 

         try{
 			int kundeFuerUpdateId = Integer.parseInt(request.getParameter("kundeFuerUpdate"));	
 			Kunde kundeFuerUpdate = km.getKundeById(kundeFuerUpdateId);
 			
 			
			String password = (String) request.getParameter("password");
			
 			
 			if(password!=null && !password.isEmpty()){
 				kundeFuerUpdate.setPassword(password);
 			}
 			
				
			// KUNDE anlegen

				Kunde geaenderterKunde = new Kunde(kundeFuerUpdate.getId(), kundeFuerUpdate.getVorname(), kundeFuerUpdate.getNachname(), 
						kundeFuerUpdate.getIban(), kundeFuerUpdate.getBic(), kundeFuerUpdate.getUsername(), 
						kundeFuerUpdate.getPassword(), kundeFuerUpdate.isActive()); 
				km.updateKunde(kundeFuerUpdate);
				String message = "Password wurde geaendert.";
				request.setAttribute("message", message);
				RequestDispatcher view = request.getRequestDispatcher("meinKundenPasswortAendern.jsp");
				view.forward(request, response);
 					
 		} catch (NullPointerException e) {
				e.printStackTrace();
				out.println("Eingabefehler!");
				String message = "Eingabefehler! Passwort nicht geaendert.";
				request.setAttribute("message", message);
				RequestDispatcher view = request.getRequestDispatcher("meinKundenPasswortAendern.jsp");
				view.forward(request, response);
	    } catch (NumberFormatException e) {
			e.printStackTrace();
			out.println("Eingabefehler!");
			String message = "Eingabefehler! Passwort nicht geaendert.";
			request.setAttribute("message", message);
			RequestDispatcher view = request.getRequestDispatcher("meinKundenPasswortAendern.jsp");
			view.forward(request, response);
		}
	}
}		
