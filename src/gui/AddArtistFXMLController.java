/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import controller.ArtisteController;
import interfaces.IartisteController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Artiste;

/**
 * FXML Controller class
 *
 * @author Ranim Ahmadi
 */
public class AddArtistFXMLController implements Initializable {

    private TextField name_TF;
    @FXML
    private TextField descDeAddArtist;
    @FXML
    private TextField originedeAddArtist;
    @FXML
    private Button boutonAddArtist;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
   
    private void add(ActionEvent event) {
        String name = name_TF.getText();
        Artiste a = new Artiste();
        IartisteController inter = new ArtisteController();

        if (name.length() == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("label de name est vide ");
            alert.show();
        } else {
            inter.ajouterArtiste(a);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("user insérée avec succés!");
            alert.show();
        }
    }

   
}