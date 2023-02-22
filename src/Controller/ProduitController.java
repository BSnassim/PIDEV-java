/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Interfaces.IproduitController;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Produit;
import utils.connexionDB;

/**
 *
 * @author Marwen.M
 */
public class ProduitController implements IproduitController {
    Statement ste;
    Connection conn = connexionDB.getInstance().getConnexion();

    @Override
    public void ajouterProduit(Produit pa) {
        try {
            String req = "INSERT INTO `produit`( `image`,`prix`,`libelle`,`type`,`description`,`id_Artiste`) VALUES ('" + pa.getImage() + "','" + pa.getPrix() + "','" + pa.getLibelle() + "','" + pa.getType() + "','" + pa.getDescr()+ "','" + pa.getId_Artiste()+ "')";
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("Produit ajouté!!!");
        } catch (SQLException ex) {
            System.out.println("Produit non ajouté");
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void modifierProduit(Produit pa, int id) {
        try {
            String req = "UPDATE `produit` SET `image` = '" + pa.getImage() + "' ,`libelle`='" +pa.getLibelle() +"' ,`description`='" +pa.getDescr()+"' ,`prix`='" +pa.getPrix() +"' ,`type`='" +pa.getType() +"' WHERE `id` = " + id;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Produit updated !");
        } catch (SQLException ex) {
            System.out.println("Produit not updated !");

            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerProduit(int id) {
try {
            String req = "DELETE FROM `produit` WHERE id = " + id;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("produit deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }

    @Override
    public List<Produit> afficherProduit() {
         List<Produit> list = new ArrayList<>();
        try {
            String req = "Select * from produit";
            Statement st = conn.createStatement();

            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                Produit pa = new Produit();
                pa.setImage(RS.getString("image"));
                pa.setLibelle(RS.getString("libelle"));
                pa.setPrix(RS.getFloat("prix"));
                pa.setType(RS.getString("type"));
                pa.setId_Artiste(RS.getInt("id_Artiste"));
                pa.setId(RS.getInt(1));
                pa.setDescr(RS.getString("description"));

                list.add(pa);
               // System.out.println(pa);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    @Override
    public Produit rechProduit(int id) {
        Produit pa = new Produit();
        try {
            String req = "Select * from produit where id ="+id;
            Statement st = conn.createStatement();

            ResultSet RS = st.executeQuery(req); 
            RS.first();
            pa.setImage(RS.getString("image"));
                pa.setLibelle(RS.getString("libelle"));
                pa.setPrix(RS.getFloat("prix"));
                pa.setType(RS.getString("type"));
                pa.setId_Artiste(RS.getInt("id_Artiste"));
            pa.setId(RS.getInt(1));
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    
            return pa;
    }
}


