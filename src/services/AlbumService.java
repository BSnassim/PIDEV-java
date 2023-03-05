package services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import interfaces.IAlbumRepository;
import model.Album;
import utils.connexionDB;

public class AlbumService implements IAlbumRepository {

	Statement ste;
	Connection conn = connexionDB.getInstance().getConnexion();

	@Override
	public void createAlbum(Album a) {
		try {
			a.setDateCreation(new Date(System.currentTimeMillis()));
			String req = "INSERT INTO `Album`( `nom`, `dateCreation`, `id_artiste`) VALUES ('" + a.getNom() + "','"
					+ a.getDateCreation() + "','" + a.getId_artiste() + "')";
			ste = conn.createStatement();
			ste.executeUpdate(req);
			System.out.println("Album ajouté");
			ste.close();
		} catch (SQLException ex) {
			System.err.println("Probleme lors de l'ajout du Album");
			System.out.println(ex.getMessage());
		}
	}

	@Override
	public void updateAlbum(Album a, int id) {
		try {
			String req = "UPDATE `Album` SET `nom` = '" + a.getNom() + "', `dateCreation` = '" + a.getDateCreation()
					+ "', `id_Artiste` = '" + a.getId_artiste() + "'WHERE `id` = " + id;
			ste = conn.createStatement();
			ste.executeUpdate(req);
			System.out.println("Album mis à jour");
			ste.close();
		} catch (SQLException ex) {
			System.err.println("Probleme lors de mis a jour de Album");
			System.out.println(ex.getMessage());
		}
	}

	@Override
	public void deleteAlbum(int id) {
		try {
			String req = "DELETE FROM `album` WHERE id = " + id;
			ste = conn.createStatement();
			ste.executeUpdate(req);
			System.out.println("Album supprimé");
			ste.close();
		} catch (SQLException ex) {
			System.err.println("Probleme lors de suppression du Album");
			System.out.println(ex.getMessage());
		}
	}

	@Override
	public List<Album> findAllAlbum() {
		List<Album> list = new ArrayList<>();
		try {
			String req = "Select * from album";
			ste = conn.createStatement();

			ResultSet RS = ste.executeQuery(req);
			while (RS.next()) {
				Album a = new Album();
				a.setId(RS.getInt("id"));
				a.setNom(RS.getString("nom"));
				a.setDateCreation(RS.getDate("dateCreation"));
				a.setId_artiste(RS.getInt("id_artiste"));
				list.add(a);
			}
			ste.close();
		} catch (SQLException ex) {
			System.err.println("Probleme lors de lecture des Albums");
			System.out.println(ex.getMessage());
		}
		return (list != null) ? list : null;
	}

	@Override
	public Album findAlbum(int id) {
		Album a = new Album();
		try {
			String req = "Select * from Album where id =" + id;
			ste = conn.createStatement();

			ResultSet RS = ste.executeQuery(req);
			RS.first();
			a.setId(RS.getInt("id"));
			a.setNom(RS.getString("nom"));
			a.setDateCreation(RS.getDate("dateCreation"));
			a.setId_artiste(RS.getInt("id_artiste"));
			ste.close();
		} catch (SQLException ex) {
			System.err.println("Probleme lors du lecture d'un Album");
			System.out.println(ex.getMessage());
		}

		return (a != null) ? a : null;
	}

}
