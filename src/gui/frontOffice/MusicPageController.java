package gui.frontOffice;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Musique;
import repositories.MusiqueRepository;

public class MusicPageController implements Initializable{
	
    @FXML
    private Text artiste;

    @FXML
    private Text categorie;

    @FXML
    private Text dateC;

    @FXML
    private Text longueur;

    @FXML
    private Text titre;
    
    @FXML
    private Button playButton;

    @FXML
    private Button returnToList;
    
    private Musique song;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	public void setData(int id) {
		MusiqueRepository musicRepo = new MusiqueRepository();
		this.song = musicRepo.findMusique(id);
		this.titre.setText(song.getNom());
		this.artiste.setText(song.getId_Artiste().toString());
		this.longueur.setText(song.getLongueur());
		this.dateC.setText(song.getDateCreation().toString());
		this.categorie.setText(song.getId_Categorie().toString());
	}
	
	@FXML
    public void onPlay(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("MusicPlayer.fxml"));
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
    void returnButton(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("MusicList.fxml"));
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



}
