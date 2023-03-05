/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.backOffice;

import interfaces.IcatalogueController;
import interfaces.IcategorieController;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Catalogue;
import services.CatalogueService;
import utils.ConnexionDB;

/**
 * FXML Controller class
 *
 * @author Siwar Ahmadi
 */
public class CatalogueBackFXMLController implements Initializable {

    private ListView<Catalogue> listView;
    static TableColumn<Catalogue, String> id ; 
    @FXML
    private TableView<Catalogue> tableVieww;
    /**
     * Initializes the controller class.
     */
    private CatalogueService cs = new CatalogueService();
    @FXML
    private TextField idIn;
    @FXML
    private TextField nomIn;
    @FXML
    private TextField idCaIn;
    public static Catalogue catt = null;
    @FXML
    private ImageView imaage;
    @FXML
    private TextField searchIN;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
  List<Catalogue> catalogues= cs.afficherCatalogue();
    
    TableColumn<Catalogue, String> idCol = new TableColumn<>("id");
    idCol.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getId())));
    
    TableColumn<Catalogue, String> nomCol = new TableColumn<>("nom");
    nomCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNom()));

    TableColumn<Catalogue, String> idCa = new TableColumn<>("id categorie");
    idCa.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getId_categorie())));
    
    
    tableVieww.getColumns().addAll(idCol,nomCol, idCa);
  
    tableVieww.setItems(FXCollections.observableList(catalogues));

    }
private String nomm=null;
private int idd=0;
    @FXML
    private void update(ActionEvent event) { 
        if (catt!=null){
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

        
    }

    @FXML
    private void delete(ActionEvent event) {
        
        cs.supprimerCatalogue(catt.getId());
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
      public ObservableList<Catalogue> getCatalogueList() {
        ObservableList<Catalogue> evenementsList = FXCollections.observableArrayList();
        Connection conn = ConnexionDB.getInstance().getConnexion();
        String query = "SELECT * FROM catalogue";
        Statement st;
        ResultSet rs;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Catalogue catalogue;
            while (rs.next()) {
                catalogue = new Catalogue(rs.getInt("id"), rs.getString("nom"), rs.getInt("id_categorie"));
                evenementsList.add(catalogue);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return evenementsList;

    }
      /*
    public void showusers() {
        ObservableList<Catalogue> list = getCatalogueList();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        id.setCellValueFactory(new PropertyValueFactory<>("prenom"));

        tableVieww.setItems(list);
        Callback<TableColumn<Catalogue, String>, TableCell<Catalogue, String>> cellFoctory = (TableColumn<Catalogue, String> param) -> {
            // make cell containing buttons
            final TableCell<Catalogue, String> cell = new TableCell<Catalogue, String>() {
                @Override
                public void updateItem(String item, boolean empty) {

                    Utilisateur utilisateur = null;
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {
                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
                        deleteIcon.setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                try {
                                    PreparedStatement ps = null;
                                    Utilisateur utilisateurs;
                                    utilisateurs = usertab.getSelectionModel().getSelectedItem();
                                    String query = "DELETE FROM `utilisateur` WHERE id =" + utilisateurs.getId();
                                    Connection conn = connexionDB();
                                    ps = conn.prepareStatement(query);

                                    ps.execute();

                                } catch (SQLException ex) {
                                    Logger.getLogger(CategorieBackFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        });

                        editIcon.setOnMouseClicked((new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                               
                               
                                
                                   
                                     Utilisateur utilisateurs = usertab.getSelectionModel().getSelectedItem();
                                System.out.println(utilisateurs);

                                logdeAddUser.setText(utilisateurs.getLogin());
                                password.setText(utilisateurs.getPassword());
                                lastname.setText(utilisateurs.getPrenom());
                                firstname.setText(utilisateurs.getNom());
                                email.setText(utilisateurs.getEmail());
                              

                            }

                        }));
                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);
                    }
                }

            };
            return cell;
        };
        actionscol.setCellFactory(cellFoctory);
        usertab.setItems(list);

    }
*/
    @FXML
    private void getSelected(MouseEvent event) throws FileNotFoundException {
        catt = tableVieww.getSelectionModel().getSelectedItem();
        InputStream stream = new FileInputStream(catt.getImage());
        imaage.setImage(new Image(stream));
        idIn.setText(String.valueOf(catt.getId()));
        nomIn.setText(catt.getNom());
        idCaIn.setText(String.valueOf(catt.getId_categorie()));
    }

    @FXML
    private void search(ActionEvent event) {
        if (!searchIN.getText().equals("")){
            List<Catalogue> cattt = cs.rechCatalogue(searchIN.getText());
            tableVieww.setItems(FXCollections.observableList(cattt));
        }
        else {
            List<Catalogue> catalogues= cs.afficherCatalogue();
    tableVieww.setItems(FXCollections.observableList(catalogues));
        }
        
    }
}
