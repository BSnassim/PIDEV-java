/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.backOffice;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import services.PDFGenerator;
import services.ProduitController;
import com.google.zxing.BarcodeFormat;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.qrcode.ByteMatrix;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import static java.lang.Float.parseFloat;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.converter.FloatStringConverter;
import model.Produit;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Marwen.M
 */
public class FXMLProduitController implements Initializable {

    ObservableList<String> types =FXCollections.observableArrayList("Type","vetements","cd");
    PDFGenerator pdf = new PDFGenerator(); 
    ProduitController ps = new ProduitController();
    
    @FXML
    private TableView<Produit> tableprod;
    @FXML
    private Button btninsert;
    @FXML
    private Button btnupdate;
    @FXML
    private Button btndelete;
    @FXML
    private TableColumn<Produit,Integer> tvi;
    @FXML
    private TableColumn<Produit, String> tvn;
    @FXML
    private TableColumn<Produit, String> tvd;
    @FXML
    private TableColumn<Produit, String> tvt;
    @FXML
    private TableColumn<Produit, String> tvimg;
    @FXML
    private TableColumn<Produit, Float> tvprix;
    @FXML
    private TableColumn<Produit, Integer> qte;
    @FXML
    private TableColumn<Produit, String> taille;

    @FXML
    private TextField taillep;

    
    @FXML
    private Button qrcode;
    
    @FXML
    private ImageView qrcodee;
    
    @FXML
    private Button tri;
    
    @FXML
    private TextField qtep;
    @FXML
    private Button btnupload;
    @FXML
    private TextField nomp;
    @FXML
    private TextField descrp;
    @FXML
    private ComboBox<String> typep;
    @FXML
    private TextField imgp;
    @FXML
    private TextField prixp;
    @FXML
    private Button pdfp;

    
    @FXML
    private TextField rech;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tableprod.setEditable(true);
        typep.setItems(types);
        
        senddata();
      //   Produit p= new Produit(1,"moh","xxxxxxx","zzz","type",1);
      //   ps.ajouterProduit(p);
        
    }    
