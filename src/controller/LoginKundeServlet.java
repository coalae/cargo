package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.KundenMgmt;
import logic.MitarbeiterMgmt;

import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Dieses Servlet dient dem Einloggen von Kunden.
 */
@WebServlet("/LoginKundeServlet")
public class LoginKundeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Konstruktor
	 */
	public LoginKundeServlet() {
		super();
	}

	/**
	 * doGet leitet auf login.jsp weiter.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("login.jsp");
		view.forward(request, response);
	}

	/**
	 * doPost wird mittels Klick auf den Kunden-Login Button aufgerufen. 
	 * Die Methode nimmt username und password und prueft, ob der Kunde einen Account hat.
	 * Kunde wird eingeloggt, wenn Daten richtig sind. Kunde wird nicht eingeloggt, wenn Daten falsch sind.
	 * Entsprechende Fehlermeldungen und Success-Meldungen werden ausgegeben.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		KundenMgmt km = new KundenMgmt();
		
		if (km.login((String) request.getParameter("username"), (String) request.getParameter("password"))) {
			
			request.getServletContext().setAttribute("kunde", km.getKundeByUsername((String) request.getParameter("username")));
			request.setAttribute("message", "Login erfolgreich!");
			RequestDispatcher view = request.getRequestDispatcher("indexLoggedInAsKunde.jsp");
			view.forward(request, response);
			
		} else {
			
			request.setAttribute("message", "Login nicht moeglich, falsches Password oder Username!");
			RequestDispatcher view = request.getRequestDispatcher("loginKunde.jsp");
			view.forward(request, response);
			return;
		
		}

	}

}
