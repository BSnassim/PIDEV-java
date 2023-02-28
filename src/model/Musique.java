package model;

import java.sql.Date;

public class Musique {

	private Integer id;
	private String nom;
	private String chemin;
	private Date dateCreation;
	private String longueur;
	private Integer id_Artiste;
	private Integer id_Categorie;
	private Integer id_album;
	// Transient fields //
	private transient String fileName;
	private transient byte[] fileContent;

	public Musique() {
	}

	public Musique(String nom, String chemin, Date dateCreation, String longueur, Integer id_Artiste,
			Integer id_Categorie, Integer id_album) {
		this.nom = nom;
		this.chemin = chemin;
		this.dateCreation = dateCreation;
		this.longueur = longueur;
		this.id_Artiste = id_Artiste;
		this.id_Categorie = id_Categorie;
		this.id_album = id_album;
	}

	public Musique(String nom, String chemin, String longueur, Integer id_Artiste, Integer id_Categorie,
			Integer id_album) {
		this.nom = nom;
		this.chemin = chemin;
		this.longueur = longueur;
		this.id_Artiste = id_Artiste;
		this.id_Categorie = id_Categorie;
		this.id_album = id_album;
	}

	public Musique(String nom, String longueur, Integer id_Artiste, Integer id_Categorie, Integer id_album) {
		this.nom = nom;
		this.longueur = longueur;
		this.id_Artiste = id_Artiste;
		this.id_Categorie = id_Categorie;
		this.id_album = id_album;
	}
	
	public Musique(String nom, String longueur, Integer id_Artiste, Integer id_Categorie) {
		this.nom = nom;
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

	public Integer getId_Artiste() {
		return id_Artiste;
	}

	public void setId_Artiste(Integer id_Artiste) {
		this.id_Artiste = id_Artiste;
	}

	public Integer getId_Categorie() {
		return id_Categorie;
	}

	public void setId_Categorie(Integer id_Categorie) {
		this.id_Categorie = id_Categorie;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getId_album() {
		return this.id_album;
	}

	public void setId_album(Integer id_album) {
		this.id_album = id_album;
	}

	@Override
	public String toString() {
		return "Musique [id=" + id + ", nom=" + nom + ", chemin=" + chemin + ", dateCreation=" + dateCreation
				+ ", longueur=" + longueur + ", id_Artiste=" + id_Artiste + ", id_Categorie=" + id_Categorie
				+ ", id_album=" + id_album + "]";
	}

}
