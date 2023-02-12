/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Ranim Ahmadi
 */
public class Artiste {
    private int id ;
    private String description ;
    private String origine ;
    private String photo;

    public Artiste() {
    }

    public Artiste(String description, String origine, String photo) {
        this.description = description;
        this.origine = origine;
        this.photo = photo;
    }

    public Artiste(String artiste, String chanteur, String music, int i) {
       
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getOrigine() {
        return origine;
    }

    public String getPhoto() {
        return photo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setOrigine(String origine) {
        this.origine = origine;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Artiste{" + "id=" + id + ", description=" + description + ", origine=" + origine + ", photo=" + photo + '}';
    }
    
    
    
}
