package gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
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
import repositories.MusiqueRepository;

public class MusiqueListController implements Initializable {
	
	MusiqueRepository musicRepo = new MusiqueRepository();
	@FXML
	private Button userButton;
	@FXML
	private Button albumButton;
	@FXML
	private Button addMusic;
	@FXML
	private Button editMusic;
	@FXML
	private Button deleteMusic;
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
	private TableColumn<Musique, Integer> colAlbum;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		colNom.setCellValueFactory(new PropertyValueFactory<Musique,String>("nom"));
		colChemin.setCellValueFactory(new PropertyValueFactory<Musique,String>("chemin"));
		colDate.setCellValueFactory(new PropertyValueFactory<Musique,Date>("dateCreation"));
		colLongueur.setCellValueFactory(new PropertyValueFactory<Musique,String>("longueur"));
		colArtiste.setCellValueFactory(new PropertyValueFactory<Musique,Integer>("id_Artiste"));
		colCategorie.setCellValueFactory(new PropertyValueFactory<Musique,Integer>("id_Categorie"));
		colAlbum.setCellValueFactory(new PropertyValueFactory<Musique,Integer>("id_album"));
		loadData();
	}
	
	public void loadData() {
		musicList.setItems(FXCollections.observableArrayList(musicRepo.findAllMusique()));
	}
	
	/*
	 * Side menu buttons
	 */
	@FXML
	private void onActionUserButton(ActionEvent event) {
		System.out.println("go to user");
	}
	
	@FXML
	private void onActionAlbumButton(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("AlbumList.fxml"));
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
	
/*
 * Crud related buttons
 */

	@FXML
	private void onActionAddMusic(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("MusiqueInsert.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
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
	
	@FXML 
	private void onActionDeleteMusic(ActionEvent event) {
		Musique m = musicList.getSelectionModel().getSelectedItem();
        musicRepo.deleteMusique(m.getId());
        loadData();
	}

}
