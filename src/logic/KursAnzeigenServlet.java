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
 * Servlet implementation class KursAnzeigenServlet.
 * Im KursAnzeigenServlet kann ein einzelner Kurs angezeigt werden.
 */
@WebServlet("/KursAnzeigenServlet")
public class KursAnzeigenServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;
 
	    /**
	     * Constructor
	     */
	    public KursAnzeigenServlet() {
	        super();
	    }

		/**
		 * doGet nimmt die Eingaben KursId param1 entgegen und leitet diesen an kursListeAnzeigen.jsp weiter, wo die
		 * Infos ueber den Kurs ausgegeben werden.  
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
			PrintWriter out = response.getWriter();
			KursMgmt km = new KursMgmt();
			response.setContentType("text/html");

 				
			String kursId = (String) request.getParameter("param1");
						
							
						// KURS Id weitergeben
						try{
							request.setAttribute("kursId",kursId);
							RequestDispatcher view = request.getRequestDispatcher("kursAnzeigen.jsp");
							view.forward(request, response);
						} catch (Exception e) {
							e.printStackTrace();
							String message = "Fehler!";
							request.setAttribute("message", message);
							RequestDispatcher view = request.getRequestDispatcher("kursAnzeigen.jsp");
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
