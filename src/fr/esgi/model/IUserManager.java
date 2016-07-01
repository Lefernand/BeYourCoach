package fr.esgi.model;

import java.sql.Date;
import java.util.List;

public interface IUserManager {
	public boolean checkLogin(String login);
	public boolean checkLoginWithPassword(String login, String password);
	boolean createUser(String login, String password, String role, String email);
	public boolean DeleteUser(String login);
	public User getUser(String login);
	public User getDefaultUser();
	public List<User> allUsers();
	boolean editProfile(String nom, String prenom, Integer taille, Integer objectif_poids, Date date_naissance, Integer id);
	boolean editCompte(String email, String password, Integer id);
	Integer getPoids(Integer id);
	public boolean setPoids(Integer id, Integer poids, Date dateDuJour);
	
}
