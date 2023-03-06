/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import enteties.event;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utils.Connection;

/**
 *
 * @author sywar
 */
public class EventController implements IEventController<event>{

    
    
    /////////////   CRUD    ////////////
    
    
    
    @Override
    public boolean ajouter(event e) {
    try {
        String querry= "INSERT INTO evenement(`prix`,`dateEvenement`, `description` , `photo`, `adresse` ) VALUES ('"+e.getPrix()+"','"+e.getDate_event()+"','"+e.getDescription()+"','"+e.getPhoto()+"','"+e.getAdresse()+"')";
        Statement stm = Connection.getInstance().getCnx().createStatement();
    
    stm.executeUpdate(querry);
    System.out.println("Evenement ajouté ✔");
    return true;
    
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());   
        return false;
}
}

    
    
    
    @Override
    public List<event> afficher() {
     List<event> events = new ArrayList();
        try {
       
        String querry ="SELECT * FROM `evenement`";
        Statement stm = Connection.getInstance().getCnx().createStatement();
            ResultSet rs= stm.executeQuery(querry);
        while (rs.next()){
            event e = new event();
            
            
             ImageView img = new ImageView();
                Image image;
                try {
                    if (rs.getString("photo") == null) {
                    } else if (rs.getString("photo") != null) {
                         image = new Image(new FileInputStream(("C:\\Users\\chahi\\Desktop\\SOUND-ON-Desktop-GererEvent\\src\\Assests\\"+rs.getString("photo"))));
                        img.setImage(image);
                        img.setPreserveRatio(false);
                        img.setFitWidth(50);
                        img.setFitHeight(50);

                    }
                } catch (FileNotFoundException ex) {
                    try {
                        System.out.println(ex.getMessage());
                        image = new Image(new FileInputStream(("C:\\Users\\chahi\\Desktop\\SOUND-ON-Desktop-GererEvent\\src\\Assests\\"+"search.png")));
                        img.setImage(image);
                        img.setPreserveRatio(true);
                        img.setFitWidth(50);
                        img.setFitHeight(50);
                    } catch (FileNotFoundException ex1) {
                        Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                }
            
            e.setId(rs.getInt(1));
            e.setDate_event((rs.getString("dateEvenement")));
            e.setPhoto(rs.getString("photo"));

            e.setAdresse(rs.getString("adresse"));
            e.setPrix(rs.getInt("prix"));
            e.setDescription(rs.getString("description"));

            events.add(e); 
}   
    } catch (SQLException ex) {
       System.out.println(ex);
        }
    return events;
    }
    
    
    
    

     @Override
    public boolean modifier(event e, int id) {
          try {
        String querry="UPDATE evenement SET dateEvenement = '"+e.getDate_event()+"', prix = '"+e.getPrix()+"', description= '"+e.getDescription()+"', photo = '"+e.getPhoto()+"', adresse = '"+e.getAdresse()+"' WHERE evenement.`id` = "+id+";";
        Statement stm =  Connection.getInstance().getCnx().createStatement();
        stm.executeUpdate(querry);
        System.out.println("Evenement modifié ✔");
                return true;

    } catch (SQLException ex) {
            System.out.println(ex.getMessage());
       }
        return false;
}

    
    
    
    
    @Override
    public boolean supprimer(int id) {
        
         try {
        String querry="DELETE FROM evenement WHERE evenement.`id` = '"+id+"'";

        Statement stm = Connection.getInstance().getCnx().createStatement();
    
    stm.executeUpdate(querry);
    
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    
    }return false ;
         }
    
    
    
    
    ////////// RECHERCHE //////////////
    
        @Override

     public List<event> rechercheAdresse(String adresse){
     List<event> events = afficher();
    
     List<event> resultat=events.stream().filter(event->adresse.equals(event.getAdresse())).collect(Collectors.toList());
        return resultat;   
    }
     
     @Override

     public List<event> recherchePrix(int prix){
     List<event> events = afficher();
    
     List<event> resultat=events.stream().filter(event->{
         return  String.valueOf(prix).equals(event.getPrix());
     }).collect(Collectors.toList());
        return resultat;   
    }
    
     
     
     
         ////////// TRI //////////////
     
         @Override

     public List<event> triDate() {
    List<event> events = afficher();
   
    List<event> resultat=events.stream().sorted( Comparator.comparing(event::getDate_event)).collect(Collectors.toList());
      return resultat;
      
}
  @Override

     public List<event> triPrix() {
    List<event> events = afficher();
   
    List<event> resultat=events.stream().sorted( Comparator.comparing(event::getPrix)).collect(Collectors.toList());
      return resultat;
      
}
    
}
