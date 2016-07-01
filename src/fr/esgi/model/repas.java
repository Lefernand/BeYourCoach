package fr.esgi.model;

import java.util.ArrayList;
import java.util.Date;

public class repas {
	private Integer id;
	private String time;
	private ArrayList<Aliment> listAliment;
	private Date date;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
