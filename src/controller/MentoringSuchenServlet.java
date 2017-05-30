package controller;

import logic.KursMgmt;
import logic.MentoringMgmt;

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
@WebServlet("/MentoringSuchenServlet")
public class MentoringSuchenServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;
 
	    /**
	     * Constructor
	     */
	    public MentoringSuchenServlet() {
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
			MentoringMgmt mm = new MentoringMgmt();
			response.setContentType("text/html");

 				
			int mentoringid = Integer.parseInt(request.getParameter("mentoringid"));
			int mentorid = Integer.parseInt(request.getParameter("mentorid"));
			int menteeid = Integer.parseInt(request.getParameter("menteeid"));

						
							
						// KURS suchen
						try{
							if (mentoringid != 0){
							request.setAttribute("mentoringid",mentoringid);
							RequestDispatcher view = request.getRequestDispatcher("mentoringSucheErgebnis.jsp");
							view.forward(request, response);
							}
							
							else if (mentorid != 0){
								request.setAttribute("mentorid",mentorid);
								RequestDispatcher view = request.getRequestDispatcher("mentoringSucheErgebnis.jsp");
								view.forward(request, response);
								}
					
							else if (menteeid != 0){
								request.setAttribute("menteeid",menteeid);
								RequestDispatcher view = request.getRequestDispatcher("mentoringSucheErgebnis.jsp");
								view.forward(request, response);
								}
								
							
						} catch (Exception e) {
							e.printStackTrace();
							String message = "Fehler!";
							request.setAttribute("message", message);
							RequestDispatcher view = request.getRequestDispatcher("mentoringSucheErgebnis.jsp");
							view.forward(request, response);
						}		
	}
}		
