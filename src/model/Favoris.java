/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author Ranim Ahmadi
 */
public class Favoris {
    private int id_user ;
    private int id_musique ;
    private Date dateFav ;

    public Favoris() {
    }

    public Favoris(int id_musique, Date dateFav) {
        this.id_musique = id_musique;
        this.dateFav = dateFav;
    }

    public Favoris(int id_user, int id_musique, Date dateFav) {
        this.id_user = id_user;
        this.id_musique = id_musique;
        this.dateFav = dateFav;
    }

    public int getId_user() {
        return id_user;
    }

    public int getId_musique() {
        return id_musique;
    }

    public Date getDateFav() {
        return dateFav;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setId_musique(int id_musique) {
        this.id_musique = id_musique;
    }

    public void setDateFav(Date dateFav) {
        this.dateFav = dateFav;
    }

    @Override
    public String toString() {
        return "Favoris{" + "id_user=" + id_user + ", id_musique=" + id_musique + ", dateFav=" + dateFav + '}';
    }
    
    
}
