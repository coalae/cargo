package controller;

import logic.KursMgmt;
import logic.MentoringMgmt;
import model.Kurs;
import model.Mentoring;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
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
 * Servlet MentoringAendernServlet.
 * Mentoringthema und Jahr können geändert werden
 */
@WebServlet("/MentoringAendernServlet")
public class MentoringAendernServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;
 
	    /**
	     * Constructor
	     */
	    public MentoringAendernServlet() {
	        super();
	    }

		/**
		 * doGet leitet auf mentoringAendern2.jsp weiter
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
			String mentoringid = (String) request.getParameter("mentoringid");
			request.setAttribute("mentoringid", mentoringid);
			RequestDispatcher view = request.getRequestDispatcher("mentoringAendern2.jsp");
			view.forward(request, response);
		}

		/**
		 * doPost nimmt die Eingaben der Mentoringdaten entgegen und versucht, versucht das Mentoring zu ändern.
		 * Wenn Eingabefelder leer: diese Felder werden nicht geaendert.
		 * Fehlerbehandlung
		 * Kein Fehler: Weiterleiten auf mentoringAendern.jsp
		 */
		@SuppressWarnings("unused")
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			PrintWriter out = response.getWriter();
			MentoringMgmt mm = new MentoringMgmt();
			response.setContentType("text/html");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy"); 

         try{
 			String mentoringFuerUpdateId = (String) request.getParameter("mentoringid");
 			int up = Integer.parseInt(mentoringFuerUpdateId);
 			Mentoring mentoringFuerUpdate = mm.getMentoringById(up);
 			
 			
			
			String thema = (String) request.getParameter("thema");		
			String beginnjahr = (String) request.getParameter("beginnjahr");
		
 			
 			if(thema!=null && !thema.isEmpty()){
 				mentoringFuerUpdate.setThema(thema);
 			}
 			
 			if(beginnjahr!=null && !beginnjahr.isEmpty()){
 				try {
 					mentoringFuerUpdate.setBeginnJahr(sdf.parse(beginnjahr));
 				}
 				catch (Exception e){
 					String message = "Falsches Datum!";
 				}
 			}
 			
 		
			// Mentoring anlegen

				Mentoring geaendertesMentoring = new Mentoring(mentoringFuerUpdate.getMentoringId(), mentoringFuerUpdate.getMenteeId(), mentoringFuerUpdate.getMentorId(), mentoringFuerUpdate.getThema(), mentoringFuerUpdate.getBeginnJahr());
				mm.updateMentoring(mentoringFuerUpdate);
				String message = "Mentoring wurde geaendert.";
				request.setAttribute("message", message);
				RequestDispatcher view = request.getRequestDispatcher("mentoringAendern.jsp");
				view.forward(request, response);
 					
 		} catch (NullPointerException e) {
				e.printStackTrace();
				out.println("Eingabefehler!");
				String message = "Eingabefehler! Eingaben wurden nicht geaendert.";
				request.setAttribute("message", message);
				RequestDispatcher view = request.getRequestDispatcher("mentoringAendern2.jsp");
				view.forward(request, response);
	    } catch (NumberFormatException e) {
			e.printStackTrace();
			out.println("Eingabefehler!");
			String message = "Eingabefehler! Eingaben wurden nicht geaendert.";
			request.setAttribute("message", message);
			RequestDispatcher view = request.getRequestDispatcher("mentoringAendern2.jsp");
			view.forward(request, response);
		}
	}
}		