/*
    public void senddata()
    {
        
        
          tvi.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("id"));
          tvt.setCellValueFactory(new PropertyValueFactory<Produit,String>("type"));
          tvt.setEditable(true);
        tvn.setCellValueFactory(new PropertyValueFactory<Produit,String>("libelle"));
        tvd.setCellValueFactory(new PropertyValueFactory<Produit,String>("descr"));
        tvimg.setCellValueFactory(new PropertyValueFactory<Produit,String>("image"));
        tvprix.setCellValueFactory(new PropertyValueFactory<Produit, Float>("prix"));
        
     ///  user_id.setCellValueFactory(new PropertyValueFactory<Article,String>("users"));
     
   
         
        tableprod.setItems(FXCollections.observableArrayList( ps.afficherProduit()));
    }*/
    public void senddata()
{
     tvi.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("id"));
    tvt.setCellValueFactory(new PropertyValueFactory<Produit,String>("type"));
    tvt.setEditable(true);
    tvt.setCellFactory(TextFieldTableCell.forTableColumn());
    taille.setCellValueFactory(new PropertyValueFactory<Produit,String>("taille"));
    taille.setEditable(true);
    qte.setCellValueFactory(new PropertyValueFactory<Produit,Integer>("qte"));
    qte.setEditable(true);
    tvn.setCellValueFactory(new PropertyValueFactory<Produit,String>("libelle"));
    tvn.setEditable(true);
    tvn.setCellFactory(TextFieldTableCell.forTableColumn()); // use TextFieldTableCell for libelle column
    tvd.setCellValueFactory(new PropertyValueFactory<Produit,String>("descr"));
    tvd.setEditable(true);
    tvd.setCellFactory(TextFieldTableCell.forTableColumn()); // use TextFieldTableCell for descr column
    tvimg.setCellValueFactory(new PropertyValueFactory<Produit,String>("image"));
    tvimg.setEditable(true);
    tvimg.setCellFactory(TextFieldTableCell.forTableColumn()); // use TextFieldTableCell for image column
    tvprix.setCellValueFactory(new PropertyValueFactory<Produit, Float>("prix"));
    tvprix.setEditable(true);
    tvprix.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter())); // use TextFieldTableCell with FloatStringConverter for prix column
   
    tableprod.setItems(FXCollections.observableArrayList( ps.afficherProduit()));
    ObservableList<Produit> oList = FXCollections.observableArrayList(ps.afficherProduit());
        FilteredList<Produit> filteredData = new FilteredList<Produit>(oList, b -> true);
        rech.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate( m -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (m.getLibelle().toLowerCase().indexOf(lowerCaseFilter)!=-1) {
                    return true;
                } else return false;
            });
        });
        SortedList<Produit> sortedList = new SortedList <Produit>(filteredData);
        sortedList.comparatorProperty().bind(tableprod.comparatorProperty())    ;
        tableprod.setItems(sortedList);

   /*
    tvn.setOnEditCommit((TableColumn.CellEditEvent<Produit, String> event) -> {
        Produit produit = event.getRowValue();
        produit.setLibelle(event.getNewValue());
    });
    tvd.setOnEditCommit((TableColumn.CellEditEvent<Produit, String> event) -> {
        Produit produit = event.getRowValue();
        produit.setDescr(event.getNewValue());
    });
    tvimg.setOnEditCommit((TableColumn.CellEditEvent<Produit, String> event) -> {
        Produit produit = event.getRowValue();
        produit.setImage(event.getNewValue());
    });
    tvprix.setOnEditCommit((TableColumn.CellEditEvent<Produit, Float> event) -> {
        Produit produit = event.getRowValue();
        produit.setPrix(event.getNewValue());
    });*/
         
   
}

    @FXML
    private void prodclick(MouseEvent event) {
    }

    @FXML
    private void insertproduit(ActionEvent event) {
         if(nomp.getText().isEmpty()  || descrp.getText().isEmpty() || (typep.getSelectionModel().getSelectedIndex()==0))
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
alert.setTitle("Error");
alert.setHeaderText("Add Error");
alert.setContentText("all fields must  not be empty !");

alert.showAndWait();
        }
        else
        {
            //float x = parseFloat(prixp.getText());
            Produit p = new Produit(Float.parseFloat((prixp.getText())),imgp.getText(),nomp.getText(),types.get(typep.getSelectionModel().getSelectedIndex()),descrp.getText(),Integer.parseInt(qtep.getText()),taillep.getText(),1);
            System.out.println(p);
            ps.ajouterProduit(p);
            senddata();
            Notifications notificationBuilder = Notifications.create()
        .title("Produit Ajouter")
        .text("votre produit a été ajoutee avec succes")
        .graphic(null)
        .hideAfter(Duration.seconds(5))
        .position(Pos.BOTTOM_RIGHT)
        .onAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("ajout avec succes");
            }
               });
        notificationBuilder.showConfirm();
            
        }
    }

    @FXML
    private void updateproduit(ActionEvent event) {
        
    }

    @FXML
    private void deleteproduit(ActionEvent event) {
        Produit p = tableprod.getSelectionModel().getSelectedItem();
        ps.supprimerProduit(p.getId());
        senddata();
        Notifications notificationBuilder = Notifications.create()
        .title("Produit supprimer")
        .text("votre produit a été supprimer avec succes")
        .graphic(null)
        .hideAfter(Duration.seconds(5))
        .position(Pos.BOTTOM_RIGHT)
        .onAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("supprimer avec succes");
            }
               });
        notificationBuilder.showConfirm();
    }

    @FXML
    private void uploadimg(ActionEvent event) {
          FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG
                = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
        FileChooser.ExtensionFilter extFilterjpg
                = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extFilterPNG
                = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
        FileChooser.ExtensionFilter extFilterpng
                = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        fileChooser.getExtensionFilters()
                .addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);

         File f = fileChooser.showOpenDialog(new Stage());

         imgp.setText(f.getAbsoluteFile().toURI().toString());
    }
