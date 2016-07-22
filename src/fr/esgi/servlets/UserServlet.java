package fr.esgi.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.esgi.model.IUserManager;
import fr.esgi.model.User;
import fr.esgi.model.UserManagerDB;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet(name = "user-servlet", description = "Servlet handling user login", urlPatterns = { "/login", "/create",
		"/admin", "/home", "/logout", "/profile", "/profileEdition", "/ajoutRepas", "/updateRoleAdmin", "/updateRoleUser", "/deleteUser"})
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// Enregistrer user en session
	static final String USER_SESSION = "userSession";
	static final String INFO_SESSION = "infoDuJour";

	// private IUserManager userManager = new UserManagerInMemory();

	private IUserManager userManager = new UserManagerDB();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		final String uri = request.getRequestURI();
		if (uri.contains("/login")) {
			this.login(request, response);
		} else if (uri.contains("/admin")) {
			this.admin(request, response);
		} else if (uri.contains("/logout")) {
			this.logout(request, response);
		} else if (uri.contains("/profileEdition")) {
				try {
					this.editProfile(request, response);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		} else if (uri.contains("/profile")) {
			this.profile(request, response);
		} else if (uri.contains("/ajoutRepas")) {
			this.ajoutRepas(request, response);
		} else if (uri.contains("/setRepas")) {
			this.ajoutRepas(request, response);
		} else if (uri.contains("/home")) {
			this.home(request, response);
		} else if (uri.contains("/updateRoleUser")) {
			this.updateRoleUser(request, response);
		} else if (uri.contains("/updateRoleAdmin")) {
			this.updateRoleAdmin(request, response);
		} else if (uri.contains("/deleteUser")) {
			this.deleteUser(request, response);
		} else {
			this.home(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		final String login = request.getParameter("user");
		final String password = request.getParameter("password");
		System.out.println(request.getParameterNames());
		if (login == null || password == null) {
			request.setAttribute("action", "login");
			request.getRequestDispatcher("/WEB-INF/html/loginForm.jsp").forward(request, response);
		} else if (this.userManager.checkLogin(login)) {			
			if (this.userManager.checkLoginWithPassword(login, password)) {
				request.getSession().setAttribute(UserServlet.USER_SESSION, this.userManager.getUser(login));
				response.sendRedirect("home");
				return;
			} else {
				request.setAttribute("errorMessage", "Password incorrect");
				request.setAttribute("action", "login");
				request.getRequestDispatcher("/WEB-INF/html/loginForm.jsp").forward(request, response);
				//response.sendRedirect("login");
			}
		} else {
			//System.out.println("Utilisateur introuvable");
			request.setAttribute("errorMessage", "Utilisateur introuvable");
			request.setAttribute("action", "login");
			request.getRequestDispatcher("/WEB-INF/html/loginForm.jsp").forward(request, response);
			//response.sendRedirect("login");
		}

	}

	private void admin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String pageType = request.getParameter("output");
		User user = (User) request.getSession().getAttribute(UserServlet.USER_SESSION);

		if (pageType == null || !pageType.equals("json")) {
			pageType = "html";
		}

		if (user == null) {
			response.sendRedirect("login");
			return;
		} else {
			System.out.println(user.getRole());
			if (user.getRole().equals("admin")) {
				request.setAttribute("title", "List of all users");
				request.setAttribute("userList", this.userManager.allUsers());
				request.getRequestDispatcher("/WEB-INF/html/userList.jsp").forward(request, response);
			} else {
				request.setAttribute("errorMessage", "Vous n'êtes pas un Administrateur");
				request.getRequestDispatcher("/WEB-INF/html/home.jsp").forward(request, response);
			}
		}
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try
		{
			HttpSession session=request.getSession(false);
			if(session!=null)
			{
				Enumeration<String> attributeNames=session.getAttributeNames();
				while(attributeNames.hasMoreElements()){
					String sAttribute=attributeNames.nextElement().toString();
					
					System.out.println(request.getRequestURL()+" Removing Session Attribute : "+sAttribute);
					session.removeAttribute(sAttribute);
		
				}
			}
		}catch(Exception e) { System.err.println(e);}
		response.sendRedirect("login");
	}

	private void home(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		User user = (User) request.getSession().getAttribute(UserServlet.USER_SESSION);		
		//String isPoids = this.userManager.isPoidsDuJour(user.getId());
		//request.getSession().setAttribute("infoDuJour", isPoids);
		
		//System.out.println("isPoids : "+isPoids);
		
		if (user != null) {
			if (this.userManager.isPoidsDuJour(user.getId()) != null) {
				request.getSession().setAttribute("infoDuJour", this.userManager.isPoidsDuJour(user.getId()));
				request.getSession().setAttribute("poidsDuJour", this.userManager.getPoidsDuJour(user.getId()).get(0));
				request.getSession().setAttribute("IMCDuJour", this.userManager.getPoidsDuJour(user.getId()).get(1));
				request.getSession().setAttribute("MGDuJour", this.userManager.getPoidsDuJour(user.getId()).get(2));
				
				System.out.println("-----SET POIDS------");
				System.out.println("user : " + user.getId());
				System.out.println("poids du jour" + request.getAttribute("poidsDuJour") + " kg");
				System.out.println("IMC du jour :" + request.getAttribute("IMCDuJour"));
				System.out.println("MG du jour :" + request.getAttribute("MGDuJour") + " %");
				System.out.println("-----------");
			} else {
				System.out.println("-----SET POIDS------");
				System.out.println("on affiche le modal aucun poids n'a été rentré");
				System.out.println("-----------");
			}

			if (request.getParameter("typeAction") != null) {
				
				System.out.println("ajout Poids du jour détecté " + request.getParameter("poids"));
				
				this.userManager.setPoids(user.getId(), 
						Integer.parseInt(request.getParameter("poids")), 
						user.getTaille(),
						java.sql.Date.valueOf(java.time.LocalDate.now()),
						user.getSexe(),
						user.getDate_naissance());
				
				request.getSession().setAttribute("infoDuJour", this.userManager.isPoidsDuJour(user.getId()));
				request.getSession().setAttribute("poidsDuJour",  this.userManager.getPoidsDuJour(user.getId()).get(0));
				request.getSession().setAttribute("IMCDuJour",  this.userManager.getPoidsDuJour(user.getId()).get(1));
				request.getSession().setAttribute("MGDuJour",  this.userManager.getPoidsDuJour(user.getId()).get(2));
			}

			request.getRequestDispatcher("/WEB-INF/html/home.jsp").forward(request, response);
		}else{
			response.sendRedirect("login");
		}
		
		

	}

	private void profile(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		User user = (User) request.getSession().getAttribute(UserServlet.USER_SESSION);
		if (user == null) {
			response.sendRedirect("login");
			return;
		} else {
			request.setAttribute("user", user);
			request.setAttribute("action", "profil");
			request.getRequestDispatcher("/WEB-INF/html/profile.jsp").forward(request, response);
		}

	}

	private void editProfile(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, ParseException {
		
		User user = (User) request.getSession().getAttribute(UserServlet.USER_SESSION);
		

		System.out.println(request.getParameter("login"));
		
		if (user == null) {
			response.sendRedirect("login");
			return;
		}else if (request.getParameter("login") != null) {
			

			request.setAttribute("user", user);
			request.setAttribute("action", "profileEdition");

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
	
					if(password.equals(password2)){
						if (this.userManager.editProfile(user.getId(), login, nom, prenom, taille, objectif_poids, date_naissance, sexe, email, password)) {
							request.setAttribute("success", "Modification réussie");
							request.getSession().setAttribute(UserServlet.USER_SESSION,
									this.userManager.getUser(user.getLogin()));
							request.getRequestDispatcher("/WEB-INF/html/editProfile.jsp").forward(request, response);
						} else {
							request.setAttribute("errorMessage", "Erreur lors de l'inscription");
							request.setAttribute("action", "profileEdition");
							request.getRequestDispatcher("/WEB-INF/html/editProfile.jsp").forward(request, response);
						}
					} else {
						request.setAttribute("errorMessage", "Password différent"+ password + password2);
						request.setAttribute("action", "profileEdition");
						request.getRequestDispatcher("/WEB-INF/html/editProfile.jsp").forward(request, response);
					}
			} else {
	
				request.setAttribute("errorMessage", "Merci de remplir tous les champs");
				request.getRequestDispatcher("/WEB-INF/html/editProfile.jsp").forward(request, response);
			}
		} else {
			request.setAttribute("user", user);
			request.setAttribute("action", "profileEdition");

			request.getRequestDispatcher("/WEB-INF/html/editProfile.jsp").forward(request, response);

		}
	}
	
	private void ajoutRepas(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException{
		User user = (User) request.getSession().getAttribute(UserServlet.USER_SESSION);
		if (user == null) {
			response.sendRedirect("login");
			return;
		} else {
			request.setAttribute("user", user);
			request.setAttribute("action", "profil");
			request.getRequestDispatcher("/WEB-INF/html/ajoutRepas.jsp").forward(request, response);
		}

	}
	
	private void updateRoleAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		final int id = Integer.parseInt(request.getParameter("id_user"));
		System.out.println("update role admin " + id);
		this.userManager.setRoleAdmin(id);
		response.sendRedirect("admin");
	}
	
	private void updateRoleUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		final int id = Integer.parseInt(request.getParameter("id_user"));
		System.out.println("update role user " + id);
		this.userManager.setRoleUser(id);
		response.sendRedirect("admin");
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		final int id = Integer.parseInt(request.getParameter("id_user"));
		
		this.userManager.deleteUser(id);
		
		response.sendRedirect("admin");
	}
}
