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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.FloatStringConverter;
import model.Produit;

/**
 * FXML Controller class
 *
 * @author Marwen.M
 */
public class FXMLProduitController implements Initializable {

    ObservableList<String> types =FXCollections.observableArrayList("Type","vetements","cd");
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
    private TableColumn<Produit, Integer> qte;
    @FXML
    private TableColumn<Produit, String> taille;

    @FXML
    private TextField taillep;


    @FXML
    private TextField qtep;
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
        tableprod.setEditable(true);
        typep.setItems(types);
        
        senddata();
      //   Produit p= new Produit(1,"moh","xxxxxxx","zzz","type",1);
      //   ps.ajouterProduit(p);
        
    }    
/*
    public void senddata()
    {
        
        
          tvi.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("id"));
          tvt.setCellValueFactory(new PropertyValueFactory<Produit,String>("type"));
          tvt.setEditable(true);
        tvn.setCellValueFactory(new PropertyValueFactory<Produit,String>("libelle"));
        tvd.setCellValueFactory(new PropertyValueFactory<Produit,String>("descr"));
        tvimg.setCellValueFactory(new PropertyValueFactory<Produit,String>("image"));
        tvprix.setCellValueFactory(new PropertyValueFactory<Produit, Float>("prix"));
        
     ///  user_id.setCellValueFactory(new PropertyValueFactory<Article,String>("users"));
     
   
         
        tableprod.setItems(FXCollections.observableArrayList( ps.afficherProduit()));
    }*/
    public void senddata()
{
     tvi.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("id"));
    tvt.setCellValueFactory(new PropertyValueFactory<Produit,String>("type"));
    tvt.setEditable(true);
    tvt.setCellFactory(TextFieldTableCell.forTableColumn());
    taille.setCellValueFactory(new PropertyValueFactory<Produit,String>("taille"));
    taille.setEditable(true);
    qte.setCellValueFactory(new PropertyValueFactory<Produit,Integer>("qte"));
    qte.setEditable(true);
    tvn.setCellValueFactory(new PropertyValueFactory<Produit,String>("libelle"));
    tvn.setEditable(true);
    tvn.setCellFactory(TextFieldTableCell.forTableColumn()); // use TextFieldTableCell for libelle column
    tvd.setCellValueFactory(new PropertyValueFactory<Produit,String>("descr"));
    tvd.setEditable(true);
    tvd.setCellFactory(TextFieldTableCell.forTableColumn()); // use TextFieldTableCell for descr column
    tvimg.setCellValueFactory(new PropertyValueFactory<Produit,String>("image"));
    tvimg.setEditable(true);
    tvimg.setCellFactory(TextFieldTableCell.forTableColumn()); // use TextFieldTableCell for image column
    tvprix.setCellValueFactory(new PropertyValueFactory<Produit, Float>("prix"));
    tvprix.setEditable(true);
    tvprix.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter())); // use TextFieldTableCell with FloatStringConverter for prix column
   
    tableprod.setItems(FXCollections.observableArrayList( ps.afficherProduit()));
   /*
    tvn.setOnEditCommit((TableColumn.CellEditEvent<Produit, String> event) -> {
        Produit produit = event.getRowValue();
        produit.setLibelle(event.getNewValue());
    });

    tvd.setOnEditCommit((TableColumn.CellEditEvent<Produit, String> event) -> {
        Produit produit = event.getRowValue();
        produit.setDescr(event.getNewValue());
    });

    tvimg.setOnEditCommit((TableColumn.CellEditEvent<Produit, String> event) -> {
        Produit produit = event.getRowValue();
        produit.setImage(event.getNewValue());
    });

    tvprix.setOnEditCommit((TableColumn.CellEditEvent<Produit, Float> event) -> {
        Produit produit = event.getRowValue();
        produit.setPrix(event.getNewValue());
    });*/
         
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
            Produit p = new Produit(Float.parseFloat((prixp.getText())),imgp.getText(),nomp.getText(),types.get(typep.getSelectionModel().getSelectedIndex()),descrp.getText(),Integer.parseInt(qtep.getText()),taillep.getText(),1);
            System.out.println(p);
            ps.ajouterProduit(p);
            senddata();
            
        }
    }

    @FXML
    private void updateproduit(ActionEvent event) {
        
    }

    @FXML
    private void deleteproduit(ActionEvent event) {
        Produit p = tableprod.getSelectionModel().getSelectedItem();
        ps.supprimerProduit(p.getId());
        senddata();
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
/*
    @FXML
    private void onproduitNomEdit(TableColumn.CellEditEvent<Produit, String> event) {
         Produit p = tableprod.getSelectionModel().getSelectedItem();
         p.setLibelle(event.getNewValue());
    }

    @FXML
    private void onproduitDescrEdit(TableColumn.CellEditEvent<Produit, String> event) {
         Produit p = tableprod.getSelectionModel().getSelectedItem();
         p.setDescr(event.getNewValue());
    }

    @FXML
    private void onproduitTypeEdit(TableColumn.CellEditEvent<Produit, String> event) {
         Produit p = tableprod.getSelectionModel().getSelectedItem();
         p.setType(event.getNewValue());
    }

    @FXML
    private void onproduitPrixEdit(TableColumn.CellEditEvent<String, Float> event) {
         Produit p = tableprod.getSelectionModel().getSelectedItem();
         p.setPrix(event.getNewValue());
    }
*/

    @FXML
    private void onproduitNomEdit(TableColumn.CellEditEvent<Produit, String> event) {
          Produit p = tableprod.getSelectionModel().getSelectedItem();
         p.setLibelle(event.getNewValue());
         ps.modifierProduit(p, p.getId());
         //System.out.println(p);
         //System.out.println(event.getNewValue());
    }

    @FXML
    private void onproduitDescrEdit(TableColumn.CellEditEvent<Produit, String> event) {
         Produit p = tableprod.getSelectionModel().getSelectedItem();
         p.setDescr(event.getNewValue());
         ps.modifierProduit(p, p.getId());
    }

    @FXML
    private void onproduitTypeEdit(TableColumn.CellEditEvent<Produit, String> event) {
        Produit p = tableprod.getSelectionModel().getSelectedItem();
         p.setType(event.getNewValue());
         ps.modifierProduit(p, p.getId());
    }

    @FXML
    private void onproduitPrixEdit(TableColumn.CellEditEvent<Produit, Float> event) {
         Produit p = tableprod.getSelectionModel().getSelectedItem();
         p.setPrix(event.getNewValue());
         ps.modifierProduit(p, p.getId());
    }
    
    
}
