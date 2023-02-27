/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Marwen.M
 */
public class FXMLMenu1Controller implements Initializable {

    /**
     * Initializes the controller class.
     */

    @FXML
    private Button acheter;

    @FXML
    void onActionAcheter(ActionEvent event) {

        try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLMenu.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.show();
			stage.toFront();
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
    }
    
    @FXML
    void onActionVente(ActionEvent event) {
        try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLvente.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.show();
			stage.toFront();
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
