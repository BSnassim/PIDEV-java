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
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.controlsfx.control.Notifications;

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
    @FXML
    private ComboBox<String> cbEtat;
    @FXML
    private ComboBox<String> cbType;
    @FXML
    private DatePicker dpStart;
    @FXML
    private DatePicker dpEND;
    @FXML
    private Button btnsearchDate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fnReclamationShow();
        ObservableList<String> valuesList = FXCollections.observableArrayList();
        for (EtatEnum value : EtatEnum.values()) {
            valuesList.add(value.toString());
        }
        pnMesReclamations.toFront();
        lbEtatRecDetails.setItems(valuesList);
        valuesList = FXCollections.observableArrayList();
        valuesList.add("");
        for (TypeEnum value : TypeEnum.values()) {
            valuesList.add(value.toString());
        }
        cbType.setItems(valuesList);
        
        ObservableList<String> valuesListEtat = FXCollections.observableArrayList();
        valuesListEtat.add("");
        for (EtatEnum value : EtatEnum.values()) {
            valuesListEtat.add(value.toString());
        }
        
        cbEtat.setItems(valuesListEtat);
    }  
    
    public void fnReclamationShow(){
        ServiceReclamation sr=new ServiceReclamation();
         ObservableList<Reclamation> list = FXCollections.observableArrayList(sr.Show());;
    
     colEtat.setCellValueFactory(new PropertyValueFactory<>("Etat"));       
     colType.setCellValueFactory(new PropertyValueFactory<>("Type"));        
     colDateUpdate.setCellValueFactory(new PropertyValueFactory<>("dateUpdate"));   
     colDateCreation.setCellValueFactory(new PropertyValueFactory<>("dateCreation"));   

        
     tvReclamation.setItems(list);
    tvReclamation.setRowFactory(tv -> new TableRow<Reclamation>() {
    @Override
    protected void updateItem(Reclamation item, boolean empty) {
        super.updateItem(item, empty);
        
    }
});
      
    FilteredList<Reclamation> filteredData = new FilteredList<>(list, b -> true);
		
		cbEtat.valueProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Reclamation -> {
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (Reclamation.getEtat().toString().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; 
				} 
				     else  
				    	 return false; 
			});
		});
                
                
                cbType.valueProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Reclamation -> {
				
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (Reclamation.getType().toString().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; 
				} 
				     else  
				    	 return false; 
			});
		});
		
		SortedList<Reclamation> sortedData = new SortedList<>(filteredData);
		
		sortedData.comparatorProperty().bind(tvReclamation.comparatorProperty());
		
		tvReclamation.setItems(sortedData);}

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
        hboxMessageReponse.setVisible(true);
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
        re.setMessage(tfReponseAEnv.getText());
        sre.add(re);
        fnShowReponse();
        pnDetails.toFront();
    }

    @FXML
    private void fnUp(ActionEvent event) {
        ServiceReclamation sr= new ServiceReclamation();
        Reclamation r=sr.getById(Integer.parseInt(lbidRecla.getText()));
        r.setEtat(EtatEnum.valueOf(lbEtatRecDetails.getValue()));
        System.out.println(r);
        sr.update(r);
        Notifications.create()
              .title("Etat modifé avec succées")
              .text("Une sms sera envoyer au client !!")
              .showWarning();//api notification 
        fnShowReponse();
        fnReclamationShow();
        pnMesReclamations.toFront();
        Service.SendSms.send("+21695607536","Vous aves une update sur une reclamation !" );
    }

    @FXML
    private void fnRechercher(ActionEvent event) {
        cbEtat.setValue("");
        cbType.setValue("");
        String begin="";
        if(dpStart.getValue()!=null){
           begin=Date.valueOf(dpStart.getValue()).toString(); 
        }
        String  Fin="";
        if(dpEND.getValue()!=null){
            Fin=Date.valueOf(dpEND.getValue()).toString();
        }
         
        
        ServiceReclamation sr=new ServiceReclamation();
         ObservableList<Reclamation> list = FXCollections.observableArrayList(sr.ShowByDate(begin,Fin));;
    
     colEtat.setCellValueFactory(new PropertyValueFactory<>("Etat"));       
     colType.setCellValueFactory(new PropertyValueFactory<>("Type"));        
     colDateUpdate.setCellValueFactory(new PropertyValueFactory<>("dateUpdate"));   
     colDateCreation.setCellValueFactory(new PropertyValueFactory<>("dateCreation"));   

        
     tvReclamation.setItems(list);
    tvReclamation.setRowFactory(tv -> new TableRow<Reclamation>() {
    @Override
    protected void updateItem(Reclamation item, boolean empty) {
        super.updateItem(item, empty);
        
    }
});
      
    FilteredList<Reclamation> filteredData = new FilteredList<>(list, b -> true);
		
		cbEtat.valueProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Reclamation -> {
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (Reclamation.getEtat().toString().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; 
				} 
				     else  
				    	 return false; 
			});
		});
                
                
                cbType.valueProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Reclamation -> {
				
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (Reclamation.getType().toString().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; 
				} 
				     else  
				    	 return false; 
			});
		});
		
		SortedList<Reclamation> sortedData = new SortedList<>(filteredData);
		
		sortedData.comparatorProperty().bind(tvReclamation.comparatorProperty());
		
		tvReclamation.setItems(sortedData);
    }
    
}
