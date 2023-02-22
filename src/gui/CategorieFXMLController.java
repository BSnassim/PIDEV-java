/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Button;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

/**
 * FXML Controller class
 *
 * @author Siwar Ahmadi
 */
public class CategorieFXMLController implements Initializable { 
    
   
    @FXML
    private ComboBox comb;

    @FXML
    private Label label;

    @FXML
    void Select(ActionEvent event) {

    }

    
     @FXML
   // void Select(ActionEvent event){ 
        
    //}
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        ObservableList<String> list = FXCollections.observableArrayList("Jazz","Rap","Pop","Rock","Classique","Francaise","Orientale","Mondial");
        //cb_cat.setItems(FXCollections.observableArrayList("Jazz","Rap","Pop","Rock","Classique","Francaise","Orientale","Mondial"));  
        comb.setItems(list);
    }     
    //@FXML
    //void addInputToComboBox(ActionEvent event){ 
       // cb_cat.getItems().add(textInput.getText());
    //}
    
}
