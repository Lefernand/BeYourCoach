package fr.esgi.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class UserManagerDB implements IUserManager {

	private Connection connection;
	private ResultSet rs;

	public UserManagerDB() {

		try {
			this.connection = this.getConnection();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 *
	 * Fonction de Connexion à la base de donnée.
	 *
	 */
	public Connection getConnection()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		try {
			String url = "jdbc:mysql://localhost:3306/esgi";
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			this.connection = DriverManager.getConnection(url, "root", "root");
		} catch (Exception e) {
			System.err.println("Erreur de connexion à la base de donnée");
			e.printStackTrace();
		}

		return connection;
	}

	/*
	 * Fonction Check if login exist
	 * 
	 */
	@Override
	public boolean checkLogin(String login) {
		return (this.getUser(login) != null);
	}

	/*
	 * Fonction Check user and password correct
	 * 
	 */
	@Override
	public boolean checkLoginWithPassword(String login, String password) {
		User u = this.getUser(login);
		if (u != null) {
			return u.getPassword().equals(password);
		}
		return false;
	}

	/*
	 * Fonction Create User in database
	 * 
	 */
	@Override
	public boolean createUser(String login, String password, String role, String email) {
		PreparedStatement state = null;
		int result = 0;
		try {
			String userSql = "INSERT INTO `esgi`.`users` (`id`, " + "`login`, " + "`email`, " + "`password`, "
					+ "`role`, " + "`nom`, " + "`prenom`, " + "`int_taille`, " + "`date_naissance`, "
					+ "`date_creation`) VALUES (NULL, ?, ?, ?, ?, NULL, NULL, NULL, NULL, ?);";

			state = this.connection.prepareStatement(userSql);
			state.setString(1, login);
			state.setString(2, email);
			state.setString(3, password);
			state.setString(4, role);

			state.setDate(5, java.sql.Date.valueOf(java.time.LocalDate.now()));

			result = state.executeUpdate();

			state.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result == 1;
	}

	/*
	 * Fonction Delete User in database
	 * 
	 */
	@Override
	public boolean DeleteUser(String login) {

		PreparedStatement state = null;
		int resultat = 0;

		try {
			String deleteUserSQL = "DELETE FROM users WHERE login = ?";
			state = (PreparedStatement) this.connection.prepareStatement(deleteUserSQL);
			state.setString(1, login);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultat == 1;
	}

	/*
	 * Fonction Get un User par son login
	 * 
	 */
	@Override
	public User getUser(String login) {
		User user = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			String userSQL = "SELECT * FROM users WHERE login = ?";
			stmt = (PreparedStatement) this.connection.prepareStatement(userSQL);
			stmt.setString(1, login);

			rs = stmt.executeQuery();
			while (rs.next()) {
				Integer id = rs.getInt("id");
				String loginU = rs.getString("login");
				String Password = rs.getString("password");
				String role = rs.getString("role");
				String email = rs.getString("email");

				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				Integer taille = rs.getInt("int_taille");
				Integer obj_poids = rs.getInt("objectif_poids");
				Date date_naissance = rs.getDate("date_naissance");
				Date date_creation = rs.getDate("date_creation");

				user = new User(loginU, Password, role, email, id, nom, prenom, taille, obj_poids, date_naissance,
						date_creation);

				break;
			}

			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}

	@Override
	public List<User> allUsers() {

		ArrayList<User> allUser = new ArrayList<>();

		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			String userSQL = "SELECT * FROM users";
			stmt = (PreparedStatement) this.connection.prepareStatement(userSQL);

			rs = stmt.executeQuery();

			while (rs.next()) {

				User user = null;

				Integer id = rs.getInt("id");
				String loginU = rs.getString("login");
				String Password = rs.getString("password");
				String role = rs.getString("role");
				String email = rs.getString("email");

				user = new User(loginU, Password, role, email, id);

				allUser.add(user);

			}

			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return allUser;

	}

	/*
	 * Fonction edit un profile utilisateur
	 * 
	 */
	@Override
	public boolean editProfile(String nom, String prenom, Integer taille, Integer objectif_poids, Date date_naissance,
			Integer id) {
		PreparedStatement stmt = null;
		rs = null;
		try {
			String userSQL = "UPDATE `esgi`.`users` SET `nom` = ?, " + "`prenom` = ?, " + "`int_taille` = ?, "
					+ "`objectif_poids` = ?, " + "`date_naissance` = ? " + "WHERE `users`.`id` = ?;";

			stmt = (PreparedStatement) this.connection.prepareStatement(userSQL);

			stmt.setString(1, nom);
			stmt.setString(2, prenom);
			stmt.setInt(3, taille);
			stmt.setInt(4, objectif_poids);
			stmt.setDate(5, date_naissance);
			stmt.setInt(6, id);

			System.out.println("je passe la !");

			System.out.println(id);
			System.out.println(nom);
			System.out.println(prenom);
			System.out.println(objectif_poids);
			System.out.println(date_naissance);

			stmt.executeUpdate();

			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	@Override
	public boolean editCompte(String email, String password, Integer id) {
		PreparedStatement stmt = null;
		try {
			String userSQL = "UPDATE `esgi`.`users` SET `email` = ?, " + "`password` = ? " + "WHERE `users`.`id` = ?;";

			stmt = (PreparedStatement) this.connection.prepareStatement(userSQL);

			stmt.setString(1, email);
			stmt.setString(2, password);
			stmt.setInt(3, id);

			stmt.executeUpdate();

			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	/*
	 * Fonction Get le poids du jour pour un user
	 * 
	 */
	@Override
	public Integer getPoids(Integer id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			String userSQL = "SELECT * FROM suivi_poids WHERE id_user = ? AND date = ?;";
			stmt = (PreparedStatement) this.connection.prepareStatement(userSQL);

			stmt.setInt(1, id);
			stmt.setDate(2, java.sql.Date.valueOf(java.time.LocalDate.now()));

			rs = stmt.executeQuery();
			System.out.println("yoloooooo");
			System.out.println("yoloooooo :  " + stmt);

			Integer poids = null;

			while (rs.next()) {
				poids = rs.getInt("poids");
			}

			System.out.println("yoloooooo les poids est:  " + poids);
			return poids;

			// if (rs != null) {
			// return rs.getInt("poids");
			// }else{
			// return null;
			// }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	/*
	 * Fonction set le poids du jour pour un user
	 * 
	 */
	public boolean setPoids(Integer id, Integer poids, Date dateDuJour) {
		PreparedStatement state = null;
		int result = 0;
		try {
			String userSql = "INSERT INTO `esgi`.`suivi_poids` (`id`, " + "`id_user`, " + "`poids`, "
					+ "`date`) VALUES (NULL, ?, ?, ?);";

			state = this.connection.prepareStatement(userSql);
			state.setInt(1, id);
			state.setInt(2, poids);
			state.setDate(3, dateDuJour);

			result = state.executeUpdate();

			state.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result == 1;
	}

}
