package model;

import java.sql.Date;

public class Album {
	
	private Integer id;
	private String nom;
	private Date dateCreation;
	private Integer id_artiste;
	
	public Album() {
	}

	public Album(String nom, Date dateCreation, Integer id_artiste) {
		this.nom = nom;
		this.dateCreation = dateCreation;
		this.id_artiste = id_artiste;
	}

	public Album(String nom, Integer id_artiste) {
		this.nom = nom;
		this.id_artiste = id_artiste;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Integer getId_artiste() {
		return id_artiste;
	}

	public void setId_artiste(Integer id_artiste) {
		this.id_artiste = id_artiste;
	}

	@Override
	public String toString() {
		return "Album [id=" + id + ", nom=" + nom + ", dateCreation=" + dateCreation + ", id_artiste=" + id_artiste
				+ "]";
	}
	
	

}
