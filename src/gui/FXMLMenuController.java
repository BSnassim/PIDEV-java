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

public class FXMLMenuController implements Initializable {

     @FXML
    private Button album;

    @FXML
    private Button clothe;

    @FXML
    private Button flyer;
    @FXML
    private Button back;
     
    
    
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onActionback(ActionEvent event) {
        try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLMenu1.fxml"));
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
    private void onActionClothes(ActionEvent event) {
        try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLProduit.fxml"));
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
    
}
