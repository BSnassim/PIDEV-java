package model;

import java.sql.Date;

public class Musique {
	
	private int id;
	private String nom;
	private String chemin;
	private Date dateCreation;
	private String longueur;
	private int id_Artiste;
	private int id_Categorie;
	// Transient fields //
	private transient String fileName;
	private transient byte[] fileContent;
	
	
	public Musique() {}
	public Musique(String nom, String chemin, Date dateCreation, String longueur, int id_Artiste, int id_Categorie) {
		this.nom = nom;
		this.chemin = chemin;
		this.dateCreation = dateCreation;
		this.longueur = longueur;
		this.id_Artiste = id_Artiste;
		this.id_Categorie = id_Categorie;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getChemin() {
		return chemin;
	}
	public void setChemin(String chemin) {
		this.chemin = chemin;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	public String getLongueur() {
		return longueur;
	}
	public void setLongueur(String longueur) {
		this.longueur = longueur;
	}
	public int getId_Artiste() {
		return id_Artiste;
	}
	public void setId_Artiste(int id_Artiste) {
		this.id_Artiste = id_Artiste;
	}
	public int getId_Categorie() {
		return id_Categorie;
	}
	public void setId_Categorie(int id_Categorie) {
		this.id_Categorie = id_Categorie;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id=id;
	}
	public byte[] getFileContent() {
		return fileContent;
	}
	public void setFileContent(byte[] fileContent) {
		this.fileContent = fileContent;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	

}
