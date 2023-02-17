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
        Categorie ca = new Categorie("ghoneya");
        Categorie ca2 = new Categorie("rap");
         Categorie ca3 = new Categorie("Arabesque");
        Catalogue c2 = new Catalogue("Artiste", 2);
        Catalogue c3 = new Catalogue("siwar", 6);
        Catalogue c4 = new Catalogue("liste",4);

        CategorieService cc = new CategorieService ();
        CatalogueService cc1 = new CatalogueService();

         //cc.ajouterCategorie(ca3);
         //System.out.println(cc.afficherCategorie());
        //cc1.ajouterCatalogue(c3);
        //cc.modifierCategorie(ca, 6);
         //System.out.println(cc1.afficherCatalogue());
        // cc1.modifierCatalogue(c3, 6);
        
        //cc.supprimerCategorie(3);
        //System.out.println(cc1.afficherCatalogue());
        //System.out.println(cc.afficherCategorie());
       // System.out.println(cc1.rechCatalogue(9)); 
        //System.out.println(cc.rechCatalogue(2));
        
        
        //System.out.println(cc1.filterCatalogue("id","9"));
        //System.out.println(cc1.filterCatalogue("id_categorie","6"));
       // cc1.ajouterCatalogue(c4);     
       cc1.modifierCatalogue(c4, 6);
    }
    
}
