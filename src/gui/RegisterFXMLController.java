/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import animatefx.animation.Pulse;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import utils.connexionDB;
import static utils.connexionDB.connexionDB;

/**
 * FXML Controller class
 *
 * @author Ranim Ahmadi
 */
public class RegisterFXMLController implements Initializable {

    public TextField logdeAddUser;

    public TextField prenomdeAddUser;
    public TextField nomdeAddUser;
    public TextField emaildeAddUser;
    public Button back;
    private Label lbllogin;
    private Label lblpwd;
    private Label lblname;
    private Label lbllastname;
    private Label lblemail;
    private PasswordField passworddeAddUser;
    @FXML
    private TextField fxLogin;
    @FXML
    private TextField fxPassword;
    @FXML
    private TextField fxConfirmPassword;
    @FXML
    private TextField fxPrenom;
    @FXML
    private TextField fxNom;
    @FXML
    private TextField fxEmail;
    @FXML
    private Button btnajout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    @FXML
    private void add(ActionEvent event) throws SQLException {
            insert();
System.out.println("6");
cancelBTN(event);
        /*Utilisateur u = new Utilisateur();
        IutilisateurController inter = new UtilisateurController();

       
            inter.ajouterUtilisateur(u);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("user insérée avec succés!");
            alert.show();
         */
    }

    private void executeQuery(String query) {
        Connection conn = connexionDB();
        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        
    }

    private boolean validnom() {
        return !nomdeAddUser.getText().isEmpty() && nomdeAddUser.getLength() > 3;
    }

    private boolean validprenom() {
        return !prenomdeAddUser.getText().isEmpty() && prenomdeAddUser.getLength() > 3;
    }

    private boolean validpassword() {
        return !passworddeAddUser.getText().isEmpty() && passworddeAddUser.getLength() > 3;
    }

    private boolean validemail() {
        return !emaildeAddUser.getText().isEmpty() && emaildeAddUser.getLength() > 3;
    }

    private boolean validlogin() {
        return !logdeAddUser.getText().isEmpty() && logdeAddUser.getLength() > 1;
    }

    private void enterAction() throws SQLException {
        Pulse pulse = new Pulse(btnajout);
        pulse.setDelay(Duration.millis(20));
        pulse.play();
        if (validnom() && validprenom() && validemail() && validlogin() && validpassword()) {
            insert();
        } else {
            lbllogin.setVisible(true);
            lblpwd.setVisible(true);
            lblname.setVisible(true);
            lbllastname.setVisible(true);
            lblemail.setVisible(true);

        }
    }

    private void insert() throws SQLException {
        String Login = fxLogin.getText();
        String Password = fxPassword.getText();
        String PasswordConfirmed = fxConfirmPassword.getText();
        String firstName = fxPrenom.getText();
        String LastName = fxNom.getText();
        String Email = fxEmail.getText();
        String query = "INSERT INTO `utilisateur`( `login`, `password`, `nom`, `prenom`, `email`) VALUES ('" + Login + "','" + Password + "','" + firstName + "','" + LastName + "','" + Email + "')";
        PreparedStatement ps = connexionDB.getInstance().getConnexion().prepareStatement(query);
System.out.println("2");
            ps.executeUpdate();

    }

   
    private void onclickback(ActionEvent event) {
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
            Logger.getLogger(AddFavFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void cancelBTN(ActionEvent event) {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UtilisateurFXML.fxml"));
        Parent root = loader.load(); 
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        stage.toFront(); //pour s'assurer que la nouvelle fenêtre est au premier plan
        
    } catch (IOException ex) {
        Logger.getLogger(DashboardFXMLController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

}
