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
import java.util.List;
import model.ImageReclamation;

/**
 *
 * @author Administrateur
 */
public class ServiceImage implements IService<ImageReclamation>{
    Connection conn;
    PreparedStatement ste;

    public ServiceImage() {
                conn=MyConnection.getInstance().getConnection();

    }
    
    

    @Override
    public void add(ImageReclamation entity) {
String sql = "insert into ImageReclamation(path) Values(?)";
        try {
            ste=conn.prepareStatement(sql);
            ste.setString(1, entity.getPath());
               
            
            ste.executeUpdate();
            System.out.println("reclamation Ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }     }

    @Override
    public void update(ImageReclamation entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Delete(ImageReclamation entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ImageReclamation> Show() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ImageReclamation getById(int id) {
        ImageReclamation r=new ImageReclamation();
        try{
        Statement st= conn.createStatement();
        String query = "select * from imagereclamation where id="+id+"";
        ResultSet rs;
        rs = st.executeQuery(query);
        while (rs.next()) {
           r = new ImageReclamation(rs.getInt("id"),rs.getString("path")); 
         

        }
         return r;    
         }catch(SQLException ex){
                         System.out.println(ex.getMessage());

         }
        return r;
    }
    
}
