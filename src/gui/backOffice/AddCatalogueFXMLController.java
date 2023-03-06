/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.backOffice;

import interfaces.IcatalogueController;
import interfaces.IcategorieController;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Catalogue;
import model.Categorie;
import services.CatalogueService;
import services.CategorieService;

/**
 * FXML Controller class
 *
 * @author Siwar Ahmadi
 */
public class AddCatalogueFXMLController implements Initializable {  
    
   @FXML
    private TextField name_TF;
    @FXML
    private TextField id_cat_TF;
    @FXML
    private ImageView image;
public static Catalogue cat=new Catalogue();
private CategorieService cs = new CategorieService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
    
    @FXML
    private void add(ActionEvent event) {
        String name = name_TF.getText();
        int id_category = Integer.parseInt(id_cat_TF.getText());
        boolean idExistant  = cs.existIdCategorie(id_category);
        if (idExistant){
            Catalogue ca = new Catalogue(name, id_category,cat.getImage());
        IcatalogueController inter = new CatalogueService();
        
        if (name.length() == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("label de name est vide ");
            alert.show();
        } else {
            inter.ajouterCatalogue(ca);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Catalogue insérée avec succés!");
            alert.show();
        }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("id categorie non existant");
            alert.show();
        }
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
    private void uploadImage(ActionEvent event) {
    
        FileChooser fc = new FileChooser();
        File selected = fc.showOpenDialog(null);
        String extension = null;
        if( selected !=null )
        {
            InputStream stream;
            try {
                stream = new FileInputStream(selected.getAbsolutePath());
                cat.setImage(selected.getAbsolutePath());
                image.setImage(new Image(stream));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(AddCatalogueFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }  
}


    

