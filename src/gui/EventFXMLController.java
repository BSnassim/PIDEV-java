/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import controller.EventController;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import enteties.event;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.Duration;
//import org.controlsfx.control.Notifications;
import utils.Connection;

/**
 * FXML Controller class
 *
 * @author chahi
 */
public class EventFXMLController implements Initializable {

    @FXML
    private TableView<event> TableEvent;
    @FXML
    private TableColumn<event, String> Date;
    @FXML
    private TableColumn<event, String> Description;
    @FXML
    private TableColumn<event, String> Adresse;
    @FXML
    private TableColumn<event, Integer> Prix;
    @FXML
    private TableColumn<event, Image> Photo;
    @FXML
    private TableColumn<event, String> editcol;
    @FXML
    private TextField TfRecherche;
    
    event e = new event();
    EventController ec =  new EventController();
    private ObservableList<event> ListEvents;
    @FXML
    private ImageView qrView;
    
        event even = null ;
    @FXML
    private TableColumn<event, Integer> col;

    
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        LoadTableEvent(); 
                
    }    

    @FXML
    private void BtnEvent(ActionEvent event) throws IOException {
         Parent root= FXMLLoader.load(getClass().getResource("EventFXML.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
       
      stage.setScene(new Scene(root));
        stage.show();
    }

    
    
     @FXML
    private void BtnReservation(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("ReservationFXML.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
       
      stage.setScene(new Scene(root));
        stage.show();
    }
    

    

    @FXML
    private void BtnAdd(ActionEvent event)  throws IOException {
         Parent root= FXMLLoader.load(getClass().getResource("AddEventFXML.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
       
      stage.setScene(new Scene(root));
        stage.show();
    }
       
 
    
    
    private void LoadTableEvent() {

        List<event> listee = new ArrayList<>();
        listee = ec.afficher();
        ObservableList<event> Liste = FXCollections.observableArrayList(listee);

        
       

        col.setCellValueFactory(new PropertyValueFactory<>("id"));
        Date.setCellValueFactory(new PropertyValueFactory<>("dateEvenement"));
        Description.setCellValueFactory(new PropertyValueFactory<>("Description"));
        Adresse.setCellValueFactory(new PropertyValueFactory<>("Adresse"));
        Prix.setCellValueFactory(new PropertyValueFactory<>("Prix"));
        Photo.setCellValueFactory(new PropertyValueFactory<>("Photo"));
        
        
        



        
        
        Callback<TableColumn<event, String>, TableCell<event, String>> cellFoctory = (TableColumn<event, String> param) -> {
            // make cell containing buttons
            final TableCell<event, String> cell = new TableCell<event, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
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
                        
                        
                        
                        //////////// DELETE ICON CLICKED ////////////
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            try {
                                e = TableEvent.getSelectionModel().getSelectedItem();
                                String query = "DELETE FROM `evenement` WHERE id  ="+e.getId();
                                Statement stm = Connection.getInstance().getCnx().createStatement();
                                stm.executeUpdate(query);
                                
                                
                              /*  Image image=new Image("/Assests/success.png");
                                Notifications notifications=Notifications.create();
                                notifications.graphic(new ImageView(image));
                                notifications.text("Evenement supprimé avec succée ✔");
                                notifications.title("Success Message");
                                notifications.hideAfter(Duration.seconds(3));
                                notifications.show();
                                */
                                
                                LoadTableEvent(); 
                                
                            } catch (SQLException ex) {
                                Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                     
                        });
                        
                        
                        
                        //////////// EDIT ICON CLICKED ////////////

                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                      
                            even = TableEvent.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("AddEventFXML.fxml"));
                            try {
                               
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            AddEventFXMLController ec = loader.getController();
                           ec.setUpdate(true);
                           ec.setTextField( even.getId(),even.getPrix(), 
                                    even.getAdresse(), even.getDescription());
                           
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();

                        });

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
         editcol.setCellFactory(cellFoctory);
        
        
        ListEvents = FXCollections.observableArrayList(listee);
        TableEvent.setItems(ListEvents);
        
        
         FilteredList<event> filteredData = new FilteredList<>(ListEvents, b -> true);
        TfRecherche.textProperty().addListener((observable, oldValue, newValue) -> {
           filteredData.setPredicate(event -> {
           if (newValue.isEmpty() || newValue==null){
           return true;
           }   
           String SearchKey= newValue.toLowerCase();
           if (event.getAdresse().toLowerCase().indexOf(SearchKey) > -1){
           return true;
           }else if(event.getDescription().toLowerCase().indexOf(SearchKey) > -1){
           return true;
           }else if(event.getDate_event().toLowerCase().indexOf(SearchKey) > -1){
           return true;
           }
           else return false;
                   });
            });
            
            SortedList<event> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(TableEvent.comparatorProperty());
            TableEvent.setItems(sortedData);
        
        
       

        
       
    }

    @FXML
    private void BtnQr(ActionEvent event) {
        
          ObservableList <event> ListEvents;
           ListEvents =TableEvent.getSelectionModel().getSelectedItems();

         QRCodeWriter qrCodeWriter = new QRCodeWriter();
        String myWeb = ListEvents.toString();
        int width = 300;
        int height = 300;
        String fileType = "png";
        
        BufferedImage bufferedImage = null;
        try {
            BitMatrix byteMatrix = qrCodeWriter.encode(myWeb, BarcodeFormat.QR_CODE, width, height);
            bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            bufferedImage.createGraphics();
            
            Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(Color.BLACK);
            
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            
            System.out.println("Success...");
            
        } catch (WriterException ex) {
            Logger.getLogger(EventFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // ImageView qrView = new ImageView();
        qrView.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
        
     
      
    }


   
    
    
    
    
}
    
    
