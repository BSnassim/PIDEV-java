/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Interfaces.IcommandeController;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Commande;
import utils.connexionDB;

/**
 *
 * @author Marwen.M
 */
public class CommandeController implements IcommandeController{
    Statement ste;
    Connection conn = connexionDB.getInstance().getConnexion();

    @Override
    public void ajouterCommande(Commande c) {
        try {
            String req = "INSERT INTO `commande`( `totale`, `dateCommande`,`id_user`) VALUES ('" + c.getTotale()+ "','" + c.getDateCommande()+ "','" + c.getId_user()+ "')";
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("Commande ajouté!!!");
        } catch (SQLException ex) {
            System.out.println("Commande non ajouté");
            System.out.println(ex.getMessage());
        }    }

    @Override
    public void modifierCommande(Commande c, int id) {
        try {
            String req = "UPDATE `commande` SET `Totale` = '" + c.getTotale()+ "', `dateCommande` = '" + c.getDateCommande()+ "' WHERE `id` = " + id;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Commande updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerCommande(int id) {
        try {
            String req = "DELETE FROM `commande` WHERE id = " + id;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Commande deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Commande> afficherCommande() {
            List<Commande> list = new ArrayList<>();
        try {
            String req = "Select * from commande";
            Statement st = conn.createStatement();

            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                Commande c = new Commande();
                c.setTotale(RS.getInt("totale"));
                c.setDateCommande(RS.getDate("dateCommande"));
                c.setId_user(RS.getInt("id_user"));
               
               
                c.setId(RS.getInt(1));

                list.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    @Override
    public Commande rechCommande(int id) {
        Commande c = new Commande();
        try {
            String req = "Select * from commande where id ="+id;
            Statement st = conn.createStatement();

            ResultSet RS = st.executeQuery(req);
            RS.first();
            c.setTotale(RS.getInt("totale"));
            c.setDateCommande(RS.getDate("dateCommande"));
            c.setId_user(RS.getInt("id_user"));
            c.setId(RS.getInt(1));
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    
            return c;
    }
    }
    

