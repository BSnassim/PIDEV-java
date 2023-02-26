package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class guiMain extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
        Parent root = FXMLLoader.load(getClass().getResource("backOffice/MusiqueList.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
