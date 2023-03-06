/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enteties;

import java.time.LocalDate;
import java.sql.Date;
import java.time.ZonedDateTime;
import javafx.beans.property.ObjectProperty;
import javafx.scene.image.ImageView;

/**
 *
 * @author sywar
 */
public class event {
    
     private int id;
    private String date_event;   
    private String description;
    private String photo;
    private ImageView imageView;
    private String adresse;
    private int prix;

   


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate_event() {
        return date_event;
    }

    public void setDate_event(String date_event) {
        this.date_event = date_event;
    }
    
  

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public event(int id, String date_event, String description, String photo,  String adresse, int prix) {
        this.id = id;
        this.date_event = date_event;
        this.description = description;
        this.photo = photo;
       // this.imageView = imageView;
        this.adresse = adresse;
        this.prix = prix;

    }

    public event(String date_event, String description, String photo, String adresse, int prix) {
        this.date_event = date_event;
        this.description = description;
        this.photo = photo;
        //this.imageView = imageView;
        this.adresse = adresse;
        this.prix = prix;

    }

    public event() {
    }

    @Override
    public String toString() {
        return "event{" + "id=" + id + ", date_event=" + date_event + ", description=" + description + ", photo=" + photo + ", imageView=" + imageView + ", adresse=" + adresse + ", prix=" + prix + '}';
    }


  
    
}
