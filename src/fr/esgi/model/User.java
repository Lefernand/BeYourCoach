package fr.esgi.model;

import java.sql.Date;

public class User {
	//user variable
	private Integer id;
	private String login;
	private String email;
	private String password;
	private String role;
	
	//profile variable
	private String prenom;
	private String nom;
	private Integer taille;
	private Integer objectif_poids;
	private Boolean sexe;
	
	private Date date_naissance;
	private Date date_creation;
	
	public User(){	
	}
	
	//contructeur createUser
	public User(String login, String password, String role, String email, Integer id, Boolean sexe) {
		this.id 		= id;
		this.login 		= login;
		this.password 	= password;
		this.role 		= role;
		this.email 		= email;
		
		this.prenom 		= null;
		this.nom 			= null;
		this.taille			= null;
		this.date_naissance	= null;
		this.date_creation 	= null;
		this.objectif_poids	= null;
		this.sexe 			= null;

	}
	
	//contructeur getUser
	public User(String login, String password, String role, String email, Integer id, String nom, String prenom, Integer taille, Integer obj_poids, Date date_naissance, Date date_creation, Boolean sexe){
		this.id 		= id;
		this.login 		= login;
		this.password 	= password;
		this.role 		= role;
		this.email 		= email;
		
		this.prenom 		= prenom;
		this.nom 			= nom;
		this.taille			= taille;
		this.date_naissance	= date_naissance;
		this.date_creation 	= null;
		this.objectif_poids	= obj_poids;
		this.date_creation	= date_creation;
		this.sexe			= sexe;
	}		
	

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return getLogin();
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Date getDate_creation() {
		return date_creation;
	}

	public void setDate_creation(Date date_creation) {
		this.date_creation = date_creation;
	}

	public Date getDate_naissance() {
		return date_naissance;
	}

	public void setDate_naissance(Date date_naissance) {
		this.date_naissance = date_naissance;
	}

	public Integer getTaille() {
		return taille;
	}

	public void setTaille(Integer taille) {
		this.taille = taille;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Integer getObjectif_poids() {
		return objectif_poids;
	}

	public void setObjectif_poids(Integer objectif_poids) {
		this.objectif_poids = objectif_poids;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Boolean getSexe() {
		return sexe;
	}

	public void setSexe(Boolean sexe) {
		this.sexe = sexe;
	}
}
