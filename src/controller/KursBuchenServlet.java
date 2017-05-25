package controller;

import logic.KursMgmt;
import model.Kurs;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

      

/**
 * Servlet implementation class KursBuchenServlet
 */
@WebServlet("/KursBuchenServlet")
public class KursBuchenServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;
 
	    /**
	     * Default-Constructor
	     */
	    public KursBuchenServlet() {
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
		 * doPost nimmt die kursId und kundenId (Id des Teilnehmers) entgegen und erhoeht teilnehmerliste um 1.
		 * Man sieht den "Kurs buchen" Button nur, wenn noch genug freie Plaetze da sind.
		 * Wenn Kurs gebucht wurde: Nachricht, dass Kurs gebucht wurde.
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			PrintWriter out = response.getWriter();
			KursMgmt km = new KursMgmt();
			response.setContentType("text/html");

 			int kursId = Integer.parseInt(request.getParameter("kursId"));
			int kundenId = Integer.parseInt(request.getParameter("kundenId"));	
			request.setAttribute("kursId", kursId);

						
						// TEILNAHME bei KURS anlegen
						try{
							km.addTeilnehmerToKurs(kundenId, kursId);

							String message = "Kurs wurde gebucht!";
							request.setAttribute("message", message);
							RequestDispatcher view = request.getRequestDispatcher("kursAnzeigen2.jsp");
							view.forward(request, response);								
				
						} catch (Exception e) {
							e.printStackTrace();
							out.println("Eingabefehler!");
							String message = "Eingabefehler! Kurs nicht hinzugefuegt.";
							request.setAttribute("message", message);
							RequestDispatcher view = request.getRequestDispatcher("kursAnzeigen2.jsp");
							view.forward(request, response);
						}		
	}
}		
