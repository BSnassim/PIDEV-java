package gui.backOffice.Musique;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Musique;
import services.AlbumService;
import services.MusiqueService;

public class MusiqueListController implements Initializable {

	MusiqueService musicRepo = new MusiqueService();
	AlbumService albumRepo = new AlbumService();
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
	private TextField searchBar;
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
	private TableColumn<Musique, String> colAlbum;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		colNom.setCellValueFactory(new PropertyValueFactory<Musique, String>("nom"));
		colChemin.setCellValueFactory(new PropertyValueFactory<Musique, String>("chemin"));
		colDate.setCellValueFactory(new PropertyValueFactory<Musique, Date>("dateCreation"));
		colLongueur.setCellValueFactory(new PropertyValueFactory<Musique, String>("longueur"));
		colArtiste.setCellValueFactory(new PropertyValueFactory<Musique, Integer>("id_Artiste"));
		colCategorie.setCellValueFactory(new PropertyValueFactory<Musique, Integer>("id_Categorie"));
		colAlbum.setCellValueFactory(new Callback<CellDataFeatures<Musique, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<Musique, String> param) {
				return (param.getValue().getId_album() != 0)
						? new SimpleStringProperty(albumRepo.findAlbum(param.getValue().getId_album()).getNom())
						: null;
			}
		});
		loadData();
	}

	public void loadData() {
		ObservableList<Musique> oList = FXCollections.observableArrayList(musicRepo.findAllMusique());
		FilteredList<Musique> filteredData = new FilteredList<Musique>(oList, b -> true);
		searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate( m -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (m.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else
					return false;
			});
		});
		SortedList<Musique> sortedList = new SortedList<Musique>(filteredData);
		sortedList.comparatorProperty().bind(musicList.comparatorProperty())	;
		musicList.setItems(sortedList);
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
			if (musicList.getSelectionModel().getSelectedItem() != null) {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("MusiqueInsert.fxml"));
				Parent root = loader.load();
				Scene scene = new Scene(root);
				Musique m = musicList.getSelectionModel().getSelectedItem();
				MusiqueInsertController mic = loader.getController();
				mic.editableData(m);
				Stage stage = new Stage();
				stage.setScene(scene);
				stage.show();
				stage.toFront();
			}
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
