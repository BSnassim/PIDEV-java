/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import model.Catalogue;
import model.Categorie;
import services.CatalogueService;
import services.CategorieService;

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
        Categorie ca = new Categorie("Classique");
        Categorie ca2 = new Categorie("rap");
        Categorie ca3 = new Categorie("Arabesque");
        Categorie ca4 = new Categorie("Orientale");
        Categorie ca5 = new Categorie("Orientale");
        Catalogue c2 = new Catalogue("Artiste", 10);
        Catalogue c3 = new Catalogue("siwar", 6);
        Catalogue c4 = new Catalogue("new",2);
        Catalogue c5 = new Catalogue("musique",2);

        CategorieService cc = new CategorieService ();
        CatalogueService cc1 = new CatalogueService(); 
        
        //cc1.modifierCatalogue(c4,13);

         //cc.ajouterCategorie(ca5);
         //System.out.println(cc.afficherCategorie());
        //cc1.ajouterCatalogue(c3);
        //cc.modifierCategorie(ca, 6);
         //System.out.println(cc1.afficherCatalogue());
        // cc1.modifierCatalogue(c3, 6);
        
        //cc.supprimerCategorie(4);
        //cc.supprimerCategorie(7);
        //cc.supprimerCategorie(8);
        //cc.supprimerCategorie(11);
        //System.out.println(cc1.afficherCatalogue());
        //System.out.println(cc.afficherCategorie());
       // System.out.println(cc1.rechCatalogue(9)); 
        //System.out.println(cc.rechCatalogue(30));
        
        
        //System.out.println(cc1.filterCatalogue("id","9"));
        //System.out.println(cc1.filterCatalogue("id_categorie","6"));
        //cc1.ajouterCatalogue(c5);     
       //cc1.modifierCatalogue(c4, 6);
       //cc1.ajouterCatalogue(c2);
    }
    
}
