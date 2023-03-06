/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import animatefx.animation.Shake;
import controller.ReservationController;
import enteties.reservation;
import static gui.AddEventFXMLController.shake;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import utils.Connection;

/**
 * FXML Controller class
 *
 * @author chahi
 */
public class AddReservationFXMLController implements Initializable {

    @FXML
    private ChoiceBox<Integer> comboartist;
    @FXML
    private ChoiceBox<Integer> comboevent;
    @FXML
    private ChoiceBox<String> combopayement;
    ObservableList<String> listmode = FXCollections.observableArrayList( "Sur Place","En ligne");
    reservation r = new reservation();
    ReservationController rc = new ReservationController();
    @FXML
    private TextField TfId;
    
    
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        combopayement.setItems(listmode);
        //combopayement.setItems(listmode);
        //combopayement.setItems(listmode);
        RemplirComboEvent();
        RemplirComboArtiste();

      

    }    

    @FXML
    private void BtnBack(MouseEvent event) throws IOException {
         Parent root= FXMLLoader.load(getClass().getResource("ReservationFXML.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
       
      stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void BtnCreate(ActionEvent event) {
         if (comboartist.getSelectionModel().getSelectedItem() == null) {
            comboartist.requestFocus();
            shake(comboartist);
            return;
        }
         if (comboevent.getSelectionModel().getSelectedItem() == null) {
            comboevent.requestFocus();
            shake(comboevent);
            return;
        }
         if (combopayement.getSelectionModel().getSelectedItem() == null) {
            combopayement.requestFocus();
            shake(combopayement);
            return;
        }
         
         r.setId_Artiste(comboartist.getSelectionModel().getSelectedItem());
        r.setId_event(comboevent.getSelectionModel().getSelectedItem());
        r.setPayement(combopayement.getSelectionModel().getSelectedItem());
        
        

        boolean result = rc.ajouter(r); 
        if (result) {

            comboartist.getSelectionModel().clearSelection();
            comboevent.getSelectionModel().clearSelection();
            combopayement.getSelectionModel().clearSelection();

           
            
       Image image=new Image("/Assests/success.png");

        Notifications notifications=Notifications.create();
        notifications.graphic(new ImageView(image));
        notifications.text("Reservation ajouté avec succée ✔");
        notifications.title("Success Message");
        notifications.hideAfter(Duration.seconds(3));
        notifications.show();
        }}
         
    
    
    
      private void RemplirComboArtiste() {
        try {

            String requetee = "SELECT id_user FROM `artiste`";
            Statement pstt = Connection.getInstance().getCnx().createStatement();
            ResultSet rs = pstt.executeQuery(requetee);
            while (rs.next()) {
                comboartist.getItems().addAll(rs.getInt("id_user"));
                 //ObservableList<String> listmode = FXCollections.observableArrayList(rs.getString("id_user"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
      
      
      
        private void RemplirComboEvent() {
        try {

            String requetee = "SELECT id FROM `evenement`";
            Statement pstt = Connection.getInstance().getCnx().createStatement();
            ResultSet rs = pstt.executeQuery(requetee);
            while (rs.next()) {
                comboevent.getItems().addAll(rs.getInt("id"));
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
         
        
        
         
    
     public static void shake(Node node) {
        new Shake(node).play();
    }

    @FXML
    private void Btnupdate(ActionEvent event) {
        
         if (comboartist.getSelectionModel().getSelectedItem() == null) {
            comboartist.requestFocus();
            shake(comboartist);
            return;
        }
         if (comboevent.getSelectionModel().getSelectedItem() == null) {
            comboevent.requestFocus();
            shake(comboevent);
            return;
        }
         if (combopayement.getSelectionModel().getSelectedItem() == null) {
            combopayement.requestFocus();
            shake(combopayement);
            return;
        }
         
         r.setId_Artiste(comboartist.getSelectionModel().getSelectedItem());
        r.setId_event(comboevent.getSelectionModel().getSelectedItem());
        r.setPayement(combopayement.getSelectionModel().getSelectedItem());
        
        

        boolean result = rc.modifier(r,Integer.parseInt(TfId.getText())); 
        if (result) {

            comboartist.getSelectionModel().clearSelection();
            comboevent.getSelectionModel().clearSelection();
            combopayement.getSelectionModel().clearSelection();

           
            
       Image image=new Image("/Assests/success.png");

        Notifications notifications=Notifications.create();
        notifications.graphic(new ImageView(image));
        notifications.text("Reservation modifié avec succée ✔");
        notifications.title("Success Message");
        notifications.hideAfter(Duration.seconds(3));
        notifications.show();
        }
    }
    
    
}
