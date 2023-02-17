/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import model.Categorie;

/**
 *
 * @author Siwar Ahmadi
 */
public interface IcategorieController {
     public void ajouterCategorie(Categorie ca);
    public void modifierCategorie(Categorie ca, int id);
    public void supprimerCategorie(int id) ;
    public List<Categorie> afficherCategorie();
    public Categorie rechCatalogue(int id); 
   
    
    
}
