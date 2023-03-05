/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.IcategorieController;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.text.Text;
import model.Categorie;
import utils.ConnexionDB;

/**
 *
 * @author Siwar Ahmadi
 */
public class CategorieService implements IcategorieController { 
     Statement ste;
    Connection conn = ConnexionDB.getInstance().getConnexion();

    @Override
    public void ajouterCategorie(Categorie ca) {
        try {
            String req = "INSERT INTO `categorie`( `nom`) VALUES ('" + ca.getNom() + "')";
            //verifier que le champ n'est pas vide
            if (ca.getNom().trim().isEmpty() ) {
                throw new Exception("Le champs est obligatoires !");
        } 
             //verifier que le champ est unique 
             Set<String> nomCategorie = new HashSet<>();
             List<Categorie> clist = new ArrayList<>();
             clist.forEach((categorie) -> {
                 nomCategorie.add(categorie.getNom());
            });
        if (nomCategorie.contains(ca.getNom())) {
            throw new Exception("Le login est déjà utilisé !");
        } 
        
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("Categorie ajouté!!!");
        } catch (SQLException ex) {
            System.out.println("Categorie non ajouté");
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
             Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
         }

    }

    @Override
    public void modifierCategorie(Categorie ca) {
        try {
            String req = "UPDATE `categorie` SET `nom` = '" + ca.getNom() + "' WHERE `id` = " + ca.getId();
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Categorie updated !");
        } catch (SQLException ex) {
            System.out.println("Categorie not updated !");

            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerCategorie(int id) {
        try {
            String req = "DELETE FROM `categorie` WHERE id = " + id;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Categorie deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Categorie> afficherCategorie() {
        List<Categorie> list = new ArrayList<>();
        try {
            String req = "Select * from categorie";
            Statement st = conn.createStatement();

            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                Categorie ca = new Categorie();
                ca.setNom(RS.getString("nom"));
                ca.setId(RS.getInt(1));
                ca.setVisiteur(RS.getInt("visiteur"));
                ca.setRate(RS.getInt("rate"));
                list.add(ca);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    @Override
    public Categorie rechCatalogue(int id) {
       Categorie cat = new Categorie();
        try {
            String req = "Select * from categorie where id ="+id;
            Statement st = conn.createStatement();

            ResultSet RS = st.executeQuery(req);
            RS.first();
            
                
                cat.setNom(RS.getString("nom"));
                cat.setId(RS.getInt(1));
               
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return cat;
    }

    public List<String> getallCategorie() {
        List<String> list = new ArrayList<>();
        try {
            String req = "Select DISTINCT(nom) from categorie";
            Statement st = conn.createStatement();

            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                list.add(RS.getString(1));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    public List<String> getallCataloguesCategorie(String value) {
        List<String> list = new ArrayList<>();
        try {
            String req = "Select DISTINCT nom from catalogue where id_categorie in ( select id from Categorie where nom = '"+value+"')";
            Statement st = conn.createStatement();
                
            ResultSet RS = st.executeQuery(req);
            int ex= 0 ;
            while (RS.next()) {
                list.add(RS.getString(1));
                ex++;
            }
            if (ex==0)
                return null;
            else return list;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    public boolean existIdCategorie(int id_category) {
        Categorie cat = new Categorie();
        try {
            String req = "Select * from categorie where id ="+id_category;
            Statement st = conn.createStatement();

            ResultSet RS = st.executeQuery(req);
            
            while (RS.next()){
                return true ; 
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public void incrementVisiteur(String nom2,int rate) {
        Categorie cat = new Categorie();
        try {
            String req = "Select * from categorie where nom ='"+nom2+"'";
            Statement st = conn.createStatement();

            ResultSet RS = st.executeQuery(req);
            
            RS.first();
            cat.setRate(RS.getInt("rate"));
            cat.setStarCount(RS.getInt("starCount"));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        
        
        try {
            String req = "UPDATE `categorie` SET  `starCount` = "+(cat.getStarCount()+1 )+" , `rate` = "+ (((cat.getRate() * cat.getStarCount()) + (rate)) / (cat.getStarCount() + 1)) +" WHERE `nom` = '" + nom2+"'";
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Categorie updated !");
        } catch (SQLException ex) {
            System.out.println("Categorie not updated !");

            System.out.println(ex.getMessage());
        }
    }
    public void incrementVisiteur2(String nom2) {
        Categorie cat = new Categorie();
        try {
            String req = "Select * from categorie where nom ='"+nom2+"'";
            Statement st = conn.createStatement();

            ResultSet RS = st.executeQuery(req);
            
            RS.first();
            cat.setVisiteur(RS.getInt("visiteur"));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        
        
        try {
            String req = "UPDATE `categorie` SET `visiteur` = " + (cat.getVisiteur()+1) + " WHERE `nom` = '" + nom2+"'";
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Categorie updated !");
        } catch (SQLException ex) {
            System.out.println("Categorie not updated !");

            System.out.println(ex.getMessage());
        }
    }
   
    
}
