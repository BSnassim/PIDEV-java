/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import controller.ArtisteController;
import controller.FavorisController;
import model.Utilisateur;
import controller.UtilisateurController;
import utils.connexionDB;
import java.sql.SQLException;
import model.Artiste;
import model.Favoris;

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
       
       // Utilisateur u1 = new Utilisateur("aa","gfrds","ahmadi","siwar","ranim.ahmadi@esprit.tn");
         Utilisateur u2 = new Utilisateur("aa","fdss","ahmdadi","sicwar","ranim.ahmadiesprit.tn");
         // Utilisateur u2 = new Utilisateur("suzi","gfrdml","kiko","loulou","ahmadi@mail.tn");
          // Utilisateur u3 = new Utilisateur("luna","hmida","kiko","rawdha","ahmadi2@mail.tn");
        UtilisateurController cc = new UtilisateurController();
        cc.ajouterUtilisateur(u2);
        // ArtisteController cc1 = new ArtisteController();
       //  ArtisteController ranim = new ArtisteController();
//         FavorisController h = new FavorisController(); 
    //    Artiste a1 = new Artiste("artiste","tunisie","music",2);
        //Artiste a2 = new Artiste("nancy","elissa","music",5);
     //  Favoris f1 = new Favoris()  ;
        
       // h.ajouterFavoris(f1);
        // cc1.ajouterArtiste(a2);
        // ranim.modifierArtiste(a1,2);
      //  ranim.setOrigine("tunisie");
       //  ranim.modifierArtiste(a1, 2);
        // for(int i = 0 ;i<ranim.afficherArtiste().size();i++){
        // System.out.println(ranim.afficherArtiste().get(i).toString());
     
         
         //cc.ajouterUtilisateur(u1);
         //cc.supprimerUtilisateur(6);
         //cc.modifierUtilisateur(u3, 4);
                   

        //cc.afficherUtilisateur(u1);
        // for(int i = 0 ;i<cc.afficherUtilisateur().size();i++){
         //  System.out.println(cc.afficherUtilisateur().get(i).toString());
     
       // u1.setNom("sousou");
      //  cc.modifierUtilisateur(u1,5);
        //for(int i = 0 ;i<cc.afficherUtilisateur().size();i++){
       //     System.out.println(cc.afficherUtilisateur().get(i).toString());
       // }
    }
    }
    

