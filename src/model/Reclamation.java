/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author houss
 */
public class Reclamation {
    public int id;
    public String description;
    public EtatEnum etat;
    public TypeEnum type;
    public Date dateCreation;
    public Date dateUpdate;
    public int id_User;

    public Reclamation() {
    }

    public Reclamation(String description, EtatEnum etat, TypeEnum type, Date dateCreation, Date dateUpdate, int id_User) {
        this.id = id;
        this.description = description;
        this.etat = etat;
        this.type = type;
        this.dateCreation = dateCreation;
        this.dateUpdate = dateUpdate;
        this.id_User = id_User;
    }

    public Reclamation(int id, String description, EtatEnum etat, TypeEnum type, Date dateCreation, Date dateUpdate, int id_User) {
        this.id = id;
        this.description = description;
        this.etat = etat;
        this.type = type;
        this.dateCreation = dateCreation;
        this.dateUpdate = dateUpdate;
        this.id_User = id_User;
    }

    public Reclamation(String description, EtatEnum etat, TypeEnum type, int id_User) {
        this.description = description;
        this.etat = etat;
        this.type = type;
        this.id_User = id_User;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EtatEnum getEtat() {
        return etat;
    }

    public void setEtat(EtatEnum etat) {
        this.etat = etat;
    }

    public TypeEnum getType() {
        return type;
    }

    public void setType(TypeEnum type) {
        this.type = type;
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

    public int getId_User() {
        return id_User;
    }

    public void setId_User(int id_User) {
        this.id_User = id_User;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", description=" + description + ", etat=" + etat + ", type=" + type + ", dateCreation=" + dateCreation + ", dateUpdate=" + dateUpdate + ", id_User=" + id_User + '}';
    }
    
    
    
}
