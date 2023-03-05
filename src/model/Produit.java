/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;

/**
 *
 * @author Marwen.M
 */
public class Produit {
    private int id;
    private Float prix;
    private String image;
    private String libelle;
    private String type;
    private String descr;
    private int qte;
    private String taille;

    public Produit(float parseFloat, String text, String text0, String get, String text1, int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public String getTaille() {
        return taille;
    }

    public void setTaille(String taille) {
        this.taille = taille;
    }
    private int id_Artiste;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
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

    public Produit(Float prix, String image, String libelle, String type, String descr, int qte,String taille , int id_Artiste) {
        this.prix = prix;
        this.image = image;
        this.libelle = libelle;
        this.type = type;
        this.descr = descr;
        this.qte=qte;
        this.taille=taille;
        this.id_Artiste = id_Artiste;
    }

    @Override
    public String toString() {
        return "Produit{ ID = "+id + " prix=" + prix + ", image=" + image + ", libelle=" + libelle + ", type=" + type + ", descr=" + descr + ", qte=" + qte+ ", taille=" + taille+ ", id_Artiste=" + id_Artiste + '}';
    }

    public void getPrix(int i, float f) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setPrix(int i, float f) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void getQte(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void ajouterProduit(Produit produit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Object getlibelle() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Connection getConnection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
