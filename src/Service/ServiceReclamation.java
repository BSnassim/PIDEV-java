/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.EtatEnum;
import Entities.Reclamation;
import Entities.TypeEnum;
import Utils.MyConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author houss
 */
public class ServiceReclamation implements IService<Reclamation>{
    
    Connection conn;
    PreparedStatement ste;

    public ServiceReclamation() {
        conn=MyConnection.getInstance().getConnection();
    }

    @Override
    public void add(Reclamation r) {
 
        String sql = "insert into reclamation(description,etat,type,dateCreation,id_User) Values(?,?,?,?,?)";
        try {
            ste=conn.prepareStatement(sql);
            ste.setString(1, r.getDescription());
            ste.setString(2, r.getEtat().toString());
            ste.setString(3, r.getType().toString());
            ste.setDate(4, new Date(System.currentTimeMillis()));
            ste.setInt(5, r.getId_User());
               
            
            ste.executeUpdate(); // exucution de requete sql 
            System.out.println("reclamation Ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
    }

    @Override
    public void update(Reclamation r) {
         String sql = "UPDATE reclamation SET description=?, etat=?,type=?,dateUpdate=? WHERE id=?";
try {
            ste=conn.prepareStatement(sql);
            ste.setString(1, r.getDescription());
            ste.setString(2, r.getEtat().toString());
            ste.setString(3, r.getType().toString());
            ste.setDate(4, new Date(System.currentTimeMillis()));
            ste.setInt(5, r.getId());
               
            
            ste.executeUpdate();
            System.out.println("reclamation Updated");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 

    }

    @Override
    public void Delete(Reclamation r) {
        String sql = "DELETE from reclamation where id= '"+r.getId()+"' "; 
        String sql1="DELETE from reponse where idReclamation= '"+r.getId()+"' "; 
        try{

            
           Statement st= conn.createStatement();
           st.executeUpdate(sql1);        
           st.executeUpdate(sql);
           System.out.println("Reclamation supprimé avec succés !");
       }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }       }

    @Override
    public List<Reclamation> Show() {
        List<Reclamation> Reclamationlist = new ArrayList<>();
        try{
        Statement st= conn.createStatement();
        String query = "select * from reclamation";
        
        ResultSet rs;
        rs = st.executeQuery(query);
        Reclamation reclamation;
        while (rs.next()) {
           reclamation = new Reclamation(rs.getInt("id"),rs.getString("description")
                   ,EtatEnum.valueOf(rs.getString("etat")),TypeEnum.valueOf(rs.getString("type"))
                   ,rs.getDate("dateCreation"),rs.getDate("DATEuPDATE"),rs.getInt("id_user")); 
            Reclamationlist.add(reclamation);

        }
         return Reclamationlist;    
         }catch(SQLException ex){
                         System.out.println(ex.getMessage());

         }
        return Reclamationlist;
    }

    @Override
    public Reclamation getById(int id) {
        Reclamation r=new Reclamation();
        try{
        Statement st= conn.createStatement();
        String query = "select * from reclamation where id="+id+"";
        ResultSet rs;
        rs = st.executeQuery(query);
        while (rs.next()) {
           r = new Reclamation(rs.getInt("id"),rs.getString("description")
                   ,EtatEnum.valueOf(rs.getString("etat")),TypeEnum.valueOf(rs.getString("type"))
                   ,rs.getDate("dateCreation"),rs.getDate("DATEuPDATE"),rs.getInt("id_user")); 
         

        }
         return r;    
         }catch(SQLException ex){
                         System.out.println(ex.getMessage());

         }
        return r;
    }
    
    
    
}
