/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import interfaces.IcategorieController;
import java.awt.Button;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Categorie;
import services.CategorieService;

/**
 * FXML Controller class
 *
 * @author Siwar Ahmadi
 *
 */
public class CategorieBackFXMLController implements Initializable {

    @FXML
    private javafx.scene.control.Button bu_add;
    @FXML
    private javafx.scene.control.Button bu_add1;
    @FXML
    private ListView<Categorie> listView;

    static String nom;
    static int id ; 
    @FXML
    private javafx.scene.control.Button bu_add11;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ListView list2 = listView;
        Categorie c = new Categorie();
        IcategorieController inter = new CategorieService();
        List<Categorie> list = inter.afficherCategorie();
        for (int i = 0; i < list.size(); i++) {
            Categorie cat = list.get(i);
            list2.getItems().add(cat);
        }

    }

    @FXML
    private void update(ActionEvent event) {
        
        ListView<Categorie> list = listView;
        IcategorieController inter = new CategorieService();
        int selectedID = list.getSelectionModel().getSelectedIndex();
        Categorie c = list.getSelectionModel().getSelectedItem();
        nom = c.getNom();
        id = c.getId(); 

        try {

            Parent page1 = FXMLLoader.load(getClass().getResource("UpdateCategorieFXML.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(CategorieBackFXMLController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @FXML
    private void delete(ActionEvent event) {
        ListView<Categorie> list = listView;
        IcategorieController inter = new CategorieService();
        int selectedID = list.getSelectionModel().getSelectedIndex();
        Categorie c = list.getSelectionModel().getSelectedItem();
        System.out.println(c.getId());
        inter.supprimerCategorie(c.getId());
        list.getItems().remove(selectedID);
    }

    @FXML
    private void back(ActionEvent event) { 
        try {
               FXMLLoader loader = new FXMLLoader(getClass().getResource("menuFXML.fxml"));
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
        
    }

