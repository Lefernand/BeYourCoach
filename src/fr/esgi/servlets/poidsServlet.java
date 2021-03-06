package fr.esgi.servlets;

import java.awt.List;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.esgi.model.IUserManager;
import fr.esgi.model.PerfUser;
import fr.esgi.model.User;
import fr.esgi.model.UserManagerDB;

/**
 * Servlet implementation class poidsServlet
 */
@WebServlet(name = "poids-servlet", description = "Servlet poids ", urlPatterns = { "/historiquePoids", "/updatePoids", "/deletePoids"})
public class poidsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private IUserManager userManager = new UserManagerDB();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public poidsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String uri = request.getRequestURI();
		
		if (uri.contains("/historiquePoids")) {
			this.historiquePoids(request, response);
		} else if (uri.contains("/updatePoids")) {
			this.updatePoids(request, response);
		} else if (uri.contains("/deletePoids")) {
			this.deletePoids(request, response);
		} 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void historiquePoids(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//on renvoit sur la page registerPage.jps
		User user = (User) request.getSession().getAttribute(UserServlet.USER_SESSION);
		System.out.println("id = "+user.getId() );
		ArrayList<PerfUser> myList = (ArrayList<PerfUser>) this.userManager.getHistoriquePoids(user.getId());  
		request.setAttribute("myPoidsList", myList);		
		
		request.getRequestDispatcher("/WEB-INF/html/historiquePoids.jsp").forward(request, response);
		//response.sendRedirect("registerPage");
	}
	
	private void updatePoids(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//on renvoit sur la page registerPage.jps
		User user = (User) request.getSession().getAttribute(UserServlet.USER_SESSION);
		
		Float poids = (float) Integer.parseInt(request.getParameter("poids"));
		Integer idPoids = Integer.parseInt(request.getParameter("idPoids"));
		
		System.out.println(poids + " nouveau poids");
		System.out.println(idPoids + " id du poids");
		
		this.userManager.updatePoids(idPoids, poids, user.getTaille(), java.sql.Date.valueOf(java.time.LocalDate.now()), user.getSexe(), user.getDate_naissance());
		
		this.historiquePoids(request, response);
	}
	
	private void deletePoids(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		this.userManager.deletePoids(Integer.parseInt(request.getParameter("id_poids")));
		
		this.historiquePoids(request, response);
	}

}
