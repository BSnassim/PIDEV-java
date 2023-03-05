/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Siwar Ahmadi
 */
public class Categorie { 
      private int id;
    private String nom; 
private int visiteur;
private int starCount ;
private int rate;
    public Categorie(String nom) {
        this.nom = nom;
    }

    public Categorie() {
 
    }

    public Categorie(int id, String nom) {
        this.nom = nom;  
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Categorie{" + "id=" + id + ", nom=" + nom + '}';
    }

    public int getVisiteur() {
        return visiteur;
    }

    public void setVisiteur(int visiteur) {
        this.visiteur = visiteur;
    }

    public int getStarCount() {
        return starCount;
    }

    public void setStarCount(int starCount) {
        this.starCount = starCount;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
    
    
}
