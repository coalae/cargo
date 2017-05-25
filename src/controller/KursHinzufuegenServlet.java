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
 * Servlet implementation class KursHinzufuegenServlet
 */
@WebServlet("/KursHinzufuegenServlet")
public class KursHinzufuegenServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;
 
	    /**
	     * Default-Constructor
	     */
	    public KursHinzufuegenServlet() {
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
		 * doPost nimmt die Eingaben der Kursdaten entgegen und versucht, den Kurs zu erstellen.
		 * Wenn Eingabefelder leer: wird "Eingabe erforderlich" angezeigt.
		 * Wenn Fehler waren: Anzeige, dass Fehler.
		 * Wenn Kurs erstellt wurde: Nachricht, dass Kurs erstellt wurde.
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			PrintWriter out = response.getWriter();
			KursMgmt km = new KursMgmt();
			response.setContentType("text/html");

 				
			String kursname = (String) request.getParameter("kursname");
			String kurslevel = (String) request.getParameter("kurslevel");
			String beginnStr = (String) request.getParameter("beginndatum");		
			String endStr = (String) request.getParameter("enddatum");
			double preis = Double.parseDouble(request.getParameter("gesamtpreis"));
			int betreuerId = Integer.parseInt(request.getParameter("betreuerId"));
			int maxAnzahl = Integer.parseInt(request.getParameter("maxAnzahl"));
			int immobilie = Integer.parseInt(request.getParameter("immobilienId"));
						
						// Bsp Datum: 10.05.2017
						SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

						try{
							Date d1=sdf.parse(beginnStr);
							Date d2=sdf.parse(endStr);
						} catch(ParseException e) {
							String message = "Datum falsch.";
							request.setAttribute("message", message);
							RequestDispatcher view = request.getRequestDispatcher("kursHinzufuegen.jsp");
							view.forward(request, response);
						} catch(StringIndexOutOfBoundsException e) {
							String message = "Datum falsch.";
							request.setAttribute("message", message);
							RequestDispatcher view = request.getRequestDispatcher("kursHinzufuegen.jsp");
							view.forward(request, response);			
						}
						
						GregorianCalendar beginnCal = new GregorianCalendar();
					    beginnCal.setLenient(false);
						try {
							beginnCal.setTime(sdf.parse(beginnStr));
						} catch (ParseException e) {
							e.printStackTrace();
						}
						
						GregorianCalendar endCal = new GregorianCalendar();
					    endCal.setLenient(false);
						try {
							endCal.setTime(sdf.parse(endStr));
						} catch (ParseException e) {
							e.printStackTrace();
						}
						
						if(beginnCal.after(endCal) || beginnCal.equals(endCal)){
							String message = "Beginn darf nicht nach Ende/am gleichen Tag wie Ende liegen.";
							request.setAttribute("message", message);
							RequestDispatcher view = request.getRequestDispatcher("kursHinzufuegen.jsp");
							view.forward(request, response);				
						}
						
						// dummy liste fuer kurserstellung
						ArrayList<Integer> teilnehmerListe=new ArrayList<Integer>();
						teilnehmerListe.add(1);
							
						// KURS anlegen
						try{
							Kurs neuerKurs = new Kurs(0, kursname, kurslevel, beginnCal, endCal, preis, betreuerId, maxAnzahl, 
							immobilie, teilnehmerListe);
							
							if(km.checkKursExists(kursname)){
								String message = "Kurs existiert schon - konnte nicht hinzugefuegt werden.";
								request.setAttribute("message", message);
								RequestDispatcher view = request.getRequestDispatcher("kursHinzufuegen.jsp");
								view.forward(request, response);								
							}
							
							km.addKurs(neuerKurs);
							String message = "Kurs wurde gespeichert.";
							request.setAttribute("message", message);
							RequestDispatcher view = request.getRequestDispatcher("kursHinzufuegen.jsp");
							view.forward(request, response);
						} catch (Exception e) {
							e.printStackTrace();
							out.println("Eingabefehler!");
							String message = "Eingabefehler! Kurs nicht hinzugefuegt.";
							request.setAttribute("message", message);
							RequestDispatcher view = request.getRequestDispatcher("kursHinzufuegen.jsp");
							view.forward(request, response);
						}

	
		
	}
}		
