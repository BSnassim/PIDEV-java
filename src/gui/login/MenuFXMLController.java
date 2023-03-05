/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.login;

import gui.backOffice.AddArtistFXMLController;
import gui.backOffice.AddFavFXMLController;
import gui.backOffice.AddUserFXMLController;
import gui.backOffice.DashboardFXMLController;
import gui.backOffice.DashboardFavFXMLController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ranim Ahmadi
 */
public class MenuFXMLController implements Initializable {
    
    
    
    @FXML
    private Button add_user;
    @FXML
    private Button display_user;
    @FXML
    private Button add_artist;
    @FXML
    private Button display_artist;
    @FXML
    private Button add_favorit;
    @FXML
    private Button display_favorit;
    @FXML
    private Button close;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    





    

    @FXML
    private void addUser(MouseEvent event) {
        
    }

    
    
    
    @FXML
    private void addArtist(MouseEvent event) {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../backOffice/AddArtistFXML.fxml"));
        Parent root = loader.load(); 
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        stage.toFront(); //pour s'assurer que la nouvelle fenêtre est au premier plan
        stage.setOnCloseRequest(e -> {
            // Si vous voulez effectuer une action avant de fermer la fenêtre actuelle,
            // vous pouvez la placer ici.
        });
    } catch (IOException ex) {
        Logger.getLogger(AddFavFXMLController.class.getName()).log(Level.SEVERE, null, ex);
    }  
    }

   
    
    @FXML
    private void addFavourite(MouseEvent event) {
        
        
         try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../backOffice/AddFavFXML.fxml"));
        Parent root = loader.load(); 
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        stage.toFront(); //pour s'assurer que la nouvelle fenêtre est au premier plan
        stage.setOnCloseRequest(e -> {
            // Si vous voulez effectuer une action avant de fermer la fenêtre actuelle,
            // vous pouvez la placer ici.
        });
    } catch (IOException ex) {
        Logger.getLogger(AddFavFXMLController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }



    

    @FXML
    private void displayUser(MouseEvent event) {
        
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../backOffice/dashboardFXML.fxml"));
        Parent root = loader.load(); 
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        stage.toFront(); //pour s'assurer que la nouvelle fenêtre est au premier plan
        stage.setOnCloseRequest(e -> {
            // Si vous voulez effectuer une action avant de fermer la fenêtre actuelle,
            // vous pouvez la placer ici.
        });
    } catch (IOException ex) {
        Logger.getLogger(DashboardFXMLController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    @FXML
    private void displayArtist(MouseEvent event) {
        
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../backOffice/AddArtistFXML.fxml"));
        Parent root = loader.load(); 
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        stage.toFront(); //pour s'assurer que la nouvelle fenêtre est au premier plan
        stage.setOnCloseRequest(e -> {
            // Si vous voulez effectuer une action avant de fermer la fenêtre actuelle,
            // vous pouvez la placer ici.
        });
    } catch (IOException ex) {
        Logger.getLogger(AddArtistFXMLController.class.getName()).log(Level.SEVERE, null, ex);
    }
        
    }

    @FXML
    private void displayFav(MouseEvent event) {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../backOffice/dashboardFavFXML.fxml"));
        Parent root = loader.load(); 
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        stage.toFront(); //pour s'assurer que la nouvelle fenêtre est au premier plan
        stage.setOnCloseRequest(e -> {
            // Si vous voulez effectuer une action avant de fermer la fenêtre actuelle,
            // vous pouvez la placer ici.
        });
    } catch (IOException ex) {
        Logger.getLogger(DashboardFavFXMLController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    @FXML
    private void onclickclose(ActionEvent event) {
          
       
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
     
        stage.close();
         //pour s'assurer que la nouvelle fenêtre est au premier plan
     
  
    }

    @FXML
    private void add(ActionEvent event) {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../backOffice/addUserFXML.fxml"));
        Parent root = loader.load(); 
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        stage.toFront(); //pour s'assurer que la nouvelle fenêtre est au premier plan
        stage.setOnCloseRequest(e -> {
            // Si vous voulez effectuer une action avant de fermer la fenêtre actuelle,
            // vous pouvez la placer ici.
        });
    } catch (IOException ex) {
        Logger.getLogger(AddUserFXMLController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
}
