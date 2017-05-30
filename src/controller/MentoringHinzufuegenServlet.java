package controller;


import logic.MentoringMgmt;
import model.Mentoring;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

      

/**
 * Servlet implementation class KursHinzufuegenServlet.
 * Im KursHinzufuegenServlet kann ein Kurs hinzugefuegt werden.
 */
@WebServlet("/MentoringHinzufuegenServlet")
public class MentoringHinzufuegenServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;
 
	    /**
	     * Constructor
	     */
	    public MentoringHinzufuegenServlet() {
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
		 * doPost nimmt die Eingaben des Mentoring entgegen und versucht, die neue Mentoringbeziehung zu erstellen.
		 * Wenn Eingabefelder leer: wird "Eingabe erforderlich" angezeigt.
		 * Wenn Fehler waren: Anzeige, dass Fehler.
		 * Wenn Mentoring erstellt wurde: Nachricht, dass Mentoring erstellt wurde.
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			PrintWriter out = response.getWriter();
			MentoringMgmt mm = new MentoringMgmt();
			response.setContentType("text/html");
			
			
			
			
		

 		
			int mentorid = Integer.parseInt(request.getParameter("mentorid"));
			int menteeid = Integer.parseInt(request.getParameter("menteeid"));
			String thema = (String) request.getParameter("thema");		
			String beginnjahr = (String) request.getParameter("beginnjahr");
			SimpleDateFormat sdf = new SimpleDateFormat("YYYY");

			try{
				Date d1=sdf.parse(beginnjahr);
			} catch(Exception e) {
				String message = "Datum falsch.";
				request.setAttribute("message", message);
				RequestDispatcher view = request.getRequestDispatcher("mentoringHinzufuegen.jsp");
				view.forward(request, response);

			}			
			GregorianCalendar beginnCal = new GregorianCalendar();
			beginnCal.setLenient(false);
			try {
				beginnCal.setTime(sdf.parse(beginnjahr));
			} catch (ParseException e) {
				e.printStackTrace();
			}
					
			
			//Mentoring anlegen
			try{
				Date bj = sdf.parse(beginnjahr);
				
							
				if(mm.checkMentoringExists(mentorid, menteeid)){
					String message = "Mentoringbeziehung existiert bereits und kann daher nicht nochmal angelegt werden";
					request.setAttribute("message", message);
					RequestDispatcher view = request.getRequestDispatcher("mentoringHinzufuegen.jsp");
					view.forward(request, response);								
				}
				
				else{
				Mentoring mentoringbeziehung = new Mentoring(0, mentorid, menteeid, thema, bj);
				mm.addMentoring(mentoringbeziehung);
				String message = "Neues Mentoring wurde gespeichert!";
				request.setAttribute("message", message);
				RequestDispatcher view = request.getRequestDispatcher("mentoringHinzufuegen.jsp");
				view.forward(request, response);
				}
			} catch (Exception e) {
				e.printStackTrace();
				out.println("Eingabefehler!");
				String message = "Eingabefehler! Mentoring konnte nicht hinzugefuegt werden.";
				request.setAttribute("message", message);
				RequestDispatcher view = request.getRequestDispatcher("mentoringHinzufuegen.jsp");
				view.forward(request, response);
			}

		}
}		
