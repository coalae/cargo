package controller;

import logic.KursMgmt;
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
 * Servlet implementation class KursAendernServlet.
 * Im KursAendernServlet koennen die Instanzvariablen des Kurs veraendert werde und die Neuerung kann 
 * in der DB gespeichert werden.
 */
@WebServlet("/KursAendernServlet")
public class KursAendernServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;
 
	    /**
	     * Constructor
	     */
	    public KursAendernServlet() {
	        super();
	    }

		/**
		 * doGet leitet auf kursAendern2.jsp weiter
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
			String kursname = (String) request.getParameter("kursname");
			request.setAttribute("kursname", kursname);
			RequestDispatcher view = request.getRequestDispatcher("kursAendern2.jsp");
			view.forward(request, response);
		}

		/**
		 * doPost nimmt die Eingaben der Kursdaten entgegen und versucht, den Kurs zu aendern.
		 * Wenn Eingabefelder leer: diese Felder werden nicht geaendert.
		 * Wenn Fehler waren: Anzeige, dass Fehler.
		 * Wenn Kurs erstellt wurde: Nachricht, dass Kurs geaendert wurde (wenn Aenderung eingetragen in Feldern).
		 */
		@SuppressWarnings("unused")
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			PrintWriter out = response.getWriter();
			KursMgmt km = new KursMgmt();
			response.setContentType("text/html");
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy"); 

         try{
 			int kursFuerUpdateId = Integer.parseInt(request.getParameter("kursFuerUpdateId"));	
 			Kurs kursFuerUpdate = km.getKursById(kursFuerUpdateId);
 			
 			
			String kursname = (String) request.getParameter("kursname");
			String beginnStr = (String) request.getParameter("beginndatum");		
			String endStr = (String) request.getParameter("enddatum");
			String gesamtpreis = (String) request.getParameter("gesamtpreis");
			String betreuerId = (String) request.getParameter("betreuerId");
			String maxAnzahl = (String) request.getParameter("maxAnzahl");
			String immobilie = (String) request.getParameter("immobilienId");
 			
 			if(kursname!=null && !kursname.isEmpty()){
 				kursFuerUpdate.setKursname(kursname);
 			}
 			
 			if(gesamtpreis!=null && !gesamtpreis.isEmpty()){
 				kursFuerUpdate.setPreis(Double.parseDouble(gesamtpreis));
 			}
 			
 			if(betreuerId!=null && !betreuerId.isEmpty()){
 				kursFuerUpdate.setBetreuerId(Integer.parseInt(betreuerId));
 			}		
 			if(maxAnzahl!=null && !maxAnzahl.isEmpty()){
 				kursFuerUpdate.setMaxAnzahl(Integer.parseInt(maxAnzahl));
 			} 		
 			if(immobilie!=null && !immobilie.isEmpty()){
 				kursFuerUpdate.setImmobilie(Integer.parseInt(immobilie));
 			}
 			
			// dummy liste fuer kurserstellung
			ArrayList<Integer> teilnehmerListe=new ArrayList<Integer>();
			teilnehmerListe.add(1);
				
			// KURS anlegen

				Kurs geaenderterKurs = new Kurs(kursFuerUpdate.getId(), kursFuerUpdate.getKursname(), kursFuerUpdate.getLevel(), 
				kursFuerUpdate.getBeginn(), kursFuerUpdate.getEnde(), kursFuerUpdate.getPreis(), 
				kursFuerUpdate.getBetreuerId(), kursFuerUpdate.getMaxAnzahl(), kursFuerUpdate.getImmobilie(), 
				teilnehmerListe);
				km.updateKurs(kursFuerUpdate);
				String message = "Kurs wurde geaendert.";
				request.setAttribute("message", message);
				RequestDispatcher view = request.getRequestDispatcher("kursAendern.jsp");
				view.forward(request, response);
 					
 		} catch (NullPointerException e) {
				e.printStackTrace();
				out.println("Eingabefehler!");
				String message = "Eingabefehler! Kurs nicht geaendert.";
				request.setAttribute("message", message);
				RequestDispatcher view = request.getRequestDispatcher("kursAendern2.jsp");
				view.forward(request, response);
	    } catch (NumberFormatException e) {
			e.printStackTrace();
			out.println("Eingabefehler!");
			String message = "Eingabefehler! Kurs nicht geaendert.";
			request.setAttribute("message", message);
			RequestDispatcher view = request.getRequestDispatcher("kursAendern2.jsp");
			view.forward(request, response);
		}
	}
}		
