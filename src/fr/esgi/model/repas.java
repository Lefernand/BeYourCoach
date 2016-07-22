package fr.esgi.model;

import java.util.ArrayList;
import java.util.Date;

public class Repas {
	private String time;
	private ArrayList<Aliment> listAliment;
	private Date date;
	private Integer energieTotal;
	
	public Repas(String time, ArrayList<Aliment> listAliment, Date date, Integer energieTotal) {
		super();
		this.time = time;
		this.listAliment = listAliment;
		this.date = date;
		this.energieTotal = energieTotal;
	}
	
	public Repas() {
		super();
	}

	public Integer getEnergieTotal() {
		return energieTotal;
	}
	public void setEnergieTotal(Integer energieTotal) {
		this.energieTotal = energieTotal;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public ArrayList<Aliment> getListAliment() {
		return listAliment;
	}
	public void setListAliment(ArrayList<Aliment> listAliment) {
		this.listAliment = listAliment;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
