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
 * MentoringSuchenfuerLoeschenServlet
 */
@WebServlet("/MentoringSuchenfuerLoeschen")
public class MentoringSuchenfuerLoeschen extends HttpServlet {
		private static final long serialVersionUID = 1L;
 
	    /**
	     * Constructor
	     */
	    public MentoringSuchenfuerLoeschen() {
	        super();
	    }

		/**
		 * doGet nimmt die Eingaben mentoringId entgegen und leitet diesen an mentoringLoeschen.jsp weiter, wo die
		 * Infos ueber das Mentoring ausgegeben werden, welche geloescht werden soll.
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
			PrintWriter out = response.getWriter();
			MentoringMgmt mm = new MentoringMgmt();
			response.setContentType("text/html");

 				
			String mentoringId = (String) request.getParameter("mentoringId");
						
							
						// Mentoring Id weitergeben
						try{
							request.setAttribute("mentoringId",mentoringId);
							RequestDispatcher view = request.getRequestDispatcher("/mentoringLoeschen.jsp");
							view.forward(request, response);
						} catch (Exception e) {
							e.printStackTrace();
							String message = "Fehler!";
							request.setAttribute("message", message);
							RequestDispatcher view = request.getRequestDispatcher("mentoringLoeschenauswaehlen.jsp");
							view.forward(request, response);
						}	
		}

		/**
		 * doPost leitet auf index.jsp weiter
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//TODO: fallunterscheidung, je nachdem ob man und als was man eingeloggt ist
			RequestDispatcher view = request.getRequestDispatcher("index.jsp");
			view.forward(request, response);	
	}
}		
