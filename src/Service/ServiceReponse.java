/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.EtatEnum;
import Entities.Reclamation;
import Entities.Reponse;
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
public class ServiceReponse implements IService<Reponse>{

    Connection conn;
    PreparedStatement ste;
    
    public ServiceReponse() {
     conn=MyConnection.getInstance().getConnection();
    }

    @Override
    public void add(Reponse r) {
        String sql = "insert into reponse(message,dateCreation,idReclamation) Values(?,?,?)";
        try {
            ste=conn.prepareStatement(sql);
            ste.setString(1, r.getMessage());
            ste.setDate(2, new Date(System.currentTimeMillis()));
            ste.setInt(3, r.getReclamation().getId());
            ste.executeUpdate();
            System.out.println("reponse Ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }     }

    @Override
    public void update(Reponse r) {
        String sql = "UPDATE reponse SET message=?,dateUpdate=? WHERE id=? ";
        try {
            ste=conn.prepareStatement(sql);
            ste.setString(1, r.getMessage());
            ste.setDate(2, new Date(System.currentTimeMillis()));
            ste.setInt(3, r.getId());
            ste.executeUpdate();
            System.out.println("reponse Updated");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }     }

    @Override
    public void Delete(Reponse r) {
       String sql = "DELETE from reponse where id= '"+r.getId()+"' "; 
        try{
           Statement st= conn.createStatement();
           st.executeUpdate(sql);
           System.out.println("Reponse supprimé avec succés !");
       }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }     
    }

    @Override
    public List<Reponse> Show() {
        List<Reponse> Reponselist = new ArrayList<>();
        try{
        Statement st= conn.createStatement();
        String query = "select * from reponse";
        
        ResultSet rs;
        rs = st.executeQuery(query);
        Reponse reponse;
        while (rs.next()) {
           ServiceReclamation ser=new ServiceReclamation();
            Reclamation rec=new Reclamation();
            rec=ser.getById(rs.getInt("idReclamation"));
           reponse = new Reponse(rs.getInt("id"),rs.getString("message"),rs.getDate("dateCreation"),rs.getDate("DATEuPDATE"),rec); 
           Reponselist.add(reponse);
        }
         return Reponselist;    
         }catch(SQLException ex){
                         System.out.println(ex.getMessage());

         }
        return Reponselist;    }

    @Override
    public Reponse getById(int id) {
        Reponse r=new Reponse();
        try{
        Statement st= conn.createStatement();
        String query = "select * from reponse where id="+id+"";
        ResultSet rs;
        rs = st.executeQuery(query);
        while (rs.next()) {
            ServiceReclamation ser=new ServiceReclamation();
            Reclamation rec=new Reclamation();
            rec=ser.getById(rs.getInt("idReclamation"));
           r = new Reponse(rs.getInt("id"),rs.getString("message"),rs.getDate("dateCreation"),rs.getDate("DATEuPDATE"),rec); 
        }
         return r;    
         }catch(SQLException ex){
                         System.out.println(ex.getMessage());

         }
        return r;
    }
    
}
