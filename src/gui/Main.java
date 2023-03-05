/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Siwar Ahmadi
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });

Parent root;
        try {
             root = FXMLLoader.load(getClass().getResource("backOffice/menuFXML.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Hello World!");
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        try {
            
             root = FXMLLoader.load(getClass().getResource("frontOffice/CategorieFXML.fxml"));
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
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
