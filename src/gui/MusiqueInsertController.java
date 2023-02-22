package gui;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Musique;
import repositories.MusiqueRepository;

public class MusiqueInsertController {

	private MusiqueRepository musicRepo = new MusiqueRepository();
	private byte[] fileContent;
	@FXML
	private Button annuler;
	@FXML
	private Button ajouter;
	@FXML
	private TextField nomField;
	@FXML
	private TextField cheminField;
	@FXML
	private TextField longueurField;
	@FXML
	private TextField artisteField;
	@FXML
	private TextField categorieField;
	@FXML
	private TextField albumField;

	@FXML
	public void onActionAnnuler(ActionEvent event) {
		Stage stage = (Stage) annuler.getScene().getWindow();
		stage.close();
	}

	@FXML
	public void onActionAjouter(ActionEvent event) {
		if (nomField.getText().isBlank() || artisteField.getText().isBlank() || cheminField.getText().isBlank()
				|| longueurField.getText().isBlank() || categorieField.getText().isBlank()) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Error");
			alert.setHeaderText("Erreur lors de l'insertion");
			alert.setContentText("Tous les champs sauf album doivent étre rempli !");
			alert.showAndWait();
		} else {
			Musique m = new Musique(nomField.getText(), longueurField.getText(),
					Integer.parseInt(artisteField.getText()), Integer.parseInt(categorieField.getText()),
					Integer.parseInt(albumField.getText()));
			musicRepo.createMusique(m);
			Stage stage = (Stage) ajouter.getScene().getWindow();
			stage.close();
		}
	}

	@FXML
	private void fileChooser(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();

		// Set extension filter
		FileChooser.ExtensionFilter extFilterAudio = new FileChooser.ExtensionFilter("Audio Files", "*.wav", "*.mp3",
				"*.aac");
		fileChooser.getExtensionFilters().addAll(extFilterAudio);

		File f = fileChooser.showOpenDialog(new Stage());
		

		cheminField.setText(f.getAbsoluteFile().toURI().toString());
	}

}
