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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private VBox chosenFruitCard;

    @FXML
    private ImageView fruitImg;

    @FXML
    private Label fruitNameLable;

    @FXML
    private Label fruitPriceLabel;

    @FXML
    private ScrollPane scroll;
    
    private ProduitController produitcontroller;
    
    private List<Produit> produits = new ArrayList<>();
    
    private List<Produit> getData(){
      //  List<Produit> produits = new ArrayList<>();
       // Produit produit;
       //scroll.(produitcontroller.afficherProduit());
        return produitcontroller.afficherProduit();
        
       /* for(int i=0; i<20; i++){
            produit = new Produit();
            produit.setId(i);
            produit.setPrix(49);
            produit.setImage("/img/pullnoir.png");
            produit.setLibelle("pull");
            produit.setType("0C090A");
          //  produit.*/
          
        
        
        
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
