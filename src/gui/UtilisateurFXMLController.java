/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import controller.UtilisateurController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Utilisateur;

/**
 * FXML Controller class
 *
 * @author Ranim Ahmadi
 */
public class UtilisateurFXMLController implements Initializable {

    @FXML
    private PasswordField fxPass;
    @FXML
    private TextField fxLog;
    private int interval = 4;
    private Timer timer = new Timer();
    @FXML
    public static Text cdsLogin;
    @FXML
    public static Text cdsPass;
    public static String login =null;
    public static String password =null;
    /**
     * Initializes the controller class.
     */
    private UtilisateurController us = new UtilisateurController();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void LoginBTN(ActionEvent event) throws IOException {
        login=fxLog.getText();
        password=fxPass.getText();
        if (login.equals("")){
            cdsLogin.setText("Veuillez remplir le champ Login");
            setTimerLogin();
            return ;
        }
        if (password.equals("")){
            cdsPass.setText("Veuillez remplir le champ Password");
            setTimerPass();
            return ;
        }
        Parent root = FXMLLoader.load(getClass().getResource("imNotARobotFXML.fxml"));
            Scene scene = new Scene(root);
            Stage primaryStage2 = new Stage();
            primaryStage2.setTitle("Offre");
            primaryStage2.setScene(scene);
            
        primaryStage2.setScene(scene);
        primaryStage2.show();
        
        
    }

    @FXML
    private void forgetPasswordBTN(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("forgetPasswordFXML.fxml"));
            Scene scene = new Scene(root);
            Stage primaryStage2 = new Stage();
            primaryStage2.setTitle("Offre");
            primaryStage2.setScene(scene);
            
        primaryStage2.setScene(scene);
        primaryStage2.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void registerBTN(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("RegisterFXML.fxml"));
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
    public void setTimerLogin() {
    timer = new Timer();    
    timer.scheduleAtFixedRate(new TimerTask() {
        public void run() {
            if(interval > 0)
            {
                interval=interval-1;
            }
            else{
                timer.cancel();
                cdsLogin.setText("");
            }
                
        }
    }, 1000,1000);
    }
    public void setTimerPass() {
    timer = new Timer();    
    timer.scheduleAtFixedRate(new TimerTask() {
        public void run() {
            if(interval > 0)
            {
                interval=interval-1;
            }
            else{
                timer.cancel();
                cdsPass.setText("");
            }
                
        }
    }, 1000,1000);
    }

    public void setTimerIncorrect() {
    timer = new Timer();    
    timer.scheduleAtFixedRate(new TimerTask() {
        public void run() {
            if(interval > 0)
            {
                interval=interval-1;
            }
            else{
                cdsLogin.setText("");
                cdsPass.setText("");
            }
                
        }
    }, 1000,1000);    }
}
