package fr.esgi.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.esgi.model.IUserManager;
import fr.esgi.model.User;
import fr.esgi.model.UserManagerDB;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet(name = "user-servlet", description = "Servlet handling user login", urlPatterns = { "/login", "/create",
		"/list", "/home", "/logout", "/profile", "/profileEdition" })
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// Enregistrer user en session
	private static final String USER_SESSION = "userSession";

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
		} else if (uri.contains("/create")) {
			this.create(request, response);
		} else if (uri.contains("/list")) {
			this.list(request, response);
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
		} else if (uri.contains("/home")) {
			this.home(request, response);
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
			}
		} else {
			System.out.println(login);
			request.getSession().setAttribute(UserServlet.USER_SESSION, this.userManager.getDefaultUser());
			response.sendRedirect("home");
			
			//request.setAttribute("errorMessage", "Utilisateur introuvable");
			//request.setAttribute("action", "login");
			//request.getRequestDispatcher("/WEB-INF/html/loginForm.jsp").forward(request, response);
		}

	}

	private void create(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		User user = (User) request.getSession().getAttribute(UserServlet.USER_SESSION);

		if (user == null) {
			response.sendRedirect("login");
			return;
		} else {
			System.out.println(user.getRole());
			if (user.getRole().equals("admin")) {
				final String login = request.getParameter("user");
				final String password = request.getParameter("password");
				final String role = request.getParameter("role");
				final String email = request.getParameter("email");

				if (login != null && password != null) {
					if (this.userManager.checkLogin(login)) {
						request.setAttribute("errorMessage", "User already exists. Please chose another");
					} else {
						this.userManager.createUser(login, password, role, email);
						request.setAttribute("success", "User succesfully created");
					}
				}
				request.setAttribute("action", "create");
				request.getRequestDispatcher("/WEB-INF/html/userForm.jsp").forward(request, response);
			} else {
				request.setAttribute("errorMessage", "Vous n'êtes pas un Administrateur");
				request.getRequestDispatcher("/WEB-INF/html/home.jsp").forward(request, response);
			}
		}

	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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
		request.getSession().removeAttribute(UserServlet.USER_SESSION);
		response.sendRedirect("login");
	}

	private void home(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		User user = (User) request.getSession().getAttribute(UserServlet.USER_SESSION);
		if (user == null) {
			response.sendRedirect("login");
			return;
		}

		Integer poids_journalier = this.userManager.getPoids(user.getId());

		System.out.println("user : " + user.getId());
		System.out.println("je set le poids du jour avec la valueur :" + poids_journalier);

		if (poids_journalier == null) {
			System.out.println("on affiche le modal");
			request.setAttribute("booleanPoids", true);
		} else {
			System.out.println("on affiche pas le modal un poids a déja été rentré");
			request.setAttribute("booleanPoids", false);
			request.setAttribute("poidDuJour", poids_journalier);
		}

		if (request.getParameter("typeAction") != null) {
			System.out.println("edit Poids détecté" + request.getParameter("poids"));
			this.userManager.setPoids(user.getId(), Integer.parseInt(request.getParameter("poids")),
					java.sql.Date.valueOf(java.time.LocalDate.now()));
		}

		request.getRequestDispatcher("/WEB-INF/html/home.jsp").forward(request, response);

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

		if (request.getParameter("typeAction") == null) {
			if (user == null) {
				response.sendRedirect("login");
				return;
			} else {
				request.setAttribute("user", user);
				request.setAttribute("action", "profileEdition");
				request.getRequestDispatcher("/WEB-INF/html/editProfile.jsp").forward(request, response);
			}
		} else {
			if (user == null) {
				response.sendRedirect("login");
				return;
			} else {
				if (request.getParameter("typeAction").equals("profile")) {
					Integer id = Integer.parseInt(request.getParameter("id"));
					String nom = request.getParameter("nom");
					String prenom = request.getParameter("prenom");
					Integer objectif_poids = Integer.parseInt(request.getParameter("objectif_poids"));
					Integer taille = Integer.parseInt(request.getParameter("taille"));

					SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-mm-dd");
					java.util.Date date = sdf1.parse(request.getParameter("date_naissance"));
					java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());

					this.userManager.editProfile(nom, prenom, taille, objectif_poids, sqlStartDate, id);
					request.setAttribute("infoMessage", "Vos informations de profils ont été modifié");
					request.getSession().setAttribute(UserServlet.USER_SESSION,
							this.userManager.getUser(user.getLogin()));

					request.setAttribute("user", user);
					request.setAttribute("action", "profileEdition");
					request.getRequestDispatcher("/WEB-INF/html/editProfile.jsp").forward(request, response);
				} else if (request.getParameter("typeAction").equals("compte")) {
					Integer id = Integer.parseInt(request.getParameter("id"));
					String email = request.getParameter("email");
					String password = request.getParameter("password");

					this.userManager.editCompte(email, password, id);

					request.setAttribute("infoMessage", "Vos informations de compte ont été modifié");
					request.getSession().setAttribute(UserServlet.USER_SESSION,
							this.userManager.getUser(user.getLogin()));

					request.setAttribute("user", user);
					request.setAttribute("action", "profileEdition");
					request.getRequestDispatcher("/WEB-INF/html/editProfile.jsp").forward(request, response);
				} else {
					request.setAttribute("user", user);
					request.setAttribute("action", "profileEdition");
					request.getRequestDispatcher("/WEB-INF/html/editProfile.jsp").forward(request, response);
				}
			}
		}

	}
}
