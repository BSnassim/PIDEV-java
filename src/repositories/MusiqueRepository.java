package repositories;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import interfaces.IMusiqueRepository;
import model.Musique;
import utils.connexionDB;

public class MusiqueRepository implements IMusiqueRepository {

	Statement ste;
	Connection conn = connexionDB.getInstance().getConnexion();

	public void uploadFile(String name, byte[] content, int artistId) {
		try {
			String path = "C:/uploadedFiles/Music/" + artistId + "/";
			File dir = new File(path);
			dir.mkdirs();
			FileOutputStream fos = new FileOutputStream(dir + "/" + name);
			fos.write(content);
			fos.flush();
			fos.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Problem uploading audio file");
		}
	}

	@Override
	public void createMusique(Musique m) {
		try {
			uploadFile(m.getFileName(), m.getFileContent(), m.getId_Artiste());
			m.setChemin("C:/uploadedFiles/Music/" + m.getId_Artiste() + "/" + m.getFileName());
			m.setDateCreation(new Date(System.currentTimeMillis()));
			String req;
			if (m.getId_album() == null) {
				req = "INSERT INTO `musique`( `nom`, `chemin`, `dateCreation`, `longueur`, `id_artiste`,`id_categorie`) VALUES ('"
						+ m.getNom() + "','" + m.getChemin() + "','" + m.getDateCreation() + "','" + m.getLongueur()
						+ "','" + m.getId_Artiste() + "','" + m.getId_Categorie() + "')";
			} else {
				req = "INSERT INTO `musique`( `nom`, `chemin`, `dateCreation`, `longueur`, `id_artiste`,`id_categorie`,`id_album`) VALUES ('"
						+ m.getNom() + "','" + m.getChemin() + "','" + m.getDateCreation() + "','" + m.getLongueur()
						+ "','" + m.getId_Artiste() + "','" + m.getId_Categorie() + "','" + m.getId_album() + "')";
			}
			ste = conn.createStatement();
			ste.executeUpdate(req);
			System.out.println("Musique ajouté");
			ste.close();
		} catch (SQLException ex) {
			System.err.println("Probleme lors de l'ajout du Musique");
			System.out.println(ex.getMessage());
		}

	}

	@Override
	public void updateMusique(Musique m, int id) {
		try {
			String req;
			if (m.getId_album() == null) {
				req = "UPDATE `musique` SET `nom` = '" + m.getNom() + "', `longueur` = '" + m.getLongueur()
						+ "', `id_Artiste` = '" + m.getId_Artiste() + "', `id_Categorie` = '" + m.getId_Categorie()
						+ "'WHERE `id` = " + id;
			} else {
				req = "UPDATE `musique` SET `nom` = '" + m.getNom() + "', `longueur` = '" + m.getLongueur()
						+ "', `id_Artiste` = '" + m.getId_Artiste() + "', `id_Categorie` = '" + m.getId_Categorie()
						+ "', `id_album` = '" + m.getId_album() + "'WHERE `id` = " + id;
			}
			ste = conn.createStatement();
			ste.executeUpdate(req);
			System.out.println("Musique mis à jour");
			ste.close();
		} catch (SQLException ex) {
			System.err.println("Probleme lors de mis a jour de Musique");
			System.out.println(ex.getMessage());
		}

	}

	@Override
	public void deleteMusique(int id) {
		try {
			String req = "DELETE FROM `musique` WHERE id = " + id;
			ste = conn.createStatement();
			ste.executeUpdate(req);
			System.out.println("Musique supprimé");
			ste.close();
		} catch (SQLException ex) {
			System.err.println("Probleme lors de suppression du Musique");
			System.out.println(ex.getMessage());
		}
	}

	@Override
	public List<Musique> findAllMusique() {
		List<Musique> list = new ArrayList<>();
		try {
			String req = "Select * from Musique";
			ste = conn.createStatement();

			ResultSet RS = ste.executeQuery(req);
			while (RS.next()) {
				Musique m = new Musique();
				m.setId(RS.getInt("id"));
				m.setNom(RS.getString("nom"));
				m.setChemin(RS.getString("chemin"));
				m.setDateCreation(RS.getDate("dateCreation"));
				m.setLongueur(RS.getString("longueur"));
				m.setId_Artiste(RS.getInt("id_artiste"));
				m.setId_Categorie(RS.getInt("id_categorie"));
				m.setId_album(RS.getInt("id_album"));
				FileInputStream fis = new FileInputStream(m.getChemin());
				m.setFileContent(fis.readAllBytes());
				fis.close();
				list.add(m);
			}
			ste.close();
		} catch (SQLException e) {
			System.err.println("Probleme lors de lecture des Musique");
			System.out.println(e.getMessage());
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage()+" file not found");
		} catch (IOException e) {
			System.out.println(e.getMessage()+" problem loading file content");
		}
		return (list != null) ? list : null;
	}

	@Override
	public Musique findMusique(int id) {
		Musique m = new Musique();
		try {
			String req = "Select * from musique where id =" + id;
			ste = conn.createStatement();

			ResultSet RS = ste.executeQuery(req);
			RS.first();
			m.setId(RS.getInt("id"));
			m.setNom(RS.getString("nom"));
			m.setChemin(RS.getString("chemin"));
			m.setDateCreation(RS.getDate("dateCreation"));
			m.setLongueur(RS.getString("longueur"));
			m.setId_Artiste(RS.getInt("id_artiste"));
			m.setId_Categorie(RS.getInt("id_categorie"));
			m.setId_album(RS.getInt("id_album"));
			FileInputStream fis = new FileInputStream(m.getChemin());
			m.setFileContent(fis.readAllBytes());
			fis.close();
			ste.close();
		} catch (SQLException ex) {
			System.err.println("Probleme lors du lecture d'un Musique");
			System.out.println(ex.getMessage());
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		return (m != null) ? m : null;
	}

}
