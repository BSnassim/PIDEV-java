/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import Controller.CommandeController;
import Controller.ProduitController;
import java.sql.Date;
import model.Commande;
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
        long now = System.currentTimeMillis();
        Date DateCommande =new Date(now); 
        
        //connexionDB db1 = connexionDB.getInstance(); 
        ProduitController ps=new ProduitController();
        Produit p= new Produit(1,"moh","xxxxxxx","zzz","type",1);
         ps.ajouterProduit(p);
        //connexionDB db2 = connexionDB.getInstance(); 
        
      //  Produit p= new Produit(20,"image","mohsen","type",8888);
     //   ProduitController p1=new ProduitController();
        //p1.ajouterProduit(p);
       // p1.modifierProduit(p, 2);
       //System.out.println(p1.afficherProduit());

        //System.out.println(p1.rechProduit(2));
        //p1.supprimerProduit(2);
        
        
      //  Commande c= new Commande(300,DateCommande,4444);
      //  CommandeController c1=new CommandeController();
        //c1.ajouterCommande(c);
        //c1.modifierCommande(c, 1);
        //System.out.println(c1.afficherCommande());
        //System.out.println(c1.rechCommande(1));
        //c1.supprimerCommande(1);
        
         
    }
    
}
