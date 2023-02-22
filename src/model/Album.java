package model;

import java.sql.Date;

public class Album {
	
	private int id;
	private String nom;
	private Date dateCreation;
	private int id_artiste;
	
	public Album() {
	}

	public Album(String nom, Date dateCreation, int id_artiste) {
		super();
		this.nom = nom;
		this.dateCreation = dateCreation;
		this.id_artiste = id_artiste;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public int getId_artiste() {
		return id_artiste;
	}

	public void setId_artiste(int id_artiste) {
		this.id_artiste = id_artiste;
	}
	
	

}
