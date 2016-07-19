package fr.esgi.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.esgi.model.Aliment;
import fr.esgi.model.IUserManager;
import fr.esgi.model.User;
import fr.esgi.model.UserManagerDB;
import fr.esgi.model.alimentManagerDB;

/**
 * Servlet implementation class RepasServlet
 */
@WebServlet(name = "repas-servlet", description = "Servlet register user", urlPatterns = { "/ajoutRepas", "/deleteRepas"})

public class RepasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private IUserManager userManager = new UserManagerDB();
	private alimentManagerDB repasManager = new alimentManagerDB();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RepasServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		final String uri = request.getRequestURI();
		
		if (uri.contains("/ajoutRepas")) {
			this.ajoutRepas(request, response);
		}
		if (uri.contains("/deleteRepas")) {
			this.deleteRepas(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void ajoutRepas(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Map<String, String[]> parameters = request.getParameterMap();
		User user = (User) request.getSession().getAttribute(UserServlet.USER_SESSION);
		
		System.out.println(parameters.size());
		
		if (parameters.size() <= 1) {
			response.sendRedirect("ajoutPetitDej");
		}else{
			ArrayList<Aliment> repas = new ArrayList<Aliment>();
			String time = request.getParameter("time");
			
			Integer nb_aliment = (parameters.size() - 1)/4;
			Integer count = 1;
			
			System.out.println("il y a " + nb_aliment +" aliemnts dans ce repas");
			System.out.println((String) parameters.get("nom-1")[0]);
			System.out.println((String) parameters.get("nom-2")[0]);
			
			for (int i = 0; i < nb_aliment; i++) {
				Aliment aliment = new Aliment((String) parameters.get("nom-"+count)[0], 
											  (String) parameters.get("image-"+count)[0], 
											  time, 
											  java.sql.Date.valueOf(java.time.LocalDate.now()), 
											  Integer.parseInt(parameters.get("energie-"+count)[0]),
											  Integer.parseInt(parameters.get("quantite-"+count)[0]), 
											  user.getId());
				
				repas.add(aliment);
				count++;
			}
			this.repasManager.ajoutRepas(repas);
			System.out.println(repas);
			response.sendRedirect("home");
		}
		
		
	}
	
	private void deleteRepas(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//on renvoit sur la page registerPage.jps
		request.getRequestDispatcher("/WEB-INF/html/registerPage.jsp").forward(request, response);
		//response.sendRedirect("registerPage");
	}

}