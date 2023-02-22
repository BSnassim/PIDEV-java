/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Entities.EtatEnum;
import Entities.Reclamation;
import Entities.Reponse;
import Entities.TypeEnum;
import Service.ServiceReclamation;
import Service.ServiceReponse;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author houss
 */
public class ReclamationBackController implements Initializable {

    @FXML
    private Label lbMenuReclamation;
    @FXML
    private TableView<Reclamation> tvReclamation;
    @FXML
    private TableColumn<Reclamation, String> colEtat;
    @FXML
    private TableColumn<Reclamation, String> colType;
    @FXML
    private TableColumn<Reclamation, Date> colDateCreation;
    @FXML
    private TableColumn<Reclamation, Date> colDateUpdate;
    @FXML
    private VBox vboxDesc;
    @FXML
    private Label lbDescription;
    @FXML
    private Button btnDetails;
    @FXML
    private Pane pnDetails;
    @FXML
    private Label lbDateRecDetails;
    @FXML
    private Label lbTypeRecDetails;
    @FXML
    private ComboBox<String> lbEtatRecDetails;
    @FXML
    private Label lbDescripRecDetails;
    @FXML
    private TableView<Reponse> tvReponse;
    @FXML
    private TableColumn<Reponse, Date> colDateReponse;
    @FXML
    private TableColumn<Reponse, Integer> colUtilisateurReponse;
    @FXML
    private HBox hboxMessageReponse;
    @FXML
    private Label lbReponseMessage;
    @FXML
    private Button btnRetourReponse;
    @FXML
    private Button btnRepondre;
    @FXML
    private Pane pnReponse;
    @FXML
    private TextField tfReponseAEnv;
    @FXML
    private Button btnRetourReponseAEnv;
    @FXML
    private Button btnEnvReponse;
    @FXML
    private Pane pnMesReclamations;
    @FXML
    private Label lbidRecla;
    @FXML
    private Button btnUp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fnReclamationShow();
        ObservableList<String> valuesList = FXCollections.observableArrayList();
        for (EtatEnum value : EtatEnum.values()) {
            valuesList.add(value.toString());
        }//bech njib enum bech nhothom fi combobox
        pnMesReclamations.toFront();
        lbEtatRecDetails.setItems(valuesList);
    }  
    
    public void fnReclamationShow(){
        ServiceReclamation sr=new ServiceReclamation();
         ObservableList<Reclamation> list = FXCollections.observableArrayList(sr.Show());;
    
     colEtat.setCellValueFactory(new PropertyValueFactory<>("Etat"));       
     colType.setCellValueFactory(new PropertyValueFactory<>("Type"));        
     colDateUpdate.setCellValueFactory(new PropertyValueFactory<>("dateUpdate"));   
     colDateCreation.setCellValueFactory(new PropertyValueFactory<>("dateCreation"));   

        
     tvReclamation.setItems(list);}

    @FXML
    private void fnMenuReclamation(MouseEvent event) {
         
    }


    @FXML
    private void onSelectionReclamation(MouseEvent event) {
        Reclamation rec= tvReclamation.getSelectionModel().getSelectedItem();
       lbidRecla.setText(String.valueOf(rec.getId()));
       vboxDesc.setVisible(true);
       lbDescription.setText(rec.description);
    }

    @FXML
    private void fnDetails(ActionEvent event) {
        
        ServiceReclamation sr= new ServiceReclamation();
        Reclamation r=sr.getById(Integer.parseInt(lbidRecla.getText()));
        lbTypeRecDetails.setText(r.getType().toString());
        lbEtatRecDetails.setValue(r.getEtat().toString());
        lbDateRecDetails.setText(r.getDateCreation().toString());
        lbDescripRecDetails.setText(r.getDescription().toString());
        fnShowReponse();
        btnRepondre.setVisible(true);
        pnDetails.toFront();
    }
    
    private void fnShowReponse() {
        ServiceReponse sr=new ServiceReponse();
         ObservableList<Reponse> list = FXCollections.observableArrayList(sr.ShowByRec(Integer.parseInt(lbidRecla.getText())));;
    
     colDateReponse.setCellValueFactory(new PropertyValueFactory<>("dateCreation"));       
     colUtilisateurReponse.setCellValueFactory(new PropertyValueFactory<>("idUser"));    

        
     tvReponse.setItems(list);    }

    @FXML
    private void onSelectionReponse(MouseEvent event) {
         Reponse r= tvReponse.getSelectionModel().getSelectedItem();
        hboxMessageReponse.setVisible(true);//tkhalik tchouf e description wakt tenzel ala tvreponse
       lbReponseMessage.setText(String.valueOf(r.getMessage()));
    }

    @FXML
    private void fnRetourReponse(ActionEvent event) {
        vboxDesc.setVisible(false);
       hboxMessageReponse.setVisible(false);
        pnMesReclamations.toFront();
    }

    @FXML
    private void fnRepondre(ActionEvent event) {
        pnReponse.toFront();
    }

    @FXML
    private void fnRetourReponseAEnv(ActionEvent event) {
        tfReponseAEnv.setText("");
        pnDetails.toFront();
    }

    @FXML
    private void fnEnvReponse(ActionEvent event) {
        ServiceReponse sre=new ServiceReponse();
        ServiceReclamation sr= new ServiceReclamation();
        Reclamation r=sr.getById(Integer.parseInt(lbidRecla.getText()));
        Reponse re=new Reponse();
        re.setReclamation(r);
        re.setIdUser(2);
         if("".equals(tfReponseAEnv.getText())){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Champs vide !!");
        alert.setHeaderText("Veuillez entrer votre texte ");
        if(alert.showAndWait().get() == ButtonType.OK) {
        }
        }else{
        re.setMessage(tfReponseAEnv.getText());
        sre.add(re); //save f base 
        fnShowReponse();
        pnDetails.toFront();}
    }

    @FXML
    private void fnUp(ActionEvent event) {
        ServiceReclamation sr= new ServiceReclamation();
        Reclamation r=sr.getById(Integer.parseInt(lbidRecla.getText()));
        r.setEtat(EtatEnum.valueOf(lbEtatRecDetails.getValue()));
        System.out.println(r);
        sr.update(r);
        fnShowReponse();
        fnReclamationShow();
        pnMesReclamations.toFront();
    }
    
}
