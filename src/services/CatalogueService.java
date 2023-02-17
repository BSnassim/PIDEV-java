/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.IcatalogueController;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Catalogue;
import utils.ConnexionDB;

/**
 *
 * @author Siwar Ahmadi
 */
public class CatalogueService implements IcatalogueController { 
    Statement ste;
    Connection conn = ConnexionDB.getInstance().getConnexion();

    @Override
    public void ajouterCatalogue(Catalogue c) {
        try {
            String req = "INSERT INTO `catalogue`( `nom`, `id_categorie`) VALUES ('" + c.getNom() + "','" + c.getId_categorie() + "')";
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("Catalogue ajouté!!!");
        } catch (SQLException ex) {
            System.out.println("Catalogue non ajouté");
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifierCatalogue(Catalogue c, int id) {
        try {
            String req = "UPDATE `catalogue` SET `nom` = '" + c.getNom() + "', `id_categorie` = '" + c.getId_categorie() + "' WHERE `id` = " + id;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Catalogue updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerCatalogue(int id) {
        try {
            String req = "DELETE FROM `catalogue` WHERE id = " + id;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Catalogue deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Catalogue> afficherCatalogue() {
        List<Catalogue> list = new ArrayList<>();
        try {
            String req = "Select * from catalogue";
            Statement st = conn.createStatement();

            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                Catalogue c = new Catalogue();
                c.setNom(RS.getString("nom"));
                c.setId(RS.getInt(1));
                c.setId_categorie(RS.getInt(1));
                list.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    @Override
    public Catalogue rechCatalogue(int id) {
        Catalogue ca = new Catalogue();
        try {
            String req = "Select * from catalogue where id =" + id;
            Statement st = conn.createStatement();

            ResultSet RS = st.executeQuery(req);
            RS.first();

            ca.setNom(RS.getString("nom"));
            ca.setId(RS.getInt(1));
            ca.setId_categorie(RS.getInt(1));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return ca;
    }

    @Override
    public List<Catalogue> filterCatalogue(String S, String SS) {
        List<Catalogue> list = new ArrayList<>();
        try {
            if (S.equals("id") || S.equals("id_categorie")) {
                int temp = Integer.parseInt(SS);
                String req = "SELECT * FROM catalogue WHERE " + S + " =" + temp;
                Statement st = conn.createStatement();
                ResultSet RS = st.executeQuery(req);
                while (RS.next()) {
                    Catalogue c = new Catalogue();
                    c.setNom(RS.getString("nom"));
                    c.setId(RS.getInt(1));
                    c.setId_categorie(RS.getInt(1));

                    list.add(c);
                }
            } else {
                String req = "SELECT * FROM catalogue WHERE " + S + " =" + "\"" + SS + "\"";
                Statement st = conn.createStatement();
                ResultSet RS = st.executeQuery(req);
                while (RS.next()) {
                    Catalogue c = new Catalogue();
                    c.setNom(RS.getString("nom"));
                    c.setId(RS.getInt(1));
                    c.setId_categorie(RS.getInt(1));

                    list.add(c);
                } 
               
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
    
}
