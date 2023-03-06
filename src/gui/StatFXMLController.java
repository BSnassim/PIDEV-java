/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import controller.EventController;
import controller.ReservationController;
import enteties.event;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author chahi
 */
public class StatFXMLController implements Initializable {

    @FXML
    private PieChart statistique;
    event e = new event();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
                   int total=0;
    ReservationController rc = new ReservationController();

    int surplace=rc.nbSurPlace();
    int enligne=rc.nbSurEnLigne();
    total = surplace+ enligne;
        double prcntFeed = ( surplace *100)/total;
        double prcntRec = (enligne * 100)/total;
        
        ObservableList<PieChart.Data> list=FXCollections.observableArrayList(

                new PieChart.Data("Sur place " + prcntFeed +"%", rc.nbSurPlace()),
            new PieChart.Data("En Ligne " + prcntRec +"%", rc.nbSurEnLigne())
         );
        
     
         statistique.setAnimated(true);
         statistique.setData(list);
    }    

    @FXML
    private void btnbck(MouseEvent event) throws IOException {
         Parent root= FXMLLoader.load(getClass().getResource("ReservationFXML.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
       
      stage.setScene(new Scene(root));
        stage.show();
    }
    }    
    

