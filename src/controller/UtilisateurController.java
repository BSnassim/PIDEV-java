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
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Ranim Ahmadi
 */
public class UtilisateurController implements IutilisateurController{
  Connection conn =connexionDB.connexionDB();
    PreparedStatement ste;
    public UtilisateurController(){}
    
    // Méthode pour valider une adresse email
    public static boolean isEmailValid(String email) {
        // Expression régulière pour valider l'adresse email
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + 
                             "[a-zA-Z0-9_+&*-]+)*@" + 
                             "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                             "A-Z]{2,7}$";
                              
        return email.matches(emailRegex);
    }
    

    @Override
    public void ajouterUtilisateur(Utilisateur u) {
         try {
            String req = "INSERT INTO `utilisateur`( `login`, `password`, `nom`, `prenom`, `email`) VALUES ('" + u.getLogin() + "','" + u.getPassword() + "','" + u.getNom() + "','" + u.getPrenom() + "','" + u.getEmail() + "')";
            // Vérifier que les données saisies sont valides
        if (u.getNom().trim().isEmpty() || u.getPrenom().trim().isEmpty() || u.getEmail().trim().isEmpty()) {
            throw new Exception("Les champs sont obligatoires !");
        }
            if (!isEmailValid(u.getEmail())) {
            throw new Exception("L'adresse email n'est pas valide !");
        }
            Set<String> loginUser = new HashSet<String>();
             List<Utilisateur> ulist = new ArrayList<>();
        for (Utilisateur utilisateur : ulist) {
           loginUser.add(utilisateur.getLogin());
        }
        if (loginUser.contains(u.getLogin())) {
            throw new Exception("Le login est déjà utilisé !");
        }
           
            ste = conn.prepareStatement(req);
            ste.executeUpdate(req);
            System.out.println("utilisateur ajouté!!!");
        } catch (SQLException ex) {
            System.out.println("utilisateur non ajouté");
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
          Logger.getLogger(UtilisateurController.class.getName()).log(Level.SEVERE, null, ex);
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
    public void modifierUtilisateurPassword(Utilisateur u,int id) {
        
            
            String req = "UPDATE utilisateur SET password=? WHERE id=?";
          try {
            ste=conn.prepareStatement(req);
            ste.setString(1, u.getPassword());
            
            ste.setInt(2, id);
               
            
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
    public ObservableList<Utilisateur> rechUtilisateurByLogin2(String login) {
        ObservableList<Utilisateur> utilis = FXCollections.observableArrayList();
        try {
            String req = "Select * from utilisateur where login ='"+login+"'";
            Statement st = conn.createStatement();
            ResultSet RS = st.executeQuery(req);
            if (RS.next()){
                Utilisateur utili = new Utilisateur();
                
                utili.setId(RS.getInt(1));   
            utili.setLogin(RS.getString("login"));
            utili.setPassword(RS.getString("password"));
            utili.setNom(RS.getString("nom"));
            utili.setPrenom(RS.getString("prenom"));
            utili.setEmail(RS.getString("email"));
            utilis.add(utili);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return utilis;
    }
    public Utilisateur getUserLogin(String login, String password) {
        Utilisateur utili = new Utilisateur();
        try {
            String req = "Select * from utilisateur where login ='"+login+"' and password='"+password+"'";
            Statement st = conn.createStatement();
            ResultSet RS = st.executeQuery(req);
            RS.first();
            if (RS.first()){
                utili.setId(RS.getInt(1));   
            utili.setLogin(RS.getString("login"));
            utili.setPassword(RS.getString("password"));
            utili.setNom(RS.getString("nom"));
            utili.setPrenom(RS.getString("prenom"));
            utili.setEmail(RS.getString("email"));
            }
            else return null;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return utili;
    }

    public Utilisateur rechUtilisateurByLogin(String login) {
        Utilisateur utili = new Utilisateur();
        try {
            String req = "Select * from utilisateur where login ='"+login+"'";
            Statement st = conn.createStatement();
            ResultSet RS = st.executeQuery(req);
            RS.first();
            if (RS.first()){
                utili.setId(RS.getInt(1));   
            utili.setLogin(RS.getString("login"));
            utili.setPassword(RS.getString("password"));
            utili.setNom(RS.getString("nom"));
            utili.setPrenom(RS.getString("prenom"));
            utili.setEmail(RS.getString("email"));
            }
            else return null;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return utili;
    }

    public Utilisateur getUserByLogin(String login) {
        Utilisateur utili = new Utilisateur();
        try {
            String req = "Select * from utilisateur where login ='"+login+"'";
            Statement st = conn.createStatement();
            ResultSet RS = st.executeQuery(req);
            RS.first();
            if (RS.first()){
                utili.setId(RS.getInt(1));   
            utili.setLogin(RS.getString("login"));
            utili.setPassword(RS.getString("password"));
            utili.setNom(RS.getString("nom"));
            utili.setPrenom(RS.getString("prenom"));
            utili.setEmail(RS.getString("email"));
            }
            else return null;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return utili;
    }

    
    
    

    

   

}
