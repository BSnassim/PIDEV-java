/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication;

import Controller.ProduitController;
import java.io.File;
import static java.lang.Float.parseFloat;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Produit;

/**
 * FXML Controller class
 *
 * @author Marwen.M
 */
public class FXMLProduitController implements Initializable {

    ObservableList<String> types =FXCollections.observableArrayList("xx","yyy","zzz");
    ProduitController ps = new ProduitController();
    @FXML
    private TableView<Produit> tableprod;
    @FXML
    private Button btninsert;
    @FXML
    private Button btnupdate;
    @FXML
    private Button btndelete;
    @FXML
    private TableColumn<Produit,Integer> tvi;
    @FXML
    private TableColumn<Produit, String> tvn;
    @FXML
    private TableColumn<Produit, String> tvd;
    @FXML
    private TableColumn<Produit, String> tvt;
    @FXML
    private TableColumn<Produit, String> tvimg;
    @FXML
    private TableColumn<Produit, Float> tvprix;
    @FXML
    private Button btnupload;
    @FXML
    private TextField nomp;
    @FXML
    private TextField descrp;
    @FXML
    private ComboBox<String> typep;
    @FXML
    private TextField imgp;
    @FXML
    private TextField prixp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        typep.setItems(types);
        senddata();
      //   Produit p= new Produit(1,"moh","xxxxxxx","zzz","type",1);
      //   ps.ajouterProduit(p);
        
    }    

    public void senddata()
    {
        /*
        private TableColumn<Produit,Integer> tvi;
    @FXML
    private TableColumn<Produit, String> tvn;
    @FXML
    private TableColumn<Produit, String> tvd;
    @FXML
    private TableColumn<Produit, String> tvt;
    @FXML
    private TableColumn<Produit, String> tvimg;
    @FXML
    private TableColumn<Produit, Float> tvprix;*/
          tvi.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("id"));
          tvt.setCellValueFactory(new PropertyValueFactory<Produit,String>("type"));
        tvn.setCellValueFactory(new PropertyValueFactory<Produit,String>("libelle"));
        tvd.setCellValueFactory(new PropertyValueFactory<Produit,String>("descr"));
        tvimg.setCellValueFactory(new PropertyValueFactory<Produit,String>("image"));
        tvprix.setCellValueFactory(new PropertyValueFactory<Produit, Float>("prix"));
        
     ///  user_id.setCellValueFactory(new PropertyValueFactory<Article,String>("users"));
     
   
         
        tableprod.setItems(FXCollections.observableArrayList( ps.afficherProduit()));
    }
    @FXML
    private void prodclick(MouseEvent event) {
    }

    @FXML
    private void insertproduit(ActionEvent event) {
         if(nomp.getText().isEmpty()  || descrp.getText().isEmpty() || (typep.getSelectionModel().getSelectedIndex()==0))
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
            Produit p = new Produit(Integer.parseInt((prixp.getText())),imgp.getText(),nomp.getText(),types.get(typep.getSelectionModel().getSelectedIndex()),descrp.getText(),1);
            System.out.println(p);
            ps.ajouterProduit(p);
            
        }
    }

    @FXML
    private void updateproduit(ActionEvent event) {
    }

    @FXML
    private void deleteproduit(ActionEvent event) {
    }

    @FXML
    private void uploadimg(ActionEvent event) {
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

         imgp.setText(f.getAbsoluteFile().toURI().toString());
    }

    @FXML
    private void nom_edit(TableColumn.CellEditEvent<Produit, String> event) {
    }

    @FXML
    private void prix_edit(TableColumn.CellEditEvent<Produit, Integer> event) {
    }

    
    
}
