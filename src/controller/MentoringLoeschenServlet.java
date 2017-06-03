package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.MentoringMgmt;
import model.Mentoring;

/**
 * Mentoring Loeschen
 */
@WebServlet("/MentoringLoeschenServlet")
public class MentoringLoeschenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MentoringLoeschenServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

   
    		/**
    		 * doGet leitet auf index.jsp weiter
    		 */
    		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
    			RequestDispatcher view = request.getRequestDispatcher("indexLoggedInAsMitarbeiter.jsp");
    			view.forward(request, response);
    		}

    		/**
    		 * doPost nimmt die Eingaben der Mentoringid entgegen und versucht das Mentoring zu loeschen.
    		 *  Wenn  Mentoring nicht existiert: Ausgabe einer Fehlermeldung
    		 * Ansonsten Weiterleitung auf mentoringLoeschenauswaehlen
    		 */
    		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    			PrintWriter out = response.getWriter();
    			MentoringMgmt mm = new MentoringMgmt();
    			response.setContentType("text/html");

    							
    						// Mentoring loeschen
    						try{
    							Integer mentoringid = Integer.parseInt(request.getParameter("mentid"));
    							    					
    							Mentoring mentoring = mm.getMentoringById(mentoringid);
    							mm.deleteMentoring(mentoring.getMentoringId());
    							String message = "Mentoring wurde geloescht.";
    							request.setAttribute("message", message);
    							RequestDispatcher view = request.getRequestDispatcher("mentoringLoeschenauswaehlen.jsp");
    							view.forward(request, response);
    						} catch (Exception e) {
    							e.printStackTrace();
    							out.println("Mentoring existiert nicht - Kein Loeschen durchgefuehrt!");
    							String message = "Mentoring existiert nicht - Kein Loeschen durchgefuehrt!";
    							request.setAttribute("message", message);
    							RequestDispatcher view = request.getRequestDispatcher("mentoringLoeschenauswaehlen.jsp");
    							view.forward(request, response);
    						}		
    	}
    }		
