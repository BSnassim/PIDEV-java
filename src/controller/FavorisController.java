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
import java.util.ArrayList;
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

    /**
     *
     * @param f
     */
    
    
    @Override
    public void supprimerFavoris(Favoris f) {
        
        try {
            String req = "DELETE FROM favoris where id_user=?";
            
            PreparedStatement st = connexionDB.getInstance().getConnexion().prepareStatement(req);
             
            st.setInt(1, f.getId_user());
            st.executeUpdate(req);
            System.out.println("favoris deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

   
    
    @Override
    public List<Favoris> afficherFavoris() {
         List<Favoris> favorisList = new ArrayList();
        try{
        Statement st= conn.createStatement();
        String query = "select * from favoris";
        
        ResultSet rs;
        rs = st.executeQuery(query);
        Favoris f = new Favoris ();
        while (rs.next()) {
                f.setId_user(rs.getInt("id_user"));
                f.setId_musique(rs.getInt("id_musique"));
                f.setDate(rs.getDate("date"));
                
               
                favorisList.add(f);

        }
         return favorisList;    
         }catch(SQLException ex){
                         System.out.println(ex.getMessage());

         }
        return favorisList;
        }

    @Override
    public void modifierFavoris(Favoris f) {
       try {
            String requete = "UPDATE favoris SET id_musique=?,dateFav=? WHERE Id_user=?";
            PreparedStatement pst = connexionDB.getInstance().getConnexion()
                    .prepareStatement(requete);
            
            
            
            pst.setInt(1, f.getId_musique());
            pst.setDate(2, f.getDateFav());
            pst.setInt(3, f.getId_user());
           
            pst.executeUpdate();
            System.out.println("Favoris modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public Favoris rechFavoris(int id_user) {
          String requete="select * from favoris where id_user=?";
        ResultSet rs=null;
        
        List<Favoris> list=new ArrayList();
        try{
            PreparedStatement ps = connexionDB.getInstance().getConnexion()
                    .prepareStatement(requete);
            ps.setInt(1, id_user);
            rs=ps.executeQuery();
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        Favoris f =new Favoris();
        try{
            while(rs.next()){
               f.setId_user(rs.getInt("id_user"));
               f.setId_musique(rs.getInt("id_musique"));
               f.setDate(rs.getDate("date"));
              
                
                
          list.add(new Favoris (rs.getInt(1), rs.getInt(2),rs.getDate(3)));
            }
        }catch(SQLException exc){
             System.err.println(exc.getMessage());
        }
        return (Favoris) list ;
    }
    

    
    
}
