package fr.esgi.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public interface IUserManager {
	public boolean checkLogin(String login);
	public boolean checkLoginWithPassword(String login, String password);
	boolean createUser(String login, String password, String role, String email);
	public boolean deleteUser(Integer id);
	public User getUser(String login);
	public List<User> allUsers();
	boolean editCompte(String email, String password, Integer id);
	ArrayList<Float> getPoidsDuJour(Integer id);
	public boolean setPoids(Integer id, Integer poids, Integer taille, Date dateDuJour, Boolean sexe, Date date_naissance);
	boolean editProfile(String nom, String prenom, Integer taille, Integer objectif_poids, Date date_naissance,
			Integer id, Integer sexe);
	String isPoidsDuJour(Integer id);
	boolean setRoleAdmin(Integer id);
	boolean setRoleUser(Integer id);
	List<PerfUser> getHistoriquePoids(Integer id);
	
}
