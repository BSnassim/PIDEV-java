/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author Marwen.M
 */
public class Commande {
    private int id;
    private int totale;
    private Date dateCommande;
    private int id_user;

    public Commande(int totale, Date dateCommande, int id_user) {
        this.totale = totale;
        this.dateCommande = dateCommande;
        this.id_user = id_user;
    }

    public Commande() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotale() {
        return totale;
    }

    public void setTotale(int totale) {
        this.totale = totale;
    }

    public Date getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", totale=" + totale + ", dateCommande=" + dateCommande + ", id_user=" + id_user + '}';
    }

    public void getId_user(int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
