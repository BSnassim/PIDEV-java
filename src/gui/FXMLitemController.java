/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Interfaces.MyListener;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import model.Produit;
import sun.applet.Main;

/**
 * FXML Controller class
 *
 * @author Marwen.M
 */
public class FXMLitemController implements Initializable {

    
    @FXML
    private Label namelabel1;

    @FXML
    private Label prixlabel1;

    @FXML
    private ImageView prodimg1;

    @FXML
    private VBox vbox;
    
     @FXML
    private void click(MouseEvent mouseEvent) {
        
        myListener.onClickListener(fruit);
        
       
    }

    private Produit fruit;
    
    private MyListener myListener;

    public void setData(Produit fruit, MyListener myListener) {
        this.fruit = fruit;
        this.myListener = myListener;
        namelabel1.setText(fruit.getLibelle());
        prixlabel1.setText(""+fruit.getPrix());
        
        Image x = new Image(fruit.getImage());
        prodimg1.setImage(x);
    }
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    private static class pidev {

        public pidev() {
        }
    }
    
}
