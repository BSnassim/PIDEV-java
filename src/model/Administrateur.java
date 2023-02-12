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
public class Administrateur {
    private int id_admin ;
    private String role ;

    public Administrateur() {
    }

    public Administrateur(String role) {
        this.role = role;
    }

    public int getId_admin() {
        return id_admin;
    }

    public String getRole() {
        return role;
    }

    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Administrateur{" + "id_admin=" + id_admin + ", role=" + role + '}';
    }
    
    
    
    
    
}
