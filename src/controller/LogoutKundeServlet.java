package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Logout eines eingeloggten Kunde.
 */
@WebServlet("/LogoutKundeServlet")
public class LogoutKundeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * Konstruktor
     */
    public LogoutKundeServlet() {
        super();
        
    }

	/**
	 * Das ServletContextAttribute wird auf null gesetzt und es kommt zu einer Weiterleitung auf index.jsp.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getServletContext().setAttribute("kunde", null);
		request.setAttribute("message", "Logout erfolgreich!");
		RequestDispatcher view = request.getRequestDispatcher("index.jsp");
		view.forward(request, response);
	}

	/**
	 * doPost leitet auf index.jsp weiter.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		RequestDispatcher view = request.getRequestDispatcher("index.jsp");
		view.forward(request, response);
	}

}
