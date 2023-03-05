/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import model.Produit;

/**
 *
 * @author Marwen.M
 */
public interface IproduitController {
      public void ajouterProduit(Produit pa);
    public void modifierProduit(Produit pa, int id);
    public void supprimerProduit(int id) ;
    public List<Produit> afficherProduit();
    public Produit rechProduit(int id);
    
}
