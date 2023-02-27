/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Controller.ProduitController;
import java.awt.Label;
import java.awt.ScrollPane;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import model.Produit;

/**
 * FXML Controller class
 *
 * @author Marwen.M
 */
public class FXMLProduitController implements Initializable {

    /**
     * Initializes the controller class.
     */
     @FXML
    private Button add;

    @FXML
    private VBox chosenFruitCard;

    @FXML
    private ComboBox<String> colortxt;

    @FXML
    private javafx.scene.control.Label namalabel;

    @FXML
    private javafx.scene.control.Label prixlabel;

    @FXML
    private ImageView prodimg;

    @FXML
    private javafx.scene.control.ScrollPane scroll;

    @FXML
    private TextField searchtxt;

    @FXML
    private ComboBox<String> taillecombo;
    @FXML
    private ComboBox<String> cbcact;
    @FXML
    private ComboBox<Float> cbprix;
    @FXML
    private ComboBox<String> cbcolor;
    @FXML
    private Button filter;
    @FXML
    private Button refresh;

    @FXML
    void addToCart(ActionEvent event) {
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void selectcat(ActionEvent event) {
    }

    @FXML
    private void selectprix(ActionEvent event) {
    }

    @FXML
    private void selectcolor(ActionEvent event) {
    }

    @FXML
    private void filteraction(ActionEvent event) {
    }

    @FXML
    private void refreshaction(ActionEvent event) {
    }
    
}
