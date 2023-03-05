/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Controller.Mailservice;
import Controller.ProduitController;
import Interfaces.MyListener;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import model.Produit;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.util.Duration;
import javax.mail.MessagingException;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Marwen.M
 */
public class FXMLProduitController implements Initializable {
    /**
     * Initializes the controller class.
     */
    ProduitController ps = new ProduitController();

    Mailservice mail = new Mailservice();
    
    
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
    
    
    
    

    private List<Produit> fruits = new ArrayList<>();
    private Image image;
    private MyListener myListener;
    @FXML
    private GridPane grid;
    @FXML
    private Button search;
   
    @FXML
    void addToCart(ActionEvent event) throws MessagingException {
        
        
        mail.sendMail("marwen.mamlouk@esprit.tn", 1);
        
        Notifications notificationBuilder = Notifications.create()
        .title("Produit Ajouter")
        .text("votre produit a été ajoutee avec succes")
        .graphic(null)
        .hideAfter(Duration.seconds(5))
        .position(Pos.BOTTOM_RIGHT)
        .onAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("ajout avec succes");
            }
               });
        notificationBuilder.showConfirm();
        
            
        
        
                }
    
    
        
        
     private void setChosenProduct(Produit p) {
        namalabel.setText(p.getLibelle());
        prixlabel.setText(" "+p.getPrix());
        Image x = new Image(p.getImage());
        prodimg.setImage(x);
        //prodimg.setImage(p.getImage());
        // image = new Image("../img/pullblanc.PNG");
        // prodimg.setImage(image);
        //  chosenFruitCard.setStyle("-fx-background-color: #" + " " + ";\n" +
        //     "    -fx-background-radius: 30;");
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        fruits.addAll(ps.afficherProduit());
         if (fruits.size() > 0) {
            setChosenProduct(fruits.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(Produit fruit) {
                   
                    setChosenProduct(fruit);
                }
            };
            
          
        }
          int column = 0;
        int row = 1;
        try {
          for (int i = 0; i < fruits.size(); i++) {
    FXMLLoader fxmlLoader = new FXMLLoader();
    fxmlLoader.setLocation(getClass().getResource("/gui/FXMLitem.fxml"));
    AnchorPane anchorPane = fxmlLoader.load();

    FXMLitemController itemController = fxmlLoader.getController();
    itemController.setData(fruits.get(i), myListener);

    final int index = i; // Declare final variable and assign value of i

    // Add mouse click listener
    anchorPane.setOnMouseClicked(e -> {
        // Code to be executed on mouse click
        // For example, you can get the fruit associated with the clicked item
       // System.out.println(fruits.get(index).getLibelle() + " Clicked on ");
       setChosenProduct(fruits.get(index));
    });

    if (column == 3) {
        column = 0;
        row++;
    }

    grid.add(anchorPane, column++, row);

    // Set grid dimensions
    grid.setMinWidth(Region.USE_COMPUTED_SIZE);
    grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
    grid.setMaxWidth(Region.USE_PREF_SIZE);
    grid.setMinHeight(Region.USE_COMPUTED_SIZE);
    grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
    grid.setMaxHeight(Region.USE_PREF_SIZE);

    GridPane.setMargin(anchorPane, new Insets(10));
}


        } catch (IOException e) {
            e.printStackTrace();
        }
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

    @FXML
    private void onActionSearch(ActionEvent event) {
        grid.getChildren().clear();
     int column = 0;
        int row = 1;
        try {
          for (int i = 0; i < fruits.size(); i++) {
              if (fruits.get(i).getLibelle().toLowerCase().equals(searchtxt.getText().toLowerCase())){
    FXMLLoader fxmlLoader = new FXMLLoader();
    fxmlLoader.setLocation(getClass().getResource("/gui/FXMLitem.fxml"));
    AnchorPane anchorPane = fxmlLoader.load();

    FXMLitemController itemController = fxmlLoader.getController();
    
    itemController.setData(fruits.get(i), myListener);
    
    final int index = i; // Declare final variable and assign value of i

    // Add mouse click listener
    anchorPane.setOnMouseClicked(e -> {
        // Code to be executed on mouse click
        // For example, you can get the fruit associated with the clicked item
       // System.out.println(fruits.get(index).getLibelle() + " Clicked on ");
       setChosenProduct(fruits.get(index));
    });

    if (column == 3) {
        column = 0;
        row++;
    }

    grid.add(anchorPane, column++, row);

    // Set grid dimensions
    grid.setMinWidth(Region.USE_COMPUTED_SIZE);
    grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
    grid.setMaxWidth(Region.USE_PREF_SIZE);
    grid.setMinHeight(Region.USE_COMPUTED_SIZE);
    grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
    grid.setMaxHeight(Region.USE_PREF_SIZE);

    GridPane.setMargin(anchorPane, new Insets(10));
}
          
           else if (searchtxt.getText().isEmpty()){
    FXMLLoader fxmlLoader = new FXMLLoader();
    fxmlLoader.setLocation(getClass().getResource("/gui/FXMLitem.fxml"));
    AnchorPane anchorPane = fxmlLoader.load();

    FXMLitemController itemController = fxmlLoader.getController();
    
    itemController.setData(fruits.get(i), myListener);
    
    final int index = i; // Declare final variable and assign value of i

    // Add mouse click listener
    anchorPane.setOnMouseClicked(e -> {
        // Code to be executed on mouse click
        // For example, you can get the fruit associated with the clicked item
       // System.out.println(fruits.get(index).getLibelle() + " Clicked on ");
       setChosenProduct(fruits.get(index));
    });

    if (column == 3) {
        column = 0;
        row++;
    }

    grid.add(anchorPane, column++, row);

    // Set grid dimensions
    grid.setMinWidth(Region.USE_COMPUTED_SIZE);
    grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
    grid.setMaxWidth(Region.USE_PREF_SIZE);
    grid.setMinHeight(Region.USE_COMPUTED_SIZE);
    grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
    grid.setMaxHeight(Region.USE_PREF_SIZE);

    GridPane.setMargin(anchorPane, new Insets(10));
}

          
          }} catch (IOException e) {
            e.printStackTrace();
        }

    }
       

   
    
}

