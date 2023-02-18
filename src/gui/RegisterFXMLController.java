/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Ranim Ahmadi
 */
public class RegisterFXMLController implements Initializable {

    @FXML
    private TextField fxLogin;
    @FXML
    private TextField fxPassword;
    @FXML
    private TextField fxConfirmPassword;
    @FXML
    private TextField fxPrenom;
    @FXML
    private TextField fxNom;
    @FXML
    private TextField fxEmail;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
