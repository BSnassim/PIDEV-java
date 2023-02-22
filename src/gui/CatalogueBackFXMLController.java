/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import static gui.CategorieBackFXMLController.id;
import static gui.CategorieBackFXMLController.nom;
import interfaces.IcatalogueController;
import interfaces.IcategorieController;
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
import model.Catalogue;
import services.CatalogueService;

/**
 * FXML Controller class
 *
 * @author Siwar Ahmadi
 */
public class CatalogueBackFXMLController implements Initializable {

    @FXML
    private ListView<Catalogue> listView;
    static int id ; 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
  
        ListView list2 = listView;
        Catalogue c = new Catalogue();
        IcatalogueController inter = new CatalogueService();
        List<Catalogue> list = inter.afficherCatalogue();
        for (int i = 0; i < list.size(); i++) {
            Catalogue cat = list.get(i);
            list2.getItems().add(cat);
        }

    }

    @FXML
    private void update(ActionEvent event) { 
         ListView<Catalogue> list = listView;
        IcatalogueController inter = new CatalogueService();
        int selectedID = list.getSelectionModel().getSelectedIndex();
        Catalogue c = list.getSelectionModel().getSelectedItem();
        nom = c.getNom();
        int id_categorie = c.getId_categorie(); 
        id = c.getId();

        try {

            Parent page1 = FXMLLoader.load(getClass().getResource("UpdateCatalogueFXML.fxml"));
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
        ListView<Catalogue> list = listView;
        IcatalogueController inter = new CatalogueService();
        int selectedID = list.getSelectionModel().getSelectedIndex();
        Catalogue c = list.getSelectionModel().getSelectedItem();
        System.out.println(c.getId());
        inter.supprimerCatalogue(c.getId());
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
