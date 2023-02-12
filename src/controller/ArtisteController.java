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
import utils.connexionDB;

/**
 *
 * @author Ranim Ahmadi
 */
public class ArtisteController implements IartisteController {
    Statement ste ;
    Connection conn = connexionDB.getInstance().getConnexion();

    @Override
    public void ajouterArtiste(Artiste a) {
        try {
            String req = "INSERT INTO `artiste`( `description`, `origine`, `photo`, `id_user`) VALUES ('" + a.getDescription() + "','" + a.getOrigine() + "','" + a.getPhoto() + "','" + a.getId() + "')";
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("artiste ajouté!!!");
        } catch (SQLException ex) {
            System.out.println("artiste non ajouté");
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifierArtiste(Artiste a, int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimerArtiste(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Artiste> afficherArtiste() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Artiste rechArtiste(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
