package gui;

import java.sql.Date;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Album;
import repositories.AlbumRepository;

public class AlbumInsertController {

	AlbumRepository musicRepo = new AlbumRepository();
	@FXML
	private Button annuler;
	@FXML
	private Button ajouter;
	@FXML
	private TextField nomField;
	@FXML
	private TextField artisteField;

	@FXML
	public void onActionAnnuler(ActionEvent event) {
		Stage stage = (Stage) annuler.getScene().getWindow();
		stage.close();
	}

	@FXML
	public void onActionAjouter(ActionEvent event) {
		if (nomField.getText().isBlank() || artisteField.getText().isBlank()) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Error");
			alert.setHeaderText("Erreur lors de l'insertion");
			alert.setContentText("Tous les champs doivent étre rempli !");
			alert.showAndWait();
		} else {
			Album a = new Album(nomField.getText(), Integer.parseInt(artisteField.getText()));
			musicRepo.createAlbum(a);
			Stage stage = (Stage) ajouter.getScene().getWindow();
			stage.close();
		}
	}

}
