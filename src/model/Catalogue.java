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
public class Catalogue {
      private int id ;
    private String nom;
    private  int id_categorie;

    public Catalogue(String nom, int id_categorie) {
        this.nom = nom;
        this.id_categorie = id_categorie; 
    } 

    public Catalogue(String nom) {
        this.nom = nom;
    }

    public Catalogue() {
        
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public int getId_categorie() {
        return id_categorie;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    @Override
    public String toString() {
        return "Catalogue{" + "id=" + id + ", nom=" + nom + ", id_categorie=" + id_categorie + '}';
    }
    
    
}
