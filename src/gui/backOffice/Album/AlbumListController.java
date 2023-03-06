package gui.backOffice.Album;

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
import model.Album;
import services.AlbumService;

public class AlbumListController implements Initializable{
	
	AlbumService albumRepo = new AlbumService();
	
	@FXML
	private Button musicButton;
	@FXML
	private TableView<Album> albumList;
	@FXML
	private TableColumn<Album, String> colNom;
	@FXML
	private TableColumn<Album, Date> colDate;
	@FXML
	private TableColumn<Album, Integer> colArtiste;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		colNom.setCellValueFactory(new PropertyValueFactory<Album,String>("nom"));
		colDate.setCellValueFactory(new PropertyValueFactory<Album,Date>("dateCreation"));
		colArtiste.setCellValueFactory(new PropertyValueFactory<Album,Integer>("id_artiste"));
		loadData();
	}
	public void loadData() {
		albumList.setItems(FXCollections.observableArrayList(albumRepo.findAllAlbum()));
	}
	
	/*
	 * Side menu buttons
	 */
	@FXML
	private void onActionMusicButton(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../Musique/MusiqueList.fxml"));
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
	private void onActionAddAlbum(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("AlbumInsert.fxml"));
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
	private void onActionEditAlbum(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("AlbumInsert.fxml"));
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
	private void onActionDeleteAlbum(ActionEvent event) {
		Album a = albumList.getSelectionModel().getSelectedItem();
        albumRepo.deleteAlbum(a.getId());
        loadData();
	}

}
