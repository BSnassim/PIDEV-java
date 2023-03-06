/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.login;

import gui.backOffice.UtilisateurFXMLController;
import java.io.File;
import services.UtilisateurController;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Utilisateur;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ImNotARobotFXMLController implements Initializable {

    @FXML
    private ImageView image1;
    @FXML
    private ImageView image2;
    @FXML
    private ImageView image3;
    @FXML
    private ImageView image4;
    @FXML
    private ImageView image5;
    @FXML
    private ImageView image6;
    @FXML
    private ImageView image7;
    @FXML
    private ImageView image8;
    @FXML
    private ImageView image9;
    @FXML
    private CheckBox notRobot;
private boolean notrobot = false;
private List<String> imageBus=new ArrayList();
private List<String> imageBus2=new ArrayList();
private List<ImageView> images=new ArrayList();
List<Integer> orderOfShuffle = new ArrayList<>();
List<Integer> hh = new ArrayList<>();
private boolean stillok=true;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        imageBus.add("bus1.png");imageBus.add("bus2V.png");imageBus.add("bus3.png");
        imageBus.add("bus4.png");imageBus.add("bus5.png");imageBus.add("bus6V.png");
        imageBus.add("bus7V.png");imageBus.add("bus8.png");imageBus.add("bus9.png");
        
        imageBus2.add("bus1.png");imageBus2.add("bus2V.png");imageBus2.add("bus3.png");
        imageBus2.add("bus4.png");imageBus2.add("bus5.png");imageBus2.add("bus6V.png");
        imageBus2.add("bus7V.png");imageBus2.add("bus8.png");imageBus2.add("bus9.png");
        
        images.add(image1);images.add(image2);images.add(image3);
        images.add(image4);images.add(image5);images.add(image6);
        images.add(image7);images.add(image8);images.add(image9);
        Collections.shuffle(imageBus);
        
        for (int i = 0; i < imageBus.size(); i++) {
            if (imageBus.get(i).equals("bus2V.png") || imageBus.get(i).equals("bus6V.png") || imageBus.get(i).equals("bus7V.png")){
                hh.add(i+1);
                System.out.println(i+1);
            }
        }
        System.out.println(orderOfShuffle.toString());
        String path = ".\\src\\gui\\ressources\\image\\";
   
        for (int i=0;i<9;i++){
            try {
                InputStream stream = new FileInputStream(path+imageBus.get(i));
                images.get(i).setImage(new Image(stream));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ImNotARobotFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }    

    @FXML
    private void valide(ActionEvent event) throws IOException {
        System.out.println(stillok);
        System.out.println(hh.size());
        if (stillok && hh.size()==3 && notrobot){
                UtilisateurFXMLController u = new UtilisateurFXMLController();
            if (u.login.equals("admin") && u.password.equals("admin")){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("menuFXML.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);  
                Stage primaryStage2 = new Stage();
                primaryStage2.setTitle("Offre");
                primaryStage2.setScene(scene);
                primaryStage2.show();
                Stage primaryStage3 = (Stage) ((Button)event.getSource()).getScene().getWindow();
                primaryStage3.close(); 
            }
            else {
                UtilisateurController us = new UtilisateurController();
                Utilisateur ut = us.getUserLogin(u.login,u.password);
                if (ut==null){
                    /*u.cdsLogin.setText("Incorrect");
                    u.cdsPass.setText("Incorrect");

                    u.setTimerIncorrect();*/
                    Stage primaryStage3 = (Stage) ((Button)event.getSource()).getScene().getWindow();
                    primaryStage3.close(); 
                    return ;
                }
                else {
                        System.out.println("User ( id :"+ut.getId()+" ) Connected succefully  ");
                    Stage primaryStage3 = (Stage) ((Button)event.getSource()).getScene().getWindow();
            primaryStage3.close(); 
                }
            }
        }else {
            Stage primaryStage3 = (Stage) ((Button)event.getSource()).getScene().getWindow();
                    primaryStage3.close();
        }
        
    }

    @FXML
    private void clickImage(MouseEvent event) {
        int index = Integer.parseInt(((ImageView)event.getSource()).getId().substring(((ImageView)event.getSource()).getId().length()-1,((ImageView)event.getSource()).getId().length()));
        System.out.println(index);
        if (!hh.contains(index)){
            stillok=false;
        }
        ((ImageView)event.getSource()).setImage(null);
        
    }

    @FXML
    private void notRobotClick(ActionEvent event) {
        if (((CheckBox)event.getSource()).isSelected())
            notrobot=true;
        else
            notrobot=false;
        
                System.out.println(notrobot);

    }
    
}
