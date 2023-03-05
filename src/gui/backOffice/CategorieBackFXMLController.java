/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.backOffice;

import static gui.backOffice.CatalogueBackFXMLController.catt;
import interfaces.IcategorieController;
import java.awt.Button;
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
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Catalogue;
import model.Categorie;
import services.CatalogueService;
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

    @FXML
    private javafx.scene.control.Button bu_add11;
    @FXML
    private TableView<Categorie> tableVieww;
    @FXML
    private javafx.scene.control.Button bu_add2;
    @FXML
    private TextField nomIn;
    @FXML
    private TextField idIn;
public static Categorie catt = null;
    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    private CategorieService cs = new CategorieService();
    @FXML
    private RadioButton idTrie;
    @FXML
    private ToggleGroup trie;
    @FXML
    private RadioButton nomTrie;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Categorie> categories= cs.afficherCategorie();
    
    TableColumn<Categorie, String> idCol = new TableColumn<>("id");
    idCol.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getId())));
    
    TableColumn<Categorie, String> nomCol = new TableColumn<>("nom");
    nomCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNom()));

    
    
    tableVieww.getColumns().addAll(idCol,nomCol);
  
    tableVieww.setItems(FXCollections.observableList(categories));
    }

    @FXML
    private void update(ActionEvent event) {
        
        if (catt!=null){
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

    }

    @FXML
    private void delete(ActionEvent event) {
        cs.supprimerCategorie(catt.getId());
        tableVieww.getItems().remove(catt);
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

    @FXML
    private void getSelected(MouseEvent event) {
        catt = tableVieww.getSelectionModel().getSelectedItem();
        idIn.setText(String.valueOf(catt.getId()));
        nomIn.setText(catt.getNom());
    }

    @FXML
    private void onIdTrie(ActionEvent event) {
        List<Categorie> categories= cs.afficherCategorie();
        List<Categorie> sortedCategoriess = categories.stream().sorted(Comparator.comparingInt(Categorie::getId)).collect(Collectors.toList());
        tableVieww.setItems(FXCollections.observableList(sortedCategoriess));
    }

    @FXML
    private void onNomTrie(ActionEvent event) {
        List<Categorie> categories= cs.afficherCategorie();
        List<Categorie> sortedCategoriess = categories.stream().sorted(Comparator.comparing(Categorie::getNom)).collect(Collectors.toList());
        tableVieww.setItems(FXCollections.observableList(sortedCategoriess));
    }
        
    }

