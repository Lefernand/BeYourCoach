package fr.esgi.servlets;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;


import fr.esgi.model.IUserManager;
import fr.esgi.model.User;
import fr.esgi.model.UserManagerDB;

/**
 * Servlet implementation class ajaxServlet
 */
@WebServlet(name = "ajax-servlet", description = "Servlet pour toutes les requêtes Ajax", urlPatterns = { "/getEvolution" })
public class ajaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private IUserManager userManager = new UserManagerDB();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ajaxServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String uri = request.getRequestURI();
		if (uri.contains("/getEvolution")) {
			this.getEvolution(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	
	protected void getEvolution(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String login = request.getSession().getAttribute("userSession").toString();
		User current_user = userManager.getUser(login);
		
		List<String> listPoid = userManager.getEvolution(current_user.getId());
		
		JSONObject data = new JSONObject();
		JSONObject legend = new JSONObject();
		JSONObject value = new JSONObject();
		
		int i = 0;
		
		for(final String recap: listPoid){
			String[] cut= recap.split("_");
			value.put(i, cut[0]);
			legend.put(i, cut[1]);
			i++;
		}
		data.put("legend", legend);
		data.put("value", value);
		
		response.setContentType("application/json");
		response.getWriter().write(data.toString());

	}
}
