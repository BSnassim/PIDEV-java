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
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ImageReclamation;

/**
 *
 * @author saida
 */
public class ServiceReclamation implements IService<Reclamation>{
    
    Connection conn;
    PreparedStatement ste;

    public ServiceReclamation() {
        conn=MyConnection.getInstance().getConnection();
    }
    
    public int getIdImage() {
        int r=0;
        try{
        Statement st= conn.createStatement();
        String query = "select max(id)as id from imagereclamation";
        ResultSet rs;
        rs = st.executeQuery(query);
        while (rs.next()) {
           r = rs.getInt("id"); 
         

        }
         return r;    
         }catch(SQLException ex){
                         System.out.println(ex.getMessage());

         }
        return r;
    }

    @Override
    public void add(Reclamation r) {
 
        String sql = "insert into reclamation(description,etat,type,dateCreation,id_User,idImage) Values(?,?,?,?,?,?)";
        try {
            ste=conn.prepareStatement(sql);
            ste.setString(1, r.getDescription());
            ste.setString(2, r.getEtat().toString());
            ste.setString(3, r.getType().toString());
            ste.setDate(4, new Date(System.currentTimeMillis()));
            ste.setInt(5, r.getId_User());
            ste.setInt(6,getIdImage() );
               
            
            ste.executeUpdate();
            System.out.println("reclamation Ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
    }

    @Override
    public void update(Reclamation r) {
         String sql = "UPDATE reclamation SET description=?, etat=?,type=?,dateUpdate=?,id_User=?,idImage=? WHERE id=?";
try {
            ste=conn.prepareStatement(sql);
            ste.setString(1, r.getDescription());
            ste.setString(2, r.getEtat().toString());
            ste.setString(3, r.getType().toString());
            ste.setDate(4, new Date(System.currentTimeMillis()));
            ste.setInt(5, r.getId_User());
            ste.setInt(7, r.getId());
            ste.setInt(6,getIdImage() );
               
            
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
    public ObservableList<Reclamation> Show() {
        ObservableList<Reclamation> Reclamationlist = FXCollections.observableArrayList();
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
           reclamation.setIdImage(rs.getInt("idImage"));
            Reclamationlist.add(reclamation);

        }
         return Reclamationlist;    
         }catch(SQLException ex){
                         System.out.println(ex.getMessage());

         }
        return Reclamationlist;
    }
    
    public ObservableList<Reclamation> ShowByUser(int id) {
        ObservableList<Reclamation> Reclamationlist = FXCollections.observableArrayList();
        try{
        Statement st= conn.createStatement();
        String query = "select * from reclamation where id_user="+id+"";
        
        ResultSet rs;
        rs = st.executeQuery(query);
        Reclamation reclamation;
        while (rs.next()) {
           reclamation = new Reclamation(rs.getInt("id"),rs.getString("description")
                   ,EtatEnum.valueOf(rs.getString("etat")),TypeEnum.valueOf(rs.getString("type"))
                   ,rs.getDate("dateCreation"),rs.getDate("DATEuPDATE"),rs.getInt("id_user")); 
           reclamation.setIdImage(rs.getInt("idImage"));
            Reclamationlist.add(reclamation);

        }
         return Reclamationlist;    
         }catch(SQLException ex){
                         System.out.println(ex.getMessage());

         }
        return Reclamationlist;
    }
    
    
    // tekhou id user connecter date debut et date fin
    public ObservableList<Reclamation> ShowByDateUser(String begin,String Fin,int id) {
        String query="";
        if(begin.equals("") && Fin.equals("")){
             query = "select * from reclamation where id_user="+id+"";
        }else if(!begin.equals("") && Fin.equals("")){
         query = "select * from reclamation where id_user="+id+" and dateCreation>='"+begin+"'";
    }else if(begin.equals("") && !Fin.equals("")){
         query = "select * from reclamation where id_user="+id+" and dateCreation<='"+Fin+"'";
    }else{
         query = "select * from reclamation where id_user="+id+" and dateCreation<='"+Fin+"'"+" and dateCreation>='"+begin+"'";
    }
        ObservableList<Reclamation> Reclamationlist = FXCollections.observableArrayList();
        try{
        Statement st= conn.createStatement();
        
        
        ResultSet rs;
        rs = st.executeQuery(query);
        Reclamation reclamation;
        while (rs.next()) {
           reclamation = new Reclamation(rs.getInt("id"),rs.getString("description")
                   ,EtatEnum.valueOf(rs.getString("etat")),TypeEnum.valueOf(rs.getString("type"))
                   ,rs.getDate("dateCreation"),rs.getDate("DATEuPDATE"),rs.getInt("id_user")); 
           reclamation.setIdImage(rs.getInt("idImage"));
            Reclamationlist.add(reclamation);

        }
         return Reclamationlist;    
         }catch(SQLException ex){
                         System.out.println(ex.getMessage());

         }
        return Reclamationlist;
    }
    
    public ObservableList<Reclamation> ShowByDate(String begin,String Fin) {
        String query="";
        if(begin.equals("") && Fin.equals("")){
             query = "select * from reclamation ";
        }else if(!begin.equals("") && Fin.equals("")){
         query = "select * from reclamation where dateCreation>='"+begin+"'";
    }else if(begin.equals("") && !Fin.equals("")){
         query = "select * from reclamation where dateCreation<='"+Fin+"'";
    }else{
         query = "select * from reclamation where dateCreation<='"+Fin+"'"+" and dateCreation>='"+begin+"'";
    }
        ObservableList<Reclamation> Reclamationlist = FXCollections.observableArrayList();
        try{
        Statement st= conn.createStatement();
        
        
        ResultSet rs;
        rs = st.executeQuery(query);
        Reclamation reclamation;
        while (rs.next()) {
           reclamation = new Reclamation(rs.getInt("id"),rs.getString("description")
                   ,EtatEnum.valueOf(rs.getString("etat")),TypeEnum.valueOf(rs.getString("type"))
                   ,rs.getDate("dateCreation"),rs.getDate("DATEuPDATE"),rs.getInt("id_user")); 
           reclamation.setIdImage(rs.getInt("idImage"));
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
         r.setIdImage(rs.getInt("idImage"));

        }
         return r;    
         }catch(SQLException ex){
                         System.out.println(ex.getMessage());

         }
        return r;
    }
    
    
    
}
