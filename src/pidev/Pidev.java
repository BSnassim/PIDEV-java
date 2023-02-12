/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import controller.ArtisteController;
import model.Utilisateur;
import controller.UtilisateurController;
import utils.connexionDB;
import java.sql.SQLException;
import model.Artiste;

/**
 *
 * @author Ranim Ahmadi
 */
public class Pidev {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       // connexionDB connexionDB = new connexionDB();
       
         Utilisateur u1 = new Utilisateur("ranim","gfrds","ahmadi","siwar","ahmadi@mail.com");
          Utilisateur u2 = new Utilisateur("suzi","gfrdml","kiko","loulou","ahmadi@mail.tn");
         UtilisateurController cc = new UtilisateurController();
         ArtisteController cc1 = new ArtisteController();
         Artiste a1 = new Artiste("artiste","chanteur","music",1);
         cc1.ajouterArtiste(a1);
         
        // cc.ajouterUtilisateur(u2);
        // cc.supprimerUtilisateur(3);
        // cc.modifierUtilisateur(u1, 1);
    }
    
}

