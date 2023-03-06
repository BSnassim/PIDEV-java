package gui.backOffice.Musique;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.StringConverter;
import model.Album;
import model.Musique;
import services.AlbumService;
import services.MusiqueService;

public class MusiqueInsertController implements Initializable {

	private int id = 0;
	private MusiqueService musicRepo = new MusiqueService();
	private AlbumService albumRepo = new AlbumService();
	private List<Album> albumList = new ArrayList<Album>();
	private byte[] fileContent;
	private String fileName;
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
//	private ComboBox<Artiste> artisteField;
	@FXML
	private TextField categorieField;
	@FXML
	private ComboBox<Album> albumField;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		albumList = albumRepo.findAllAlbum();

		albumField.setItems(FXCollections.observableArrayList(albumList));
		albumField.setConverter(new StringConverter<Album>() {
			@Override
			public String toString(Album object) {
				return object.getNom();
			}

			@Override
			public Album fromString(String string) {
				return albumField.getItems().stream().filter(a -> a.getNom().equals(string)).findFirst().orElse(null);
			}
		});
	}

	public void editableData(Musique m) {
		this.id = m.getId();
		nomField.setText(m.getNom());
		cheminField.setText(m.getChemin());
		longueurField.setText(m.getLongueur());
		artisteField.setText(m.getId_Artiste().toString());
		categorieField.setText(m.getId_Categorie().toString());

		int x = 0, i = 0;
		System.out.println(albumList.get(2));
		while ((albumList.size() > i) && (albumList.get(i).getId() != m.getId_album())) {
			i++;
		}
		x = i;
		albumField.getSelectionModel().select(x);

	}

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
		} /* if editing */ else if (this.id != 0) {
			Musique m = new Musique(nomField.getText(), longueurField.getText(),
					Integer.parseInt(artisteField.getText()), Integer.parseInt(categorieField.getText()));
			if (albumField.getSelectionModel().getSelectedItem() != null) {
				m.setId_album(albumField.getSelectionModel().getSelectedItem().getId());
			}
			m.setFileContent(fileContent);
			m.setFileName(fileName);
			musicRepo.updateMusique(m, this.id);
			Stage stage = (Stage) ajouter.getScene().getWindow();
			stage.close();
		} /* if inserting */ else {
			Musique m = new Musique(nomField.getText(), longueurField.getText(),
					Integer.parseInt(artisteField.getText()), Integer.parseInt(categorieField.getText()));
			if (albumField.getSelectionModel().getSelectedItem() != null) {
				m.setId_album(albumField.getSelectionModel().getSelectedItem().getId());
			}
			m.setFileContent(fileContent);
			m.setFileName(fileName);
			musicRepo.createMusique(m);
//			FXMLLoader loader = new FXMLLoader(getClass().getResource("MusiqueList.fxml"));
//			MusiqueListController mlc = loader.getController();
//			mlc.loadData();
//			Notifications notificationBuilder = Notifications.create()
//			        .title("Musique ajouté")
//			        .text("Musique ajouté avec succées")
//			        .graphic(null)
//			        .hideAfter(Duration.seconds(5))
//			        .position(Pos.BOTTOM_RIGHT)
//			        .onAction(new EventHandler<ActionEvent>() {
//			            @Override
//			            public void handle(ActionEvent event) {
//			                System.out.println("Success");
//			            }
//			               });
//			        notificationBuilder.showConfirm();
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
		try {
			this.fileContent = Files.readAllBytes(f.toPath());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		this.fileName = f.getName();
		cheminField.setText(f.getAbsoluteFile().toURI().toString());
	}

}
