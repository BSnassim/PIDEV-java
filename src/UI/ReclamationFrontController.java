/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import Entities.Reclamation;
import Entities.Reponse;
import Entities.EtatEnum;
import Entities.TypeEnum;
import Service.ServiceReclamation;
import Service.ServiceReponse;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author houss
 */
public class ReclamationFrontController implements Initializable {

    @FXML
    private Label lbMenuReclamation;
    @FXML
    private Label lbMenuMesReclamations;
    @FXML
    private Pane pnReclamation;
    @FXML
    private Pane pnMesReclamations;
    @FXML
    private ComboBox<String> tfType;
    @FXML
    private TextField tfDesc;
    @FXML
    private Button btnAnuller;
    @FXML
    private Button btnEnvoyer;
    @FXML
    private TableView<Reclamation> tvReclamation;
    @FXML
    private TableColumn<Reclamation, String> colEtat;
    @FXML
    private TableColumn<Reclamation, String> colType;
    @FXML
    private TableColumn<Reclamation, Date> colDateUpdate;
    @FXML
    private TableColumn<Reclamation, Date> colDateCreation;
    @FXML
    private Label lbDescription;
    @FXML
    private Button btnDetails;
    @FXML
    private Button btnSupprim;
    @FXML
    private Button btnEdite;
    @FXML
    private Label lbidRecla;
    @FXML
    private Label lbDateRecDetails;
    @FXML
    private Label lbTypeRecDetails;
    @FXML
    private Label lbEtatRecDetails;
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
    private TextField tfReponseAEnv;
    @FXML
    private Button btnRetourReponseAEnv;
    @FXML
    private Button btnEnvReponse;
    @FXML
    private Pane pnDetails;
    @FXML
    private Pane pnReponse;
    @FXML
    private Label lbTitreAjout;
    @FXML
    private Button btnUpdate;
    @FXML
    private Label lbTitreUpdate;
    @FXML
    private VBox vboxDesc;
    @FXML
    private HBox hboxButtonRec;
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
        pnMesReclamations.toFront();
        fnReclamationShow();
        ObservableList<String> valuesList = FXCollections.observableArrayList();
        for (TypeEnum value : TypeEnum.values()) {
            valuesList.add(value.toString());
        }
        tfType.setItems(valuesList);
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
         ObservableList<Reclamation> list = FXCollections.observableArrayList(sr.ShowByUser(1));;
    
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
        pnReclamation.toFront();
    }

    @FXML
    private void fnMenuMesReclamations(MouseEvent event) {
        fnReclamationShow();
        vboxDesc.setVisible(false);
       hboxButtonRec.setVisible(false);
        pnMesReclamations.toFront();
        
    }


    @FXML
    private void fnAnuller(ActionEvent event) {
        tfDesc.setText("");
        tfType.setValue("");
        fnReclamationShow();
        vboxDesc.setVisible(false);
       hboxButtonRec.setVisible(false);
        pnMesReclamations.toFront();
    }

    @FXML
    private void fnEnvoyer(ActionEvent event) {
        Reclamation r = new Reclamation();
        r.setId_User(1);
        r.setEtat(EtatEnum.En_Cours);
        r.setDescription(tfDesc.getText());
        r.setType(TypeEnum.valueOf(tfType.getValue()));
        ServiceReclamation sr =new ServiceReclamation();
        sr.add(r);
        tfDesc.setText("");
        tfType.setValue("");
        fnReclamationShow();
        vboxDesc.setVisible(false);
       hboxButtonRec.setVisible(false);
        pnMesReclamations.toFront();
        
    }

    @FXML
    private void onSelectionReclamation(MouseEvent event) {
         Reclamation rec= tvReclamation.getSelectionModel().getSelectedItem();
       lbidRecla.setText(String.valueOf(rec.getId()));
       vboxDesc.setVisible(true);
       hboxButtonRec.setVisible(true);
       lbDescription.setText(rec.description);
    }

    @FXML
    private void fnDetails(ActionEvent event) {
        ServiceReclamation sr= new ServiceReclamation();
        Reclamation r=sr.getById(Integer.parseInt(lbidRecla.getText()));
        lbTypeRecDetails.setText(r.getType().toString());
        lbEtatRecDetails.setText(r.getEtat().toString());
        lbDateRecDetails.setText(r.getDateCreation().toString());
        lbDescripRecDetails.setText(r.getDescription().toString());
        fnShowReponse();
        btnRepondre.setVisible(true);
        pnDetails.toFront();
    }

    @FXML
    private void fnSupprim(ActionEvent event) {
        ServiceReclamation sr= new ServiceReclamation();
        sr.Delete(sr.getById(Integer.parseInt(lbidRecla.getText())));
        vboxDesc.setVisible(false);
       hboxButtonRec.setVisible(false);
        fnReclamationShow();
    }

    @FXML
    private void fnEdite(ActionEvent event) {
        ServiceReclamation sr= new ServiceReclamation();
        Reclamation r=sr.getById(Integer.parseInt(lbidRecla.getText()));
        pnReclamation.toFront();
        tfType.setValue(r.getType().toString());
        tfDesc.setText(r.getDescription());
        lbTitreAjout.setVisible(false);
        lbTitreUpdate.setVisible(true);
        btnEnvoyer.setVisible(false);
        btnUpdate.setVisible(true);
        
    }

    @FXML
    private void onSelectionReponse(MouseEvent event) {
        Reponse r= tvReponse.getSelectionModel().getSelectedItem();
        hboxMessageReponse.setVisible(true);
       lbReponseMessage.setText(String.valueOf(r.getMessage()));
       
    }

    @FXML
    private void fnRetourReponse(ActionEvent event) {
        vboxDesc.setVisible(false);
       hboxButtonRec.setVisible(false);
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
    private void fnUpdateRec(ActionEvent event) {
        ServiceReclamation sr= new ServiceReclamation();
        Reclamation r=sr.getById(Integer.parseInt(lbidRecla.getText()));
        r.setType(TypeEnum.valueOf(tfType.getValue().toString()));
        r.setDescription(tfDesc.getText());
        sr.update(r);
        fnReclamationShow();
        vboxDesc.setVisible(false);
       hboxButtonRec.setVisible(false);
        pnReclamation.toFront();
    }

    private void fnShowReponse() {
        ServiceReponse sr=new ServiceReponse();
         ObservableList<Reponse> list = FXCollections.observableArrayList(sr.ShowByRec(Integer.parseInt(lbidRecla.getText())));;
    
     colDateReponse.setCellValueFactory(new PropertyValueFactory<>("dateCreation"));       
     colUtilisateurReponse.setCellValueFactory(new PropertyValueFactory<>("idUser"));    

        
     tvReponse.setItems(list);    }

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
         ObservableList<Reclamation> list = FXCollections.observableArrayList(sr.ShowByDateUser(begin,Fin,1));;
    
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
