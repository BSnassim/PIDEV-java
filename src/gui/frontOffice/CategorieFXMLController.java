/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.frontOffice;

import gui.backOffice.UpdateCatalogueFXMLController;
import java.awt.Button;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Categorie;
import services.CategorieService;

/**
 * FXML Controller class
 *
 * @author Siwar Ahmadi
 */
public class CategorieFXMLController implements Initializable { 
    
   
    @FXML
    private ComboBox<String> comb;

    @FXML
    private ComboBox<String> comb1;
private CategorieService cs = new CategorieService();
    @FXML
    private javafx.scene.control.Button sel3;
    @FXML
    private ImageView image2;
    @FXML
    private Text nom1;
    @FXML
    private Text nom2;
    @FXML
    private ImageView star1;
    @FXML
    private ImageView star2;
    @FXML
    private ImageView star3;
    @FXML
    private ImageView star4;
    @FXML
    private ImageView star5;
    
    private int starClicked = 0; 

    
   // void Select(ActionEvent event){ 
        
    //}
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        sel3.setDisable(true);
        List<String> list = cs.getallCategorie();
        comb.setItems(FXCollections.observableArrayList(list));  
        try {
            star1.setImage(new Image(new FileInputStream(".\\src\\gui\\ressources\\image\\starS.png")));
            star2.setImage(new Image(new FileInputStream(".\\src\\gui\\ressources\\image\\starS.png")));
            star3.setImage(new Image(new FileInputStream(".\\src\\gui\\ressources\\image\\starS.png")));
            star4.setImage(new Image(new FileInputStream(".\\src\\gui\\ressources\\image\\starS.png")));
            star5.setImage(new Image(new FileInputStream(".\\src\\gui\\ressources\\image\\starS.png")));
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CategorieFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (starClicked==0){
            List<ImageView> l = new ArrayList<ImageView>();
        l.add(star1);l.add(star2);l.add(star3);l.add(star4);l.add(star5);
        for (int i=0;i<5;i++){
            l.get(i).setImage(null);
        }
        }
    }     
    //@FXML
    //void addInputToComboBox(ActionEvent event){ 
       // cb_cat.getItems().add(textInput.getText());
    //}

    @FXML
    private void Select(javafx.event.ActionEvent event) {
        List<String> list = cs.getallCataloguesCategorie(comb.getValue());
        starClicked=0;
        if (list!=null){
            comb1.setItems(FXCollections.observableArrayList(list));  
        }
        else  {
            sel3.setDisable(true);
            comb1.setItems(null);  
        }
        if (starClicked==0){
            List<ImageView> l = new ArrayList<ImageView>();
            l.add(star1);l.add(star2);l.add(star3);l.add(star4);l.add(star5);
            for (int i=0;i<5;i++){
                l.get(i).setImage(null);
            }
        }
    }

    @FXML
    private void Select3(javafx.event.ActionEvent event) {
        nom1.setText(comb1.getValue());
        nom2.setText(comb.getValue());
        cs.incrementVisiteur2(nom2.getText());
        try {
               FXMLLoader loader = new FXMLLoader(getClass().getResource("CatalogueFXML.fxml"));
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
               Logger.getLogger(UpdateCatalogueFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Select2(javafx.event.ActionEvent event) throws FileNotFoundException {
        sel3.setDisable(false);
        nom1.setText(comb1.getValue());
        nom2.setText(comb.getValue());
        starClicked=0;
        
        if (starClicked==0){
            List<ImageView> l = new ArrayList<ImageView>();
        l.add(star1);l.add(star2);l.add(star3);l.add(star4);l.add(star5);
        for (int i=0;i<5;i++){
                l.get(i).setImage(new Image(new FileInputStream(".\\src\\gui\\ressources\\image\\starS.png")));
        }
        }
    }

    @FXML
    private void hideStar(MouseEvent event) {
        
    }

    @FXML
    private void showStar(MouseEvent event) throws FileNotFoundException {
        if (starClicked==0){
            List<ImageView> l = new ArrayList<ImageView>();
        l.add(star1);l.add(star2);l.add(star3);l.add(star4);l.add(star5);
        String index = ((ImageView)event.getSource()).getId().substring(((ImageView)event.getSource()).getId().length()-1, ((ImageView)event.getSource()).getId().length());
        for (int i=0;i<5;i++){
            if (i<Integer.parseInt(index)){
                l.get(i).setImage(new Image(new FileInputStream(".\\src\\gui\\ressources\\image\\star.png")));
            }
            else {
                l.get(i).setImage(new Image(new FileInputStream(".\\src\\gui\\ressources\\image\\starS.png")));
            }
        }
        }
        
    }

    @FXML
    private void fixStar(MouseEvent event) {
        if (starClicked==0){
            List<ImageView> l = new ArrayList<ImageView>();
            l.add(star1);l.add(star2);l.add(star3);l.add(star4);l.add(star5);
            String index = ((ImageView)event.getSource()).getId().substring(((ImageView)event.getSource()).getId().length()-1, ((ImageView)event.getSource()).getId().length());
            starClicked=Integer.parseInt(index);
            cs.incrementVisiteur(nom2.getText(),starClicked);
        }
        
        
    }
    
}
