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
public class Utilisateur {

    public static void ajouterUtilisateur(Utilisateur utilisateur) {
       
    }
    private  int id ;
    private String login;
    private String  password;
    private String  nom;
    private String  prenom;
    private String  email;
   
    public Utilisateur() {
    }

    public Utilisateur(int id, String login, String password, String nom, String prenom, String email) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }
    

    public Utilisateur(String login, String password, String nom, String prenom, String email) {
        this.login = login;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
       
    }

   

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

   

    public void setId(int id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "id=" + id + ", login=" + login + ", password=" + password + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + '}';
    }

    

    
    
    
    
    
    
    
    
    
}

