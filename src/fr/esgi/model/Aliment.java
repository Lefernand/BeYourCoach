package fr.esgi.model;

import java.util.Date;

public class Aliment {
	private String nom;
	private String path_image;
	private String time;
	private Date dateAjout;
	private Integer energie;
	private Integer quantite;
	private Integer user_id;
	
	public Aliment(String nom, String path_image, String time, Date dateAjout, Integer energie, Integer quantite,
			Integer user_id) {
		super();
		this.nom = nom;
		this.path_image = path_image;
		this.time = time;
		this.dateAjout = dateAjout;
		this.energie = energie;
		this.quantite = quantite;
		this.user_id = user_id;
	}
	
	
	
	public Aliment() {
		super();
	}



	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Integer getEnergie() {
		return energie;
	}
	public void setEnergie(Integer energie) {
		this.energie = energie;
	}
	public String getPath_image() {
		return path_image;
	}
	public void setPath_image(String path_image) {
		this.path_image = path_image;
	}
	
	public Integer getQuantite() {
		return quantite;
	}
	public void setQuantite(Integer quantite) {
		this.quantite = quantite;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Date getDateAjout() {
		return dateAjout;
	}
	public void setDateAjout(Date dateAjout) {
		this.dateAjout = dateAjout;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	

}
