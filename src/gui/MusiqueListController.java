package gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Musique;

public class MusiqueListController implements Initializable {

	@FXML
	private Button userButton;
	@FXML
	private Button addMusic;
	@FXML
	private Button editMusic;
	@FXML
	private TableView<Musique> musicList;
	@FXML
	private TableColumn<Musique, String> colNom;
	@FXML
	private TableColumn<Musique, String> colChemin;
	@FXML
	private TableColumn<Musique, Date> colDate;
	@FXML
	private TableColumn<Musique, String> colLongueur;
	@FXML
	private TableColumn<Musique, Integer> colArtiste;
	@FXML
	private TableColumn<Musique, Integer> colCategorie;

	@FXML
	private void onActionUserButton(ActionEvent event) {
		System.out.println("go to user");
	}

	private ObservableList<Musique> musicRows = FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
//		musicRows.add( new Musique("song1", "path/to/song", Date.valueOf("21-02-2023"), "2:05", 1, 1));
		colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
		colChemin.setCellValueFactory(new PropertyValueFactory<>("chemin"));
		colDate.setCellValueFactory(new PropertyValueFactory<>("dateCreation"));
		colLongueur.setCellValueFactory(new PropertyValueFactory<>("longueur"));
		colArtiste.setCellValueFactory(new PropertyValueFactory<>("id_Artiste"));
		colCategorie.setCellValueFactory(new PropertyValueFactory<>("id_Categorie"));
		musicList.setItems(musicRows);
	}

	@FXML
	private void onActionAddMusic(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("MusiqueInsert.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.show();
			stage.toFront();
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	@FXML
	private void onActionEditMusic(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("MusiqueInsert.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.show();
			stage.toFront();
			stage.setOnCloseRequest(e -> {
				
			});
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	}

}
