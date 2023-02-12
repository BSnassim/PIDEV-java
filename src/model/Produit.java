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
    private int id_Artiste;

    public Produit(int prix, String image, String libelle, String type, int id_Artiste) {
        this.prix = prix;
        this.image = image;
        this.libelle = libelle;
        this.type = type;
        this.id_Artiste = id_Artiste;
    }

    public Produit() {
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrix() {
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

    public int getId_Artiste() {
        return id_Artiste;
    }

    public void setId_Artiste(int id_Artiste) {
        this.id_Artiste = id_Artiste;
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", prix=" + prix + ", image=" + image + ", libelle=" + libelle + ", type=" + type + ", id_Artiste=" + id_Artiste + '}';
    }

    public void getImage(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    
    
}
