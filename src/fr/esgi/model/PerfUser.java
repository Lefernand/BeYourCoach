package fr.esgi.model;

import java.util.Date;

public class PerfUser {
	private Integer id;
	private Integer id_user;
	private Float poids;
	private Float IMC;
	private Float MG;
	private Date date;
	
	public PerfUser(Integer id, Integer id_user, Float poids, Float iMC, Float mG, Date date) {
		super();
		this.id = id;
		this.id_user = id_user;
		this.poids = poids;
		IMC = iMC;
		MG = mG;
		this.date = date;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId_user() {
		return id_user;
	}

	public void setId_user(Integer id_user) {
		this.id_user = id_user;
	}

	public Float getPoids() {
		return poids;
	}

	public void setPoids(Float poids) {
		this.poids = poids;
	}

	public Float getIMC() {
		return IMC;
	}

	public void setIMC(Float iMC) {
		IMC = iMC;
	}

	public Float getMG() {
		return MG;
	}

	public void setMG(Float mG) {
		MG = mG;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}

