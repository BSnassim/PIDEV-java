/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enteties;

import java.util.Date;

/**
 *
 * @author sywar
 */
public class reservation {
    private int id ;
     private int id_Artiste;
     private int id_event;
     private String payement;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_Artiste() {
        return id_Artiste;
    }

    public void setId_Artiste(int id_Artiste) {
        this.id_Artiste = id_Artiste;
    }

    public int getId_event() {
        return id_event;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public String getPayement() {
        return payement;
    }

    public void setPayement(String payement) {
        this.payement = payement;
    }

    public reservation(int id, int id_Artiste, int id_event, String payement) {
        this.id = id;
        this.id_Artiste = id_Artiste;
        this.id_event = id_event;
        this.payement = payement;
    }

    public reservation(int id_Artiste, int id_event, String payement) {
        this.id_Artiste = id_Artiste;
        this.id_event = id_event;
        this.payement = payement;
    }

    public reservation() {
    }

    
    
    @Override
    public String toString() {
        return "reservation{" + "id=" + id + ", id_Artiste=" + id_Artiste + ", id_event=" + id_event + ", payement=" + payement + '}';
    }

   
}
