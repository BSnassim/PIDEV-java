/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.frontOffice;

import services.ProduitController;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Produit;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Marwen.M
 */
public class FXMLventeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    ObservableList<String> types =FXCollections.observableArrayList("Type","vetements","Flyers","Albums");
    ProduitController ps = new ProduitController();
    @FXML
    private Button btnupload;

    @FXML
    private TextField descrt;

    @FXML
    private TextField imgt;

    @FXML
    private TextField nomt;

    @FXML
    private TextField prixt;

    @FXML
    private TableColumn<Produit, Integer> qte;

    @FXML
    private TextField qtet;

    @FXML
    private TableView<String> tableprod;

    @FXML
    private TableColumn<Produit, Integer> taille;

    @FXML
    private TextField taillet;

    @FXML
    private ComboBox<String> typet;
    
    @FXML
    void onActionR(ActionEvent event) {
        try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLMenu1.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.show();
			stage.toFront();
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
    }
    
    @FXML
    void insertproduit(ActionEvent event) {
        
        if(nomt.getText().isEmpty()  || descrt.getText().isEmpty() || (typet.getSelectionModel().getSelectedIndex()==0))
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
alert.setTitle("Error");
alert.setHeaderText("Add Error");
alert.setContentText("all fields must  not be empty !");

alert.showAndWait();
        }
        else
        {
            //float x = parseFloat(prixp.getText());
            Produit p = new Produit(Float.parseFloat((prixt.getText())),imgt.getText(),nomt.getText(),types.get(typet.getSelectionModel().getSelectedIndex()),descrt.getText(),Integer.parseInt(qtet.getText()),taillet.getText(),1);
            System.out.println(p);
            ps.ajouterProduit(p);
            Notifications notificationBuilder = Notifications.create()
        .title("Produit vendu")
        .text("votre produit a été vendu avec succes!!!!")
        .graphic(null)
        .hideAfter(Duration.seconds(5))
        .position(Pos.BOTTOM_RIGHT)
        .onAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("vendu avec succes");
            }
               });
        notificationBuilder.showConfirm();
            
        }
    }

    @FXML
    void onproduitDescrEdit(ActionEvent event) {

    }

    @FXML
    void onproduitNomEdit(ActionEvent event) {

    }

    @FXML
    void onproduitPrixEdit(ActionEvent event) {

    }

    @FXML
    void onproduitTypeEdit(ActionEvent event) {

    }

    @FXML
    void prodclick(MouseEvent event) {

    }

    @FXML
    void uploadimg(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG
                = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
        FileChooser.ExtensionFilter extFilterjpg
                = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extFilterPNG
                = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
        FileChooser.ExtensionFilter extFilterpng
                = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        fileChooser.getExtensionFilters()
                .addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);

         File f = fileChooser.showOpenDialog(new Stage());

         imgt.setText(f.getAbsoluteFile().toURI().toString());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        typet.setItems(types);
        
        
    }    
    
}
