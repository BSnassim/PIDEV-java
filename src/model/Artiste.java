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
    private int id_user ;
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

    public Artiste(String description, String origine, String photo, int id_user) {
         this.description = description;
        this.origine = origine;
        this.photo = photo;
        this.id_user = id_user;
       
    }

    public int getId_user() {
        return id_user;
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

    public void setId_user(int id_user) {
        this.id_user = id_user;
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
        return "Artiste{" + "id_user=" + id_user + ", description=" + description + ", origine=" + origine + ", photo=" + photo + '}';
    }
    
    
    
}
