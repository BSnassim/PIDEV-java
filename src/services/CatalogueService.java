/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.IcatalogueController;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            System.out.println(c.getImage());
            String req = "INSERT INTO `catalogue`( `nom`, `id_categorie`,`image`) VALUES (?,?,?)";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setString(1, c.getNom());
            ps.setInt(2, c.getId_categorie());
            ps.setString(3, c.getImage());
            ps.executeUpdate();
            System.out.println("Catalogue ajouté!!!");
        } catch (SQLException ex) {
            System.out.println("Catalogue non ajouté");
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(CatalogueService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modifierCatalogue(Catalogue c) {
        try {
            String req = "UPDATE `catalogue` SET `nom` = '" + c.getNom() + "' WHERE `id` = " + c.getId();
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
                c.setImage(RS.getString("image"));
                c.setId(RS.getInt(1));
                c.setId_categorie(RS.getInt("id_categorie"));
                list.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    @Override
    public List<Catalogue> rechCatalogue(String mot) {
        
        List<Catalogue> ca = new ArrayList<Catalogue>();
        try {
            String req = "Select * from catalogue where nom like '%" + mot+"%'";
            Statement st = conn.createStatement();

            ResultSet RS = st.executeQuery(req);
            while(RS.next()){
                Catalogue c = new Catalogue();
            c.setNom(RS.getString("nom"));
            c.setId(RS.getInt(1));
            c.setImage(RS.getString("image"));
            c.setId_categorie(RS.getInt(1));
            ca.add(c);
            }
            
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
