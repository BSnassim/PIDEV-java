/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import animatefx.animation.Shake;
import com.jfoenix.controls.JFXDatePicker;
import controller.EventController;
import enteties.event;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.util.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.controlsfx.control.Notifications;


/**
 * FXML Controller class
 *
 * @author Sywar
 */
public class AddEventFXMLController implements Initializable {

    @FXML
    private TextField TfPrix;
    @FXML
    private TextField TfAdresse;
    @FXML
    private TextField TfDescription;
    @FXML
    private DatePicker TfDate;
    @FXML
    private ImageView PreviewImage;
   
    
    
    File selectedFile;
    private FileChooser Fc = new FileChooser();
    private File file;
    private static String pathImage = "";
    event e = new event();
    EventController ec = new EventController();
        private boolean update;
String query = null;
    Connection connection = null;
        int eventid;
    @FXML
    private TextField Td;
    private TextField url;


  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

           getQuery();

    }    

    
    
    @FXML
    private void BtnBack(MouseEvent event)  throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("EventFXML.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
       
      stage.setScene(new Scene(root));
        stage.show();
    }
    
   
    
//////UPLOAD IMAGE ////////
    @FXML
    private void BtnUpload(ActionEvent event) {
         File dest = new File("C:\\xampp\\htdocs\\Projet\\Uploads\\");
        Fc.setTitle("Open Resource File");
        Fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("images", "*.bmp", "*.png", "*.jpg", "*.gif"));
        selectedFile = Fc.showOpenDialog(null);

        if (selectedFile != null) {
            try {

                String Destination = "C:\\xampp\\htdocs\\Projet\\Uploads\\";
                File f = new File(dest, selectedFile.getName());

                FileUtils.copyFileToDirectory(selectedFile, dest);
                pathImage = selectedFile.getName();

                Image image = new Image(new FileInputStream(selectedFile), 200, 200, true, true);
                PreviewImage.setImage(image);
            } catch (IOException ex) {
                ex.getStackTrace();
            }
        }
    }
    
    
    
    
/////// ADD EVENT ////////
    @FXML
    private void BtnAdd(ActionEvent event) {
        
         /////// CONTROLE DE SAISIE ///////
        if (TfDate.getValue()== null) {
            TfDate.requestFocus();
            shake(TfDate);
            return;
        }
        
         if (TfPrix.getText().isEmpty()) {
            TfPrix.requestFocus();
            shake(TfPrix);
            return;
        }
        
          if (TfAdresse.getText().isEmpty()) {
            TfAdresse.requestFocus();
            shake(TfAdresse);
            return;
        }
         
        
         if (TfDescription.getText().isEmpty()) {
            TfDescription.requestFocus();
            shake(TfDescription);
            return;
        }

         
        if (PreviewImage.getImage()== null) {
            PreviewImage.requestFocus();
            shake(PreviewImage);
            return;
        }

        e.setDate_event(TfDate.getEditor().getText());
        e.setDescription(TfDescription.getText());
        e.setAdresse(TfAdresse.getText());
        e.setPrix(Integer.valueOf(TfPrix.getText()));
        e.setPhoto(pathImage);

        

        boolean result = ec.ajouter(e); 
        if (result) {

            TfDate.getEditor().clear();
            TfPrix.clear();
            TfDescription.clear();
            TfAdresse.clear();
           PreviewImage.imageProperty().set(null);
          
            
       Image image=new Image("/Assests/success.png");

        Notifications notifications=Notifications.create().position(Pos.BOTTOM_RIGHT);
        notifications.graphic(new ImageView(image));
        notifications.text("Evenement ajouté avec succée ✔");
        notifications.title("Success Message");
        notifications.hideAfter(Duration.seconds(3));
        notifications.show();

        }
    }
    
    
    void setTextField(int id, int prix, String description, String adresse) {

        eventid = id;
        //TfDate.setValue(e.getDate_event());
        TfPrix.setText(Integer.toString(e.getPrix()));
        TfAdresse.setText(e.getAdresse());
        TfDescription.setText(e.getDescription());

    }

    
     private void getQuery() {

        if (update == false) {
            
            query = "INSERT INTO `evenement`( `dateEvenement`, `prix`, `photo`,`adresse`, `description`) VALUES (?,?,?,?)";

        }else{
            query = "UPDATE `evenement` SET "
                    + "`dateEvenement`=?,"
                    + "`prix`=?,"
                    + "`photo`=?,"
                    + "`adresse`=?,"
                    + "`description`= ? WHERE id = '"+eventid+"'";
        }

    }
     
     void setUpdate(boolean b) {
        this.update = b;

    }
    
     
    public static void shake(Node node) {
        new Shake(node).play();
    }

    @FXML
    private void btndd(ActionEvent event) throws SQLException {
       
        if (TfDate.getValue()== null) {
            TfDate.requestFocus();
            shake(TfDate);
            return;
        }
        
         if (TfPrix.getText().isEmpty()) {
            TfPrix.requestFocus();
            shake(TfPrix);
            return;
        }
        
          if (TfAdresse.getText().isEmpty()) {
            TfAdresse.requestFocus();
            shake(TfAdresse);
            return;
        }
         
        
         if (TfDescription.getText().isEmpty()) {
            TfDescription.requestFocus();
            shake(TfDescription);
            return;
        }

         
        if (PreviewImage.getImage()== null) {
            PreviewImage.requestFocus();
            shake(PreviewImage);
            return;
        }

        e.setDate_event(TfDate.getEditor().getText());
        e.setDescription(TfDescription.getText());
        e.setAdresse(TfAdresse.getText());

        e.setPrix(Integer.valueOf(TfPrix.getText()));
        e.setPhoto(pathImage);
        

        boolean result = ec.modifier(e,Integer.parseInt(Td.getText())); 
        if (result) {

            TfDate.getEditor().clear();
            TfPrix.clear();
            TfDescription.clear();
            TfAdresse.clear();
           PreviewImage.imageProperty().set(null);
          
            
       Image image=new Image("/Assests/success.png");

        Notifications notifications=Notifications.create().position(Pos.BOTTOM_RIGHT);
        notifications.graphic(new ImageView(image));
        notifications.text("Evenement modifié avec succée ✔");
        notifications.title("Success Message");
        notifications.hideAfter(Duration.seconds(3));
        notifications.show();

        }
       
    }
    
    
    
    
   
    
}
