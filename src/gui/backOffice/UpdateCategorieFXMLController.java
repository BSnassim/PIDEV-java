/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.backOffice;

import gui.backOffice.CategorieBackFXMLController;
import interfaces.IcategorieController;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Catalogue;
import model.Categorie;
import services.CategorieService;

/**
 * FXML Controller class
 *
 * @author Siwar Ahmadi
 */
public class UpdateCategorieFXMLController implements Initializable {

    @FXML
    private TextField name_TF;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        name_TF.setText(String.valueOf(CategorieBackFXMLController.catt.getNom()));
    }

    @FXML
    private void valider(ActionEvent event) throws IOException {
        
        String name = name_TF.getText();
        
        IcategorieController inter = new CategorieService();
        Categorie c =new Categorie(CategorieBackFXMLController.catt.getId(),name);
        inter.modifierCategorie(c);
            Parent page1 = FXMLLoader.load(getClass().getResource("CategorieBackFXML.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        
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
    



