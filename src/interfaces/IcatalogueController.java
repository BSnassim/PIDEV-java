/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import model.Catalogue;

/**
 *
 * @author Siwar Ahmadi
 */
public interface IcatalogueController { 
     public void ajouterCatalogue(Catalogue c);

    public void modifierCatalogue(Catalogue c, int id);

    public void supprimerCatalogue(int id);

    public List<Catalogue> afficherCatalogue();

    public Catalogue rechCatalogue(int id); 
    
    public List<Catalogue> filterCatalogue(String S,String SS);

    
}