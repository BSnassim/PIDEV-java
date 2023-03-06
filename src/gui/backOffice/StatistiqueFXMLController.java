/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.backOffice;

import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.Catalogue;
import model.Categorie;
import services.CatalogueService;
import services.CategorieService;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class StatistiqueFXMLController implements Initializable {

    @FXML
    private TableView<Categorie> tableVieew;

    /**
     * Initializes the controller class.
     */
    private CategorieService cs = new CategorieService();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        List<Categorie> categories= cs.afficherCategorie();
        List<Categorie> sortedCategories = categories.stream()
    .sorted(Comparator.comparingInt(Categorie::getVisiteur).reversed())
    .collect(Collectors.toList());
    TableColumn<Categorie, String> idCol = new TableColumn<>("id");
    idCol.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getId())));
    
    TableColumn<Categorie, String> nomCol = new TableColumn<>("nom");
    nomCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNom()));

    TableColumn<Categorie, String> vCol = new TableColumn<>("Nombre de visiteur");
    vCol.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getVisiteur())));

    TableColumn<Categorie, String> sCol = new TableColumn<>("Rate");
    sCol.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getRate())));

    tableVieew.getColumns().addAll(idCol,nomCol, vCol,sCol);
  
    tableVieew.setItems(FXCollections.observableList(sortedCategories));
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
