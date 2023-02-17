/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import controller.CatalogueController;
import controller.CategorieController;
import java.sql.SQLException;
import model.Catalogue;
import model.Categorie;
import utils.connexionDB;

/**
 *
 * @author Siwar Ahmadi
 */
public class Pidev {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Catalogue c = new Catalogue("Artiste", 1);
        Categorie ca = new Categorie("ghoneya");
        Categorie ca2 = new Categorie("rap");
        Catalogue c2 = new Catalogue("Artiste", 2);
        Catalogue c3 = new Catalogue("si_aymen", 6);

        CategorieController cc = new CategorieController();
        CatalogueController cc1 = new CatalogueController();

        cc.ajouterCategorie(ca2);
         System.out.println(cc.afficherCategorie());
        cc1.ajouterCatalogue(c3);
        cc.modifierCategorie(ca, 6);
         System.out.println(cc1.afficherCatalogue());
         cc1.modifierCatalogue(c3, 6);
        
        cc1.supprimerCatalogue(6);
        System.out.println(cc1.afficherCatalogue());
        System.out.println(cc.afficherCategorie());
        System.out.println(cc1.rechCatalogue(10)); 
        System.out.println(cc.rechCatalogue(2));
        
        

    }

}
