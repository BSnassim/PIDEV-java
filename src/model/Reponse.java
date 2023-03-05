/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author saida
 */
public class Reponse {
    private int id ;
    private String message;
    private Date dateCreation;
    private Date dateUpdate;
    private Reclamation reclamation;
    private int idUser;

    public Reponse() {
    }

    public Reponse(int id, String message, Date dateCreation, Date dateUpdate, Reclamation reclamation,int idUser) {
        this.id = id;
        this.message = message;
        this.dateCreation = dateCreation;
        this.dateUpdate = dateUpdate;
        this.reclamation = reclamation;
        this.idUser=idUser;
    }

    public Reponse(String message, Date dateCreation, Date dateUpdate, Reclamation reclamation,int idUser) {
        this.message = message;
        this.dateCreation = dateCreation;
        this.dateUpdate = dateUpdate;
        this.reclamation = reclamation;
        this.idUser=idUser;
    }

    public Reponse(String message, Reclamation reclamation,int idUser) {
        this.message = message;
        this.reclamation = reclamation;
        this.idUser=idUser;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public Reclamation getReclamation() {
        return reclamation;
    }

    public void setReclamation(Reclamation reclamation) {
        this.reclamation = reclamation;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    
    

    @Override
    public String toString() {
        return "Reponse{" + "id=" + id + ", message=" + message + ", dateCreation=" + dateCreation + ", dateUpdate=" + dateUpdate + ", reclamation=" + reclamation.toString() + '}';
    }
    
    
    
}
