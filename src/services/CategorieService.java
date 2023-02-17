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
import java.util.List;
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
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("Categorie ajouté!!!");
        } catch (SQLException ex) {
            System.out.println("Categorie non ajouté");
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void modifierCategorie(Categorie ca, int id) {
        try {
            String req = "UPDATE `categorie` SET `nom` = '" + ca.getNom() + "' WHERE `id` = " + id;
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

   


    
}
