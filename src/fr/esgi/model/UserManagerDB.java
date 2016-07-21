package fr.esgi.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class UserManagerDB implements IUserManager {

	private Connection connection;
	private ResultSet rs;

	public UserManagerDB() {

		try {
			this.connection = this.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 *
	 * Fonction de Connexion à la base de donnée.
	 *
	 */
	public Connection getConnection() throws Exception {
		String url = "jdbc:mysql://localhost:3306/esgi";
		String driver = "com.mysql.jdbc.Driver";
	    String username = "root";
	    String password = "root";

		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return null;
		}

		System.out.println("MySQL JDBC Driver Registered!");
		Connection connection = null;

		try {
			connection = DriverManager
			.getConnection(url, username, password);

		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return connection;
		}
		
		if(connection != null)
			System.out.println("Connection Done! Let's work");
		
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
			String userSql = "INSERT INTO `esgi`.`users` (`id`, "
					+ "`sexe`, "
					+ "`login`, "
					+ "`email`, "
					+ "`password`, "
					+ "`role`, "
					+ "`nom`, "
					+ "`prenom`, "
					+ "`int_taille`, "
					+ "`objectif_poids`, "
					+ "`date_naissance`, "
					+ "`date_creation`) VALUES (NULL, NULL, ?, ?, ?, ?, NULL, NULL, NULL, NULL, NULL, ?);";

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
	public boolean deleteUser(Integer id) {

		PreparedStatement state = null;
		int resultat = 0;

		try {
			String deleteUserSQL = "DELETE FROM `users` WHERE id = ?";
									
			state = (PreparedStatement) this.connection.prepareStatement(deleteUserSQL);
			state.setInt(1, id);
			
			state.executeUpdate();
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
				Boolean sexe = rs.getBoolean("sexe");

				user = new User(loginU, Password, role, email, id, nom, prenom, taille, obj_poids, date_naissance,
						date_creation, sexe);

				System.out.println(email);
				break;
			}

			rs.close();
			stmt.close();
		} catch (Exception e) {
			return user;
			//e.printStackTrace();
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
				Boolean sexe = rs.getBoolean("sexe");

				user = new User(loginU, Password, role, email, id, sexe);

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
			Integer id, Integer sexe) {
		PreparedStatement stmt = null;
		rs = null;
		try {
			String userSQL = "UPDATE `esgi`.`users` SET `sexe` = ?, "
									+ "`nom` = ?, "
									+ "`prenom` = ?, "
									+ "`int_taille` = ?, "
									+ "`objectif_poids` = ?, "
									+ "`date_naissance` = ? WHERE `users`.`id` = ?;";

			stmt = (PreparedStatement) this.connection.prepareStatement(userSQL);

			stmt.setInt(1, sexe);
			stmt.setString(2, nom);
			stmt.setString(3, prenom);
			stmt.setInt(4, taille);
			stmt.setInt(5, objectif_poids);
			stmt.setDate(6, date_naissance);
			stmt.setInt(7, id);
			

			System.out.println("je passe la !");

			System.out.println(id);
			System.out.println(nom);
			System.out.println(prenom);
			System.out.println(objectif_poids);
			System.out.println(date_naissance);
			System.out.println(sexe);

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
	public ArrayList<Float> getPoidsDuJour(Integer id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Float> result = new ArrayList<>();
		try {
			String userSQL = "SELECT * FROM suivi_poids WHERE id_user = ? AND date = ? LIMIT 1;";
			stmt = (PreparedStatement) this.connection.prepareStatement(userSQL);

			stmt.setInt(1, id);
			stmt.setDate(2, java.sql.Date.valueOf(java.time.LocalDate.now()));

			rs = stmt.executeQuery();

			Float poids = null;
			Float IMC = null;
			Float MG = null;
			
			while (rs.next()) {
					poids = rs.getFloat("poids");
					IMC = rs.getFloat("IMC");
					MG = rs.getFloat("MG");
				}
				
			
			result.add(poids);
			result.add(IMC);
			result.add(MG);

			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/*
	 * Fonction chack if le poids du jour existe
	 * 
	 */
	@Override
	public String isPoidsDuJour(Integer id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			String userSQL = "SELECT * FROM suivi_poids WHERE id_user = ? AND date = ? LIMIT 1;";
			stmt = (PreparedStatement) this.connection.prepareStatement(userSQL);

			stmt.setInt(1, id);
			stmt.setDate(2, java.sql.Date.valueOf(java.time.LocalDate.now()));

			rs = stmt.executeQuery();
			
			if (!rs.next() ) {
			    return "pasok";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return "pasok";
		}
		return "ok";
	}

	/*
	 * Fonction set le poids du jour pour un user
	 * 
	 */
	public boolean setPoids(Integer id, Integer poids, Integer taille, Date dateDuJour, Boolean sexe, Date date_naissance) {
		PreparedStatement state = null;
		int result = 0;
		try {
			//calcul IMC
			
			Float taile_use = (float) (taille*0.01);
			
			Float IMC = (float) (poids / (taile_use*taile_use));
			//calcul Masse graisseuse
			
			GregorianCalendar birth = new GregorianCalendar();
		    birth.setTime(date_naissance);

		    GregorianCalendar now = new GregorianCalendar();

		    Integer age = now.get(GregorianCalendar.YEAR) - birth.get(GregorianCalendar.YEAR);

		    
		    Integer sexe_use;
		    if (sexe) {
				sexe_use = 1;
			}else{
				sexe_use = 0;
			}
			
			Float IMG = (float) ((1.20*IMC)+(0.23*age)-(10.8*sexe_use)-5.4);
			
			System.out.println("------PASS dans la fonction setPOIDS-----");
			System.out.println(age + " ans");
			System.out.println(poids + " kg");
			System.out.println(taille + " cm");
		    System.out.println("IMC = "+IMC);
		    System.out.println("MG = "+IMG + " %");
		    System.out.println("-----------");
			
			String userSql = "INSERT INTO `esgi`.`suivi_poids` (`id`, "
															 + "`id_user`, "
															 + "`poids`, "
															 + "`IMC`, "
															 + "`MG`, "
															 + "`date`) VALUES (NULL, "
																				+ "?,"
																				+ "?, "
																				+ "?, "
																				+ "?, "
																				+ "?);";

			state = this.connection.prepareStatement(userSql);
			state.setInt(1, id);
			state.setInt(2, poids);
			state.setFloat(3, IMC);
			state.setFloat(4, IMG);
			state.setDate(5, dateDuJour);

			result = state.executeUpdate();

			state.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result == 1;
	}
	
	/*
	 * Fonction set Role Admin
	 * 
	 */
	@Override
	public boolean setRoleAdmin(Integer id) {
		PreparedStatement stmt = null;
		try {
			String userSQL = "UPDATE `esgi`.`users` SET `role` = ? WHERE `users`.`id` = ?;";
							  
			stmt = (PreparedStatement) this.connection.prepareStatement(userSQL);

			stmt.setString(1, "admin");
			stmt.setInt(2, id);

			stmt.executeUpdate();

			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}
	
	/*
	 * Fonction set Role User
	 * 
	 */
	@Override
	public boolean setRoleUser(Integer id) {
		PreparedStatement stmt = null;
		try {
			System.out.println(id);
			
			String userSQL = "UPDATE `esgi`.`users` SET `role` = ? WHERE `users`.`id` = ?;";
			System.out.println(userSQL);	  
			stmt = (PreparedStatement) this.connection.prepareStatement(userSQL);

			stmt.setString(1, "user");
			stmt.setInt(2, id);

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
	public ArrayList<PerfUser> getHistoriquePoids(Integer id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<PerfUser> list_PerfUser = new ArrayList<PerfUser>();
		try {
			String userSQL = "SELECT * FROM suivi_poids WHERE id_user = ? ORDER BY date";
			stmt = (PreparedStatement) this.connection.prepareStatement(userSQL);

			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				
				PerfUser Puser = null;

				Integer id_perf = rs.getInt("id");
				Integer id_user = rs.getInt("id_user");
				Float poids = rs.getFloat("poids");
				Float iMC = rs.getFloat("IMC");
				Float mG = rs.getFloat("MG");
				Date date = rs.getDate("date");
				
				System.out.println(poids+ " " +iMC+ " " +date);

				Puser = new PerfUser(id_perf, id_user, poids, iMC, mG, date);

				list_PerfUser.add(Puser);

			}
			return list_PerfUser;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list_PerfUser;
	}

}
