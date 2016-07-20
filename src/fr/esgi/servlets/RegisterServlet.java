package fr.esgi.servlets;

import java.io.IOException;
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String uri = request.getRequestURI();
		
		if (uri.contains("/registerPage")) {
			this.registerPage(request, response);
		}
		if (uri.contains("/registerForm")) {
			this.registerForm(request, response);
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
		request.getRequestDispatcher("/WEB-INF/html/registerPage.jsp").forward(request, response);
		//response.sendRedirect("registerPage");
	}
	
	private void registerForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		final String login = request.getParameter("user");
		final String password = request.getParameter("password");
		final String email = request.getParameter("email");

		if (login != null && password != null) {
			if (this.userManager.checkLogin(login)) {
				request.setAttribute("errorMessage", "Désolé, cet utilisateur existe déja dans notre base de donnée");
				response.sendRedirect("registerPage");
			} else {
				this.userManager.createUser(login, password, "user",  email);
				//request.setAttribute("success", "Utilisateur enregistré");
				response.sendRedirect("login");
			}
		}
	}

}
