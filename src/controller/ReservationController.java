/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import enteties.event;
import enteties.reservation;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import utils.Connection;

/**
 *
 * @author sywar
 */
public class ReservationController implements IReservationController<reservation>{
    
     @Override
    public boolean ajouter(reservation r) {
    try {
        String querry= "INSERT INTO reservation(`id_Artiste`,`id_Event`, `payement`  ) VALUES ('"+r.getId_Artiste()+"','"+r.getId_event()+"','"+r.getPayement()+"')";
        Statement stm = Connection.getInstance().getCnx().createStatement();
    
    stm.executeUpdate(querry);
        return true;

    
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    
}
        return false;

}

    
    
    
    
    @Override
    public List<reservation> afficher() {
     List<reservation> reservations = new ArrayList();
        try {
       
        String querry ="SELECT * FROM `reservation`";
        Statement stm = Connection.getInstance().getCnx().createStatement();
            ResultSet rs= stm.executeQuery(querry);
        while (rs.next()){
            reservation r = new reservation();
            
            r.setId(rs.getInt(1));
            r.setId_Artiste(rs.getInt("id_Artiste"));
            r.setId_Artiste(rs.getInt("id_Event"));
            r.setPayement(rs.getString("payement"));

            reservations.add(r); 
}
        
    
    } catch (SQLException ex) {
       System.out.println(ex);
        }
    return reservations;
    }
    
    
    
    

     @Override
    public boolean modifier(reservation r, int id) {
          try {
        String querry="UPDATE reservation SET id_Artiste = '"+r.getId_Artiste()+"', id_Event = '"+r.getId_event()+"' WHERE reservation.`id` = "+id+";";
        Statement stm =  Connection.getInstance().getCnx().createStatement();
        stm.executeUpdate(querry);
                 return true;

    } catch (SQLException ex) {
            System.out.println(ex.getMessage());
       }
         return false;
}

    @Override
    public boolean supprimer(reservation r) {
        
         try {
            String requete = "DELETE FROM reservation where id = ?";
            PreparedStatement pst = Connection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, r.getId());
            pst.executeUpdate();

            System.out.println("Reservation supprimée ✔");

            return true;

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return false;
         }

    
     @Override
     public int nbSurPlace(){
     String req="SELECT COUNT(*) FROM reservation WHERE payement='Sur Place' ";
      
      int nb =0;
        
        try {
          Statement stm = Connection.getInstance().getCnx().createStatement();
            ResultSet rs = stm.executeQuery(req);
            while(rs.next()){
             nb= rs.getInt(1);
              System.out.println(nb);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return nb;
    }
     
     @Override
     public int nbSurEnLigne(){
     String req="SELECT COUNT(*) FROM reservation WHERE payement='En Ligne' ";
      
      int nb =0;
        
        try {
          Statement stm = Connection.getInstance().getCnx().createStatement();
            ResultSet rs = stm.executeQuery(req);
            while(rs.next()){
             nb= rs.getInt(1);
              System.out.println(nb);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return nb;
    }
   
    
    
}
