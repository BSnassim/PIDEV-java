/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Marwen.M
 */
public class Produit {
    private int id;
    private int prix;
    private String image;
    private String libelle;
    private String type;
    private String descr;
    private int id_Artiste;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public int getId_Artiste() {
        return id_Artiste;
    }

    public void setId_Artiste(int id_Artiste) {
        this.id_Artiste = id_Artiste;
    }

    public Produit() {
    }

    public Produit(int prix, String image, String libelle, String type, String descr, int id_Artiste) {
        this.prix = prix;
        this.image = image;
        this.libelle = libelle;
        this.type = type;
        this.descr = descr;
        this.id_Artiste = id_Artiste;
    }

    @Override
    public String toString() {
        return "Produit{" + "prix=" + prix + ", image=" + image + ", libelle=" + libelle + ", type=" + type + ", descr=" + descr + ", id_Artiste=" + id_Artiste + '}';
    }

    
}
