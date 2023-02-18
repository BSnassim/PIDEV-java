/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import interfaces.IartisteController;
import java.sql.SQLException;
import java.util.List;
import model.Artiste;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import model.Artiste;
import utils.connexionDB;
import java.sql.PreparedStatement;

/**
 *
 * @author Ranim Ahmadi
 */
public class ArtisteController implements IartisteController {
     PreparedStatement ste;
    Connection conn = connexionDB.getInstance().getConnexion();

    @Override
    public void ajouterArtiste(Artiste a) {
        try {
            String req = "INSERT INTO `artiste`( `description`, `origine`, `photo`, `id_user`) VALUES ('" + a.getDescription() + "','" + a.getOrigine() + "','" + a.getPhoto() + "','" + a.getId_user() + "')";
            ste = (PreparedStatement) conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("artiste ajouté!!!");
        } catch (SQLException ex) {
            System.out.println("artiste non ajouté");
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifierArtiste(Artiste a, int id_user) {
         String req = "UPDATE artiste SET description = ? ,  origine = ? , photo = ? WHERE id_user=?";
          try {
            ste=conn.prepareStatement(req);
            ste.setString(1, a.getDescription());
            ste.setString(2, a.getOrigine());
            ste.setString(3, a.getPhoto());
    
       
         
            ste.setInt(4, a.getId_user());
               
            
            ste.executeUpdate();
            System.out.println("artiste Updated");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 

    }

    @Override
    public void supprimerArtiste(int id_user) {
        try {
            String req = "DELETE FROM `artiste` WHERE id_user = " + id_user;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("artiste deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Artiste> afficherArtiste() {
         List<Artiste> alist = new ArrayList<>();
        try{
        Statement st= conn.createStatement();
        String query = "select * from artiste";
        
        ResultSet rs;
        rs = st.executeQuery(query);
        Artiste artiste;
        while (rs.next()) {
           artiste = new Artiste(rs.getString("description"),rs.getString("origine")
                   ,rs.getString("photo"),rs.getInt("id_user"));
                   
            alist.add(artiste);

        }
         return alist;    
         }catch(SQLException ex){
                         System.out.println(ex.getMessage());

         }
        return alist;
        }

    @Override
    public Artiste rechArtiste(int id) {
        return null;
       
    }

    


    
    
}
