/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.login;

import services.UtilisateurController;
import java.net.URL;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import model.Utilisateur;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ForgetPasswordFXMLController implements Initializable {

    @FXML
    private Pane pane1;
    @FXML
    private TextField fxLog;
    @FXML
    private Text cdsEnvoyer;
    @FXML
    private Text cdsPass;
    @FXML
    private Pane pane2;
    @FXML
    private TextField fxCode;
    @FXML
    private Text cdsConfirmer;
    @FXML
    private Text cdsPass1;
    @FXML
    private Pane pane3;
    @FXML
    private TextField fxnewPassword;
    @FXML
    private Text cdsReinitialiser;
    @FXML
    private Text cdsPass11;
    private static int code = 0; private static String loggg = ""; 
    
    private UtilisateurController uc = new UtilisateurController();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        pane3.setVisible(false);
        pane2.setVisible(false);
        pane1.setVisible(true);
        
    }    

    @FXML
    private void EnvoyerBTN(ActionEvent event) {
        pane1.setVisible(false);
        pane3.setVisible(false);
        pane2.setVisible(true);
        Utilisateur u = uc.rechUtilisateurByLogin(fxLog.getText());
        if (u!=null){
            loggg=fxLog.getText();
            Random random = new Random();
            int randomNumber = random.nextInt(900000) + 100000;
            System.out.println("Random number: " + randomNumber);
            code = randomNumber;
            sendMail(u.getEmail(),randomNumber);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("login incorrect");
            alert.show();
        }
        
    }

    @FXML
    private void ConfirmerBTN(ActionEvent event) {
        
        if (fxCode.getText().equals(String.valueOf(code))){
            pane2.setVisible(false);
            pane1.setVisible(false);
            pane3.setVisible(true);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("code incorrect");
            alert.show();
        }
    }

    @FXML
    private void reinitialiserBTN(ActionEvent event) {
        
        Utilisateur u = uc.getUserByLogin(loggg);
        u.setPassword(fxnewPassword.getText());
        uc.modifierUtilisateurPassword(u, u.getId());
        Stage primaryStage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        primaryStage.close(); 
        
    }
    public void sendMail(String recipient,int code){
        System.out.println(recipient);
        /*String host="ranim.ahmadi@esprit.tn";
        final String user="ranim.ahmadi@esprit.tn";//← my email address
        final String password="201JFT2214";//change accordingly   //← my email password
*/
        String host="ranim.ahmadi@esprit.tn";  //← my email address
        final String user="ranim.ahmadi@esprit.tn";//← my email address
        final String password="201JFT2214";//change accordingly   //← my email password

        String to=recipient;//→ the EMAIL i want to send TO →
        Properties props = new Properties (); 
        props.put("mail.smtp.ssl.trust", "*");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.starttls.enable", "true");
        
       Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user,password);
                    }
                });

        //My message :
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            message.setSubject(" Reinitialisation de mot de passe !!! ");
            //Text in emial :
            //message.setText("  → Text message for Your Appointement ← ");
            //Html code in email :
            message.setContent(
                    "<h1 style \"color:red\" >Code de confirmation :   "+code+" </h1> <br/> <img width=\"50%\" height=\"50%\" src=https://i.imgur.com/iYcBkOf.png>",
                    "text/html");

            //send the message
            Transport.send(message);

            System.out.println("message sent successfully via mail ... !!! ");

        } catch (MessagingException e) {e.printStackTrace();}

    }


    
}
