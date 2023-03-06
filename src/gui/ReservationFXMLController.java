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
import controller.ReservationController;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import enteties.event;
import enteties.reservation;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import utils.Connection;

/**
 * FXML Controller class
 *
 * @author chahi
 */
public class ReservationFXMLController implements Initializable {

    @FXML
    private TableColumn<reservation, String> artistcol;
    @FXML
    private TableColumn<reservation, String> eventcol;
    @FXML
    private TableColumn<reservation, String> paycol;
    @FXML
    private TableColumn<reservation, String> editcol;
    @FXML
    private TextField recherche;
     reservation r = new reservation();
    ReservationController rc =  new ReservationController();
    private ObservableList<reservation> Listreservations;
    @FXML
    private TableView<reservation> TableReservation;
    @FXML
    private ImageView qrcod;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LoadTableReservation(); 
    }    

    @FXML
    private void BtnEvent(ActionEvent event)throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("EventFXML.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
       
      stage.setScene(new Scene(root));
        stage.show();
    }


    @FXML
    private void BtnAdd(ActionEvent event) throws IOException {
         Parent root= FXMLLoader.load(getClass().getResource("AddReservationFXML.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
       
      stage.setScene(new Scene(root));
        stage.show();
    }
    
    
    
    
    
     private void LoadTableReservation() {

        List<reservation> listee = new ArrayList<>();
        listee = rc.afficher();
        ObservableList<reservation> Liste = FXCollections.observableArrayList(listee);

        
       

 
        artistcol.setCellValueFactory(new PropertyValueFactory<>("id_Artiste"));
        eventcol.setCellValueFactory(new PropertyValueFactory<>("id_Event"));
        paycol.setCellValueFactory(new PropertyValueFactory<>("payement"));
       


        
        
        Callback<TableColumn<reservation, String>, TableCell<reservation, String>> cellFoctory = (TableColumn<reservation, String> param) -> {
            // make cell containing buttons
            final TableCell<reservation, String> cell = new TableCell<reservation, String>() {
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
                                r = TableReservation.getSelectionModel().getSelectedItem();
                                String query = "DELETE FROM `reservation` WHERE id  ="+r.getId();
                                Statement stm = Connection.getInstance().getCnx().createStatement();
                                stm.executeUpdate(query);
                                
                                
                                Image image=new Image("/Assests/success.png");
                                Notifications notifications=Notifications.create();
                                notifications.graphic(new ImageView(image));
                                notifications.text("Reservation supprimé avec succée ✔");
                                notifications.title("Success Message");
                                notifications.hideAfter(Duration.seconds(3));
                                notifications.show();
                                
                                
                                LoadTableReservation(); 
                                
                            } catch (SQLException ex) {
                                Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                     
                        });
                        
                        //////////// EDIT ICON CLICKED ////////////

                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            r = TableReservation.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("AddReservationFXML.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            AddReservationFXMLController rc = loader.getController();
                           //ec.setUpdate(true);
                           //ec.setTextField(e.getId(), e.getPrix(), 
                           //       e.getDate_event().toString(),e.getAdresse(), e.getPhoto());
                           
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
        
        
        Listreservations = FXCollections.observableArrayList(listee);
        TableReservation.setItems(Listreservations);
        
         FilteredList<reservation> filteredData = new FilteredList<>(Listreservations, b -> true);
         recherche.textProperty().addListener((observable, oldValue, newValue) -> {
           filteredData.setPredicate(event -> {
           if (newValue.isEmpty() || newValue==null){
           return true;
           }   
           String SearchKey= newValue.toLowerCase();
           if (event.getPayement().toLowerCase().indexOf(SearchKey) > -1){
           return true;
           }
           else return false;
                   });
            });
            
            SortedList<reservation> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(TableReservation.comparatorProperty());
            TableReservation.setItems(sortedData);
        
        
       
    }

    @FXML
    private void BtnStat(ActionEvent event) throws IOException {
        
        Parent root= FXMLLoader.load(getClass().getResource("StatFXML.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
       
      stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void qrcod(ActionEvent event) {
         ObservableList <reservation> ListEvents;
           Listreservations =TableReservation.getSelectionModel().getSelectedItems();

         QRCodeWriter qrCodeWriter = new QRCodeWriter();
        String myWeb = Listreservations.toString();
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
        qrcod.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
        
     
    }
    
    
    
}
