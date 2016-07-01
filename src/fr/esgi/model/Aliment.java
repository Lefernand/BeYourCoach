package fr.esgi.model;

public class Aliment {
	private String nom;
	private String path_image;
	private Integer energie;
	
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

}
