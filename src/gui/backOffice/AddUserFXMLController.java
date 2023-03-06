/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.backOffice;


import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.connexionDB;

/**
 * FXML Controller class
 *
 * @author Ranim Ahmadi
 */
public class AddUserFXMLController implements Initializable {

    @FXML
    private TextField logdeAddUser;
       
    @FXML
    private TextField passworddeAddUser;
          
    @FXML
    private TextField ConfirmPassworddeAddUser;
             
    @FXML
    private TextField prenomdeAddUser;
                
    @FXML
    private TextField nomdeAddUser;
                   
    @FXML
    private TextField emaildeAddUser;
    @FXML
    private Button btnajout;
    @FXML
    private Button back;
    @FXML
    private Label lbllogin;
    @FXML
    private Label lblpwd;
    @FXML
    private Label lblname;
    @FXML
    private Label lbllastname;
    @FXML
    private Label lblemail;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    @FXML
    private void add(ActionEvent event) throws SQLException {
        insert();
System.out.println("6");
cancelBTN(event);
    }
    @FXML
    private void onclickback(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../login/menuFXML.fxml"));
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
    private void insert() throws SQLException {
        String Login = logdeAddUser.getText();
        String Password = passworddeAddUser.getText();
        String PasswordConfirmed = passworddeAddUser.getText();
        String firstName = prenomdeAddUser.getText();
        String LastName = nomdeAddUser.getText();
        String Email = emaildeAddUser.getText();
        String query = "INSERT INTO `utilisateur`( `login`, `password`, `nom`, `prenom`, `email`) VALUES ('" + Login + "','" + Password + "','" + firstName + "','" + LastName + "','" + Email + "')";
        PreparedStatement ps = connexionDB.getInstance().getConnexion().prepareStatement(query);
System.out.println("2");
            ps.executeUpdate();

    }

   

    private void cancelBTN(ActionEvent event) {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UtilisateurFXML.fxml"));
        Parent root = loader.load(); 
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        stage.toFront(); //pour s'assurer que la nouvelle fenêtre est au premier plan
        
    } catch (IOException ex) {
        Logger.getLogger(DashboardFXMLController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
}