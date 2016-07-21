package fr.esgi.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.esgi.model.IUserManager;
import fr.esgi.model.UserManagerDB;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet(name = "register-servlet", description = "Servlet register user", urlPatterns = { "/registerPage", "/registerForm"})
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	private IUserManager userManager = new UserManagerDB();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		final String uri = request.getRequestURI();
		
		if (uri.contains("/registerPage")) {
			this.registerPage(request, response);
		}
		if (uri.contains("/registerForm")) {
			try {
				this.registerForm(request, response);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void registerPage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//on renvoit sur la page registerPage.jps
		request.setAttribute("action", "registerForm");
		request.getRequestDispatcher("/WEB-INF/html/registerPage.jsp").forward(request, response);
	}
	
	private void registerForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ParseException {
		
		final String login = request.getParameter("login");
		final String nom = request.getParameter("nom");
		final String prenom = request.getParameter("prenom");
		final Integer taille = Integer.parseInt(request.getParameter("taille"));
		final Integer objectif_poids = Integer.parseInt(request.getParameter("objectif_poids"));
		final String date_naissance = request.getParameter("date_naissance");
		final Boolean sexe = Boolean.parseBoolean(request.getParameter("sexe"));
		final String email = request.getParameter("email");
		final String password = request.getParameter("password");
		final String password2 = request.getParameter("password2");
		

		if (nom != null && prenom != null && taille != 0 && objectif_poids != 0 && date_naissance != null &&  email != null && password != null && password2 != null) {

			if (this.userManager.checkLogin(login)) {
				request.setAttribute("errorMessage", "Désolé, cet utilisateur existe déja dans notre base de donnée");
				request.setAttribute("action", "registerForm");
				request.getRequestDispatcher("/WEB-INF/html/registerPage.jsp").forward(request, response);
			} else {
				if(password.equals(password2)){
					if (this.userManager.createUser(login, nom, prenom, taille, objectif_poids, date_naissance, sexe, email, password)) {
						request.setAttribute("success", "Inscription réussi");
						request.setAttribute("action", "login");
						request.getRequestDispatcher("/WEB-INF/html/loginForm.jsp").forward(request, response);
					} else {
						request.setAttribute("errorMessage", "Erreur lors de l'inscription");
						request.setAttribute("action", "registerForm");
						request.getRequestDispatcher("/WEB-INF/html/registerPage.jsp").forward(request, response);
					}
				} else {
					request.setAttribute("errorMessage", "Password différent"+ password + password2);
					request.setAttribute("action", "registerForm");
					request.getRequestDispatcher("/WEB-INF/html/registerPage.jsp").forward(request, response);
				}
			}
		} else {

			request.setAttribute("errorMessage", "Merci de remplir tous les champs");
			request.setAttribute("action", "registerForm");
			request.getRequestDispatcher("/WEB-INF/html/registerPage.jsp").forward(request, response);
		}
	}

}
