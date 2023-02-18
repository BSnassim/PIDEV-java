/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import interfaces.IfavorisController;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import model.Favoris;
import utils.connexionDB;

/**
 *
 * @author Ranim Ahmadi
 */
public class FavorisController implements IfavorisController {
     PreparedStatement ste;
    Connection conn = connexionDB.getInstance().getConnexion();


    @Override
    public void ajouterFavoris(Favoris f) {
         try {
            String req = "INSERT INTO `favoris`( `id_user`, `id_musique`, `dateFav`) VALUES ('" + f.getId_user() + "','" + f.getId_musique() + "','" + f.getDateFav() + "')";
            ste = (PreparedStatement) conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("favoris ajouté!!!");
        } catch (SQLException ex) {
            System.out.println("favoris non ajouté");
            System.out.println(ex.getMessage());
        }
    }

    

    @Override
    public void supprimerFavoris(int id_user, int id_musique) {
        try {
            String req = "DELETE FROM `favoris` WHERE id_user , id_musique = " + id_user , id_musique;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("favoris deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Favoris> afficherFavoris() {
         try{
        Statement st= conn.createStatement();
        String query = "select * from favoris";
        
        ResultSet rs;
        rs = st.executeQuery(query);
        Favoris favoris;
        while (rs.next()) {
           favoris = new Favoris(rs.getInt("id_user"),rs.getInt("id_musique")
                   ,rs.getDate("dateFav"));
                   
            flist.add(favoris);

        }
         return flist;    
         }catch(SQLException ex){
                         System.out.println(ex.getMessage());

         }
        return flist;
        }

    /*@Override
    public void modifierFavoris(Favoris f, int id_user, int id_musique) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Favoris rechFavoris(int id_user, int id_musique) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
