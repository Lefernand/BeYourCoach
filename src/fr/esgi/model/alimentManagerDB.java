package fr.esgi.model;

import java.awt.List;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class alimentManagerDB {
	
	private Connection connection;
	private ResultSet rs;

	public alimentManagerDB() {
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
	
	public boolean ajoutRepas(ArrayList<Aliment> listAliment) {
		PreparedStatement state = null;
		
			for (int i = 0; i < listAliment.size(); i++) {
				try {	
					String querySql ="INSERT INTO `esgi`.`aliments` (`id`, "
																 + "`nom`, "
																 + "`image`, "
																 + "`energie`, "
																 + "`id_user`, "
																 + "`time`, "
																 + "`date`, "
																 + "`quantite`) VALUES (NULL, "
																 				+ "?, "
																 				+ "?, "
																 				+ "?, "
																 				+ "?, "
																 				+ "?, "
																 				+ "?, "
																 				+ "?);";
				state = this.connection.prepareStatement(querySql);
				state.setString(1, listAliment.get(i).getNom());
				state.setString(2, listAliment.get(i).getPath_image());
				state.setInt(3, listAliment.get(i).getEnergie());
				state.setInt(4, listAliment.get(i).getUser_id());
				state.setString(5, listAliment.get(i).getTime());
				state.setDate(6, (Date) listAliment.get(i).getDateAjout());
				state.setInt(7, listAliment.get(i).getQuantite());
				
				state.executeUpdate();

				state.close();
				
				} catch (SQLException e) {
					e.printStackTrace();
					return false;
				}
			}
			return true;
	}
	
	public ArrayList<Repas> getRepasJournee(Integer id){
		System.out.println("je suis dans la fonctionnnnn nuuuuul");
		Repas historiqueRepasPetitDej = new Repas();
		Repas historiqueRepasDej = new Repas();
		Repas historiqueRepasDin = new Repas();
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Aliment> PDJ = new ArrayList<Aliment>();
		ArrayList<Aliment> DEJ = new ArrayList<Aliment>();
		ArrayList<Aliment> DIN = new ArrayList<Aliment>();
		try {
			String userSQL = "SELECT * FROM aliment WHERE id_user = ? ORDER BY date";
			stmt = (PreparedStatement) this.connection.prepareStatement(userSQL);

			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				
				Aliment aliment = null;
				
				String nom 		 	= rs.getString("nom");
				String path_image 	= rs.getString("image");
				String time			= rs.getString("time");
				Integer energie 	= rs.getInt("energie");
				Integer quantite	= rs.getInt("quantite");
				Date date 		 	= rs.getDate("date");
				
				System.out.println(nom+ " " +time+ " " +quantite);

				aliment = new Aliment(nom, path_image, time, date, energie, quantite, id);
				
				if (time.equals("PDJ")) {
					PDJ.add(aliment);
				} else if (time.equals("DEJ")) {
					DEJ.add(aliment);
				} else if (time.equals("DIN")) {
					DIN.add(aliment);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Integer totalPDJ = 0;
		Integer totalDEJ = 0;
		Integer totalDIN = 0;
		
		historiqueRepasPetitDej.setListAliment(PDJ);
		historiqueRepasPetitDej.setTime("PDJ");
		
		for (int i = 0; i < PDJ.size(); i++) {
			totalPDJ += PDJ.get(i).getEnergie();
		}
		historiqueRepasPetitDej.setEnergieTotal(totalPDJ);
		
		historiqueRepasDej.setListAliment(DEJ);
		historiqueRepasDej.setTime("DEJ");
		
		for (int i = 0; i < PDJ.size(); i++) {
			totalPDJ += PDJ.get(i).getEnergie();
		}
		historiqueRepasDej.setEnergieTotal(totalDEJ);
		
		historiqueRepasDin.setListAliment(DIN);
		historiqueRepasDin.setTime("DIN");
		for (int i = 0; i < PDJ.size(); i++) {
			totalPDJ += PDJ.get(i).getEnergie();
		}
		historiqueRepasDin.setEnergieTotal(totalDIN);
		
		ArrayList<Repas> result = new ArrayList<Repas>();
		
		result.add(historiqueRepasPetitDej);
		result.add(historiqueRepasDej);
		result.add(historiqueRepasDin);
		
		return result;
	}
}
