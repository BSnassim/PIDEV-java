/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import model.Artiste;


/**
 *
 * @author Ranim Ahmadi
 */
public interface IartisteController {
    
    public void ajouterArtiste(Artiste a);

    public void modifierArtiste(Artiste a, int id);

    public void supprimerArtiste(int id);

    public List<Artiste> afficherArtiste();

    public Artiste rechArtiste(int id);
    
}
