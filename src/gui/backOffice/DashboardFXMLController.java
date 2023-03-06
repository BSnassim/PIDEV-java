/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.backOffice;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import gui.frontOffice.RegisterFXMLController;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Utilisateur;
import services.UtilisateurController;
import utils.connexionDB;

/**
 * FXML Controller class
 *
 * @author Ranim Ahmadi
 */
public class DashboardFXMLController implements Initializable {
    
    RegisterFXMLController objet = new RegisterFXMLController() ; 
    @FXML
    private TableColumn<Utilisateur, Integer> idUserDash;
    @FXML
    private TableColumn<Utilisateur, String> firstnameUserDash;
    @FXML
    private TableColumn<Utilisateur, String> flastnameUserDash;
    @FXML
    private TableColumn<Utilisateur, String> emailUserdash;
    @FXML
    private TableColumn<Utilisateur, String> loginUserDash;
    @FXML
    private HBox passwordUserDash;
    @FXML
    private Button boutonUpdateuser;
    @FXML
    private TableView<Utilisateur> usertab;
    @FXML
    private TableColumn<Utilisateur, String> passwordcol;
    @FXML
    private TableColumn<Utilisateur, String> actionscol;
    @FXML
    private TextField email;
    @FXML
    private TextField logdeAddUser;
    @FXML
    private TextField firstname;
    @FXML
    private TextField lastname;
    @FXML
    private TextField password;
    @FXML
    private Button back;
    @FXML
    private TextField searchIN;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showusers();
        // TODO
    }

    public ObservableList<Utilisateur> getutilisateurList() {
        ObservableList<Utilisateur> evenementsList = FXCollections.observableArrayList();
        Connection conn = connexionDB.getInstance().getConnexion();
        String query = "SELECT * FROM utilisateur";
        Statement st;
        ResultSet rs;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Utilisateur utilisateur;
            while (rs.next()) {
                utilisateur = new Utilisateur(rs.getInt("id"), rs.getString("login"), rs.getString("password"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"));
                evenementsList.add(utilisateur);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return evenementsList;

    }

    public void showusers() {
        ObservableList<Utilisateur> list = getutilisateurList();
        idUserDash.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstnameUserDash.setCellValueFactory(new PropertyValueFactory<>("nom"));
        flastnameUserDash.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        emailUserdash.setCellValueFactory(new PropertyValueFactory<>("email"));
        loginUserDash.setCellValueFactory(new PropertyValueFactory<>("login"));
        passwordcol.setCellValueFactory(new PropertyValueFactory<>("password"));

        usertab.setItems(list);
        Callback<TableColumn<Utilisateur, String>, TableCell<Utilisateur, String>> cellFoctory = (TableColumn<Utilisateur, String> param) -> {
            // make cell containing buttons
            final TableCell<Utilisateur, String> cell = new TableCell<Utilisateur, String>() {
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
                                    Connection conn = connexionDB.getInstance().getConnexion();
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
    public void showuserByLogin(String login) {
        UtilisateurController u= new UtilisateurController();  
        ObservableList<Utilisateur> list = u.rechUtilisateurByLogin2(login);
        idUserDash.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstnameUserDash.setCellValueFactory(new PropertyValueFactory<>("nom"));
        flastnameUserDash.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        emailUserdash.setCellValueFactory(new PropertyValueFactory<>("email"));
        loginUserDash.setCellValueFactory(new PropertyValueFactory<>("login"));
        passwordcol.setCellValueFactory(new PropertyValueFactory<>("password"));

        usertab.setItems(list);
        Callback<TableColumn<Utilisateur, String>, TableCell<Utilisateur, String>> cellFoctory = (TableColumn<Utilisateur, String> param) -> {
            // make cell containing buttons
            final TableCell<Utilisateur, String> cell = new TableCell<Utilisateur, String>() {
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
                                    Connection conn = connexionDB.getInstance().getConnexion();
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
    private void executeQuery(String query) {
        Connection conn = connexionDB.getInstance().getConnexion();
        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace();

        }

    }

    @FXML
    private void updateuser(ActionEvent event) {
         if (event.getSource() == boutonUpdateuser) {
       Utilisateur utilisateurs = usertab.getSelectionModel().getSelectedItem();
          String query = "UPDATE utilisateur SET login = '"+ logdeAddUser.getText()+"' ,  nom = '" +firstname.getText()+"' , prenom = '" +lastname.getText()+"' , email = '" +email.getText()+"' WHERE id='" +utilisateurs.getId()+"' " ; 
          executeQuery(query);
            showusers();
         }
    }

    @FXML
    private void onclickback(ActionEvent event) {
                try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../login/menuFXML.fxml"));
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
        Logger.getLogger(AddFavFXMLController.class.getName()).log(Level.SEVERE, null, ex);
    }  
    }

    @FXML
    private void chercheuser(ActionEvent event) {
        String login = searchIN.getText();
        if (login.equals("")){
            showusers();
        }
        else
        showuserByLogin(login);
    }
}
    

