/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

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
    
    //private Listner listner;
    
    
//    private void click(MouseEvent mouseEvent)
//    {
//        listner.onClickListener(produit);
//    }
//    
//    public void  refresh()
//     {
//         vbox.getChildren().removeAll(vbox.getChildren());
//     }
//    public void setData(Produit prod, Listner listner) 
//    {
//       
//        this.produit=produit;
//        this.listner=listner;
//        namelabel1.setText(prod.getLibelle());
//        prixlabel1.setText(prod.getPrix()+ "DT");
//        Image image;
//            image = new Image(new FileInputStream(Badge.url_upload +prod.getImage()));
//            prodimg1.setImage(image);
//        
//        }
       
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
