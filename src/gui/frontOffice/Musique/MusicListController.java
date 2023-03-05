package gui.frontOffice.Musique;

import java.awt.Font;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.GroupLayout.Alignment;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import services.MusiqueService;

public class MusicListController implements Initializable {

	@FXML
	private VBox musicVBox;

	List<Button> musicList = new ArrayList<>();
	MusiqueService musicRepo = new MusiqueService();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		musicRepo.findAllMusique().forEach(m -> {
			Button b = new Button(m.getNom());
			b.setAlignment(Pos.TOP_LEFT);
			b.setPrefWidth(Double.MAX_VALUE);
			b.setTextFill(Paint.valueOf("white"));
			b.setStyle("-fx-background-color : #4a6dd8; -fx-border-color: black; -fx-font-size: 15;-fx-font-weight: bold");
			b.setOnAction(e -> {
				try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("MusicPage.fxml"));
					Parent root = loader.load();
					Scene scene = new Scene(root);
					MusicPageController mpc = loader.getController();
					mpc.setData(m.getId());
					Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
					stage.setScene(scene);
					stage.show();
					stage.toFront();
				} catch (IOException ex) {
					System.out.println(ex.getMessage());
				}
			});
			musicList.add(b);
		});
		musicVBox.getChildren().clear();
		musicVBox.getChildren().addAll(musicList);

	}

}