/*
    @FXML
    private void onproduitNomEdit(TableColumn.CellEditEvent<Produit, String> event) {
         Produit p = tableprod.getSelectionModel().getSelectedItem();
         p.setLibelle(event.getNewValue());
    }
    @FXML
    private void onproduitDescrEdit(TableColumn.CellEditEvent<Produit, String> event) {
         Produit p = tableprod.getSelectionModel().getSelectedItem();
         p.setDescr(event.getNewValue());
    }
    @FXML
    private void onproduitTypeEdit(TableColumn.CellEditEvent<Produit, String> event) {
         Produit p = tableprod.getSelectionModel().getSelectedItem();
         p.setType(event.getNewValue());
    }
    @FXML
    private void onproduitPrixEdit(TableColumn.CellEditEvent<String, Float> event) {
         Produit p = tableprod.getSelectionModel().getSelectedItem();
         p.setPrix(event.getNewValue());
    }
*/

    @FXML
    private void onproduitNomEdit(TableColumn.CellEditEvent<Produit, String> event) {
          Produit p = tableprod.getSelectionModel().getSelectedItem();
         p.setLibelle(event.getNewValue());
         ps.modifierProduit(p, p.getId());
         //System.out.println(p);
         //System.out.println(event.getNewValue());
    }

    @FXML
    private void onproduitDescrEdit(TableColumn.CellEditEvent<Produit, String> event) {
         Produit p = tableprod.getSelectionModel().getSelectedItem();
         p.setDescr(event.getNewValue());
         ps.modifierProduit(p, p.getId());
    }

    @FXML
    private void onproduitTypeEdit(TableColumn.CellEditEvent<Produit, String> event) {
        Produit p = tableprod.getSelectionModel().getSelectedItem();
         p.setType(event.getNewValue());
         ps.modifierProduit(p, p.getId());
    }

    @FXML
    private void onproduitPrixEdit(TableColumn.CellEditEvent<Produit, Float> event) {
         Produit p = tableprod.getSelectionModel().getSelectedItem();
         p.setPrix(event.getNewValue());
         ps.modifierProduit(p, p.getId());
    }
    
    @FXML
    void onactionpdf(ActionEvent event)  {
        
        if(tableprod.getSelectionModel().getSelectedItem()!= null){
        Produit P = tableprod.getSelectionModel().getSelectedItem();
        try {
            pdf.GeneratePdf("xxx", P);
        } catch (DocumentException ex) {
            Logger.getLogger(FXMLProduitController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FXMLProduitController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(FXMLProduitController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
}}
    
    @FXML
    void onQRcode(ActionEvent event) {
        Produit p = tableprod.getSelectionModel().getSelectedItem();
      

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        String Information = "nom de produit : "+p.getLibelle()+"\n"+"Taille : "+p.getTaille();
        int width = 300;
        int height = 300;
        BufferedImage bufferedImage = null;
         try{
            BitMatrix byteMatrix = qrCodeWriter.encode(Information, BarcodeFormat.QR_CODE, width, height);
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
            
            
            

            
            qrcodee.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
            // TODO
        } catch (WriterException ex) {
        }}
   
        


    
    
    @FXML
    void onactiontri(ActionEvent event) {
        Produit p = new Produit();
        
        //forums = ps.afficherProduit().stream.collect(Collectors.toCollection(() -> new TreeSet<Produit>((Produit a, Produit b) -> ( b.getPrix() * 100) - (a.getPrix() * 100))));
       TreeSet<Produit> forums = ps.afficherProduit().stream().collect(Collectors.toCollection(() -> new TreeSet<Produit>((a, b) -> (Float.compare(b.getPrix()* 100, a.getPrix()* 100)))));

        taille.setCellValueFactory(new PropertyValueFactory<Produit,String>("taille"));
        tvi.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("id"));
        tvn.setCellValueFactory(new PropertyValueFactory<Produit,String>("libelle"));
        qte.setCellValueFactory(new PropertyValueFactory<Produit,Integer>("qte"));
        tvprix.setCellValueFactory(new PropertyValueFactory<Produit, Float>("prix"));
        tableprod.setItems((FXCollections.observableArrayList(forums)));
        
        System.out.println(forums);
        ObservableList<Produit> oList = FXCollections.observableArrayList(ps.afficherProduit());
        FilteredList<Produit> filteredData = new FilteredList<Produit>(oList, b -> true);
        rech.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate( m -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (m.getLibelle().toLowerCase().indexOf(lowerCaseFilter)!=-1) {
                    return true;
                } else return false;
            });
        });
        SortedList<Produit> sortedList = new SortedList <Produit>(filteredData);
        sortedList.comparatorProperty().bind(tableprod.comparatorProperty())    ;
        tableprod.setItems(sortedList);

    }

    
    }

