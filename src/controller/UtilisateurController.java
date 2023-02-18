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
import java.sql.PreparedStatement;

/**
 *
 * @author Ranim Ahmadi
 */
public class UtilisateurController implements IutilisateurController{
  Connection conn;
    PreparedStatement ste;
    public UtilisateurController(){
        conn=connexionDB.getInstance().getConnexion();}
    

    @Override
    public void ajouterUtilisateur(Utilisateur u) {
         try {
            String req = "INSERT INTO `utilisateur`( `login`, `password`, `nom`, `prenom`, `email`) VALUES ('" + u.getLogin() + "','" + u.getPassword() + "','" + u.getNom() + "','" + u.getPrenom() + "','" + u.getEmail() + "')";
            ste = conn.prepareStatement(req);
            ste.executeUpdate(req);
            System.out.println("utilisateur ajouté!!!");
        } catch (SQLException ex) {
            System.out.println("utilisateur non ajouté");
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifierUtilisateur(Utilisateur u,int id) {
        
            
            String req = "UPDATE utilisateur SET login = ? ,  nom = ? , prenom = ? , email = ? WHERE id=?";
          try {
            ste=conn.prepareStatement(req);
            ste.setString(1, u.getLogin());
            ste.setString(2, u.getNom());
            ste.setString(3, u.getPrenom());
             ste.setString(4, u.getEmail());
            
            ste.setInt(5, id);
               
            
            ste.executeUpdate();
            System.out.println("compte updated ");
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
 @Override
 

    public List<Utilisateur> afficherUtilisateur() {
        List<Utilisateur> ulist = new ArrayList<>();
        try{
        Statement st= conn.createStatement();
        String query = "select * from utilisateur";
        
        ResultSet rs;
        rs = st.executeQuery(query);
        Utilisateur utilisateur;
        while (rs.next()) {
           utilisateur = new Utilisateur(rs.getInt("id"),rs.getString("login")
                   ,rs.getString("password"),rs.getString("nom"),rs.getString("prenom"),rs.getString("email"));
                   
            ulist.add(utilisateur);

        }
         return ulist;    
         }catch(SQLException ex){
                         System.out.println(ex.getMessage());

         }
        return ulist;
        
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

    
    
    

    

   

}
