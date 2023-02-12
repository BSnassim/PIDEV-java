/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import interfaces.IutilisateurController;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Utilisateur;
import utils.connexionDB;

/**
 *
 * @author Ranim Ahmadi
 */
public class UtilisateurController implements IutilisateurController{
    Statement ste ;
    Connection conn = connexionDB.getInstance().getConnexion();
    

    @Override
    public void ajouterUtilisateur(Utilisateur u) {
         try {
            String req = "INSERT INTO `utilisateur`( `login`, `password`, `nom`, `prenom`, `email`) VALUES ('" + u.getLogin() + "','" + u.getPassword() + "','" + u.getNom() + "','" + u.getPrenom() + "','" + u.getEmail() + "')";
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("utilisateur ajouté!!!");
        } catch (SQLException ex) {
            System.out.println("utilisateur non ajouté");
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifierUtilisateur(Utilisateur u, int id) {
        try {
            
            String req = "UPDATE `utilisateur` SET `email` = '" + u.getEmail() + "', `login` = '" + u.getLogin() + "'WHERE `id` = " + id ;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Utilisateur updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


    @Override
    public void supprimerUtilisateur(int id) {
         try {
            String req = "DELETE FROM `utilisateur` WHERE id = " + id;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("utilisateur deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Utilisateur> afficherUtilisateur() {
        List<Utilisateur> list = new ArrayList<>();
        try {
            String req = "Select * from utilisateur";
            Statement st = conn.createStatement();

            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                Utilisateur u = new Utilisateur();
                u.setId(RS.getInt(1));
                u.setLogin(RS.getString("login"));
                u.setPassword(RS.getString("password"));
                u.setNom(RS.getString("nom"));
                u.setPrenom(RS.getString("prenom"));
                u.setEmail(RS.getString("email"));
                
                
               
                list.add(u);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    @Override
    public Utilisateur rechUtilisateur(int id) {
        Utilisateur utili = new Utilisateur();
        try {
            String req = "Select * from utilisateur where id ="+id;
            Statement st = conn.createStatement();

            ResultSet RS = st.executeQuery(req);
            RS.first();
           
                        utili.setId(RS.getInt(1));   
                        utili.setLogin(RS.getString("login"));
                        utili.setPassword(RS.getString("password"));
                        utili.setNom(RS.getString("nom"));
                        utili.setPrenom(RS.getString("prenom"));
                        utili.setEmail(RS.getString("email"));
                        
                
           
               
           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return utili ;
    }

    @Override
    public List<Utilisateur> afficherCatalogue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
