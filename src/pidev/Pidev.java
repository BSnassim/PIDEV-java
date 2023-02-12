/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import Controller.ProduitController;
import model.Produit;
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
        connexionDB db1 = connexionDB.getInstance(); 
        connexionDB db2 = connexionDB.getInstance(); 
        
        Produit p= new Produit(20,"image","mohsen","type",8888);
        ProduitController p1=new ProduitController();
        //p1.ajouterProduit(p);
       // p1.modifierProduit(p, 2);
       //System.out.println(p1.afficherProduit());

        //System.out.println(p1.rechProduit(2));
        p1.supprimerProduit(2);
        
         
    }
    
}
